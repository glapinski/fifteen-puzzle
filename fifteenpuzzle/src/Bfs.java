import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Bfs {

    public Bfs() {

    }

    public Board bfs(Board startingPuzzle, Board finalBoard, String order) {
        if(startingPuzzle.equals(finalBoard)) {
            return startingPuzzle;
        }
        Queue<Board> queueList = new LinkedList<>();
        Set<Board> closedList = new HashSet<>();
        queueList.add(startingPuzzle);
        closedList.add(startingPuzzle);
        while(!queueList.isEmpty()) {
            Board v = queueList.remove();
            v.getNeighbours(order);
            for (Board tmpBoard : v.neighbours)
            {
                if (tmpBoard.equals(finalBoard)) {
                    tmpBoard.processedStates = closedList.size();
                    tmpBoard.visitedStates = tmpBoard.processedStates + queueList.size();
                    return tmpBoard;
                }
                if (!closedList.contains(tmpBoard)) {
                    queueList.add(tmpBoard);
                    closedList.add(tmpBoard);
                }
            }
        }
        return null;
    }
}
