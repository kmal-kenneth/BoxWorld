/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld.presentation;

import boxworld.BoxWorld;
import boxworld.domain.Assets;
import boxworld.domain.Sprite;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author oscar
 */
public class Canvas1 extends JPanel implements Runnable,  ActionListener {
    
    private final int C_WIDTH = 688; // ANCHO DEL CANVAS EN PIXELES
    private final int C_HEIGHT = 688; // ALTURA DEL CANVAS EN PIXELES
    
    private final BufferedImage bufferedImage; 
    
    private Sprite playerPos; // PARA USO EN MOVIMIENTO CON TECLADO
    
    private final Sprite[][] worldAssets = new Sprite[16][16]; // ARRAY PARA LOS OBJETOS QUE NO SON DE PISO
    private final Sprite[][] worldFloor = new Sprite[16][16]; // ARRAY PARA LOS OBJETOS DE PISO
    private final Sprite[] targets = new Sprite[4]; // ARRAY PARA POSICIONAR LOS OBJETIVOS 
    private final Sprite[] boxes = new Sprite[4]; // ARRAY PARA POSICIONAR LAS CAJAS
    
    private boolean check = false;    //
    private boolean check1 = false;   // CHECKS PARA VALIDAR EL PASO DE NIVEL
    private boolean check2 = false;   //
    private boolean check3 = false;   //
    
    
    private BoxWorld main;
    
    public Canvas1(BoxWorld main) {
        this.main = main;
        addKeyListener(new Canvas1.TAdapter());
        setFocusable(true);
        setSize(new Dimension(C_WIDTH, C_HEIGHT));
        setPreferredSize(new Dimension(C_WIDTH, C_HEIGHT));
        
        this.bufferedImage = new BufferedImage(this.C_WIDTH, this.C_HEIGHT, BufferedImage.TYPE_INT_RGB); 

        // ESTE FOR LLENA EL CANVAS DE PASTO
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
            
                worldFloor[i][j] = new Sprite(Assets.FLOOR, i, j);
            }        
        }
        
        // POSICIONAMIENTO DE LOS OBJETIVOS EN EL CANVAS, QUE SON FLOOR FALSO
        worldFloor[5][8] = new Sprite(Assets.FLOOR, false, 5, 8);
        worldFloor[7][5] = new Sprite(Assets.FLOOR, false, 7, 5);
        worldFloor[8][10] = new Sprite(Assets.FLOOR, false, 8, 10);
        worldFloor[10][7] = new Sprite(Assets.FLOOR, false, 10, 7);
        
        //CARGA DEL ARRAY CON LAS COORDENADAS DE LOS OBJETIVOS
        targets[0] = worldFloor[5][8];        
        targets[1] = worldFloor[7][5];        
        targets[2] = worldFloor[8][10];        
        targets[3] = worldFloor[10][7];     
        
        //POSICIONAMIENTO DE LAS CAJAS EN EL CANVAS
        worldAssets[7][7] = new Sprite(Assets.BOX, 7, 7);
        worldAssets[7][8] = new Sprite(Assets.BOX, 7, 8);
        worldAssets[8][9] = new Sprite(Assets.BOX, 8, 9);
        worldAssets[9][7] = new Sprite(Assets.BOX, 9, 7);
        
        // CARGA DEL ARRAY CON LAS COORDENADAS DE LAS CAJAS
        boxes[0] = worldAssets[7][7];
        boxes[1] = worldAssets[7][8];
        boxes[2] = worldAssets[8][9];
        boxes[3] = worldAssets[9][7];
        
        // POSICIONAMIENTO DEL PLAYER Y CARGA DE LA VARIABLE playerPos CON SUS COORDENADAS, PARA MOVIMIENTO
        worldAssets[8][8] = new Sprite(Assets.PLAYER, 8, 8);
        playerPos = worldAssets[8][8];
        
        // CARGA INDIVIDUAL DE LAS PAREDES DEL NIVEL
        // Columna X=4
        worldAssets[4][7] = new Sprite(Assets.WALL, 4, 7);
        worldAssets[4][8] = new Sprite(Assets.WALL, 4, 8);
        worldAssets[4][9] = new Sprite(Assets.WALL, 4, 9);
        // Columna X=5
        worldAssets[5][7] = new Sprite(Assets.WALL, 5, 7);
        worldAssets[5][9] = new Sprite(Assets.WALL, 5, 9);
        // Columna X=6
        worldAssets[6][4] = new Sprite(Assets.WALL, 6, 4);
        worldAssets[6][5] = new Sprite(Assets.WALL, 6, 5);
        worldAssets[6][6] = new Sprite(Assets.WALL, 6, 6);
        worldAssets[6][7] = new Sprite(Assets.WALL, 6, 7);
        worldAssets[6][9] = new Sprite(Assets.WALL, 6, 9);
        // Columna X=7
        worldAssets[7][4] = new Sprite(Assets.WALL, 7, 4);
        worldAssets[7][9] = new Sprite(Assets.WALL, 7, 9);
        worldAssets[7][10] = new Sprite(Assets.WALL, 7, 10);
        worldAssets[7][11] = new Sprite(Assets.WALL, 7, 11);
        // Columna X=8
        worldAssets[8][4] = new Sprite(Assets.WALL, 8, 4);
        worldAssets[8][5] = new Sprite(Assets.WALL, 8, 5);
        worldAssets[8][6] = new Sprite(Assets.WALL, 8, 6);
        worldAssets[8][11] = new Sprite(Assets.WALL, 8, 11);
        // Columna X=9
        worldAssets[9][6] = new Sprite(Assets.WALL, 9, 6);
        worldAssets[9][8] = new Sprite(Assets.WALL, 9, 8);
        worldAssets[9][9] = new Sprite(Assets.WALL, 9, 9);
        worldAssets[9][10] = new Sprite(Assets.WALL, 9, 10);
        worldAssets[9][11] = new Sprite(Assets.WALL, 9, 11);
        // Columna X=10
        worldAssets[10][6] = new Sprite(Assets.WALL, 10, 6);
        worldAssets[10][8] = new Sprite(Assets.WALL, 10, 8);
        // Columna X=11
        worldAssets[11][6] = new Sprite(Assets.WALL, 11, 6);
        worldAssets[11][7] = new Sprite(Assets.WALL, 11, 7);
        worldAssets[11][8] = new Sprite(Assets.WALL, 11, 8);

    }
    
    // METODO QUE PINTA Y CARGA EL CANVAS
    // LOS FOR RECORREN LA MATRIZ, SI NO HAY NADA EN LA POS, CONTINUA; SI HAY, PINTA
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
                if (worldFloor[x][y] == null)
                {
                    continue;
                }
                worldFloor[x][y].paint(g2D, this);
            }
        }
        
        for (int x = 0; x <= 15; x++ )
        {
            for (int y = 0; y <= 15; y++)
            {
                if (worldAssets[x][y] == null)
                {
                    continue;
                }
                worldAssets[x][y].paint(g2D, this);
            }
        }
        g.drawImage(bufferedImage, 0, 0, this);
    }
    
    // METODO QUE ACTUALIZA EL CANVAS
    @Override
    public void run() {
        repaint();
    }
    
    // METODO QUE ACTUALIZA LA ACCION REALIZADA EN EL TECLADO
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    // CLASE ENCARGADA DEL MOVIMIENTO POR MEDIO DEL TECLADO
    private class TAdapter extends KeyAdapter {
        private Sprite point1 = null;
        private Sprite point2 = null;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                //METODO DE MOVIMIENTO HACIA LA IZQUIERDA
                case KeyEvent.VK_LEFT:                    
                    //VALIDA SI EL SPRITE IZQUIERDO ES MURO, CAJA/MURO O CAJA/CAJA, PARA NO MOVERSE MAS
                    if (!getWall(playerPos.getX()-1, playerPos.getY())) 
                    {
                        Sprite box = getBox(playerPos.getX()-1, playerPos.getY());
                        Sprite boxAfterBox = getBox(playerPos.getX()-2, playerPos.getY());
                        boolean wallAfterBox = getWall(playerPos.getX()-2, playerPos.getY());
                        
                        if (box != null && boxAfterBox != null) 
                        {
                            // NO HACE NADA
                        } 
                        else if( box != null && !wallAfterBox)
                        {    
                            box.setX(box.getX() -1);                            
                            playerPos.setX(playerPos.getX()-1);
                        } 
                        else if (box != null && wallAfterBox) 
                        {
                            // NO HACE NADA
                        } 
                        else 
                        {
                            playerPos.setX(playerPos.getX()-1);
                        }
                    
                    }
                    break;
                
                //METODO DE MOVIMIENTO HACIA LA DERECHA
                case KeyEvent.VK_RIGHT:
                    //VALIDA SI EL SPRITE DERECHO ES MURO, CAJA/MURO O CAJA/CAJA, PARA NO MOVERSE MAS
                    if (!getWall(playerPos.getX()+1, playerPos.getY())) 
                    {                        
                        Sprite box = getBox(playerPos.getX()+1, playerPos.getY());
                        boolean wallNextBox = getWall(playerPos.getX()+2, playerPos.getY());
                        Sprite boxNextBox = getBox(playerPos.getX()+2, playerPos.getY());
                        
                        if (box != null && boxNextBox != null) 
                        {    
                            // NO HACE NADA
                        } 
                        else if( box != null && !wallNextBox)
                        {
                            box.setX(box.getX() +1);                            
                            playerPos.setX(playerPos.getX()+1);
                        } 
                        else if (box != null && wallNextBox) 
                        {
                            // NO HACE NADA
                        } 
                        else 
                        { 
                            playerPos.setX(playerPos.getX()+1);
                        }
                    }
                    break;
                    
                //METODO DE MOVIMIENTO HACIA ARRIBA 
                case KeyEvent.VK_UP:
                    
                    //VALIDA SI EL SPRITE SUPERIOR ES MURO, CAJA/MURO O CAJA/CAJA, PARA NO MOVERSE MAS
                    if (!getWall(playerPos.getX(), playerPos.getY()-1)) 
                    {
                        Sprite box = getBox(playerPos.getX(), playerPos.getY()-1);
                        boolean wallNextBox = getWall(playerPos.getX(), playerPos.getY()-2);
                        Sprite boxNextBox = getBox(playerPos.getX(), playerPos.getY()-2);
                        
                        if (box != null && boxNextBox != null) 
                        {
                            // NO HACE NADA
                        } 
                        else if( box != null && !wallNextBox)
                        {
                            box.setY(box.getY() -1);                            
                            playerPos.setY(playerPos.getY()-1);
                        } 
                        else if (box != null && wallNextBox) 
                        {
                            // NO HACE NADA
                        } 
                        else 
                        {
                            playerPos.setY(playerPos.getY() -1);
                        }
                    }
                    break;
                    
                //METODO DE MOVIMIENTO HACIA ABAJO
                case KeyEvent.VK_DOWN:
                    
                    //VALIDA SI EL SPRITE INFERIOR ES MURO, CAJA/MURO O CAJA/CAJA, PARA NO MOVERSE MAS
                    if (!getWall(playerPos.getX(), playerPos.getY() +1)) {
                        
                        Sprite box = getBox(playerPos.getX(), playerPos.getY()+1);
                        Sprite boxNextBox = getBox(playerPos.getX(), playerPos.getY()+2);
                        boolean wallNextBox = getWall(playerPos.getX(), playerPos.getY()+2);
                        
                        if (box != null && boxNextBox != null) 
                        {
                            // NO HACE NADA
                        } 
                        else if( box != null && !wallNextBox)
                        {
                            box.setY(box.getY()+1);                            
                            playerPos.setY(playerPos.getY()+1);
                        } 
                        else if (box != null && wallNextBox) 
                        {
                            // NO HACE NADA
                        } 
                        else 
                        {
                            playerPos.setY(playerPos.getY()+1);
                            main.init(new Canvas2());
                        }
                    
                    }                    
                    break;
            }
            playerInTarget();
            boxInTarget();

            //ESTE IF VALIDA SI LAS CAJAS ESTAN EN LOS OBJETIVOS PARA PASAR DE NIVEL
            if(check && check1 && check2 && check3) 
            {
                System.out.println("HACIA EL SIGUIENTE NIVEL");
                
                removeAll();//or remove(JComponent)
                add(new Canvas2());
                revalidate();
                repaint();
            }
            repaint();
        }
    }
    
    // METODO QUE RECIBE LAS COORDENADAS X Y Y DE LAS CAJAS PARA USARSE EN EL MAIN DE MOVIMIENTO
    private Sprite getBox(int x, int y){
        Sprite t = null;
        for (int i = 0; i < 4; i++ )
        {
            if (boxes[i].getX() == x && boxes[i].getY() == y)
            {
                t = boxes[i];
                break;
            }
        }
        return t;
    }
        
    // METODO QUE RECIBE LAS COORDENADAS X Y Y DE LAS PAREDES PARA USARSE EN EL MAIN DE MOVIMIENTO
    private boolean getWall(int x, int y){
        boolean t = false;
        for (int i = 0; i <= 15; i++ )
        {
            for (int j = 0; j <= 15; j++)
            {
                if (worldAssets[i][j] != null && (worldAssets[i][j].getX() == x && worldAssets[i][j].getY() == y) 
                        && worldAssets[i][j].getAsset() == Assets.WALL)
                {
                    t = true;
                    break;
                }
            }
        }
        return t;
    }
    // METODO QUE VALIDA SI EL JUGADOR ESTA EN LOS OBJETIVOS, PARA CAMBIARLE LA PIEL
    private void playerInTarget(){
        boolean defaultSkin = true;

        for (int i= 0; i < 4; i++)
        {
            if(targets[i].getX() == playerPos.getX() && targets[i].getY() == playerPos.getY()) 
            {
                defaultSkin = false;
            }
        }
        playerPos.setDefaultSkin(defaultSkin);
    }
        
    // METODO QUE VALIDA SI LAS CAJAS ESTA EN LOS OBJETIVOS, PARA CAMBIARLES 
    // LA PIEL Y VALIDAR LOS CHECKS DE PASO DE NIVEL
    private void boxInTarget(){
        boolean defaultSkin1 = true;
        boolean defaultSkin2 = true;
        boolean defaultSkin3 = true;
        boolean defaultSkin4 = true;

        check =  false;
        check1 =  false;
        check2 =  false;
        check3 =  false;

        for (int i= 0; i < 4; i++){
            for (int j= 0; j < 4; j++)
            {
                if(targets[i].getX() == boxes[0].getX() && targets[i].getY() == boxes[0].getY())
                {
                    defaultSkin1 =  false;
                    check = true;
                } 
                else if(targets[i].getX() == boxes[1].getX() && targets[i].getY() == boxes[1].getY())
                {
                    check1 = true;
                    defaultSkin2 =  false;
                } 
                else if(targets[i].getX() == boxes[2].getX() && targets[i].getY() == boxes[2].getY())
                {
                    check2 = true;
                    defaultSkin3 =  false;
                } 
                else if(targets[i].getX() == boxes[3].getX() && targets[i].getY() == boxes[3].getY())
                {
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
