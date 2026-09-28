package com.app;

import java.awt.Image;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

import com.ui.MainFrame;

public class Application
{
	private static Image originalImage = new ImageIcon("teste.png").getImage();
	private static boolean processing;
	
	private Application() { MainFrame.create() ;}

    public static void start() { new Application() ;}

    public interface ProcessingListener
    {
        void processingStarted();
        void processingSucceeded(Image image, double elapsedMilliseconds);
        void processingFailed(Exception exception);
        void processingFinished();
    }

    public static synchronized boolean runAsync(ProcessingListener listener)
    {
        if (processing) { return false ;}

        processing = true;
        listener.processingStarted();

        new SwingWorker<ProcessingResult, Void>()
        {
            @Override
            protected ProcessingResult doInBackground()
            {
                long initialTime = System.nanoTime();
                Image processedImage = ImageProcessor.processImage(originalImage);
                double elapsedMilliseconds = (System.nanoTime() - initialTime) / 1_000_000.0;
                return new ProcessingResult(processedImage, elapsedMilliseconds);
            }

            @Override
            protected void done()
            {
                try
                {
                    ProcessingResult result = get();
                    System.out.println("Processing time = " + result.elapsedMilliseconds() + " ms");
                    listener.processingSucceeded(result.image(), result.elapsedMilliseconds());
                }
                catch (InterruptedException exception)
                {
                    Thread.currentThread().interrupt();
                    listener.processingFailed(exception);
                }
                catch (ExecutionException exception)
                {
                    Throwable cause = exception.getCause();
                    listener.processingFailed(cause instanceof Exception
                            ? (Exception) cause
                            : new RuntimeException(cause));
                }
                finally
                {
                    synchronized (Application.class)
                    {
                        processing = false;
                    }
                    listener.processingFinished();
                }
            }
        }.execute();

        return true;
    }

    private record ProcessingResult(Image image, double elapsedMilliseconds)
    {
    }
}