package com.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class DrawFunctions
{
	private Graphics2D graphs2D;
	
	public DrawFunctions(Graphics graphs)
	{
		graphs2D = (Graphics2D) graphs;
	}
	public void DrawImage(Image File, int[] Pos, String Alignment)
	{       
		if (File == null) { return ;}
        
        int l = (int)(File.getWidth(null)) ;
        int h = (int)(File.getHeight(null)) ;
        if (Alignment.equals("Left"))
        {
            graphs2D.drawImage(File, Pos[0], Pos[1] - h, l, h, null);
        }
        if (Alignment.equals("Center"))
        {
            graphs2D.drawImage(File, Pos[0] - l/2, Pos[1] - h/2, l, h, null);
        }
        if (Alignment.equals("Right"))
        {
            graphs2D.drawImage(File, Pos[0] - l, Pos[1] - h, l, h, null);
        }
	}
}
