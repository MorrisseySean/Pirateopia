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
            /// Get the current width and height of the player based, varying depending on if their in a ship
            int curWidth = w;
            int curHeight = h;
            if(ship != null){                
                curWidth = ship.w;
                curHeight = ship.h;
            }
            
            /// If the player is in a ship, use box collision to detect collision with terrain
            if(ship != null){
                if(Game.checkBoxCollision(ship.x, ship.y, curWidth, curHeight, curTile.x, curTile.y, curTile.w, curTile.h)){
                    /// On colliding with the terrain, check if the player can stand on the land.
                    if(inShip == true){
                        //x = ((curTile.x + (curTile.w / 2)) - (w / 2) * (velX / (Game.RATIO / 16)));
                        //y = ((curTile.y + (curTile.h / 2)) - (h / 2) * (velY / (Game.RATIO / 16)));
                        x += (velX / (Game.RATIO / 16)) * curWidth + Game.RATIO / 10;
                        y += (velY / (Game.RATIO / 16)) * curHeight + Game.RATIO / 10;
                        if(Game.checkPointCollision(x + (w / 2), y + (h / 2), curTile.x, curTile.y, curTile.w, curTile.h)){
                            ship = null;                                        
                            inShip = false; 
                        }
                    }
                    onPassable = true;
                }
            }
            else{
                if(Game.checkPointCollision(x + (w / 2), y + (h / 2), curTile.x, curTile.y, curTile.w, curTile.h)){
                    onPassable = true;
                }
            }
        }
        if(ship == null){
            /// List through all gameobjects to find the ship
            for(int i = 0; i < m_handler.objects.size(); i++){
                if(m_handler.objects.get(i).id == ID.Ship){
                    GameObject tempShip = m_handler.objects.get(i); 
                    if(Game.checkBoxCollision(x, y, w, h, tempShip.x, tempShip.y, tempShip.w, tempShip.h)){
                        ship = tempShip;
                        //inShip = true;
                        onPassable = true;
                        x = (ship.x + (ship.w / 2)) - (w / 2);
                        y = (ship.y + (ship.h / 2)) - (h / 2);
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
            x = (Game.WIDTH * 2) - (w * 2);
        }
        else if(x > (Game.WIDTH * 2) - w){
            m_handler.nextIsland(1, 0);
            x = 1;
        }
        else if(y < -h){
            m_handler.nextIsland(0, -1);
            y = (Game.HEIGHT * 2) - (h * 2);
        }
        else if(y > (Game.HEIGHT * 2) - h){
            m_handler.nextIsland(0, 1);    
            y = 1;
        }
        
        
        
        if(ship != null){
            if(inShip == true){
                ship.x = (x + w / 2) - ship.w / 2;
                ship.y = (y + h / 2) - ship.h / 2;
            }
            else{
                inShip = true;
            }
        }
        
        Game.CAMX = this.x - Game.WIDTH / 2;       
        Game.CAMY = this.y - Game.HEIGHT / 2;
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x - Game.CAMX, y - Game.CAMY, Game.RATIO / 4, Game.RATIO / 4);
    }
}
