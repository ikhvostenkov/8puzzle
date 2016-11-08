/**
 * Created by ikhvostenkov on 02.11.16.
 */
public class Board {
    int[] blocks;

    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks){
        this.blocks = new int[blocks.length * 2];
        for (int i = 0; i < blocks.length; i++){

        }
    }

    // board dimension n
    public int dimension(){
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming(){
        return 1;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan(){
        return 1;

    }

    // is this board the goal board?
    public boolean isGoal(){
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){
        return this;
    }

    // does this board equal y?
    public boolean equals(Object y){
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString(){
        return "";
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
