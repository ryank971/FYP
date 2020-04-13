///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
////package MainFolder;
//
//import static MainFolder.DraftsMain.BuildFromNode;
//import static MainFolder.DraftsMain.DEPTH_TO_BUILD;
//import static junit.framework.Assert.assertTrue;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Ryan Kelly
// */
//public class JUnitTest {
//    
//  
////    //Testing a double jump going (+2,+2),(+2,-2)
////    @Test
////    public void testDoubleJumpV1() {
////        Board game = new Board();
////        game.board = new int[][]{
////        {0, 1, 0, 1, 0, 1, 0, 1},
////        {1, 0, 1, 0, 1, 0, 1, 0},
////        {0, 1, 0, 1, 0, 1, 0, 1},
////        {0, 0, 0, 0, 2, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {2, 0, 2, 0, 2, 0, 2, 0},
////        {0, 2, 0, 0, 0, 2, 0, 2},
////        {2, 0, 2, 0, 2, 0, 2, 0}
////        };
////        int[] current = {2,3};
////        int[] move={6,3};
////        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)));
////        TreeNode Tree = new TreeNode(game.GetBoard());
////       
////        BuildFromNode(game,1,Tree);
////         Tree.displayNodeData(Tree);
////        Tree.traverse(Tree);
////        assertTrue(EvaluateMove.isValidMove(game,current,move));
////    }
////    
////    
////    //Testing a double jump going (+2,+2),(+2,+2)
////    @Test
////    public void testDoubleJumpV2() {
////        Board game = new Board();
////        game.board = new int[][]{
////        {0, 1, 0, 1, 0, 1, 0, 1},
////        {1, 0, 1, 0, 1, 0, 1, 0},
////        {0, 1, 0, 1, 0, 1, 0, 1},
////        {0, 0, 0, 0, 2, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {2, 0, 2, 0, 2, 0, 2, 0},
////        {0, 2, 0, 0, 0, 2, 0, 0},
////        {2, 0, 2, 0, 2, 0, 2, 0}
////        };
////        int[] current = {2,3};
////        int[] move={6,7};
////        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)));
////        assertTrue(EvaluateMove.isValidMove(game,current,move));
////    }
////    
////    
////    //Testing a double jump going (+2,+2),(+2,+2)
////    @Test
////    public void testDoubleJumpV3() {
////        Board game = new Board();
////        game.turn = 2;
////        game.board = new int[][]{
////        {0, 1, 0, 1, 0, 1, 0, 1},
////        {1, 0, 0, 0, 1, 0, 1, 0},
////        {0, 1, 0, 1, 0, 1, 0, 1},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 0, 0, 1, 0, 0},
////        {2, 0, 2, 0, 2, 0, 2, 0},
////        {0, 2, 0, 0, 0, 2, 0, 0},
////        {2, 0, 2, 0, 2, 0, 2, 0}
////        };
////        int[] current = {5,6};
////        int[] move={1,2};
////        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)));
////        assertTrue(EvaluateMove.isValidMove(game,current,move));
////    }
////    
////    
////    //Testing a double jump going (+2,+2),(+2,+2)
////    @Test
////    public void testDoubleJumpV4() {
////        Board game = new Board();
////        game.board = new int[][]{
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {4, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 1, 0, 0, 0, 1},
////        {0, 0, 0, 0, 1, 0, 1, 0},
////        {0, 1, 0, 0, 0, 2, 0, 1},
////        {2, 0, 1, 0, 2, 0, 2, 0},
////        {0, 1, 0, 1, 0, 2, 0, 2},
////        {2, 0, 2, 0, 3, 0, 2, 0}
////        };
//////        int[] current = {1,0};
//////        int[] move={0,1};
////        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)));
////        TreeNode Tree = new TreeNode(game.GetBoard());
////        BuildFromNode(game,DEPTH_TO_BUILD,Tree);
////        MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,1);             
////        String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
////        System.out.println("move choosen: "+move);
////        //assertTrue(EvaluateMove.isValidMove(game,current,move));
////    }
////    
////        @Test
////    public void testDoubleJumpV5() {
////        Board game = new Board();
////        game.board = new int[][]{
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {4, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 1, 0, 0, 0, 0},
////        {0, 0, 0, 0, 1, 0, 1, 0},
////        {0, 0, 0, 1, 0, 1, 0, 1},
////        {2, 0, 1, 0, 1, 0, 2, 0},
////        {0, 1, 0, 0, 0, 2, 0, 2},
////        {2, 0, 2, 0, 3, 0, 2, 0}
////        };
//////        int[] current = {1,0};
//////        int[] move={0,1};
////        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)));
////        TreeNode Tree = new TreeNode(game.GetBoard());
////        BuildFromNode(game,DEPTH_TO_BUILD,Tree);
////        MoveCalculator.minimax(Tree,DEPTH_TO_BUILD,true,DEPTH_TO_BUILD,1);             
////        String move = EvaluateMove.ReturnMoveString(game, MoveCalculator.MaxMove.data);
////        System.out.println("move choosen: "+move);
////        //assertTrue(EvaluateMove.isValidMove(game,current,move));
////    }
////    
////    
////    
////    
////    
////    @Test
////    public void testEndGameScenario1(){
////        Board game = new Board();
////        game.board = new int[][]{
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 0, 2, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {2, 0, 2, 0, 2, 0, 2, 0},
////        {0, 2, 0, 0, 0, 2, 0, 2},
////        {1, 0, 2, 0, 2, 0, 2, 0}
////        };
////        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)).size());
////        assertTrue(EvaluateMove.GameOver(game));
////    }
////    
////    @Test
////    public void testEndGameScenario2(){
////        Board game = new Board();
////        game.board = new int[][]{
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {3, 0, 0, 2, 0, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 0},
////        {0, 0, 0, 0, 0, 0, 0, 1},
////        {0, 0, 0, 0, 1, 0, 1, 0},
////        {0, 0, 0, 1, 0, 1, 0, 1},
////        {3, 0, 0, 0, 3, 0, 0, 0}
////        };
////        System.out.println("Possible moves : "+ MoveCalculator.listOfMoves(game, game.allGamePieces(game.turn)).size());
////        assertFalse(EvaluateMove.GameOver(game));
////    }  
//    
//    
//  
//    
//    
//    
//    
//}
