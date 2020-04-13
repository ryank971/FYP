/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder.Reversi;

import MainFolder.Board;
import MainFolder.IAgent;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ryan Kelly
 */
public class AlphaBetaAgentR implements IAgent {

    Reversi reversiRules = new Reversi();
    static String MaxMoveString;
    static String MinMoveString;

    @Override
    public void makeMove(Board ReversiBoard) {

        alphaBeta(ReversiBoard, 8, true, 8, ReversiBoard.turn, -9999999, 9999999);
        int row = Integer.parseInt(MaxMoveString.split(",")[0]);
        int col = Integer.parseInt(MaxMoveString.split(",")[1]);

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

        if (win(game.GetBoard(), turn) == true) {
            return 999999;
        } else if (lose(game.GetBoard(), turn) == true) {
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

    public static Boolean win(int[][] board, int turn) {
        Boolean win = true;
        int Opponent;
        if (turn == 1) {
            Opponent = 2;
        } else {
            Opponent = 1;
        }

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == Opponent) {
                    win = false;
                }

            }
        }

        return win;
    }

    public static Boolean lose(int[][] board, int turn) {
        Boolean lose = true;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == turn) {
                    lose = false;
                }

            }
        }

        return lose;
    }

}
