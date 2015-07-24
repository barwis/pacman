/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.util.TimerTask;
import javax.swing.JFrame;



/**
 *
 * @author Aleksandra
 */
enum Direction{
    up,right,down,left 
}


public class Pacman extends JFrame{
    
    public Pacman(){
       setTitle("Pacman"); // set the tiele of the frame
       setResizable(false); // for not letting the user to resize the window
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // without it closing window is very difficult for user
       
       add(new Display());
       setVisible(true); // to make display visible
       pack(); // to make the display fit in the window
    
    }

    
    public static void main(String[] args) {
        new Pacman();
    }
    
}

