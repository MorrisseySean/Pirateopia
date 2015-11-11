/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    
    public static int HEALTH = 100;
    
    public void tick(){
        HEALTH--;
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }
    
    public void render(Graphics g){        
        g.setColor(Color.RED);
        g.fillRect(10, 10, 256 * HEALTH / 100, 32);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(10, 10, 256, 32);        
    }
}
