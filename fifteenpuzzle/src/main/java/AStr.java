import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStr {

    public AStr () {

    }

    public Puzzle Astr(Puzzle startingPuzzle, String heuristic) {
        Results results = new Results();
        if(startingPuzzle.isGoal()) {
            return startingPuzzle;
        }
        Queue<Puzzle> P = new PriorityQueue<>();
        Set<Puzzle> T = new HashSet<>();
        P.add(startingPuzzle);
        int f = 0;
        while(!P.isEmpty()) {
            Puzzle v = P.remove();
            if(v.isGoal()) {
                results.makeStatsAStr(v, v.solutionPath.length(), T.size(), T.size() + P.size());
                return v;
            }
            T.add(v);
            v.getNeighbours("LURD");
            for(Puzzle n : v.neighbours) {
                if(!T.contains(n))
                {
                    switch(heuristic) {
                        case "hamm":
                            f = n.hammingHeuristic();
                            break;
                        case "manh":
                            f = n.manhattanHeuristic();
                            break;
                    }
                    if(!P.contains(n)) {
                        n.priority = f;
                        P.add(n);
                    }
                    else if(P.element().priority > f) {
                        P.remove(n);
                        n.priority = f;
                        P.add(n);
                    }
                }
            }
        }
        return null;
    }
}
