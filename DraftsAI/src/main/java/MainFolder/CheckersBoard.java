package MainFolder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelly
 */
import static MainFolder.DraftsMain.BuildFromNode;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;  
import java.applet.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class CheckersBoard {
    public static int rows = 8;
    public static int columns = 8;
    public static Color col1 = Color.BLACK;
    public static Color col2 = Color.WHITE;
    final static int DEPTH_TO_BUILD =6;
    static int  counter = 0;
    static JFrame checkerBoard = new JFrame();
        
   

public static void main(String[] args) throws InterruptedException {
    Game game = new Game();
    game.PrintBoard();
    System.out.println("");
    
    while(!EvaluateMove.GameOver(game)){
        CreateBoard(game);
        if (game.turn == 1)
            {
              TreeNode Tree = new TreeNode(game.GetBoard());
              BuildFromNode(game,DEPTH_TO_BUILD,Tree);  
              MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn);
              String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
              String moves[]=move.split("-");
              EvaluateMove.makeMove(game,moves[0] ,moves[1]); 
            }
        else
        {
//            TreeNode Tree = new TreeNode(game.GetBoard());
//            BuildFromNode(game,1,Tree);
//            MoveCalculator.minimax(Tree,1,false,1,game.turn);
//            String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MinMove.data);
//            String moves[]=move.split("-");
//            EvaluateMove.makeMove(game,moves[0] ,moves[1]);   
              String moves[] = MoveCalculator.RandomMove(game, game.allPieces(game.turn)).split("-");
              EvaluateMove.makeMove(game,moves[0] ,moves[1]);
        }
        counter++; 
        TimeUnit.SECONDS.sleep(1);
        UpdateBoard(game);
        game.PrintBoard();
        System.out.println("");
        //UpdateBoard(game);

    }
}  


public static void CreateBoard(Game game){
    checkerBoard.setSize(800,800);
    checkerBoard.setTitle("CheckerBoard");
}

public static void UpdateBoard(Game game){
    

    
    //checkerBoard.revalidate();
    //checkerBoard.repaint();

    
    
    Container pane = checkerBoard.getContentPane();
    pane.removeAll();
    pane.setLayout(new GridLayout(rows,columns));
    
    Color temp;
    for (int i = 0; i < rows; i++) {
        if(i%2 == 0)
            temp = col1;
        else
            temp = col2;
        for (int j = 0; j < columns; j++) {

                if(game.GetBoard()[i][j] == 1 ||game.GetBoard()[i][j] == 3)
                {   
                    GreyPiece circle = new GreyPiece();
                    circle.setBackground(temp);
                    pane.add(circle);
                }
                else if(game.GetBoard()[i][j] == 2 ||game.GetBoard()[i][j] == 4)
                {
                    RedPiece circle = new RedPiece();
                    circle.setBackground(temp);
                    pane.add(circle);
                }
                else
                {
                    JPanel panel = new JPanel();
                    panel.setBackground(temp);
                    pane.add(panel);
                }
            
                  
            if(temp == col1)
            temp = col2;
            else
            temp = col1;
            

        }
    }

    checkerBoard.setVisible(true);
}

public static void BuildFromNode(Game game, int recursiveCalls,TreeNode Tree){
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

public static int[][] cloneBoard(int[][]board){
        final int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }
}  

