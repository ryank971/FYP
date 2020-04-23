/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Checkers;

import MainFolder.Board;
import java.util.List;
import java.util.Random;
import MainFolder.Agent;

/**
 *
 * @author Ryan Kelly
 */
public class CheckersRandom implements Agent {

    Checkers checkerRules = new Checkers();

    @Override
    public String makeMove(Board game, int depth, boolean playingFor) {
        String BestMove;
        List<String> validMoves = checkerRules.validMoves(game);
        Random rand = new Random();
        int a = rand.nextInt(validMoves.size());
        String CurrentAndMove = validMoves.get(a);
        BestMove = CurrentAndMove;
        String current = CurrentAndMove.split("-")[0];
        String move = CurrentAndMove.split("-")[1];

        String[] CurrentS = current.split(",");
        String[] MoveS = move.split(",");

        int size = CurrentS.length;
        int[] Current = new int[size];
        int[] Move = new int[size];
        for (int i = 0; i < size; i++) {
            Current[i] = Integer.parseInt(CurrentS[i]);
            Move[i] = Integer.parseInt(MoveS[i]);

        }

        if (checkerRules.isDoubleJump(game, Current, Move)) {
            if (game.turn == 1) {
                if (Move[0] == 7) {
                    game.board[Move[0]][Move[1]] = game.turn + 2;
                    game.board[Current[0]][Current[1]] = 0;
                } else {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                }
            } else if (game.turn == 2) {
                if (Move[0] == 0) {
                    game.board[Move[0]][Move[1]] = game.turn + 2;
                    game.board[Current[0]][Current[1]] = 0;
                } else {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                }

            }

            if (game.turn == 1) {
                game.turn = 2;
            } else {
                game.turn = 1;
            }

        } else {

            if (Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2) {
                game.board[Current[0] + 1][Current[1] + 1] = 0;
            } else if (Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2) {
                game.board[Current[0] + 1][Current[1] - 1] = 0;
            } else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2) {
                game.board[Current[0] - 1][Current[1] + 1] = 0;
            } else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2) {
                game.board[Current[0] - 1][Current[1] - 1] = 0;
            }

            if (game.turn == 1) {
                if (Move[0] == 7) {
                    game.board[Move[0]][Move[1]] = game.turn + 2;
                    game.board[Current[0]][Current[1]] = 0;
                } else {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                }
            } else if (game.turn == 2) {
                if (Move[0] == 0) {
                    game.board[Move[0]][Move[1]] = game.turn + 2;
                    game.board[Current[0]][Current[1]] = 0;
                } else {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                }

            }

            if (game.turn == 1) {
                game.turn = 2;
            } else {
                game.turn = 1;
            }
        }
        return BestMove;

    }


}
