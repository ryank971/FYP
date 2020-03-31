package MainFolder;


import MainFolder.EvaluateMove;
import MainFolder.Board;
import MainFolder.MoveCalculator;
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
    
    final static int DEPTH_TO_BUILD = 6;
    /**2,1
     * 5,
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
//      RunSimulation();
        
//                Game game = new Game();
//                TreeNode Tree = new TreeNode(game.GetBoard());
//                BuildFromNode(game,DEPTH_TO_BUILD,Tree); 
//                MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true);
//                String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.move.data);
//                System.out.println(move);
//                MoveCalculator.move.displayNodeData(MoveCalculator.move);

//                for(TreeNode node : MoveCalculator.moves){
//                    node.displayNodeData(node);
//                    System.out.println("");
//                }
        TestTreeNode Tree = new TestTreeNode(1);
        TestMinmax(Tree);
        System.out.println(TestMinimax.TestAlphaBeta(Tree, 3,true,-9999999,9999999));
        //Tree.traverse(Tree);
        //System.out.println("best move : "+TestMinimax.best);
////        for(int move : TestMinimax.moves)
////        {
////            System.out.println(move);
////        }
//        
        //System.out.println("best move = "+TestMinimax.best);
       
        
        
    }
    
    
    public static void RunSimulation(){
                // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Board game = new Board();
        int counter = 0;
        
        
        
        
        while(!EvaluateMove.GameOver(game))
                {

                        
                    if (game.turn == 1)
                    {
                        System.out.println("**************************************************************AI**************************************************************");
                        
                    
                        TreeNode Tree = new TreeNode(game.GetBoard());
                        BuildFromNode(game,DEPTH_TO_BUILD,Tree);

                        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)));
                        MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn);
                       
                        String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
                        System.out.println("move choosen: "+move);
                        String moves[]=move.split("-");
                        EvaluateMove.makeMove(game,moves[0] ,moves[1]); 
                        game.PrintGame();
                    }
                    else
                    {
                        
//                        System.out.println("**************************************************************Random**************************************************************");
//                        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allPieces(game.turn)));
//                        String moves[] = MoveCalculator.RandomMove(game, game.allPieces(game.turn)).split("-");
//                        EvaluateMove.makeMove(game,moves[0] ,moves[1]);
                          TreeNode Tree = new TreeNode(game.GetBoard());
                          BuildFromNode(game,1,Tree);
                          MoveCalculator.minimax(Tree,1,false,1,game.turn);
                          String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MinMove.data);
                          String moves[]=move.split("-");
                          EvaluateMove.makeMove(game,moves[0] ,moves[1]);
//                        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allPieces(game.turn)));
//                        game.PrintBoard();
//                        System.out.print("From: ");
//                          String current = in.nextLine();
//                          System.out.print("Too: ");
//                          String move = in.nextLine();
//                  
//                          EvaluateMove.makeMove(game,current ,move);
                    }
                    
                      counter++;            
                    
               }
        System.out.println("_________WINNER_________");
        game.PrintGame();
        System.out.println("Count: "+counter);
    }
    
    
    public static void BuildFromNode(Board game, int recursiveCalls,TreeNode Tree){
        //System.out.println("ITERATION NUMBER: "+recursiveCalls+ " GAME STATE=");
        //game.PrintBoard();
        recursiveCalls--;
            if(recursiveCalls>=0){
                int[][] copyBoard = cloneBoard(game.board);
                Board CopyGame = new Board(copyBoard,game.turn);
                List<String> validMoves = MoveCalculator.listOfMoves(CopyGame, CopyGame.allGamePieces(game.turn));
                for(String move : validMoves)
                {
                    int[][] temp = cloneBoard(CopyGame.board);
                    Board Temp = new Board(temp,game.turn);
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
        TestTreeNode Two = new TestTreeNode(200);
        testTree.addChild(Two);
        
        TestTreeNode three = new TestTreeNode(300);
        testTree.addChild(three);
        
        TestTreeNode six = new TestTreeNode(600);
        Two.addChild(six);
        TestTreeNode seven = new TestTreeNode(700);
        Two.addChild(seven);
        
        
        TestTreeNode five = new TestTreeNode(500);
        three.addChild(five);
        TestTreeNode nine = new TestTreeNode(900);
        three.addChild(nine);
        
        six.addChild(3);
        six.addChild(5);
        //six.addChild(15);
       // Two.addChild(4);
        seven.addChild(6);
        seven.addChild(9);
        //seven.addChild(34);
        //three.addChild(10);
        
        
        five.addChild(1);
        five.addChild(2);
        //five.addChild(35);
        
        
        nine.addChild(0);
        nine.addChild(-1);
       // nine.addChild(78);
    }
    
    
}


