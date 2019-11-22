package riskgame.Agents;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.State;
import riskgame.Territory;

public abstract class Player implements Cloneable {
    
    private String name;
    private int numberOfTroops;
    private ArrayList<Territory> territories;
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
    
    public void addTerritory(Territory territory){
        territories.add(territory);
    }
    
    public void addTroops(int troops) {
        this.numberOfTroops += troops;
    }
    
    public Object clone() {
        
        try {
            Player cloned = (Player) super.clone();
            cloned.setOpponent((Player) cloned.getOpponent().clone());
            cloned.setTerritories((ArrayList<Territory>) cloned.getTerritories().clone());

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public abstract State play(State state);
    
}
