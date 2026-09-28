package com.ui;

import java.awt.Image;

import javax.swing.JButton;

import com.app.IProcessingListener;



final class ProcessingUiListener implements IProcessingListener
{
    private final JButton playButton;
    private final MainPanel mainPanel;

    ProcessingUiListener(JButton playButton)
    {
        this.playButton = playButton;
        this.mainPanel = MainPanel.getInstance() ;
    }

    @Override
    public void processingStarted()
    {
        playButton.setEnabled(false);
        mainPanel.updateStatus("Processando imagem...");
    }

    @Override
    public void processingSucceeded(Image image, double elapsedMilliseconds)
    {
        mainPanel.updateProcessedImage(image);
        mainPanel.updateStatus(
                String.format("Processamento concluido em %.2f ms", elapsedMilliseconds));
    }

    @Override
    public void processingFailed(Exception exception)
    {
        mainPanel.updateStatus("Falha ao processar imagem: " + exception.getMessage());
    }

    @Override
    public void processingFinished()
    {
        playButton.setEnabled(true);
    }
}