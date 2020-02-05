
import java.util.Arrays;
import java.util.List;
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
        int counter = 0;
        
        
        
        
        while(EvaluateMove.GameOver(game.GetBoard(),game.turn) == false)
                {
                 
                    game.PrintBoard();

                        TreeNode Tree = new TreeNode(game.GetBoard());
                        BuildFromNode(game,1,Tree);
                        
                    if (game.turn == 1)
                    {
                        System.out.println("**************************************************************AI**************************************************************");
                        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allPieces(game.turn)));
                        //Tree.traverse(Tree);
                        MoveCalculator.minimax(Tree,1,true);
                        String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.move.data);
                        System.out.println(move);
                        String moves[]=move.split("-");
                        EvaluateMove.makeMove(game,moves[0] ,moves[1]);  
                    }
                    else
                    {
                        System.out.println("**************************************************************Random**************************************************************");
                        String moves[] = MoveCalculator.RandomMove(game, game.allPieces(game.turn)).split("-");
                        EvaluateMove.makeMove(game,moves[0] ,moves[1]);
                    }
                    
                      counter++;            
                    
               }
        System.out.println("_________WINNER_________");
        game.PrintBoard();
        System.out.println("Count: "+counter);
        

        

        
        
    }
    
    
    public static void BuildFromNode(Game game, int recursiveCalls,TreeNode Tree){
        //System.out.println("ITERATION NUMBER: "+recursiveCalls+ " GAME STATE=");
        //game.PrintBoard();
        recursiveCalls--;
            if(recursiveCalls>=0){
                int[][] copyBoard = cloneBoard(game.board);
                Game CopyGame = new Game(copyBoard,game.turn);
                List<String> validMoves = MoveCalculator.listOfMoves(CopyGame, CopyGame.allPieces(game.turn));
                for(String move : validMoves)
                {
                    int[][] temp = cloneBoard(CopyGame.board);
                    Game Temp = new Game(temp,game.turn);
                    String moves[] = move.split("-");
                    EvaluateMove.makeMove(Temp,moves[0] ,moves[1]);
                    BuildFromNode(Temp,recursiveCalls,Tree.addChild(Temp.GetBoard()));
                }
               
            }    
    }
    
 
    
    public static int[][] cloneBoard(int[][]board)
    {
     
                final int[][] result = new int[board.length][];
    for (int i = 0; i < board.length; i++) {
        result[i] = Arrays.copyOf(board[i], board[i].length);
    }
    return result;
    }
    
    
    public static void TestMinmax(TestTreeNode testTree){
        TestTreeNode Two = new TestTreeNode(2);
        testTree.addChild(Two);
        
        TestTreeNode three = new TestTreeNode(3);
        testTree.addChild(three);
        
        Two.addChild(6);
        Two.addChild(7);
        Two.addChild(4);
        three.addChild(5);
        three.addChild(9);
        three.addChild(10);
    }
    
    
}
