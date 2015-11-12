/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.Color;
import java.awt.Graphics;

/*
* BasicShip is used for movement on non land based tiles.
*/
public class BasicShip extends GameObject{
    
    public BasicShip(int x, int y, int w, int h, ID id){
        super(x, y, w, h, id);        
    }
    
    public void tick(){
    }
    
    public void render(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, w, h);
    }
    
}
