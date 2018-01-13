/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Emil Poorando
 */
public class Battleship extends Application {
    /*
        Deklaration
    */
    private Stage stage;
    private PutShipsController objektPS = new PutShipsController();
    private StartMenuController objektST = new StartMenuController();
    private LevelOfDifficultyController  objektSL = new LevelOfDifficultyController();
    private Scene sceneMenu;
    private Scene levelScene;
    
    /*
        Windows
    */
    
    //Menu Fenster
    @Override
    public void start(Stage stage) throws Exception {    
        this.stage = new Stage();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StartMenu.fxml"));
        Parent root = loader.load();
        
        StartMenuController controller = loader.getController();
        controller.setMainApp(this);
        objektST = controller.getMe(); //Klasse bekannt machen
        
        this.sceneMenu = new Scene(root);
        
        this.stage.setScene(sceneMenu);
        this.stage.setResizable(false); // Benutzer kann die Grösse des Fensters nicht ändern
        this.stage.show();        
    }
    
    //Vorbereitungsfenster
    public void putShipsWindow() throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PutShips.fxml"));
        Parent root = loader.load();
        
        PutShipsController controller = loader.getController();
        controller.setMainApp(this);
        objektPS = controller.getMe(); //Klasse bekannt machen
        objektPS.createMap();
        
       
        
        
        Scene scene = new Scene(root);
        
        this.stage.setScene(scene);   
 
    }
    
    //Schwierigkeitsgrad Fenster
    public void levelOfDifficultyWindow() throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("levelOfDifficulty.fxml"));
        Parent root = loader.load();
        
        LevelOfDifficultyController controller = loader.getController();
        controller.setMainApp(this);
        objektSL = controller.getMe(); //Klasse bekannt machen

    
        this.levelScene = new Scene(root);
        
        this.stage.setScene(levelScene);   
 
    }
    
    public void goToMenu(){
      this.stage.setScene(sceneMenu);
    }
    public void goToLevel() throws IOException{
      levelOfDifficultyWindow();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
