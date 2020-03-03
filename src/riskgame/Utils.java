package riskgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import riskgame.Agents.Player;

public class Utils {

    public static void printHeap(PriorityQueue<State> heap) {
        for (State state : heap) {
            System.out.print(state.getCost() + ",");
        }
        System.out.println("");
    }

    public ArrayList<Territory> initUSA() {
        ArrayList<Territory> ts = new ArrayList<>();
        ts.add(new Territory(1, new int[]{5, 2, 50}));
        ts.add(new Territory(2, new int[]{3, 4, 5}));
        ts.add(new Territory(3, new int[]{4, 2, 9, 49}));
        ts.add(new Territory(4, new int[]{2, 3, 5, 8, 9}));
        ts.add(new Territory(5, new int[]{1, 2, 4, 6, 7, 8}));
        ts.add(new Territory(6, new int[]{5, 7, 17, 16}));
        ts.add(new Territory(7, new int[]{5, 6, 8, 10, 15, 16}));
        ts.add(new Territory(8, new int[]{4, 5, 7, 9, 10}));
        ts.add(new Territory(9, new int[]{3, 4, 8, 11, 49}));
        ts.add(new Territory(10, new int[]{7, 8, 11, 15, 14, 13}));
        ts.add(new Territory(11, new int[]{9, 10, 13, 12}));
        ts.add(new Territory(12, new int[]{11, 13, 21, 22}));
        ts.add(new Territory(13, new int[]{10, 11, 12, 14, 20, 21}));
        ts.add(new Territory(14, new int[]{10, 13, 15, 20}));
        ts.add(new Territory(15, new int[]{7, 10, 14, 16, 19, 20}));
        ts.add(new Territory(16, new int[]{6, 7, 15, 18, 19}));
        ts.add(new Territory(17, new int[]{6, 16, 18}));
        ts.add(new Territory(18, new int[]{16, 17, 19, 34, 36}));
        ts.add(new Territory(19, new int[]{16, 15, 18, 20, 34, 33}));
        ts.add(new Territory(20, new int[]{15, 14, 13, 19, 21, 33, 32, 31}));
        ts.add(new Territory(21, new int[]{13, 12, 22, 20, 31, 23}));
        ts.add(new Territory(22, new int[]{12, 21, 23}));
        ts.add(new Territory(23, new int[]{21, 22, 24, 31}));
        ts.add(new Territory(24, new int[]{23, 25, 26, 31}));
        ts.add(new Territory(25, new int[]{24, 26}));
        ts.add(new Territory(26, new int[]{24, 25, 27, 31, 28}));
        ts.add(new Territory(27, new int[]{26, 28}));
        ts.add(new Territory(28, new int[]{26, 27, 29, 31}));
        ts.add(new Territory(29, new int[]{28, 31, 32, 30, 48}));
        ts.add(new Territory(30, new int[]{29, 48, 38, 37, 32}));
        ts.add(new Territory(31, new int[]{20, 21, 23, 24, 26, 28, 32, 29}));
        ts.add(new Territory(32, new int[]{20, 31, 29, 30, 37, 35, 33}));
        ts.add(new Territory(33, new int[]{19, 20, 32, 35, 34, 36}));
        ts.add(new Territory(34, new int[]{18, 19, 33, 36}));
        ts.add(new Territory(35, new int[]{32, 33, 36, 37}));
        ts.add(new Territory(36, new int[]{18, 34, 35, 37, 33}));
        ts.add(new Territory(37, new int[]{35, 36, 32, 30, 38}));
        ts.add(new Territory(38, new int[]{30, 37, 39, 48, 47, 46}));
        ts.add(new Territory(39, new int[]{38, 46, 45, 43, 40}));
        ts.add(new Territory(40, new int[]{39, 41, 43}));
        ts.add(new Territory(41, new int[]{40, 42, 43}));
        ts.add(new Territory(42, new int[]{41}));
        ts.add(new Territory(43, new int[]{40, 41, 39, 45, 44}));
        ts.add(new Territory(44, new int[]{43, 45}));
        ts.add(new Territory(45, new int[]{39, 44, 43}));
        ts.add(new Territory(46, new int[]{38, 39, 47}));
        ts.add(new Territory(47, new int[]{38, 46, 48}));
        ts.add(new Territory(48, new int[]{29, 30, 38, 47}));
        ts.add(new Territory(49, new int[]{3, 9}));
        ts.add(new Territory(50, new int[]{1}));
        return ts;
    }

    public void divideTerritoriesRandom(ArrayList<Player> p, ArrayList<Territory> territories) {
        HashSet<Integer> set = new HashSet<>();
        int nPerPlayer = territories.size() / p.size();
        for (int i = 0; i < nPerPlayer; i++) {
            for (Player player : p) {
                int rndm = (int) Math.round(Math.random() * (territories.size() - 1));
                while (set.contains(rndm)) {
                    rndm = (int) Math.round(Math.random() * (territories.size() - 1));
                }
                set.add(rndm);
                player.addTerritory(rndm+1);
            }
        }
        for (int i = 0; i < territories.size() - set.size(); i++) {
            int k = 0;
            int rndm = (int) Math.round(Math.random() * (territories.size() - 1));
            while (set.contains(rndm)) {
                rndm = (int) Math.round(Math.random() * (territories.size() - 1));
            }
            set.add(rndm);
            p.get(k).addTerritory(rndm+1);
            k = (k + 1) % p.size();
        }
        
        if(territories.size()-set.size()!=0){
            for (int i = 0; i < territories.size(); i++) {
                if(set.contains(i)){
                    continue;
                }
                 int rndm = (int) Math.round(Math.random() * (p.size() - 1));
                p.get(rndm).addTerritory(i+1);
            }
        }

    }

    public void divideTroops(State state) {
        ArrayList<Player> p = state.getPlayers();

        for (int i = 0; i < p.size(); i++) {
            int numberOfTroops = state.getTerritories().size();
            int numberOfTerritories = p.get(i).getTerritories().size();
            for (int num : p.get(i).getTerritories()) {
                state.getTerritories().get(num - 1).setNumberOfTroops(1);
                numberOfTroops--;
            }
            ArrayList<Integer> ts = new ArrayList<>(p.get(i).getTerritories());
            for (int j = 0; j < numberOfTroops; j++) {
                int rndm = (int) Math.round(Math.random() * (numberOfTerritories - 1));
                state.getTerritories().get(ts.get(rndm) - 1).setNumberOfTroops(state.getTerritories().get(ts.get(rndm) - 1).getNumberOfTroops() + 1);
//            System.out.println("number of troops "+ts.get(rndm));
            }

        }
    }

    public static void printState(State state) {
//        System.out.println("----------------------------------------------------------------------");
//        for (Player p : state.getPlayers()) {
//            System.out.println("Player: " + p.getTurn() + " *" + p.getClass().getSimpleName() + "*");
//            System.out.println("Territories:" + p.getTerritories());
//        }
//        for (Territory t : state.getTerritories()) {
//            System.out.print(t.getNumber() + " : " + t.getNumberOfTroops() + " troops || ");
//        }
//
//        System.out.println("----------------------------------------------------------------------");
    }

}
