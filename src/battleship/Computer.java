/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emazi
 */
public class Computer {
    
    public double probabilityToHit = 0;
    private boolean probability;
    private int y;
    private int x;
    private ArrayList<Integer> position = new ArrayList<Integer>();
    private ArrayList<Integer> randomArray = new ArrayList<Integer>();
    
    //Konstruktor
    Computer(){
        addToArray();
    }
    
    private void attack(Player player){
        int rnd;
        System.out.println("ATTACK POSITON = "+position);
        rnd = randomFunc(position.size() - 1);
        System.out.println("POSITION SIZE = "+position.size());
        destroyShip(player,position.get(rnd),position.get(rnd+1));
        System.out.println("FUNCTION ATTACK");
    }
    
    private void destroyShip(Player player, int y, int x){
        if(player.getMap().map[y][x].isShip()){
            player.getMap().map[y][x].setHitted(true);
            setNeighbour(true,y, x, player);
            player.findDestroyedShip();
        }else{
            player.getMap().map[y][x].setChecked(true);
        }
  
        
    }
    
    public void tryToHit(Player player){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        int random;
        probability = Math.random() < (probabilityToHit/100);
        probability = false;
        System.out.println("probablitiy = "+ probability);
        findNotDestroyedShip(player);
        if(position.isEmpty()){
             random = random(player, probability);
             if(findShip(player, random)){
                tryToHit(player); 
             } 
        }else{
            attack(player);
        }    
          
        
    }
    
    private void findNotDestroyedShip(Player player){
         for(Field[] yAxis: player.getMap().map){
                for(Field xAxis : yAxis){
                    if(xAxis.isShip()){
                        if(xAxis.isChecked() && !xAxis.getHitted()){
                            continue;
                        }else if(xAxis.getHitted() && !xAxis.isDestroyed()){
                            y = xAxis.getY();
                            x = xAxis.getX();
                            position = findNeigh(player,y,x);
                             System.out.println("found not Destroyed Y ="+y+" X = "+x);
                        }  
                    }
         }      }
    }
   private boolean findShip(Player player, int shipID){
         for(Field[] yAxis: player.getMap().map){
                for(Field xAxis : yAxis){
                    //System.out.println("xAxis.getIDShip() = "+xAxis.getShipID() +" shipID = "+ shipID);
                    if(xAxis.getShipID() == shipID){
                       destroyShip(player, xAxis.getY(), xAxis.getX());
                        System.out.println("xAXIS Y = "+xAxis.getY()+" X = "+xAxis.getX());
                       if(xAxis.isShip()){
                        return true;
                       }else{
                         return false;
                       }
                    }
         }      }
        System.out.println("Error: ID not find");
        return false;
    }
    
    
     private ArrayList<Integer> findNeigh(Player player, int y, int x){
        ArrayList<Integer> position = new ArrayList<Integer>();
        int [] yx = {y,x};
        int [] add = {0,1,0};
        
        for(int a = 0; a < 2; a++){
            if(yx[a] +1 < player.getMap().map.length){
                System.out.println("Find Neigh Y = "+y+" X = "+x);
                if(!player.getMap().map[y+add[a+1]][x+add[a]].isChecked()&& !player.getMap().map[y+add[a+1]][x+add[a]].getHitted()){
                    position.add(y+add[a+1]);
                    position.add(x+add[a]);
                }    
            }  
        }
        for(int a = 0; a < 2; a++){
            if(yx[a] - 1 >= 0){
                System.out.println("Find Neigh Y = "+y+" X = "+x);
                if(!player.getMap().map[y-add[a+1]][x-add[a]].isChecked() && !player.getMap().map[y-add[a+1]][x-add[a]].getHitted()){
                    position.add(y-add[a+1]);
                    position.add(x-add[a]);
                }  
            }
        }
        System.out.println(position);
        return position;
    }   
    

   public int randomFunc(int size)
    {
        ArrayList<Integer> list = new ArrayList();
        for(int index = 0; index < size; index ++){
            if(index % 2 == 0){
                System.out.println("index is "+index);
                list.add(index);
            }
        }
        if(list.size()-1 == 0){
          return 0;
        }
        else{
          return list.get(new Random().nextInt(list.size()-1));  
        }
        
    }
    public int random(Player player, boolean promission){
        int randomNumber,y,x;
        ArrayList<Integer> list = new ArrayList();
        list = randomArray;
         int position = 0;
        for(int index = list.size(); index > 0; index --){
            randomNumber = new Random().nextInt(index);
            y = getY(list.get(randomNumber));
            x = getX(list.get(randomNumber));
                if(player.getMap().map[y][x].isShip() && promission){
                    System.out.println("SIZE IS "+ randomArray.size());
                    position = randomArray.get(randomNumber);
                    randomArray.remove(randomNumber);
                     return position;
                }else if(!player.getMap().map[y][x].isShip() && !promission){
                    System.out.println("SIZE IS "+ randomArray.size());
                    position = randomArray.get(randomNumber);
                    randomArray.remove(randomNumber);
                    return position;
                }
            list.remove(list.get(randomNumber));
        }
        System.out.println("error in Function \"Random\"");
        return 0;
    }
    private int getY(int yx){
        String str;
        if(yx < 10){
          str = "0";
        }else{
            str = Integer.toString(yx).substring(0,1);
        }
        int y = Integer.parseInt(str);
        return y;  
    }
    private int getX(int yx){
        String str = Integer.toString(yx);
        if(str.length() == 1 && str != "0"){
            str = "0" +  Integer.toString(yx).substring(0,1);            
        }else if(str == "0"){
            str = "0";
        }else{
            System.out.println("STRING BY X IS = "+str);
            str = Integer.toString(yx).substring(1,2);
        }
        int y = Integer.parseInt(str);
        return y;  
    }
    
    
    private void addToArray(){
        for(int index = 0; index < 100; index++){
            randomArray.add(index);
        }
    }   
   
         /* SET Neighbour */
    //  Setzt Nachbarn ein 
    public void setNeighbour(boolean setNeigh,int y, int x, Player player){
         if(y - 1 >= 0){//Falls Feld nich ganz oben ist
             player.getMap().map[y - 1][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                if(x + 1 < player.getMap().map.length){//Falls Feld nicht ganz oben rechts ist
                    player.getMap().map[y-1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
                if(x - 1 >= 0){//Falls Feld nicht ganz oben links ist
                    player.getMap().map[y-1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
         }
         if(y + 1< player.getMap().map.length){ ///Falls Feld nicht ganz unten ist
            player.getMap().map[y+1][x].setIsNeighbour(setNeigh);
                if(x + 1 < player.getMap().map.length){///Falls Feld nicht ganz rechts unten ist
                    player.getMap().map[y+1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
                if(x - 1 >= 0){///Falls Feld nicht ganz links unten ist
                    player.getMap().map[y+1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
        }
        if(x + 1 < player.getMap().map.length){///Falls Feld nicht ganz rechts ist
             player.getMap().map[y][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
         }
        if(x - 1 >= 0){///Falls Feld links ganz unten ist
             player.getMap().map[y][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
         }
        player.getMap().map[y][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
    }
     


}