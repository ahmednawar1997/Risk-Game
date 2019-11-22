package riskgame;

import riskgame.Agents.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Territory implements Cloneable {

    private int number;
    private ArrayList<Territory> neighbours;
    private int numberOfTroops;
    private Player owner;

    public Territory(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Territory> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Territory> neighbours) {
        this.neighbours = neighbours;
    }

    public int getNumberOfTroops() {
        return numberOfTroops;
    }

    public void setNumberOfTroops(int numberOfTroops) {
        this.numberOfTroops = numberOfTroops;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Object clone() {

        try {
            Territory cloned = (Territory) super.clone();
            cloned.setOwner((Player) cloned.getOwner().clone());
            cloned.setNeighbours((ArrayList<Territory>) cloned.getNeighbours().clone());
            
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
