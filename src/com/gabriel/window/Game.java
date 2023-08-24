package com.gabriel.window;

import com.gabriel.framework.KeyInput;
import com.gabriel.framework.Objectid;
import com.gabriel.framework.Texture;
import com.gabriel.objects.Block;
import com.gabriel.objects.Flag;
import com.gabriel.objects.Player;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Camera cam;
    public static int WIDTH,HEIGHT;
    private BufferedImage level=null,clouds=null;
    private static Texture tex;
    public static int Level=1;
    public static Clip clip;
    public void init(){
        WIDTH =getWidth();
        HEIGHT =getHeight();
        cam = new Camera(0,0);
        handler= new Handler(cam);
        tex = new Texture();
        BufferedImageLoader loader = new BufferedImageLoader();
        //loading imgae
        level = loader.loadimage("/level.png");
        clouds =loader.loadimage("/cloud.png");
        InputStream file = getClass().getResourceAsStream("/nuc.wav");
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        // adding ground
        //handler.createlevel();
        //adding player
        //handler.addobject(new Player(100,100,Objectid.Player,handler));
        handler.printimage(level);
        this.addKeyListener(new KeyInput(handler));
    }
    @Override
    public void run() {
        init();
        System.out.println("thread as started...");
        long lasttime= System.nanoTime();
        double amountoftick=60.0;
        double ns =1000000000/amountoftick;
        double delta=0;
        long timer= System.currentTimeMillis();
        int update=0;
        int frame=0;
        while (running){
            //speed
            long now = System.nanoTime();
            delta+= (now-lasttime)/ns;
            lasttime = now;
            while (delta>= 1){
                tick();
                update++;
                delta--;
            }
            frame++;
            render();
            //reset
            if (System.currentTimeMillis()-timer>1000){
                timer+=1000;
                System.out.println("FPS :"+frame+" TICK :"+update);
                frame =0;
                update=0;
            }
        }
    }

    public synchronized void start(){
        if (running)
            return;
        running=true;

        thread = new Thread(this);
        thread.start();
    }
    //update
    private void tick(){
        handler.tick();
        //follow player
        for (int i=0;i<handler.object.size();i++){
            if (handler.object.get(i).getId()==Objectid.Player){
                cam.tick(handler.object.get(i));
            }
        }
    }
    //holds the graphic
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(25,191,236));
        g.fillRect(0,0,getWidth(),getHeight());
        g2d.translate(cam.getX(),cam.getY());//begin cam

        for (int i =0;i<clouds.getWidth()*3;i+=clouds.getWidth()) {
            g.drawImage(clouds, i, 50, this);
        }
        handler.render(g);
        g2d.translate(-cam.getX(),-cam.getY());//end cam
        g.dispose();
        bs.show();
    }
    //reads the load image folder
    public static Texture getInstance(){
        return tex;
    }

    public static void main(String[] args) {
        new Window(800,600,"Tooms Ranger",new Game());
    }
}