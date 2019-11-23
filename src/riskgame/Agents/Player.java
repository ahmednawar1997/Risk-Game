package riskgame.Agents;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.State;
import riskgame.Territory;

public abstract class Player implements Cloneable {

    private String name;
    private int numberOfTroops = 50;
    private ArrayList<Territory> territories = new ArrayList<>();
    private Player opponent;
    private int bonusTroops;

    public Player() {
        this.bonusTroops = 0;
    }

    public int getBonusTroops() {
        return bonusTroops;
    }

    public void setBonusTroops(int bonusTroops) {
        this.bonusTroops = bonusTroops;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfTroops() {
        return numberOfTroops;
    }

    public void setNumberOfTroops(int numberOfTroops) {
        this.numberOfTroops = numberOfTroops;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }

    public void addTroops(int troops) {
        this.numberOfTroops += troops;
    }

    public Object clone(State state) {
        Player cloned = null;
        try {
            cloned = (Player) super.clone();
            ArrayList<Territory> ts = new ArrayList<>();
            ts = cloneArrayList(cloned.getTerritories(), state);
            cloned.setTerritories(ts);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;

    }

    private static ArrayList<Territory> cloneArrayList(ArrayList<Territory> territories, State state) {
        ArrayList<Territory> ts = new ArrayList<>();
        for (Territory t : territories) {
            ts.add(state.getTerritories().get(t.getNumber() - 1));
        }

        return ts;
    }

    public void divideTroopsRandom() {
        int numberOfTerritories = this.territories.size();
        for (int i = 0; i < numberOfTroops; i++) {
            int rndm = (int) Math.round(Math.random() * (numberOfTerritories + 1));
            System.out.println("random: " + rndm);
            territories.get(rndm).setNumberOfTroops(territories.get(rndm).getNumberOfTroops() + 1);
        }
    }

    public Territory getTerritoryWithLowestTroops() {
        int min = Integer.MAX_VALUE;
        Territory lowestTerritory = null;
        for (Territory territory : this.territories) {
            if (min == 0) {
                break;
            }
            if (territory.getNumberOfTroops() < min) {
                lowestTerritory = territory;
                min = territory.getNumberOfTroops();
            }
        }
        return lowestTerritory;
    }

    public Territory getTerritoryWithHighestTroops() {
        int max = Integer.MIN_VALUE;
        Territory highestTerritory = null;
        for (Territory territory : this.territories) {
            if (territory.getNumberOfTroops() > max) {
                highestTerritory = territory;
                max = territory.getNumberOfTroops();
            }
        }
        return highestTerritory;
    }

    public abstract State play(State state);

}
