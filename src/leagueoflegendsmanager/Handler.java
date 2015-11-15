/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leagueoflegendsmanager;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    // A list of all objects in the game
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    LinkedList<Island> islands = new LinkedList<Island>();
    Island currentIsland;
    
    // Loops through all game objects and their update method
    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
        
        currentIsland.tick();
    }
    
    // Loops through all game objects and calls their render method
    public void render(Graphics g){
        currentIsland.render(g);
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
        
    }
    
    // Adds object to list of all objects
    public void addObject(GameObject object){
        objects.add(object);
    }
    
    // Removes object from list of all objects
    public void remove(GameObject object){
        objects.remove(object);
    }
    
    public void setCurrentIsland(Island isl){
        currentIsland = isl;
    }
    
    public Island getCurrentIsland(){
        try{
            return currentIsland;
        }catch(Exception e){
            System.out.println("Error: currentIsland not found");
            return null;
        }
        
    }
    
    public int nextIsland(int x, int y){
        if(currentIsland != null){
            /// Get the coordinates of the new island
            int newX = currentIsland.getXCoord() + x;
            int newY = currentIsland.getYCoord() + y;

            /// Check if the island already exists
            for(int i = 0; i < islands.size(); i++){
                /// If it does, set as current and leave method
                if(islands.get(i).getXCoord() == newX && islands.get(i).getYCoord() == newY){
                    currentIsland = islands.get(i);
                    return 1;
                }
            }

            /// Create a new island at the specified coordiantes
            islands.add(new Island(newX, newY, ID.Island));
            currentIsland = islands.get(islands.size() - 1);
            return 0;
        }
        else{
            islands.add(new Island(0, 0, ID.Island));
            currentIsland = islands.get(islands.size() - 1);
            return 0;
        }
    }
}
