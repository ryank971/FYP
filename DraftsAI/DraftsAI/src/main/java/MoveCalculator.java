
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
static TreeNode move;
static List<TreeNode> moves = new ArrayList<TreeNode>();
    
    public static List listOfMoves(Game game,List<String> list) {
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
                   if(EvaluateMove.isValidMove(game, Current, move)==true)
                   {
                       validMoves.add(list.get(i)+"-"+r+","+c);
                       //System.out.println(r+","+c +" is a valid move from "+ Current[0]+","+Current[1]);
                   }
                }

            }
        }
        return validMoves;

    }
    
    public static String RandomMove(Game game,List<String> list) {
        List<String> validMoves = listOfMoves(game,list); 
        Random rand = new Random();
        int a = rand.nextInt(validMoves.size());
       return validMoves.get(a);
    
}
    
    public static int evaluationFucntion(int[][]board){
        int playerOne=0;
        int playerTwo=0;
        for (int[] x : board) {
                        for (int y : x) {
                            if(y==1)
                            playerOne++;
                            else if(y==2)
                            playerTwo++;
                        }
        }
        return playerOne - playerTwo;
    }
    
    
    public static  int minimax(TreeNode node,int depth, Boolean maximizingP){
//       System.out.println("*******************************************************************************************************************************");
//       System.out.println("Recursive call No."+ counter);
//       counter++;
       
       if(depth == 0)
       {
           //System.out.println(MoveCalculator.evaluationFucntion(node.data));
            //node.displayNodeData(node);
            //System.out.println("");
           
            return MoveCalculator.evaluationFucntion(node.data);
            
       }

       
       if (maximizingP == true){
           int bestValueMax = -9999999;
           for (int i = 0; i<node.children.size();i++){
               int val = minimax(node.children.get(i),depth-1,false);
               //bestValueMax= Max(bestValueMax,val);
               if(val > bestValueMax)
               {
                   bestValueMax = val;
                   move = node.getChildren().get(i);
                   moves.add(node.getChildren().get(i));
                   //move.displayNodeData(move);
               }    

               //System.out.println("BestValueMax "+ bestValueMax);
           }

           return bestValueMax;
       }
       else if (maximizingP == false){
           int bestValueMin = 9999999;
           for (int i = 0; i<node.children.size();i++){
               int val = minimax(node.children.get(i),depth-1,true);
               //bestValueMin = Min(bestValueMin,val);]
               if (val < bestValueMin)
               {
                    bestValueMin = val;
                     move = node.getChildren().get(i);
                     //move.displayNodeData(move);
               }
           }

           return bestValueMin;
        }
                   
       
        else return 99999999;
       
           
    }
    
    
    public static void DisplayListOfMoves(){
        for(TreeNode move : moves)
        {
            move.displayNodeData(move);
        }
    }
    
    
}


