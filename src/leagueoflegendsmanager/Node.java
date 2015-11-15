/*
 * Copyright(C) WolfbrandGames.com from 2015 to Present
 * All Rights Reserved  * 
 */
package leagueoflegendsmanager;

import java.util.LinkedList;

public class Node {
    int x;
    int y;
    LinkedList<Node> connected = new LinkedList<Node>();
    
    public void addNode(Node n){
        connected.add(n);
    }
}
