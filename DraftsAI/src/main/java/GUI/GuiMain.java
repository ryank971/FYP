/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Ryan Kelly
 */
public class GuiMain extends Observable  {
    public GuiMain() {
        GameFrame game = new GameFrame();
    }


}
