/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.domain;
import java.awt.Graphics2D;
import javax.swing.JPanel;
/**
 *
 * @author oscar
 */
public interface Drawable {
    
    public abstract void paint(Graphics2D g2d, JPanel canvas);
    
    public abstract void paint(Graphics2D g2d, JPanel canvas, int x, int y); 
   
}

