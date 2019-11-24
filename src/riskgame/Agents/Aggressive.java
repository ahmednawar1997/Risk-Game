package riskgame.Agents;

import java.util.ArrayList;
import riskgame.State;
import riskgame.Territory;
import riskgame.Utils;

public class Aggressive extends Player {

    public Aggressive(int turn) {
        super(turn);
    }

    @Override
    public State play(State state) {
//        state.setPlayerTurn(this.getTurn());

        State newState = (State) state.clone();
        Territory territory = newState.getPlayers().get(this.getTurn()).getTerritoryWithHighestTroops(newState);/////////
        territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayers().get(this.getTurn()).getBonusTroops());
        newState.getPlayers().get(this.getTurn()).setBonusTroops(0);

        /*Attack*/
        Territory enemyTerritory = getTerritoryWithHighestTroopsFromNeighbours(territory, newState);
        attack(territory, enemyTerritory, territory.getNumberOfTroops() - 1, newState);

        Utils.printState(newState);
        return newState;

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

    private void attack(Territory territory, Territory enemyTerritory, int numberOfAttacking, State newState) {
        Player attackingPlayer = newState.getPlayers().get(territory.getOwner(newState));
        Player defendingPlayer = newState.getPlayers().get(enemyTerritory.getOwner(newState));
        System.out.println("Player " + attackingPlayer.getTurn() + " attacking");
        System.out.println("Troops Attacking: " + territory.getNumberOfTroops() + " vs " + enemyTerritory.getNumberOfTroops());
        if (territory.getNumberOfTroops() > enemyTerritory.getNumberOfTroops()) {
            System.out.println("Player " + attackingPlayer.getTurn() + " won " + enemyTerritory.getNumber() + " with Territory " + territory.getNumber());

            enemyTerritory.setNumberOfTroops(numberOfAttacking);
            territory.setNumberOfTroops(1);

            attackingPlayer.getTerritories().add(enemyTerritory.getNumber());
            defendingPlayer.getTerritories().remove(enemyTerritory.getNumber());

        } else {
            System.out.println("Player " + defendingPlayer.getTurn() + " won " + territory.getNumber() + " with Territory " + enemyTerritory.getNumber());

            territory.setNumberOfTroops(0);
            newState.getPlayers().get(enemyTerritory.getOwner(newState)).getTerritories().add(territory.getNumber());

            newState.getPlayers().get(territory.getOwner(newState)).getTerritories().remove(territory.getNumber());
        }
    }
}
