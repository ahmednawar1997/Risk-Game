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
        if (state.getPlayers().get(this.getTurn()).getTerritories().isEmpty()) {
            return state;
        }
        State newState = (State) state.clone();
        Territory territory = newState.getPlayers().get(this.getTurn()).getTerritoryWithHighestTroops(newState);/////////
        territory.setNumberOfTroops(territory.getNumberOfTroops() + newState.getPlayers().get(this.getTurn()).getBonusTroops());
        newState.getPlayers().get(this.getTurn()).setBonusTroops(0);

        /*Attack*/
        boolean conquered = false;

        Territory enemyTerritory = getTerritoryWithleastTroopsFromNeighbours(territory, newState);

        while (!conquered) {
            if (territory.getNumberOfTroops() == 1) {
                break;
            }


            conquered = attack(territory, enemyTerritory, newState);

        }
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

    public Territory getTerritoryWithleastTroopsFromNeighbours(Territory territory, State state) {
        int max = Integer.MAX_VALUE;

        ArrayList<Territory> attackableNeighbours = getAttackableNeighbours(territory, state);

        Territory highestTerritory = null;
        for (Territory t : attackableNeighbours) {
            if (t.getNumberOfTroops() < max) {
                highestTerritory = t;
                max = t.getNumberOfTroops();
            }
        }
        return highestTerritory;
    }

    public ArrayList<Territory> getAttackableNeighbours(Territory territory, State state) {
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

    protected boolean attack(Territory territory, Territory enemyTerritory, State newState) {
        Player attackingPlayer = newState.getPlayers().get(territory.getOwner(newState));
        Player defendingPlayer = newState.getPlayers().get(enemyTerritory.getOwner(newState));

        int attackingNumber = calculateAttackingNumber(territory);
        int defendingNumber = calculateDefendingNumber(enemyTerritory);

        System.out.println("Player " + attackingPlayer.getTurn() + " attacking");
        System.out.println("Troops Attacking: " + attackingNumber + " vs " + defendingNumber);
        System.out.println("Total Troops in Attacking: " + territory.getNumberOfTroops() + " vs " + enemyTerritory.getNumberOfTroops());
        System.out.println("Attacking " + territory.getNumber() + " on " + enemyTerritory.getNumber());

        if (isAttackingWon(attackingNumber, defendingNumber)) {
            enemyTerritory.setNumberOfTroops(enemyTerritory.getNumberOfTroops() - defendingNumber);

            System.out.println("attacking won");
            if (enemyTerritory.getNumberOfTroops() == 0) {
                System.out.println("Player " + attackingPlayer.getTurn() + " won " + enemyTerritory.getNumber() + " with Territory " + territory.getNumber());

                conquer(territory, enemyTerritory, attackingPlayer, defendingPlayer);
                return true;
            }

        } else {
            System.out.println("defending won");

            territory.setNumberOfTroops(territory.getNumberOfTroops() - attackingNumber);
        }

        return false;
    }

}
