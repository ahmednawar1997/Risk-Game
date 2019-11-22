package riskgame;

import riskgame.Agents.Player;
import java.util.ArrayList;
import java.util.List;

public class Territory {

    private int number;
    private List<Territory> neighbours;
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

    public List<Territory> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Territory> neighbours) {
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

}
