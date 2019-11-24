package riskgame.Agents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.State;
import riskgame.Territory;

public abstract class Player implements Cloneable {

    private int turn;
//    private int numberOfTroops = 50;
    private HashSet<Integer> territories = new HashSet<>();
    private int bonusTroops;

    public Player(int turn) {
        this.bonusTroops = 0;
        this.turn = turn;
    }

    public int getBonusTroops() {
        return bonusTroops;
    }

    public void setBonusTroops(int bonusTroops) {
        this.bonusTroops = bonusTroops;
    }

    public void addBonusTroops() {
        bonusTroops += territories.size() / 3;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

//
//    public void setNumberOfTroops(int numberOfTroops) {
//        this.numberOfTroops = numberOfTroops;
//    }
    public HashSet<Integer> getTerritories() {
        return territories;
    }

    public void setTerritories(HashSet<Integer> territories) {
        this.territories = territories;
    }

    public void addTerritory(int territory) {
        territories.add(territory);
    }

    public Object clone(State state) {
        Player cloned = null;
        try {
            cloned = (Player) super.clone();
            cloned.setTerritories(cloneHashSet(cloned.getTerritories()));
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;

    }

    private static HashSet<Integer> cloneHashSet(HashSet<Integer> territories) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer t : territories) {
            set.add(t);
        }
        return set;
    }

    public void divideTroopsRandom(State state, int numberOfTroops) {
        int numberOfTerritories = this.territories.size();
        for (int num : this.territories) {
            state.getTerritories().get(num - 1).setNumberOfTroops(1);
            numberOfTroops--;
        }
        ArrayList<Integer> ts = new ArrayList<>(this.getTerritories());
        for (int i = 0; i < numberOfTroops; i++) {
            int rndm = (int) Math.round(Math.random() * (numberOfTerritories - 1));
            state.getTerritories().get(ts.get(rndm) - 1).setNumberOfTroops(state.getTerritories().get(ts.get(rndm) - 1).getNumberOfTroops() + 1);
//            System.out.println("number of troops "+ts.get(rndm));
        }
    }

    public Territory getTerritoryWithLowestTroops(State state) {
        int min = Integer.MAX_VALUE;
        Territory lowestTerritory = null;
        for (Integer territory : this.territories) {
            if (min == 0) {
                break;
            }
            if (state.getTerritories().get(territory - 1).getNumberOfTroops() < min) {
                lowestTerritory = state.getTerritories().get(territory - 1);
                min = state.getTerritories().get(territory - 1).getNumberOfTroops();
            }
        }
        return lowestTerritory;
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

    public Territory getTerritoryWithHighestTroops(State state) {
        int max = Integer.MIN_VALUE;
        Territory highestTerritory = null;
        for (Integer territory : this.territories) {
            if (state.getTerritories().get(territory - 1).getNumberOfTroops() > max && getAttackableNeighbours(state.getTerritories().get(territory - 1), state).size() > 0) {
                highestTerritory = state.getTerritories().get(territory - 1);
                max = state.getTerritories().get(territory - 1).getNumberOfTroops();
            }
        }
        return highestTerritory;
    }

    public int getNumberOfTroops(State state) {
        int troops = 0;
        for (int i : this.getTerritories()) {
            troops += state.getTerritories().get(i - 1).getNumberOfTroops();
        }
        return troops;
    }

    public abstract State play(State state);

}
