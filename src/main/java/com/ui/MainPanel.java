package com.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
    private static final int MAX_DISPLAY_WIDTH = 800;
    private static final int MAX_DISPLAY_HEIGHT = 800;
    private static final int IMAGE_LEFT_MARGIN = 50;
    private static final int IMAGE_GAP = 40;
    private static final int IMAGE_TOP = 60;
    private static final int IMAGE_BOTTOM_MARGIN = 50;

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
        if (originalImage == null) { return ;}

        int maxDisplayWidth = Math.min(MAX_DISPLAY_WIDTH,
                (getWidth() - 2 * IMAGE_LEFT_MARGIN - IMAGE_GAP) / 2);
        int maxDisplayHeight = Math.min(MAX_DISPLAY_HEIGHT,
                getHeight() - IMAGE_TOP - IMAGE_BOTTOM_MARGIN);

        if (maxDisplayWidth <= 0 || maxDisplayHeight <= 0) { return ;}

        int largestImageWidth = originalImage.getWidth(null);
        int largestImageHeight = originalImage.getHeight(null);
        if (processedImage != null)
        {
            largestImageWidth = Math.max(largestImageWidth, processedImage.getWidth(null));
            largestImageHeight = Math.max(largestImageHeight, processedImage.getHeight(null));
        }

        double scale = Math.min(1.0, Math.min(
                maxDisplayWidth / (double) largestImageWidth,
                maxDisplayHeight / (double) largestImageHeight));
        int originalWidth = Math.max(1, (int) Math.round(originalImage.getWidth(null) * scale));
        int originalHeight = Math.max(1, (int) Math.round(originalImage.getHeight(null) * scale));
        int originalLeft = IMAGE_LEFT_MARGIN;
        g.drawImage(originalImage, originalLeft, IMAGE_TOP, originalWidth, originalHeight, this);
        g.drawString("Original", originalLeft, IMAGE_TOP - 8);

        if (processedImage != null)
        {
            int processedWidth = Math.max(1, (int) Math.round(processedImage.getWidth(null) * scale));
            int processedHeight = Math.max(1, (int) Math.round(processedImage.getHeight(null) * scale));
            int processedLeft = originalLeft + originalWidth + IMAGE_GAP;
            g.drawImage(processedImage, processedLeft, IMAGE_TOP,
                    processedWidth, processedHeight, this);
            g.drawString("Processada", processedLeft, IMAGE_TOP - 8);
        }
    }
}
