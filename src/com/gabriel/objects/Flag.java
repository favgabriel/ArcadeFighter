package com.gabriel.objects;

import com.gabriel.framework.GameObject;
import com.gabriel.framework.Objectid;
import com.gabriel.window.Camera;

import java.awt.*;
import java.util.LinkedList;

public class Flag extends GameObject {
    Camera cam;
    public Flag(int x, int y, Objectid id, Camera cam) {
        super(x, y, id);
        this.cam=cam;
    }

    @Override
    public void tick(LinkedList<GameObject> gameObject) {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getbounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
