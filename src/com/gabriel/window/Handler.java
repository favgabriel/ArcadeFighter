package com.gabriel.window;

import com.gabriel.framework.GameObject;
import com.gabriel.framework.Objectid;
import com.gabriel.objects.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

///////////////////////////////////////////////////////////////////////////
// this class handles all the game objects in the objectid
///////////////////////////////////////////////////////////////////////////
public class Handler {
    public LinkedList<GameObject> object= new LinkedList<>();
    protected GameObject gameObject;
    private Camera cam;
    private BufferedImage level2=null, level3 = null, level=null;
    public Handler(Camera cam){
        this.cam=cam;
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadimage("/level.png");
        level2=loader.loadimage("/level2.png");
        level3 = loader.loadimage("/level3.png");
    }

    public void tick(){
        //calls all the game objects tick
        for (int i=0; i<object.size();i++){
            gameObject= object.get(i);
            gameObject.tick(object);

        }
    }
    public void render(Graphics g){
        //calls all the game object graphic
        for (int i=0; i<object.size();i++){
            gameObject= object.get(i);
            gameObject.render(g);
        }
    }
    public void addobject(GameObject object){
        this.object.add(object);
    }
    public void removeobject(GameObject object){
        this.object.remove(object);
    }

    //creating ground level
   /* public void createlevel(){
        for (int i=0; i<Game.WIDTH+32; i+=32){
            addobject(new Block(i,Game.HEIGHT-32, Objectid.Block));
        }
        for (int i=0; i<Game.HEIGHT+32; i+=32){
            addobject(new Block(0,i, Objectid.Block));
        }
        for (int i=0; i<Game.HEIGHT+32; i+=32){
            addobject(new Block(Game.WIDTH-32,i, Objectid.Block));
        }
        for (int i=200; i<600; i+=32){
            addobject(new Block(i,400, Objectid.Block));
        }
        for (int i=100; i<400; i+=32){
            addobject(new Block(i,200, Objectid.Block));
        }
        for (int i=100; i<300; i+=32){
            addobject(new Block(i,50, Objectid.Block));
        }
    }*/
   public void switchlevel(){
       clearlevel();
       cam.setX(0);
       switch (Game.Level){
           case 0:
               printimage(level);
               startmusic();
               break;
           case 1:
               printimage(level2);
               startmusic();
               break;
           case 2:
               printimage(level3);
               startmusic();
               break;
           default:
               break;
       }
       Game.Level++;
   }

   public void stopmusic(){
       Game.clip.stop();
   }
   public void clearlevel(){
        this.object.clear();
   }

   public void startmusic(){
       if (!Game.clip.isRunning()){
           Game.clip.setFramePosition(0);
           Game.clip.start();
       }
   }

   public void printimage(BufferedImage image){
        //get the image height and width
        int w =image.getWidth();
        int h = image.getHeight();
        System.out.println("Width height :" +w+" "+h);
        //creates a loop for height and width
        for (int x=0;x<h;x++){
            for (int y=0;y<w;y++){
                //create a pixel variable and calling the rgb method from the image class and setting our loop
                //coordinate
                int pixel = image.getRGB(x,y);
                int red = (pixel>>16)& 0xff;//reads for red intensity
                int green=(pixel>>8)& 0xff;//read for green intensity
                int blue=(pixel)& 0xff;//read for blue intensity

                if (red==255&& green==255&&blue==255)
                    //checking if the color gradient rhymes
                    //setting our block coordinate to image
                    addobject(new Block(x*32,y*32,0,Objectid.Block));
                if (red==0&& green==0&&blue==255)
                    //checking if the color gradient rhymes
                    //setting our player coordinate to image
                    addobject(new Player(x*32,y*32,Objectid.Player,this,cam));
                if (red==255&& green==242&&blue==0)
                    //checking if the color gradient rhymes
                    //setting our player coordinate to image
                    addobject(new Flag(x*32,y*32,Objectid.Flag,cam));
                if (red==0&& green==255&&blue==0)
                    //checking if the color gradient rhymes
                    //setting our player coordinate to image
                    addobject(new Coins(x*32,y*32,Objectid.Coins));
                if (red==255&& green==0&&blue==0)
                    //checking if the color gradient rhymes
                    //setting our player coordinate to image
                    addobject(new Enemy(x*32,y*32,Objectid.Enemy,(int)3));
            }
        }
    }
}