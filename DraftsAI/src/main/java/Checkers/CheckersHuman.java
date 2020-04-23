/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Checkers;

import MainFolder.Board;
import MainFolder.Agent;

/**
 *
 * @author Ryan Kelly
 */
public class CheckersHuman {

    static Checkers checkers = new Checkers();
    public static  String BestMove;
    public static Boolean makeMove(Board game, String current, String move) {
        BestMove = current+"-"+move;
        String[] CurrentS = current.split(",");
        String[] MoveS = move.split(",");

        int size = CurrentS.length;
        int[] Current = new int[size];
        int[] Move = new int[size];
        for (int i = 0; i < size; i++) {
            Current[i] = Integer.parseInt(CurrentS[i]);
            Move[i] = Integer.parseInt(MoveS[i]);

        }

        if (checkers.isValidMove(game, Current, Move) == true) {
            if (checkers.isDoubleJump(game, Current, Move)) {
                int turn = game.turn;
                int[] middleMove = checkers.ReturnDoubleJump(game, Current, Move);
                SimulatePlay(game, Current[0] + "," + Current[1], middleMove[0] + "," + middleMove[1]);
                game.turn = turn;
                SimulatePlay(game, middleMove[0] + "," + middleMove[1], Move[0] + "," + Move[1]);

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
        }
        return true;
    }

    public static Board SimulatePlay(Board CheckerBoard, String current, String move) {
        String[] CurrentS = current.split(",");
        String[] MoveS = move.split(",");

        int size = CurrentS.length;
        int[] Current = new int[size];
        int[] Move = new int[size];
        for (int i = 0; i < size; i++) {
            Current[i] = Integer.parseInt(CurrentS[i]);
            Move[i] = Integer.parseInt(MoveS[i]);

        }

        if (Checkers.isDoubleJump(CheckerBoard, Current, Move)) {
            int turn = CheckerBoard.turn;
            int[] middleMove = ReturnDoubleJump(CheckerBoard, Current, Move);
            SimulatePlay(CheckerBoard, Current[0] + "," + Current[1], middleMove[0] + "," + middleMove[1]);
            CheckerBoard.turn = turn;
            SimulatePlay(CheckerBoard, middleMove[0] + "," + middleMove[1], Move[0] + "," + Move[1]);

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
        return CheckerBoard;
    }

    public static int[] ReturnDoubleJump(Board CheckerBoard, int[] Current, int[] Move) {
        for (int i = -2; i <= 2; i += 4) {
            for (int j = 2; j >= -2; j -= 4) {
                int[] newMove = {Move[0] - i, Move[1] + j};
                if (Checkers.isSingleJump(CheckerBoard, Current, newMove) == true) {
                    if (Checkers.isSingleJump(CheckerBoard, newMove, Move) == true) {
                        return newMove;
                    }
                }
            }
        }
        return null;

    }

}
