package com.app;

import java.util.ResourceBundle;

public final class PixelClassifier
{
    private static final String[] CATEGORIAS = ResourceBundle.getBundle("application").getString("CATEGORIAS").split(";") ;
    private static final NeuralNetwork NEURAL_NETWORK = new NeuralNetwork(CATEGORIAS) ;

	static int categoryCount()
	{
		return CATEGORIAS.length;
	}

	static String categoryName(int index)
	{
		return CATEGORIAS[index];
	}

	static int classifyPixel(int argb)
	{
		return NEURAL_NETWORK.classifyRgb((argb >>> 16) & 0xff, (argb >>> 8) & 0xff, argb & 0xff);
	}
}