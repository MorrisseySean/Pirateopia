/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    
    /// Reference to the game object handler for collision checking
    Handler m_handler;
    
    /// Whether the player is in a ship or not
    boolean inShip = true;
    
    /// Ship container if player isn't riding a ship
    GameObject ship;
    
    public Player(int x, int y, ID id, Handler h){
        super(x, y, Game.RATIO/5, Game.RATIO/5, id); 
        this.m_handler = h;
        
        /// Add the player starting ship
        ship = new BasicShip(x, y, Game.RATIO/3, Game.RATIO/2, ID.Ship);
        m_handler.addObject(ship);
    }
    
    public void tick(){
        /// Move the player by their velocity
        x += velX;
        y += velY;       
        
        /// Check if the player is still on a passable tile
        boolean onPassable = inShip;
        /// List through every square on the current island
        for(int i = 0; i < m_handler.getCurrentIsland().getTiles().size(); i++){      
            GameObject curTile = m_handler.getCurrentIsland().getTiles().get(i);
            /// If the player still collides with a square, change onGround to true
            int curWidth = w;
            int curHeight = h;
            if(ship != null){
                curWidth = ship.w;
                curHeight = ship.h;
            }
            if(Game.checkBoxCollision(x, y, curWidth, curHeight, curTile.x, curTile.y, curTile.w, curTile.h)){
                if(inShip == true){
                    x += (velX / (Game.RATIO / 18)) * ship.w;
                    y += (velY / (Game.RATIO / 18)) * ship.h;
                    if(Game.checkBoxCollision(x, y, w, h, curTile.x, curTile.y, curTile.w, curTile.h)){
                        ship = null;                                        
                        inShip = false; 
                    }
                    else{
                        x = ship.x;
                        y = ship.y;                        
                    }
                }
                onPassable = true;
                
            }
        }
        if(ship == null){
            /// List through all gameobjects to find the ship
            for(int i = 0; i < m_handler.objects.size(); i++){
                if(m_handler.objects.get(i).id == ID.Ship){
                    GameObject tempShip = m_handler.objects.get(i); 
                    if(Game.checkBoxCollision(x, y, w, h, tempShip.x, tempShip.y, tempShip.w, tempShip.h)){
                        ship = tempShip;
                        inShip = true;
                        onPassable = true;
                        x = ship.x;
                        y = ship.y;
                    }
                }
            }
        }
        /// If onPassable remained false, change to ship
        if(onPassable == false){
            x -= velX;
            y -= velY;
        }        
        
        if(x < -w){
            m_handler.nextIsland(-1, 0);
            x = Game.WIDTH - (w * 2);
        }
        else if(x > Game.WIDTH - w){
            m_handler.nextIsland(1, 0);
            x = 1;
        }
        else if(y < -h){
            m_handler.nextIsland(0, -1);
            y = Game.HEIGHT - (h * 2);
        }
        else if(y > Game.HEIGHT - h){
            m_handler.nextIsland(0, 1);    
            y = 1;
        }
        
        
        
        if(ship != null){
            ship.x = x;
            ship.y = y;
        }
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, Game.RATIO / 4, Game.RATIO / 4);
    }
}
