/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect4;

import MainFolder.Board;
import java.util.Arrays;
import java.util.List;
import MainFolder.Agent;

/**
 *
 * @author Ryan Kelly
 */
public class Connect4AlphaBeta implements Agent {

    Connect4 Connect4Rules = new Connect4();
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

        game.board[row][col] = game.turn;

        if (game.turn == 1) {
            game.turn = 2;
        } else {
            game.turn = 1;
        }
        return BestMove;
    }

    public  double alphaBeta(Board game, int depth, Boolean maximizingP, int maxDepth, int turn, double alpha, double beta) {

        if (Connect4Rules.CheckWin(game) == turn) {
            return 999999;
        } else if (Connect4Rules.CheckWin(game) != turn && Connect4Rules.CheckWin(game) != 0) {
            return -99999;
        } else if (depth == 0) {
            return evaluationFucntion(game.GetBoard());
        }
        List<String> validMoves = Connect4Rules.validMoves(game);
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

    public static Board BuildFromNode(Board connect4Board, String move) {
        int[][] copyBoard = cloneBoard(connect4Board.board);
        Board CopyGame = new Board(copyBoard, connect4Board.turn);
        int[][] temp = cloneBoard(CopyGame.board);
        Board Temp = new Board(temp, connect4Board.turn);
        SimulatePlay(Temp, move);
        return Temp;
    }

    public static int[][] cloneBoard(int[][] board) {

        final int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    public static Board SimulatePlay(Board connect4Board, String move) {

        int row = Integer.parseInt(move.split(",")[0]);
        int col = Integer.parseInt(move.split(",")[1]);
        connect4Board.board[row][col] = connect4Board.turn;

        if (connect4Board.turn == 1) {
            connect4Board.turn = 2;
        } else {
            connect4Board.turn = 1;
        }
        return connect4Board;

    }

    public static double evaluationFucntion(int[][] board) {

        int scoreForTwo = 10;
        int scoreForThree = 1000;
        int playerOneScore = 0;
        int playerTwoScore = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int playerScore = 0;
                int player = board[row][col];
                if (player == 0) {
                    continue;
                }

                if (col + 3 < 8 && player == board[row][col + 1]) {

                    if (player == board[row][col + 2] && 0 == board[row][col + 3]) {
                        playerScore += scoreForThree;
                    } else if (0 == board[row][col + 2]) {
                        playerScore += scoreForTwo;
                    }
                }

                if (row + 3 < 8) {
                    if (player == board[row + 1][col]) {

                        if (player == board[row + 2][col] && 0 == board[row + 3][col]) {
                            playerScore += scoreForThree;
                        } else if (0 == board[row + 2][col]) {
                            playerScore += scoreForTwo;
                        }

                    }

                    if (col + 3 < 8 && player == board[row + 1][col + 1]) {

                        if (player == board[row + 2][col + 2] && 0 == board[row + 3][col + 3]) {
                            playerScore += scoreForThree;
                        } else if (0 == board[row + 2][col + 2]) {
                            playerScore += scoreForTwo;
                        }
                    }

                    if (col - 3 >= 0 && player == board[row + 1][col - 1]) {
                        if (player == board[row + 2][col - 2] && 0 == board[row + 3][col - 3]) {
                            playerScore += scoreForThree;
                        } else if (0 == board[row + 2][col - 2]) {
                            playerScore += scoreForTwo;
                        }
                    }

                }
                if (player == 1) {
                    playerOneScore += playerScore;
                } else if (player == 2) {
                    playerTwoScore += playerScore;
                }

            }
        }

        return playerOneScore - playerTwoScore;

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


}
