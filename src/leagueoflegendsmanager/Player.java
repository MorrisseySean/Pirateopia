/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    
    Handler m_handler;
    
    public Player(int x, int y, ID id, Handler h){
        super(x, y, Game.RATIO/4, Game.RATIO/4, id); 
        this.m_handler = h;
    }
    
    public void tick(){
        x += velX;
        y += velY;
        boolean onGround = false;
        for(int i = 0; i < m_handler.getCurrentIsland().getTiles().size(); i++){
            GameObject curTile = m_handler.getCurrentIsland().getTiles().get(i);
            if(Game.checkBoxCollision(x, y, w, h, curTile.x, curTile.y, curTile.w, curTile.h)){
                onGround = true;
            }
        }
        if(onGround == false){
            x -= velX;
            y -= velY;
        }
        x = Game.clamp(x, 0, Game.WIDTH - 60);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, Game.RATIO / 4, Game.RATIO / 4);
    }
}
