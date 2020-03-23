package MainFolder;


import java.util.ArrayList;
import java.util.Arrays;
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
static TreeNode MaxMove;
static String MaxMoveString;
static String MinMoveString;

static TreeNode MinMove;

    
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
    
        public static List listOfMovesFromPosition(Game game,List<String> list,String current) {
        List<String> validMoves = new ArrayList<String>(); 
            //System.out.println("Current "+list.get(i));
             String[] CurrentS = current.split(",");
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
                       validMoves.add(current+"-"+r+","+c);
                       //System.out.println(r+","+c +" is a valid move from "+ Current[0]+","+Current[1]);
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
    
    public static double evaluationFucntion(int[][]board){
        double playerOne=0;
        double playerTwo=0;
        for (int[] x : board) {
                        for (int y : x) {
                            if(y==1)
                            playerOne++;
                            else if(y==3)
                            playerOne+=1.5;
                            else if(y==2)
                            playerTwo++;
                            else if(y==4)
                            playerTwo+=1.5;
                        }
        }
//        System.out.println("1 has a score of "+ playerOne);
//        System.out.println("2 has a score of "+ playerTwo);
//        System.out.println("________________________________________");
        return playerOne - playerTwo;
    }
    
    
    public static  double minimax(TreeNode node,int depth, Boolean maximizingP,int maxDepth, int turn){
//       System.out.println("*******************************************************************************************************************************");
//       System.out.println("Recursive call No."+ counter);
//       counter++;
//            System.out.println("Depth : "+depth);
            //System.out.println("here");
//        node.displayNodeData(node);
//        System.out.println(turn);
        
        
       if(EvaluateMove.win(node.data,turn) == true){
           //System.out.println("sees win");
           return 999999;
       }
       else if(EvaluateMove.lose(node.data,turn) == true){
           //System.out.println("sees lose");
           return -99999;
       }
        
        
       else if(depth == 0)
       {
           //System.out.println(MoveCalculator.evaluationFucntion(node.data));
            //node.displayNodeData(node);
            //System.out.println("");
           //System.out.println("here");
           return MoveCalculator.evaluationFucntion(node.data);
            
       }



       
       if (maximizingP == true){
           double bestValueMax = -9999999;
           for (int i = 0; i<node.children.size();i++){
               double val = minimax(node.children.get(i),depth-1,false,maxDepth,turn);
//               System.out.println("--Child--");
//               node.children.get(i).displayNodeData(node.children.get(i));
               //System.out.println(val);
               if(val > bestValueMax)
               {
                   bestValueMax = val;
                   if (depth ==maxDepth){
                      //System.out.println("here");
                      MaxMove = node.children.get(i);
                       //MoveCalculator.MaxMove.displayNodeData( MoveCalculator.MaxMove);
                   }

                   //move.displayNodeData(move);
               }

                  // System.out.println("At Depth "+ depth + " Val = "+ val + " IS LESS THAN = " + bestValueMax);

               //System.out.println("BestValueMax "+ bestValueMax);
           }

           return bestValueMax;
       }
       else if (maximizingP == false){
           double bestValueMin = 9999999;
           for (int i = 0; i<node.children.size();i++){
               double val = minimax(node.children.get(i),depth-1,true,maxDepth,turn);
               //bestValueMin = Min(bestValueMin,val);]
               if (val < bestValueMin)
               {
                    bestValueMin = val;
                    if(depth == maxDepth)
                    {
                        MinMove = node.children.get(i);
                    }
                     //move = node.getChildren().get(i);
                     //move.displayNodeData(move);
               }
           }

           return bestValueMin;
        }
                   
       
        else return 99999999;
       
           
    }
    
    
    public static  double alphaBeta(TreeNode node,int depth, Boolean maximizingP,int maxDepth, int turn,double alpha,double beta){

       if(EvaluateMove.win(node.data,turn) == true){
           return 999999;
       }
       else if(EvaluateMove.lose(node.data,turn) == true){
           return -99999;
       }
        
       else if(depth == 0)
       {
           return MoveCalculator.evaluationFucntion(node.data);     
       }
       
       if (maximizingP == true){
           double bestValueMax = -9999999;
           for (int i = 0; i<node.children.size();i++){
               double val = minimax(node.children.get(i),depth-1,false,maxDepth,turn);
               if(val > bestValueMax)
               {
                   bestValueMax = val;
                   if (depth ==maxDepth){
                      MaxMove = node.children.get(i);
                   }
               }
               alpha = max(alpha,val);
               if(beta <= alpha)
                   break;
           }

           return bestValueMax;
       }
       else if (maximizingP == false){
           double bestValueMin = 9999999;
           for (int i = 0; i<node.children.size();i++){
               double val = minimax(node.children.get(i),depth-1,true,maxDepth,turn);
               if (val < bestValueMin)
               {
                    bestValueMin = val;
                    if(depth == maxDepth)
                    {
                        MinMove = node.children.get(i);
                    }
               }
               beta = min(beta,val);
               if(beta <= alpha)
                    break;
           }

           return bestValueMin;
        }
        else return 99999999;
       
           
    }
    
    
    
    
       public static  double minimaxTreeGenerationIntegrated(Game game,int depth, Boolean maximizingP,int maxDepth, int turn,double alpha,double beta){

       if(EvaluateMove.win(game.GetBoard(),turn) == true){
           return 999999;
       }
       else if(EvaluateMove.lose(game.GetBoard(),turn) == true){
           return -99999;
       }
        
       else if(depth == 0)
       {
           return MoveCalculator.evaluationFucntion(game.GetBoard());     
       }
       List<String> validMoves  = MoveCalculator.listOfMoves(game, game.allPieces(game.turn));
       if (maximizingP == true){
           double bestValueMax = -9999999;
           
           for (int i = 0; i< validMoves.size() ;i++){
               
               Game Temp = BuildFromNode(game,validMoves.get(i));
               double val = minimaxTreeGenerationIntegrated(Temp,depth-1,false,maxDepth,turn,alpha,beta);
               if(val > bestValueMax)
               {
                   bestValueMax = val;
                   if (depth ==maxDepth){
                      MaxMoveString = validMoves.get(i);
                   }
               }
               alpha = max(alpha,bestValueMax);
               if(beta <= alpha)
                   break;
           }

           return bestValueMax;
       }
       else if (maximizingP == false){
           double bestValueMin = 9999999;
           for (int i = 0; i<validMoves.size();i++){
               
               Game Temp = BuildFromNode(game,validMoves.get(i));
               double val = minimaxTreeGenerationIntegrated(Temp,depth-1,true,maxDepth,turn,alpha,beta);
               if (val < bestValueMin)
               {
                    bestValueMin = val;
                    if(depth == maxDepth)
                    {
                        MinMoveString = validMoves.get(i);
                    }
               }
               beta = min(beta,bestValueMin);
               if(beta <= alpha)
                    break;
           }

           return bestValueMin;
        }
        else return 99999999;
       
           
    }
       
       
    
    
    static public double  max(double one,double two)
    {
        if(one > two)
        return one;
        else return two;
    }
    
    static public double  min(double one,double two)
    {
        if(one < two)
        return one;
        else return two;
    }
    
    
    
    
        public static Game BuildFromNode(Game game,String move){
                int[][] copyBoard = cloneBoard(game.board);
                Game CopyGame = new Game(copyBoard,game.turn);
                    int[][] temp = cloneBoard(CopyGame.board);
                    Game Temp = new Game(temp,game.turn);
                    String moves[] = move.split("-");
                    EvaluateMove.makeMove(Temp,moves[0] ,moves[1]);  
                    return Temp;
    }
    
 
    
    public static int[][] cloneBoard(int[][]board)
    {
     
                final int[][] result = new int[board.length][];
    for (int i = 0; i < board.length; i++) {
        result[i] = Arrays.copyOf(board[i], board[i].length);
    }
    return result;
    }
    
    
}