/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reversi;

import MainFolder.Board;
import java.util.Arrays;
import java.util.List;
import MainFolder.Agent;

/**
 *
 * @author Ryan Kelly
 */
public class ReversiAlphaBeta implements Agent {

    static Reversi reversiRules = new Reversi();
    static String MaxMoveString;
    static String MinMoveString;


    @Override
    public String makeMove(Board game, int depth, boolean playingFor) {
        String BestMove;
        alphaBeta(game, depth, playingFor, depth, game.turn, -9999999, 9999999);

        if (playingFor == false) {
            BestMove = MinMoveString;
        } else {
            BestMove = MaxMoveString;
        }

        int row = Integer.parseInt(BestMove.split(",")[0]);
        int col = Integer.parseInt(BestMove.split(",")[1]);

        ImplementMove(game, -1, -1, row, col);
        ImplementMove(game, -1, 0, row, col);
        ImplementMove(game, -1, 1, row, col);

        ImplementMove(game, 0, -1, row, col);
        ImplementMove(game, 0, 1, row, col);

        ImplementMove(game, 1, -1, row, col);
        ImplementMove(game, 1, 0, row, col);
        ImplementMove(game, 1, 1, row, col);

        game.board[row][col] = game.turn;

        if (game.turn == 1) {
            game.turn = 2;
        } else {
            game.turn = 1;
        }
        return BestMove;

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

    public double alphaBeta(Board game, int depth, Boolean maximizingP, int maxDepth, int turn, double alpha, double beta) {

        if (reversiRules.CheckWin(game) == game.turn) {
            return 999999;
        } else if (reversiRules.CheckWin(game) != game.turn && reversiRules.CheckWin(game) != 0) {
            return -99999;
        } else if (depth == 0) {
            return evaluationFucntion(game.GetBoard());
        }
        List<String> validMoves = reversiRules.validMoves(game);
        if (maximizingP == true) {
            double bestValueMax = -9999999;

            for (int i = 0; i < validMoves.size(); i++) {

                Board Temp = BuildFromNode(game, validMoves.get(i));
                double val = alphaBeta(Temp, depth - 1, false, maxDepth, turn, alpha, beta);
                if (val > bestValueMax) {
                    bestValueMax = val;
                    if (depth == maxDepth) {
                        MaxMoveString = validMoves.get(i);
                    }
                }
                alpha = max(alpha, bestValueMax);
                if (beta <= alpha) {
                    break;
                }
            }

            return bestValueMax;
        } else if (maximizingP == false) {
            double bestValueMin = 9999999;
            for (int i = 0; i < validMoves.size(); i++) {

                Board Temp = BuildFromNode(game, validMoves.get(i));
                double val = alphaBeta(Temp, depth - 1, true, maxDepth, turn, alpha, beta);
                if (val < bestValueMin) {
                    bestValueMin = val;
                    if (depth == maxDepth) {
                        MinMoveString = validMoves.get(i);
                    }
                }
                beta = min(beta, bestValueMin);
                if (beta <= alpha) {
                    break;
                }
            }

            return bestValueMin;
        } else {
            return 99999999;
        }

    }

    public static Board BuildFromNode(Board ReversiBoard, String move) {
        int[][] copyBoard = cloneBoard(ReversiBoard.board);
        Board CopyGame = new Board(copyBoard, ReversiBoard.turn);
        int[][] temp = cloneBoard(CopyGame.board);
        Board Temp = new Board(temp, ReversiBoard.turn);
        SimulatePlay(Temp, move);
        return Temp;
    }

    public static Board SimulatePlay(Board ReversiBoard, String move) {

        int row = Integer.parseInt(move.split(",")[0]);
        int col = Integer.parseInt(move.split(",")[1]);
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
        return ReversiBoard;

    }

    public static int[][] cloneBoard(int[][] board) {

        final int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    static public double max(double one, double two) {
        if (one > two) {
            return one;
        } else {
            return two;
        }
    }

    static public double min(double one, double two) {
        if (one < two) {
            return one;
        } else {
            return two;
        }
    }

    public static double evaluationFucntion(int[][] board) {
        double playerOne = 0;
        double playerTwo = 0;
        for (int[] x : board) {
            for (int y : x) {
                if (y == 1) {
                    playerOne++;
                } else if (y == 2) {
                    playerTwo++;
                }
            }
        }
        return playerOne - playerTwo;
    }





}
