package com.gabriel.objects;

import com.gabriel.framework.GameObject;
import com.gabriel.framework.Objectid;
import com.gabriel.framework.Texture;
import com.gabriel.window.Animation;
import com.gabriel.window.Camera;
import com.gabriel.window.Game;
import com.gabriel.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {
    private final float width=48;
    private final float height=96;
    private float gravity=0.5f;
    private final float MAX_SPEED= 10;
    private final Handler handler;
    private Camera cam;
    //-1 left;
    //1 right;
    Texture tex = Game.getInstance();
    private final Animation playerwalk;
    private final Animation playerwalkleft;
    private final Animation playerjump;
    private final Animation playerjumpleft;

    public Player(int x, int y, Objectid id,Handler handler,Camera cam) {
        super(x, y, id);
        this.handler=handler;
        this.cam=cam;

        playerwalk= new Animation(5,tex.player[1],tex.player[2]);
        playerwalkleft= new Animation(5,tex.player[4],tex.player[5]);
        playerjump= new Animation(5,tex.playerjump[0],tex.playerjump[1]);
        playerjumpleft= new Animation(5,tex.playerjump[2],tex.playerjump[3]);
    }

    @Override
    public void tick(LinkedList<GameObject> Object) {
        x+=velx;
        y+=vely;
        if (velx>0)facing=1;
        if (velx<0)facing=-1;
        //setting gravity
        if (falling||jumping){
            vely+=gravity;
            //control the speed at fall
            if (vely>MAX_SPEED)
                vely=MAX_SPEED;
        }
        //falling();
        Collision();
        shot();
        playerwalk.runanimation();
        playerwalkleft.runanimation();
        playerjump.runanimation();
        playerjumpleft.runanimation();
    }

    //collision against block or ground
    private void Collision(){
        for (int i =0; i<handler.object.size(); i++){
            GameObject gameObject = handler.object.get(i);
            if (gameObject.getId()==Objectid.Block){
                if (getboundstop().intersects(gameObject.getbounds())){
                    //aligning player to ground
                    y= gameObject.getY()+ 32;
                    vely=0;
                }
                if (getbounds().intersects(gameObject.getbounds())){
                    //aligning player to ground
                    y= gameObject.getY()-height;
                    vely=0;
                    falling=false;
                    jumping=false;
                }
                //when not in collision with ground gravity applies
                else
                    falling=true;
                if (getboundsright().intersects(gameObject.getbounds())){
                    //aligning player to ground
                    x= gameObject.getX()- width;
                }
                if (getboundsleft().intersects(gameObject.getbounds())){
                    //aligning player to ground
                    x= gameObject.getX()+ 32;
                }
            }//character collision with flag
            else if (gameObject.getId()==Objectid.Flag){
                if (getbounds().intersects(gameObject.getbounds())) {
                    handler.switchlevel();
                }
            }//character collision with coins
            else if (gameObject.getId()==Objectid.Coins){
                if (getbounds().intersects(gameObject.getbounds()))
                    handler.removeobject(gameObject);
            }//character collision with enemy
            else if (gameObject.getId()==Objectid.Enemy){
                if (getboundsright().intersects(gameObject.getbounds())) {
                    handler.clearlevel();
                    handler.stopmusic();
                    System.out.println("Gameover");
                    Game.Level--;
                    handler.switchlevel();
                }if (getboundsleft().intersects(gameObject.getbounds())) {
                    handler.clearlevel();
                    handler.stopmusic();
                    System.out.println("Gameover");
                    Game.Level--;
                    handler.switchlevel();
                }
            }
        }
    }

    float bulletxaxis;
    Rectangle bulletbound;
    float enemyxaxis;

    public void shot(){
        for (int i=0; i<handler.object.size();i++){
            GameObject gameObject= handler.object.get(i);
            if (gameObject==handler.object.get(i)){
                //collecting axis of the bullet and getting bullet bound
                if (gameObject.getId()==Objectid.Bullet){
                    bulletxaxis=gameObject.getX();
                    bulletbound=gameObject.getbounds();
                }
                if (gameObject.getId()==Objectid.Enemy){
                    enemyxaxis=gameObject.getX();
                    //if the bullet coordinate is greater than enemy coordinate
                    if (bulletxaxis> enemyxaxis){
                        //emey object is removed
                        if (bulletbound != null) {
                            if (bulletbound.intersects(gameObject.getbounds())) {
                                handler.removeobject(gameObject);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //Image m = new ImageIcon("c:/test/left.png").getImage();
       // g.drawImage(m,(int)x,(int)y,null);
        /*g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,(int)width,(int)height);*/
        //for jumping
        if (jumping) {
            if (facing==1) {
                g.drawImage(tex.playerjump[0], (int) x, (int) y, 48, 96, null);
            }else if (facing==-1){
                g.drawImage(tex.playerjump[2], (int) x, (int) y, 48, 96, null);
            }
        }else {
            //when idle
            if (velx!=0){
                if (facing==1) {
                    playerwalk.drawanim(g, (int) x, (int) y, 48, 96);
                }else if (facing==-1){
                    playerwalkleft.drawanim(g, (int) x, (int) y, 48, 96);
                }
            }else {
                if (facing==1)
                    g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);
                else if (facing==-1){
                    g.drawImage(tex.player[3], (int) x, (int) y, 48, 96, null);

                }
            }
        }

        /*Graphics2D g2d= (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getboundsleft());
        g2d.draw(getboundsright());
        g2d.draw(getboundstop());
        g2d.draw(getbounds());*/
    }

    @Override
    public Rectangle getbounds() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),
                (int) ((int)y+((height/2))),
                (int)width/2,
                (int)height/2);
    }
    public Rectangle getboundstop() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),
                (int)y,
                (int)width/2,
                (int)height/2);
    }
    public Rectangle getboundsright() {
        return new Rectangle((int) ((int)x+width-5),
                (int)y+5,
                (int)5,
                (int)height-10);
    }
    public Rectangle getboundsleft() {
        return new Rectangle((int)x,
                (int)y+5,
                (int)5,
                (int)height-10);
    }

}