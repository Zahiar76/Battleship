/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Emil Poorando
 */
public class GameVScomputerController implements Initializable {
   private Battleship manager;
    private Player player = new Player();
    private Player computer = new Player();
    private Computer computerLogic;
    public static boolean playerTurn = false; 
    private static Alert  alert = new Alert(AlertType.INFORMATION);
    public static int winner;
    private double probabilityToHit;

    public double getProbabilityToHit() {
        return probabilityToHit;
    }

    public void setProbabilityToHit(double probabilityToHit) {
        this.probabilityToHit = probabilityToHit;
    }


    
    @FXML
    GridPane opponentMap = new GridPane();
    
    @FXML
    HBox opponentHBox = new HBox();
    
    @FXML
    VBox opponentVBox = new VBox();
    
    //Maps
    @FXML
    GridPane playerMap = new GridPane();
    
    @FXML
    HBox playerHBox = new HBox();
    
    @FXML
    VBox playerVBox = new VBox();
    
    /**
     * Initializes the controller class.
     */
    
    public void createMap(){
        Button[] putShips = {new Button(),new Button(), new Button(), new Button()};
        Label[] toSetLabels = {new Label(),new Label(),new Label(),new Label()};
        //computer.getMap().createMap(opponentMap, opponentVBox, opponentHBox,putShips, toSetLabels, player);
       playerMap.getChildren().clear();
       manager.getPlayer1().getMap().setMap(playerMap, playerVBox, playerHBox);
       computer.getMap().createMap(opponentMap, opponentVBox, opponentHBox,putShips, toSetLabels, computer);
       computer.getMap().setShipsComputer(computer);
       computerLogic =  new Computer(manager.getPlayer1(), computer);
       computerLogic.setDaemon(true);
       computerLogic.probabilityToHit = probabilityToHit;
        computerLogic.start();
        
//       opponentMap.addEventFilter(MouseEvent.MOUSE_PRESSED, (e) ->{
//         if(e.isPrimaryButtonDown()){ 
//            if(playerTurn  == false){
//                computerLogic.setAttack(true);
//            }
//         }
//         });

       
    }
    
    
    public static void winMessage(int whoHasWon){
    Platform.runLater(() -> {
        if(whoHasWon==1){
            alert.setTitle("Congratulations");
            alert.setHeaderText(null);
            alert.setContentText("You are the winner!");
            alert.showAndWait();            
        }else{
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Game over!");
            alert.showAndWait();   
           
        }
    });
    }

    
    @FXML
    private void showDestroyedShips(ActionEvent event){
       //manager.getPlayer1().findDestroyedShip();
        computer.findDestroyedShip();
    }
    @FXML
    public void create(ActionEvent event){
        computer.getMap().setShipsComputer(computer); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }   
        
    public void setMainApp(Battleship manager){
        this.manager = manager;
    }
    
    public GameVScomputerController getMe(){
        return this;
    }
    
    
}
