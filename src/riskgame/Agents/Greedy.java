package riskgame.Agents;

import java.util.ArrayList;
import java.util.PriorityQueue;
import riskgame.Heuristic;
import riskgame.State;
import riskgame.StateActions;
import riskgame.Territory;

public class Greedy extends Player {

    private PriorityQueue<State> heap = new PriorityQueue<>();
    private int exploredCount = 0;
    private boolean isEndTurn = false;
    private boolean aggressive;

    public Greedy(int turn, boolean aggressive) {
        super(turn);
        this.heap = new PriorityQueue<>();
        this.aggressive = aggressive;

    }

    @Override
    public State play(State state) {
        state.setPlayerTurn(this.getTurn());
        if (state.getPlayers().get(state.getPlayerTurn()).getTerritories().size() <= 1) {
            return state;
        }
        state = getBestPlacement(state);

        while (true) {
            System.out.println("Greedy Thinking...");
            StateActions actions = getBestAttack(state);
            if (actions.getAttackingTerritory() == -1) {
                System.out.println("Greedy Ended Turn");
                return state;
            }

            Territory attackingTerritory = state.getTerritories().get(actions.getAttackingTerritory() - 1);
            Territory defendingTerritory = state.getTerritories().get(actions.getDefendingTerritory() - 1);
            state = attackUntilConquer(attackingTerritory, defendingTerritory, state);
        }

    }

    private State attackUntilConquer(Territory attackingTerritory, Territory defendingTerritory, State root) {
        System.out.println("Greedy Attacking");
        boolean conquered = false;
        while (!conquered) {
            if (attackingTerritory.getNumberOfTroops() == 1) {
                break;
            }
            conquered = attack(attackingTerritory, defendingTerritory, root);
        }
        return root;

    }

    private StateActions getBestAttack(State state) {
        int bestAttackableEnemy = -1;
        int bestAttackerTerritory = -1;
        double bestHeuristic = Heuristic.calculateHeuristic(state.getPlayers().get(state.getPlayerTurn()), state);
        for (int number : state.getPlayers().get(state.getPlayerTurn()).getTerritories()) {
            Territory territory = state.getTerritories().get(number - 1);
            ArrayList<Territory> attackableNeighbours = state.getPlayers().get(state.getPlayerTurn()).getAttackableNeighbours(territory, state);

            for (Territory enemyNeigbour : attackableNeighbours) {
                if (territory.getNumberOfTroops() >= 2) {

//                    double Hn = getStochasticHeuristicAfterAttack(territory, enemyNeigbour, state);
                    double Hn = aggressive ? getHeuristicAfterWin(territory, enemyNeigbour, state) : getStochasticHeuristicAfterAttack(territory, enemyNeigbour, state);

                    if (Hn < bestHeuristic) {
                        bestAttackableEnemy = enemyNeigbour.getNumber();
                        bestAttackerTerritory = territory.getNumber();
                        bestHeuristic = Hn;
                        System.out.println("Found best attack");
                    }
                }
            }
        }
        return new StateActions(bestAttackerTerritory, bestAttackableEnemy);
    }

    private State getBestPlacement(State state) {
        double max = Double.MIN_VALUE;
        Territory territory = null;
        for (int number : state.getPlayers().get(state.getPlayerTurn()).getTerritories()) {

            double m = Heuristic.BSR(state.getTerritories().get(number - 1), state);

            if (m > max) {
                territory = state.getTerritories().get(number - 1);
                max = m;
            }
        }
        State temp = (State) state.clone();
        System.out.println("Placing " + temp.getPlayers().get(temp.getPlayerTurn()).getBonusTroops() + " troops on " + territory.getNumber());
        return placeTroops(territory.getNumber(), temp);
    }

    private double getStochasticHeuristicAfterAttack(Territory atckTerritory, Territory dfndTerritory, State state) {

        double stochasticHeuristic;
        State winState = getStateAfterWinAttack(atckTerritory, dfndTerritory, state);
        State loseState = getStateAfterLoseAttack(atckTerritory, dfndTerritory, state);

        double Hwin = Heuristic.calculateHeuristic(winState.getPlayers().get(winState.getPlayerTurn()), winState);
        double Hlose = Heuristic.calculateHeuristic(loseState.getPlayers().get(loseState.getPlayerTurn()), loseState);

        double weightedWin = ((Hwin * atckTerritory.getNumberOfTroops()) / (atckTerritory.getNumberOfTroops() + dfndTerritory.getNumberOfTroops()));
        double weightedLose = ((Hlose * dfndTerritory.getNumberOfTroops()) / (atckTerritory.getNumberOfTroops() + dfndTerritory.getNumberOfTroops()));

        stochasticHeuristic = weightedWin + weightedLose;

        return stochasticHeuristic;
    }

    private double getHeuristicAfterWin(Territory atckTerritory, Territory dfndTerritory, State state) {
        State winState = getStateAfterWinAttack(atckTerritory, dfndTerritory, state);

        return Heuristic.calculateHeuristic(winState.getPlayers().get(winState.getPlayerTurn()), winState) * 0.98;
    }

    protected boolean isAttackingWonAboveThreshhold(Territory attackingTerritory, Territory defendingTerritory, double aggroThreshhold) {
        if (attackingTerritory.getNumberOfTroops() * 1.0 / defendingTerritory.getNumberOfTroops() * 1.0 > aggroThreshhold) {
            return true;
        }
        return false;
    }

    private State getStateAfterWinAttack(Territory atckTerritory, Territory dfndTerritory, State state) {
        State winState = (State) state.clone();
        Territory territory = winState.getTerritories().get(atckTerritory.getNumber() - 1);
        Territory enemyTerritory = winState.getTerritories().get(dfndTerritory.getNumber() - 1);
        Player attackingPlayer = winState.getPlayers().get(winState.getPlayerTurn());
        Player defendingPlayer = winState.getPlayers().get(enemyTerritory.getOwner(winState));

        enemyTerritory.setNumberOfTroops(territory.getNumberOfTroops() - 1);
        territory.setNumberOfTroops(1);
        attackingPlayer.getTerritories().add(enemyTerritory.getNumber());
        defendingPlayer.getTerritories().remove(enemyTerritory.getNumber());
        return winState;
    }

    private State getStateAfterLoseAttack(Territory atckTerritory, Territory dfndTerritory, State state) {
        State loseState = (State) state.clone();
        Territory territory = loseState.getTerritories().get(atckTerritory.getNumber() - 1);
        Territory enemyTerritory = loseState.getTerritories().get(dfndTerritory.getNumber() - 1);
        Player attackingPlayer = loseState.getPlayers().get(loseState.getPlayerTurn());
        Player defendingPlayer = loseState.getPlayers().get(enemyTerritory.getOwner(loseState));

        territory.setNumberOfTroops(1);
        return loseState;
    }
}
