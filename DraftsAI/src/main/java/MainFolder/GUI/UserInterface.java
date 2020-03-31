/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder.GUI;

import MainFolder.AlphaBetaAgent;
import MainFolder.Board;
import MainFolder.Checkers;
import MainFolder.GUI.CheckersBoard;
import MainFolder.GUI.GreyPiece;
import MainFolder.GUI.RedPiece;
import MainFolder.MinimaxAgent;
import MainFolder.RandomAgent;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ryan Kelly
 */
public class UserInterface extends JFrame implements ActionListener, MouseListener {

    public static int rows = 8;
    public static int columns = 8;
    public static Color col1 = Color.BLACK;
    public static Color col2 = Color.WHITE;
    final static int DEPTH_TO_BUILD = 6;
    static int counter = 0;
    static int[] Current;
    static List<String> AvailableMoves = new ArrayList();
    static int[] Move;
    static RandomAgent randomAgent = new RandomAgent();
    static MinimaxAgent minimaxAgent = new MinimaxAgent();
    static AlphaBetaAgent alphaBetaAgent = new AlphaBetaAgent();
    static Board checkerBoard = new Board();
    static Checkers checkers = new Checkers();
    static UserInterface Gui = new UserInterface();

    public static void main(String[] args) throws InterruptedException {

        //ShowOption();
        RandomVHuman();

    }

    public static void RandomVRandom() throws InterruptedException {

        while (!checkers.isOver(checkerBoard)) {
            Gui.CreateBoard(checkerBoard);
            if (checkerBoard.turn == 1) {
                randomAgent.makeMove(checkerBoard);
                Thread.sleep(1000);
            } else {
                randomAgent.makeMove(checkerBoard);
                Thread.sleep(1000);

            }
            counter++;
            Gui.UpdateBoard(checkerBoard);
        }
    }

    public static void AlphaBetaVHuman() throws InterruptedException {

        while (!checkers.isOver(checkerBoard)) {
            Gui.CreateBoard(checkerBoard);
            if (checkerBoard.turn == 1) {
                alphaBetaAgent.makeMove(checkerBoard);
                Thread.sleep(1000);
            } else {
                while (checkerBoard.turn == 2) {
                    Thread.sleep(1000);
                }

            }
            counter++;
            Gui.UpdateBoard(checkerBoard);
        }
    }
    
        public static void RandomVHuman() throws InterruptedException {

        while (!checkers.isOver(checkerBoard)) {
            Gui.CreateBoard(checkerBoard);
            if (checkerBoard.turn == 1) {
                randomAgent.makeMove(checkerBoard);
                Thread.sleep(1000);
            } else {
                while (checkerBoard.turn == 2) {
                    Thread.sleep(1000);
                }

            }
            counter++;
            Gui.UpdateBoard(checkerBoard);
        }
    }

    public static void MinimaxVRandom() throws InterruptedException {

        while (!checkers.isOver(checkerBoard)) {
            Gui.CreateBoard(checkerBoard);
            if (checkerBoard.turn == 1) {
                minimaxAgent.makeMove(checkerBoard);
                Thread.sleep(2000);
            } else {
                randomAgent.makeMove(checkerBoard);
                Thread.sleep(1000);

            }
            counter++;
            Gui.UpdateBoard(checkerBoard);
        }
    }

    public static void alphaBetaVRandom() throws InterruptedException {

        while (!checkers.isOver(checkerBoard)) {
            Gui.CreateBoard(checkerBoard);
            if (checkerBoard.turn == 1) {
                alphaBetaAgent.makeMove(checkerBoard);
                Thread.sleep(2000);
            } else {
                randomAgent.makeMove(checkerBoard);
                Thread.sleep(1000);

            }
            counter++;
            Gui.UpdateBoard(checkerBoard);
        }
    }

    public void CreateBoard(Board game) {
        setSize(800, 800);
        setTitle("CheckerBoard");
        addMouseListener(this);
    }

    public void UpdateBoard(Board game) {

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
                        boolean isAvailableMove = false;
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
                        if (isAvailableMove == true) {
                            JPanel panel = new JPanel();
                            panel.setBackground(Color.GREEN);
                            pane.add(panel);
                        } else {
                            JPanel panel = new JPanel();
                            panel.setBackground(temp);
                            pane.add(panel);
                        }

                    } else {
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (checkerBoard.turn == 2) {
            int x = e.getX();
            int y = e.getY();
            if (Current != null && !(AvailableMoves.isEmpty())) {
                Move = new int[]{y / (getContentPane().getSize().width / 8), x / (getContentPane().getSize().height / 8)};
                makeMove(checkerBoard, Current[0] + "," + Current[1], Move[0] + "," + Move[1]);
            }

            String move = y / (getContentPane().getSize().width / 8) + "," + x / (getContentPane().getSize().height / 8);
            Current = new int[]{y / (getContentPane().getSize().width / 8), x / (getContentPane().getSize().height / 8)};
            AvailableMoves = checkers.validMovesFromPiece(checkerBoard, move);
            UpdateBoard(checkerBoard);
        }
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
    
    public static void makeMove(Board game, String current, String move) {
        String[] CurrentS = current.split(",");
        String[] MoveS = move.split(",");

        int size = CurrentS.length;
        int[] Current = new int[size];
        int[] Move = new int[size];
        for (int i = 0; i < size; i++) {
            Current[i] = Integer.parseInt(CurrentS[i]);
            Move[i] = Integer.parseInt(MoveS[i]);

        }
        
        
        
        if (checkers.isValidMove(game, Current, Move) == true) {
            if(checkers.isDoubleJump(game, Current, Move)){  
                int turn = game.turn;
            int[] middleMove = checkers.ReturnDoubleJump(game, Current, Move);
            minimaxAgent.SimulatePlay(game, Current[0] + "," + Current[1], middleMove[0] + "," + middleMove[1]);
            game.turn = turn;
            minimaxAgent.SimulatePlay(game, middleMove[0] + "," + middleMove[1], Move[0] + "," + Move[1]);
                
            }
            else{
            
                if (Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2) {
                    game.board[Current[0] + 1][Current[1] + 1] = 0;
                }
                else if (Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2) {
                   game.board[Current[0] + 1][Current[1] - 1] = 0;
                }
                else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2) {
                    game.board[Current[0] - 1][Current[1] + 1] = 0;
                }
                else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2) {
                    game.board[Current[0] - 1][Current[1] - 1] = 0;
                }



                if(game.turn == 1)
                {    
                    if(Move[0]== 7)
                    {
                    game.board[Move[0]][Move[1]] = game.turn+2;
                    game.board[Current[0]][Current[1]] = 0;
                    }
                    else
                    {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                    }
                }
                else if(game.turn == 2)
                {
                    if(Move[0] == 0){
                    game.board[Move[0]][Move[1]] = game.turn+2;
                    game.board[Current[0]][Current[1]] = 0;
                    }
                    else
                    {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                    }

                }




                if(game.turn==1) {
                    game.turn = 2;
                } else {
                    game.turn = 1;
                }
            }
        }

    }

}
