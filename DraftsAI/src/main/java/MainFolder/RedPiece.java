/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Ryan Kelly
 */
public class RedPiece extends JPanel{
        public void paint(Graphics g){
        super.paintComponent(g);
        setOpaque(true);
                g.setColor(Color.red);
        g.fillOval(15,15,70,70);

        }
}
