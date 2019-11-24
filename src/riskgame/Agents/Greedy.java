package riskgame.Agents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import riskgame.Territory;

public class Greedy extends Player {

    public Greedy(int turn) {
        super(turn);
    }

    @Override
    public State play(State state) {
        //1.cloning the state
        State newState = (State) state.clone();

        //2. Get all the player territories
        ArrayList<Integer> playerTerritories = new ArrayList<>(state.getPlayers().get(this.getTurn()).getTerritories(newState));

        //3.Attack each territory with all the attackable neighbours and return a cost assigned to each attacking pair
        ArrayList<Territory> attackableNeighbours = new ArrayList<>();

        PriorityQueue<State> frontier = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State stateq, State state2) {
                return 22;
            }
        });

        for (int number : playerTerritories) {
            Territory attackingTerritory = state.getTerritories().get(number - 1);
            attackableNeighbours = getAttackableNeighbours(attackingTerritory, newState);
            for (Territory enemyTerritory : attackableNeighbours) {
                attack(attackingTerritory, enemyTerritory, newState);

            }
        }
    }

    // ArrayList<Territory> attackableNeighbours= new ArrayList<>();
    // for (int number : playerTerritories) {
    //     attackableNeighbours.add();
    // }
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

    private void attack(Territory territory, Territory enemyTerritory,State newState){


    }
    private void attack(Territory territory, Territory enemyTerritory, int numberOfAttacking, State newState) {
        //System.out.println("Aggressive Attacking");
        //System.out.println("Troops Attacking: "+territory.getNumberOfTroops() + " : " + enemyTerritory.getNumberOfTroops());
        if (territory.getNumberOfTroops() > enemyTerritory.getNumberOfTroops()) {
            System.out.println("Aggressive won Territory " + enemyTerritory.getNumber() + " with Territory " + territory.getNumber());

            enemyTerritory.setNumberOfTroops(numberOfAttacking);
            territory.setNumberOfTroops(1);

            newState.getPlayers().get(territory.getOwner(newState)).getTerritories().add(enemyTerritory.getNumber());
            newState.getPlayers().get(enemyTerritory.getOwner(newState)).getTerritories().remove(enemyTerritory.getNumber());

        } else {
            System.out.println("Passive won Territory " + territory.getNumber() + " with Territory " + enemyTerritory.getNumber());

            territory.setNumberOfTroops(0);
            newState.getPlayers().get(enemyTerritory.getOwner(newState)).getTerritories().add(territory.getNumber());

            newState.getPlayers().get(territory.getOwner(newState)).getTerritories().remove(territory.getNumber());
        }
    }

}
