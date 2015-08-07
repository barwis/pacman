/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Aleksandra
 */
public class Ghost {
    
    Color color = Color.MAGENTA;
    public int defaultX;
    public int defaultY;
    public int x;
    public int y;
    public int ghostSize = Display.blockSize;
    Direction direction = Direction.up;
    
    private List<Direction> movement = new ArrayList<Direction>();
    
    
    public Ghost(int _x, int _y){
        defaultX = _x;
        defaultY = _y;
        x = _x;
        y = _y;

    }
    
    public void setColor(Color c) {
        color = c;
    }
    
    public void resetPosition() {
        x = defaultX;
        y = defaultY;
    }
    
    public void paint(Graphics g){
        BufferedImage image = null;
        
        
        
        g.setColor(color);
//        g.drawRect(x * Display.blockSize, y * Display.blockSize, ghostSize, ghostSize);
        g.fillRect(x * Display.blockSize, y * Display.blockSize, ghostSize, ghostSize);
        
        String ghostURL = "ghost.png";
     
        
        switch (direction) {
            case up: 
                ghostURL = "ghost_up.png";
                break;
            case down:
                ghostURL = "ghost_down.png";
                break;
            case left: 
                ghostURL = "ghost_left.png";
                break;
            case right:
                ghostURL = "ghost_right.png";
                break;
            default:
                ghostURL = "ghost_left.png";
                break;
            
        }
        
        try {
            image = ImageIO.read(Pacman.class.getResource("../images/" + ghostURL));
        } catch (IOException ex) {
            
        }
        g.drawImage(image, x * Display.blockSize, y * Display.blockSize, null);
        
    }
    
    public void  update()
    {
        if (!Display.gameRunning) {
            return;
        }
        movement.clear();
        
        if (Maze.grid[x][y-1] && direction != Direction.down) {
            movement.add(Direction.up);
        }
        if (Maze.grid[x][y+1] && direction != Direction.up) {
            movement.add(Direction.down);
        }
        if (Maze.grid[x+1][y] && direction != Direction.left) {
            movement.add(Direction.right);
        }
        if (Maze.grid[x-1][y] && direction != Direction.right) {
            movement.add(Direction.left);
        }
        
        
        if (movement.contains(Direction.left) && movement.contains(Direction.right)) {
            if (Ball.x < x) {
                movement.remove(Direction.right);
            } else {
                movement.remove(Direction.left);
            }
        }
        
        if (movement.contains(Direction.up) && movement.contains(Direction.down)) {
            if (Ball.y < y) {
                movement.remove(Direction.down);
            } else {
                movement.remove(Direction.up);
            }
        }

        // pick random direction from possible directions list
        Random randomGen = new Random();// create a random number generator

        int theRandomNumber = randomGen.nextInt(movement.size());// generate a random number, between 0 and n-1 (0,1,2 in this case)
        direction = movement.get(theRandomNumber);
        
        // setting up the movement and collision detection
        if (direction == Direction.up) {
            y = y - 1;
        } else if (direction == Direction.down) {
            y = y + 1;
        } else if (direction == Direction.right) {
            x = x + 1;
        } else if (direction == Direction.left) {
            x = x - 1;
        }

    }    
    
}
