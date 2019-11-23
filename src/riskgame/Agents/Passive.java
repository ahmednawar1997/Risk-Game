package riskgame.Agents;

import riskgame.State;
import riskgame.Territory;

public class Passive extends Player {

    public Passive(int turn) {
        super(turn);
    }

    
    @Override
    public State play(State state) {
        state.setPlayerTurn(this.getTurn());
        
        if (this.getBonusTroops() > 0) {
            State newState = (State) state.clone();
            Territory territory = newState.getPlayers().get(this.getTurn()).getTerritoryWithLowestTroops(newState);

            territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayers().get(this.getTurn()).getBonusTroops());
            return newState;
        }
        return state;
    }

}
