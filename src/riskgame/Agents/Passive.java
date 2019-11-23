package riskgame.Agents;

import riskgame.State;
import riskgame.Territory;

public class Passive extends Player {

    @Override
    public State play(State state) {

        if (this.getBonusTroops() > 0) {
            State newState = (State) state.clone();
            Territory territory = newState.getPlayer().getTerritoryWithLowestTroops();

            territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayer().getBonusTroops());
            return newState;
        }
        return state;
    }

}
