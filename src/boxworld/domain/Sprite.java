/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boxworld.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author oscar
 */
public class Sprite {
    
    protected int x;
    protected int y;
    protected Image skin1;
    protected Image skin2;
    private Assets asset;
    
    private boolean defaultSkin;

    protected final int SPRITE_SIZE = 48;

    public Sprite(Assets asset) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        defaultSkin = true;
    } 
    
    public Sprite(Assets asset, int x, int y) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        this.x = x;
        this.y = y;
        
        defaultSkin = true;
    } 
    
    public Sprite(Assets asset, boolean defaultSkin) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        this.defaultSkin = defaultSkin;
    }
    
    public Sprite(Assets asset, boolean defaultSkin, int x, int y) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        this.x = x;
        this.y = y;
        
        this.defaultSkin = defaultSkin;
    }
   
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

    public void paint(Graphics2D g2d, JPanel canvas) {
        
        if (defaultSkin){
        
            g2d.drawImage(skin1, this.x * SPRITE_SIZE, this.y * SPRITE_SIZE, canvas);
        } else if(skin2 != null){
        
            g2d.drawImage(skin2, this.x * SPRITE_SIZE, this.y * SPRITE_SIZE, canvas);
        } else {
        
            g2d.drawImage(skin1, this.x * SPRITE_SIZE, this.y * SPRITE_SIZE, canvas);
        }
    }

    public Assets getAsset() {
        return asset;
    }

    public void setAsset(Assets asset) {
        this.asset = asset;
    }

    public boolean isDefaultSkin() {
        return defaultSkin;
    }

    public void setDefaultSkin(boolean defaultSkin) {
        this.defaultSkin = defaultSkin;
    }
    
    
}
