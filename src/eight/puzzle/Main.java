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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] in = {
            {0, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };
        
        int[][] de = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
        };
        
        State initial = new State(in);
        initial.setLevel(0);
        State desired = new State(de);
        Solver.solve(initial, desired);
    }
}
