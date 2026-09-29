package com.app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public final class ImageProcessor
{
	private static final int[] CATEGORY_COLORS = createCategoryColors();

	private static int[] createCategoryColors()
	{
		int[] categoryColors = new int[PixelClassifier.categoryCount()];
		for (int category = 0; category < categoryColors.length; category++)
		{
			String categoryName = PixelClassifier.categoryName(category);
			categoryColors[category] = switch (categoryName)
			{
				case "Telhado" -> 0xffc7967f;
				case "Rua" -> 0xff817d74;
				case "AreaVerde" -> 0xff6c8160;
				case "Sombra" -> 0xff000000;
				case "Luz" -> 0xffffffff;
				default -> throw new IllegalStateException("No output color configured for category: " + categoryName);
			};
		}
		return categoryColors;
	}

	public static Image processImage(Image originalImage)
	{
		int ImageL = (int)(originalImage.getWidth(null)), ImageH = (int)(originalImage.getHeight(null));	// dimensions of the image in pixels
		BufferedImage bufferedImage = toBufferedImage(originalImage);
		for (int x = 0; x < ImageL; x += 1)
		{
			for (int y = 0; y < ImageH; y += 1)
			{
				int category = PixelClassifier.classifyPixel(bufferedImage.getRGB(x, y));
				bufferedImage.setRGB(x, y, CATEGORY_COLORS[category]);
			}
		}
		return bufferedImage;
    }

    
	private static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}
