/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reversi;

import MainFolder.Board;

/**
 *
 * @author Ryan Kelly
 */
public class HumanPlayerReversi {

    public static String BestMove;
    static Reversi reversiRules = new Reversi();

    public static void makeMove(Board Board, String move) {
        BestMove = move;
        int row = Integer.parseInt(move.split(",")[0]);
        int col = Integer.parseInt(move.split(",")[1]);

        Board.board[row][col] = Board.turn;

        ImplementMove(Board, -1, -1, row, col);
        ImplementMove(Board, -1, 0, row, col);
        ImplementMove(Board, -1, 1, row, col);

        ImplementMove(Board, 0, -1, row, col);
        ImplementMove(Board, 0, 1, row, col);

        ImplementMove(Board, 1, -1, row, col);
        ImplementMove(Board, 1, 0, row, col);
        ImplementMove(Board, 1, 1, row, col);

        if (Board.turn == 1) {
            Board.turn = 2;
            if (reversiRules.validMoves(Board).isEmpty()) {
                Board.turn = 1;
            }
        } else {
            Board.turn = 1;
            if (reversiRules.validMoves(Board).isEmpty()) {
                Board.turn = 2;
            }
        }
    }

    public static boolean ImplementMove(Board ReversiBoard, int directionRow, int directionCol, int row, int column) {
        if ((row + directionRow) < 0 || (row + directionRow > 7)) {
            return false;
        }

        if ((column + directionCol < 0) || (column + directionCol > 7)) {
            return false;
        }
        if (ReversiBoard.board[row + directionRow][column + directionCol] == 0) {
            return false;
        }
        if (ReversiBoard.board[row + directionRow][column + directionCol] == ReversiBoard.turn) {
            return true;
        } else {
            if (ImplementMove(ReversiBoard, directionRow, directionCol, row + directionRow, column + directionCol)) {
                ReversiBoard.board[row + directionRow][column + directionCol] = ReversiBoard.turn;
                return true;

            } else {
                return false;
            }
        }

    }

}
