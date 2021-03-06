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
    
    private boolean randomMovement = false;
    public int isDead = 0;
    
    
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
        direction = Direction.up;
        randomMovement = false;
        isDead = 0;
    }
    
    public void paint(Graphics g){
        BufferedImage image = null;
        
        if (Ball.superPacman > 0 ) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(color);
        }

        g.fillRect(x * Display.blockSize, y * Display.blockSize, ghostSize, ghostSize);
        
        String ghostURL = "ghost.png";
        
        if (isDead > 0) {
            ghostURL = "ghost_dead.png";
        } else {
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
        }
        
        try {
            image = ImageIO.read(Pacman.class.getResource("../images/" + ghostURL));
        } catch (IOException ex) {
        }
        
        g.drawImage(image, x * Display.blockSize, y * Display.blockSize, null);
    }
    
    private void pathFind(int destX, int destY) {
        List<Direction> possibleDirections = new ArrayList<Direction>();
        
        if (Maze.grid[x][y - 1] && direction != Direction.down) {
                possibleDirections.add(Direction.up);
        }
        if (Maze.grid[x][y + 1] && direction != Direction.up) {
                possibleDirections.add(Direction.down);
        }
        if (Maze.grid[x + 1][y] && direction != Direction.left) {
            possibleDirections.add(Direction.right);
        }
        if (Maze.grid[x - 1][y] && direction != Direction.right) {
                possibleDirections.add(Direction.left);
        }
        
        if (possibleDirections.contains(Direction.left) && possibleDirections.contains(Direction.right) && randomMovement) {
            
            if (destX < x) {
                possibleDirections.remove(Direction.right);
            } else {
                possibleDirections.remove(Direction.left);
            }
        }
        
        if (possibleDirections.contains(Direction.up) && possibleDirections.contains(Direction.down) && randomMovement) {
            if (destY < y) {
                possibleDirections.remove(Direction.down);
            } else {
                possibleDirections.remove(Direction.up);
            }
        }
        
        if (!randomMovement && possibleDirections.contains(Direction.up)) {
            direction = Direction.up;
        } else {

            Random randomGen = new Random();// create a random number generator

            int theRandomNumber = randomGen.nextInt(possibleDirections.size());// generate a random number, between 0 and n-1 (0,1,2 in this case)
            direction = possibleDirections.get(theRandomNumber);
        }
    }
    
    public void  update()
    {
        if (!Display.gameRunning) {
            return;
        }
        
        
        if ( ( y < 12 || y > 15) && !randomMovement) {
            randomMovement = true;
        }

        if (isDead > 0) {
            pathFind(defaultX, defaultY);
        } else {
            pathFind(Ball.x, Ball.y);
        }
        
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
        
        if (isDead > 0) {
            isDead--;
        }
    }    
}
