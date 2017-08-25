/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.presentation;
import boxworld.domain.Assets;
import boxworld.domain.Sprite;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author oscar
 */
public class Canvas3 extends JPanel implements Runnable{
    
    private final int C_WIDTH = 688; //ancho del canvas en pixeles
    private final int C_HEIGHT = 688; //alto del canvas en pixeles
    private final int SPRITE_SIZE = 43; //ancho y alto del sprite en pixeles
    
    private final BufferedImage bufferedImage; 
    
    private final Sprite[][] world = new Sprite[16][16];
    
    public Canvas3() {
        setFocusable(true);
        setSize(new Dimension(C_WIDTH, C_HEIGHT));
        setPreferredSize(new Dimension(C_WIDTH, C_HEIGHT));
        
        this.bufferedImage = new BufferedImage(this.C_WIDTH, this.C_HEIGHT, BufferedImage.TYPE_INT_RGB); 

        
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
            
                world[i][j] = new Sprite(Assets.FLOOR, i, j);
            }        
        }
        
        //Columna X=3
        world[3][6] = new Sprite(Assets.WALL, 3, 6);
        world[3][7] = new Sprite(Assets.WALL, 3, 7);
        world[3][8] = new Sprite(Assets.WALL, 3, 8);
        world[3][9] = new Sprite(Assets.WALL, 3, 9);
        //Columna X=4
        world[4][4] = new Sprite(Assets.WALL, 4, 4);
        world[4][5] = new Sprite(Assets.WALL, 4, 5);
        world[4][6] = new Sprite(Assets.WALL, 4, 6);
        world[4][9] = new Sprite(Assets.WALL, 4, 9);
        world[4][10] = new Sprite(Assets.WALL, 4, 10);
        //Columna X=5
        world[5][4] = new Sprite(Assets.WALL, 5, 4);
        world[5][6] = new Sprite(Assets.BOX, 5, 6);
        world[5][7] = new Sprite(Assets.PLAYER, 5, 7);
        world[5][8] = new Sprite(Assets.FLOOR, false, 5, 8);
        world[5][9] = new Sprite(Assets.FLOOR, false, 5, 9);
        world[5][10] = new Sprite(Assets.WALL, 5, 10);
        //Columna X=6
        world[6][4] = new Sprite(Assets.WALL, 6, 4);
        world[6][6] = new Sprite(Assets.WALL, 6, 6);
        world[6][8] = new Sprite(Assets.FLOOR, false, 6, 8);
        world[6][9] = new Sprite(Assets.FLOOR, false, 6, 9);
        world[6][10] = new Sprite(Assets.WALL, 6, 10);
        //Columna X=7
        world[7][4] = new Sprite(Assets.WALL, 7, 4);
        world[7][6] = new Sprite(Assets.WALL, 7, 6);
        world[7][7] = new Sprite(Assets.BOX, 7, 7);
        world[7][8] = new Sprite(Assets.WALL, 7, 8);
        world[7][9] = new Sprite(Assets.WALL, 7, 9);
        world[7][10] = new Sprite(Assets.WALL, 7, 10);
        //Columna X=8
        world[8][4] = new Sprite(Assets.WALL, 8, 4);
        world[8][6] = new Sprite(Assets.WALL, 8, 6);
        world[8][10] = new Sprite(Assets.WALL, 8, 10);
        //Columna X=9
        world[9][4] = new Sprite(Assets.WALL, 9, 4);
        world[9][8] = new Sprite(Assets.BOX, 9, 8);
        world[9][10] = new Sprite(Assets.WALL, 9, 10);
        //Columna X=10
        world[10][4] = new Sprite(Assets.WALL, 10, 4);
        world[10][5] = new Sprite(Assets.WALL, 10, 5);
        world[10][7] = new Sprite(Assets.BOX, 10, 7);
        world[10][10] = new Sprite(Assets.WALL, 10, 10);
        //Columna X=11
        world[11][5] = new Sprite(Assets.WALL, 11, 5);
        world[11][8] = new Sprite(Assets.WALL, 11, 8);
        world[11][9] = new Sprite(Assets.WALL, 11, 9);
        world[11][10] = new Sprite(Assets.WALL, 11, 10);
        //Columna X=12
        world[12][5] = new Sprite(Assets.WALL, 12, 5);
        world[12][6] = new Sprite(Assets.WALL, 12, 6);
        world[12][7] = new Sprite(Assets.WALL, 12, 7);
        world[12][8] = new Sprite(Assets.WALL, 12, 8);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) bufferedImage.createGraphics();
        g2D.setColor(Color.lightGray);
        g2D.fillRect(0,0,this.getWidth(),this.getHeight());
        
        Sprite sprite;
        for (int x = 0; x <= 15; x++ )
        {
            for (int y = 0; y <= 15; y++)
            {
                if (world[x][y] == null)
                    continue;
                sprite = world[x][y];
                sprite.paint(g2D, this);
            }
        }
        g.drawImage(bufferedImage, 0, 0, this);
    }
    
    @Override
    public void run() {
        repaint();
    }
}
