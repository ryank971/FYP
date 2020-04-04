/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder.Checkers;


import MainFolder.Board;
import MainFolder.IGame;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Kelly
 */
public class Checkers implements IGame {

    
    @Override
    public List validMoves(Board CheckerBoard) {
        List<String> validMoves = new ArrayList<String>();
        List<String> list = CheckerBoard.allGamePieces(CheckerBoard.turn);
        for (int i = 0; i < list.size(); i++) {
            String[] CurrentS = list.get(i).split(",");
            int size = CurrentS.length;
            int[] Current = new int[size];
            for (int a = 0; a < size; a++) {
                Current[a] = Integer.parseInt(CurrentS[a]);
            }
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {

                    int[] move = {r, c};
                    if (isValidMove(CheckerBoard, Current, move) == true) {
                        validMoves.add(list.get(i) + "-" + r + "," + c);
                    }
                }

            }
        }
        return validMoves;
    }

    @Override
    public Boolean isOver(Board CheckersBoard) {
        Boolean gameOver = true;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (CheckersBoard.board[r][c] == CheckersBoard.turn || CheckersBoard.board[r][c] == CheckersBoard.turn + 2) {
                    gameOver = false;
                }

            }
        }
        if (validMoves(CheckersBoard).size() == 0) {
            System.out.println("GAME OVER");
            gameOver = true;
        }

        return gameOver;
    }
    
    
    
    @Override
    public List validMovesFromPiece(Board game, String postion) {
        List<String> validMoves = new ArrayList<String>();
        String[] CurrentS = postion.split(",");
        int size = CurrentS.length;
        int[] Current = new int[size];
        for (int a = 0; a < size; a++) {
            Current[a] = Integer.parseInt(CurrentS[a]);
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                int[] move = {r, c};
                if (isValidMove(game, Current, move) == true) {
                    validMoves.add(postion + "-" + r + "," + c);
                }
            }
        }
        return validMoves;
    }

    public static boolean isValidMove(Board game, int[] Current, int[] Move) {
        if (Move[0] <= 8 && Move[1] <= 8) {
            if (game.board[Current[0]][Current[1]] == game.turn || game.board[Current[0]][Current[1]] == game.turn + 2) {
                if (!(game.board[Move[0]][Move[1]] == game.turn)) {
                    if ((Move[0] == Current[0] + 1 && (Move[1] == Current[1] + 1 || Move[1] == Current[1] - 1) && game.board[Move[0]][Move[1]] == 0) && game.turn == 1) {
                        return true;
                    } else if ((Move[0] == Current[0] - 1 && (Move[1] == Current[1] + 1 || Move[1] == Current[1] - 1) && game.board[Move[0]][Move[1]] == 0) && game.turn == 2) {
                        return true;
                    } else if (((Move[0] == Current[0] + 1 || Move[0] == Current[0] - 1) && (Move[1] == Current[1] + 1 || Move[1] == Current[1] - 1) && game.board[Move[0]][Move[1]] == 0) && game.board[Current[0]][Current[1]] == game.turn + 2) {
                        return true;
                    } else if (game.board[Current[0]][Current[1]] == game.turn) {
                        if (isSingleJump(game, Current, Move) == true) {
                            return true;
                        }
                    }
                    if (isDoubleJump(game, Current, Move) == true) {
                        return true;
                    }
                    if (game.board[Current[0]][Current[1]] == game.turn + 2) {
                        if (Current[0] != 7 && Current[1] != 7 && Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2 && game.board[Move[0]][Move[1]] == 0) {
                            if (game.board[Current[0] + 1][Current[1] + 1] != game.turn && game.board[Current[0] + 1][Current[1] + 1] != game.turn + 2 && game.board[Current[0] + 1][Current[1] + 1] != 0) {
                                return true;
                            }
                        } else if (Current[1] != 0 && Current[0] != 0 && Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2 && game.board[Move[0]][Move[1]] == 0) {
                            if (game.board[Current[0] - 1][Current[1] - 1] != game.turn && game.board[Current[0] - 1][Current[1] - 1] != game.turn + 2 && game.board[Current[0] - 1][Current[1] - 1] != 0) {
                                return true;
                            }
                        } else if (Current[1] != 0 && Current[0] != 7 && Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2 && game.board[Move[0]][Move[1]] == 0) {
                            if (game.board[Current[0] + 1][Current[1] - 1] != game.turn && game.board[Current[0] + 1][Current[1] - 1] != game.turn + 2 && game.board[Current[0] + 1][Current[1] - 1] != 0) {
                                return true;
                            }
                        } else if (Current[1] != 7 && Current[0] != 0 && Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2 && game.board[Move[0]][Move[1]] == 0) {
                            if (game.board[Current[0] - 1][Current[1] + 1] != game.turn && game.board[Current[0] - 1][Current[1] + 1] != game.turn + 2 && game.board[Current[0] - 1][Current[1] + 1] != 0) {
                                return true;
                            }
                        }
                    }

                }

            }
        }
        return false;
    }

//    public static void MakeDoubleJump(Board game, int[] Current, int[] Move) {
//        int turn = game.turn;
//        int[] middleMove = ReturnDoubleJump(game, Current, Move);
//        makeMove(game, Current[0] + "," + Current[1], middleMove[0] + "," + middleMove[1]);
//        game.turn = turn;
//        makeMove(game, middleMove[0] + "," + middleMove[1], Move[0] + "," + Move[1]);
//    }

    public static boolean isSingleJump(Board game, int[] Current, int[] Move) {
        //from 7,6 TO 6,7

        if (Move[0] < 8 && Move[1] < 8 && Move[0] >= 0 && Move[1] >= 0) {
            //System.out.println("here1");

            //System.out.println("here2");
            if (!(game.board[Move[0]][Move[1]] == game.turn)) {
                //System.out.println("here3");

                if (game.turn == 1 && Current[1] != 0 && Current[0] != 7 && (game.board[Current[0] + 1][Current[1] - 1] != 0 && game.board[Current[0] + 1][Current[1] - 1] != 1 && game.board[Current[0] + 1][Current[1] - 1] != 3)) {
                    //System.out.println("hereee");
                    //System.out.println(turn);
                    if (Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2 && game.board[Move[0]][Move[1]] == 0) {
                        //take this line of code out as this is a verify function
                        //board[Current[0] + 1][Current[1] - 1] = 0;
                        return true;
                    }
                }
                if (game.turn == 1 && Current[0] != 7 && Current[1] != 7 && (game.board[Current[0] + 1][Current[1] + 1] != 0 && game.board[Current[0] + 1][Current[1] + 1] != 1 && game.board[Current[0] + 1][Current[1] + 1] != 3)) {
                    //System.out.println("CHECK " +board[Current[0] + 1][Current[1] + 1]);
                    //int[] Opp = {Current[0]+1,Current[1]+1};
                    if (Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2 && game.board[Move[0]][Move[1]] == 0) {
                        //take this line of code out as this is a verify function
                        //board[Current[0] + 1][Current[1] + 1] = 0;
                        return true;
                    }//else System.out.println("failing here");
                    //5,4
                } else if (game.turn == 2 && Current[1] != 7 && Current[0] != 0 && (game.board[Current[0] - 1][Current[1] + 1] != 0 && game.board[Current[0] - 1][Current[1] + 1] != 2 && game.board[Current[0] - 1][Current[1] + 1] != 4)) {
                    //System.out.println("CHECK " +GetBoard()[Current[0] + 1][Current[1] + 1]);
                    //System.out.println("here4");
                    //int[] Opp = {Current[0]+1,Current[1]+1};
                    if (Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2 && game.board[Move[0]][Move[1]] == 0) {
                        //take this line of code out as this is a verify function
                        //board[Current[0] - 1][Current[1] + 1] = 0;
                        return true;
                    }//else System.out.println("failing heree");

                }
                if (game.turn == 2 && Current[1] != 0 && Current[0] != 0 && (game.board[Current[0] - 1][Current[1] - 1] != 0 && game.board[Current[0] - 1][Current[1] - 1] != 2 && game.board[Current[0] - 1][Current[1] - 1] != 4)) {
                    //System.out.println("in here");
                    if (Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2 && game.board[Move[0]][Move[1]] == 0) {
                        //take this line of code out as this is a verify function
                        //board[Current[0] - 1][Current[1] - 1] = 0;
                        return true;
                    }//else System.out.println("failed in here");
                }
            }
        }

        return false;
    }

    public static boolean isDoubleJump(Board game, int[] Current, int[] Move) {
        for (int i = -2; i <= 2; i += 4) {
            for (int j = 2; j >= -2; j -= 4) {
                int[] newMove = {Move[0] - i, Move[1] + j};
                if (isSingleJump(game, Current, newMove) == true) {
                    if (isSingleJump(game, newMove, Move) == true) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int[] ReturnDoubleJump(Board game, int[] Current, int[] Move) {
        for (int i = -2; i <= 2; i += 4) {
            for (int j = 2; j >= -2; j -= 4) {
                int[] newMove = {Move[0] - i, Move[1] + j};
                if (isSingleJump(game, Current, newMove) == true) {
                    if (isSingleJump(game, newMove, Move) == true) {
                        return newMove;
                    }
                }
            }
        }
        return null;

    }



}
