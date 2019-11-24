package riskgame.Agents;

import java.util.PriorityQueue;
import riskgame.State;

public class A_Star extends Player {

    private PriorityQueue<State> heap;
    public A_Star(int turn) {
        super(turn);
        this.heap = new PriorityQueue();
    }

    
    
    
    
    
    @Override
    public State play(State state) {
    }

}
