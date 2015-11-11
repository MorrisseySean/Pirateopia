/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leagueoflegendsmanager;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Morro
 */
public class Handler {
    // A list of all objects in the game
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    
    // Loops through all game objects and their update method
    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }
    
    // Loops through all game objects and calls their render method
    public void render(Graphics g){
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
}
