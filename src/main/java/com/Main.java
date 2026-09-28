package com;
import com.app.Application;

import javax.swing.SwingUtilities;

public class Main
{
	public static void main (String[] args) 
	{
		SwingUtilities.invokeLater(Application::start) ;
	}
}