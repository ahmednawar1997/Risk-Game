package riskgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.Agents.Player;

public class Territory implements Cloneable {

    private int number;
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

    public Object clone(State state) {
        Territory cloned = null;
        try {
            cloned = (Territory) super.clone();
            cloned.setNeighbours(cloned.getNeighbours().clone());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;
    }

    public int getOwner(State state) {
        for (Player p : state.getPlayers()) {
            if (p.getTerritories().contains(number)) {
                return p.getTurn();
            }
        }
        System.out.println("Cannot find territory:" + number);
        return -1;
    }

}