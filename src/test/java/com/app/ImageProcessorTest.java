package com.app;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

public class ImageProcessorTest {

	@Test
	void repeatedProcessingPreservesOriginalImage() throws InterruptedException
	{
		BufferedImage originalImage = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
		originalImage.setRGB(0, 0, 0xff123456);
		originalImage.setRGB(1, 0, 0xffabcdef);
		originalImage.setRGB(0, 1, 0xff00ff00);
		originalImage.setRGB(1, 1, 0xffff0000);
		int[] originalPixels = originalImage.getRGB(0, 0, 2, 2, null, 0, 2);
		ImageProcessingService service = new ImageProcessingService(originalImage);

		Image firstResult = process(service);
		Image secondResult = process(service);

		assertArrayEquals(originalPixels, originalImage.getRGB(0, 0, 2, 2, null, 0, 2));
		assertArrayEquals(readPixels(firstResult), readPixels(secondResult));
	}

	private static Image process(ImageProcessingService service) throws InterruptedException
	{
		CountDownLatch finished = new CountDownLatch(1);
		AtomicReference<Image> result = new AtomicReference<>();
		AtomicReference<Exception> failure = new AtomicReference<>();

		assertTrue(service.runAsync(new IProcessingListener()
		{
			@Override
			public void processingStarted()
			{
			}

			@Override
			public void processingSucceeded(Image image, double elapsedMilliseconds)
			{
				result.set(image);
			}

			@Override
			public void processingFailed(Exception exception)
			{
				failure.set(exception);
			}

			@Override
			public void processingFinished()
			{
				finished.countDown();
			}
		}));

		assertTrue(finished.await(10, TimeUnit.SECONDS));
		assertNull(failure.get());
		assertNotNull(result.get());
		return result.get();
	}

	private static int[] readPixels(Image image)
	{
		BufferedImage bufferedImage = (BufferedImage) image;
		return bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0,
				bufferedImage.getWidth());
	}
}
