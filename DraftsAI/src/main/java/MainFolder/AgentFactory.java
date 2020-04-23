/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFolder;

import Checkers.CheckersAlphaBeta;
import Checkers.CheckersMinimax;
import Checkers.CheckersRandom;
import Connect4.Connect4AlphaBeta;
import Connect4.Connect4Minimax;
import Connect4.Connect4Random;
import Reversi.ReversiAlphaBeta;
import Reversi.ReversiMinimax;
import Reversi.ReversiRandom;

/**
 *
 * @author Ryan Kelly
 */
public class AgentFactory {

    public AgentFactory() {
    }
    
    public Agent getAgent(String agentName){
        
        if(agentName == null){
        return null;
        }
        if(agentName =="CheckersAlphaBeta"){
            return new CheckersAlphaBeta();
        }
        else if(agentName=="CheckersMinimax"){
            return new CheckersMinimax();
        }
        else if(agentName=="CheckersRandom"){
            return new CheckersRandom();
        }
        else if(agentName=="Connect4AlphaBeta"){
            return new Connect4AlphaBeta();
        }
        else if(agentName== "Connect4Minimax"){
            return new Connect4Minimax();
        }
        else if(agentName == "Connect4Random"){
            return new Connect4Random();
        }
        else if(agentName == "ReversiAlphaBeta"){
            return new ReversiAlphaBeta();
        }
        else if(agentName =="ReversiMinimax"){
            return new ReversiMinimax();
        }
        else if(agentName == "ReversiRandom"){
            return new ReversiRandom();
        }
        return null;
    }
}
