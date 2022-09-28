package Components;

import java.awt.Color;

public class Category 
{
	private String Name;
	private int[][] ColorRange;
	private int[][] ColorDRange;
	private Color color;
	private int[] Dcolor;
	
	public Category (String Name, int[][] ColorRange, int[][] ColorDRange)
	{
		this.Name = Name;
		this.ColorRange = ColorRange;
		this.ColorDRange = ColorDRange;
		color = new Color((ColorRange[0][0] + ColorRange[0][1]) / 2, (ColorRange[1][0] + ColorRange[1][1]) / 2, (ColorRange[2][0] + ColorRange[2][1]) / 2);
		Dcolor = new int[] {(ColorDRange[0][0] + ColorDRange[0][1]) / 2, (ColorDRange[1][0] + ColorDRange[1][1]) / 2, (ColorDRange[2][0] + ColorDRange[2][1]) / 2};
	}

	public String getName() {return Name;}
	public int[][] getColorRange() {return ColorRange;}
	public int[][] getColorDRange() {return ColorDRange;}
	public Color getcolor() {return color;}
	public int[] getDcolor() {return Dcolor;}
	public void setName(String N) {Name = N;}
	public void setColorRange(int[][] C) {ColorRange = C;}
	public void setColorDRange(int[][] C) {ColorDRange = C;}
	public void setcolor(Color C) {color = C;}
	public void setDColor(int[] D) {Dcolor = D;}
}
