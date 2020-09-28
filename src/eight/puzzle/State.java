/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight.puzzle;


/**
 *
 * @author A
 */
public class State {
    private State parent;   //Parent state
    private int[][] matrix; //The matrix
    private int x, y;       //Blank tile's coordinates
    private int cost;       //Number of misplaced tiles
    private int level;      //Number of moves

    public State(int m, int n) {
        this.matrix = new int[m][n];
    }
    
    public State(int[][] matrix) {
        this.matrix = matrix;
    }
    public State newState(int x, int y, int newX, int newY, int level){
        State state = new State(this.matrix);
        
        //Copies datas from this State to the new State
        state.setParent(this);
        int[][] clone = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            clone[i] = matrix[i].clone();
        }
        state.setMatrix(clone);
        state.setLevel(level);
        
        //Move tile by 1
        int temp = state.getMatrix(x, y);
        state.setMatrix(x, y, state.getMatrix(newX, newY));
        state.setMatrix(newX, newY, temp);
        
        //Update new blank tile coordinate
        state.setX(newX);
        state.setY(newY);
        
        return state;
    }
    
    //Calculate cost
    public int cost(int desiredMatrix[][]){
        int m = this.matrix.length;
        int n = this.matrix[0].length;
        if (m == desiredMatrix.length && n == desiredMatrix[0].length) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (this.matrix[i][j] != 0 && this.matrix[i][j] != desiredMatrix[i][j]) {
                        count += 1;
                    }
                }
            }
            
            return count;
        }
        else
            return -1;
    }
    
    //Compare two state
    public boolean compare(State state){
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getCollumn(); j++) {
                if (matrix[i][j] != state.getMatrix(i, j)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    //Getters and Setters
    public State getParent() {
        return parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public int getMatrix(int x, int y) {
        return matrix[x][y];
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public void setMatrix(int x, int y, int z) {
        this.matrix[x][y] = z;
    }
    
    public int getRow(){
        return matrix.length;
    }
    
    public int getCollumn(){
        return matrix[0].length;
    }
}
