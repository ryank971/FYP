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
    
      public static  int minimax(TestTreeNode node,int depth, Boolean maximizingP){
       if(depth == 0)
       {
           //System.out.println(MoveCalculator.evaluationFucntion(node.data));
             node.displayNodeData(node);
            //System.out.println("");
            return node.data;
       }
       if (maximizingP == true){
           int bestValueMax = -9999999;
           for (int i = 0; i<node.children.size();i++){
               int val = minimax(node.children.get(i),depth-1,false);
               bestValueMax= Max(bestValueMax,val);
               //System.out.println("BestValueMax "+ bestValueMax);
           }
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