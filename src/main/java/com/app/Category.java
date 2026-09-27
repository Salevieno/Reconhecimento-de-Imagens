package com.app;

import java.awt.Color;

public class Category 
{
	private String name;
	private Color color;
	private int[] Dcolor;
	
	public Category (String name, int[][] colorRange, int[][] colorDRange)
	{
		this.name = name;
		this.color = new Color((colorRange[0][0] + colorRange[0][1]) / 2, (colorRange[1][0] + colorRange[1][1]) / 2, (colorRange[2][0] + colorRange[2][1]) / 2);
		this.Dcolor = new int[] {(colorDRange[0][0] + colorDRange[0][1]) / 2, (colorDRange[1][0] + colorDRange[1][1]) / 2, (colorDRange[2][0] + colorDRange[2][1]) / 2};
	}

	public String getName() {return name;}
	public Color getcolor() {return color;}
	public int[] getDcolor() {return Dcolor;}
}
