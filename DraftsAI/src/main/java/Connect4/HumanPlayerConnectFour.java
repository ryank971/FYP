/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect4;

import MainFolder.Board;

/**
 *
 * @author Ryan Kelly
 */
public class HumanPlayerConnectFour {
        public static String BestMove;
        public static void makeMove(Board Board, String move) {
            BestMove = move;
            int row = Integer.parseInt(move.split(",")[0]);
            int col = Integer.parseInt(move.split(",")[1]);
            Board.board[row][col] = Board.turn;

            if (Board.turn == 1) {
                Board.turn = 2;
            } else {
                Board.turn = 1;
            }
    }
}
