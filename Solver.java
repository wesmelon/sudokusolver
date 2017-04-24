public class Solver {
  public static final int N = 9;

  public boolean solve(int[][] grid) {
    Tuple tuple = findUnassignedLoc(grid);
    if (!tuple.unassigned) return true;

    int row = tuple.row;
    int col = tuple.col;

    for (int num = 1; num <= 9; num++) {
      if (isSafe(grid, row, col, num)) {
        grid[row][col] = num;
        if (solve(grid)) {
          return true;
        }
        grid[row][col] = 0;
      }
    }

    return false;   
  }

  public Tuple findUnassignedLoc(int[][] grid) {
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (grid[row][col] == 0) {
          return new Tuple(row, col, true);
        }
      }
    }
    return new Tuple(0, 0, false);
  }

  public boolean isSafe(int[][] grid, int row, int col, int num) {
    return !isInRow(grid, row, num) &&
           !isInCol(grid, col, num) &&
           !isInBox(grid, row - row % 3, col - col % 3, num);
  }

  public boolean isInRow(int[][] grid, int row, int num) {
    for (int col = 0; col < N; col++) {
      if (grid[row][col] == num) {
        return true;
      }
    }
    return false;
  }

  public boolean isInCol(int[][] grid, int col, int num) {
    for (int row = 0; row < N; row++) {
      if (grid[row][col] == num) {
        return true;
      }
    }
    return false;
  }

  public boolean isInBox(int[][] grid, int rowStart, int colStart, int num) {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        if (grid[r + rowStart][c + colStart] == num) {
          return true;
        }
      }
    }
    return false;
  }

  public static class Tuple {
    public int row;
    public int col;
    public boolean unassigned;

    public Tuple(int row, int col, boolean unassigned) {
      this.row = row;
      this.col = col;
      this.unassigned = unassigned;
    }
  }


  public static void print(int[][] grid) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(grid[i][j] + " ");
        if ((j + 1) % 3 == 0 && (j + 1) != N) {
          System.out.print("| ");
        }
      }
      System.out.print("\n");
      if ((i + 1) % 3 == 0 && (i + 1) != N) {
        System.out.println("---------------------");
      }
    }
  }

  public static void main(String[] args) {
    int grid[][] = new int[][]{
      {0, 2, 0, 0, 0, 6, 3, 1, 0},
      {6, 0, 0, 0, 0, 0, 0, 7, 2},
      {0, 8, 9, 3, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 1, 4, 3, 0},
      {0, 0, 0, 0, 0, 0, 0, 0 ,0},
      {0, 9, 6, 8, 0, 0, 0, 0 ,0},
      {0, 0, 0, 0, 0, 7, 1, 4, 0},
      {9, 1, 0, 0, 0, 0, 0, 0, 7},
      {0, 7, 8, 4, 0, 0, 0, 9, 0}
    };

    Solver solver = new Solver();

    if (solver.solve(grid)) {
      print(grid);
    } else {
      System.out.println("no solution");
    }
  }
}
