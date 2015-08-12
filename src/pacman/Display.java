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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.*;
import java.io.*;

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
    public static boolean gameRunning = true;
        
    static Timer time;
    private int timerBase = 200;
    
    Ball ball;
        Maze maze;
    Food food;
    
    Ghost[] ghosts = new Ghost[3];
    public static Clip clip;

    
        public Display(){
        this.setPreferredSize(new Dimension(stageWidth*blockSize, stageHeight*blockSize));
        maze = new Maze();
        food = new Food();
        ball = new Ball(13,23);
        ghosts[0] = new Ghost(11, 14);
        ghosts[1] = new Ghost(13, 14);
        ghosts[2] = new Ghost(16, 14);

        setTimerSpeed(); 
       
        maze.fillMaze();
        food.fillFoodMaze();
        
        this.setFocusable(true);
        this.addKeyListener(this);
        play(Pacman.class.getResource("../sounds/pacman_chomp.wav").getPath(), true);

        time.start();
    }
    
    private void setTimerSpeed() {
        time = new Timer(( timerBase / level), this);
    }
    
    public static void play(String filename, boolean infinite)
    {
        try
        {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            if (infinite) {
                clip.loop(clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
                 
 
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }
    
    public void displayStats(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        
        BufferedImage life = null;
        
        try {
            life = ImageIO.read(Pacman.class.getResource("../images/life.png"));
        } catch (IOException ex) {

        }
        
        g.drawString("Lives:", 30, 310);
        for (int i = 0; i < ball.lives; i++) {
            g.drawImage(life, (90 + i * 20), 295, null);
        }
        
        g.drawString("Points: " + points, 30, 340);
        g.drawString("Level: " + level, 30, 370);
        
        g.drawString("Food left: " + food.getRemainingFood(), 30, 400);
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
        if (Ball.superPacman > 0) {
            Ball.superPacman--;
        }
    }
    
    private void checkCollision() {
        time.stop();
        for (Ghost ghost : ghosts) {
            if (ball.x == ghost.x && ball.y == ghost.y) {
                ball.x = ghost.x;
                ball.y = ghost.y;
                if (Ball.superPacman > 0) {
                    points += (level * 200);
                    ghost.isDead = 30;
                    play(Pacman.class.getResource("../sounds/pacman_eatghost.wav").getPath(), false);
                } else {
                    death();
                    break;
                }
            }
        }
        time.start();
    }
    
    private void death() {

        gameRunning = false;
        ball.lives -= 1;
        clip.stop();
        play(Pacman.class.getResource("../sounds/pacman_death.wav").getPath(), false);
        time.stop();
        
        if (ball.lives > 0) {
            JOptionPane.showMessageDialog(this,  String.format("You died. You have %1$d lives left", ball.lives));
            ball.resetPosition();
            for (Ghost ghost : ghosts) {
                ghost.resetPosition();
            }
            gameRunning = true;
            time.start();
            clip.stop();
            play(Pacman.class.getResource("../sounds/pacman_chomp.wav").getPath(), true);
        } else {
            endGame();
        }
    }
    
    private void endGame() {
        time.stop();
        gameRunning = false;
        int result = JOptionPane.showOptionDialog(this,  String.format("The End \n Your high score is: %1$d \n Click OK to play again", points), "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null );
        if (result == JOptionPane.OK_OPTION) {
            restart();
            clip.stop();
            play(Pacman.class.getResource("../sounds/pacman_chomp.wav").getPath(), true);
        }
        if (result == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }
    
    private void restart() {

        points = 0;
        ball.lives = 3;
        level = 1;
        ball.resetPosition();
        for (Ghost ghost : ghosts) {
            ghost.resetPosition();
        }
        
        setTimerSpeed();
        

        gameRunning = true;
        time.start();
    }
    
    private void eat() {
        if (Food.foodGrid[Ball.x][Ball.y] > 0) {
            if (Food.foodGrid[Ball.x][Ball.y] > 1 ) {
                Ball.superPacman = 30;
            }
            Food.foodGrid[Ball.x][Ball.y] = 0;
            points += 10 * level;
        }
        if (food.getRemainingFood() == 0) {
            gameRunning = false;
            time.stop();
            JOptionPane.showMessageDialog(this,  String.format("Level cleared. Click ok to begin level %1$d", level+1));

            level++;
            food.fillFoodMaze();
            ball.resetPosition();
            for (Ghost ghost : ghosts) {
                ghost.resetPosition();
            }
            gameRunning = true;
//            time = new Timer((100 / level), this);
            setTimerSpeed();
            
            time.start();
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
