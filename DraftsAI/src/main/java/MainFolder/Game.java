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
public interface Game {
    
    public abstract List validMoves(Board board);
    
    public abstract Boolean isOver(Board board);
    
    
    public abstract int CheckWin(Board board);

}
