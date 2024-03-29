package com.gabriel.framework;

import java.awt.*;
import java.awt.image.BufferedImage;

///////////////////////////////////////////////////////////////////////////
// this code collects a cropped image of specific cor in row and col
///////////////////////////////////////////////////////////////////////////
public class SpriteSheet {
    BufferedImage image;
    public SpriteSheet(BufferedImage image){
        this.image=image;
    }
    public BufferedImage grabimage(int col,int row,int width,int height){
        BufferedImage img = image.getSubimage((col*width)-width,(row*height)-height,width,height);
        return img;
    }
}
