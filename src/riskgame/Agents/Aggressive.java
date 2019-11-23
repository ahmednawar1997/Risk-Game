package riskgame.Agents;

import java.util.ArrayList;
import riskgame.State;
import riskgame.Territory;

public class Aggressive extends Player {

    public Aggressive(int turn) {
        super(turn);
    }

    @Override
    public State play(State state) {
        if (this.getBonusTroops() > 0) {
            State newState = (State) state.clone();
            Territory territory = newState.getPlayers().get(this.getTurn()).getTerritoryWithHighestTroops(state);
            territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayers().get(this.getTurn()).getBonusTroops());

            /*Attack*/
            
            
            Territory enemyTerritory = getTerritoryWithHighestTroopsFromNeighbours(territory, state);
            attack(territory, enemyTerritory);

            return newState;
        }
        return state;
    }

    public Territory getTerritoryWithHighestTroopsFromNeighbours(Territory territory, State state) {
        int max = Integer.MIN_VALUE;

        ArrayList<Territory> attackableNeighbours = getAttackableNeighbours(territory, state);

        Territory highestTerritory = null;
        for (Territory t : attackableNeighbours) {
            if (t.getNumberOfTroops() > max) {
                highestTerritory = t;
                max = t.getNumberOfTroops();
            }
        }
        return highestTerritory;
    }

    private ArrayList<Territory> getAttackableNeighbours(Territory territory, State state) {
        int[] neighbours = territory.getNeighbours();
        ArrayList<Territory> attackableNeighbours = new ArrayList<>();
        for (int number : neighbours) {
            if (isEnemy(state.getTerritories().get(number - 1))) {
                attackableNeighbours.add(state.getTerritories().get(number - 1));
            }
        }
        return attackableNeighbours;
    }

    private boolean isEnemy(Territory t) {
        boolean enemy = true;
        for (int territory : this.getTerritories()) {
            if (t.getNumber() == territory) {
                enemy = false;
            }
        }
        return enemy;
    }

    private void attack(Territory territory, Territory enemyTerritory) {

        }
    }


