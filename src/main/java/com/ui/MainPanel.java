package com.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.graphics.DrawFunctions;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private static MainPanel mainPanel ;
    private Image processedImage ;
	
	private MainPanel()
	{
		setFocusable(true) ;
	}
	
	protected static void create(Dimension size)
	{
		if (mainPanel != null) { return ;}

		mainPanel = new MainPanel() ;
	}

	public static MainPanel getInstance() { return mainPanel ;}

    public void updateProcessedImage(Image image)
    {
        processedImage = image ;
        repaint() ;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        DrawFunctions DF = new DrawFunctions(g) ; // TODO
        if (processedImage != null)
        {
            DF.DrawImage(processedImage, new int[] {250,  50 + processedImage.getHeight(null)}, "Left");
        }
    }
}
