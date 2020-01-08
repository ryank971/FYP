
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelly
 */
public class MoveCalculator {
    
    public static List listOfMoves(int[][] board,List<String> list) {
        List<String> validMoves = new ArrayList<String>(); 
        //System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            //System.out.println("Current "+list.get(i));
             String[] CurrentS = list.get(i).split(",");
             int size = CurrentS.length;
             int[] Current = new int[size];
             for (int a = 0; a < size; a++) {
                 Current[a] = Integer.parseInt(CurrentS[a]);
             }
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    
                    int[] move = {r,c};
//                    System.out.println("----EVALUATING----");
//                    System.out.println("FROM "+Current[0]+","+Current[1]);
//                    System.out.println("Too "+move[0]+","+move[1]);
//                    System.out.println("------------------");
                   if(EvaluateMove.isValidMove(board, Current, move)==true)
                   {
                       validMoves.add(list.get(i)+"-"+r+","+c);
                       //System.out.println(r+","+c +" is a valid move from "+ Current[0]+","+Current[1]);
                   }
                }

            }
        }
        return validMoves;

    }
    
    public static String RandomMove(int[][] board,List<String> list) {
        List<String> validMoves = listOfMoves(board,list); 
        Random rand = new Random();
        int a = rand.nextInt(validMoves.size());
       return validMoves.get(a);
    
}
}
