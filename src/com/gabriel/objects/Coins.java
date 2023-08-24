package com.gabriel.objects;

import com.gabriel.framework.GameObject;
import com.gabriel.framework.Objectid;

import java.awt.*;
import java.util.LinkedList;

public class Coins extends GameObject {
    public Coins(int x, int y, Objectid id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<GameObject> gameObject) {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getbounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
