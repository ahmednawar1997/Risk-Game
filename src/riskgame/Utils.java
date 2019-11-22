package riskgame;

import java.util.ArrayList;
import java.util.Arrays;
import riskgame.Agents.Player;

public class Utils {

    public ArrayList<Territory> initUSA() {
        ArrayList<Territory> territories = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            territories.add(new Territory(i + 1));
        }

        territories.get(0).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(4), territories.get(1), territories.get(49))));
        territories.get(1).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(2), territories.get(3), territories.get(4))));
        territories.get(2).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(3), territories.get(1), territories.get(8), territories.get(48))));
        territories.get(3).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(1), territories.get(2), territories.get(4), territories.get(7), territories.get(8))));
        territories.get(4).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(0), territories.get(1), territories.get(3), territories.get(5), territories.get(6), territories.get(7))));
        territories.get(5).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(4), territories.get(6), territories.get(16), territories.get(15))));
        territories.get(6).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(4), territories.get(5), territories.get(7), territories.get(7), territories.get(9), territories.get(14), territories.get(15))));
        territories.get(7).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(3), territories.get(4), territories.get(6), territories.get(8), territories.get(9))));
        territories.get(8).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(2), territories.get(3), territories.get(7), territories.get(10), territories.get(48))));
        territories.get(9).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(6), territories.get(7), territories.get(10), territories.get(14), territories.get(13), territories.get(12))));
        territories.get(10).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(8), territories.get(9), territories.get(12), territories.get(11))));
        territories.get(11).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(10), territories.get(12), territories.get(20), territories.get(21))));
        territories.get(12).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(9), territories.get(10), territories.get(11), territories.get(13), territories.get(19), territories.get(20))));
        territories.get(13).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(9), territories.get(12), territories.get(14), territories.get(19))));
        territories.get(14).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(6), territories.get(9), territories.get(13), territories.get(15), territories.get(18), territories.get(19))));
        territories.get(15).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(5), territories.get(6), territories.get(14), territories.get(17), territories.get(18))));
        territories.get(16).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(5), territories.get(15), territories.get(17))));
        territories.get(17).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(15), territories.get(16), territories.get(18), territories.get(33), territories.get(35))));
        territories.get(18).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(15), territories.get(14), territories.get(17), territories.get(19), territories.get(33), territories.get(32))));
        territories.get(19).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(14), territories.get(13), territories.get(12), territories.get(18), territories.get(20), territories.get(32), territories.get(31), territories.get(30))));
        territories.get(20).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(12), territories.get(11), territories.get(21), territories.get(19), territories.get(30), territories.get(22))));
        territories.get(21).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(11), territories.get(20), territories.get(22))));
        territories.get(22).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(20), territories.get(21), territories.get(23), territories.get(30))));
        territories.get(23).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(22), territories.get(24), territories.get(25), territories.get(30))));
        territories.get(24).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(23), territories.get(25))));
        territories.get(25).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(23), territories.get(24), territories.get(26), territories.get(30), territories.get(27))));
        territories.get(26).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(25), territories.get(27))));
        territories.get(27).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(25), territories.get(26), territories.get(28), territories.get(30))));
        territories.get(28).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(27), territories.get(30), territories.get(31), territories.get(29), territories.get(47))));
        territories.get(29).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(28), territories.get(47), territories.get(37), territories.get(36), territories.get(31))));
        territories.get(30).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(19), territories.get(20), territories.get(22), territories.get(23), territories.get(25), territories.get(27), territories.get(31), territories.get(28))));
        territories.get(31).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(19), territories.get(30), territories.get(28), territories.get(29), territories.get(36), territories.get(34), territories.get(32))));
        territories.get(32).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(18), territories.get(19), territories.get(31), territories.get(34), territories.get(33), territories.get(35))));
        territories.get(33).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(17), territories.get(18), territories.get(32), territories.get(35))));
        territories.get(34).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(31), territories.get(32), territories.get(35), territories.get(36))));
        territories.get(35).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(17), territories.get(33), territories.get(34), territories.get(36), territories.get(32))));
        territories.get(36).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(34), territories.get(35), territories.get(31), territories.get(29), territories.get(37))));
        territories.get(37).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(29), territories.get(36), territories.get(38), territories.get(47), territories.get(46), territories.get(45))));
        territories.get(38).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(37), territories.get(45), territories.get(44), territories.get(42), territories.get(39))));
        territories.get(39).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(38), territories.get(40), territories.get(42))));
        territories.get(40).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(39), territories.get(41), territories.get(42))));
        territories.get(40).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(39), territories.get(41), territories.get(42))));
        territories.get(41).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(40))));
        territories.get(42).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(39), territories.get(40), territories.get(38), territories.get(44), territories.get(43))));
        territories.get(43).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(42), territories.get(44))));
        territories.get(44).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(38), territories.get(43), territories.get(42))));
        territories.get(45).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(37), territories.get(38), territories.get(46))));
        territories.get(46).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(37), territories.get(45), territories.get(47))));
        territories.get(47).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(28), territories.get(29), territories.get(37), territories.get(46))));
        territories.get(48).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(2), territories.get(8))));
        territories.get(49).setNeighbours(new ArrayList<>(Arrays.asList(territories.get(0))));

        return territories;
    }

    public void divideTerritoriesRandom(Player player1, Player player2, ArrayList<Territory> territories) {
        for (Territory t : territories) {
            if (Math.round(Math.random()) == 1) {
                player1.addTerritory(t);
            } else {
                player2.addTerritory(t);
            }
        }
    }
}
