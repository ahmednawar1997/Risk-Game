package riskgame.Agents;

import java.util.ArrayList;
import riskgame.Heuristic;
import riskgame.State;
import riskgame.Territory;

public class Passive extends Player {

    
    @Override
    public State play(State state) {

        if (state.getPlayer().getBonusTroops() > 0) {
            Territory territory = state.getTerritoryWithLowestTroops();
            State newState = (State) state.clone();
            territory.setNumberOfTroops(territory.getNumberOfTroops() + state.getPlayer().getBonusTroops());

            return newState;
        }
        return state;
    }

}
