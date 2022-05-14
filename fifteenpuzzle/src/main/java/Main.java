public class Main {
    public static void main(String[] args) {
        //String strategy = args[0];
        String strategy = "bfs";
        //String param = args[1];
        String param = "LRUD";
        //String puzzle = args[2];
        String puzzle = "puzzles/4x4_04_00007.txt";
        //String solution = args[3];
        String solution = "solution.txt";
        //String stats = args[4];
        String stats = "stats.txt";

        Bfs bfs = new Bfs();

        int[][] finalBoard = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };
        Puzzle puzzleToSolve = new Puzzle(puzzle);
        Puzzle solved = new Puzzle(finalBoard);

        switch(strategy) {
            case "bfs":
                bfs.bfs(puzzleToSolve, solved, param);
            case "dfs":

            case "astr":
        }

        int[][] tab = new int[2][3];
        tab[0][1] = 4;
        int[][]copiedTab = tab;
        System.out.println(copiedTab[0][2]);
    }
}