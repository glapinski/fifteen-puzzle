public class Results {
    public void makeStatsDFS(Puzzle puzzle, int solutionSize, int processedStatesNumber, int visitedStatesNumber, int maxDepth)
    {
        puzzle.solutionSize = solutionSize;
        puzzle.processedStates = processedStatesNumber;
        puzzle.visitedStates = visitedStatesNumber;
        puzzle.maxDepth = maxDepth;
    }

    public void makeStatsBFS(Puzzle puzzle, int processedStates, int visitedStates)
    {
        puzzle.visitedStates = visitedStates;
        puzzle.processedStates = processedStates;
    }
    public void makeStatsAStr(Puzzle puzzle, int solutionSize, int processedStates, int visitedStates)
    {
        puzzle.solutionSize = solutionSize;
        puzzle.processedStates = processedStates;
        puzzle.visitedStates = visitedStates;
    }
}

