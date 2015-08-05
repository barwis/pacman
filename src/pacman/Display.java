/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    
    public static int points = 0;
    private int level = 1;
    
    private boolean endGame = false;
    Ball ball;
    static Timer time;
    Maze maze;
    Food food;
    Ghost ghost;
    
    Ghost[] ghosts = new Ghost[3];
    
//    Ghost ghost1;
//    Ghost ghost2;
    
    
    public Display(){
        this.setPreferredSize(new Dimension(stageWidth*blockSize, stageHeight*blockSize));
        maze = new Maze();
        food = new Food();
        ball = new Ball(13,23);
        ghosts[0] = new Ghost(13, 11);
        ghosts[1] = new Ghost(14, 11);
        ghosts[2] = new Ghost(15, 11);
        
        // create ghosts 

        time = new Timer((100 / level), this);
        
        maze.fillMaze();
        food.fillFoodMaze();
        
        this.setFocusable(true);
        this.addKeyListener(this);
        time.start();

    }
    
    public void displayStats(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        
        BufferedImage life = null;
        
        try {
            life = ImageIO.read(Pacman.class.getResource("../images/life.png"));
        } catch (IOException ex) {

        }
        
        g.drawString("Lives:", 30, 300 + 10);
        for (int i = 0; i < ball.lives; i++) {
            g.drawImage(life, (90 + i * 20), 295, null);
        }
        
    }
    
    @Override
    public void paint(Graphics g){
        maze.paint(g);
        food.paint(g);
        ball.paint(g);
        
        ghosts[0].setColor(Color.RED);
        ghosts[0].paint(g);
        
        ghosts[1].setColor(Color.GREEN);
        ghosts[1].paint(g);
        
        ghosts[2].setColor(Color.MAGENTA);
        ghosts[2].paint(g);
        
        displayStats(g);
        
//        g.setColor(Color.RED);
//        ghost1.paint(g);
//
//        g.setColor(Color.PINK);
//        ghost2.paint(g);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e){
        checkCollision();
        
        if (!endGame) {
            repaint();
            
            for (Ghost ghost: ghosts) {
                ghost.update();
            }
            
            checkCollision();
        }
        
        if (!endGame) {
            ball.update();
            eat();
        }
    }
    
    private void checkCollision() {
        
        if (endGame) {
        time.stop();

            JOptionPane.showMessageDialog(null, "You died!");
        }
        
        for (Ghost ghost : ghosts) {
            if (ball.x == ghost.x && ball.y == ghost.y) {

                ball.x = ghost.x;
                ball.y = ghost.y;
                endGame = true;
                break;

            }
        }
        
    }
    
    private void eat() {
        if (Food.foodGrid[Ball.x][Ball.y] > 0) {
            Food.foodGrid[Ball.x][Ball.y] = 0;
            points += 10;
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
