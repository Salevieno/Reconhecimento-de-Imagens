package com.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JLabel;

import com.graphics.DrawFunctions;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private static MainPanel mainPanel ;
    private Image originalImage;
    private Image processedImage ;
    private final JLabel statusLabel;
	
	private MainPanel()
	{
        statusLabel = new JLabel("Adicione uma imagem para iniciar o processo");
        add(statusLabel);
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

    public void updateOriginalImage(Image image)
    {
        originalImage = image;
        repaint();
    }

    public void clearProcessedImage()
    {
        updateProcessedImage(null);
    }

    public void updateStatus(String status)
    {
        statusLabel.setText(status);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        DrawFunctions DF = new DrawFunctions(g) ; // TODO
        if (originalImage != null)
        {
            int imageTop = 60;
            int originalLeft = 50;
            DF.DrawImage(originalImage,
                    new int[] {originalLeft, imageTop + originalImage.getHeight(null)}, "Left");
            g.drawString("Original", originalLeft, imageTop - 8);

            if (processedImage != null)
            {
                int processedLeft = originalLeft + originalImage.getWidth(null) + 40;
                DF.DrawImage(processedImage,
                        new int[] {processedLeft, imageTop + processedImage.getHeight(null)}, "Left");
                g.drawString("Processada", processedLeft, imageTop - 8);
            }
        }
    }
}
