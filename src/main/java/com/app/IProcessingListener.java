package com.app;

import java.awt.Image;

public interface IProcessingListener
{
    void processingStarted();
    void processingSucceeded(Image image, double elapsedMilliseconds);
    void processingFailed(Exception exception);
    void processingFinished();
}