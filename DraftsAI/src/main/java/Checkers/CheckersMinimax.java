/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Checkers;

import MainFolder.Board;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import MainFolder.Agent;

/**
 *
 * @author Ryan Kelly
 */
public class CheckersMinimax implements Agent {

    Checkers checkerRules = new Checkers();
    static String MaxMoveString;
    static String MinMoveString;


    @Override
    public String makeMove(Board game, int depth, boolean playingFor) {
        String BestMove;
        minimax(game, depth, playingFor, depth, game.turn);
        if (playingFor == false) {
            BestMove = MinMoveString;
        } else {
            BestMove = MaxMoveString;
        }
        String current = BestMove.split("-")[0];
        String move = BestMove.split("-")[1];
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
            int turn = game.turn;
            int[] middleMove = ReturnDoubleJump(game, Current, Move);
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
        return BestMove;

    }

    public double minimax(Board game, int depth, Boolean maximizingP, int maxDepth, int turn) {

        if (win(game.GetBoard(), turn) == true) {
            return 999999;
        } else if (lose(game.GetBoard(), turn) == true) {
            return -99999;
        } else if (depth == 0) {
            return evaluationFucntion(game.GetBoard());
        }
        List<String> validMoves = checkerRules.validMoves(game);
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

    public static Boolean win(int[][] board, int turn) {
        Boolean win = true;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != turn && board[r][c] != turn + 2 && board[r][c] != 0) {
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
                if (board[r][c] == turn || board[r][c] == turn + 2) {
                    lose = false;
                }

            }
        }

        return lose;
    }

    public Board BuildFromNode(Board board, String move) {
        int[][] copyBoard = cloneBoard(board.board);
        Board CopyGame = new Board(copyBoard, board.turn);
        int[][] temp = cloneBoard(CopyGame.board);
        Board Temp = new Board(temp, board.turn);
        String moves[] = move.split("-");
        SimulatePlay(Temp, moves[0], moves[1]);
        return Temp;
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

    public static double evaluationFucntion(int[][] board) {
        double playerOne = 0;
        double playerTwo = 0;
        for (int[] x : board) {
            for (int y : x) {
                if (y == 1) {
                    playerOne++;
                } else if (y == 3) {
                    playerOne += 1.5;
                } else if (y == 2) {
                    playerTwo++;
                } else if (y == 4) {
                    playerTwo += 1.5;
                }
            }
        }
        return playerOne - playerTwo;
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
