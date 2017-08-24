/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.presentation;

import boxworld.classes.*;
import boxworld.domain.Sprite;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author oscar
 */
public class Canvas extends JPanel implements Runnable{
    
    private final int C_WIDTH = 768; //ancho del canvas en pixeles
    private final int C_HEIGHT = 768; //alto del canvas en pixeles
    private final int DOT_SIZE = 48;//ancho y alto del sprite en pixeles
    private final int ALL_DOTS = ((C_WIDTH * C_HEIGHT) / (DOT_SIZE * DOT_SIZE));

    private final int RAND_POS_X = C_WIDTH / DOT_SIZE;
    private final int RAND_POS_Y = C_HEIGHT / DOT_SIZE; 
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    
    private Image wall;
    private Image floor1;
    private Image floor2;
    private Image box1;
    private Image box2;
    private Image player1;
    private Image player2;
    
    private int box1_x;
    private int box1_y;
    //private final Sprite[][] world = new Sprite[16][16];
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
    public Canvas() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        
        setPreferredSize(new Dimension(C_WIDTH, C_HEIGHT));
        loadImage();
        //initGame();
    }
    
    private void loadImage(){
        ImageIcon wallIcon = new ImageIcon("image/wall.png");
        wall = wallIcon.getImage();
        
        ImageIcon floor1Icon = new ImageIcon("image/floor1.png");
        floor1 = floor1Icon.getImage();
        
        ImageIcon floor2Icon = new ImageIcon("image/floor2.png");
        floor2 = floor2Icon.getImage(); 
        
        ImageIcon box1Icon = new ImageIcon("image/box1.png");
        box1 = box1Icon.getImage();
        
        ImageIcon box2Icon = new ImageIcon("image/box2.png");
        box2 = box2Icon.getImage();
        
        ImageIcon play1Icon = new ImageIcon("image/player1.png");
        player1 = play1Icon.getImage();  
        
        ImageIcon play2Icon = new ImageIcon("image/player2.png");
        player2 = play2Icon.getImage();  
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //Dibujar el canvas
        this.doDrawing(g);
    }
    
    private void doDrawing(Graphics g){
        if (inGame){
            g.drawImage(wall, WIDTH, WIDTH, this);
//            g.drawImage(wall, x[4], y[8], this);
//            g.drawImage(wall, x[4], y[9], this);
//            g.drawImage(wall, x[5], y[7], this);
//            g.drawImage(wall, x[5], y[9], this);
//            g.drawImage(wall, x[6], y[4], this);
//            g.drawImage(wall, x[6], y[5], this);
//            g.drawImage(wall, x[6], y[6], this);
//            g.drawImage(wall, x[6], y[7], this);
//            g.drawImage(wall, x[6], y[9], this);
//            g.drawImage(wall, x[7], y[4], this);
//            g.drawImage(wall, x[7], y[9], this);
//            g.drawImage(wall, x[7], y[10], this);
//            g.drawImage(wall, x[7], y[11], this);
//            g.drawImage(wall, x[8], y[4], this);
//            g.drawImage(wall, x[8], y[5], this);
//            g.drawImage(wall, x[8], y[6], this);
//            g.drawImage(wall, x[8], y[11], this);
//            g.drawImage(wall, x[9], y[6], this);
//            g.drawImage(wall, x[9], y[8], this);
//            g.drawImage(wall, x[9], y[9], this);
//            g.drawImage(wall, x[9], y[10], this);
//            g.drawImage(wall, x[9], y[11], this);
//            g.drawImage(wall, x[10], y[6], this);
//            g.drawImage(wall, x[10], y[8], this);
//            g.drawImage(wall, x[11], y[6], this);
//            g.drawImage(wall, x[11], y[7], this);
//            g.drawImage(wall, x[11], y[8], this);
//            g.drawImage(box1, x[7], y[7], this);
//            g.drawImage(box1, x[7], y[8], this);
//            g.drawImage(box1, x[8], y[9], this);
//            g.drawImage(box1, x[9], y[7], this);
//            g.drawImage(floor2, x[5], y[8], this);
//            g.drawImage(floor2, x[7], y[5], this);
//            g.drawImage(floor2, x[8], y[10], this);
//            g.drawImage(floor2, x[10], y[7], this);
//            g.drawImage(floor1, x[6], y[8], this);
//            g.drawImage(floor1, x[7], y[6], this);
//            g.drawImage(floor1, x[8], y[7], this);
//            g.drawImage(player1, x[8], y[8], this);
            
            //printFeatures(g);
//            for (int z = 0; z < snakeTail; z++){
//                if (z == 0){
//                    g.drawImage(head, x[z], y[z], this);
//                }
//                else{
//                    g.drawImage(body, x[z], y[z], this);
//                }
//            }
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    //Encontrar coordenadas de cada caja
    private void locateBox(){
        int r, z;
        boolean x_found = false;
        boolean y_found = false;
        // Calcular coordenada X de la caja1
        while (!x_found){
            r = (int) (Math.random() * RAND_POS_X);
            box1_x = (r * DOT_SIZE);
            x_found = true;
            
            /*for ( z = dots ; z >= 0 ; z--){
                if (apple_x == x[z]){
                    x_found = false;
                    break;
                }
            }*/
        }
        // calcular coordenada Y de la caja1
        while (!y_found){
            r = (int) (Math.random() * RAND_POS_Y);
            box1_y = (r * DOT_SIZE);
            y_found = true;
            
            /*for ( z = dots ; z >= 0 ; z--){
                if (apple_y == y[z]){
                    y_found = false;
                    break;
                }
            }*/
        }
    }
    /*
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
    }*/
    
    @Override
    public void run() {
        repaint();
    }
    
    private void move() {

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                move();
                if ((x[0] == box1_x) && (y[0] == box1_y)) {
                    box1_x-=20;
                    
                }
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                move();
                if ((x[0] == box1_x) && (y[0] == box1_y)) {
                    box1_x +=20;
                    
                }
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                move();
                if ((x[0] == box1_x) && (y[0] == box1_y)) {
                    box1_y-=20;
                    
                }
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                move();
                if ((x[0] == box1_x) && (y[0] == box1_y)) {
                    box1_y+=20;
                    
                }
            }
        }
    }
    
    /*private void initGame(){
        this.snakeTail = INITIAL_SIZE;
        // posición inicial
        for (int z = 0; z < snakeTail; z++){
            x[z] = (DOT_SIZE * 5) - z * DOT_SIZE;
            y[z] = (DOT_SIZE * 5);
        }
        
        this.locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }*/
}
