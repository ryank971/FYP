package MainFolder;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Kelly
 */
public class EvaluateMove {
    
     public static boolean isValidMove(Game game, int[] Current, int[] Move) {
//        System.out.println("TURN : "+Game.turn);
//           System.out.println("EVALUATING THE MOVE "+ Current[0]+","+Current[1]+ " too "+ Move[0]+","+Move[1]);
//        System.out.println("Current: "+board[Current[0]][Current[1]]);
//        System.out.println("Jump: "+board[Current[0] + 1][Current[1] - 1]);
//        System.out.println("Move: "+board[Move[0]][Move[1]]);
        
                
        if (Move[0] <= 8 && Move[1] <= 8) {
            //System.out.println("here1");
            if (game.board[Current[0]][Current[1]] == game.turn || game.board[Current[0]][Current[1]] == game.turn+2) {
                //System.out.println("here2");
                if (!(game.board[Move[0]][Move[1]] == game.turn)) {
                    //System.out.println("here3");
                    if ((Move[0] == Current[0] + 1 && (Move[1] == Current[1] + 1 || Move[1] == Current[1] - 1) && game.board[Move[0]][Move[1]] == 0) && game.turn ==1) {
                        return true;
                    }else if((Move[0] == Current[0] - 1 && (Move[1] == Current[1] + 1 || Move[1] == Current[1] - 1) && game.board[Move[0]][Move[1]] == 0) && game.turn ==2)
                        return true;
                    else if(((Move[0] == Current[0] + 1 || Move[0] == Current[0] -1) && (Move[1] == Current[1] + 1 || Move[1] == Current[1] - 1) && game.board[Move[0]][Move[1]] == 0) && game.board[Current[0]][Current[1]] == game.turn+2 )
                            return true;
                    else if(game.board[Current[0]][Current[1]]==game.turn){ 
                        if(isSingleJump(game,Current,Move) == true)
                        return   true;
                    }
                     if(isDoubleJump(game,Current,Move) == true)
                     return true;
//                    
                        if (game.board[Current[0]][Current[1]] == game.turn+2) {
                        //System.out.println("CHECK " +board[Current[0] + 1][Current[1] + 1]);
                        //int[] Opp = {Current[0]+1,Current[1]+1};
                        if (Current[0] != 7 && Current[1] != 7 && Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2 && game.board[Move[0]][Move[1]] ==0) {
                            if(game.board[Current[0]+1][Current[1]+1]!= game.turn && game.board[Current[0]+1][Current[1]+1]!= game.turn+2 && game.board[Current[0]+1][Current[1]+1]!= 0)
                            return true;
                        }
                        else if(Current[1] != 0 && Current[0] != 0 && Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2 && game.board[Move[0]][Move[1]] ==0){
                            if(game.board[Current[0]-1][Current[1]-1]!= game.turn && game.board[Current[0]-1][Current[1]-1]!= game.turn+2 && game.board[Current[0]-1][Current[1]-1]!= 0)
                            return true;
                        }
                        else if(Current[1] != 0 && Current[0] != 7 && Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2 && game.board[Move[0]][Move[1]] ==0){
                            if(game.board[Current[0]+1][Current[1]-1]!= game.turn && game.board[Current[0]+1][Current[1]-1]!= game.turn+2 && game.board[Current[0]+1][Current[1]-1]!= 0)
                            return true;
                        }
                        else if(Current[1] != 7 && Current[0] !=0 && Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2 && game.board[Move[0]][Move[1]] ==0){
                            if(game.board[Current[0]-1][Current[1]+1]!= game.turn && game.board[Current[0]-1][Current[1]+1]!= game.turn+2 && game.board[Current[0]-1][Current[1]+1]!= 0)
                            return true;
                        }
                        //5,4
                    }
                        
                    
  
                   
                }

            }

        }

        return false;

    }
     
    public static boolean isDoubleJump(Game game, int[] Current, int[] Move){
        //System.out.println("Orginal Move : "+Move[0]+","+Move[1]);
        for (int i = -2; i <= 2; i+=4) {
            for (int j = 2; j >= -2; j-=4) {
                 int[] newMove = {Move[0]-i,Move[1]+j};
                    if(isSingleJump(game,Current,newMove) == true)
                        {
            
                            //System.out.println("MADE IT HERE");
                            //System.out.println(game.turn);
                            if(isSingleJump(game,newMove,Move) == true)
                                return true;
                        }
                 //System.out.println("HERE: "+newMove[0]+","+newMove[1]);
            }
        }
        return false;
    }
    
    
    
    public static int[] ReturnDoubleJump(Game game, int[] Current, int[] Move){
        //System.out.println("Orginal Move : "+Move[0]+","+Move[1]);
        for (int i = -2; i <= 2; i+=4) {
            for (int j = 2; j >= -2; j-=4) {
                 int[] newMove = {Move[0]-i,Move[1]+j};
                    if(isSingleJump(game,Current,newMove) == true)
                        {
            
                            //System.out.println("MADE IT HERE");
                            //System.out.println(game.turn);
                            if(isSingleJump(game,newMove,Move) == true)
                                return newMove;
                        }
                 //System.out.println("HERE: "+newMove[0]+","+newMove[1]);
            }
        }
         return null;

    }
        
    public static void MakeDoubleJump(Game game, int[] Current, int[] Move){
            int turn = game.turn;
            int[] middleMove =ReturnDoubleJump(game, Current, Move);
            makeMove(game,Current[0]+","+Current[1],middleMove[0]+","+middleMove[1]);
            game.turn = turn;
            makeMove(game,middleMove[0]+","+middleMove[1],Move[0]+","+Move[1]);
    }
        
    
    public static boolean isSingleJump(Game game, int[] Current, int[] Move){
         //from 7,6 TO 6,7
         
                 if (Move[0] < 8 && Move[1] < 8 && Move[0]>= 0 && Move[1]>=0) {
            //System.out.println("here1");

                //System.out.println("here2");
                if (!(game.board[Move[0]][Move[1]] == game.turn)) {
                    //System.out.println("here3");
                    

                            
                            
                     if (game.turn ==1 && Current[1] != 0 && Current[0] != 7 &&(game.board[Current[0] + 1][Current[1] - 1] != 0 && game.board[Current[0] + 1][Current[1] - 1] != 1 && game.board[Current[0] + 1][Current[1] - 1] != 3)) {
                        //System.out.println("hereee");
                        //System.out.println(turn);
                        if (Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2  && game.board[Move[0]][Move[1]] ==0) {
                            //take this line of code out as this is a verify function
                            //board[Current[0] + 1][Current[1] - 1] = 0;
                            return true;
                        }
                    }  
                    if (game.turn ==1 && Current[0] != 7 && Current[1] != 7 && (game.board[Current[0] + 1][Current[1] + 1] != 0 && game.board[Current[0] + 1][Current[1] + 1] != 1 && game.board[Current[0] + 1][Current[1] + 1] != 3)) {
                        //System.out.println("CHECK " +board[Current[0] + 1][Current[1] + 1]);
                        //int[] Opp = {Current[0]+1,Current[1]+1};
                        if (Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2 && game.board[Move[0]][Move[1]] ==0) {
                            //take this line of code out as this is a verify function
                            //board[Current[0] + 1][Current[1] + 1] = 0;
                            return true;
                        }//else System.out.println("failing here");
                        //5,4
                    }
                    
                    


                       
                    else if (game.turn ==2 && Current[1] != 7 && Current[0] !=0 &&  (game.board[Current[0] - 1][Current[1] + 1] != 0 && game.board[Current[0] - 1][Current[1] + 1] != 2 && game.board[Current[0] - 1][Current[1] + 1] != 4)) {
                        //System.out.println("CHECK " +GetBoard()[Current[0] + 1][Current[1] + 1]);
                       //System.out.println("here4");
                        //int[] Opp = {Current[0]+1,Current[1]+1};
                        if (Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2  && game.board[Move[0]][Move[1]] ==0) {
                            //take this line of code out as this is a verify function
                            //board[Current[0] - 1][Current[1] + 1] = 0;
                            return true;
                        }//else System.out.println("failing heree");

                    }
                    if (game.turn ==2 && Current[1] != 0 && Current[0] != 0 &&(game.board[Current[0] - 1][Current[1] - 1] != 0 && game.board[Current[0] - 1][Current[1] - 1] != 2 && game.board[Current[0] - 1][Current[1] - 1] != 4)) {
                        //System.out.println("in here");
                        if (Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2  && game.board[Move[0]][Move[1]] ==0) {
                            //take this line of code out as this is a verify function
                            //board[Current[0] - 1][Current[1] - 1] = 0;
                            return true;
                        }//else System.out.println("failed in here");
                    }
                }
            }
                 
        
        return false;
    }
    
    public static void makeMove(Game game, String current, String move) {
        String[] CurrentS = current.split(",");
        String[] MoveS = move.split(",");

        int size = CurrentS.length;
        int[] Current = new int[size];
        int[] Move = new int[size];
        for (int i = 0; i < size; i++) {
            Current[i] = Integer.parseInt(CurrentS[i]);
            Move[i] = Integer.parseInt(MoveS[i]);

        }
        
        
        
        if (isValidMove(game, Current, Move) == true) {
            if(isDoubleJump(game, Current, Move)){  
                MakeDoubleJump(game, Current, Move);  
                
            }
            else{
            
                if (Move[0] == Current[0] + 2 && Move[1] == Current[1] + 2) {
                    game.board[Current[0] + 1][Current[1] + 1] = 0;
                }
                else if (Move[0] == Current[0] + 2 && Move[1] == Current[1] - 2) {
                   game.board[Current[0] + 1][Current[1] - 1] = 0;
                }
                else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] + 2) {
                    game.board[Current[0] - 1][Current[1] + 1] = 0;
                }
                else if (Move[0] == Current[0] - 2 && Move[1] == Current[1] - 2) {
                    game.board[Current[0] - 1][Current[1] - 1] = 0;
                }



                if(game.turn == 1)
                {    
                    if(Move[0]== 7)
                    {
                    game.board[Move[0]][Move[1]] = game.turn+2;
                    game.board[Current[0]][Current[1]] = 0;
                    }
                    else
                    {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                    }
                }
                else if(game.turn == 2)
                {
                    if(Move[0] == 0){
                    game.board[Move[0]][Move[1]] = game.turn+2;
                    game.board[Current[0]][Current[1]] = 0;
                    }
                    else
                    {
                    game.board[Move[0]][Move[1]] = game.board[Current[0]][Current[1]];
                    game.board[Current[0]][Current[1]] = 0;
                    }

                }




                if(game.turn==1) {
                    game.turn = 2;
                } else {
                    game.turn = 1;
                }
            }
        }

    }
    
    public static String ReturnMoveString(Game game, int[][] move){
        String Move = "";
        for (int row = 0; row < game.GetBoard().length; row++) {
           for (int col = 0; col < game.GetBoard()[row].length; col++) {
              if((game.GetBoard()[row][col] ==game.turn ||game.GetBoard()[row][col] ==game.turn+2) && move[row][col] ==0)
                 Move+=row+","+col+"-";

           }
        }
        
         for (int row = 0; row < game.GetBoard().length; row++) {
           for (int col = 0; col < game.GetBoard()[row].length; col++) {
                if(game.GetBoard()[row][col] ==0 &&(move[row][col] ==game.turn ||move[row][col] ==game.turn+2))
                return Move+=row+","+col;

           }
        }
        
        
        

        
        
        return Move;
    }
    

    public static Boolean GameOver(Game game) {
        Boolean gameOver = true;
           for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if(game.board[r][c] == game.turn || game.board[r][c] == game.turn+2)
                    {
                        //System.out.println("GAME OVER");
                        gameOver = false;
                    }
                    
                }
            }
           
          // System.out.println("Number Of Moves : "+MoveCalculator.listOfMoves(game, game.allPieces(game.turn)).size());
           if(MoveCalculator.listOfMoves(game, game.allPieces(game.turn)).size() ==0)
           {
               System.out.println("GAME OVER");
                gameOver= true;
           }
        
        return gameOver;
    }
    
    
        public static Boolean win(int [][] board,int turn) {
        Boolean win = true;
           for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if(board[r][c] != turn && board[r][c] != turn+2 && board[r][c] != 0)
                    {
                        win = false;
                    }
                    
                }
            }
           
        return win;
    }
        
        public static Boolean lose(int [][] board,int turn) {
        Boolean lose = true;
           for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if(board[r][c] == turn || board[r][c] == turn+2)
                    {
                        //System.out.println("GAME OVER");
                        lose = false;
                    }
                    
                }
            }
           
        return lose;
    }
    
    
}
