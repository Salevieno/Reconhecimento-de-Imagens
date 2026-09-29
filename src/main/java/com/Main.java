package com;
import com.app.Application;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
	public static void main (String[] args) 
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException exception)
		{
			Logger.getLogger(Main.class.getName()).log(Level.WARNING,
					"Could not load the system look and feel", exception);
		}

		SwingUtilities.invokeLater(Application::start) ;
	}
}