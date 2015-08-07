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
public class Food {
    
    public static int foodGrid[][] = new int[Display.stageWidth][Display.stageHeight];
    
    public Food(){
        
    }
    public void paint(Graphics g){
        for(int i = 0; i < foodGrid.length; i++){
            for(int j = 0; j < foodGrid[0].length; j++){
                if(foodGrid[i][j]== 1)
                {
                    g.setColor(Color.YELLOW); // first, set color
                    g.fillOval((i*Display.blockSize ) + 10, (j*Display.blockSize) + 10, 10, 10); // then fill it with this color
                    
                }

                else if(foodGrid[i][j] == 2){
                    g.setColor(Color.GREEN); // first, set color of the background
                    g.fillOval((i*Display.blockSize ) + 7, (j*Display.blockSize) + 7, 15, 15);;       
                } 
            }
        }    
    }
    
    public int getRemainingFood() {
        int count = 0;
        for (int i = 0; i < foodGrid.length; i++) {
            for (int j = 0; j < foodGrid[0].length; j++) {
                if(foodGrid[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void fillFoodMaze()
    { 
        
//        foodGrid[18][26] = 1;
        for(int i = 0; i < foodGrid.length; i++ ) //feeling thr array
        {
            for(int j = 0; j < foodGrid[0].length; j++)
            {
                foodGrid [i][j] = 1;
            }
        }

            for(int i = 0; i < foodGrid.length; i++) // setting the bariers - top
            {
                foodGrid [i][0] = 0;
            }
            for(int i = 0; i < foodGrid[0].length; i++) // left
            {
               foodGrid [0][i] = 0;
            }
            for(int i = 0; i < foodGrid[0].length; i++) //right
            {
               foodGrid [foodGrid.length-1][i] = 0; 
            }
            for(int i = 0; i < foodGrid.length; i++) // bottom
            {
                foodGrid [i][foodGrid[0].length-1] = 0;
            }

            //obstacles
            foodGrid[2][2]=0;
            foodGrid[3][2]= 0;
            foodGrid[4][2]=0;
            foodGrid[5][2]=0;
            foodGrid[2][3]=0;
            foodGrid[3][3]= 0;
            foodGrid[4][3]=0;
            foodGrid[5][3]=0;
            foodGrid[2][4]=0;
            foodGrid[3][4]= 0;
            foodGrid[4][4]=0;
            foodGrid[5][4]=0;

            foodGrid[2][6]=0;
            foodGrid[3][6]= 0;
            foodGrid[4][6]=0;
            foodGrid[5][6]=0;
            foodGrid[2][7]=0;
            foodGrid[3][7]= 0;
            foodGrid[4][7]=0;
            foodGrid[5][7]=0;

            for (int i = 1; i <= 5; i++ ){
                for(int j = 9; j <= 19; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 2; i <= 5; i++ ){
                for(int j = 21; j <= 22; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 4; i <= 5; i++ ){
                for(int j = 23; j <= 25; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 1; i <= 2; i++ ){
                for(int j = 24; j <= 25; j++){
                    foodGrid[i][j]= 0;
                }
            }

            for (int i = 2; i <= 11; i++ ){
                for(int j = 27; j <= 28; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 7; i <= 8; i++ ){
                for(int j = 24; j <= 26; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 7; i <= 11; i++ ){
                for(int j = 2; j <= 4; j++){
                    foodGrid[i][j]= 0;
                }
            }        
            for (int i = 13; i <= 14; i++ ){
                for(int j = 1; j <= 4; j++){
                    foodGrid[i][j]= 0;
                }
            }        
            for (int i = 16; i <= 20; i++ ){
                for(int j = 2; j <= 4; j++){
                    foodGrid[i][j]= 0;
                }
            }        
            for (int i = 22; i <= 25; i++ ){
                for(int j = 2; j <= 4; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 7; i <= 8; i++ ){
                for(int j = 6; j <= 13; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 9; i <= 11; i++ ){
                for(int j = 9; j <= 10; j++){
                    foodGrid[i][j]= 0;
                }
            }        
            for (int i = 10; i <= 17; i++ ){
                for(int j = 6; j <= 7; j++){
                    foodGrid[i][j]= 0;
                }
            } 
            for (int i = 13; i <= 14; i++ ){
                for(int j = 8; j <= 10; j++){
                    foodGrid[i][j]= 0;
                }
            }        
            for (int i = 16; i <= 18; i++ ){
                for(int j = 9; j <= 10; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 19; i <= 20; i++ ){
                for(int j = 6; j <= 13; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 22; i <= 25; i++ ){
                for(int j = 6; j <= 7; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 22; i <= 26; i++ ){
                for(int j = 9; j <= 19; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 19; i <= 20; i++ ){
                for(int j = 15; j <= 19; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 16; i <= 20; i++ ){
                for(int j = 21; j <= 22; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 7; i <= 11; i++ ){
                for(int j = 21; j <= 22; j++){
                    foodGrid[i][j]= 0;
                }
            }        
            for (int i = 7; i <= 8; i++ ){
                for(int j = 15; j <= 19; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 10; i <= 17; i++ ){
                for(int j = 18; j <= 19; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 13; i <= 14; i++ ){
                for(int j = 20; j <= 22; j++){
                    foodGrid[i][j]= 0;
                }
            }        
            for (int i = 22; i <= 25; i++ ){
                for(int j = 21; j <= 22; j++){
                    foodGrid[i][j]= 0;
                }
            } 
            for (int i = 22; i <= 23; i++ ){
                for(int j = 23; j <= 25; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 10; i <= 17; i++ ){
                for(int j = 24; j <= 25; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 19; i <= 20; i++ ){
                for(int j = 24; j <= 26; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 16; i <= 25; i++ ){
                for(int j = 27; j <= 28; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 13; i <= 14; i++ ){
                for(int j = 26; j <= 28; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 25; i <= 26; i++ ){
                for(int j = 24; j <= 25; j++){
                    foodGrid[i][j]= 0;
                }
            }
            for (int i = 10; i <= 12; i++ ){
                foodGrid[i][12]= 0;
            } 
            for (int i = 15; i <= 17; i++ ){
                foodGrid[i][12]= 0;
            } 
            for (int i = 10; i <= 17; i++ ){
                foodGrid[i][16]= 0;
            }
            for (int j = 12; j <= 16; j++ ){
                foodGrid[10][j]= 0;
            }
            for (int j = 12; j <= 16; j++ ){
                foodGrid[17][j]= 0;
            }        


            for (int i = 11; i <= 16; i++ ){
                for(int j = 13; j <= 15; j++){
                    foodGrid[i][j]= 0;
                }
            }

            for (int i = 9; i <= 18; i++ ){
                foodGrid[i][11]= 0;
            }  
            for (int i = 9; i <= 18; i++ ){
                foodGrid[i][17]= 0;
            }
            for (int j = 11; j <= 19; j++ ){
                foodGrid[9][j]= 0;
            }          
            for (int j = 11; j <= 19; j++ ){
                foodGrid[18][j]= 0;
            } 
            for (int i = 7; i <= 8; i++ ){
                foodGrid[i][14]= 0;
            }        
             for (int i = 19; i <= 20; i++ ){
                foodGrid[i][14]= 0;
            } 
             for (int i = 13; i <= 14; i++ ){
                foodGrid[i][12]= 0;
            }   
            for (int j = 9; j <= 10; j++ ){
                foodGrid[12][j]= 0;
            } 
            for (int j = 9; j <= 10; j++ ){
                foodGrid[15][j]= 0;
            }
            
            //superfood
            foodGrid[26][29] = 2;
        }
}
