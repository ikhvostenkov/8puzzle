/**
 * Created by ikhvostenkov on 02.11.16.
 */
public class Board {
    private final int size;
    private final int dimension;
    private int[] tiles;

    private Board(int[] blocks) {
        this.tiles = blocks;
        this.dimension = (int) Math.sqrt(blocks.length);
        this.size = this.tiles.length;
    }

    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        this.tiles = new int[blocks.length * blocks[0].length];
        this.dimension = blocks[0].length;
        this.size = this.tiles.length;

        for (int i = 0; i < size; i++) {
            this.tiles[i] = blocks[i / blocks.length][i % blocks.length];
            if (this.tiles[i] == 0) {
                this.tiles[i] = size;
            }
        }
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
        int blocksOutOfPlace = 0;
        for (int i = 1; i < size; i++) {
            if (tiles[i - 1] != i) {
                blocksOutOfPlace++;
            }
        }

        return blocksOutOfPlace;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int distanceToGoal = 0;
        for (int i = 0; i < size; i++) {
            int value = i + 1;
            if (tiles[i] != size && tiles[i] != value) {
                distanceToGoal += Math.abs((tiles[i] - 1) / dimension - i / dimension)
                    + Math.abs((tiles[i] -1) % dimension - i % dimension);
            }
        }

        return distanceToGoal;

    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 1; i <= size; i++) {
            if (tiles[i - 1] != i) {
                return false;
            }
        }

        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[] blocks = this.tiles.clone();
        for (int i = 0; i < size - 1; i++) {
            if (this.tiles[i] != size && this.tiles[i + 1] != size) {
                blocks[i] = this.tiles[i + 1];
                blocks[i + 1] = this.tiles[i];
                return new Board(blocks);
            }
        }

        return new Board(blocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;

        return this.size == that.size && this.isEqualsBlocks(that.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> boards = new Stack<Board>();
        int[] blocks = this.tiles.clone();
        for (int i = 0; i < size; i++) {
            if (blocks[i] == size) {
                if (i / dimension < dimension - 1) {
                    blocks[i] = blocks[i + dimension];
                    blocks[i + dimension] = size;
                    boards.push(new Board(blocks));
                    blocks = this.tiles.clone();
                }

                if (i == 0 || i % dimension != dimension - 1) {
                    blocks[i] = blocks[i + 1];
                    blocks[i + 1] = size;
                    boards.push(new Board(blocks));
                    blocks = this.tiles.clone();
                }

                if (i / dimension > 0) {
                    blocks[i] = blocks[i - dimension];
                    blocks[i - dimension] = size;
                    boards.push(new Board(blocks));
                    blocks = this.tiles.clone();
                }

                if (i % dimension != 0) {
                    blocks[i] = blocks[i - 1];
                    blocks[i - 1] = size;
                    boards.push(new Board(blocks));
                }
            }
        }
        return boards;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension);
        for (int i = 0; i < size; i++) {
            if (i % dimension == 0) {
                stringBuilder.append("\r\n").append(" ");
            }

            if (tiles[i] == size) {
                stringBuilder.append(String.format("%2d ", 0));
                continue;
            }
            stringBuilder.append(String.format("%2d ", tiles[i]));
        }

        return stringBuilder.append("\r\n").toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }

    private boolean isEqualsBlocks(int[] blocks) {
        for (int i = 0; i < size; i++) {
            if (this.tiles[i] != blocks[i]) {
                return false;
            }
        }

        return true;
    }
}
