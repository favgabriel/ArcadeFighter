package com.gabriel.objects;

import com.gabriel.framework.GameObject;
import com.gabriel.framework.Objectid;
import com.gabriel.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {
    Handler handler;
    public Bullet(int x, int y, Objectid id,int velx) {
        super(x, y, id);
        this.velx=velx;
    }

    @Override
    public void tick(LinkedList<GameObject> gameObject) {
        x+=velx;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(255,77,0));
        g.fillRect((int)x,(int)y,4,6);
    }

    @Override
    public Rectangle getbounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
