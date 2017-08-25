/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.presentation;
import boxworld.domain.Assets;
import boxworld.domain.Sprite;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Spring;
import simplesnake.Board;

/**
 *
 * @author oscar
 */
public class Canvas3 extends JPanel implements Runnable,  ActionListener {
    
    private final int C_WIDTH = 688; //ancho del canvas en pixeles
    private final int C_HEIGHT = 688; //alto del canvas en pixeles
    
    private final BufferedImage bufferedImage; 
    
    private Sprite pj;
    
    private final Sprite[][] world = new Sprite[16][16];
    private final Sprite[][] worldFloor = new Sprite[16][16];
    private final Sprite[] goals = new Sprite[4];
    private final Sprite[] boxes = new Sprite[4];
    
    private boolean check = false;
    private boolean check1 = false;
    private boolean check2 = false;
    private boolean check3 = false;
    
    public Canvas3() {
        
        addKeyListener(new Canvas3.TAdapter());
        setFocusable(true);
        setSize(new Dimension(C_WIDTH, C_HEIGHT));
        setPreferredSize(new Dimension(C_WIDTH, C_HEIGHT));
        
        this.bufferedImage = new BufferedImage(this.C_WIDTH, this.C_HEIGHT, BufferedImage.TYPE_INT_RGB); 

        
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
            
                worldFloor[i][j] = new Sprite(Assets.FLOOR, i, j);
            }        
        }
        worldFloor[5][8] = new Sprite(Assets.FLOOR, false, 5, 8);
        worldFloor[5][9] = new Sprite(Assets.FLOOR, false, 5, 9);
        worldFloor[6][9] = new Sprite(Assets.FLOOR, false, 6, 9);
        worldFloor[6][8] = new Sprite(Assets.FLOOR, false, 6, 8);
        
        goals[0] = worldFloor[5][8];        
        goals[1] = worldFloor[5][9];        
        goals[2] = worldFloor[6][9];        
        goals[3] = worldFloor[6][8];     
        
        world[5][6] = new Sprite(Assets.BOX, 5, 6);
        world[7][7] = new Sprite(Assets.BOX, 7, 7);
        world[9][8] = new Sprite(Assets.BOX, 9, 8);
        world[10][7] = new Sprite(Assets.BOX, 10, 7);
        
        boxes[0] = world[5][6];
        boxes[1] = world[7][7];
        boxes[2] = world[9][8];
        boxes[3] = world[10][7];
        
        world[5][7] = new Sprite(Assets.PLAYER, 5, 7);
        pj = world[5][7];
        
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
        world[5][10] = new Sprite(Assets.WALL, 5, 10);
        //Columna X=6
        world[6][4] = new Sprite(Assets.WALL, 6, 4);
        world[6][6] = new Sprite(Assets.WALL, 6, 6);
        world[6][10] = new Sprite(Assets.WALL, 6, 10);
        //Columna X=7
        world[7][4] = new Sprite(Assets.WALL, 7, 4);
        world[7][6] = new Sprite(Assets.WALL, 7, 6);
        world[7][8] = new Sprite(Assets.WALL, 7, 8);
        world[7][9] = new Sprite(Assets.WALL, 7, 9);
        world[7][10] = new Sprite(Assets.WALL, 7, 10);
        //Columna X=8
        world[8][4] = new Sprite(Assets.WALL, 8, 4);
        world[8][6] = new Sprite(Assets.WALL, 8, 6);
        world[8][10] = new Sprite(Assets.WALL, 8, 10);
        //Columna X=9
        world[9][4] = new Sprite(Assets.WALL, 9, 4);
        world[9][10] = new Sprite(Assets.WALL, 9, 10);
        //Columna X=10
        world[10][4] = new Sprite(Assets.WALL, 10, 4);
        world[10][5] = new Sprite(Assets.WALL, 10, 5);
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
        
        for (int x = 0; x <= 15; x++ )
        {
            for (int y = 0; y <= 15; y++)
            {
                if (worldFloor[x][y] == null){
                
                    continue;
                }

                worldFloor[x][y].paint(g2D, this);
            }
        }
        
        for (int x = 0; x <= 15; x++ )
        {
            for (int y = 0; y <= 15; y++)
            {
                if (world[x][y] == null){
                    continue;
                }

                world[x][y].paint(g2D, this);
            }
        }
        
        g.drawImage(bufferedImage, 0, 0, this);
    }
    
    @Override
    public void run() {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        System.out.println("test");
    }
    
    private class TAdapter extends KeyAdapter {
        
        private Sprite point = null;
        private Sprite point2 = null;

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:                    
                    
                    //Si el Sprite izquierdo es muro no se mueve
                    if (!getWall(pj.getX() -1, pj.getY())) {
                        
                        Sprite box = getBox(pj.getX() -1, pj.getY());
                        Sprite boxNextBox = getBox(pj.getX() -2, pj.getY());
                        boolean wallNextBox = getWall(pj.getX() -2, pj.getY());
                        
                        if (box != null && boxNextBox != null) {
                            //No se hace nada en este caso;
                        } else if( box != null && !wallNextBox){
                            
                            box.setX(box.getX() -1);                            
                            pj.setX(pj.getX() -1);
                        } else if (box != null && wallNextBox) {
                            //No se hace nada en este caso;
                        } else {
                        
                            pj.setX(pj.getX() -1);
                        }
                    
                    }
                    
                    
                    break;
                case KeyEvent.VK_RIGHT:
                    
                    //Si el Sprite izquierdo es muro no se mueve
                    if (!getWall(pj.getX() +1, pj.getY())) {
                        
                        Sprite box = getBox(pj.getX() +1, pj.getY());
                        boolean wallNextBox = getWall(pj.getX() +2, pj.getY());
                        Sprite boxNextBox = getBox(pj.getX() +2, pj.getY());
                        
                        if (box != null && boxNextBox != null) {
                            
                            //No se hace nada en este caso;
                            
                        } else if( box != null && !wallNextBox){
                            
                            box.setX(box.getX() +1);                            
                            pj.setX(pj.getX() +1);
                        } else if (box != null && wallNextBox) {
                            //No se hace nada en este caso;

                        } else {
                        
                            pj.setX(pj.getX() +1);
                        }
                    
                    }
                    break;
                case KeyEvent.VK_UP:
                    
                    //Si el Sprite izquierdo es muro no se mueve
                    if (!getWall(pj.getX(), pj.getY() -1)) {
                        
                        Sprite box = getBox(pj.getX(), pj.getY() -1);
                        boolean wallNextBox = getWall(pj.getX(), pj.getY() -2);
                        Sprite boxNextBox = getBox(pj.getX(), pj.getY() -2);
                        
                        if (box != null && boxNextBox != null) {
                            //No se hace nada en este caso;
                        } else if( box != null && !wallNextBox){
                            
                            box.setY(box.getY() -1);                            
                            pj.setY(pj.getY() -1);
                        } else if (box != null && wallNextBox) {
                            //No se hace nada en este caso;
                        } else {
                        
                            pj.setY(pj.getY() -1);
                        }
                    
                    }
                    
                    break;
                case KeyEvent.VK_DOWN:
                    
                    //Si el Sprite izquierdo es muro no se mueve
                    if (!getWall(pj.getX(), pj.getY() +1)) {
                        
                        Sprite box = getBox(pj.getX(), pj.getY() +1);
                        Sprite boxNextBox = getBox(pj.getX(), pj.getY() +2);
                        boolean wallNextBox = getWall(pj.getX(), pj.getY() +2);
                        
                        if (box != null && boxNextBox != null) {
                            //No se hace nada en este caso;
                        } else if( box != null && !wallNextBox){
                            
                            box.setY(box.getY() +1);                            
                            pj.setY(pj.getY() +1);
                        } else if (box != null && wallNextBox) {
                            //No se hace nada en este caso;
                        } else {
                        
                            pj.setY(pj.getY() +1);
                        }
                    
                    }                    
                    
                    break;
            }
                    // Agregar verificacion si tor esta en goal
                    pjGoals();
                    // Agregar verificacion si box esta en goal
                    boxGoals();
                    
                    //Verificar si todos los estados estan en true Para 
                    if(check && check1 && check2 && check3) {
                    
                        System.out.println("finish");
                    }
                    
                    repaint();
        }
        
        
        private Sprite getBox(int x, int y){
            
            Sprite t = null;
        
            for (int i = 0; i < 4; i++ )
            {
                if (boxes[i].getX() == x && boxes[i].getY() == y){
                    t = boxes[i];
                    break;
                }
            }
            
            return t;
        }
        
        private boolean getWall(int x, int y){
            
            boolean t = false;
        
            for (int i = 0; i <= 15; i++ )
            {
                for (int j = 0; j <= 15; j++)
                {
                    if (world[i][j] != null && (world[i][j].getX() == x && world[i][j].getY() == y) && world[i][j].getAsset() == Assets.WALL){
                        t = true;
                        break;
                    }
                }
            }
            
            return t;
        }
        
        private void pjGoals(){
            
            boolean defaultSkin = true;
        
            for (int i= 0; i < 4; i++){
            
                if(goals[i].getX() == pj.getX() && goals[i].getY() == pj.getY()) {
                
                    defaultSkin = false;
                }
            }
                    pj.setDefaultSkin(defaultSkin);
        }
        
        private void boxGoals(){
            
            boolean defaultSkin1 = true;
            boolean defaultSkin2 = true;
            boolean defaultSkin3 = true;
            boolean defaultSkin4 = true;
            
            check =  false;
            check1 =  false;
            check2 =  false;
            check3 =  false;
        
            for (int i= 0; i < 4; i++){
                for (int j= 0; j < 4; j++){

                        if(goals[i].getX() == boxes[0].getX() && goals[i].getY() == boxes[0].getY()){
                            
                            defaultSkin1 =  false;
                            check = true;
                        
                        } else if(goals[i].getX() == boxes[1].getX() && goals[i].getY() == boxes[1].getY()){
                        
                            check1 = true;
                            defaultSkin2 =  false;
                        } else if(goals[i].getX() == boxes[2].getX() && goals[i].getY() == boxes[2].getY()){
                        
                            check2 = true;
                            defaultSkin3 =  false;
                        } else if(goals[i].getX() == boxes[3].getX() && goals[i].getY() == boxes[3].getY()){
                            
                            check3 = true;
                            defaultSkin4 =  false;
                        }
                }
            }
            
            boxes[0].setDefaultSkin(defaultSkin1);
            boxes[1].setDefaultSkin(defaultSkin2);
            boxes[2].setDefaultSkin(defaultSkin3);
            boxes[3].setDefaultSkin(defaultSkin4);
            
        }
        
    }
}

