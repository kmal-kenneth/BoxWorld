/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.domain;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author kenma
 */
public enum Assets {
    
    /**
     *
     */
    BOX(new ImageIcon("image/box1.png").getImage(), new ImageIcon("image/box2.png").getImage()),
    Player(new ImageIcon("image/player1.png").getImage(), new ImageIcon("image/player2.png").getImage()),
    WALL(new ImageIcon("image/wall.png").getImage(), null),
    FLOOR(new ImageIcon("image/floor1.png").getImage(), new ImageIcon("image/floor2.png").getImage());
    
    private Image skin1;
    private Image skin2;

    private Assets(Image skin1, Image skin2) {
        this.skin1 = skin1;
        this.skin2 = skin2;
    }

    public Image getSkin1() {
        return skin1;
    }

    public void setSkin1(Image skin1) {
        this.skin1 = skin1;
    }

    public Image getSkin2() {
        return skin2;
    }

    public void setSkin2(Image skin2) {
        this.skin2 = skin2;
    }
    
    
    
    
    
}
