/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder.Reversi;

import MainFolder.Board;
import static MainFolder.Checkers.Checkers.isValidMove;
import MainFolder.IGame;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Kelly
 */
public class Reversi implements IGame {

    public Reversi() {

    }

    @Override
    public List validMoves(Board ReversiBoard) {
        List<String> validMoves = new ArrayList<String>();
        List<String> list = ReversiBoard.allGamePieces(ReversiBoard.turn);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (ReversiBoard.board[row][col] == 0) {
                    boolean nw = validMove(ReversiBoard, -1, -1, row, col);
                    boolean nn = validMove(ReversiBoard, -1, 0, row, col);
                    boolean ne = validMove(ReversiBoard, -1, 1, row, col);

                    boolean ww = validMove(ReversiBoard, 0, -1, row, col);
                    boolean ee = validMove(ReversiBoard, 0, 1, row, col);

                    boolean sw = validMove(ReversiBoard, 1, -1, row, col);
                    boolean ss = validMove(ReversiBoard, 1, 0, row, col);
                    boolean se = validMove(ReversiBoard, 1, 1, row, col);

                    if (nw || nn || ne || ww || ee || sw || ss || se) {
                        validMoves.add(row + "," + col);
                    }
                }
            }
        }

        return validMoves;
    }

    public Boolean validMove(Board ReversiBoard, int rowMove, int columnMove, int row, int column) {
        int other;
        if (ReversiBoard.turn == 1) {
            other = 2;
        } else if (ReversiBoard.turn == 2) {
            other = 1;
        } else {
            return false;
        }
        if ((row + rowMove) < 0 || (row + rowMove > 7)) {
            return false;
        }

        if ((column + columnMove < 0) || (column + columnMove > 7)) {
            return false;
        }
        if (ReversiBoard.board[row + rowMove][column + columnMove] != other) {
            return false;
        }

        if ((row + rowMove + rowMove) < 0 || (row + rowMove + rowMove > 7)) {
            return false;
        }

        if ((column + columnMove + columnMove < 0) || (column + columnMove + columnMove > 7)) {
            return false;
        }
        return checkLineMatch(ReversiBoard, rowMove, columnMove, row + rowMove + rowMove, column + columnMove + columnMove);

    }

    public Boolean checkLineMatch(Board ReversiBoard, int rowMove, int columnMove, int row, int column) {

        if (ReversiBoard.board[row][column] == ReversiBoard.turn) {
            return true;
        }
        if ((row + rowMove) < 0 || (row + rowMove > 7)) {
            return false;
        }

        if ((column + columnMove < 0) || (column + columnMove > 7)) {
            return false;
        }
        return checkLineMatch(ReversiBoard, rowMove, columnMove, row + rowMove, column + columnMove);
    }

    @Override
    public Boolean isOver(Board ReversiBoard) {
        Boolean gameOver = true;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (ReversiBoard.board[r][c] ==0) {
                    gameOver = false;
                }

            }
        }
        return gameOver;
    }

        @Override
        public List validMovesFromPiece
        (Board ReversiBoard, String postion
        
            ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
