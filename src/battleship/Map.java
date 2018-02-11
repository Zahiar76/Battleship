/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;

/**
 *
 * @author Emil Poorando
 */
public class Map{
    Battleship manager;
    int groesse = 10; //Grösse von Map
    Field[][] map = new Field[groesse][groesse]; //Zwei Dimensionale Array, auf der Schiffe eingesetzt werden  1 = Y 2 = X
    int whichShip = 0;  // Was für ein Schiff wird eingesetzt
    ArrayList<Field> list = new ArrayList<Field>();
    int[] index = new int[4];
    public static final AtomicInteger count = new AtomicInteger(1); //unique index
    private  int shipID; // ID
    private int numberOfShips = 20; // For Game
    private boolean turn = true;
    /*Man weisst, wieviele Boxe eines Schiffes schon eingesetzt wurden*/
    ArrayList<int[][]> allShipsPlayer = new ArrayList<int[][]>(){{
      add(new int[4+1][1+1]);
      add(new int[3+1][2+1]);
      add(new int[2+1][3+1]);
      add(new int[1+1][4+1]);
    }};
    ArrayList<int[][]> allShipsComputer = new ArrayList<int[][]>(){{
      add(new int[4+1][1+1]);
      add(new int[3+1][2+1]);
      add(new int[2+1][3+1]);
      add(new int[1+1][4+1]);
    }};
    
    ArrayList<ArrayList<Integer>> allShipsTest = new ArrayList<ArrayList<Integer>>(){{
      add(new ArrayList<Integer>());
      add(new ArrayList<Integer>());
      add(new ArrayList<Integer>());
      add(new ArrayList<Integer>());
    }};


    public int getWhichShip() {
        return whichShip;
    }

    public void setWhichShip(int whichShip) {
        this.whichShip = whichShip;
    }

    public Field[][] getMap() {
        return map;
    }

    public void setMap(Field[][] map) {
        this.map = map;
    }
    public int getShipID() {
        return shipID;
    }
    //ID wird gesetzt
    public void setShipID(int y, int x) {
        this.shipID = map[y][x].getIDShip();
    }   

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void setNumberOfShips(int numberOfShips) {
        this.numberOfShips = numberOfShips;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

         

    
    //Map wird dynamisch erstellt
    public void createMap(GridPane mapGui,VBox vbox, HBox hbox,Button[] putShips ,Label[] toSetLabels,Player player){
        ActionEvent event = new ActionEvent();
        int position = 0;
        mapGui.getColumnConstraints().clear(); //Alle Spalten werden gelöscht
        mapGui.getRowConstraints().clear();    //Alle Zeilen werden gelöscht

        //Felder werden in eine Karte hinzugefügt 
        for(Field[] yAxis: map){
            for(Field xAxis : yAxis){
              //Button wird mit entsprechenden Informationen erstellt
              xAxis = new Field(mapGui,groesse, position);
              position ++;
              //Felder bekommen automatisch eine Methode 
              setMethod(player, xAxis, putShips , toSetLabels);
             map[xAxis.getY()][xAxis.getX()] = xAxis; //Zum Map zuweisen
             mapGui.add(map[xAxis.getY()][xAxis.getX()].getBtn(),xAxis.getX(),xAxis.getY()); //Buttons werden in ein GridLayout hinzugefügt und somit graphisch dargestellt 
            }
        }        
        //Beschriftung der Karte wird erstellt. ABDC und 1234 
        setNumbers(mapGui,vbox,hbox);    
    }
    //Map wird für ein Spiel eingesetzt 
    public void setMap(GridPane mapGui, VBox vbox, HBox hbox){
       ActionEvent event = new ActionEvent();
       int position = 0;
       mapGui.getColumnConstraints().clear(); //Alle Spalten werden gelöscht
       mapGui.getRowConstraints().clear();    //Alle Zeilen werden gelöscht
       
       
        //Felder werden in eine Karte hinzugefügt 
        for(Field[] yAxis: map){
            for(Field xAxis : yAxis){
              //Button wird mit entsprechenden Informationen erstellt
              xAxis = new Field(mapGui,groesse, position);
              position ++;
              
             //Methode         
             mapGui.add(map[xAxis.getY()][xAxis.getX()].getBtn(),xAxis.getX(),xAxis.getY()); //Buttons werden in ein GridLayout hinzugefügt und somit graphisch dargestellt
             
            }
        }        
        //Beschriftung der Karte wird erstellt. ABDC und 1234 
        vbox.getChildren().clear();
        hbox.getChildren().clear();
        setNumbers(mapGui,vbox,hbox);   
    }
    
     //For Computer
     public void createMap(GridPane mapGui,Button[] putShips ,Label[] toSetLabels,Player player){
         ActionEvent event = new ActionEvent();
         int position = 0;
         mapGui.getColumnConstraints().clear(); //Alle Spalten werden gelöscht
         mapGui.getRowConstraints().clear();    //Alle Zeilen werden gelöscht
 
         //Felder werden in eine Karte hinzugefügt 
         for(Field[] yAxis: map){
             for(Field xAxis : yAxis){
               //Button wird mit entsprechenden Informationen erstellt
               xAxis = new Field(mapGui,groesse, position);
               position ++;
               //Felder bekommen automatisch eine Methode 
               setMethod(player, xAxis);
              map[xAxis.getY()][xAxis.getX()] = xAxis; //Zum Map zuweisen
              mapGui.add(map[xAxis.getY()][xAxis.getX()].getBtn(),xAxis.getX(),xAxis.getY()); //Buttons werden in ein GridLayout hinzugefügt und somit graphisch dargestellt 
             }
         }        
   
     }
     
     //CREATING MAP FOR COMP
     public void setShipsComputer(Player player){
         ArrayList<Integer> x = new ArrayList();
         ArrayList<Integer> y = new ArrayList();
         addToArray(y, x);
         int randomX = (int) (Math.random() * 10);// Random
         int randomY = (int) (Math.random() * 10);// Random
         boolean done = false; //Map is created
         int count = 0;
         
      
         while(count != 20){
             
                randomX = (int) (Math.random() * 10);// Random
                randomY = (int) (Math.random() * 10);// Random
                   
                changeWhichShip(count);

              if(putShip(player,y.get(randomY),x.get(randomX)) == true){
                     count ++;  
                     map[y.get(randomY)][x.get(randomX)].hideShip();
                 }      
         }   
          setNewMethod(player);
     }
     
     public void addToArray(ArrayList y, ArrayList x){
         for(int index = 0; index < 10; index ++){
             y.add(index);
             x.add(index);
         }
    }
     
    public void changeWhichShip(int count){
           if(count < 4){
               whichShip = 4; //4
            }else if(count < 10){
                whichShip = 3; //3
            }else if(count < 16){
                whichShip = 2; //2
            }else if(count < 20){
                whichShip = 1;} //1
    }
    

 
    
    private void setNumbers(GridPane mapGui,VBox vbox, HBox hbox){
        for(int v = 1; v <= groesse; v++){     
            //Vertikal
            Label vLabel = new Label(Integer.toString(v)); //Neuer Button mit entsprechender Beschriftung wird erstellt
            vLabel.setPrefWidth(mapGui.getPrefWidth() / groesse); //Breite des Labels
            vLabel.setPrefHeight(mapGui.getPrefHeight() / groesse); //Höhe des Labels
            vLabel.setAlignment(Pos.CENTER); //Text zentriert
            vLabel.setId("VHLabel");
            vbox.getChildren().add(vLabel); //Label Grafisch dargestellt
            
            //Horizontal
            Label hLabel = new Label(Character.toString((char) ((char) 64 + v))); //ASCI CODE: A,B,C,D,E
            hLabel.setPrefWidth(mapGui.getPrefWidth() / groesse); //Breite des Labels
            hLabel.setPrefHeight(mapGui.getPrefHeight() / groesse); //Höhe des Labels
            hLabel.setAlignment(Pos.CENTER); //Text zentriert
            hLabel.setId("VHLabel");
            hbox.getChildren().add(hLabel); //Label Grafisch dargestellt
        }
    }
    
    //Ein Feld wird als Schiff markiert(Einsetzen)
    @FXML
    public void putShip(MouseEvent event,Player player, Button[] putShips ,Label[] toSetLabels){
       int y = Integer.parseInt(event.getSource().toString().substring(10, 11)); //Position auf der Y-Achse
       int x = Integer.parseInt(event.getSource().toString().substring(11, 12)); //Position auf der X-Achse
       String lokalisation  = event.getSource().toString().substring(10, 12); //Position auf der X-Achse
       
        //Welches Schiff wurde zum Einsetzen markiert
        if(whichShip == 1 || whichShip == 2 || whichShip == 3 || whichShip == 4){

              while(index[whichShip-1] < allShipsPlayer.get(whichShip-1).length - 1){ //Solange index kleiner als die Schiffgroesse ist
                
                if(allShipsPlayer.get(whichShip-1)[index[whichShip-1]][whichShip] != whichShip){ //Falls  weniger Boxen eingesetzt wurden, als die entsprechende Grösse des Schiffes
                    if(check(y,x,index[whichShip-1],whichShip,player) == true){ //Falls man auf dem Feld ein schiff einsetzen kann
                       ////Position des Schiffes wird gespeichert
                       player.addToArray(whichShip, lokalisation);
                       allShipsPlayer.get(whichShip-1)[index[whichShip-1]][whichShip] ++; 
                        //Falls genau soviel Felder schon als Schiff markiert wurden, wie die entsprechene Grösse des Scgiffes
                        if(allShipsPlayer.get(whichShip - 1)[index[whichShip-1]][whichShip] -1 == whichShip - 1){ 
                            //int a = 0;
                            index[whichShip-1] ++;
                           // toSetLabels[whichShip -1].setText(Integer.toString(index[whichShip-1]));
                            howManyShipsToSet(whichShip,0,toSetLabels);
                        }
                       
                    }
                    break;
                }
              }
         
        }
    }
    
    //For Computer 
     @FXML
     public boolean putShip(Player player,int y, int x){
        String lokalisation  = Integer.toString(y)+ Integer.toString(x); //Position auf der X-Achse
 
         //Welches Schiff wurde zum Einsetzen markiert
         if(whichShip == 1 || whichShip == 2 || whichShip == 3 || whichShip == 4){
              
               while(index[whichShip-1] < allShipsPlayer.get(whichShip-1).length - 1){ //Solange index kleiner als die Schiffgroesse ist
              
                 if(allShipsPlayer.get(whichShip-1)[index[whichShip-1]][whichShip] != whichShip){ //Falls  weniger Boxen eingesetzt wurden, als die entsprechende Grösse des Schiffes
                     if(check(y,x,index[whichShip-1],whichShip,player) == true){ //Falls man auf dem Feld ein schiff einsetzen kann
                        ////Position des Schiffes wird gespeichert
                        player.addToArray(whichShip, lokalisation);
                        allShipsPlayer.get(whichShip-1)[index[whichShip-1]][whichShip] ++; 
                         //Falls genau soviel Felder schon als Schiff markiert wurden, wie die entsprechene Grösse des Scgiffes
                  
                         if(allShipsPlayer.get(whichShip - 1)[index[whichShip-1]][whichShip] -1 == whichShip - 1){ 
                             //int a = 0;
                             index[whichShip-1] ++;
                              
                            // toSetLabels[whichShip -1].setText(Integer.toString(index[whichShip-1]));
                         }
                        return true;
                     }
                     break;
                 }
               }
          
         }
         return false;
    }
    // 1 = erhöhen, 0 = abkürzen
    public void howManyShipsToSet(int whichShip,int deleteOrinsert, Label[] labels){
        if(deleteOrinsert == 0){
            int number = Integer.parseInt(labels[whichShip-1].getText())-1; //abgekürzt um eins
            labels[whichShip-1].setText(Integer.toString(number)); //Setzt neue Zahl ein
        }else if(deleteOrinsert == 1){
            int number = Integer.parseInt(labels[whichShip-1].getText())+1; //Erhöht um eins
            labels[whichShip-1].setText(Integer.toString(number)); //Setzt neue Zahl ein
        }
    }
    
    public boolean checkShipIsCompled(){
        
        return true;
    }
    public boolean check(int y, int x, int index,int whichShip,Player player){
        boolean isOk = false;
        if(!map[y][x].isShip()){ //Falls das markierte Feld kein Schiff oder Nachbarn ist
            //Falls es genung platz gibt und prüft, ob man auf dem Feld ein Schiff einsetzen kann
            
            if(0==findNeighbour(1,y,x,shipID,map)&&allShipsPlayer.get(whichShip-1)[index][whichShip] == 0){
               shipID = count.incrementAndGet();
               if(enoughPlaceForShip( y,  x,  whichShip) == true &&0==findNeighbour(1,y,x,shipID,map)){
               isOk = true;}
            }else if(0<findNeighbour(0,y,x, shipID,map) && 0==findNeighbour(1,y,x,shipID,map)){
               isOk = true;
            }            
            //Schiff wird als Schif-Feld markiert
            if(isOk == true){
                map[y][x].setShip(true,whichShip);
                map[y][x].setIDShip(shipID);     
            }     
        }   
        return isOk;
    }
    // 0 = man kann das Schif einsetzen, 1= es wurde ein Nachbarn gefunden
    public int  findNeighbour(int a, int y, int x, int shipID,Field[][] map){
        int count = 0;
        int indexY = -1;
        int indexX;
        while(indexY <= 1){
            indexX = -1;
            if(y + indexY < map.length && y + indexY >= 0){
                while(indexX <= 1){
                  if(x + indexX < map.length && x + indexX >= 0){
                    if(map[y+indexY][x+indexX].getIDShip() != shipID && a == 1 
                     &&map[y+indexY][x+indexX].getIDShip() > 0){
                         count++; 
                    }else if(a == 0){
                       if(indexY == 0 && indexX == 0){
                           
                       }else if(map[y+indexY][x].getIDShip() == shipID && indexY !=0){
                         count++;
                         break;
                       }else if(map[y+indexY][x+indexX].getIDShip() == shipID && indexY ==0){
                         count ++;
                       }
                    }else if(a == 3 && map[y+indexY][x+indexX].getIDShip() == shipID){
                        if((y+indexY + x+indexX) == (y+x)){
                        }else{
                            count ++;}  
                    }
                  }
                indexX ++;
              }
            }
            indexY ++;
        }
        return count;
    }
    
    public boolean canDelte(int y, int x){
        int top = 0,mid=0,bot=0;
        int position = 0, middlePosition = 0;
        int indexY = -1, indexX;
        while(indexY <= 1){
            indexX = -1;
            if(y + indexY < map.length && y + indexY >= 0){
                while(indexX <= 1){
                  if(x + indexX < map.length && x + indexX >= 0){ 
                    if(map[y+indexY][x+indexX].getIDShip() == shipID){
                     if(indexX == 0 && indexY == 0){    
                         
                     }else if(indexY == 1){
                        if(indexX != 0){
                        position = indexX;}
                        top ++;
                     }else if(indexY == -1){
                         if(indexX != 0){
                         position = indexX;}
                         bot ++;
                     }if(indexY == 0 && indexX != 0){
                         middlePosition = indexX;
                     }
                    }
                  } 
                  indexX ++;
                }
            }
            indexY ++;
        }
        if(middlePosition == position && (top == 2 || bot == 2)){
            return true;
        }
        return false;
    }
  
    
  public boolean enoughPlaceForShip(int y, int x, int whichShip){
      Field[][] map = makeCopyMap(this.map); //Macht eine Kopie eines Objektes
      insertFieldToArray(map); //Fügt alle Felder in ArrrayListe hinzu
      int index = 1;
      map[y][x].setIDShip(-1);
      map[y][x].setShip(true,0);
      list.remove(map[y][x]);
      if(whichShip != 1){
        for(int a = 0; a < 2; a++){
          insertFieldToArray(map); //Fügt alle Felder in ArrrayListe hinzu
          int groesse= 99,random; //Alle Felder
          while(groesse > 0){
              random = (int) (Math.random() * groesse);
              y = list.get(random).getY();
              x = list.get(random).getX();

              if(!map[y][x].isShip()){
                if(0<findNeighbour(0,y,x, -1,map) && 0==findNeighbour(1,y,x,-1,map)){
                    if(index != whichShip){
                      index ++;  
                      map[y][x].setShip(true,0);
                      map[y][x].setIDShip(-1);
                    }
                    if(index == whichShip){
                        return true;}
                    }      
                }
            list.remove(random);
            groesse --;
          }
        }
      }else{
          return true;
      }
      return false;
  }
  
  public Field[][] makeCopyMap(Field[][] map1){
        Field[][] map = new Field[10][10];
        
        for(Field[] g : map1){
            for(Field j : g){
                map[j.getY()][j.getX()] = new Field();
                if(j.isShip()){
                    map[j.getY()][j.getX()].setShip(true,0);
                    map[j.getY()][j.getX()].setIDShip(map1[j.getY()][j.getX()].getIDShip());
                }
                if(j.getIsNeighbour()){
                    map[j.getY()][j.getX()].setIsNeighbour(true);
                }
                
                map[j.getY()][j.getX()].getBtn().setId(Integer.toString(j.getY())+""+Integer.toString(j.getX())); // Setzt die ID des Buttons ein
            }  
        }
    return map;
  }

  
    //Fügt Felder in eine ArrayListe hinzu
    public void insertFieldToArray(Field[][] map1){
        list = new ArrayList<Field>();
        //Fügt alle Felder in die Array hinzu
        for(Field[] y : map1){ //Y Achse
            for(Field x : y){//X Achse
                list.add(x);
            }  
        }
 
    }
    //Set Method 
    public void setNewMethod(Player player){
         for(Field[] yAxis: map){
            for(Field xAxis : yAxis){
                setMethodAttack(player, xAxis);
            }
         }
        
    }
    
    //Methode zum Attacken
    public void attack(MouseEvent event, Player player){
       if(getNumberOfShips() > 0 && GameVScomputerController.winner == 0 && turn == true){
            int y = Integer.parseInt(event.getSource().toString().substring(10, 11)); //Position auf der Y-Achse
            int x = Integer.parseInt(event.getSource().toString().substring(11, 12)); //Position auf der X-Achse
             if(!player.getMap().map[y][x].isChecked()){
                  if(player.getMap().map[y][x].isShip() && !player.getMap().map[y][x].getHitted()){
                       map[y][x].setHitted(true);
                       player.findDestroyedShip();
                       checkNumberOfShips(1);
                       turn = true;

                  }else if(!player.getMap().map[y][x].getHitted()&&!player.getMap().map[y][x].isChecked()){
                      map[y][x].setHitted(false);
                       turn = false;
                  }
             } 
       }
    }
    
    //Erstellt einen neuen Button
    public void setMethod(Player player, Field feld,Button[] putShips ,Label[] toSetLabels){
        //Felder bekommen automatisch eine Methode 
        feld.getBtn().addEventFilter(MouseEvent.MOUSE_PRESSED, (e) ->{
        if(e.isPrimaryButtonDown()){  
          putShip(e,player,putShips ,toSetLabels);
        }else if(e.isSecondaryButtonDown()){
          player.getBack(e,toSetLabels);
        }
        }); // Setzt die ID des Buttons ein  
   }
    //AttackSpiel-Button
    public void setMethodAttack(Player player, Field feld){
        //Felder bekommen automatisch eine Methode 
        
        feld.getBtn().addEventFilter(MouseEvent.MOUSE_PRESSED, (e) ->{
        if(e.isPrimaryButtonDown()){  
          attack(e,player);
        }
        }); // Setzt die ID des Buttons ein  
        
   }
    
    
         
     //For Computer
    public void setMethod(Player player, Field feld){
         //Felder bekommen automatisch eine Methode 
         feld.getBtn().addEventFilter(MouseEvent.MOUSE_PRESSED, (e) ->{
         if(e.isPrimaryButtonDown()){ 
             int x = 0;
             int y = 0;
           putShip(player, y, x);
         }
         }); // Setzt die ID des Buttons ein  
    }
    
    public void checkNumberOfShips(int winner){
        //Platform.setImplicitExit(false);
        setNumberOfShips(getNumberOfShips() -1);
        if(getNumberOfShips() == 0){ // If 0 = Computer has won
            GameVScomputerController.winner = winner;
        }
    } 
  
  

}
