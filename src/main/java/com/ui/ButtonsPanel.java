package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.app.ImageProcessingService;

public class ButtonsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

    private final ImageProcessingService processingService ;
	private JButton playButton;
	private static ButtonsPanel buttonsPanel ;
	private static final String IMG_PATH = ".\\Icons\\";
	
	private ButtonsPanel()
	{
        setLayout(new FlowLayout(FlowLayout.CENTER)) ;
		this.add(createPlayButton()) ;
		setFocusable(true) ;
        this.processingService = new ImageProcessingService(new ImageIcon("teste.png").getImage()) ;
	}
	
	protected static void create(Dimension size)
	{
		if (buttonsPanel != null) { return ;}

		buttonsPanel = new ButtonsPanel() ;
	}

	public static ButtonsPanel getInstance() { return buttonsPanel ;}
    
	private JButton createPlayButton()
    {
        ImageIcon playIcon = new ImageIcon(IMG_PATH + "PlayIcon.png");
		playButton = createButton(playIcon, new int[2], new int[] {30, 30}, Color.cyan) ;

        playButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
				processingService.runAsync(new ProcessingUiListener(playButton)) ;
            }
        });

        return playButton ;
    }

	private static JButton createButton(ImageIcon icon, int[] alignment, int[] size, Color color)
	{
		JButton button = new JButton();
		button.setIcon(icon);
		button.setVerticalAlignment(alignment[0]);
		button.setHorizontalAlignment(alignment[1]);
		button.setBackground(color);
		button.setPreferredSize(new Dimension(size[0], size[1]));
		return button;
	}
}
