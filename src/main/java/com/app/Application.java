package com.app;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.ui.MainFrame;
import com.ui.MainPanel;


public class Application
{
	private static Image originalImage = new ImageIcon("teste.png").getImage();
	
	private Application() { MainFrame.create() ;}

    public static void start() { new Application() ;}

    public static void run()
    {
        Long initialTime = System.nanoTime() ;
        Image processedImage = ImageProcessor.processImage(originalImage);
        System.out.println("Processing time = " + (System.nanoTime() - initialTime) * Math.pow(10, -6) + " ms");
        MainPanel.getInstance().updateProcessedImage(processedImage) ;
    }
}