/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect4;

import MainFolder.Board;
import java.util.List;
import java.util.Random;
import MainFolder.Agent;

/**
 *
 * @author Ryan Kelly
 */
public class RandomConnectFour implements Agent {

    public static String BestMove;
    Connect4 connect4Rules = new Connect4();

    @Override
    public void makeMove(Board connect4Board) {
        List<String> validMoves = connect4Rules.validMoves(connect4Board);

        Random rand = new Random();
        int a = rand.nextInt(validMoves.size());
        String Move = validMoves.get(a);
        BestMove = Move;
        int row = Integer.parseInt(Move.split(",")[0]);
        int col = Integer.parseInt(Move.split(",")[1]);

        connect4Board.board[row][col] = connect4Board.turn;

        if (connect4Board.turn == 1) {
            connect4Board.turn = 2;
        } else {
            connect4Board.turn = 1;
        }

    }

}
