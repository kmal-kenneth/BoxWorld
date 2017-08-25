/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplesnake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author GOLLO
 */
public class Board extends JPanel implements ActionListener {
    private final int B_WIDTH = 688;
    private final int B_HEIGHT = 688;
    private final int DOT_SIZE = 43;
    private final int ALL_DOTS = ((B_WIDTH * B_HEIGHT) / (DOT_SIZE * DOT_SIZE));
    private final int INITIAL_SIZE = 3;
    private final int RAND_POS_X = B_WIDTH / DOT_SIZE;
    private final int RAND_POS_Y = B_HEIGHT / DOT_SIZE; 
    private int DELAY = 200;
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
    private int dots;
    private int apple_x;
    private int apple_y;
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    
    private Timer timer;
    private Image wall;
    private Image player1;
    private Image player2;
    private Image floor1;
    private Image floor2;
    private Image box1;
    private Image box2;
    
    public Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImage();
        initGame();
    }
    
    private void loadImage(){
        // CARGA IMAGEN DE LA PARED DE LADRILLOS ROJA
        ImageIcon icWall = new ImageIcon("images/wall.png");
        wall = icWall.getImage();
        
        //CARGA IMAGEN DEL JUGADOR Y SU SEGUNDA PIEL
        ImageIcon icPlayer1 = new ImageIcon("images/player1.png");
        ImageIcon icPlayer2 = new ImageIcon("images/player2.png");
        player1 = icPlayer1.getImage();
        player2 = icPlayer2.getImage();
        
        //CARGA IMAGEN DEL PISO PRINCIPAL Y DEL OBJETIVO
        ImageIcon icFloor1 = new ImageIcon("images/floor1.png");
        ImageIcon icFloor2 = new ImageIcon("images/floor2.png");
        floor1 = icFloor1.getImage();
        floor2 = icFloor2.getImage();
        
        //CARGA IMAGEN DE LA CAJITA Y SU SEGUNDA PIEL
        ImageIcon icBox1 = new ImageIcon("images/box1.png");
        ImageIcon icBox2 = new ImageIcon("images/box2.png");
        box1 = icBox1.getImage();
        box2 = icBox2.getImage();
    }
    
    private void locateApple(){
        int r, z;
        boolean x_found = false;
        boolean y_found = false;
        // Calcular coordenada X de la caja
        while (!x_found){
            r = (int) (Math.random() * RAND_POS_X);
            System.out.println(r);
            apple_x = (r * DOT_SIZE);
            System.out.println(apple_x);
            x_found = true;
            
            for ( z = dots ; z >= 0 ; z--){
                if (apple_x == x[z]){
                    x_found = false;
                    break;
                }
            }
        }
        // calcular coordenada Y de la caja
        while (!y_found){
            r = (int) (Math.random() * RAND_POS_Y);
            apple_y = (r * DOT_SIZE);
            y_found = true;
            
            for ( z = dots ; z >= 0 ; z--){
                if (apple_y == y[z]){
                    y_found = false;
                    break;
                }
            }
        }
    }
    
    private void initGame(){
        this.dots = INITIAL_SIZE;
        // posición inicial
        for (int z = 0; z < dots; z++){
            x[z] = (DOT_SIZE * 5) - z * DOT_SIZE;
            y[z] = (DOT_SIZE * 5);
        }
        
        this.locateApple();
        timer = new Timer(DELAY, this);
        
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //Dibujar el canvas
        this.doDrawing(g);
    }
    
    private void gameOver(Graphics g){
        String message = "Game Over";
        Font big = new Font("Helvética", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(big);
        g.setColor(Color.white);
        g.setFont(big);
        g.drawString(message,((B_WIDTH - metr.stringWidth(message))/2),(B_HEIGHT / 2));
    }
    
    private void doDrawing(Graphics g){
        
        if (inGame){
            //PINTA LAS PAREDES EXTERNAS EN EL CANVAS:
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 4),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 4),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 4),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 9), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 5),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 5),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 9), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 6),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 4), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 6),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 5), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 6),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 6), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 6),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 6),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 9), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 4), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 9), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 10), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 11), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 4), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 5), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 6), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 11), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 9),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 6), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 9),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 9),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 9), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 9),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 10), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 9),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 11), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 10),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 6), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 10),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 11),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 6), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 11),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            g.drawImage(wall, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 11),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);  
            
            //PINTA LOS OBJETIVOS EN EL CANVAS:
            g.drawImage(floor2, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 5),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);
            g.drawImage(floor2, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 5), this);
            g.drawImage(floor2, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 10), this);
            g.drawImage(floor2, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 10),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            
            //PINTA EL PISO NORMAL EN EL CANVAS:
            g.drawImage(floor1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 6),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);
            g.drawImage(floor1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 6), this);
            g.drawImage(floor1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            
            //PINTA LAS CAJAS EN EL CANVAS:
            g.drawImage(box1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            g.drawImage(box1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 7),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);
            g.drawImage(box1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 9), this);
            g.drawImage(box1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 9),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 7), this);
            
            //PINTA EL JUGADOR EN EL CANVAS:
            //g.drawImage(player1, ((B_HEIGHT/(B_HEIGHT/DOT_SIZE)) * 8),((B_WIDTH/(B_WIDTH/DOT_SIZE)) * 8), this);
            
            for (int z = 0; z < dots; z++){
                if (z == 0){
                    g.drawImage(player1, x[0], y[0], this);
                }
                /*else{
                    g.drawImage(body, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
    }else{
        gameOver(g);
                }*/
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame){
//            move();
            //acelerator();
            checkCollision();
//            checkApple();
//            System.out.println("test");
        }
        repaint();
    }
    
    private void acelerator(){
        for (int z = dots ; z > 0 ; z++ ){
            if (dots % 2 == 0){
            DELAY-=10;
            }
        }
    }
    
    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

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
    
    private void printFeatures(Graphics g){
        String message = "Conteo: " + (dots - 3) + " manzanas.";
        String message1 = "Coordenadas de la manzana: ( X <" + apple_x + "> , Y <" + apple_y + "> )." ;
        String message2 = "Coordenadas de la cabeza: ( X <" + x[0] + "> , Y <" + y[0] + "> )." ;
        Font big = new Font("Arial", Font.BOLD, 12);
        FontMetrics metr = getFontMetrics(big);
        g.setColor(Color.white);
        g.setFont(big);
        g.drawString(message,20,20);
        g.drawString(message1,20,35);
        g.drawString(message2,20,50);
        
    }
    
    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }
    }
    
    private void checkApple() {
        if ((x[0] == apple_x) && (y[0]+20 == apple_y)) {
            dots++;
            locateApple();
            
            System.out.println("manza");
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
                if ((x[0] == apple_x) && (y[0] == apple_y)) {
                    apple_x-=20;
                    System.out.println("apple1");
                }
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                move();
                if ((x[0] == apple_x) && (y[0] == apple_y)) {
                    apple_x+=20;
                    System.out.println("apple2");
                }
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                move();
                if ((x[0] == apple_x) && (y[0] == apple_y)) {
                    apple_y-=20;
                    System.out.println("apple3");
                }
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                move();
                if ((x[0] == apple_x) && (y[0] == apple_y)) {
                    apple_y+=20;
                    System.out.println("apple4");
                }
            }
        }
    }
    
    
}
