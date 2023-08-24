package com.gabriel.objects;

import com.gabriel.framework.GameObject;
import com.gabriel.framework.Objectid;
import com.gabriel.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Enemy extends GameObject {
    Handler handler;
    int velx;
    public Enemy(int x, int y, Objectid id,int velx) {
        super(x, y, id);
        this.velx=velx;
    }

    @Override
    public void tick(LinkedList<GameObject> Object) {
         x-=velx;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0,64,64));
        g.fillRect((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getbounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
