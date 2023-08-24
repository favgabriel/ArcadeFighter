package com.gabriel.framework;

import com.gabriel.objects.Bullet;
import com.gabriel.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    public KeyInput(Handler handler){
        this.handler=handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key= e.getKeyCode();
        //checks the handler for objects
        for (int i=0; i<handler.object.size();i++){
            //puts object in the gameobject
            GameObject gameObject= handler.object.get(i);
            //check for if the gameobject.id is playerid
            if (gameObject.getId()==Objectid.Player){
                if (key==KeyEvent.VK_D) gameObject.setVelx(5);
                if (key==KeyEvent.VK_A) gameObject.setVelx(-5);
                if (key==KeyEvent.VK_W &&!gameObject.isJumping()){
                    gameObject.setJumping(true);
                    gameObject.setVely(-15);
                }
                if (key==KeyEvent.VK_SPACE)
                    handler.addobject(new Bullet((int)gameObject.getX(),(int)gameObject.getY()+48,Objectid.Bullet,(int) gameObject.getFacing()*10));
            }
        }
        if (key==KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key= e.getKeyCode();
        //checks the handler for objects
        for (int i=0; i<handler.object.size();i++){
            //puts object in the gameobject
            GameObject gameObject= handler.object.get(i);
            //check for if the gameobject.id is playerid
            if (gameObject.getId()==Objectid.Player){
                if (key==KeyEvent.VK_D) gameObject.setVelx(0);
                if (key==KeyEvent.VK_A) gameObject.setVelx(0);
            }
        }
    }
}
