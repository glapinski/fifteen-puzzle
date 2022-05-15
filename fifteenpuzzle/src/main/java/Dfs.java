import java.util.*;

public class Dfs {

    public Dfs() {

    }

    public Puzzle dfs(Puzzle startingPuzzle, String order) {
        Results results = new Results();
        int currentDepth = 0;
        if(startingPuzzle.isGoal()) {
            return startingPuzzle;
        }
        int states = 0;
        LinkedList<Puzzle> S = new LinkedList<>();
        Set<Puzzle> T = new HashSet<>();
        S.push(startingPuzzle);
        states++;
        while(!S.isEmpty()) {
            Puzzle v = S.pop();
            if (v.depth > 20) {
                continue;
            }
            if(v.depth > currentDepth) {
                currentDepth = v.depth;
            }
            T.add(v);
            v.getNeighbours(order);
            v.reverse();
            for (Puzzle n : v.neighbours) {
                if(n.isGoal()) {
                    results.makeStatsDFS(n, n.solutionPath.length(), T.size(), states, currentDepth);
                    return n;
                }
                if(!T.contains(n) && !S.contains(n))
                {
                    S.push(n);
                    states++;
                }
            }
        }
        results.makeStatsDFS(startingPuzzle, -1, T.size(), states, currentDepth);
        return startingPuzzle;
    }
}
