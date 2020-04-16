/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Checkers.HumanPlayer;
import static GUI.GameFrame.AvailableMoves;
import static GUI.GameFrame.Board;
import static GUI.GameFrame.CheckWin;
import static GUI.GameFrame.PlayerOne;
import static GUI.GameFrame.PlayerTwo;
import static GUI.GameFrame.boardPanel;
import static GUI.GameFrame.connect4;
import static GUI.GameFrame.makeMove;
import static GUI.GameFrame.menu;
import static GUI.GameFrame.reversi;
import static GUI.Menu.minimaxAgent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan Kelly
 */
public class TilePanel extends JLabel implements MouseListener {

    HumanPlayer player = new HumanPlayer();
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

        if (menu.PlayCheckers) {
            if (Board.turn == 2 && PlayerTwo.equals("Human")) {

                if (Current != null && !(AvailableMoves.isEmpty())) {
                    Move = new int[]{Row, Column};
                    player.makeMove(Board, Current[0] + "," + Current[1], Move[0] + "," + Move[1]);

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
                    player.makeMove(Board, Current[0] + "," + Current[1], Move[0] + "," + Move[1]);

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
            Move = new int[]{Row, Column};
            List<String> validMoves = reversi.validMoves(Board);
            for (String move : validMoves) {

                if (move.equals(Move[0] + "," + Move[1])) {
                    System.out.println(Board.turn + " made move = " + Move);
                    makeMove(Board, Move[0] + "," + Move[1]);
                }
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    boardPanel.updateBoardPanel(Board);
                    boardPanel.revalidate();
                }
            });
        } else {

            Move = new int[]{Row, Column};
            List<String> validMoves = connect4.validMoves(Board);
            for (String move : validMoves) {

                if (move.equals(Move[0] + "," + Move[1])) {
                    System.out.println(Board.turn + " made move = " + Move);
                    makeMove(Board, Move[0] + "," + Move[1]);
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

    @Override
    public void mousePressed(MouseEvent arg0) {

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

}
