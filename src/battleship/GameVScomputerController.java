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
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

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
    
    @FXML
    Rectangle playerTurn = new Rectangle();
    @FXML 
    Rectangle opponentTurn = new Rectangle();

    @FXML
    Text playerTurnText = new Text();
    
    @FXML
    Text opponentTurnText = new Text();
    
    @FXML
    AnchorPane winnerBox = new AnchorPane();
    @FXML
    VBox gamePane = new VBox();
    @FXML
    AnchorPane winnerStage = new AnchorPane();
    @FXML
    Label titleLabel = new Label();
    @FXML
    Label winnerText = new Label();
    @FXML
    Button backToMenuBtn = new Button();
    @FXML
    Button playAgainBtn = new Button();
    
    
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
        turnAnimation();
       
    }
    @FXML
    private void turnAnimation(){
        
        
        FillTransition ft = new FillTransition(Duration.seconds(10), playerTurn,Color.WHITE, Color.web("001f3f"));
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);
        ft.setRate(8.0);
 
        FillTransition ft2 = new FillTransition(Duration.seconds(10), opponentTurn,Color.WHITE, Color.web("001f3f"));
        ft2.setCycleCount(Animation.INDEFINITE);
        ft2.setAutoReverse(true);
        ft2.setRate(8.0);
        Thread turn = new Thread(){
            @Override
            public synchronized void run(){

            while(winner==0){
              if(computer.getMap().isTurn()){
              ft.jumpTo(Duration.INDEFINITE);    
              ft.stop();
              ft2.play();
              }else{
               ft2.jumpTo(Duration.INDEFINITE);      
               ft2.stop();
               ft.play();
              }
            }
            if(winner == 1 || winner == 2){
               ft2.stop();
               ft.stop();
                try {
                    computerLogic.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameVScomputerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    if(winner == 2){

                        titleLabel.setText("Game Over");
                        winnerText.setText("You have lost! :C");
                    }else{
                        titleLabel.setText("Congratulations");
                        winnerText.setText("You have won! :D");                   
                    }
                    gamePane.setDisable(true);
                    gamePane.setOpacity(0.75d);
                    winnerStage.setDisable(false);
                    winnerStage.setVisible(true);
                    winner = 0;
                });
                
                
               
               
            }
            
            }
        };
        turn.setDaemon(true);
        turn.start();
    }
    
    @FXML
    private void winMessage(int whoHasWon){
    Platform.runLater(() -> {
        if(whoHasWon==1){
            //winnerStage.setDisable(false);
        }else{
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Game over!");
            alert.showAndWait();   
           
        }
    });
    }
    
    @FXML
    private void goToMenu(ActionEvent event){
      manager.goToMenu();
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
