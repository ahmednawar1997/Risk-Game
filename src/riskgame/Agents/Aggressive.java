package riskgame.Agents;

import riskgame.State;
import riskgame.Territory;

public class Aggressive extends Player {

    @Override
    public State play(State state) {
        if (this.getBonusTroops() > 0) {
            State newState = (State) state.clone();
            Territory territory = newState.getPlayer().getTerritoryWithHighestTroops();
            territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayer().getBonusTroops());
            
            
            /*Attack*/
            
            
            
            return newState;
        }
        return state;
    }

}
