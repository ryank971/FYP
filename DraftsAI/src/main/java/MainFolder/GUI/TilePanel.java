/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder.GUI;

import static MainFolder.GUI.CheckersView.AvailableMoves;
import static MainFolder.GUI.CheckersView.Board;
import static MainFolder.GUI.CheckersView.boardPanel;
import static MainFolder.GUI.CheckersView.makeMove;
import static MainFolder.GUI.CheckersView.menu;
import static MainFolder.GUI.CheckersView.reversi;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan Kelly
 */
public class TilePanel extends JLabel implements MouseListener {

    static int[] Move;
    static int[] Current;
    int Row;
    int Column;

    public TilePanel(int row, int column) {
        Row = row;
        Column = column;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

        System.out.println("Tile " + Row + "," + Column + " was clicked");
        if (menu.PlayCheckers) {
            if (Board.turn == 2) {

                if (Current != null && !(AvailableMoves.isEmpty())) {
                    Move = new int[]{Row, Column};
                    makeMove(Board, Current[0] + "," + Current[1], Move[0] + "," + Move[1]);
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
        } else {
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
