package riskgame.Agents;

import riskgame.State;
import riskgame.Territory;

public class Passive extends Player {

    
    @Override
    public State play(State state) {

        if (state.getPlayer().getBonusTroops() > 0) {
            Territory territory = state.getTerritoryWithLowestTroops();
            State newState = (State) state.clone();
            territory.setNumberOfTroops(territory.getNumberOfTroops() + state.getPlayer().getBonusTroops());

            state.getPlayer().addTroops(40);
            
            return newState;
        }
        return state;
    }

}
