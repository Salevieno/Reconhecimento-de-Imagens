package com.app;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;

public final class PixelClassifier
{
    private static final String[] CATEGORIAS = ResourceBundle.getBundle("application").getString("CATEGORIAS").split(";") ;
    private static final NeuralNetwork NEURAL_NETWORK = new NeuralNetwork(CATEGORIAS) ;

	public static String[][] intelligentlyClassifyPixels(BufferedImage image, float[] area, int width, int height, int[] pixelDimensions)
	{
		String[][] classification = new String[pixelDimensions[0]][pixelDimensions[1]];

		for (int x = (int)(area[0]*width); x <= (int)(area[2]*width) - 1; x += 1)
		{
			for (int y = (int)(area[1]*height); y <= (int)(area[3]*height) - 1; y += 1)
			{
				Color pixelColor = getPixelColor(image, new int[] {x, y});
				classification[x][y] = NEURAL_NETWORK.forwardPropagation(new double[] {pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()});
			}
		}

		return classification;
	}

	private static Color getPixelColor(BufferedImage Bufferedimage, int[] Pos)
	{
		int clr = Bufferedimage.getRGB(Pos[0], Pos[1]);
		int red = (clr & 0x00ff0000) >> 16;
		int green = (clr & 0x0000ff00) >> 8;
		int blue = clr & 0x000000ff;
		return new Color(red, green, blue);
	}
}