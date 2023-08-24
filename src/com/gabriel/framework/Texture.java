package com.gabriel.framework;

import com.gabriel.window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet bs,ps;
    private BufferedImage blocksheet=null;
    private BufferedImage playersheet=null;

    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[6];
    public BufferedImage[] playerjump = new BufferedImage[4];
    public Texture(){
        BufferedImageLoader loader= new BufferedImageLoader();
        blocksheet= loader.loadimage("/blocksheet.png");
        playersheet = loader.loadimage("/playersheet.png");

        bs = new SpriteSheet(blocksheet);
        ps = new SpriteSheet(playersheet);

        getTexture();
    }
    public void getTexture(){
        block[0]=bs.grabimage(1,1,32,32);//dirt
        block[1]=bs.grabimage(3,1,32,32);//grass

        //looking right
        player[0]=ps.grabimage(1,1,32,74);
        player[1]=ps.grabimage(1,1,32,74);
        player[2]=ps.grabimage(2,1,32,74);

        //lookingleft
        player[3]=ps.grabimage(14,1,32,74);
        player[4]=ps.grabimage(14,1,32,74);
        player[5]=ps.grabimage(13,1,32,74);

        //jumping right
        playerjump[0]=ps.grabimage(1,2,32,74);
        playerjump[1]=ps.grabimage(2,2,32,74);

        //jumping left
        playerjump[2]=ps.grabimage(14,2,32,74);
        playerjump[3]=ps.grabimage(13,2,32,74);

    }

}
