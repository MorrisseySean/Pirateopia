/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    public Player(int x, int y, ID id){
        super(x, y, id);
        
    }
    
    public void tick(){
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 60);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, 32, 32);
    }
}
