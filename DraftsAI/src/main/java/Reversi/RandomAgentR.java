/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reversi;

import MainFolder.Board;
import Checkers.Checkers;
import java.util.List;
import java.util.Random;
import MainFolder.Agent;

/**
 *
 * @author Ryan Kelly
 */
public class RandomAgentR implements Agent {

    Reversi reversiRules = new Reversi();

    @Override
    public void makeMove(Board ReversiBoard) {

        List<String> validMoves = reversiRules.validMoves(ReversiBoard);

        if (!validMoves.isEmpty()) {
            Random rand = new Random();
            int a = rand.nextInt(validMoves.size());
            String Move = validMoves.get(a);
            int row = Integer.parseInt(Move.split(",")[0]);
            int col = Integer.parseInt(Move.split(",")[1]);
            
            ReversiBoard.board[row][col] = ReversiBoard.turn;

            ImplementMove(ReversiBoard, -1, -1, row, col);
            ImplementMove(ReversiBoard, -1, 0, row, col);
            ImplementMove(ReversiBoard, -1, 1, row, col);

            ImplementMove(ReversiBoard, 0, -1, row, col);
            ImplementMove(ReversiBoard, 0, 1, row, col);

            ImplementMove(ReversiBoard, 1, -1, row, col);
            ImplementMove(ReversiBoard, 1, 0, row, col);
            ImplementMove(ReversiBoard, 1, 1, row, col);

            if (ReversiBoard.turn == 1) {
                ReversiBoard.turn = 2;
            } else {
                ReversiBoard.turn = 1;
            }
        } else {
            if (ReversiBoard.turn == 1) {
                ReversiBoard.turn = 2;
            } else {
                ReversiBoard.turn = 1;
            }
        }
    }

    public boolean ImplementMove(Board ReversiBoard, int directionRow, int directionCol, int row, int column) {
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
