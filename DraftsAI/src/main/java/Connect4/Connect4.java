/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect4;

import MainFolder.Board;
import java.util.ArrayList;
import java.util.List;
import MainFolder.Game;

/**
 *
 * @author Ryan Kelly
 */
public class Connect4 implements Game {

    @Override
    public List validMoves(Board connect4Board) {

        List<String> validMoves = new ArrayList<String>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row == 7 && connect4Board.board[row][col] == 0) {
                    validMoves.add(row + "," + col);
                } else if (row != 7) {
                    if (connect4Board.board[row][col] == 0 && connect4Board.board[row + 1][col] != 0) {
                        validMoves.add(row + "," + col);
                    }
                }
            }
        }

        return validMoves;
    }

    @Override
    public Boolean isOver(Board connect4Board) {

        Boolean gameOver = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (connect4Board.board[row][col] == 0) {
                    gameOver = false;
                }
            }
        }

        if (CheckWin(connect4Board) != 0) {
            gameOver = true;
        }

        return gameOver;
    }

    @Override
    public int CheckWin(Board connect4Board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                int player = connect4Board.board[row][col];
                if (player == 0) {
                    continue;
                }

                if (col + 3 < 8 && player == connect4Board.board[row][col + 1]
                        && player == connect4Board.board[row][col + 2]
                        && player == connect4Board.board[row][col + 3]) {
                    return player;
                }

                if (row + 3 < 8) {
                    if (player == connect4Board.board[row + 1][col]
                            && player == connect4Board.board[row + 2][col]
                            && player == connect4Board.board[row + 3][col]) {
                        return player;
                    }

                    if (col + 3 < 8
                            && player == connect4Board.board[row + 1][col + 1]
                            && player == connect4Board.board[row + 2][col + 2]
                            && player == connect4Board.board[row + 3][col + 3]) {
                        return player;
                    }

                    if (col - 3 >= 0
                            && player == connect4Board.board[row + 1][col - 1]
                            && player == connect4Board.board[row + 2][col - 2]
                            && player == connect4Board.board[row + 3][col - 3]) {
                        return player;
                    }

                }

            }
        }
        return 0;
    }

    @Override
    public List validMovesFromPiece(Board game, String postion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
