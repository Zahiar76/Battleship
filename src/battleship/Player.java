/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Emil Poorando
 */
public class Player {
    /*
        Deklaration
    */
    Map map = new Map(); //Zwei Dimensionale Array, auf der Schiffe eingesetzt werden  1 = Y 2 = X
    int numberOfHits; //Anzahl der Treffer
    int numberOfMisses; //Anzahl der Fehlschläge
    int numberOfAllHits; //Summe von Treffern und Fehlschlägen
    private ArrayList<ArrayList<ArrayList<String>>> shipsPosition2 = new ArrayList<ArrayList<ArrayList<String>>>(){{
    }};;
    
    
    private void addArray(){
        for(int typ = 0; typ < 4; typ++){ //Typ
            shipsPosition2.add(new ArrayList());
                for(int number = 0; number < 4; number ++){ //ID des Schiffes
                    shipsPosition2.get(typ).add(new ArrayList<String>());}}
    }
    public void addToArray(int whichShip, String lokalisation){
        int number = findFreePlaceInArray(whichShip-1); // ID des Schiffes 
      //  shipsPosition2.get(whichShip-1).add(new ArrayList<String>());
        if(number != -1){ //Falls kein Fehler
            shipsPosition2.get(whichShip -1).get(number).add(lokalisation); //Position gespeichert
        }
    }
    public int findFreePlaceInArray(int whichShip){
       int index = 0; //ID des Schiffes
        for(boolean free = false; free == false;){ //falls es freien Platz gibt

            if(shipsPosition2.get(whichShip).get(index).size() < (whichShip+1)){ // Freier Platz wird gesucht
                return index; //Erfolgreich
            }else{
                index ++; // Weitersuchen 
            }
        }
        System.out.println("error addToArray");
        return -1;
    }
    
    public String getPositionFromArray(int whichShip, int position){
        for(int index = shipsPosition2.get(whichShip-1).size()- 1; index >= 0; index --){ //falls es freien Platz gibt
            if(shipsPosition2.get(whichShip-1).get(index).size() == (whichShip)){ // Freier Platz wird gesucht
                
                return shipsPosition2.get(whichShip-1).get(index).get(position); 
                
            }
        }
        System.out.println("error GetPositionFromArray");
        return "error";
    }
    public int getNumberOfHits() {
        return numberOfHits;
    }

    public void setNumberOfHits(int numberOfHits) {
        this.numberOfHits = numberOfHits;
    }

    public int getNumberOfMisses() {
        return numberOfMisses;
    }

    public void setNumberOfMisses(int numberOfMisses) {
        this.numberOfMisses = numberOfMisses;
    }

    public int getNumberOfAllHits() {
        return numberOfAllHits;
    }

    public void setNumberOfAllHits(int numberOfAllHits) {
        this.numberOfAllHits = numberOfAllHits;
    }



    public ArrayList<ArrayList<ArrayList<String>>> getShipsPosition2() {
        return shipsPosition2;
    }

    public void setShipsPosition2(ArrayList<ArrayList<ArrayList<String>>> shipsPosition2) {
        this.shipsPosition2 = shipsPosition2;
    }
    
    
    /*
        Konstruktor
    */
    Player() {
       addArray();
    }
   /*
        Methoden
   */
   //Setter und Getter
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }



    public void computerMap(Field[][] map){
        
    }
    
   
    
    //Return-Button
   @FXML
   public void getBack(MouseEvent event,Label[] toSetLabels){
        
        String lokalisation = event.getSource().toString().substring(10, 12); //Position auf der Y-Achse
        int y = Integer.parseInt(lokalisation.substring(0,1));
        int x = Integer.parseInt(lokalisation.substring(1,2));
        int whichShip = getWhichShip( y,  x) + 1; 
        
        if(map.getWhichShip() -1 == getWhichShip(y,x)){
            if(map.getMap()[y][x].isShip() && ableToDelete( y,  x)){
                map.setShipID(y, x);
                    if(deleteInMiddle(y,x)){
                     setNeighbour(lokalisation, false,toSetLabels);
                     map.getMap()[y][x].showNormal();
                     deleteFromArray(lokalisation);
                // System.out.println(map.getMap()[y][x].isShip());
                }
            }
        }
   }
   
   //set Nachbarn
   @FXML
   public void setNeighbour(String lokalisation, boolean neigh, Label[] toSetLabels){
    int whichShip = 0;
        for(ArrayList typ : shipsPosition2){
        int number = 0;
        for(Object a : typ){
             String str = a.toString();
                if(str.indexOf(lokalisation) >= 0){
                  //  System.out.println(shipsPosition2.get(whichShip).get(number).get(0));
                   for(String n : shipsPosition2.get(whichShip).get(number)){
                       String y = n.substring(0,1); String x = n.substring(1,2);
                      // map.setNeighbour(neigh,Integer.parseInt(y), Integer.parseInt(x));
                       //break;
                   } 
                   if(neigh == false && (shipsPosition2.get(whichShip).get(number).size() == 0 || shipsPosition2.get(whichShip).get(number).size() == whichShip+1)){
                       map.index[whichShip] --;
                       map.howManyShipsToSet(whichShip+1, 1, toSetLabels);
                      // showQuantityOfShips(whichShip);
                   }
                break;
            }
            number ++;
        }
     whichShip ++;
  
    }
     
   }
   /*
   ERROR  ALLSHIPSPLAYER problme with deleting
   */
   public boolean  deleteFromArray(String lokalisation){
       int whichShip = 0;
       int typ = 0;
       for(ArrayList index0 : shipsPosition2){

           for(ArrayList index1 : shipsPosition2.get(whichShip)){
               
               if(shipsPosition2.get(whichShip).get(typ).indexOf(lokalisation) >= 0){ 
                   
                   shipsPosition2.get(whichShip).get(typ).remove(lokalisation);
                  
                   int length = map.allShipsPlayer.get(whichShip).length;
                   if(map.index[whichShip] >= length){
                       map.index[whichShip] --;
                   }
                   if(map.allShipsPlayer.get(whichShip)[map.index[whichShip]][whichShip+1] > 0){
                   map.allShipsPlayer.get(whichShip)[map.index[whichShip]][whichShip+1] --;
                       
                   }              
                   return true;
               }
               typ ++;
           }
           typ = 0;
           whichShip ++;
       }
       return false;
   }
   //Erlaubt das Löschen eines Felder nicht, wenn er sich in der Mitte befindet
   public boolean deleteInMiddle(int y, int x){
       if(map.canDelte(y, x) || map.findNeighbour(0,y, x, map.getShipID(),map.getMap()) <= 1){
           getWhichShip(y, x);
           return true;
       }
      return false;
   }
    
   public boolean ableToDelete(int y, int x){
       int isOk = 0;
       String lokalisation = Integer.toString(y)+Integer.toString(x);
       int whichShip = getWhichShip(y, x);
       
        if(whichShip != 0){
            if(getShipsPosition2().get(whichShip).get(findFreePlaceInArray(whichShip)).isEmpty()
               ||
               getShipsPosition2().get(whichShip).get(findFreePlaceInArray(whichShip)).size() % ((whichShip+1)) == 0){
                return true;
            }

            if(getShipsPosition2().get(whichShip).get(findFreePlaceInArray(whichShip)).indexOf(lokalisation) >= 0){
                return true;
            }
        }else{
            return true;
        }
        
       return false;
   }
   
   public boolean showQuantityOfShips(int whichShip){
        ArrayList<Integer> list = new ArrayList<>();
        int number = findFreePlaceInArray(whichShip);
        int isOk = 0;
        for(int index = 0; index <= 3; index ++){
            if(whichShip != index){
               list.add(index);
            }
        }
        
        for(int index = 0; index < 3; index ++){
            if(getShipsPosition2().get(list.get(index)).get(findFreePlaceInArray(index+1)).isEmpty()
               ||
              getShipsPosition2().get(list.get(index)).get(findFreePlaceInArray(index+1)).size() % ((list.get(index)+1)) == 0){
                isOk++;
            }
        }
        return isOk == 3;
   }
   
   //By deleting
   public int getWhichShip(int y, int x){
       String position = Integer.toString(y) + Integer.toString(x);
       int number = 0;
       int whichShip = 0;
           
        for(ArrayList a : shipsPosition2){
         number = 0;
         for(Object b : a){
          if(shipsPosition2.get(whichShip).get(number).indexOf(position) >= 0){
              return whichShip;
          }
          number ++;
         }
        whichShip ++;
        }
        System.out.println("WhichShip Error");
        return -1;
      
    }
    
    public void setShipsToPut(){
        
    }
    
    //private ArrayList<ArrayList<ArrayList<String>>> shipsPosition2 = new ArrayList<ArrayList<ArrayList<String>>>(){{
        
    //}
    public void findDestroyedShip(){
        String yx = "";
        int y;
        int x;
        String ok;

        //Ship 1
        for(int index = 0; index <= 3; index++){                 
            for(ArrayList a : shipsPosition2.get(index)){
                yx = a.toString();
                yx = yx.replaceAll("[^-?0-9]+", ""); 
                if(index == 0 && yx.length() == 2 ){
                  ok =  setDestroyedField(yx);
                }
                if(index == 1 && yx.length() == 4){
                  ok = setDestroyedField(yx);
                }
                if(index == 2 && yx.length() == 6){
                  ok = setDestroyedField(yx);
                }
                if(index == 3 && yx.length() == 8){
                  ok =  setDestroyedField(yx);
                }
            }
        }
    }  
    
    public String setDestroyedField(String yx){
        int y,x;
        int size = yx.length();
        ArrayList<Integer> list = new ArrayList<>();
        int zero = -1;
        int one = 0;
        int count = 0;
        int number = 0;
         int index1 = 0;
   
        for(int index =0; index < size; index ++){
            zero ++;
            one ++; 
            list.add(Integer.parseInt(yx.substring(zero,one))); 
        }
        number = 0;
        for(int index =1; index <= size/2; index ++){
            if(getMap().map[list.get(number)][list.get(number+1)].getHitted()){
                count ++;  
            }
          number = number+2;
          index1 = index;
        }
    
        if(count == index1){
            number = 0;
            for(int index =0; index < size/2; index ++){  
              getMap().map[list.get(number)][list.get(number+1)].setDestroyed(true);  
              setNeighbour(true,list.get(number),list.get(number+1));
              number = number+2;
            }
           return "Destroyed";  
        }
        return "Not Destroyed";
    }
    
        public void setNeighbour(boolean setNeigh,int y, int x){
         if(y - 1 >= 0){//Falls Feld nich ganz oben ist
             getMap().map[y - 1][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                if(x + 1 < getMap().map.length){//Falls Feld nicht ganz oben rechts ist
                   getMap().map[y-1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
                if(x - 1 >= 0){//Falls Feld nicht ganz oben links ist
                   getMap().map[y-1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
         }
         if(y + 1< getMap().map.length){ ///Falls Feld nicht ganz unten ist
            getMap().map[y+1][x].setIsNeighbour(setNeigh);
                if(x + 1 < getMap().map.length){///Falls Feld nicht ganz rechts unten ist
                    getMap().map[y+1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
                if(x - 1 >= 0){///Falls Feld nicht ganz links unten ist
                    getMap().map[y+1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
        }
        if(x + 1 < getMap().map.length){///Falls Feld nicht ganz rechts ist
             getMap().map[y][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
         }
        if(x - 1 >= 0){///Falls Feld links ganz unten ist
             getMap().map[y][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
         }
        getMap().map[y][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
    }


    
}
