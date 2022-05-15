import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Bfs {

    public Bfs() {

    }

    public Puzzle bfs(Puzzle startingPuzzle, String order) {
        Results results = new Results();
        if(startingPuzzle.isGoal()) {
            return startingPuzzle;
        }
        Queue<Puzzle> Q = new LinkedList<>();
        Set<Puzzle> U = new HashSet<>();
        Q.add(startingPuzzle);
        U.add(startingPuzzle);
        while(!Q.isEmpty()) {
            Puzzle v = Q.remove();
            v.getNeighbours(order);
            for (Puzzle n : v.neighbours)
            {
                if (n.isGoal()) {
                    results.makeStatsBFS(n, U.size(), U.size() + Q.size());
                    return n;
                }
                if (!U.contains(n)) {
                    Q.add(n);
                    U.add(n);
                }
            }
        }
        return null;
    }
}
