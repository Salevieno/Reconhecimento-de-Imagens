package com.reconhecimentoDeImagens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class Util
{
	/* Color and image methods */

	public static Color[] ColorPalette(int Palette)
	{
		Color[] color = new Color[28];
		if (Palette == 0)
		{
			color[0] = Color.cyan;		// Sky, crystal, menu 1, menu ornaments, player window, quest requirements, bestiary windows, knight
			color[1] = Color.magenta;	// Mage
			color[2] = Color.orange;	// Archer
			color[3] = Color.green;		// Animal, pet 0, grass, plant element, selected text, poison
			color[4] = Color.gray;		// Thief, metal, rock, unavailable stuff, player att window 1
			color[5] = Color.blue;		// ocean, text
			color[6] = Color.red;		// berry, blood, selected bag item, life, level, crit
			color[7] = Color.white;		// intro windows, snow land, choices menu, ok button, player eye
			color[8] = Color.lightGray;	// Elemental NPC color, part of window gradient backgrounds
			color[9] = Color.black;		// Contour, attack animation
			color[10] = Color.yellow;	// Satiation, fire element
			color[11] = Color.pink;		// Petal
			color[12] = new Color(0, 0, 128);	// Player legs
			color[13] = new Color(128, 0, 0);	// Player shoes
			color[14] = new Color(128, 128, 128);	// Player shirt, player arm
			color[15] = new Color(255, 179, 128);	// Player head (skin)
			color[16] = new Color(0, 128, 0);	// Grass contour color, player hair
			color[17] = new Color(200, 50, 200);	// pet 1
			color[18] = new Color(200, 200, 30);	// pet 2 and 3, map, gold color, chest reward animation
			color[19] = new Color(100, 50, 0);	// Soil, wood, bag menu, player window, pterodactile
			color[20] = new Color(150, 100, 0);	// Sand, menu 0, menu 2
			color[21] = new Color(180, 0, 180);	// Herb, exp, magic floor, shopping text
			color[22] = new Color(230, 230, 230);	// Neutral, air, light and snow elements
			color[23] = new Color(0, 50, 0);	// Herb contour
			color[24] = new Color(0, 150, 0);	// Status 1 (poison)
			color[25] = new Color(30, 200, 30);	// Bag text
			color[26] = new Color(0, 150, 255);	// Shopping menu
			color[27] = new Color(200, 200, 255);	// Sign window
		}
		else if (Palette == 1)
		{
			color[0] = new Color(27, 224, 233);		// Sky, crystal, menu 1, menu ornaments, player window, quest requirements, bestiary windows, knight
			color[1] = new Color(230, 46, 150);	// Mage
			color[2] = new Color(244, 162, 25);	// Archer
			color[3] = new Color(29, 237, 29);		// Animal, pet 0, grass, plant element, selected text, poison
			color[4] = new Color(109, 103, 97);		// Thief, metal, rock, unavailable stuff, player att window 1
			color[5] = new Color(63, 40, 231);		// ocean, text
			color[6] = new Color(236, 44, 44);		// berry, blood, selected bag item, life, level, crit
			color[7] = new Color(241, 233, 225);		// intro windows, snow land, choices menu, ok button, player eye
			color[8] = new Color(203, 195, 188);	// Elemental NPC color, part of window gradient backgrounds
			color[9] = new Color(11, 11, 11);		// Contour, attack animation
			color[10] = new Color(234, 237, 27);	// Satiation, fire element
			color[11] = new Color(232, 93, 143);		// Petal
			color[12] = new Color(78, 36, 193);	// Player legs
			color[13] = new Color(167, 28, 70);	// Player shoes
			color[14] = new Color(109, 103, 97);	// Player shirt, player arm
			color[15] = new Color(233, 158, 125);	// Player head (skin)
			color[16] = new Color(46, 192, 81);	// Grass contour color, player hair
			color[17] = new Color(194, 35, 144);	// pet 1
			color[18] = new Color(127, 192, 42);	// pet 2 and 3, map, gold color, chest reward animation
			color[19] = new Color(101, 61, 44);	// Soil, wood, bag menu, player window, pterodactile
			color[20] = new Color(143, 90, 52);	// Sand, menu 0, menu 2
			color[21] = new Color(155, 33, 128);	// Herb, exp, magic floor, shopping text
			color[22] = new Color(137, 249, 204);	// Neutral, air, light and snow elements
			color[23] = new Color(41, 88, 66);	// Herb contour
			color[24] = new Color(46, 192, 81);	// Status 1 (poison)
			color[25] = new Color(174, 242, 94);	// Bag text
			color[26] = new Color(34, 82, 194);	// Shopping menu
			color[27] = new Color(91, 247, 217);	// Sign window
		}
		else if (Palette == 2)
		{
			color[0] = new Color(61, 240, 226);		// Sky, crystal, menu 1, menu ornaments, player window, quest requirements, bestiary windows, knight
			color[1] = new Color(235, 38, 226);	// Mage, petal, pet 1, herb, exp, magic floor, shopping text
			color[2] = new Color(234, 237, 27);	// Archer, satiation, fire element
			color[3] = new Color(122, 236, 67);		// Animal, pet 0, grass, plant element, selected text, poison
			color[4] = new Color(164, 155, 147);		// Thief, metal, rock, wall, unavailable stuff, sign window, player att window 1
			color[5] = new Color(63, 40, 231);		// ocean, text
			color[6] = new Color(236, 44, 44);		// berry, blood, selected bag item, life, level, crit
			color[7] = new Color(241, 233, 225);		// Elemental NPC color, neutral and light elements, intro windows, choices menu, ok button, player eye
			color[8] = new Color(137, 249, 204);	// snow land, air and snow elements, part of window gradient backgrounds
			color[9] = new Color(11, 11, 11);		// Contour, attack animation
			color[10] = new Color(167, 28, 70);	// Lava, volcano
			color[11] = new Color(228, 89, 80);
			color[12] = new Color(245, 117, 170);	// Player legs, herb contour
			color[13] = new Color(43, 45, 99);	// Player shoes
			color[14] = new Color(28, 162, 208);	// Player shirt, player arm
			color[15] = new Color(219, 251, 137);	// Player head (skin)
			color[16] = new Color(41, 88, 66);	// grass contour color, player hair
			color[17] = new Color(241, 199, 128);
			color[18] = new Color(171, 195, 49);	// pet 2 and 3, map, gold color, shopping menu, chest reward animation
			color[19] = new Color(143, 90, 52);	// Soil, wood, bag menu, stalactite, player window, pterodactile
			color[20] = new Color(242, 176, 63);	// Sand, menu 0, menu 2
			color[21] = new Color(247, 224, 143);
			color[22] = new Color(101, 131, 246);
			color[23] = new Color(141, 31, 159);	// Bag, shopping and crafting items text
			color[24] = new Color(48, 99, 97);	//
			color[25] = new Color(76, 131, 42);	//
			color[26] = new Color(242, 91, 168);	//
			color[27] = new Color(105, 50, 50);	//
		}
		return color;
	}

	public static double GamaDecompress(double color)
	{
		if (color < 0.04045)
		{
			return color / 12.92;
		}
		else
		{
			return Math.pow(((color + 0.055) / 1.055), 2.4);
		}
	}

	public static double GamaCompress(double luminance)
	{
		if (luminance < 0.0031308)
		{
			return 12.92 * luminance;
		}
		else
		{
			return Math.pow(1.055 * luminance, 1 / 2.4) - 0.055;
		}
	}

	public static Color[] AddHue(Color[] OriginalColor, double incH, double incV, double incS)
	{
		Color[] NewColors = new Color[OriginalColor.length];

		for (int c = 0; c <= NewColors.length - 1; c += 1)
		{
			float[] HSV = Color.RGBtoHSB(OriginalColor[c].getRed(), OriginalColor[c].getGreen(), OriginalColor[c].getBlue(), null);
			double H = HSV[0], S = HSV[1], V = HSV[2];
			float newH = (float) (H + incH), newS = (float) (S + incS), newV = (float) (V + incV);
			if (newH < 0)
			{
				newH = newH + 1;
			}
			if (1.0 < newH)
			{
				newH = newH - 1;
			}
			if (newS < 0)
			{
				newS = 0;
			}
			if (1.0 < newS)
			{
				newS = 1;
			}
			if (newV < 0)
			{
				newV = 0;
			}
			if (1.0 < newV)
			{
				newV = 1;
			}
			NewColors[c] = new Color(Color.HSBtoRGB(newH, newS, newV));
		}

		return NewColors;
	}

	public static Color[] toGrayScale(Color[] OriginalColor)
	{
		Color[] newColor = new Color[OriginalColor.length];

		for (int c = 0; c <= newColor.length - 1; c += 1)
		{
			double linRed, linGreen, linBlue;
			linRed = GamaDecompress(OriginalColor[c].getRed() / 255.0);
			linGreen = GamaDecompress(OriginalColor[c].getGreen() / 255.0);
			linBlue = GamaDecompress(OriginalColor[c].getBlue() / 255.0);
			double linLuminance = 0.2126 * linRed + 0.7152 * linGreen + 0.0722 * linBlue;
			int luminance = (int) (255 * Util.GamaCompress(linLuminance));
			newColor[c] = new Color (luminance, luminance, luminance);
		}

		return newColor;
	}

	public static Image toGrayScale(BufferedImage image)
	{
		BufferedImage newImage = image;

		for (int i = 0; i <= image.getWidth(null) - 1; i += 1)
		{
			for (int j = 0; j <= image.getHeight(null) - 1; j += 1)
			{
				Color PixelColor = Util.GetPixelColor(image, new int[] {i, j});
				double linRed, linGreen, linBlue;
				linRed = GamaDecompress(PixelColor.getRed() / 255.0);
				linGreen = GamaDecompress(PixelColor.getGreen() / 255.0);
				linBlue = GamaDecompress(PixelColor.getBlue() / 255.0);
				double linLuminance = 0.2126 * linRed + 0.7152 * linGreen + 0.0722 * linBlue;
				int luminance = (int) (255 * Util.GamaCompress(linLuminance));
				Color newColor = new Color (luminance, luminance, luminance);
				newImage.setRGB(i, j, newColor.getRGB());
			}
		}

		return newImage;
	}

	public static BufferedImage toBufferedImage(Image img)
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

	public static Color GetPixelColor(BufferedImage Bufferedimage, int[] Pos)
	{
		int clr = Bufferedimage.getRGB(Pos[0], Pos[1]);
		int red = (clr & 0x00ff0000) >> 16;
		int green = (clr & 0x0000ff00) >> 8;
		int blue = clr & 0x000000ff;
		return new Color(red, green, blue);
	}

	public static Image ChangeImageColor(Image image, float[] area, Color newColor, Color OriginalColor)
	{
		int l = (int)(image.getWidth(null)), h = (int)(image.getHeight(null));
		BufferedImage BufferedFile = toBufferedImage(image);
		for (int i = (int)(area[0]*l); i <= (int)(area[2]*l) - 1; i += 1)
		{
			for (int j = (int)(area[1]*h); j <= (int)(area[3]*h) - 1; j += 1)
			{
				Color PreviousColor = GetPixelColor(BufferedFile, new int[] {i, j});
				if (PreviousColor.getRed() == OriginalColor.getRed() & PreviousColor.getGreen() == OriginalColor.getGreen() & PreviousColor.getBlue() == OriginalColor.getBlue())
				{
					BufferedFile.setRGB(i, j, newColor.getRGB());
				}
			}
		}

		Image A = BufferedFile;
		return A;
	}

	/* Button methods */

	public static JButton AddButton(ImageIcon Icon, int[] Alignment, int[] Size, Color color)
	{
		JButton NewButton = new JButton();
		NewButton.setIcon(Icon);
		NewButton.setVerticalAlignment(Alignment[0]);
		NewButton.setHorizontalAlignment(Alignment[1]);
		NewButton.setBackground(color);
		NewButton.setPreferredSize(new Dimension(Size[0], Size[1]));
		return NewButton;
	}

	/* Text file methods */

	/* Array methods */

	public static int[] AddElem(int[] OriginalArray, int NewElem)
	{
		if (OriginalArray == null)
		{
			return new int[] {NewElem};
		}
		else
		{
			int[] NewArray = new int[OriginalArray.length + 1];
			for (int i = 0; i <= OriginalArray.length - 1; i += 1)
			{
				NewArray[i] = OriginalArray[i];
			}
			NewArray[OriginalArray.length] = NewElem;
			return NewArray;
		}
	}

	public static int[][] AddElem(int[][] OriginalArray, int[] NewElem)
	{
		if (OriginalArray == null)
		{
			return new int[][] {NewElem};
		}
		else
		{
			int[][] NewArray = new int[OriginalArray.length + 1][];
			for (int i = 0; i <= OriginalArray.length - 1; i += 1)
			{
				NewArray[i] = OriginalArray[i];
			}
			NewArray[OriginalArray.length] = NewElem;
			return NewArray;
		}
	}

	public static int FindMin(int[] Vector)
	{
		int Min = Vector[0];
		for (int s = 1; s <= Vector.length - 1; s += 1)
        {
			if (Vector[s] < Min)
			{
				Min = Vector[s];
			}
        }
		return Min;
	}

	public static double FindMin(double[] Vector)
	{
		double Min = Vector[0];
		for (int s = 1; s <= Vector.length - 1; s += 1)
        {
			if (Vector[s] < Min)
			{
				Min = Vector[s];
			}
        }
		return Min;
	}

	public static int FindMax(int[] Vector)
	{
		int Max = Vector[0];
		for (int s = 1; s <= Vector.length - 1; s += 1)
        {
			if (Max < Vector[s])
			{
				Max = Vector[s];
			}
        }
		return Max;
	}

	public static double FindMax(double[] Vector)
	{
		double Max = Vector[0];
		for (int s = 1; s <= Vector.length - 1; s += 1)
        {
			if (Max < Vector[s])
			{
				Max = Vector[s];
			}
        }
		return Max;
	}

	public static double FindMax(double[][][] Array)
	{
		double Max = Array[0][0][0];
		for (int i = 0; i <= Array.length - 1; i += 1)
		{
			for (int j = 0; j <= Array[i].length - 1; j += 1)
			{
				for (int k = 0; k <= Array[i][j].length - 1; k += 1)
				{
					if (Max < Math.abs(Array[i][j][k]))
					{
						Max = Math.abs(Array[i][j][k]);
					}
				}
			}
		}
		return Max;
	}

	public static double MaxAbs(double[] vector)
	{
		double max = 0;
		for (int i = 0; i <= vector.length - 1; i += 1)
		{
			if (max < vector[i])
			{
				max = vector[i];
			}
		}

		return max;
	}

	public static int FindMinIndex(double[] Vector)
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

	public static double[] VecMatrixProd(double[] vector, double[][] matrix)
	{
		if (vector.length != matrix[0].length)
		{
			System.out.println("Attempted to multiply matrices of different sizes at UtilGeral -> MatrixProd");
			System.out.println("Vector size: " + vector.length + " Matrix size : " + matrix[0].length);
			return null;
		}
		else
		{
			double product[] = new double[matrix.length];
			for (int i = 0; i <= matrix.length - 1; i += 1)
			{
				for (int j = 0; j <= vector.length - 1; j += 1)
				{
					product[i] += vector[j] * matrix[i][j];
				}
			}
			return product;
		}
	}

	public static boolean ArrayContains(int[][] Array, int[] value)
	{
		if (Array != null)
		{
			for (int i = 0; i <= Array.length - 1; i += 1)
			{
				if (Array[i] == value)
				{
					return true;
				}
			}
		}
		return false;
	}

	public static int IndexOf(int[] Array, int Value)
	{
		if (Array != null)
		{
			for (int i = 0; i <= Array.length - 1; i += 1)
			{
				if (Value == Array[i])
				{
					return i;
				}
			}
		}
		return -1;
	}

	public static int IndexOf(String[] Vector, String Value)
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

	/* Math methods */

	public static float Round(double num, int decimals)
	{
		return BigDecimal.valueOf(num).setScale(decimals, RoundingMode.HALF_EVEN).floatValue();
	}

	/* Music methods */

	/* Print methods */

	/* Miscellaneous methods */

	public static double act(double x)
	{
		return 1.0 / (1.0 + Math.exp(-x));
	}

	public static int TextL(String Text, Font TextFont, int size, Graphics G)
	{
		FontMetrics metrics = G.getFontMetrics(TextFont);
		return (int)(metrics.stringWidth(Text)*0.05*size);
	}

	public static int TextH(int TextSize)
	{
		return (int)(0.8*TextSize);
	}

	public static int[] OffsetFromPos(String Alignment, int l, int h)
	{
		int[] offset = new int[2];
		if (Alignment.equals("TopLeft"))
		{
			offset[0] = 0;
			offset[1] = 0;
		}
		if (Alignment.equals("BotLeft"))
		{
			offset[0] = 0;
			offset[1] = -h;
		}
		if (Alignment.equals("TopCenter"))
		{
			offset[0] = -l/2;
			offset[1] = 0;
		}
		if (Alignment.equals("BotCenter"))
		{
			offset[0] = -l/2;
			offset[1] = -h;
		}
		if (Alignment.equals("Center"))
		{
			offset[0] = -l/2;
			offset[1] = -h/2;
		}
		if (Alignment.equals("LeftCenter"))
		{
			offset[0] = 0;
			offset[1] = -h/2;
		}
		if (Alignment.equals("RightCenter"))
		{
			offset[0] = -l;
			offset[1] = -h/2;
		}
		if (Alignment.equals("BotRight"))
		{
			offset[0] = -l;
			offset[1] = -h;
		}
		if (Alignment.equals("TopRight"))
		{
			offset[0] = -l;
			offset[1] = 0;
		}
		return offset;
	}

	public static int[] GetRelMousePos(int[] PanelPos)
 	{
		return new int[] {MouseInfo.getPointerInfo().getLocation().x - PanelPos[0], MouseInfo.getPointerInfo().getLocation().y - PanelPos[1]};
 	}
}
