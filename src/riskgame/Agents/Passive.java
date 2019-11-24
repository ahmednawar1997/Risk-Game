package riskgame.Agents;

import riskgame.RiskGame;
import riskgame.State;
import riskgame.Territory;

public class Passive extends Player {

    public Passive(int turn) {
        super(turn);
    }

    @Override
    public State play(State state) {
        state.setPlayerTurn(this.getTurn());

        State newState = (State) state.clone();
        Territory territory = newState.getPlayers().get(this.getTurn()).getTerritoryWithLowestTroops(newState);
        System.out.println("Passive: Placing "+newState.getPlayers().get(this.getTurn()).getBonusTroops()+" troops on "+territory.getNumber());
        territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayers().get(this.getTurn()).getBonusTroops());
        newState.getPlayers().get(this.getTurn()).setBonusTroops(0);
//            Utils.printState(newState);

        return newState;

    }
}
