/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leagueoflegendsmanager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.util.HashSet;

/**
 *
 * @author Morro
 */
public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 1080, HEIGHT = WIDTH /12 * 9, RATIO = WIDTH/12;
    private Thread mainThread;
    private boolean isRunning = false;
    private Handler handler;
    private HUD hud;
    public Island currentIsland;
    public Game(){
        /// Initialize handler to control gameobjects
        handler = new Handler();
        
        hud = new HUD();
        
        /// Tells game to keep track of key inputs
        this.addKeyListener(new KeyInput(handler));
        
        /// Initialise the game window
        new Window(WIDTH, HEIGHT, "League Of Legends Manager", this);        
        
        ///Add an island
        currentIsland = new Island(0, 0, ID.Island);
        handler.addObject(currentIsland);
        handler.setCurrentIsland(currentIsland);

        
        /// Add a new player to the handler
        handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player, handler));
        
    }
    
    public synchronized void start(){
        mainThread = new Thread(this);
        mainThread.start();
        isRunning = true;
    }
    
    public synchronized void stop(){
        try{
            mainThread.join();
            isRunning = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();                
                delta--;
            }
            if(isRunning){
                render();
            }
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }                 
        }
        stop();
    }
    
    private void tick(){
        handler.tick();
        hud.tick();
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        // Set background color to gray
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        hud.render(g);
        g.dispose();
        bs.show();
    }
    
    public static int clamp(int var, int min, int max){
        if(var > max){
            return max;
        }
        else if(var < min){
            return min;
        }
        else{
            return var;
        }
    }
    
    public static boolean checkBoxCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2){
        if(x1 > x2 + w2 ||
                x1 + w1 < x2 ||
                y1 > y2 + h2 ||
                y1 + h1 < y2){
            return false;
        }
        return true;
    }
    
}
