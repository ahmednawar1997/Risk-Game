package riskgame.Agents;

import java.util.ArrayList;
import riskgame.State;
import riskgame.Territory;

public abstract class Player{

    private String name;
    private int numberOfTroops;
    private int numberOfTerritories;
    private ArrayList<Territory> territories;
    private Player opponent;
    private int bonusTroops;

    public Player() {
        this.bonusTroops = 0;
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

    public int getNumberOfTerritories() {
        return numberOfTerritories;
    }

    public void setNumberOfTerritories(int numberOfTerritories) {
        this.numberOfTerritories = numberOfTerritories;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

    public void addTroops(int troops) {
        this.numberOfTroops += troops;
    }

    public Territory getTerritoryWithLowestTroops() {
        int min = 10000;
        Territory lowestTerritory = null;
        for (Territory territory : this.territories) {
            if (min == 1) {
                break;
            }
            if (territory.getNumberOfTroops() < min) {
                lowestTerritory = territory;
                min = territory.getNumberOfTroops();
            }
        }
        return lowestTerritory;
    }
    
    public abstract void play(State state);

}
