/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight.puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A
 */
public class Solver {
    private static boolean compare(State a, State b){
        return (a.getCost() + a.getLevel()) < (b.getCost() + b.getLevel());
    }
    
    private State getMinimum(List<State> list){
        State m = list.get(0);
        for (State state : list) {
            if(compare(m, state))
                m = state;
        }
        
        return m;
    }
    
    private static boolean isValid(int x, int y, State subject){
        int m = subject.getRow();
        int n = subject.getCollumn();
        
        return (x <= m - 1 && x >= 0 && y <= n - 1 && y >= 0);
    }
    
    private static void print(State state){
        for (int[] is : state.getMatrix()) {
            for (int i : is) {
                System.out.print(" " + i);
            }
            System.out.println("");
        }
        
        System.out.println("");
    }
    
    private static void printPath(State root){
        if (root == null) {
            return;
        }
        
        print(root.getParent());
        printPath(root);
    }
    
    private static boolean checkChildren(State target){
        if (target.getParent() == null) {
            return false;
        }
        else if(target.compare(target.getParent())){
            return true;
        }
        
        return checkChildren(target.getParent());
    }
    
    private static boolean checkHistory(List<State> history, State child){
        for (State state : history) {
            if (state.getLevel() != child.getLevel()) {
                if (child.compare(state)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void solve(State initial, State desired){
        for (int i = 0; i < initial.getRow(); i++) {
            for (int j = 0; j < initial.getCollumn(); j++) {
                if (initial.getMatrix(i, j) == 0) {
                    initial.setX(i);
                    initial.setY(j);
                    
                    break;
                }
            }
        }
        
        initial.setCost(initial.cost(desired.getMatrix()));
        initial.setParent(null);
        
        State pq = initial;
        List<State> history = new ArrayList<>();
        history.add(pq);
        
        int[] pos = {1, 0, -1, 0};
        
        while(pq != null){
            State min = pq;
            
            if (min.getCost() == 0) {
                printPath(min);
                pq = null;
            }
            
            for (int i = 0; i < 4; i++) {
                int _x = min.getX();
                int _y = min.getY();
                if(isValid(_x + pos[i], _y + pos[3 - i], min)){
                    State child = min.newState(_x, _y, _x + pos[i], _y + pos[3 - i], min.getLevel() + 1);
                    child.setParent(min);

                    child.setCost(child.cost(desired.getMatrix()));
                    print(child);
                    
                    if (!checkHistory(history, child)) {
                        history.add(child);
                    }
                    
                    if (i == 0) {
                        pq = child;
                    }
                    else if (compare(child, pq) && !checkChildren(child)){
                        pq = child;
                    }
                }
            }
        }
    }
}
