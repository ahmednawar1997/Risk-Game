package riskgame;

import riskgame.Agents.Player;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Territory implements Cloneable {

    private int number;
//    private ArrayList<Territory> neighbours;
    private int[] neighbours;
    private int numberOfTroops;

    public Territory(int number, int[] neighbours) {
        this.number = number;
        this.neighbours = neighbours;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(int[] neighbours) {
        this.neighbours = neighbours;
    }

    public int getNumberOfTroops() {
        return numberOfTroops;
    }

    public void setNumberOfTroops(int numberOfTroops) {
        this.numberOfTroops = numberOfTroops;
    }

//    public Player getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Player owner) {
//        this.owner = owner;
//    }
    public Object clone() {
        Territory cloned = null;
        try {
            cloned = (Territory) super.clone();
//            cloned.setOwner((Player) cloned.getOwner().clone());
            cloned.setNeighbours(cloned.getNeighbours().clone());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;

    }

}
