/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder;

import Checkers.Checkers;
import Connect4.Connect4;
import Reversi.Reversi;

/**
 *
 * @author Ryan Kelly
 */
public class GameFactory {

    public GameFactory() {
    }
    public Game getGame(String game){
        if(game == null){
        return null;
        }
        if(game == "Checkers"){
            return new Checkers();
        }
        else if(game =="Connect4"){
            return new Connect4();
        }
        else if(game =="Reversi"){
            return new Reversi();
        }
        return null;
    }
}
