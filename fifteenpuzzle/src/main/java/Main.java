import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        String strategy = args[0];
        String param = args[1];
        String puzzle = args[2];
        String solutionFile = args[3];
        String stats = args[4];

        BigDecimal startTime = BigDecimal.valueOf(0);
        BigDecimal endTime = BigDecimal.valueOf(0);
        BigDecimal elapsedTime;


        Bfs bfs = new Bfs();
        Dfs dfs = new Dfs();
        AStr astr = new AStr();

        Puzzle puzzleToSolve = new Puzzle(puzzle);
        Puzzle solution;

        switch (strategy) {
                case "bfs":
                    startTime = BigDecimal.valueOf(System.nanoTime());
                    solution = bfs.bfs(puzzleToSolve, param);
                    endTime = BigDecimal.valueOf(System.nanoTime());
                    break;
                case "dfs":
                    startTime = BigDecimal.valueOf(System.nanoTime());
                    solution = dfs.dfs(puzzleToSolve, param);
                    endTime = BigDecimal.valueOf(System.nanoTime());
                    break;
                 case "astr":
                     startTime = BigDecimal.valueOf(System.nanoTime());
                     solution = astr.Astr(puzzleToSolve,param);
                     endTime = BigDecimal.valueOf(System.nanoTime());
                     break;
                default:
                    solution = puzzleToSolve;
        }
        elapsedTime = endTime.subtract(startTime);
        elapsedTime = elapsedTime.divide(BigDecimal.valueOf(1000000));

        if(!"dfs".equals(strategy)){
            solution.solutionSize = solution.solutionPath.length();
        }
        try (PrintWriter print = new PrintWriter(solutionFile)) {
            print.println(solution.solutionSize);
            print.println(solution.solutionPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintWriter print = new PrintWriter(stats)) {
            print.println(solution.solutionSize);
            print.println(solution.visitedStates);
            print.println(solution.processedStates);
            print.println(solution.depth);
            print.println(elapsedTime);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}