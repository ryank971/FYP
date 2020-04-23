/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reversi;

import MainFolder.Board;
import java.util.ArrayList;
import java.util.List;
import MainFolder.Game;

/**
 *
 * @author Ryan Kelly
 */
public class Reversi implements Game {

    public Reversi() {

    }

    @Override
    public List validMoves(Board ReversiBoard) {
        List<String> validMoves = new ArrayList<String>();

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
        if (ReversiBoard.board[row][column] == 0) {
            return false;
        }
        return checkLineMatch(ReversiBoard, rowMove, columnMove, row + rowMove, column + columnMove);
    }

    @Override
    public Boolean isOver(Board ReversiBoard) {

        Boolean gameOver = true;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (ReversiBoard.board[r][c] == 0) {
                    gameOver = false;
                }

            }
        }

        if (validMoves(ReversiBoard).isEmpty()) {
            gameOver = true;
        }

        return gameOver;
    }

    @Override
    public int CheckWin(Board game) {
        int playerOne = 0;
        int playerTwo = 0;
        if (isOver(game) == true) {

            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if (game.board[r][c] == 1) {
                        playerOne++;
                    } else if (game.board[r][c] == 2) {
                        playerTwo++;
                    }

                }
            }

        }
        if (playerOne > playerTwo) {

            return 1;
        } else if (playerTwo > playerOne) {

            return 2;
        } else {
            return 0;
        }
    }

}
