/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/// Island class controls generation of upkeep of Islands
public class Island extends GameObject{
    /// Island coordinates
    private int xCoord;
    private int yCoord;
    
    /// A list of all the tiles on the island
    public LinkedList<GameObject> tiles = new LinkedList<GameObject>();
    
    public Island(int x, int y, ID id){
        super(x, y, Game.WIDTH, Game.HEIGHT, id);
        xCoord = x;
        yCoord = y;
        
        /// Generate the island, this will be changed in later updates                
        int map[][] = GenerateIsland();
        
        /// Loop through the map and place a ground tile for each instance of 1
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 1){
                    tiles.add(new GroundTile(j * Game.RATIO, i * Game.RATIO, ID.Tile));
                }
            } /// End inner for loop
        } /// End outer for loop
    } ///End Method  
    
    public int[][] GenerateIsland(){
        int map[][] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};       
        
        Random r = new Random(System.currentTimeMillis());
        for(int i = 2; i < map.length - 2; i++){
            for(int j = 2; j < map[i].length - 2; j++){
                map[i][j] = (int)Math.floor(r.nextDouble() + 0.5);
            }
        }
        return map;
    }
    public void tick(){
        
    }
    
    public void render(Graphics g){
        for(int i = 0; i < tiles.size(); i++){
            tiles.get(i).render(g);
        }
    }
    
    public LinkedList<GameObject> getTiles(){
        return tiles;
    }
    
    /// Checks if the coordinates are equal to x and y
    public boolean checkCoords(int x, int y){
        if(x == xCoord && y == yCoord){
            return true;
        }
        return false;
    }
    
    /// Get methods for coordinate values
    public int getXCoord(){
        return xCoord;
    }
    public int getYCoord(){
        return yCoord;
    }
    
}
