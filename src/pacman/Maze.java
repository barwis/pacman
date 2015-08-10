/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Aleksandra
 */
public class Maze {
    
    public static boolean grid[][] = new boolean[Display.stageWidth][Display.stageHeight];

    public Maze(){
    
    }
    public void paint(Graphics g){
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]== true)
                {
                    g.setColor(Color.BLACK); // first, set color of the background
                    g.fillRect(i*Display.blockSize, j*Display.blockSize, Display.blockSize , Display.blockSize); // then fill it with this color
                    
                }
                else{
                    g.setColor(Color.BLUE); // first, set color of the background
                    g.fillRect(i*Display.blockSize, j*Display.blockSize, Display.blockSize , Display.blockSize);       
                }
                
//                g.setFont(new Font("Arial", Font.PLAIN, 8));
//                g.setColor(Color.WHITE);
//                g.drawString("[" + i + "," + j + "]", i*Display.blockSize, j*Display.blockSize + 10);
                
            }
        }

        
    }
    
    public void fillMaze(){

        for(int i = 0; i < grid.length; i++ ) //feeling the array
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                grid [i][j] = true;
                  
            }
        }
        
        for(int i = 0; i < grid.length; i++) // setting the bariers - top
        {
            grid [i][0] = false;
        }
        for(int i = 0; i < grid[0].length; i++) // left
        {
            grid [0][i] = false;
        }
        for(int i = 0; i < grid[0].length; i++) //right
        {
            grid [grid.length-1][i] = false; 
        }
        for(int i = 0; i < grid.length; i++) // bottom
        {
            grid [i][grid[0].length-1] = false;
        }
        
        //obstacles
        grid[2][2]=false;
        grid[3][2]= false;
        grid[4][2]=false;
        grid[5][2]=false;
        grid[2][3]=false;
        grid[3][3]= false;
        grid[4][3]=false;
        grid[5][3]=false;
        grid[2][4]=false;
        grid[3][4]= false;
        grid[4][4]=false;
        grid[5][4]=false;
        
        grid[2][6]=false;
        grid[3][6]= false;
        grid[4][6]=false;
        grid[5][6]=false;
        grid[2][7]=false;
        grid[3][7]= false;
        grid[4][7]=false;
        grid[5][7]=false;
        
        for (int i = 1; i <= 5; i++ ){
            for(int j = 9; j <= 19; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 2; i <= 5; i++ ){
            for(int j = 21; j <= 22; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 4; i <= 5; i++ ){
            for(int j = 23; j <= 25; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 1; i <= 2; i++ ){
            for(int j = 24; j <= 25; j++){
                grid[i][j]= false;
            }
        }
        
        for (int i = 2; i <= 11; i++ ){
            for(int j = 27; j <= 28; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 7; i <= 8; i++ ){
            for(int j = 24; j <= 26; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 7; i <= 11; i++ ){
            for(int j = 2; j <= 4; j++){
                grid[i][j]= false;
            }
        }        
        for (int i = 13; i <= 14; i++ ){
            for(int j = 1; j <= 4; j++){
                grid[i][j]= false;
            }
        }        
        for (int i = 16; i <= 20; i++ ){
            for(int j = 2; j <= 4; j++){
                grid[i][j]= false;
            }
        }        
        for (int i = 22; i <= 25; i++ ){
            for(int j = 2; j <= 4; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 7; i <= 8; i++ ){
            for(int j = 6; j <= 13; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 9; i <= 11; i++ ){
            for(int j = 9; j <= 10; j++){
                grid[i][j]= false;
            }
        }        
        for (int i = 10; i <= 17; i++ ){
            for(int j = 6; j <= 7; j++){
                grid[i][j]= false;
            }
        } 
        for (int i = 13; i <= 14; i++ ){
            for(int j = 8; j <= 10; j++){
                grid[i][j]= false;
            }
        }        
        for (int i = 16; i <= 18; i++ ){
            for(int j = 9; j <= 10; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 19; i <= 20; i++ ){
            for(int j = 6; j <= 13; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 22; i <= 25; i++ ){
            for(int j = 6; j <= 7; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 22; i <= 26; i++ ){
            for(int j = 9; j <= 19; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 19; i <= 20; i++ ){
            for(int j = 15; j <= 19; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 16; i <= 20; i++ ){
            for(int j = 21; j <= 22; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 7; i <= 11; i++ ){
            for(int j = 21; j <= 22; j++){
                grid[i][j]= false;
            }
        }        
        for (int i = 7; i <= 8; i++ ){
            for(int j = 15; j <= 19; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 10; i <= 17; i++ ){
            for(int j = 18; j <= 19; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 13; i <= 14; i++ ){
            for(int j = 20; j <= 22; j++){
                grid[i][j]= false;
            }
        } 
        for (int i = 15; i <= 16; i++ ){
            for(int j = 13; j <= 15; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 22; i <= 25; i++ ){
            for(int j = 21; j <= 22; j++){
                grid[i][j]= false;
            }
        } 
        for (int i = 22; i <= 23; i++ ){
            for(int j = 23; j <= 25; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 10; i <= 17; i++ ){
            for(int j = 24; j <= 25; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 19; i <= 20; i++ ){
            for(int j = 24; j <= 26; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 16; i <= 25; i++ ){
            for(int j = 27; j <= 28; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 13; i <= 14; i++ ){
            for(int j = 26; j <= 28; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 25; i <= 26; i++ ){
            for(int j = 24; j <= 25; j++){
                grid[i][j]= false;
            }
        }
        for (int i = 10; i <= 12; i++ ){
            grid[i][12]= false;
        } 
        for (int i = 15; i <= 17; i++ ){
            grid[i][12]= false;
        } 
        for (int i = 10; i <= 17; i++ ){
            grid[i][16]= false;
        }
        for (int j = 12; j <= 16; j++ ){
            grid[10][j]= false;
        }
        for (int j = 12; j <= 16; j++ ){
            grid[17][j]= false;
        }
        
        grid[11][13] = false;
        grid[15][14] = true;
        grid[16][14] = false;
        grid[11][15] = false;
        grid[12][15] = false;
        grid[13][15] = false;
        grid[14][15] = false;
        grid[11][14] = false;
        grid[15][13] = true;
        grid[13][12] = false;
        
//        for ( int i = 11; i < 17; i++) {
//            for (int j = 13; j < 16; j++) {
//                grid[i][j] = true;
//            }
//        }
//        for (int i = 15; i < 17; i++) {
//            for (int j = 13; j < 16; j++) {
//                grid[i][j] = true;
//            }
//        }
//        
//        grid[13][12] = false;
//        grid[14][12] = false;

//        for( int i = 0; i <=5; i++){
//            grid[i][14]= true;
//        }
//        for( int i = 22; i <= 27; i++){
//            grid[i][14]= true;
//        }        
    }
}

