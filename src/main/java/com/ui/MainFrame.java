package com.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L ;

    private static final Dimension SIZE = new Dimension(1800, 1080) ;
    private static final Point TOP_LEFT = new Point(16, 16) ;
    private static final String TITLE = "Reconhecimento de imagens" ;
	private static MainFrame mainFrame ;

	private MainFrame()
    {
		setTitle(TITLE);
		setSize(SIZE);
		setLocation(TOP_LEFT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    }
	
	public static void create()
	{
		if (mainFrame != null) { return ;}

        mainFrame = new MainFrame();

        ButtonsPanel.create(SIZE);
        mainFrame.add(ButtonsPanel.getInstance(), BorderLayout.NORTH);

        MainPanel.create(SIZE);
        mainFrame.add(MainPanel.getInstance(), BorderLayout.CENTER);

        mainFrame.setVisible(true);
	}

	public static MainFrame getInstance() { return mainFrame ;}
}
