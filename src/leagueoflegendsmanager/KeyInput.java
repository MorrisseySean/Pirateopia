/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    
    /// Keep track of the game handler
    private Handler handler;
    
    int PlayerVel = Game.RATIO / 16;
    
    public KeyInput(Handler h){
        handler = h;
    }
    
    public void keyPressed(KeyEvent e){
        /// Grab the pressed key
        int key = e.getKeyCode();
        
        /// Check through all objects
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            
            /// If the gameobject is the player
            if(tempObject.getID() == ID.Player){
                /// If W key is pressed, move player up
                if(key == KeyEvent.VK_W){
                    tempObject.setVelY(-PlayerVel);
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelY(PlayerVel);
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setVelX(-PlayerVel);
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(PlayerVel);
                }
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        /// Check through all objects
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            
            /// If the gameobject is the player
            if(tempObject.getID() == ID.Player){
                /// If W key is pressed, move player up
                if(key == KeyEvent.VK_W){
                    tempObject.setVelY(0);
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelY(0);
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setVelX(0);
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(0);
                }
            }
        }
    }
    
}
