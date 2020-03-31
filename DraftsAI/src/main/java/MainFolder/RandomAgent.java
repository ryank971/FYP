/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Ryan Kelly
 */
public class RandomAgent implements IAgent {

    Checkers checkerRules = new Checkers();

    @Override
    public void makeMove(Board CheckerBoard) {

        List<String> validMoves = checkerRules.validMoves(CheckerBoard);
        Random rand = new Random();
        int a = rand.nextInt(validMoves.size());
        String CurrentAndMove = validMoves.get(a);
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

        if (checkerRules.isDoubleJump(CheckerBoard, Current, Move)) {
            if (CheckerBoard.turn == 1) {
                if (Move[0] == 7) {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.turn + 2;
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                } else {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.board[Current[0]][Current[1]];
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                }
            } else if (CheckerBoard.turn == 2) {
                if (Move[0] == 0) {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.turn + 2;
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                } else {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.board[Current[0]][Current[1]];
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                }

            }

            if (CheckerBoard.turn == 1) {
                CheckerBoard.turn = 2;
            } else {
                CheckerBoard.turn = 1;
            }

        } else {

            if (Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2) {
                CheckerBoard.board[Current[0] + 1][Current[1] + 1] = 0;
            } else if (Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2) {
                CheckerBoard.board[Current[0] + 1][Current[1] - 1] = 0;
            } else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2) {
                CheckerBoard.board[Current[0] - 1][Current[1] + 1] = 0;
            } else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2) {
                CheckerBoard.board[Current[0] - 1][Current[1] - 1] = 0;
            }

            if (CheckerBoard.turn == 1) {
                if (Move[0] == 7) {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.turn + 2;
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                } else {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.board[Current[0]][Current[1]];
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                }
            } else if (CheckerBoard.turn == 2) {
                if (Move[0] == 0) {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.turn + 2;
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                } else {
                    CheckerBoard.board[Move[0]][Move[1]] = CheckerBoard.board[Current[0]][Current[1]];
                    CheckerBoard.board[Current[0]][Current[1]] = 0;
                }

            }

            if (CheckerBoard.turn == 1) {
                CheckerBoard.turn = 2;
            } else {
                CheckerBoard.turn = 1;
            }
        }

    }
}
