package com.gabriel.framework;

import java.awt.*;
import java.util.LinkedList;

///////////////////////////////////////////////////////////////////////////
// this is the basic code for movement in java gaming when it comes to all game object
///////////////////////////////////////////////////////////////////////////
public abstract class GameObject {
    protected float x,y;
    protected float velx=0,vely=0;
    protected Objectid id;
    protected boolean falling=true;
    protected boolean jumping=false;
    protected int facing =1;

    public int getFacing() {
        return facing;
    }
    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public GameObject(int x, int y, Objectid id){
        this.x=x;
        this.y=y;
        this.id=id;
    }
    //holds object
    public abstract void tick(LinkedList<GameObject> gameObject);
    //holds graphics
    public abstract void render(Graphics g);
    //checks for collision
    public abstract Rectangle getbounds();

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x=x;
    }
    public void setY(float y) {
        this.y=y;
    }

    public float getVelx() {
        return velx;
    }
    public float getVely() {
        return vely;
    }
    public void setVelx(float velx) {
        this.velx=velx;
    }
    public void setVely(float vely) {
        this.vely=vely;
    }

    public Objectid getId() {
        return id;
    }
}
