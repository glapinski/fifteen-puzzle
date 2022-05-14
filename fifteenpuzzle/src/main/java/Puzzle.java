import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle implements Comparable<Puzzle> {

    public int[] getPuzzle(String puzzleFile) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(puzzleFile));
        int rows = sc.nextInt();
        int columns= sc.nextInt();
        int[] boardFileNumbersAmount = new int[2+rows*columns];
        boardFileNumbersAmount[0] = rows;
        boardFileNumbersAmount[1] = columns;
        for(int i = 2;i<boardFileNumbersAmount.length;i++)
            boardFileNumbersAmount[i] = sc.nextInt();
        return boardFileNumbersAmount;
//        solvingBoard = new int[boardFileNumbersAmount[0]][boardFileNumbersAmount[1]];
//        int z = 2;
//        for(int i = 0; i< boardFileNumbersAmount[0]; i++)
//            for(int j = 0; j< boardFileNumbersAmount[1]; j++) {
//                solvingBoard[i][j] = boardFileNumbersAmount[z];
//                z++;
//            }
    }
    public Puzzle(String puzzleFile) {
        try {
            this.previousMove = "none";
            int z = 2;
            int[] boardFileNumbersAmount = getPuzzle(puzzleFile);
            solvingPuzzle = new int[boardFileNumbersAmount[0]][boardFileNumbersAmount[1]];
            for(int i = 0; i< boardFileNumbersAmount[0]; i++)
               for(int j = 0; j< boardFileNumbersAmount[1]; j++) {
                   solvingPuzzle[i][j] = boardFileNumbersAmount[z];
                   z++;
               }
            for(int i = 0; i< solvingPuzzle.length; i++) {
                for(int j = 0; j< solvingPuzzle.length; j++)
                    if(solvingPuzzle[i][j] == 0) {
                        this.zeroPlace[0] = i;
                        this.zeroPlace[1] = j;
                    }
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public List<Puzzle> neighbours = new ArrayList<>();
    public int processedStates;
    public int visitedStates;
    public int[] zeroPlace = new int [2];
    public String previousMove;
    public int depth;
    public int solutionSize;
    public int[][] solvingPuzzle;
    public String solutionPath = "";
    public int priority = 0;

    public Puzzle(int[][] puzzle) {
        this.depth = 0;
        this.solutionSize = 0;
        this.previousMove = "none";
        this.solvingPuzzle = puzzle;
    }

    public Puzzle(Puzzle newPuzzle, String move) {
        this.solvingPuzzle = copyPuzzles(newPuzzle);
        solutionPath = String.valueOf(newPuzzle.solutionPath);
        this.priority = 0;
        this.previousMove = move;
        this.zeroPlace[0] = newPuzzle.zeroPlace[0];
        this.zeroPlace[1] = newPuzzle.zeroPlace[1];

    }

    public int getPuzzleValue(int x, int y) {
        return solvingPuzzle[x][y];
    }

    public int[][] getSolvingPuzzle() {
        return solvingPuzzle;
    }

    int[][] finalBoard = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public int[][] copyPuzzles(Puzzle puzzle) {
        int[][] tmp = new int[puzzle.solvingPuzzle.length][puzzle.solvingPuzzle.length];
        for(int i = 0;i<puzzle.solvingPuzzle.length;i++)
            for(int j = 0;j<puzzle.solvingPuzzle.length;j++)
                tmp[i][j] = puzzle.solvingPuzzle[i][j];
            return tmp;
    }

    public boolean arePuzzlesEqual(int[][] puzzle) {
        for(int i = 0; i< solvingPuzzle.length; i++)
            for(int j = 0; j< solvingPuzzle.length; j++)
                if(solvingPuzzle[i][j] != puzzle[i][j])
                    return false;
                return true;
    }
    @Override
    public int compareTo(Puzzle o) {
        return 0;
    }

    public void getNeighbours(String order) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Puzzle puzzle = (Puzzle) o;

        return new EqualsBuilder().append(solvingPuzzle,puzzle.solvingPuzzle).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,37).append(solvingPuzzle).toHashCode();
    }
}

