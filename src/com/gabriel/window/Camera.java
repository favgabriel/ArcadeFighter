package com.gabriel.window;

import com.gabriel.framework.GameObject;
import com.gabriel.objects.Player;

public class Camera {
    private float x,y;
    public Camera(float x,float y){
        this.x=x;
        this.y=y;
    }
    public void tick(GameObject object){
        x = -object.getX()+ Game.WIDTH/2;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
