package com.app;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingWorker;

public final class ImageProcessingService
{
    private final Image originalImage;
    private final AtomicBoolean processing = new AtomicBoolean();

    public ImageProcessingService(Image originalImage)
    {
        this.originalImage = Objects.requireNonNull(originalImage, "originalImage");
    }

    public boolean runAsync(IProcessingListener listener)
    {
        Objects.requireNonNull(listener, "listener");
        if (!processing.compareAndSet(false, true))
        {
            return false;
        }

        listener.processingStarted();
        new SwingWorker<ProcessingResult, Void>()
        {
            @Override
            protected ProcessingResult doInBackground()
            {
                long initialTime = System.nanoTime();
                Image processedImage = ImageProcessor.processImage(copyImage(originalImage));
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
                    processing.set(false);
                    listener.processingFinished();
                }
            }
        }.execute();

        return true;
    }

    private static BufferedImage copyImage(Image image)
    {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage copy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = copy.createGraphics();
        try
        {
            graphics.drawImage(image, 0, 0, null);
        }
        finally
        {
            graphics.dispose();
        }
        return copy;
    }

    private record ProcessingResult(Image image, double elapsedMilliseconds)
    {
    }
}
