package com.app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public final class ImageProcessor
{
	private static Image changeImageColor(Image image, float[] area, int l, int h, Color[][] NewColor)
	{
		BufferedImage bufferedImage = toBufferedImage(image);
		
		for (int i = (int)(area[0]*l); i <= (int)(area[2]*l) - 1; i += 1)
		{
			for (int j = (int)(area[1]*h); j <= (int)(area[3]*h) - 1; j += 1)
			{
				bufferedImage.setRGB(i, j, NewColor[i][j].getRGB());
			}
		}
		
		return bufferedImage;
	}

	public static Image processImage(Image originalImage)
	{
		Category[] Cat = new Category[5];
		Cat[0] = new Category("Telhado", new int[][] {{144, 255}, {103, 197}, {84, 170}}, new int[][] {{32, 82}, {44, 115}, {8, 42}});
		Cat[1] = new Category("Rua", new int[][] {{77, 181}, {68, 183}, {59, 174}}, new int[][] {{-2, 13}, {-4, 23}, {-9, 17}});
		Cat[2] = new Category("AreaVerde", new int[][] {{37, 180}, {70, 189}, {38, 155}}, new int[][] {{-56, -2}, {-15, 35}, {26, 51}});
		Cat[3] = new Category("Sombra", new int[][] {{0, 0}, {0, 0}, {0, 0}}, new int[][] {{0, 0}, {0, 0}, {0, 0}});
		Cat[4] = new Category("Luz", new int[][] {{255, 255}, {255, 255}, {255, 255}}, new int[][] {{0, 0}, {0, 0}, {0, 0}});
		String[] Cats = new String[Cat.length];
		for (int cat = 0; cat <= Cat.length - 1; cat += 1)
		{
			Cats[cat] = Cat[cat].getName();
		}
		int ImageL = (int)(originalImage.getWidth(null)), ImageH = (int)(originalImage.getHeight(null));	// dimensions of the image in pixels
		float[] area = new float[] {0, 0, 1, 1};	// area of the image to be considered
		int[] NPixels = new int[] {(int) ((area[2] - area[0])*ImageL), (int) ((area[3] - area[1])*ImageH)};
		String[][] PixelClassification = new String[NPixels[0]][NPixels[1]];
		Color[][] PixelNewColor = new Color[NPixels[0]][NPixels[1]];

		PixelClassification = PixelClassifier.intelligentlyClassifyPixels(toBufferedImage(originalImage), area, ImageL, ImageH, NPixels);
		System.out.println("Pixels classificados!");
		for (int i = (int)(area[0]*ImageL); i <= (int)(area[2]*ImageL) - 1; i += 1)
		{
			for (int j = (int)(area[1]*ImageH); j <= (int)(area[3]*ImageH) - 1; j += 1)
			{
				PixelNewColor[i][j] = Cat[indexOf(Cats, PixelClassification[i][j])].getcolor();
			}
		}
		System.out.println("Pixels recoloridos!");
		Image processedImage = changeImageColor(originalImage, area, ImageL, ImageH, PixelNewColor);
		System.out.println("Nova imagem gerada!");
        return processedImage ;
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
    
    private static int indexOf(String[] Vector, String Value)
	{
		if (Vector != null)
		{
			for (int i = 0; i <= Vector.length - 1; ++i)
			{
				if (Vector[i].equals(Value))
				{
					return i;
				}
			}
		}
		return -1;
	}
}
