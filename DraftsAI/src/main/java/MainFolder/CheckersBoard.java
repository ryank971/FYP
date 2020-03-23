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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckersBoard extends JFrame implements ActionListener, MouseListener {

    public static int rows = 8;
    public static int columns = 8;
    public static Color col1 = Color.BLACK;
    public static Color col2 = Color.WHITE;
    final static int DEPTH_TO_BUILD = 6;
    static int counter = 0;
    static Game game = new Game();
    static int[] Current;
    static List<String> AvailableMoves = new ArrayList();
    static int[] Move;

    public static void main(String[] args) throws InterruptedException {

        StartGame();

//   CheckersBoard board = new CheckersBoard();
//   board.SimulatePlay();

    }
    public static void StartGame() throws InterruptedException {

        CheckersBoard board = new CheckersBoard();
        while (!EvaluateMove.GameOver(game)) {
            board.CreateBoard(game);
            if (game.turn == 1) {
//              TreeNode Tree = new TreeNode(game.GetBoard());
//              BuildFromNode(game,DEPTH_TO_BUILD,Tree);  
//              MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn);
//              String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
//              String moves[]=move.split("-");
//              EvaluateMove.makeMove(game,moves[0] ,moves[1]); 

                MoveCalculator.minimaxTreeGenerationIntegrated(game, 7, true, 7, game.turn, -9999999, 9999999);
                String moves[] = MoveCalculator.MaxMoveString.split("-");
                EvaluateMove.makeMove(game, moves[0], moves[1]);
            } else {
                while(game.turn==2)
                {
                    Thread.sleep(1000);
                }
//            TreeNode Tree = new TreeNode(game.GetBoard());
//            BuildFromNode(game,6,Tree);
//            MoveCalculator.minimax(Tree,6,false,6,game.turn);
//            String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MinMove.data);
//            String moves[]=move.split("-");
//            EvaluateMove.makeMove(game,moves[0] ,moves[1]);   
//                String moves[] = MoveCalculator.RandomMove(game, game.allPieces(game.turn)).split("-");
//                EvaluateMove.makeMove(game, moves[0], moves[1]);
//            TreeNode Tree = new TreeNode(game.GetBoard());
//            BuildFromNode(game,DEPTH_TO_BUILD,Tree);  
//            MoveCalculator.alphaBeta(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn,Double.MAX_VALUE,Double.MIN_VALUE);
//            //MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn);
//            String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
//            String moves[]=move.split("-");
//            EvaluateMove.makeMove(game,moves[0] ,moves[1]); 
            }
            counter++;
            board.UpdateBoard(game);
            game.PrintBoard();
            System.out.println("");
            //UpdateBoard(game);

        }
    }

    public void CreateBoard(Game game) {
        setSize(800, 800);
        setTitle("CheckerBoard");
        addMouseListener(this);
    }

    public void UpdateBoard(Game game) {

        Container pane = getContentPane();
        pane.removeAll();
        pane.setLayout(new GridLayout(rows, columns));

        Color temp;
        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                temp = col1;
            } else {
                temp = col2;
            }
            outerloop:
            for (int j = 0; j < columns; j++) {

                if (game.GetBoard()[i][j] == 1 || game.GetBoard()[i][j] == 3) {
                    GreyPiece circle = new GreyPiece();
                    circle.setBackground(temp);
                    pane.add(circle);
                } else if (game.GetBoard()[i][j] == 2 || game.GetBoard()[i][j] == 4) {
                    RedPiece circle = new RedPiece();
                    circle.setBackground(temp);
                    pane.add(circle);
                } else {

                    if (!AvailableMoves.isEmpty()) {
                        boolean isAvailableMove= false;
                        for (int l = 0; l < AvailableMoves.size(); l++) {
                            String[] tempM = AvailableMoves.get(l).split("-");
                            String[] CurrentS = tempM[1].split(",");
                            int size = CurrentS.length;
                            int[] Current = new int[size];
                            for (int a = 0; a < size; a++) {
                                Current[a] = Integer.parseInt(CurrentS[a]);
                            }

                            if (i == Current[0] && j == Current[1]) {
                                isAvailableMove = true;
                                
                            }
                        }
                        if(isAvailableMove == true){
                        JPanel panel = new JPanel();
                        panel.setBackground(Color.GREEN);
                        pane.add(panel);
                        }
                        else
                        {
                        JPanel panel = new JPanel();
                        panel.setBackground(temp);
                        pane.add(panel);
                        }



                    }else{
                        JPanel panel = new JPanel();
                        panel.setBackground(temp);
                        pane.add(panel);
                    }

                }

                if (temp == col1) {
                    temp = col2;
                } else {
                    temp = col1;
                }

            }
        }

        setVisible(true);
    }

    public static void BuildFromNode(Game game, int recursiveCalls, TreeNode Tree) {
        recursiveCalls--;
        if (recursiveCalls >= 0) {
            int[][] copyBoard = cloneBoard(game.board);
            Game CopyGame = new Game(copyBoard, game.turn);
            List<String> validMoves = MoveCalculator.listOfMoves(CopyGame, CopyGame.allPieces(game.turn));
            for (String move : validMoves) {
                int[][] temp = cloneBoard(CopyGame.board);
                Game Temp = new Game(temp, game.turn);
                String moves[] = move.split("-");
                EvaluateMove.makeMove(Temp, moves[0], moves[1]);
                BuildFromNode(Temp, recursiveCalls, Tree.addChild(Temp.GetBoard()));
            }

        }
    }

    public static int[][] cloneBoard(int[][] board) {
        final int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    public void SimulatePlay() throws InterruptedException {
        Game game = new Game();
        game.PrintBoard();
        System.out.println("");

        while (!EvaluateMove.GameOver(game)) {
            CreateBoard(game);
            if (game.turn == 1) {
//              TreeNode Tree = new TreeNode(game.GetBoard());
//              BuildFromNode(game,DEPTH_TO_BUILD,Tree);  
//              MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn);
//              String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
//              String moves[]=move.split("-");
//              EvaluateMove.makeMove(game,moves[0] ,moves[1]); 

                MoveCalculator.minimaxTreeGenerationIntegrated(game, 7, true, 7, game.turn, -9999999, 9999999);
                String moves[] = MoveCalculator.MaxMoveString.split("-");
                EvaluateMove.makeMove(game, moves[0], moves[1]);
            } else {
//            TreeNode Tree = new TreeNode(game.GetBoard());
//            BuildFromNode(game,6,Tree);
//            MoveCalculator.minimax(Tree,6,false,6,game.turn);
//            String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MinMove.data);
//            String moves[]=move.split("-");
//            EvaluateMove.makeMove(game,moves[0] ,moves[1]);   
                String moves[] = MoveCalculator.RandomMove(game, game.allPieces(game.turn)).split("-");
                EvaluateMove.makeMove(game, moves[0], moves[1]);
//            TreeNode Tree = new TreeNode(game.GetBoard());
//            BuildFromNode(game,DEPTH_TO_BUILD,Tree);  
//            MoveCalculator.alphaBeta(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn,Double.MAX_VALUE,Double.MIN_VALUE);
//            //MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,game.turn);
//            String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
//            String moves[]=move.split("-");
//            EvaluateMove.makeMove(game,moves[0] ,moves[1]); 
            }
            counter++;
            TimeUnit.SECONDS.sleep(1);
            UpdateBoard(game);
            game.PrintBoard();
            System.out.println("");
            //UpdateBoard(game);

        }
    }
    


    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.turn == 2)
        {    
        int x = e.getX();
        int y = e.getY();
        if (Current!= null && !(AvailableMoves.isEmpty()))
        {
          Move = new int[]{y / (getContentPane().getSize().width / 8), x / (getContentPane().getSize().height / 8)};
            System.out.println("Making Move!!");
            EvaluateMove.makeMove(game,Current[0]+","+Current[1],Move[0]+","+Move[1]); 
        }

        String move = y / (getContentPane().getSize().width / 8) + "," + x / (getContentPane().getSize().height / 8);
        System.out.println("You clicked on square {" + y / (getContentPane().getSize().width / 8) + "," + x / (getContentPane().getSize().height / 8) + "]");
        System.out.println("Possible moves : " + MoveCalculator.listOfMovesFromPosition(game, game.allPieces(game.turn), move));
        Current = new int[]{y / (getContentPane().getSize().width / 8), x / (getContentPane().getSize().height / 8)};
        AvailableMoves = MoveCalculator.listOfMovesFromPosition(game, game.allPieces(game.turn), move);
        UpdateBoard(game);
        }
        else
            System.out.println("Not Youre Turn");
        

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

}
