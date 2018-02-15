/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Emil Poorando
 */
public class LevelOfDifficultyController implements Initializable {
    
    Battleship manager;
    
    @FXML
    private VBox menuPane = new VBox();
    
    @FXML
    private VBox mainPane = new VBox();
    
    @FXML
    private DialogPane dialogPane = new DialogPane();
    
    @FXML
    private StackPane stackPane = new StackPane();
    
    //test
    
    @FXML
    private void showMenu(){
        
        dialogPane.setDisable(false);
        dialogPane.setVisible(true);
        mainPane.setDisable(true);
        mainPane.setOpacity(0.55);

        
    }
    
    @FXML
    private void closeMenu(){
        mainPane.setOpacity(100);
        mainPane.setDisable(false);
        
        dialogPane.setVisible(false);
        dialogPane.setDisable(true);
        
    }
    
    //
    @FXML
    private void startGame(ActionEvent event) throws IOException{
        if(event.getSource().toString().contains("level1")){
            manager.startGame(20.0);
        }else if(event.getSource().toString().contains("level2")){
            manager.startGame(25.0);
        }else if(event.getSource().toString().contains("level3")){
            manager.startGame(30.0);
        }else if(event.getSource().toString().contains("level4")){
            manager.startGame(35.0);
        }else if(event.getSource().toString().contains("level5")){
            manager.startGame(40.0);
        }
 
    }
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stackPane.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ESCAPE == event.getCode()){
                System.out.println("Works1");
                showMenu();
           
            }
            
        });
        dialogPane.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ESCAPE == event.getCode()){
                System.out.println("Work2");
                closeMenu();
           
            }
            
        });
    }   
    
    public void setMainApp(Battleship manager){
        this.manager = manager;
    }
    
    public LevelOfDifficultyController getMe(){
        return this;
    }
    
}