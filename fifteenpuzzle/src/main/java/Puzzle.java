import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import java.lang.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puzzle implements Comparable<Puzzle> {
    public List<Puzzle> neighbours = new ArrayList<>();
    public int processedStates;
    public int visitedStates;

    public int maxDepth;
    public int[] zeroPlace = new int [2];
    public char previousMove;
    public int depth;
    public int solutionSize;
    public int[][] solvingPuzzle;
    public String solutionPath = "";
    public int priority;

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
    }

    public boolean isGoal() {
        return Arrays.deepEquals(this.solvingPuzzle, finalPuzzle);
    }
    public Puzzle(String puzzleFile) {
        try {
            this.previousMove = 'n';
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

    public Puzzle(int[][] puzzle) {
        this.depth = 0;
        this.solutionSize = 0;
        this.previousMove = 'n';
        this.solvingPuzzle = puzzle;
    }

    public Puzzle(Puzzle newPuzzle, char direction) {
        this.solvingPuzzle = copyPuzzles(newPuzzle);
        solutionPath = String.valueOf(newPuzzle.solutionPath);
        this.priority = 0;
        this.previousMove = direction;
        this.zeroPlace[0] = newPuzzle.zeroPlace[0];
        this.zeroPlace[1] = newPuzzle.zeroPlace[1];

    }

    public void printPuzzle(){
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                System.out.print(getPuzzleValue(i, j));
                if(getPuzzleValue(i, j)<10){
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
    }

    public Puzzle move(char direction) {
        Puzzle newPuzzle = new Puzzle(this, direction);
        if (Objects.equals(direction, 'L')) {
            newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1]] = newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1] - 1];
            newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1] - 1] = 0;
            newPuzzle.zeroPlace[1] = this.zeroPlace[1] - 1;
            newPuzzle.solutionPath += 'L';
            newPuzzle.depth = this.depth + 1;
        }
        else if (Objects.equals(direction, 'U')) {
            newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1]] = newPuzzle.solvingPuzzle[zeroPlace[0] - 1][zeroPlace[1]];
            newPuzzle.solvingPuzzle[zeroPlace[0] - 1][zeroPlace[1]] = 0;
            newPuzzle.zeroPlace[0] = this.zeroPlace[0] - 1;
            newPuzzle.solutionPath += 'U';
            newPuzzle.depth = this.depth + 1;
        }
        else if (Objects.equals(direction, 'R')) {
            newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1]] = newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1] + 1];
            newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1] + 1] = 0;
            newPuzzle.zeroPlace[1] = this.zeroPlace[1] + 1;
            newPuzzle.solutionPath += 'R';
            newPuzzle.depth = this.depth + 1;
        }
        else if (Objects.equals(direction, 'D')) {
            newPuzzle.solvingPuzzle[zeroPlace[0]][zeroPlace[1]] = newPuzzle.solvingPuzzle[zeroPlace[0] + 1][zeroPlace[1]];
            newPuzzle.solvingPuzzle[zeroPlace[0] + 1][zeroPlace[1]] = 0;
            newPuzzle.zeroPlace[0] = this.zeroPlace[0] + 1;
            newPuzzle.solutionPath += 'D';
            newPuzzle.depth = this.depth + 1;
        }
        return newPuzzle;
    }

    public boolean isPossibleMove(char direction) {
        if (Objects.equals(direction, 'L')) {
            return zeroPlace[1] != 0;
        }
        else if(Objects.equals(direction, 'U')) {
            return zeroPlace[0] != 0;
        }
        else if(Objects.equals(direction, 'R')) {
            return zeroPlace[1] != solvingPuzzle.length - 1;
        }
        else if(Objects.equals(direction, 'D')) {
            return zeroPlace[0] != solvingPuzzle.length - 1;
        }
        return false;
    }

    public void reverse() {
        Collections.reverse(neighbours);
    }

    public int getPuzzleValue(int x, int y) {
        return solvingPuzzle[x][y];
    }

    int[][] finalPuzzle= {
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

    public int hammingHeuristic() {
        int hammingHeuristicValue = 0;
        int z = 0;
        int[] array = new int[solvingPuzzle.length*solvingPuzzle.length];
        for(int i = 0;i <solvingPuzzle.length;i++)
            for(int j = 0;j < solvingPuzzle.length;j++) {
                array[z] = solvingPuzzle[i][j];
                z++;
            }
        for (int i = 0; i < array.length; i++)
            if (array[i] != 0 && array[i] != i + 1)
                hammingHeuristicValue++;
        hammingHeuristicValue += this.depth;
        return hammingHeuristicValue;
    }

    public int manhattanHeuristic() {
        int manhattanValue = 0;
        int correctX;
        int correctY;
        int incorrectY;
        int incorrectX;
        for (int i = 0; i < solvingPuzzle.length; i++) {
            for (int j = 0; j < solvingPuzzle.length; j++) {
                if(this.solvingPuzzle[i][j] != 0 && this.solvingPuzzle[i][j] != i * 4 + j + 1){
                    int boardValue = this.solvingPuzzle[i][j] -1;
                    incorrectX = i;
                    incorrectY = j;
                    correctX = boardValue / 4;
                    correctY = boardValue % 4 - 1;
                    manhattanValue += Math.abs(correctX - incorrectX) + Math.abs(correctY - incorrectY);
                }
            }
        }
        manhattanValue += this.depth;
        return manhattanValue;
    }
    @Override
    public int compareTo(Puzzle n) {
        return Integer.compare(this.priority, n.priority);
    }

    public void getNeighbours(String order) {
        for(int i = 0; i < 4; i++) {
            if (isPossibleMove(order.charAt(i)) && !isRepeated(order.charAt(i))) {
                neighbours.add(move(order.charAt(i)));
            }
        }
    }

    public boolean isRepeated(char direction) {
        if (Objects.equals(previousMove, 'L') && Objects.equals(direction, 'R'))
            return true;
        else if (Objects.equals(previousMove, 'U') && Objects.equals(direction, 'D'))
            return true;
        else if (Objects.equals(previousMove, 'R') && Objects.equals(direction, 'L'))
            return true;
        else if (Objects.equals(previousMove, 'D') && Objects.equals(direction, 'U'))
            return true;
        return false;
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

