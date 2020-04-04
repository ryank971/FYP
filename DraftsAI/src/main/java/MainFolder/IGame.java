/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Kelly
 */
public interface IGame {
    
    public abstract List validMoves(Board CheckersBoard);
    
    public abstract Boolean isOver(Board CheckersBoard);
    
    public  abstract List validMovesFromPiece(Board game,String postion);

}
