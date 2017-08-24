/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.classes;
import boxworld.domain.Sprite;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author oscar
 */
public class Box extends Sprite {
    
    public Box(String type){
        if (type.equals("a"))
            this.skin1 = (new ImageIcon("image/box1.png").getImage());
        else
            this.skin2 = (new ImageIcon("image/box2.png").getImage());
    }
    
    public void paint(Graphics2D g2d, JPanel canvas, int x, int y){
        this.x = x;
        this.y = y;
        paint(g2d, canvas);
    }
    
    /**
     *
     * @param g2d
     * @param canvas
     */
    public void paint(Graphics2D g2d, JPanel canvas) {
        g2d.drawImage(getSkin1(), x * SPRITE_SIZE, y * SPRITE_SIZE, canvas);
        g2d.drawImage(getSkin2(), x * SPRITE_SIZE, y * SPRITE_SIZE, canvas);
    }
}
