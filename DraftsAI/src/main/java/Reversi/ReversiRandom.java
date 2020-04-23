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
public class ReversiRandom implements Agent {
    public static String BestMove;
    Reversi reversiRules = new Reversi();

    @Override
    public String makeMove(Board game, int depth, boolean playingFor) {

        String BestMove="";
        List<String> validMoves = reversiRules.validMoves(game);

        if (!validMoves.isEmpty()) {
            Random rand = new Random();
            int a = rand.nextInt(validMoves.size());
            String Move = validMoves.get(a);
            BestMove = Move;
            int row = Integer.parseInt(Move.split(",")[0]);
            int col = Integer.parseInt(Move.split(",")[1]);
            
            game.board[row][col] = game.turn;

            ImplementMove(game, -1, -1, row, col);
            ImplementMove(game, -1, 0, row, col);
            ImplementMove(game, -1, 1, row, col);

            ImplementMove(game, 0, -1, row, col);
            ImplementMove(game, 0, 1, row, col);

            ImplementMove(game, 1, -1, row, col);
            ImplementMove(game, 1, 0, row, col);
            ImplementMove(game, 1, 1, row, col);

            if (game.turn == 1) {
                game.turn = 2;
            } else {
                game.turn = 1;
            }
        } else {
            if (game.turn == 1) {
                game.turn = 2;
            } else {
                game.turn = 1;
            }
        }
        return BestMove;
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
