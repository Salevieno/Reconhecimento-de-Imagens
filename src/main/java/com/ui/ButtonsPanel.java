package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.app.Application;

public class ButtonsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private static ButtonsPanel buttonsPanel ;
	private static final String IMG_PATH = ".\\Icons\\";
	private JButton playButton;
	
	private ButtonsPanel()
	{
        setLayout(new FlowLayout(FlowLayout.CENTER)) ;
        this.add(createPlayButton());
		setFocusable(true) ;
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
				Application.runAsync(new Application.ProcessingListener()
				{
					@Override
					public void processingStarted()
					{
						playButton.setEnabled(false);
						MainPanel.getInstance().updateStatus("Processando imagem...");
					}

					@Override
					public void processingSucceeded(Image image, double elapsedMilliseconds)
					{
						MainPanel.getInstance().updateProcessedImage(image);
						MainPanel.getInstance().updateStatus(
								String.format("Processamento concluido em %.2f ms", elapsedMilliseconds));
					}

					@Override
					public void processingFailed(Exception exception)
					{
						MainPanel.getInstance().updateStatus("Falha ao processar imagem: " + exception.getMessage());
					}

					@Override
					public void processingFinished()
					{
						playButton.setEnabled(true);
					}
				}) ;
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
