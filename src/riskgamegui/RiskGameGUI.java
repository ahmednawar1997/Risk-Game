package riskgamegui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
//import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import riskgame.Agents.A_Star;
import riskgame.Agents.Aggressive;

import riskgame.Agents.MiniMax;
import riskgame.Agents.Pacifist;
import riskgame.Agents.Passive;
import riskgame.Agents.*;
import riskgame.Agents.Player;
import riskgame.Controller;
import riskgame.RiskGame;
import riskgame.State;
import riskgame.Territory;
import riskgame.Utils;

public class RiskGameGUI extends Application {

    State s;
    Scene scene;
    
  
    
    
    
    Text numBonus = new Text("Bonus Troops: 23");
    Text space = new Text("\n\n");
    Text info = new Text("Select Teritory to place..");
    Button endturn = new Button("End Turn");
   public static boolean turnEnd = false;
    
    public final Text clicked = new Text();
    Text text;
    public static final ObservableList names
            = FXCollections.observableArrayList();
    static final ObservableList data
            = FXCollections.observableArrayList();
    Text Details = new Text();
    ListView listView;

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        SVGPath svgPath = new SVGPath();
        Group statGroup = new Group();
        scene = new Scene(root, 1400, 1000);
        text = new Text("Turn Number: 0");
        listView = new ListView(data);

        listView.setEditable(true);

        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));

        Details.setText("Hii");
        Group detail = new Group();
        detail.getChildren().add(Details);
        detail.setLayoutX(500);
        detail.setLayoutY(100);
        text.setFill(Color.RED);
        statGroup.getChildren().add(text);

        listView.setLayoutY(100);
        listView.setLayoutX(1100);
        statGroup.setLayoutY(900);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.BLUEVIOLET);

        for (String path : Map.getMap()) {
            svgPath = new SVGPath();
            svgPath.setContent(path);
            // svgPath.setP;
            svgPath.setFill(Color.BISQUE);

            root.getChildren().add(svgPath);
        }

        root.scaleXProperty().add(100);
        root.getChildren().add(statGroup);
        statGroup.setLayoutY(900);
        root.getChildren().add(listView);
        SVGPath svg = (SVGPath) root.getChildren().get(48);
        svg.setFill(Color.BLACK);
        Singleton sin = Singleton.getInstance();
        sin.setS(scene);
        root.getChildren().add(detail);
        RiskGame game = new RiskGame();
       
        primaryStage.setTitle("Risk");
        primaryStage.setScene(scene);
        primaryStage.show();
        Thread t = new Thread() {
            @Override
            public void run() {
                initGame();
            }

        };
        t.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(RiskGameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  System.out.println("dsaasdasddasdasdsads");
        // initGame();
        for (int i = 0; i < 50; i++) {
            final int indx = s.getTerritories().get(i).getNumber();
            SVGPath ss = (SVGPath) root.getChildren().get(i);

            final double x = (ss.getBoundsInParent().getMaxX() + ss.getBoundsInParent().getMinX()) / 2;
            final double y = (ss.getBoundsInParent().getMaxY() + ss.getBoundsInParent().getMinY()) / 2;

            root.getChildren().get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    String teritoryDetails = getTeritoryDetails(indx);
                    Details.setText(teritoryDetails);
                    root.getChildren().remove(text);
                    root.getChildren().add(text);
                }
            });

            root.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    clicked.setText(String.valueOf(indx));
                    // System.out.println(indx);
                }
            });
        }

    }

    public static void main(String[] args) {

        System.out.println("asdasddasdasdasdasdasf");
        launch(args);

        // new RiskGameGUI().initGame();
    }
    static ArrayList<Color> colors = new ArrayList<>();

    public boolean initGame() {
        Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
       // Player player1 = new Pacifist(0);
         Player player2;
       
        Player player1 = new Aggressive(0);
         player2 = new Greedy(1,true);
        //Player player3 = new Pacifist(2);
        //Player player4 = new Aggressive(3);
        
        //Player player3 = new Aggressive(2);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
       // players.add(player3);
        //players.add(player4);
        utils.divideTerritoriesRandom(players, usaTerritories);

        State state = new State(usaTerritories, players, 0, this);
        utils.divideTroops(state);

        Utils.printState(state);
        s = state;
        Recolor(state);

        startGame(state);
        return true;
    }

    private void startGame(State state) {
        int turns = 0;
        while (true) {
            turns++;

            for (int i = 0; i < state.getPlayers().size(); i++) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RiskGameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                s = state;
                Utils.printState(state);
                Recolor(state);
                if (isGameEnded(state)) {
                    Recolor(state);
                    System.out.println("Turns = " + turns);
                    return;
                }
                Group g = (Group) scene.getRoot();
                Group f = (Group) g.getChildren().get(50);
                RiskGameGUI.turnEnd=false;
                // data.add("Turn has passed");

                updateTurn("\tTurn Number: " + turns + "\n\tTurn of: " + state.getPlayers().get(i).getClass().getSimpleName());
                state.setPlayerTurn(i);
                state.getPlayers().get(i).addBonusTroops();
                state = state.getPlayers().get(i).play(state);
                System.out.println("*************************************************************");
            }
        }

    }

    public boolean isGameEnded(State state) {
        int numOfSurviving = 0;
        for (Player player : state.getPlayers()) {

            if (!player.getTerritories().isEmpty()) {

                numOfSurviving++;

            }
        }
        if (numOfSurviving == 1) {
            updateList("Game Over");
            return true;
        }
        return false;
    }

    public static void printState(State temp) {
//        ArrayList<Integer> s = new ArrayList<>(temp.getPlayers().get(0).getTerritories());
//        System.out.println("MinMax ownes: ");
//        for (int i = 0; i < s.size(); i++) {
//            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
//        }
//        System.out.println("");
//
//        s = new ArrayList<>(temp.getPlayers().get(1).getTerritories());
//        System.out.println("Pssive ownnes: ");
//        for (int i = 0; i < s.size(); i++) {
//            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
//        }
//        System.out.println("");
    }

    public void Recolor(State state) {
        Group root = (Group) scene.getRoot();

        ArrayList<Player> p = state.getPlayers();
        for (int i = 0; i < p.size(); i++) {

            ArrayList<Integer> t = new ArrayList<>(p.get(i).getTerritories());
            for (int j = 0; j < t.size(); j++) {
                SVGPath svg = (SVGPath) root.getChildren().get(t.get(j) - 1);
                svg.setFill(colors.get(i));
                svg.setStroke(Color.BLACK);
            }
        }
    }

    public String getTeritoryDetails(int i) {
        //System.out.println("sadsdsad    "+i);
        int t = i;
        String details = "Territory number: " + t + "\nNumber of Troops: " + s.getTerritories().get(t - 1).getNumberOfTroops();
        return details;
    }

    public void updateList(String s) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                data.add(s);
            }
        });
    }

    public void updateTurn(String s) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                text.setText(s);
            }
        });
    }

    public void updateInfo(String string) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                info.setText(string);
            }
        });
    }
}
