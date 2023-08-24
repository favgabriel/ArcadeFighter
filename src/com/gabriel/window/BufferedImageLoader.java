package com.gabriel.window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
//this class would load image
public class BufferedImageLoader {
    BufferedImage image;
    public BufferedImage loadimage(String path){
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return image;
    }
}
