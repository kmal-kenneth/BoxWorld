/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplesnake;

import java.awt.EventQueue;
import javax.swing.JFrame;


/**
 *
 * @author GOLLO
 */
public class SimpleSnake extends JFrame {

    public SimpleSnake() {

        this.add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Simple Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new SimpleSnake();
                ex.setVisible(true);                
            }
        });
    }
    
}
