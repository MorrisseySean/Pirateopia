/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leagueoflegendsmanager;

import java.awt.Graphics;

/**
 *
 * @author Morro
 */
public abstract class GameObject {
    
    protected int x, y, w, h;
    protected ID id;
    protected int velX, velY;
    
    public GameObject(int x, int y, int w, int h, ID id){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.id = id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    // Set Methods
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setVelX(int x){
        velX = x;
    }
    
    public void setVelY(int y){
        velY = y;
    }
    public void setID(ID id){
        this.id = id;
    }
    
    // Get Methods 
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public ID getID(){
        return this.id;
    }
}
