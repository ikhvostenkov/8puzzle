/**
 * Created by ikhvostenkov on 02.11.16.
 */
public class Solver {
    private SearchNode lastSearchNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }

        MinPQ<SearchNode> moves = new MinPQ<SearchNode>();
        moves.insert(new SearchNode(initial));

        MinPQ<SearchNode> twinMoves = new MinPQ<SearchNode>();
        twinMoves.insert(new SearchNode(initial.twin()));

        while (true) {
            lastSearchNode = expand(moves);
            if (lastSearchNode != null || expand(twinMoves) != null) return;
        }
    }

    private SearchNode expand(MinPQ<SearchNode> moves) {
        if (moves.isEmpty()) return null;
        SearchNode bestSearchNode = moves.delMin();
        if (bestSearchNode.board.isGoal()) return bestSearchNode;
        for (Board neighbor : bestSearchNode.board.neighbors()) {
            if (bestSearchNode.previous == null || !neighbor.equals(bestSearchNode.previous.board)) {
                moves.insert(new SearchNode(neighbor, bestSearchNode));
            }
        }

        return null;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return (lastSearchNode != null);
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable() ? lastSearchNode.numMoves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> moves = new Stack<Board>();
        while (lastSearchNode != null) {
            moves.push(lastSearchNode.board);
            lastSearchNode = lastSearchNode.previous;
        }

        return moves;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

    }

    private class SearchNode implements Comparable<SearchNode> {
        private SearchNode previous;
        private Board      board;
        private int numMoves = 0;

        public SearchNode(Board board) {
            this.board = board;
        }

        public SearchNode(Board board, SearchNode previous) {
            this.board = board;
            this.previous = previous;
            this.numMoves = previous.numMoves + 1;
        }

        public int compareTo(SearchNode searchNode) {
            return (this.board.manhattan() - searchNode.board.manhattan()) + (this.numMoves - searchNode.numMoves);
        }
    }
}
