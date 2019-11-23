package riskgame.Agents;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.State;
import riskgame.Territory;

public abstract class Player implements Cloneable {

    private int turn;
    private int numberOfTroops = 50;
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

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getNumberOfTroops() {
        return numberOfTroops;
    }

    public void setNumberOfTroops(int numberOfTroops) {
        this.numberOfTroops = numberOfTroops;
    }

    public HashSet<Integer> getTerritories() {
        return territories;
    }

    public void setTerritories(HashSet<Integer> territories) {
        this.territories = territories;
    }

    public void addTerritory(int territory) {
        territories.add(territory);
    }

    public void addTroops(int troops) {
        this.numberOfTroops += troops;
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

    public void divideTroopsRandom(State state) {
        int numberOfTerritories = this.territories.size();
        for (int i = 0; i < numberOfTroops; i++) {
            int rndm = (int) Math.round(Math.random() * (numberOfTerritories - 1));
            //territories.get(rndm).setNumberOfTroops(territories.get(rndm).getNumberOfTroops() + 1);
            if (territories.contains(rndm)) {
                state.getTerritories().get(rndm).setNumberOfTroops(state.getTerritories().get(rndm).getNumberOfTroops() + 1);
            } else {
                i--;
            }
        }
    }

    public Territory getTerritoryWithLowestTroops(State state) {
        int min = Integer.MAX_VALUE;
        Territory lowestTerritory = null;
        for (Integer territory : this.territories) {
            if (min == 0) {
                break;
            }
            if (state.getTerritories().get(territory).getNumberOfTroops() < min) {
                lowestTerritory = state.getTerritories().get(territory);
                min = state.getTerritories().get(territory).getNumberOfTroops();
            }
        }
        return lowestTerritory;
    }

    public Territory getTerritoryWithHighestTroops(State state) {
        int max = Integer.MIN_VALUE;
        Territory highestTerritory = null;
        for (Integer territory : this.territories) {
            if (state.getTerritories().get(territory).getNumberOfTroops() > max) {
                highestTerritory = state.getTerritories().get(territory);
                max = state.getTerritories().get(territory).getNumberOfTroops();
            }
        }
        return highestTerritory;
    }

    public abstract State play(State state);

}
