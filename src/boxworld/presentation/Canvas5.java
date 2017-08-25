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

/**
 *
 * @author oscar
 */
public class Canvas5 extends JPanel implements Runnable,  ActionListener {
    
    private final int C_WIDTH = 688; // ANCHO DEL CANVAS EN PIXELES
    private final int C_HEIGHT = 688; // ALTURA DEL CANVAS EN PIXELES
    
    private final BufferedImage bufferedImage; 
    
    private Sprite playerPos; // PARA USO EN MOVIMIENTO CON TECLADO
    
    private final Sprite[][] worldAssets = new Sprite[16][16]; // ARRAY PARA LOS OBJETOS QUE NO SON DE PISO
    private final Sprite[][] worldFloor = new Sprite[16][16]; // ARRAY PARA LOS OBJETOS DE PISO
    private final Sprite[] targets = new Sprite[1]; // ARRAY PARA POSICIONAR LOS OBJETIVOS 
    private final Sprite[] boxes = new Sprite[1]; // ARRAY PARA POSICIONAR LAS CAJAS
    
    private boolean check = false;    // CHECKS PARA VALIDAR EL PASO DE NIVEL
    
    public Canvas5() {
        
        addKeyListener(new Canvas5.TAdapter());
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
        worldFloor[12][10] = new Sprite(Assets.FLOOR, false, 12, 10);       
        
        //CARGA DEL ARRAY CON LAS COORDENADAS DE LOS OBJETIVOS
        targets[0] = worldFloor[12][10];        
        
        //POSICIONAMIENTO DE LAS CAJAS EN EL CANVAS
        worldAssets[11][3] = new Sprite(Assets.BOX, 11, 3);
        
        // CARGA DEL ARRAY CON LAS COORDENADAS DE LAS CAJAS
        boxes[0] = worldAssets[11][3];
        
        // POSICIONAMIENTO DEL PLAYER Y CARGA DE LA VARIABLE playerPos CON SUS COORDENADAS, PARA MOVIMIENTO
        worldAssets[2][12] = new Sprite(Assets.PLAYER, 2, 12);
        playerPos = worldAssets[2][12];
        
        // CARGA INDIVIDUAL DE LAS PAREDES DEL NIVEL
        // Columna X=1
        worldAssets[1][7] = new Sprite(Assets.WALL, 1, 7);
        worldAssets[1][8] = new Sprite(Assets.WALL, 1, 8);
        worldAssets[1][9] = new Sprite(Assets.WALL, 1, 9);
        worldAssets[1][10] = new Sprite(Assets.WALL, 1, 10);
        worldAssets[1][11] = new Sprite(Assets.WALL, 1, 11);
        worldAssets[1][12] = new Sprite(Assets.WALL, 1, 12);
        worldAssets[1][13] = new Sprite(Assets.WALL, 1, 13);
        // Columna X=2
        worldAssets[2][7] = new Sprite(Assets.WALL, 2, 7);
        worldAssets[2][13] = new Sprite(Assets.WALL, 2, 13);
        // Columna X=3
        worldAssets[3][5] = new Sprite(Assets.WALL, 3, 5);
        worldAssets[3][6] = new Sprite(Assets.WALL, 3, 6);
        worldAssets[3][7] = new Sprite(Assets.WALL, 3, 7);
        worldAssets[3][9] = new Sprite(Assets.WALL, 3, 9);
        worldAssets[3][11] = new Sprite(Assets.WALL, 3, 11);
        worldAssets[3][13] = new Sprite(Assets.WALL, 3, 13);
        // Columna X=4
        worldAssets[4][5] = new Sprite(Assets.WALL, 4, 5);
        worldAssets[4][13] = new Sprite(Assets.WALL, 4, 13);
        // Columna X=5
        worldAssets[5][5] = new Sprite(Assets.WALL, 5, 5);
        worldAssets[5][8] = new Sprite(Assets.WALL, 5, 8);
        worldAssets[5][9] = new Sprite(Assets.WALL, 5, 9);
        worldAssets[5][11] = new Sprite(Assets.WALL, 5, 11);
        worldAssets[5][12] = new Sprite(Assets.WALL, 5, 12);
        worldAssets[5][13] = new Sprite(Assets.WALL, 5, 13);
        // Columna X=6
        worldAssets[6][3] = new Sprite(Assets.WALL, 6, 3);
        worldAssets[6][4] = new Sprite(Assets.WALL, 6, 4);
        worldAssets[6][5] = new Sprite(Assets.WALL, 6, 5);
        worldAssets[6][6] = new Sprite(Assets.WALL, 6, 6);
        worldAssets[6][8] = new Sprite(Assets.WALL, 6, 8);
        worldAssets[6][9] = new Sprite(Assets.WALL, 6, 9);
        worldAssets[6][11] = new Sprite(Assets.WALL, 6, 11);
        // Columna X=7
        worldAssets[7][2] = new Sprite(Assets.WALL, 7, 2);
        worldAssets[7][3] = new Sprite(Assets.WALL, 7, 3);
        worldAssets[7][6] = new Sprite(Assets.WALL, 7, 6);
        worldAssets[7][8] = new Sprite(Assets.WALL, 7, 8);
        worldAssets[7][9] = new Sprite(Assets.WALL, 7, 9);
        worldAssets[7][11] = new Sprite(Assets.WALL, 7, 11);
        // Columna X=8
        worldAssets[8][2] = new Sprite(Assets.WALL, 8, 2);
        worldAssets[8][9] = new Sprite(Assets.WALL, 8, 9);
        worldAssets[8][11] = new Sprite(Assets.WALL, 8, 11);
        // Columna X=9
        worldAssets[9][2] = new Sprite(Assets.WALL, 9, 2);
        worldAssets[9][5] = new Sprite(Assets.WALL, 9, 5);
        worldAssets[9][8] = new Sprite(Assets.WALL, 9, 8);
        worldAssets[9][9] = new Sprite(Assets.WALL, 9, 9);
        worldAssets[9][11] = new Sprite(Assets.WALL, 9, 11);
        // Columna X=10
        worldAssets[10][1] = new Sprite(Assets.WALL, 10, 1);
        worldAssets[10][2] = new Sprite(Assets.WALL, 10, 2);
        worldAssets[10][3] = new Sprite(Assets.WALL, 10, 3);
        worldAssets[10][5] = new Sprite(Assets.WALL, 10, 5);
        worldAssets[10][6] = new Sprite(Assets.WALL, 10, 6);
        worldAssets[10][7] = new Sprite(Assets.WALL, 10, 7);
        worldAssets[10][8] = new Sprite(Assets.WALL, 10, 8);
        worldAssets[10][9] = new Sprite(Assets.WALL, 10, 9);
        worldAssets[10][11] = new Sprite(Assets.WALL, 10, 11);
        // Columna X=11
        worldAssets[11][1] = new Sprite(Assets.WALL, 11, 1);
        worldAssets[11][5] = new Sprite(Assets.WALL, 11, 5);
        worldAssets[11][9] = new Sprite(Assets.WALL, 11, 9);
        worldAssets[11][11] = new Sprite(Assets.WALL, 11, 11);
        // Columna X=12
        worldAssets[12][1] = new Sprite(Assets.WALL, 12, 1);
        worldAssets[12][5] = new Sprite(Assets.WALL, 12, 5);
        worldAssets[12][9] = new Sprite(Assets.WALL, 12, 9);
        worldAssets[12][11] = new Sprite(Assets.WALL, 12, 11);
        // Columna X=11
        worldAssets[13][1] = new Sprite(Assets.WALL, 13, 1);
        worldAssets[13][2] = new Sprite(Assets.WALL, 13, 2);
        worldAssets[13][3] = new Sprite(Assets.WALL, 13, 3);
        worldAssets[13][4] = new Sprite(Assets.WALL, 13, 4);
        worldAssets[13][5] = new Sprite(Assets.WALL, 13, 5);
        worldAssets[13][9] = new Sprite(Assets.WALL, 13, 9);
        worldAssets[13][10] = new Sprite(Assets.WALL, 13, 10);
        worldAssets[13][11] = new Sprite(Assets.WALL, 13, 11);

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
                        }
                    
                    }                    
                    break;
            }
            playerInTarget();
            boxInTarget();

            //ESTE IF VALIDA SI LAS CAJAS ESTAN EN LOS OBJETIVOS PARA PASAR DE NIVEL
            if(check) 
            {
                System.out.println("HACIA EL SIGUIENTE NIVEL");
            }
            repaint();
        }
    }
    
    // METODO QUE RECIBE LAS COORDENADAS X Y Y DE LAS CAJAS PARA USARSE EN EL MAIN DE MOVIMIENTO
    private Sprite getBox(int x, int y){
        Sprite t = null;
        for (int i = 0; i < 1; i++ )
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

        for (int i= 0; i < 1; i++)
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

        check =  false;
        
        for (int i= 0; i < 1; i++){
            for (int j= 0; j < 1; j++)
            {
                if(targets[i].getX() == boxes[0].getX() && targets[i].getY() == boxes[0].getY())
                {
                    defaultSkin1 =  false;
                    check = true;
                } 
            }
        }
        boxes[0].setDefaultSkin(defaultSkin1);
    }      
}