package com.reconhecimentoDeImagens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

class ImageHelpersBaselineTest {

    @Test
    void returnsBufferedImageWithoutCopying() {
        BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);

        assertSame(image, Util.toBufferedImage(image));
    }

    @Test
    void readsPixelColorAtRequestedCoordinates() {
        BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        image.setRGB(1, 0, new Color(12, 128, 240).getRGB());

        assertEquals(new Color(12, 128, 240), Util.GetPixelColor(image, new int[] {1, 0}));
    }
}