import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board implements Comparable<Board> {

    public int[] getBoard(String boardFile) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(boardFile));
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
    public Board(String boardFile) {
        try {
            this.previousMove = "none";
            int z = 2;
            int[] boardFileNumbersAmount = getBoard(boardFile);
            solvingBoard = new int[boardFileNumbersAmount[0]][boardFileNumbersAmount[1]];
            for(int i = 0; i< boardFileNumbersAmount[0]; i++)
               for(int j = 0; j< boardFileNumbersAmount[1]; j++) {
                   solvingBoard[i][j] = boardFileNumbersAmount[z];
                   z++;
               }
            for(int i = 0;i< solvingBoard.length;i++) {
                for(int j = 0;j< solvingBoard.length;j++)
                    if(solvingBoard[i][j] == 0) {
                        this.zeroPlace[0] = i;
                        this.zeroPlace[1] = j;
                    }
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public List<Board> neighbours = new ArrayList<>();
    public int processedStates;
    public int visitedStates;
    public int[] zeroPlace = new int [2];
    public String previousMove;
    public int depth;
    public int solutionSize;
    public int[][] solvingBoard;

    public Board(int[][] board) {
        this.depth = 0;
        this.solutionSize = 0;
        this.previousMove = "none";
        this.solvingBoard = board;
    }

    int[][] finalBoard = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };


    public boolean areBoardsEqual(int[][] board) {
        for(int i=0;i<solvingBoard.length;i++)
            for(int j = 0;j<solvingBoard.length;j++)
                if(solvingBoard[i][j] != board[i][j])
                    return false;
                return true;
    }
    @Override
    public int compareTo(Board o) {
        return 0;
    }

    public void getNeighbours(String order) {
    }
}

