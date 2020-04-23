/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Checkers.CheckersHuman;
import Connect4.Connect4Human;
import static GUI.BoardPanel.checkers;
import static GUI.GameFrame.*;
import Reversi.ReversiHuman;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;


/**
 *
 * @author Ryan Kelly
 */
public class TilePanel extends JLabel implements MouseListener {

    CheckersHuman playerCheckers = new CheckersHuman();
    ReversiHuman playerReversi = new ReversiHuman();
    Connect4Human playerConnectFour = new Connect4Human();
    static int[] Move;
    static int[] Current;
    int Row;
    int Column;

    public TilePanel(int row, int column) {
        Row = row;
        Column = column;
        addMouseListener(this);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        if (menu.PlayCheckers) {
            if (Board.turn == 2 && PlayerTwo.equals("Human")) {

                if (Current != null && !(AvailableMoves.isEmpty())) {
                    Move = new int[]{Row, Column};
                    List<String> validMoves = checkers.validMoves(Board);
                    for (String move : validMoves) {
                        if (move.equals(Current[0] + "," + Current[1] + "-" + Move[0] + "," + Move[1])) {
                            playerCheckers.makeMove(Board, Current[0] + "," + Current[1], Move[0] + "," + Move[1]);
                            addMovetoLogMoves(playerCheckers.BestMove);
                        }
                    }

                }

                String move = this.Row + "," + this.Column;
                Current = new int[]{this.Row, this.Column};
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        boardPanel.updateBoardPanel(Board, move);
                        boardPanel.revalidate();
                    }
                });

            } else if (Board.turn == 1 && PlayerOne.equals("Human")) {
                if (Current != null && !(AvailableMoves.isEmpty())) {
                    Move = new int[]{Row, Column};
                    List<String> validMoves = checkers.validMoves(Board);
                    for (String move : validMoves) {
                        if (move.equals(Current[0] + "," + Current[1] + "-" + Move[0] + "," + Move[1])) {
                            playerCheckers.makeMove(Board, Current[0] + "," + Current[1], Move[0] + "," + Move[1]);
                            addMovetoLogMoves(playerCheckers.BestMove);
                        }
                    }

                }

                String move = this.Row + "," + this.Column;
                Current = new int[]{this.Row, this.Column};
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        boardPanel.updateBoardPanel(Board, move);
                        boardPanel.revalidate();
                    }
                });
            }

        } else if (menu.PlayReversi) {
            if (Board.turn == 2 && PlayerTwo.equals("Human")) {
                Move = new int[]{Row, Column};
                List<String> validMoves = reversi.validMoves(Board);
                for (String move : validMoves) {
                    if (move.equals(Move[0] + "," + Move[1])) {
                        System.out.println(Board.turn + " made move = " + Move);
                        playerReversi.makeMove(Board, Move[0] + "," + Move[1]);
                        addMovetoLogMoves(playerReversi.BestMove);
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        boardPanel.updateBoardPanel(Board);
                        boardPanel.revalidate();
                    }
                });
            } else if (Board.turn == 1 && PlayerOne.equals("Human")) {
                Move = new int[]{Row, Column};
                List<String> validMoves = reversi.validMoves(Board);
                for (String move : validMoves) {
                    if (move.equals(Move[0] + "," + Move[1])) {
                        System.out.println(Board.turn + " made move = " + Move);
                        playerReversi.makeMove(Board, Move[0] + "," + Move[1]);
                        addMovetoLogMoves(playerReversi.BestMove);
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        boardPanel.updateBoardPanel(Board);
                        boardPanel.revalidate();
                    }
                });
            }
        } else if (menu.PlayConnect4) {
            if (Board.turn == 2 && PlayerTwo.equals("Human")) {
                Move = new int[]{Row, Column};
                List<String> validMoves = connect4.validMoves(Board);
                for (String move : validMoves) {

                    if (move.equals(Move[0] + "," + Move[1])) {
                        System.out.println(Board.turn + " made move = " + Move);
                        playerConnectFour.makeMove(Board, Move[0] + "," + Move[1]);
                        addMovetoLogMoves(playerConnectFour.BestMove);
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        boardPanel.updateBoardPanel(Board);
                        boardPanel.revalidate();
                    }
                });
            } else if (Board.turn == 1 && PlayerOne.equals("Human")) {
                Move = new int[]{Row, Column};
                List<String> validMoves = connect4.validMoves(Board);
                for (String move : validMoves) {

                    if (move.equals(Move[0] + "," + Move[1])) {
                        System.out.println(Board.turn + " made move = " + Move);
                        playerConnectFour.makeMove(Board, Move[0] + "," + Move[1]);
                        addMovetoLogMoves(playerConnectFour.BestMove);
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        boardPanel.updateBoardPanel(Board);
                        boardPanel.revalidate();
                    }
                });
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0
    ) {

    }

    @Override
    public void mouseExited(MouseEvent arg0
    ) {

    }

}
