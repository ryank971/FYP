/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder;

import Checkers.Checkers;
import MainFolder.Board;
import Checkers.CheckersHuman;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan Kelly
 */
public class JUnitTest {

    CheckersHuman player = new CheckersHuman();
    Checkers checkerRules = new Checkers();

    //Testing a double jump going (+2,+2),(+2,-2)
    @Test
    public void testDoubleJumpV1() {
        Board game = new Board();
        game.board = new int[][]{
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 0, 0, 2, 0, 2},
            {2, 0, 2, 0, 2, 0, 2, 0}
        };
        String current = "2,3";
        String move = "6,3";

        assertTrue(player.makeMove(game, current, move));
        game.PrintGame();
    }

//    //Testing a double jump going (+2,+2),(+2,+2)
    @Test
    public void testDoubleJumpV2() {
        Board game = new Board();
        game.board = new int[][]{
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 0, 0, 2, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0}
        };
        String current = "2,3";
        String move = "6,7";
        assertTrue(player.makeMove(game, current, move));
        game.PrintGame();
    }

//    //Testing a double jump going (+2,+2),(+2,+2)
    @Test
    public void testDoubleJumpV3() {
        Board game = new Board();
        game.turn = 2;
        game.board = new int[][]{
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 0, 0, 2, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0}
        };
        String current = "5,6";
        String move = "1,2";
        assertTrue(player.makeMove(game, current, move));
    }
    
      
    //Testing a King Single Move going Back
    @Test
    public void testDoubleJumpV4() {
        Board game = new Board();
        game.board = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 2, 0, 1},
            {2, 0, 1, 0, 2, 0, 2, 0},
            {0, 1, 0, 1, 0, 2, 0, 2},
            {2, 0, 2, 0, 3, 0, 2, 0}
        };
        String current = "1,0";
        String move = "0,1";
        assertTrue(player.makeMove(game, current, move));
    }


    //Testing a King Single Move going Forward
        @Test
    public void testDoubleJumpV5() {
        Board game = new Board();
        game.board = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {4, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 1, 0, 1},
        {2, 0, 1, 0, 1, 0, 2, 0},
        {0, 1, 0, 0, 0, 2, 0, 2},
        {2, 0, 2, 0, 3, 0, 2, 0}
        };
        String current = "1,0";
        String move="0,1";
        assertTrue(player.makeMove(game, current, move));
    }
    
    
    
    
    
    @Test
    public void testEndGameScenario1(){
        Board game = new Board();
        game.board = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 2, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 2, 0, 0, 0, 2, 0, 2},
        {1, 0, 2, 0, 2, 0, 2, 0}
        };
        assertTrue(checkerRules.isOver(game));
    }
    
    @Test
    public void testEndGameScenario2(){
        Board game = new Board();
        game.board = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {3, 0, 0, 2, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 1, 0, 1},
        {3, 0, 0, 0, 3, 0, 0, 0}
        };
        assertFalse(checkerRules.isOver(game));
    }  
    
    
    
   

}
