/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Emil Poorando
 */
public class Battleship extends Application {
    /*
        Deklaration
    */
    
    //HEY
    //THIS IS A BITGHUB TEST
    //Test SSL
    
    private Stage stage;
    private PutShipsController objektPS = new PutShipsController();
    private StartMenuController objektST = new StartMenuController();
    private LevelOfDifficultyController  objektSL = new LevelOfDifficultyController();
    private GameVScomputerController objektGame = new GameVScomputerController();
    private Scene sceneMenu;
    private Scene levelScene;
    private Scene gameScene;
    private int whoHasWon; // Player 1 or Player 2
    
    private Player player1 = new Player();
    private Player player2 = new Player();

       /*
    Getter und Setter
     */
   
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

 
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public PutShipsController getObjektPS() {
        return objektPS;
    }

    public void setObjektPS(PutShipsController objektPS) {
        this.objektPS = objektPS;
    }

    public StartMenuController getObjektST() {
        return objektST;
    }

    public void setObjektST(StartMenuController objektST) {
        this.objektST = objektST;
    }

    public LevelOfDifficultyController getObjektSL() {
        return objektSL;
    }

    public void setObjektSL(LevelOfDifficultyController objektSL) {
        this.objektSL = objektSL;
    }

    public GameVScomputerController getObjektGame() {
        return objektGame;
    }

 
    public void setObjektGame(GameVScomputerController objektGame) {
        this.objektGame = objektGame;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setSceneMenu(Scene sceneMenu) {
        this.sceneMenu = sceneMenu;
    }
    public Stage getStage() {    
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getSceneMenu() {
        return sceneMenu;
    }

    public Scene getLevelScene() {
        return levelScene;
    }

    public void setLevelScene(Scene levelScene) {
        this.levelScene = levelScene;
    }

    public int getWhoHasWon() {
        return whoHasWon;
    }

//    public void setWhoHasWon(int whoHasWon) {
//        this.whoHasWon = whoHasWon;
//        if(whoHasWon == 1){
//            objektGame.winMessage();
//        }
//    }

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
    
        //Schwierigkeitsgrad Fenster
    public void game() throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gameVScomputer.fxml"));
        Parent root = loader.load();
        
        GameVScomputerController controller = loader.getController();
        controller.setMainApp(this);
        objektGame = controller.getMe(); //Klasse bekannt machen
        objektGame.createMap();
    
        this.gameScene= new Scene(root);
        
        this.stage.setScene(gameScene);   
 
    }
    
    public void goToMenu(){
      this.stage.setScene(sceneMenu);
    }
    public void startGame() throws IOException{
        game();
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
