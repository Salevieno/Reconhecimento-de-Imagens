package Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Components.Category;
import Graphics.DrawFunctions;


public class ReconhecimentoDeImagens extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel2;
	Image OriginalImage = new ImageIcon("teste.png").getImage();
	private Image ModifiedImage;
	private Image GrayScaleImage1;
	private Image GrayScaleImage2;
	private boolean RunProgram = false, DrawResult = false;
	private DrawFunctions DF;
	
	public ReconhecimentoDeImagens(int[] WinDim) 
	{
		initComponents();	// Creates a JPanel inside the JFrame
		AddButtons();
		setTitle("Reconhecimento de imagens");	// Set main window title
		setSize(WinDim[0], WinDim[1]);		// Set main window size
		setLocation(new Point(-34, 83));
		setVisible(true);					// Show main window
		Initialization();
	}
	
	public void Initialization()
	{
		
	}
		
	public void AddButtons()
	{	
		/* Defining Button Icons */
		String ImagesPath = ".\\Icons\\";
		ImageIcon PlayIcon = new ImageIcon(ImagesPath + "PlayIcon.png");
		
		/* Defining Buttons */
		Color BackgroundColor = Color.cyan;
		JButton PlayButton = Utg.AddButton(PlayIcon, new int[2], new int[] {30, 30}, BackgroundColor);
		Container cp = getContentPane();
		cp.add(PlayButton);
		
		/* Defining button actions */
		PlayButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				RunProgram = !RunProgram;
			}
		});
	}
	
	public static void GetColorRange()
	{
		Image image = new ImageIcon("C:\\Users\\SALVIO.005867\\Desktop\\teste2.png").getImage();
		int imageL = (int)(image.getWidth(null)), imageH = (int)(image.getHeight(null));	// dimensions of the image in pixels
		Color[][] PixelColor = new Color[imageL][imageH];
		int[] PixelRed = new int[imageL * imageH], PixelGreen = new int[imageL * imageH], PixelBlue = new int[imageL * imageH];
		int[] DRedGreen = new int[imageL * imageH], DRedBlue = new int[imageL * imageH], DGreenBlue = new int[imageL * imageH];
		BufferedImage Bimage = Utg.toBufferedImage(image);
		for (int i = 0; i <= imageL - 1; i += 1)
		{
			for (int j = 0; j <= imageH - 1; j += 1)
			{
				PixelColor[i][j] = Utg.GetPixelColor(Bimage, new int[] {i, j});
				PixelRed[i * imageH + j] = PixelColor[i][j].getRed();
				PixelGreen[i * imageH + j] = PixelColor[i][j].getGreen();
				PixelBlue[i * imageH + j] = PixelColor[i][j].getBlue();
				DRedGreen[i * imageH + j] = PixelColor[i][j].getRed() - PixelColor[i][j].getGreen();
				DRedBlue[i * imageH + j] = PixelColor[i][j].getRed() - PixelColor[i][j].getBlue();
				DGreenBlue[i * imageH + j] = PixelColor[i][j].getGreen() - PixelColor[i][j].getBlue();
			}
		}
		
		int RedMin = -1, RedMax = -1, GreenMin = -1, GreenMax = -1, BlueMin = -1, BlueMax = -1;
		int DRedGreenMin = -1, DRedGreenMax = -1, DRedBlueMin = -1, DRedBlueMax = -1, DGreenBlueMin = -1, DGreenBlueMax = -1;
		RedMin = Utg.FindMin(PixelRed);
		RedMax = Utg.FindMax(PixelRed);
		GreenMin = Utg.FindMin(PixelGreen);
		GreenMax = Utg.FindMax(PixelGreen);
		BlueMin = Utg.FindMin(PixelBlue);
		BlueMax = Utg.FindMax(PixelBlue);
		
		DRedGreenMin = Utg.FindMin(DRedGreen);
		DRedGreenMax = Utg.FindMax(DRedGreen);
		DRedBlueMin = Utg.FindMin(DRedBlue);
		DRedBlueMax = Utg.FindMax(DRedBlue);
		DGreenBlueMin = Utg.FindMin(DGreenBlue);
		DGreenBlueMax = Utg.FindMax(DGreenBlue);
		System.out.println("Red range: " + RedMin + " to " + RedMax);
		System.out.println("Green range: " + GreenMin + " to " + GreenMax);
		System.out.println("Blue range: " + BlueMin + " to " + BlueMax);
		
		System.out.println("DRedGreen: " + DRedGreenMin + " to " + DRedGreenMax);
		System.out.println("DRedBlue: " + DRedBlueMin + " to " + DRedBlueMax);
		System.out.println("DGreenBlue: " + DGreenBlueMin + " to " + DGreenBlueMax);
	}
	
	public static String GetClosestCat(Category[] Cat, Color PixelColor)
	{
		int ClosestCatId = -1;
		double[] DistToCat = new double[Cat.length - 1];
		double[][] CatAvr = new double[Cat.length - 1][3];
		double[][] CatDAvr = new double[Cat.length - 1][3];
		int PixelDRedGreen = PixelColor.getRed() - PixelColor.getGreen();
		int PixelDRedBlue = PixelColor.getRed() - PixelColor.getBlue();
		int PixelDGreenblue = PixelColor.getGreen() - PixelColor.getBlue();
		
		for (int cat = 0; cat <= Cat.length - 2; cat += 1)
		{
			CatAvr[cat] = new double[] {Cat[cat].getcolor().getRed(), Cat[cat].getcolor().getGreen(), Cat[cat].getcolor().getBlue()};
			CatDAvr[cat] = new double[] {Cat[cat].getDcolor()[0], Cat[cat].getDcolor()[1], Cat[cat].getDcolor()[2]};
			DistToCat[cat] = Math.sqrt(Math.pow(PixelColor.getRed() - CatAvr[cat][0], 2) + Math.pow(PixelColor.getGreen() - CatAvr[cat][1], 2) + Math.pow(PixelColor.getBlue() - CatAvr[cat][2], 2));
			DistToCat[cat] += Math.sqrt(Math.pow(PixelDRedGreen - CatDAvr[cat][0], 2) + Math.pow(PixelDRedBlue - CatDAvr[cat][1], 2) + Math.pow(PixelDGreenblue - CatDAvr[cat][2], 2));
		}
		ClosestCatId = Utg.FindMinIndex(DistToCat);
		
		return Cat[ClosestCatId].getName();
	}
	
	public static int[][] GetAdjacentPixels(String cat, int[] FirstPixel, String[][] PixelClass)
	{
		int[][] AdjPixels = null;
		
		AdjPixels = Utg.AddElem(AdjPixels, FirstPixel);
		/*for (int i = 0; i <= AdjPixels.length - 1; i += 1)
		{
			int[] LeftPixel = new int[] {AdjPixels[i][0] - 1, AdjPixels[i][1]};
			int[] RightPixel = new int[] {AdjPixels[i][0] + 1, AdjPixels[i][1]};
			int[] TopPixel = new int[] {AdjPixels[i][0], AdjPixels[i][1] - 1};
			int[] BotPixel = new int[] {AdjPixels[i][0], AdjPixels[i][1] + 1};
			if (PixelClass[LeftPixel[0]][LeftPixel[1]].equals(cat) & !Utg.ArrayContains(AdjPixels, LeftPixel))
			{
				AdjPixels = Utg.AddElem(AdjPixels, LeftPixel);
				i = 0;
			}
			if (PixelClass[RightPixel[0]][RightPixel[1]].equals(cat) & !Utg.ArrayContains(AdjPixels, RightPixel))
			{
				AdjPixels = Utg.AddElem(AdjPixels, RightPixel);
				i = 0;
			}
			if (PixelClass[TopPixel[0]][TopPixel[1]].equals(cat) & !Utg.ArrayContains(AdjPixels, TopPixel))
			{
				AdjPixels = Utg.AddElem(AdjPixels, TopPixel);
				i = 0;
			}
			if (PixelClass[BotPixel[0]][BotPixel[1]].equals(cat) & !Utg.ArrayContains(AdjPixels, BotPixel))
			{
				AdjPixels = Utg.AddElem(AdjPixels, BotPixel);
				i = 0;
			}
			System.out.println(AdjPixels.length);
		}*/
		
		return AdjPixels;
	}
	
	public static String CategorizeOutput(double output)
	{
		String[] Categories = new String[] {"Telhado", "Rua", "AreaVerde", "Sombra", "Luz"};
		double[] Catpoint = new double[] {0, 0.25, 0.5, 0.75, 1};
		double[] dist = new double[Categories.length];
		for (int cat = 0; cat <= Categories.length - 1; cat += 1)
		{
			dist[cat] = Math.abs(output - Catpoint[cat]);
		}
		int ClosestCat = Utg.FindMinIndex(dist);
		return Categories[ClosestCat];
	}
	
	public static String TrainedANNForwardPropagation(double[] input)
	{
		int[] Nneurons = new int[] {3, 3, 2, 2, 1};
		int Nlayers = Nneurons.length;
		double[][][] weight = new double[4][][];
		/*weight[0] = new double[][] {{-2.8127509970407494, -1.4585013497756838, -3.11200054100099}, {0.9122554326872762, 13.841518695031766, -20.31743877749188}, {16.292115782700606, -3.9281601772969825, -18.329061720964862}};
		weight[1] = new double[][] {{2.1448028463057374, -6.753826280049139, 4.692927243044666}, {-0.6475791055198662, 4.79499357126936, -4.454666018055507}};
		weight[2] = new double[][] {{-13.247711086859614, 2.22116067348402}, {1.8133967914093816, -12.166563641981782}};
		weight[3] = new double[][] {{12.107837350649358, -9.895927018742622}};*/
		
		weight[0] = new double[][] {{6.047935003742251, -16.344671292575633, 0.540871118148459}, {-3.017974685839781, 1.6806598872748106, -0.01377111810712359}, {-10.827545739418936, 14.958723192225474, -2.297070062090363}};
		weight[1] = new double[][] {{-1.7495918036826112, -12.515272406952201, 2.5304196447491782}, {-19.705724197658128, -3.6071472898510986, -1.90020017116109}};
		weight[2] = new double[][] {{3.916268369868793, -9.698429274138109}, {-3.346849787421948, 7.869689404492559}};
		weight[3] = new double[][] {{13.819136898327812, -11.96207647687522}};
		double[][] neuronvalue = new double[Nlayers][];
		neuronvalue[0] = new double[3];
		double output = -1;
		String cat = "";
		for (int cor = 0; cor <= 3 - 1; cor += 1)
		{
			neuronvalue[0][cor] = input[cor] / 255.0;
		}
		for (int layer = 1; layer <= Nlayers - 1; layer += 1)
		{
			neuronvalue[layer] = Utg.VecMatrixProd(neuronvalue[layer - 1], weight[layer - 1]);
			for (int n = 0; n <= Nneurons[layer] - 1; n += 1)
			{
				neuronvalue[layer][n] = Utg.act(neuronvalue[layer][n]);
			}
		}
		output = neuronvalue[Nlayers - 1][0];
		cat = CategorizeOutput(output);
	
		return cat;
	}
	
	public static Image ChangeImageColor(Image image, float[] area, int l, int h, Color[][] NewColor)
	{
		BufferedImage BufferedFile = Utg.toBufferedImage(image);
		
		for (int i = (int)(area[0]*l); i <= (int)(area[2]*l) - 1; i += 1)
		{
			for (int j = (int)(area[1]*h); j <= (int)(area[3]*h) - 1; j += 1)
			{
				BufferedFile.setRGB(i, j, NewColor[i][j].getRGB());
			}
		}
		
		Image A = BufferedFile;
		return A;
	}
	
	public static Image toGrayScale1(BufferedImage image)
	{
		BufferedImage newImage = image;
		
		for (int i = 0; i <= image.getWidth(null) - 1; i += 1)
		{
			for (int j = 0; j <= image.getHeight(null) - 1; j += 1)
			{
				Color PixelColor = Utg.GetPixelColor(image, new int[] {i, j});
				int luminance = (PixelColor.getRed() + PixelColor.getGreen() + PixelColor.getBlue()) / 3;
				Color newColor = new Color (luminance, luminance, luminance);
				newImage.setRGB(i, j, newColor.getRGB());
			}
		}
		
		return newImage;
	}
	
	public static String[][] FindRoofContour(BufferedImage image, float[] area, int ImageL, int ImageH, String[][] PixelClass)
	{
		String[][] NewPixelClass = new String[PixelClass.length][];
		Color[][] PixelColor = new Color[PixelClass.length][];
		for (int i = (int)(area[0]*ImageL); i <= (int)(area[2]*ImageL) - 1; i += 1)
		{
			NewPixelClass[i] = new String[PixelClass[i].length];
			PixelColor[i] = new Color[PixelClass[i].length];
			for (int j = (int)(area[1]*ImageH); j <= (int)(area[3]*ImageH) - 1; j += 1)
			{
				NewPixelClass[i][j] = PixelClass[i][j];
				PixelColor[i][j] = Utg.GetPixelColor(image, new int[] {i, j});
			}
		}
		for (int i = (int)(area[0]*ImageL) + 1; i <= (int)(area[2]*ImageL) - 2; i += 1)
		{
			for (int j = (int)(area[1]*ImageH) + 1; j <= (int)(area[3]*ImageH) - 2; j += 1)
			{
				String CurrentPixel = PixelClass[i][j];
				String TopPixel = PixelClass[i][j - 1];
				String LeftPixel = PixelClass[i - 1][j];
				String RightPixel = PixelClass[i + 1][j];
				String BotPixel = PixelClass[i][j + 1];
				boolean Condition1 = !CurrentPixel.equals("Telhado") & !CurrentPixel.equals("Roof contour") & LeftPixel.equals("Telhado");
				boolean Condition2 = !CurrentPixel.equals("Telhado") & !CurrentPixel.equals("Roof contour") & RightPixel.equals("Telhado");
				boolean Condition3 = !CurrentPixel.equals("Telhado") & !CurrentPixel.equals("Roof contour") & TopPixel.equals("Telhado");
				boolean Condition4 = !CurrentPixel.equals("Telhado") & !CurrentPixel.equals("Roof contour") & BotPixel.equals("Telhado");
				if (Condition1)
				{
					NewPixelClass[i - 1][j] = "Roof contour";
				}
				if (Condition2)
				{
					NewPixelClass[i + 1][j] = "Roof contour";
				}
				if (Condition3)
				{
					NewPixelClass[i][j - 1] = "Roof contour";
				}
				if (Condition4)
				{
					NewPixelClass[i][j + 1] = "Roof contour";
				}
			}
		}

		for (int i = (int)(area[0]*ImageL) + 1; i <= (int)(area[2]*ImageL) - 2; i += 1)
		{
			for (int j = (int)(area[1]*ImageH) + 1; j <= (int)(area[3]*ImageH) - 2; j += 1)
			{
				String CurrentPixel = NewPixelClass[i][j];
				String TopPixel = NewPixelClass[i][j - 1];
				String LeftPixel = NewPixelClass[i - 1][j];
				String RightPixel = NewPixelClass[i + 1][j];
				String BotPixel = NewPixelClass[i][j + 1];
				String TopLeftPixel = NewPixelClass[i - 1][j - 1];
				String TopRightPixel = NewPixelClass[i + 1][j - 1];
				String BotLeftPixel = NewPixelClass[i - 1][j + 1];
				String BotRightPixel = NewPixelClass[i + 1][j + 1];
				
				Color CurrentColor = PixelColor[i][j];
				Color TopColor = PixelColor[i][j - 1];
				Color LeftColor = PixelColor[i - 1][j];
				Color RightColor = PixelColor[i + 1][j];
				Color BotColor = PixelColor[i][j + 1];
				Color TopLeftColor = PixelColor[i - 1][j - 1];
				Color TopRightColor = PixelColor[i + 1][j - 1];
				Color BotLeftColor = PixelColor[i - 1][j + 1];
				Color BotRightColor = PixelColor[i + 1][j + 1];
				boolean Condition1 = CurrentPixel.equals("Telhado") & LeftPixel.equals("Telhado");
				boolean Condition2 = CurrentPixel.equals("Telhado") & RightPixel.equals("Telhado");
				boolean Condition3 = CurrentPixel.equals("Telhado") & TopPixel.equals("Telhado");
				boolean Condition4 = CurrentPixel.equals("Telhado") & BotPixel.equals("Telhado");
				boolean Condition5 = CurrentPixel.equals("Telhado") & TopLeftPixel.equals("Telhado");
				boolean Condition6 = CurrentPixel.equals("Telhado") & TopRightPixel.equals("Telhado");
				boolean Condition7 = CurrentPixel.equals("Telhado") & BotLeftPixel.equals("Telhado");
				boolean Condition8 = CurrentPixel.equals("Telhado") & BotRightPixel.equals("Telhado");
				
				boolean Condition01 = 5 < Math.abs(CurrentColor.getRed() - LeftColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - LeftColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - LeftColor.getBlue());
				boolean Condition02 = 5 < Math.abs(CurrentColor.getRed() - RightColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - RightColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - RightColor.getBlue());
				boolean Condition03 = 5 < Math.abs(CurrentColor.getRed() - TopColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - TopColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - TopColor.getBlue());
				boolean Condition04 = 5 < Math.abs(CurrentColor.getRed() - BotColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - BotColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - BotColor.getBlue());
				boolean Condition05 = 5 < Math.abs(CurrentColor.getRed() - TopLeftColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - TopLeftColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - TopLeftColor.getBlue());
				boolean Condition06 = 5 < Math.abs(CurrentColor.getRed() - TopRightColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - TopRightColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - TopRightColor.getBlue());
				boolean Condition07 = 5 < Math.abs(CurrentColor.getRed() - BotLeftColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - BotLeftColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - BotLeftColor.getBlue());
				boolean Condition08 = 5 < Math.abs(CurrentColor.getRed() - BotRightColor.getRed()) & 5 < Math.abs(CurrentColor.getGreen() - BotRightColor.getGreen()) & 5 < Math.abs(CurrentColor.getBlue() - BotRightColor.getBlue());
				if (Condition1 & Condition3 & Condition4 & Condition5 & Condition6 & Condition7 & Condition8 & Condition01)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
				if (Condition2 & Condition3 & Condition4 & Condition5 & Condition6 & Condition7 & Condition8 & Condition02)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
				if (Condition1 & Condition2 & Condition3 & Condition5 & Condition6 & Condition7 & Condition8 & Condition03)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
				if (Condition1 & Condition2 & Condition4 & Condition5 & Condition6 & Condition7 & Condition8 & Condition04)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
				if (Condition1 & Condition2 & Condition3 & Condition4 & Condition5 & Condition6 & Condition7 & Condition05)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
				if (Condition1 & Condition2 & Condition3 & Condition4 & Condition5 & Condition6 & Condition8 & Condition06)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
				if (Condition1 & Condition2 & Condition3 & Condition4 & Condition5 & Condition7 & Condition8 & Condition07)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
				if (Condition1 & Condition2 & Condition3 & Condition4 & Condition6 & Condition7 & Condition8 & Condition08)
				{
					NewPixelClass[i][j] = "Roof contour";
				}
			}
		}
		
		return NewPixelClass;
	}
	
	public static int CountRoofs(BufferedImage image, String[][] PixelClass)
	{
		int NRoofs = 0;
		boolean counting = true;
		int imageL = (int)(image.getWidth(null)), imageH = (int)(image.getHeight(null));	// dimensions of the image in pixels
		int MinRoofDim = 30;
		int Limj = -1;
		/*for (int i = 0; i <= imageL - 1; i += MinRoofDim)
		{
			for (int j = 0; j <= imageH - 1; j += MinRoofDim)
			{
				if (PixelClass[i][j].equals("Telhado") & counting)
				{
					NRoofs += 1;
					counting = false;
				}
				if (PixelClass[i][j].equals("Roof contour"))
				{
					Limj = j;
				}
				if (Limj + MinRoofDim < j)
				{
					Limj = -1;
					counting = true;
				}
			}
		}*/
		return NRoofs;
	}
	
	public static String[][] PixelClassification(BufferedImage image, float[] area, int l, int h, int[] NPixels, Category[] Cat)
	{
		String[][] PixelClassification = new String[NPixels[0]][NPixels[1]];
		
		for (int i = (int)(area[0]*l); i <= (int)(area[2]*l) - 1; i += 1)
		{
			for (int j = (int)(area[1]*h); j <= (int)(area[3]*h) - 1; j += 1)
			{
				Color PixelColor = Utg.GetPixelColor(image, new int[] {i, j});
				int PixelDRedGreen = PixelColor.getRed() - PixelColor.getGreen();
				int PixelDRedBlue = PixelColor.getRed() - PixelColor.getBlue();
				int PixelDGreenblue = PixelColor.getGreen() - PixelColor.getBlue();
				for (int cat = 0; cat <= 3 - 1; cat += 1)
				{
					int[][] CatRange = Cat[cat].getColorRange();
					int[][] CatDRange = Cat[cat].getColorDRange();
					if (CatRange[0][0] <= PixelColor.getRed() & PixelColor.getRed() <= CatRange[0][1] & CatRange[1][0] <= PixelColor.getGreen() & PixelColor.getGreen() <= CatRange[1][1] & CatRange[2][0] <= PixelColor.getBlue() & PixelColor.getBlue() <= CatRange[2][1])
					{
						if (CatDRange[0][0] <= PixelDRedGreen & PixelDRedGreen <= CatDRange[0][1] & CatDRange[1][0] <= PixelDRedBlue & PixelDRedBlue <= CatDRange[1][1] & CatDRange[2][0] <= PixelDGreenblue & PixelDGreenblue <= CatDRange[2][1])
						{
							PixelClassification[i][j] = Cat[cat].getName();
							cat = 3;
						}
					}
				}
				if (PixelClassification[i][j] == null)
				{					
					PixelClassification[i][j] = GetClosestCat(Cat, PixelColor);
				}
			}
		}
		
		return PixelClassification;
	}
	
	public static String[][] IntelligentPixelClassification(BufferedImage image, float[] area, int l, int h, int[] NPixels, Category[] Cat)
	{
		String[][] PixelClassification = new String[NPixels[0]][NPixels[1]];
		
		for (int i = (int)(area[0]*l); i <= (int)(area[2]*l) - 1; i += 1)
		{
			for (int j = (int)(area[1]*h); j <= (int)(area[3]*h) - 1; j += 1)
			{
				Color PixelColor = Utg.GetPixelColor(image, new int[] {i, j});
				PixelClassification[i][j] = TrainedANNForwardPropagation(new double[] {PixelColor.getRed(), PixelColor.getGreen(), PixelColor.getBlue()});
			}
		}
		
		return PixelClassification;
	}
	
	public void GetPixelColor()
	{
		ModifiedImage = new ImageIcon("teste.png").getImage();
		
	}
	
	public void ReconhecimentoDeImagem()
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
		int ImageL = (int)(OriginalImage.getWidth(null)), ImageH = (int)(OriginalImage.getHeight(null));	// dimensions of the image in pixels
		float[] area = new float[] {0, 0, 1, 1};	// area of the image to be considered
		int[] NPixels = new int[] {(int) ((area[2] - area[0])*ImageL), (int) ((area[3] - area[1])*ImageH)};
		String[][] PixelClassification = new String[NPixels[0]][NPixels[1]];
		Color[][] PixelNewColor = new Color[NPixels[0]][NPixels[1]];

		//PixelClassification = PixelClassification(Utg.toBufferedImage(OriginalImage), area, ImageL, ImageH, NPixels, Cat);
		PixelClassification = IntelligentPixelClassification(Utg.toBufferedImage(OriginalImage), area, ImageL, ImageH, NPixels, Cat);		
		System.out.println("Pixels classificados!");
		//PixelClassification = FindRoofContour(Utg.toBufferedImage(OriginalImage), area, ImageL, ImageH, PixelClassification);
		//System.out.println("Contorno dos telhados encontrados!");
		for (int i = (int)(area[0]*ImageL); i <= (int)(area[2]*ImageL) - 1; i += 1)
		{
			for (int j = (int)(area[1]*ImageH); j <= (int)(area[3]*ImageH) - 1; j += 1)
			{
				PixelNewColor[i][j] = Cat[Utg.IndexOf(Cats, PixelClassification[i][j])].getcolor();
			}
		}
		System.out.println("Pixels recoloridos!");
		ModifiedImage = ChangeImageColor(OriginalImage, area, ImageL, ImageH, PixelNewColor);
		System.out.println("Nova imagem gerada!");
		//int NRoofs = CountRoofs(Utg.toBufferedImage(ModifiedImage), PixelClassification);
		//System.out.println("Número de telhados: " + NRoofs);
		GrayScaleImage1 = toGrayScale1(Utg.toBufferedImage(OriginalImage));
		GrayScaleImage2 = Utg.toGrayScale(Utg.toBufferedImage(OriginalImage));
	}
	
	public void DrawResults(Image OriginalImage, Image ModifiedImage)
	{
		int H1 = OriginalImage.getHeight(null);
		int H2 = ModifiedImage.getHeight(null);
		//DF.DrawImage(OriginalImage, new int[] {50,  50 + H1}, "Left");
		DF.DrawImage(ModifiedImage, new int[] {250,  50 + H2}, "Left");
		//DF.DrawImage(GrayScaleImage1, new int[] {450,  50 + H2}, "Left");
		//DF.DrawImage(GrayScaleImage2, new int[] {650,  50 + H2}, "Left");
		//DF.DrawColorScheme();
	}
	
	class Panel extends JPanel 
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Panel() 
	    {
	        setPreferredSize(new Dimension(700, 700));	// set a preferred size for the custom panel.
	    }
		@Override
		public void paintComponent(Graphics g)
		{
	        super.paintComponent(g);
	        DF = new DrawFunctions(g);
	        if (RunProgram)
		    {
	        	ReconhecimentoDeImagem();
	        	//GetPixelColor();
	        	//GetColorRange();
	    		RunProgram = false;
	    		DrawResult = true;
		    }
	        if (DrawResult)
		    {
	        	DrawResults(OriginalImage, ModifiedImage);
		    }
	        //DrawStuff(WinDim);
	        repaint();
	    }
	}

	private void initComponents() 
	{     
	    jPanel2 = new Panel();	// we want a custom Panel2, not a generic JPanel!
	    jPanel2.setBackground(new Color(250, 240, 220));
	    jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	    jPanel2.addMouseListener(new MouseAdapter() 
	    {
			public void mousePressed(MouseEvent evt)
			{
				int[] MousePos = Utg.GetRelMousePos(new int[] {0, 114});
				Color PixelColor = Utg.GetPixelColor(Utg.toBufferedImage(ModifiedImage), new int[] {MousePos[0] - 24, MousePos[1] - 50});
				System.out.println(PixelColor.getRed() + " " + PixelColor.getGreen() + " " + PixelColor.getBlue());
			}
			public void mouseReleased(MouseEvent evt) 
			{
			    //mouseReleased(evt);
		    }
		});
	    jPanel2.addMouseMotionListener(new MouseMotionAdapter() 
	    {
	        public void mouseDragged(MouseEvent evt) 
	        {
	            //mouseDragged(evt);
	        }
	    });
	    jPanel2.addMouseWheelListener(new MouseWheelListener()
	    {
			@Override
			public void mouseWheelMoved(MouseWheelEvent evt) 
			{
				
			}       	
	    });
	    jPanel2.addKeyListener(new KeyListener()
	    {
			@Override
			public void keyPressed(KeyEvent evt)
			{
				int key = evt.getKeyCode();
				System.out.println(1);
				if (key == KeyEvent.VK_ESCAPE)
				{
					
				}
			}
	
			@Override
			public void keyReleased(KeyEvent evt)
			{
				
			}
	
			@Override
			public void keyTyped(KeyEvent evt)
			{
				
			}        	
	    });
	    this.setContentPane(jPanel2);	// add the component to the frame to see it!
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
