package com.app;

import com.ui.MainFrame;

public class Application
{
	private Application() { MainFrame.create() ;}

    public static void start() { new Application() ;}
}