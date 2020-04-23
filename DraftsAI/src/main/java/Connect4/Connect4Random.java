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
public class Connect4Random implements Agent {

    public static String BestMove;
    Connect4 connect4Rules = new Connect4();

    @Override
    public String makeMove(Board game, int depth, boolean playingFor) {
        String BestMove;
        List<String> validMoves = connect4Rules.validMoves(game);

        Random rand = new Random();
        int a = rand.nextInt(validMoves.size());
        String Move = validMoves.get(a);
        BestMove = Move;
        int row = Integer.parseInt(Move.split(",")[0]);
        int col = Integer.parseInt(Move.split(",")[1]);

        game.board[row][col] = game.turn;

        if (game.turn == 1) {
            game.turn = 2;
        } else {
            game.turn = 1;
        }
        return BestMove;
    }



}
