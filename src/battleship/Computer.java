/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Random;

/**
 *
 * @author emazi
 */
public class Computer {
    
    int probabilityToHit = 0;
    boolean probability;
    
    public void attack(int probabilityToHit, Player player){
        if(tryToHit(probabilityToHit)){
            
        }
    }
    
    private boolean tryToHit(int probabilityToHit){
        boolean probability;
        probability = probability = new Random().nextInt(probabilityToHit)==0;
        
        return probability;
    }
    
    private void findNotDestroyedShip(){
        
    }
    
//    private boolean canIShoot(Player player){
//            if(tryToHit(probabilityToHit) == true){
//            for(Field[] yAxis: player.getMap().map){
//                for(Field xAxis : yAxis){
//                    if(xAxis.isShip()){
//                        probabilityToHit++;
//                        System.out.println(probabilityToHit);
//                    }
//                }
//            }
//        }
//       return      
//    }
}