/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxworld;

import boxworld.presentation.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author oscar
 */
public class BoxWorld extends JFrame {
    
    public BoxWorld(){       
        this.add(new Canvas5());
        setResizable(false);
        pack();
        setTitle("|_| THOR BOX WORLD |_|");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame ex = new BoxWorld();
                ex.setVisible(true);
            }
        });
    }
}    
