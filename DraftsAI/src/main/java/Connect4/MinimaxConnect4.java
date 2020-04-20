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
public class MinimaxConnect4 implements Agent {

    Connect4 Connect4Rules = new Connect4();
    public static int Depth;
    static String MaxMoveString;
    static String MinMoveString;
    public static Boolean PlayingFor;
    public static String BestMove;

    @Override
    public void makeMove(Board connect4Board) {
        minimax(connect4Board, Depth, PlayingFor, Depth, connect4Board.turn);

        if (PlayingFor == false) {
            BestMove = MinMoveString;
        } else {
            BestMove = MaxMoveString;
        }
        int row = Integer.parseInt(BestMove.split(",")[0]);
        int col = Integer.parseInt(BestMove.split(",")[1]);

        connect4Board.board[row][col] = connect4Board.turn;

        if (connect4Board.turn == 1) {
            connect4Board.turn = 2;
        } else {
            connect4Board.turn = 1;
        }
    }

    public double minimax(Board game, int depth, Boolean maximizingP, int maxDepth, int turn) {

        if (Connect4Rules.CheckWin(game) == game.turn) {
            game.PrintGame();
            System.out.println("^^ Winning board for player " + game.turn);
            return 999999;
        } else if (Connect4Rules.CheckWin(game) != game.turn && Connect4Rules.CheckWin(game) != 0) {
//            game.PrintGame();
//            System.out.println("^^ Losing Board for player " + game.turn);
            return -99999;
        } else if (depth == 0) {
            return evaluationFucntion(game.GetBoard());
        }
        List<String> validMoves = Connect4Rules.validMoves(game);
        if (maximizingP == true) {
            double bestValueMax = -9999999;
            for (int i = 0; i < validMoves.size(); i++) {
                Board Temp = BuildFromNode(game, validMoves.get(i));
                double val = minimax(Temp, depth - 1, false, maxDepth, turn);
                if (val > bestValueMax) {
                    bestValueMax = val;
                    if (depth == maxDepth) {
                        MaxMoveString = validMoves.get(i);
                    }
                }

            }

            return bestValueMax;
        } else if (maximizingP == false) {
            double bestValueMin = 9999999;
            for (int i = 0; i < validMoves.size(); i++) {

                Board Temp = BuildFromNode(game, validMoves.get(i));
                double val = minimax(Temp, depth - 1, true, maxDepth, turn);
                if (val < bestValueMin) {
                    bestValueMin = val;
                    if (depth == maxDepth) {
                        MinMoveString = validMoves.get(i);
                    }
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
// int scoreForTwo = 10;
//        int scoreForThree = 1000;
//        int playerOneScore = 0;
//        int playerTwoScore = 0;
//
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 8; col++) {
//                int playerScore = 0;
//                int player = board[row][col];
//                if (player == 0) {
//                    continue;
//                }
//
//                if (col + 3 < 8 && player == board[row][col + 1]) {
//
//                    if (player == board[row][col + 2] && 0 == board[row][col + 3]) {
//                        playerScore += scoreForThree;
//                    } else if (0 == board[row][col + 2]){
//                        playerScore += scoreForTwo;
//                    }
//                }
//
//                if (row + 3 < 8) {
//                    if (player == board[row + 1][col]) {
//
//                        if (player == board[row + 2][col] && 0 == board[row+3][col]) {
//                            playerScore += scoreForThree;
//                        } 
//                        else if(0 == board[row+2][col]) {
//                            playerScore += scoreForTwo;
//                        }
//
//                    }
//
//                    if (col + 3 < 8 && player == board[row + 1][col + 1]) {
//                        
//                        if (player == board[row + 2][col + 2] && 0 == board[row + 3][col + 3] ) {
//                            playerScore += scoreForThree;
//                        } else if (0 == board[row + 2][col + 2]){
//                            playerScore += scoreForTwo;
//                        }
//                    }
//
//                    if (col - 3 >= 0 && player == board[row + 1][col - 1]) {
//                        if (player == board[row + 2][col - 2] && 0 == board[row + 3][col - 3]) {
//                            playerScore += scoreForThree;
//                        } else if(0 == board[row + 2][col - 2]){
//                            playerScore += scoreForTwo;
//                        }
//                    }
//
//                }
//                if (player == 1) {
//                    playerOneScore += playerScore;
//                } else if (player == 2) {
//                    playerTwoScore += playerScore;
//                }
//
//            }
//        }
//
//        return playerOneScore - playerTwoScore;
        return 0;

    }

}
