import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ikhvostenkov on 02.11.16.
 */
public class Solver {
    private List<Board> boards = new ArrayList<Board>();
    private boolean solvable = true;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }

        this.boards.add(initial);
        Board searchNode = initial;
        MinPQ<Board> pq;
        while (!searchNode.isGoal()) {
            pq = new MinPQ<Board>(new NeighborPriority());
            for (Board neighbor : searchNode.neighbors()) {
                if (!boards.contains(neighbor)) {
                    pq.insert(neighbor);
                }
            }
            if (pq.isEmpty()) {
                solvable = false;
                break;
            }

            searchNode = pq.delMin();
            boards.add(searchNode);
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return boards.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }

        return boards;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

    }

    private class NeighborPriority implements Comparator<Board> {
        public int compare(Board o1, Board o2) {
            int priority1 = o1.manhattan() + o1.hamming();
            int priority2 = o2.manhattan() + o2.hamming();

            if (priority1 > priority2) {
                return 1;
            } else if (priority1 < priority2) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
