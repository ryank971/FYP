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
public class TestMinimax {
    
    
        static int best;
        static List<Integer> moves = new ArrayList<Integer>();
      public static  int minimax(TestTreeNode node,int depth, Boolean maximizingP){
       if(depth == 0)
       {
           //System.out.println(MoveCalculator.evaluationFucntion(node.data));
             //node.displayNodeData(node);
            //System.out.println("");
            return node.data;
       }
       if (maximizingP == true){
           int bestValueMax = -9999999;
           for (int i = 0; i<node.children.size();i++){
               int val = minimax(node.children.get(i),depth-1,false);
               if(val > bestValueMax)
               {
                   if(depth ==3 )
                   {
                       best = node.children.get(i).data;
                   }
                   bestValueMax = val;
                   
                   

                   //move.displayNodeData(move);
               }    
               //System.out.println("BestValueMax "+ bestValueMax);
           }
           moves.add(bestValueMax);
           return bestValueMax;
       }
       else if (maximizingP == false){
           int bestValueMin = 9999999;
           for (int i = 0; i<node.children.size();i++){
               int val = minimax(node.children.get(i),depth-1,true);
               bestValueMin = Min(bestValueMin,val);
           }
           return bestValueMin;
        }
        else return 99999999;
       
           
    }
    
    public static int Max(int valueOne,int valueTwo){
        if(valueOne > valueTwo)
        return valueOne;
        else return valueTwo;
    }
    
    public static int Min(int valueOne,int valueTwo){
        if(valueOne < valueTwo)
        return valueOne;
        else return valueTwo;
    }
    
    
}
