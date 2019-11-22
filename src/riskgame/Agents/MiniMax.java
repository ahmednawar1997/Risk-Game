package riskgame.Agents;

import java.util.ArrayList;
import riskgame.Heuristic;
import riskgame.State;
import riskgame.Territory;

public class MiniMax extends Player {

    @Override
    public State play(State state) {
        max(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    int max(State state, int alpha, int beta) {

        if (state.getPlayer().getTerritories().size() == 0 || state.getPlayer().getOpponent().getTerritories().size() == 0) {
            return Heuristic.evaluateUtility(state);
        }

        if (state.getPlayer().getBonusTroops() > 0) {//assign troops

            for (Territory t : state.getTerritories()) {
                placeTroops(t, state.getPlayer());
            }

        }
    
    
    

    private State placeTroops(Territory territory, Player player) {

        
        territory.setNumberOfTroops(territory.getNumberOfTroops() + player.getBonusTroops());
        

    }
}
