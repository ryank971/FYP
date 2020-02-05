/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelly
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;  
public class CheckersBoard extends JPanel{


//JFrame f;  
CheckersBoard(){  
//JButton b=new JButton("click");//create button  
//b.setBounds(130,100,100, 40);  
//
//
//add(b);//adding button on frame  
//setSize(700,700);  
//setLayout(null);  
//setVisible(true);  
}  
    
    
    public void paint(Graphics g){
        g.fillRect(100, 100, 400, 400);
        //g.clearRect(100, 100, 50, 50);
        for (int i = 100; i <= 400; i+=100) {
            for (int j = 100; j <= 400; j+=100) {
               g.clearRect(i, j, 50, 50);
            }

        }
        for (int i = 150; i <= 450; i+=100) {
            for (int j = 150; j <= 450; j+=100) {
                 g.clearRect(i, j, 50, 50);
            }
            
        }

    }

    
    
    




public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(600,600);
    frame.getContentPane().add(new CheckersBoard());
    frame.setLocationRelativeTo(null);
    frame.setBackground(Color.LIGHT_GRAY);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}  


}  

