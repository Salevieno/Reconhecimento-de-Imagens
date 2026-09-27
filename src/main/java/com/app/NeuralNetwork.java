package com.app;

public class NeuralNetwork
{
    private final int[] neuronsPerLayer ;
	private final int layerCount ;
    private final double[][][] weights ;
	private final String[] categories ;
    private final double[] categoryPoints ;

    public NeuralNetwork(String[] categories)
    {
        this.neuronsPerLayer = new int[] {3, 3, 2, 2, 1} ;
	    this.layerCount = neuronsPerLayer.length ;
        this.weights = new double[4][][] ;
		weights[0] = new double[][] {{6.047935003742251, -16.344671292575633, 0.540871118148459}, {-3.017974685839781, 1.6806598872748106, -0.01377111810712359}, {-10.827545739418936, 14.958723192225474, -2.297070062090363}};
		weights[1] = new double[][] {{-1.7495918036826112, -12.515272406952201, 2.5304196447491782}, {-19.705724197658128, -3.6071472898510986, -1.90020017116109}};
		weights[2] = new double[][] {{3.916268369868793, -9.698429274138109}, {-3.346849787421948, 7.869689404492559}};
		weights[3] = new double[][] {{13.819136898327812, -11.96207647687522}};
        this.categories = categories ;
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

	public String forwardPropagation(double[] input)
	{
		double[][] neuronValues = new double[layerCount][];
		neuronValues[0] = new double[3];

		for (int color = 0; color <= 3 - 1; color += 1)
		{
			neuronValues[0][color] = input[color] / 255.0;
		}
		for (int layer = 1; layer <= layerCount - 1; layer += 1)
		{
			neuronValues[layer] = vecMatrixProd(neuronValues[layer - 1], weights[layer - 1]);
			for (int neuron = 0; neuron <= neuronsPerLayer[layer] - 1; neuron += 1)
			{
				neuronValues[layer][neuron] = act(neuronValues[layer][neuron]);
			}
		}

		return categorizeOutput(neuronValues[layerCount - 1][0]);
	}

    private static double[] vecMatrixProd(double[] vector, double[][] matrix)
	{
		if (vector.length != matrix[0].length)
		{
			System.out.println("Attempted to multiply matrices of different sizes at UtilGeral -> MatrixProd");
			System.out.println("Vector size: " + vector.length + " Matrix size : " + matrix[0].length);
			return null;
		}
		
        double product[] = new double[matrix.length];
        for (int i = 0; i <= matrix.length - 1; i += 1)
        {
            for (int j = 0; j <= vector.length - 1; j += 1)
            {
                product[i] += vector[j] * matrix[i][j];
            }
        }
        return product ;
	}

	private static int findMinIndex(double[] Vector)
	{
		int MinId = -1;
		double Min = Vector[0];
		for (int s = 0; s <= Vector.length - 1; s += 1)
        {
			if (Vector[s] <= Min)
			{
				MinId = s;
				Min = Vector[s];
			}
        }
		return MinId;
	}

	private String categorizeOutput(double output)
	{
		double[] distances = new double[categories.length];
		for (int category = 0; category <= categories.length - 1; category += 1)
		{
			distances[category] = Math.abs(output - categoryPoints[category]);
		}
		return categories[findMinIndex(distances)];
	}
}
