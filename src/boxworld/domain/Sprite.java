/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.domain;

import java.awt.Image;

/**
 *
 * @author oscar
 */
public abstract class Sprite implements Drawable {
    
    protected int x;
    protected int y;
    protected Image skin1;
    protected Image skin2;
    boolean currentImage;

    protected final int SPRITE_SIZE = 48;
   
    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return x;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
    
    public void setSkin1(Image skin1) {
        this.skin1 = skin1;
    }

    public Image getSkin1() {
        return skin1;
    }
    
    public void setSkin2(Image skin2) {
        this.skin2 = skin2;
    }
 
    public Image getSkin2() {
        return skin2;
    }
}
