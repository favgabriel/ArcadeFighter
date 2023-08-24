package com.gabriel.window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;
    private int frame;
    private int index=0;
    private int count=0;
    private BufferedImage[] img;
    private BufferedImage currentimg;

    public Animation(int speed,BufferedImage... args){
        this.speed=speed;
        img= new BufferedImage[args.length];
        for (int i=0;i<args.length;i++){
            img[i]=args[i];
        }
        frame=args.length;
    }
    public void runanimation(){
        index++;
        if (index > speed){
            index=0;
            nextframe();
        }
    }
    private void nextframe(){
       for (int i=0;i<frame;i++){
           if (count==i){
               currentimg=img[i];
           }
           count++;
           if (count>frame){
                count=0;
           }
       }
    }
    public void  drawanim(Graphics g, int x,int y){
        g.drawImage(currentimg,x,y,null);
    }
    public void  drawanim(Graphics g, int x,int y,int scalex,int scaley){
        g.drawImage(currentimg,x,y,scalex,scaley,null);
    }
}
