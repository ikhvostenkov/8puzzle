/**
 * Created by ikhvostenkov on 02.11.16.
 */
public class Board {
    private int[] blocks;

    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length * blocks[0].length];
        for (int i = 0; i < this.blocks.length; i++) {
            this.blocks[i] = blocks[i / blocks.length][i % blocks.length];
        }
    }

    // board dimension n
    public int dimension() {
        return this.blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int blocksOutOfPlace = 0;
        int lastElement = this.blocks.length - 1;
        for (int i = 1; i < lastElement; i++) {
            if (blocks[i - 1] != i || ((i == lastElement - 1) && (this.blocks[lastElement] != 0))) {
                blocksOutOfPlace++;
            }
        }

        return blocksOutOfPlace;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int distanceToGoal = 0;
        int lastElement = this.blocks.length - 1;
        for (int i = 1; i < lastElement; i++) {
            if (blocks[i - 1] != i || ((i == lastElement - 1) && (this.blocks[lastElement] != 0))) {
                distanceToGoal++;
            }
        }

        return distanceToGoal;

    }

    // is this board the goal board?
    public boolean isGoal() {
        int lastElement = this.blocks.length - 1;
        for (int i = 1; i < lastElement; i++) {
            if (blocks[i - 1] != i || ((i == lastElement - 1) && (this.blocks[lastElement] != 0))) {
                return false;
            }
        }

        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return this;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        return "";
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}
