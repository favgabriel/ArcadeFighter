package com.gabriel.objects;

import com.gabriel.framework.GameObject;
import com.gabriel.framework.Objectid;
import com.gabriel.framework.Texture;
import com.gabriel.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {
    Texture tex = Game.getInstance();
    private int type;
    public Block(int x, int y,int type, Objectid id) {
        super(x, y, id);
        this.type=type;
    }

    @Override
    public void tick(LinkedList<GameObject> gameObject) {

    }

    @Override
    public void render(Graphics g) {
       /* g.setColor(Color.white);
        g.drawRect((int) x,(int) y,32,32);*/
       if (type==0){//dirt
           g.drawImage(tex.block[0],(int)x,(int)y,null);
       }
        if (type==1){//grass
            g.drawImage(tex.block[1],(int)x,(int)y,null);
        }
    }

    @Override
    public Rectangle getbounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}