
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelly
 */
public class DraftsMain {

    /**2,1
     * 5,
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        while(EvaluateMove.GameOver(game.GetBoard(),game.turn) == false)
                {
                    System.out.println("*****************************************************************");
                    game.PrintBoard();
                    //System.out.println("player: "+game.turn+" make move: ");
                    System.out.println(MoveCalculator.listOfMoves(game.GetBoard(), game.allPieces(game.turn)));
                    System.out.println("Move Choosen " + MoveCalculator.RandomMove(game.GetBoard(), game.allPieces(game.turn)));
                    String moves[] = MoveCalculator.RandomMove(game.GetBoard(), game.allPieces(game.turn)).split("-");
//                    System.out.print("MOVE: ");
//                    String current = in.nextLine();
//                    System.out.print("Too: ");
//                    String move = in.nextLine();
//
                    EvaluateMove.makeMove(game.GetBoard(),moves[0] ,moves[1]);
//                    EvaluateMove.makeMove(game.GetBoard(),current ,move);


                    
                }
                  //System.out.println(game.allPieces(1));
                  //
        

        

        
        
    }
    
}
