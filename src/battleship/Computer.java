/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emazi
 */
public class Computer {
    
    int probabilityToHit = 0;
    boolean probability;
    int y;
    int x;
    
    public void attack(int probabilityToHit, Player player){
            findNotDestroyedShip(player);
    }
    
    private boolean tryToHit(int probabilityToHit){
        boolean probability;
        probability = probability = new Random().nextInt(probabilityToHit)==0;
        
        return probability;
    }
    
    private void findNotDestroyedShip(Player player){
         for(Field[] yAxis: player.getMap().map){
                for(Field xAxis : yAxis){
                    if(xAxis.isShip()){
                        if(xAxis.isChecked() && !xAxis.getHitted()){
                            continue;
                        }else if(xAxis.getHitted()){
                            y = xAxis.getY();
                            x = xAxis.getX();
                            findNeigh(player,y,x);
             
                        }
                        System.out.println(probabilityToHit);
                    }
         }      }
    }
     private ArrayList<Integer> findNeigh(Player player, int y, int x){
        ArrayList<Integer> position = new ArrayList<Integer>();
        int [] yx = {y,x};
        int [] add = {0,1,0};
        
        for(int a = 0; a < 2; a++){
            if(yx[a] +1 < player.getMap().map.length){
                if(player.getMap().map[y+add[a+1]][x+add[a]].isShip() && !player.getMap().map[y+add[a+1]][x+add[a]].getHitted()){
                    position.add(y+add[a+1]);
                    position.add(x+add[a]);
                }    
            }  
        }
        for(int a = 0; a < 2; a++){
            if(yx[a] - 1 >= 0){
                if(player.getMap().map[y-add[a+1]][x-add[a]].isShip() && !player.getMap().map[y-add[a+1]][x-add[a]].getHitted()){
                    position.add(y-add[a+1]);
                    position.add(x-add[a]);
                }  
            }
        }
        System.out.println(position);
        return position;
    }      


}