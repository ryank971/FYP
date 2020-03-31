/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Kelly
 */
public interface IGame {
    
    public abstract List validMoves(Board CheckersBoard);
    
    public abstract Boolean isOver(Board CheckersBoard);
    
    public  abstract List validMovesFromPiece(Board game,String postion);

//    public static List listOfMoves(Board game) {
//        List<String> validMoves = new ArrayList<String>();
//        List<String> list = game.allGamePieces(game.turn);
//        for (int i = 0; i < list.size(); i++) {
//            String[] CurrentS = list.get(i).split(",");
//            int size = CurrentS.length;
//            int[] Current = new int[size];
//            for (int a = 0; a < size; a++) {
//                Current[a] = Integer.parseInt(CurrentS[a]);
//            }
//            for (int r = 0; r < 8; r++) {
//                for (int c = 0; c < 8; c++) {
//
//                    int[] move = {r, c};
//                    if (isValidMove(game, Current, move) == true) {
//                        validMoves.add(list.get(i) + "-" + r + "," + c);
//                    }
//                }
//
//            }
//        }
//        return validMoves;
//    }
//
//    public static Boolean GameOver(Board game) {
//        Boolean gameOver = true;
//        for (int r = 0; r < 8; r++) {
//            for (int c = 0; c < 8; c++) {
//                if (game.board[r][c] == game.turn || game.board[r][c] == game.turn + 2) {
//                    gameOver = false;
//                }
//
//            }
//        }
//        if (listOfMoves(game, game.allGamePieces(game.turn)).size() == 0) {
//            System.out.println("GAME OVER");
//            gameOver = true;
//        }
//
//        return gameOver;
//    }
//
//    public static List listOfMovesFromPosition(Board game, List<String> list, String current) {
//        List<String> validMoves = new ArrayList<String>();
//        String[] CurrentS = current.split(",");
//        int size = CurrentS.length;
//        int[] Current = new int[size];
//        for (int a = 0; a < size; a++) {
//            Current[a] = Integer.parseInt(CurrentS[a]);
//        }
//        for (int r = 0; r < 8; r++) {
//            for (int c = 0; c < 8; c++) {
//                int[] move = {r, c};
//                if (isValidMove(game, Current, move) == true) {
//                    validMoves.add(current + "-" + r + "," + c);
//                }
//            }
//
//        }
//
//        return validMoves;
//
//    }
}
