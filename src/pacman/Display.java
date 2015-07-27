/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Aleksandra
 */
public class Display extends JPanel implements ActionListener, KeyListener{
    
    public static int stageWidth = 28;
    public static int stageHeight = 31;
    public static int blockSize = 30;
    
    private boolean endGame = false;
    Ball ball;
    static Timer time;
    Maze maze;
    Food food;
    Ghost ghost;
    
    Ghost ghost1;
    Ghost ghost2;
    
    
    public Display(){
        this.setPreferredSize(new Dimension(stageWidth*blockSize, stageHeight*blockSize));
        maze = new Maze();
        food = new Food();
        ball = new Ball(13,23);
        ghost = new Ghost(13, 11);

        time = new Timer(100, this);
        
        maze.fillMaze();
        food.fillFoodMaze();
        
        this.setFocusable(true);
        this.addKeyListener(this);
        time.start();

        
        
    }
    @Override
    public void paint(Graphics g){
        maze.paint(g);
        food.paint(g);
        ball.paint(g);
        
        g.setColor(Color.MAGENTA);
        ghost.paint(g);
//        
//        g.setColor(Color.RED);
//        ghost1.paint(g);

//        g.setColor(Color.PINK);
//        ghost2.paint(g);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e){

        checkCollision();
        
        if (!endGame) {
            repaint();
            ghost.update();
            checkCollision();
        }
        
        if (!endGame) {
            ball.update();
        }
//        checkCollision();
//
//        ball.update();
//        
//        checkCollision();
//        
//        ghost.update();
//        checkCollision();
//        
//        repaint();
//
//        if (endGame) {
//            ball.x = ghost.x;
//            ball.y = ghost.y;
//            JOptionPane.showMessageDialog(null, "You died!");
//        }
//        
    }
    
    private void checkCollision() {
        
        if (ball.x == ghost.x && ball.y == ghost.y) {
            
            ball.x = ghost.x;
            ball.y = ghost.y;
            time.stop();
            endGame = true;
            JOptionPane.showMessageDialog(null, "You died!");
        }

    }
    
    
    public void keyReleased(KeyEvent e)
    {
        
    }
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ball.setDirection(Direction.up);
            ball.setDirectionAngle(120);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            ball.setDirection(Direction.right);
            ball.setDirectionAngle(30);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            ball.setDirection(Direction.down);
            ball.setDirectionAngle(300);
        }
         else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            ball.setDirection(Direction.left);
            ball.setDirectionAngle(210);
        }
    }
    public void keyTyped(KeyEvent e)
    {
        
    }
}
