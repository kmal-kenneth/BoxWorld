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
 * CLASE QUE HEREDA A LOS OBJETOS IMAGEN
 */
public class Sprite {
    
    protected int x;
    protected int y;
    protected Image skin1;
    protected Image skin2;
    private Assets asset; // INSTANCIA DEL ENUM ASSETS
    private boolean defaultSkin; // PIEL POR DEFECTO
    protected final int SPRITE_SIZE = 43;

    // CONTRUCTOR QUE RECIBE SOLO LA INSTANCIA DE ENUM ASSETS
    public Sprite(Assets asset) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        defaultSkin = true;
    } 
    
    // CONTRUCTOR QUE RECIBE LA INSTANCIA DE ENUM ASSETS MAS LAS COORDENADAS EN X Y Y
    public Sprite(Assets asset, int x, int y) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        this.x = x;
        this.y = y;
        
        defaultSkin = true;
    } 
    
    // CONTRUCTOR QUE RECIBE LA INSTANCIA DE ENUM ASSETS Y EL FALSO O VERDADERO DE LA PIEL DEL SPRITE
    public Sprite(Assets asset, boolean defaultSkin) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        this.defaultSkin = defaultSkin;
    }
    
    // CONTRUCTOR QUE RECIBE LA INSTANCIA DE ENUM ASSETS, EL FALSO O VERDADERO DE LA PIEL DEL SPRITE
    // Y LAS COORDENADAS EN X Y Y
    public Sprite(Assets asset, boolean defaultSkin, int x, int y) {
        this.asset = asset;
        
        skin1 = this.asset.getSkin1();
        skin2 = this.asset.getSkin2();
        
        this.x = x;
        this.y = y;
        
        this.defaultSkin = defaultSkin;
    }
   
    // SETTERS Y GETTERS
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

    // METODO PAINT DESDE LA CLASE, PARA PINTURA DE UNA U OTRA PIEL.
    // SI LA PIEL ES LA PRIMERA, QUE LA PINTE SEGUN TAMAÑO DE PIXELES,
    // SI LA SEGUNDA PIEL NO TIENE NADA, QUE SE LA PINTE, Y PARA CUALQUIER
    // OTRO ESTADO, QUE PINTE LA PRIMERA
    public void paint(Graphics2D g2d, JPanel canvas) {
        if (defaultSkin)
        {
            g2d.drawImage(skin1, this.x * SPRITE_SIZE, this.y * SPRITE_SIZE, canvas);
        } 
        else if(skin2 != null)
        {        
            g2d.drawImage(skin2, this.x * SPRITE_SIZE, this.y * SPRITE_SIZE, canvas);
        } 
        else 
        {
            g2d.drawImage(skin1, this.x * SPRITE_SIZE, this.y * SPRITE_SIZE, canvas);
        }
    }

    // GETTERS Y SETTERS DE LA INSTANCIA ASSETS
    public Assets getAsset() {
        return asset;
    }

    public void setAsset(Assets asset) {
        this.asset = asset;
    }
    
    // GET Y SET DE LA PIEL POR DEFECTO
    public boolean isDefaultSkin() {
        return defaultSkin;
    }

    public void setDefaultSkin(boolean defaultSkin) {
        this.defaultSkin = defaultSkin;
    }
}
