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
            System.out.println("Passive turn:    ");
       
            State newState = (State) state.clone();
            newState.setPlayerTurn(1);
            if(RiskGame.isGameEnded(newState)){
                return newState;
            }
            Territory territory = newState.getPlayers().get(newState.getPlayerTurn()).getTerritoryWithLowestTroops(newState);
            System.out.println("Placing "+  newState.getPlayers().get(newState.getPlayerTurn()).getBonusTroops()+" troops on "+territory.getNumber());
            territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayers().get(newState.getPlayerTurn()).getBonusTroops());
            newState.getPlayers().get(newState.getPlayerTurn()).setBonusTroops(0);
            System.out.println("Ending Turn");
            return newState;
        
      
    }

}
