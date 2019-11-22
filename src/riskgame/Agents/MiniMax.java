package riskgame.Agents;

import riskgame.State;
import riskgame.Agents.Player;


public class MiniMax extends Player {

    @Override
    public void play(State state) {
        max(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    int max(State state, int alpha, int beta) {
        if(state.getPlayer().getTerritories().size()==0){
        
        }
        return 0;
    }
}
