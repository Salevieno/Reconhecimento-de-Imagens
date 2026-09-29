package com.app;

public class NeuralNetwork
{
    private final double[][][] weights ;
    private final double[] categoryPoints ;

    public NeuralNetwork(String[] categories)
    {
        this.weights = new double[4][][] ;
		weights[0] = new double[][] {{6.047935003742251, -16.344671292575633, 0.540871118148459}, {-3.017974685839781, 1.6806598872748106, -0.01377111810712359}, {-10.827545739418936, 14.958723192225474, -2.297070062090363}};
		weights[1] = new double[][] {{-1.7495918036826112, -12.515272406952201, 2.5304196447491782}, {-19.705724197658128, -3.6071472898510986, -1.90020017116109}};
		weights[2] = new double[][] {{3.916268369868793, -9.698429274138109}, {-3.346849787421948, 7.869689404492559}};
		weights[3] = new double[][] {{13.819136898327812, -11.96207647687522}};
		this.categoryPoints = new double[categories.length] ;
        for (int i = 0 ; i <= categories.length - 1 ; i += 1)
        {
            categoryPoints[i] = i * 1.0 / (categories.length - 1) ;
        }
    }

	private static double act(double x)
	{
		return 1.0 / (1.0 + Math.exp(-x));
	}

	protected int classifyRgb(double red, double green, double blue)
	{
		red = red / 255.0;
		green = green / 255.0;
		blue = blue / 255.0;

        double output = forwardPropagation(red, green, blue) ;

		return categorizeOutput(output);
	}

    private double forwardPropagation(double red, double green, double blue)
    {
		double layerOne0 = act(weightedSum3(red, green, blue, weights[0][0]));
		double layerOne1 = act(weightedSum3(red, green, blue, weights[0][1]));
		double layerOne2 = act(weightedSum3(red, green, blue, weights[0][2]));

		double layerTwo0 = act(weightedSum3(layerOne0, layerOne1, layerOne2, weights[1][0]));
		double layerTwo1 = act(weightedSum3(layerOne0, layerOne1, layerOne2, weights[1][1]));

		double layerThree0 = act(weightedSum2(layerTwo0, layerTwo1, weights[2][0]));
		double layerThree1 = act(weightedSum2(layerTwo0, layerTwo1, weights[2][1]));
		double output = act(weightedSum2(layerThree0, layerThree1, weights[3][0]));

        return output ;
    }

	private static double weightedSum3(double first, double second, double third, double[] weights)
	{
		double sum = 0.0;
		sum += first * weights[0];
		sum += second * weights[1];
		sum += third * weights[2];
		return sum;
	}

	private static double weightedSum2(double first, double second, double[] weights)
	{
		double sum = 0.0;
		sum += first * weights[0];
		sum += second * weights[1];
		return sum;
	}

	private int categorizeOutput(double output)
	{
		int minimumIndex = -1;
		double minimumDistance = Math.abs(output - categoryPoints[0]);
		for (int category = 0; category < categoryPoints.length; category++)
		{
			double distance = Math.abs(output - categoryPoints[category]);
			if (distance <= minimumDistance)
			{
				minimumIndex = category;
				minimumDistance = distance;
			}
		}
		return minimumIndex;
	}
}
