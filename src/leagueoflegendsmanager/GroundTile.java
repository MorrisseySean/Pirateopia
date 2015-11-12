/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.Graphics;
import java.awt.Color;

/// Ground tile is a patch of green the player can walk on but the ship cannot traverse
public class GroundTile extends GameObject{
    
    public GroundTile(int x, int y, ID id){
        super(x, y, Game.RATIO, Game.RATIO, id);
    }
    
    public void tick(){
    }
    
    public void render(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, w, h);    
    }
    
}
