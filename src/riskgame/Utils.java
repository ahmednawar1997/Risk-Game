package riskgame;

import java.util.ArrayList;
import java.util.Arrays;
import riskgame.Agents.Player;

public class Utils {

//    public ArrayList<Territory> initUSA2() {
//        ArrayList<Territory> territories = new ArrayList<>();
//
//        for (int i = 0; i < 50; i++) {
//            territories.add(new Territory(i + 1));
//        }
//
//        territories.get(0).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(4), territories.get(1), territories.get(49))));
//        territories.get(1).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(2), territories.get(3), territories.get(4))));
//        territories.get(2).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(3), territories.get(1), territories.get(8), territories.get(48))));
//        territories.get(3).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(1), territories.get(2), territories.get(4), territories.get(7), territories.get(8))));
//        territories.get(4).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(0), territories.get(1), territories.get(3), territories.get(5), territories.get(6), territories.get(7))));
//        territories.get(5).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(4), territories.get(6), territories.get(16), territories.get(15))));
//        territories.get(6).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(4), territories.get(5), territories.get(7), territories.get(7), territories.get(9), territories.get(14), territories.get(15))));
//        territories.get(7).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(3), territories.get(4), territories.get(6), territories.get(8), territories.get(9))));
//        territories.get(8).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(2), territories.get(3), territories.get(7), territories.get(10), territories.get(48))));
//        territories.get(9).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(6), territories.get(7), territories.get(10), territories.get(14), territories.get(13), territories.get(12))));
//        territories.get(10).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(8), territories.get(9), territories.get(12), territories.get(11))));
//        territories.get(11).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(10), territories.get(12), territories.get(20), territories.get(21))));
//        territories.get(12).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(9), territories.get(10), territories.get(11), territories.get(13), territories.get(19), territories.get(20))));
//        territories.get(13).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(9), territories.get(12), territories.get(14), territories.get(19))));
//        territories.get(14).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(6), territories.get(9), territories.get(13), territories.get(15), territories.get(18), territories.get(19))));
//        territories.get(15).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(5), territories.get(6), territories.get(14), territories.get(17), territories.get(18))));
//        territories.get(16).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(5), territories.get(15), territories.get(17))));
//        territories.get(17).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(15), territories.get(16), territories.get(18), territories.get(33), territories.get(35))));
//        territories.get(18).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(15), territories.get(14), territories.get(17), territories.get(19), territories.get(33), territories.get(32))));
//        territories.get(19).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(14), territories.get(13), territories.get(12), territories.get(18), territories.get(20), territories.get(32), territories.get(31), territories.get(30))));
//        territories.get(20).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(12), territories.get(11), territories.get(21), territories.get(19), territories.get(30), territories.get(22))));
//        territories.get(21).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(11), territories.get(20), territories.get(22))));
//        territories.get(22).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(20), territories.get(21), territories.get(23), territories.get(30))));
//        territories.get(23).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(22), territories.get(24), territories.get(25), territories.get(30))));
//        territories.get(24).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(23), territories.get(25))));
//        territories.get(25).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(23), territories.get(24), territories.get(26), territories.get(30), territories.get(27))));
//        territories.get(26).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(25), territories.get(27))));
//        territories.get(27).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(25), territories.get(26), territories.get(28), territories.get(30))));
//        territories.get(28).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(27), territories.get(30), territories.get(31), territories.get(29), territories.get(47))));
//        territories.get(29).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(28), territories.get(47), territories.get(37), territories.get(36), territories.get(31))));
//        territories.get(30).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(19), territories.get(20), territories.get(22), territories.get(23), territories.get(25), territories.get(27), territories.get(31), territories.get(28))));
//        territories.get(31).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(19), territories.get(30), territories.get(28), territories.get(29), territories.get(36), territories.get(34), territories.get(32))));
//        territories.get(32).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(18), territories.get(19), territories.get(31), territories.get(34), territories.get(33), territories.get(35))));
//        territories.get(33).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(17), territories.get(18), territories.get(32), territories.get(35))));
//        territories.get(34).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(31), territories.get(32), territories.get(35), territories.get(36))));
//        territories.get(35).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(17), territories.get(33), territories.get(34), territories.get(36), territories.get(32))));
//        territories.get(36).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(34), territories.get(35), territories.get(31), territories.get(29), territories.get(37))));
//        territories.get(37).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(29), territories.get(36), territories.get(38), territories.get(47), territories.get(46), territories.get(45))));
//        territories.get(38).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(37), territories.get(45), territories.get(44), territories.get(42), territories.get(39))));
//        territories.get(39).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(38), territories.get(40), territories.get(42))));
//        territories.get(40).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(39), territories.get(41), territories.get(42))));
//        territories.get(40).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(39), territories.get(41), territories.get(42))));
//        territories.get(41).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(40))));
//        territories.get(42).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(39), territories.get(40), territories.get(38), territories.get(44), territories.get(43))));
//        territories.get(43).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(42), territories.get(44))));
//        territories.get(44).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(38), territories.get(43), territories.get(42))));
//        territories.get(45).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(37), territories.get(38), territories.get(46))));
//        territories.get(46).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(37), territories.get(45), territories.get(47))));
//        territories.get(47).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(28), territories.get(29), territories.get(37), territories.get(46))));
//        territories.get(48).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(2), territories.get(8))));
//        territories.get(49).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(0))));
//
//        return territories;
//    }
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

    public void divideTerritoriesRandom(Player player1, Player player2, ArrayList<Territory> territories) {
        for (Territory t : territories) {
            if (Math.round(Math.random()) == 1) {
                player1.addTerritory(t);
//                t.setOwner(player1);
            } else {
                player2.addTerritory(t);
//                t.setOwner(player2);
            }
        }
    }

}
