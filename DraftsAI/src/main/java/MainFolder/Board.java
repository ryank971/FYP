package MainFolder;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ryan Kelly
 */
public class Board {

    public volatile int turn = 1;
    public int[][] board;

    public Board(int[][] copyBoard, int copyTurn) {
        this.board = copyBoard;
        this.turn = copyTurn;
    }

    public Board() {
        this.board = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

//         this.board = new int[][]{
//        {0, 1, 0, 1, 0, 1, 0, 1},
//        {1, 0, 1, 0, 1, 0, 1, 0},
//        {0, 1, 0, 1, 0, 1, 0, 1},
//        {0, 0, 0, 0, 0, 0, 0, 0},
//        {0, 0, 0, 0, 0, 0, 0, 0},
//        {2, 0, 2, 0, 2, 0, 2, 0},
//        {0, 2, 0, 2, 0, 2, 0, 2},
//        {2, 0, 2, 0, 2, 0, 2, 0}
//        };
        this.turn = turn;

    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setCheckerBoard() {
        this.board = new int[][]{
        {0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 2, 0, 2, 0, 2, 0, 2},
        {2, 0, 2, 0, 2, 0, 2, 0}
        };
    }

    public void setReversiBoard() {
        this.board = new int[][]{
//            {2, 2, 2, 2, 2, 2, 2, 1},
//            {2, 1, 2, 2, 2, 2, 2, 1},
//            {2, 2, 2, 2, 2, 1, 1, 1},
//            {2, 2, 2, 1, 1, 1, 2, 1},
//            {2, 2, 2, 2, 1, 2, 2, 1},
//            {1, 1, 2, 2, 2, 2, 1, 0},
//            {1, 1, 1, 1, 1, 1, 0, 0},
//            {1, 1, 1, 1, 1, 0, 0, 0}
//        };
        
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 0},
            {0, 0, 0, 1, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
//2 2 2 2 2 2 2 1 
//2 1 2 2 2 2 2 1 
//2 2 2 2 2 1 1 1 
//2 2 2 1 1 1 2 1 
//2 2 2 2 1 2 2 1 
//1 1 2 2 2 2 1 0 
//1 1 1 1 1 1 0 0 
//1 1 1 1 1 0 0 0 
    }

    public void setConnect4Board() {
        this.board = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    public void PrintGame() {
        for (int[] x : board) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public int[][] GetBoard() {
        return board;
    }

    public List allGamePieces(int player) {
        List<String> list = new ArrayList<String>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                //System.out.println("row = "+row +" Column = "+col);
                if (board[row][col] == player || board[row][col] == player + 2) {
                    list.add(row + "," + col);
                }
            }

        }

        return list;
    }

}
