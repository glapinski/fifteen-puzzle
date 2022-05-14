import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Bfs {

    public Bfs() {

    }

    public Puzzle bfs(Puzzle startingPuzzle, Puzzle finalPuzzle, String order) {
        if(startingPuzzle.equals(finalPuzzle)) {
            return startingPuzzle;
        }
        Queue<Puzzle> queueList = new LinkedList<>();
        Set<Puzzle> closedList = new HashSet<>();
        queueList.add(startingPuzzle);
        closedList.add(startingPuzzle);
        while(!queueList.isEmpty()) {
            Puzzle v = queueList.remove();
            v.getNeighbours(order);
            for (Puzzle tmpPuzzle : v.neighbours)
            {
                if (tmpPuzzle.equals(finalPuzzle)) {
                    tmpPuzzle.processedStates = closedList.size();
                    tmpPuzzle.visitedStates = tmpPuzzle.processedStates + queueList.size();
                    return tmpPuzzle;
                }
                if (!closedList.contains(tmpPuzzle)) {
                    queueList.add(tmpPuzzle);
                    closedList.add(tmpPuzzle);
                }
            }
        }
        return null;
    }
}
