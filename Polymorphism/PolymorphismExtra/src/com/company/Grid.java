package com.company;

public class Grid {
    int row = 10;
    int column = 10;
    int[][] grid = new int[row][column];

    // function to initialize grid with a value, created this function to not repeat the code while using polymorphism
    public void initialize_grid(int value){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = value;
            }
        }
    }

    public Grid(){
        initialize_grid(0);
    }

    public Grid(int value){
        initialize_grid(value);
    }

    public Grid(String order){

        int starter_value;

        if(order.equals("acending")){
            starter_value = 0; // acending starts from 0
        } else {
            starter_value = row * column; // decending starts for the max value, which in this case is row * column
        }


        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){

                if(order.equals("acending")){
                    grid[i][j] = starter_value;
                    starter_value++; // acending adds up the values
                }

                if(order.equals("decending")){
                    grid[i][j] = starter_value;
                    starter_value--; // decending, substracts the values
                }

            }
        }
    }

    public void display(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] < 10){
                    System.out.print(grid[i][j] + "   "); // padding
                } else if (grid[i][j] > 99){
                    System.out.print(grid[i][j] + " ");
                } else {
                    System.out.print(grid[i][j] + "  ");
                }

            }
            System.out.println();
        }
    }

    public int getter(int row, int col){
        return grid[row][col];
    }

    public void setter(int row, int col, int value){
        grid[row][col] = value;
    }
}