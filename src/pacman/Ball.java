/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Aleksandra
 */
public class Ball {
    
    Color color = Color.yellow;
    public static int x;
    public static int y;
    public int directionAngle = 30;
    public int mouthAngle = 300;
    Direction direction;
    public int superPacman = 0;
    
    public Ball(int _x, int _y){
        
        x = _x;
        y = _y;
        direction = Direction.right;
    }
    public void paint(Graphics g){
        
        g.setColor(color);
        g.fillArc(x * Display.blockSize, y * Display.blockSize, Display.blockSize,Display.blockSize, directionAngle, mouthAngle);
        
    }
    
    public void setDirectionAngle(int a) {
        directionAngle = a;
    }
    public void setDirection(Direction d){
        if ( direction != d) {
            direction = d;
        }
    }
    public void setColor(Color c){
       color = c;
    }
    public void update(){
        if(mouthAngle == 360)
        {
            mouthAngle = 300;
        }
        else{
            mouthAngle = 360;
        } 
      
        // setting up the movement and collision detection
        if(direction == Direction.up && Maze.grid[x][y-1])
        {
            y = y - 1;
         
        }
        else if(direction == Direction.down && Maze.grid[x][y+1])
        {
            y = y + 1;

            
        }
        else if(direction == Direction.right && Maze.grid[x+1][y])
        {
            x = x + 1; 
            
        }
        else if(direction == Direction.left && Maze.grid[x-1][y])
        {
            x = x - 1;                
        }
//        
//        if(Food.foodGrid[x][y] == 1){
// 
//            Food.foodGrid[x][y] = 0;
//        }
//        else if(Food.foodGrid[x][y] == 2){
//            Food.foodGrid[x][y] = 0;
//            setColor(Color.GREEN);
//            superPacman = 30;
//        }
//        if(superPacman > 0){
//            superPacman--; 
//        }
//        else {
//           setColor(Color.YELLOW);
//        }

    }

    
}