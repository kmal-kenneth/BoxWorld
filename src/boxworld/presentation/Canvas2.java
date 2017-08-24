/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.presentation;
import boxworld.classes.*;
import boxworld.domain.Sprite;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author oscar
 */
public class Canvas2 extends JPanel implements Runnable{
    
    private final int C_WIDTH = 768; //ancho del canvas en pixeles
    private final int C_HEIGHT = 768; //alto del canvas en pixeles
    private final int SPRITE_SIZE = 48; //ancho y alto del sprite en pixeles
    
    private final Sprite[][] world = new Sprite[16][16];
    
    private final BufferedImage bufferedImage;
    private Image skin1;
    
    public Canvas2() {
        setFocusable(true);
        setSize(new Dimension(C_WIDTH, C_HEIGHT));
        setPreferredSize(new Dimension(C_WIDTH, C_HEIGHT));
        
        this.bufferedImage = new BufferedImage(this.C_WIDTH, this.C_HEIGHT, BufferedImage.TYPE_INT_RGB);
                    
        //Columna X=4
        world[4][3] = new Wall();
        world[4][4] = new Wall();
        world[4][5] = new Wall();
        world[4][6] = new Wall();
        world[4][7] = new Wall();

        //Columna X=5
        world[5][3] = new Wall();
        world[5][4] = new Player("a");
        world[5][5] = new Floor("a");
        world[5][6] = new Floor("a");
        world[5][7] = new Wall();
        world[5][8] = new Wall();
        world[5][9] = new Wall();
        world[5][10] = new Wall();
        world[5][11] = new Wall();
        //Columna X=6
        world[6][3] = new Wall();
        world[6][4] = new Floor("a");
        world[6][5] = new Box("a");
        world[6][6] = new Box("a");
        world[6][7] = new Wall();
        world[6][8] = new Wall();
        world[6][9] = new Floor("a");
        world[6][10] = new Floor("a");
        world[6][11] = new Wall();
        //Columna X=7
        world[7][3] = new Wall();
        world[7][4] = new Floor("a");
        world[7][5] = new Box("a");
        world[7][6] = new Floor("a");
        world[7][7] = new Floor("a");
        world[7][8] = new Floor("a");
        world[7][9] = new Floor("a");
        world[7][10] = new Floor("a");
        world[7][11] = new Wall();
        //Columna X=8
        world[8][3] = new Wall();
        world[8][4] = new Wall();
        world[8][5] = new Wall();
        world[8][6] = new Wall();
        world[8][7] = new Wall();
        world[8][8] = new Floor("a");
        world[8][9] = new Floor("a");
        world[8][10] = new Floor("a");
        world[8][11] = new Wall();
        //Columna X=9
        world[9][7] = new Wall();
        world[9][8] = new Floor("a");
        world[9][9] = new Wall();
        world[9][10] = new Wall();
        world[9][11] = new Wall();
        //Columna X=10
        world[10][5] = new Wall();
        world[10][6] = new Wall();
        world[10][7] = new Wall();
        world[10][8] = new Floor("a");
        world[10][9] = new Floor("a");
        world[10][10] = new Wall();
        //Columna X=11
        world[11][5] = new Wall();
        world[11][6] = new Floor("b");
        world[11][7] = new Floor("b");
        world[11][8] = new Floor("b");
        world[11][9] = new Floor("a");
        world[11][10] = new Wall();
        //Columna X=11
        world[12][5] = new Wall();
        world[12][6] = new Wall();
        world[12][7] = new Wall();
        world[12][8] = new Wall();
        world[12][9] = new Wall();
        world[12][10] = new Wall();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) bufferedImage.createGraphics();
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0,0,this.getWidth(),this.getHeight());
        
        Sprite sprite;
        for (int x = 0; x <= 15; x++ )
        {
            for (int y = 0; y <= 15; y++)
            {
                if (world[x][y] == null)
                    continue;
                sprite = world[x][y];
                sprite.paint(g2D, this, x, y);
            }
        }
        g.drawImage(bufferedImage, 0, 0, this);
    }
    
    @Override
    public void run() {
        repaint();
    }
}
