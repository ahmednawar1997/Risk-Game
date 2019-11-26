package riskgame.Agents;

import java.util.ArrayList;
import riskgame.State;
import riskgame.Territory;
import riskgame.Utils;

public class Pacifist extends Player {

    public Pacifist(int turn) {
        super(turn);
    }

    @Override
    public State play(State state) {
        if (state.getPlayers().get(this.getTurn()).getTerritories().isEmpty()) {
            return state;
        }
        state.setPlayerTurn(this.getTurn());
        State newState = (State) state.clone();
        Territory territory = newState.getPlayers().get(this.getTurn()).getTerritoryWithLowestTroops(newState);
        System.out.println("Pacifict: Placing " + newState.getPlayers().get(this.getTurn()).getBonusTroops() + " troops on " + territory.getNumber());
        territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayers().get(this.getTurn()).getBonusTroops());
        newState.getPlayers().get(this.getTurn()).setBonusTroops(0);

        int att[] = getLowestEnemyTer(newState);
        if (att[0] == -1) {
            return newState;
        }

        boolean conquered = false;
        State s = (State) newState.clone();
        territory = s.getTerritories().get(att[0] - 1);
        Territory enemyTerritory = s.getTerritories().get(att[1] - 1);
        while (!conquered) {
            if (territory.getNumberOfTroops() == 1) {
                break;
            }

            conquered = attack(territory, enemyTerritory, s);

            Utils.printState(s);
        }
        return s;

    }

    private int[] getLowestEnemyTer(State state) {
        ArrayList<Integer> myterty = new ArrayList<>(state.getPlayers().get(this.getTurn()).getTerritories());
        int t[] = {-1, -1};
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < myterty.size(); i++) {
            if (state.getTerritories().get(myterty.get(i) - 1).getNumberOfTroops() > 1) {
                ArrayList<Territory> att = getAttackableNeighbours(state.getTerritories().get(myterty.get(i) - 1), state);

                for (int j = 0; j < att.size(); j++) {
                    if (att.get(j).getNumberOfTroops() < min) {
                        min = att.get(j).getNumberOfTroops();
                        t[0] = myterty.get(i);
                        t[1] = att.get(j).getNumber();
                    }
                }
            }
        }
        return t;
    }

}
