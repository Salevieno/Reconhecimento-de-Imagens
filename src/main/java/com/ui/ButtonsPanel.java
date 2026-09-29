package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.app.ImageProcessingService;

public class ButtonsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private ImageProcessingService processingService;
	private JButton playButton;
	private JButton addImageButton;
	private static ButtonsPanel buttonsPanel ;
	private static final String IMG_PATH = ".\\Icons\\";
	
	private ButtonsPanel()
	{
        setLayout(new FlowLayout(FlowLayout.CENTER)) ;
		this.add(createAddImageButton());
		this.add(createPlayButton()) ;
		setFocusable(true) ;
	}
	
	protected static void create(Dimension size)
	{
		if (buttonsPanel != null) { return ;}

		buttonsPanel = new ButtonsPanel() ;
	}

	public static ButtonsPanel getInstance() { return buttonsPanel ;}

	private JButton createAddImageButton()
	{
		addImageButton = new JButton("Adicionar imagem");
		addImageButton.addActionListener(event -> chooseImage());
		return addImageButton;
	}

	private void chooseImage()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter(
				"Imagens (PNG, JPEG, BMP, GIF)", "png", "jpg", "jpeg", "bmp", "gif"));
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		File selectedFile = fileChooser.getSelectedFile();
		try
		{
			BufferedImage image = ImageIO.read(selectedFile);
			if (image == null)
			{
				MainPanel.getInstance().updateStatus("O arquivo selecionado nao e uma imagem suportada.");
				return;
			}

			processingService = new ImageProcessingService(image);
			MainPanel mainPanel = MainPanel.getInstance();
			mainPanel.updateOriginalImage(image);
			mainPanel.clearProcessedImage();
			mainPanel.updateStatus("Imagem carregada. Pressione Play para iniciar.");
			playButton.setEnabled(true);
		}
		catch (IOException exception)
		{
			MainPanel.getInstance().updateStatus("Falha ao carregar imagem: " + exception.getMessage());
		}
	}
    
	private JButton createPlayButton()
    {
        ImageIcon playIcon = new ImageIcon(IMG_PATH + "PlayIcon.png");
		playButton = createButton(playIcon, new int[2], new int[] {30, 30}, Color.cyan) ;
		playButton.setEnabled(false);

        playButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
				processingService.runAsync(new ProcessingUiListener(playButton, addImageButton)) ;
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
