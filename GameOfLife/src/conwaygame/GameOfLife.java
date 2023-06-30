package conwaygame;
import java.util.ArrayList;

/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        StdIn.setFile(file);

        int r = StdIn.readInt();
        int c = StdIn.readInt();

        grid = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = StdIn.readBoolean();
            }
        }
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        if (grid[row][col] == ALIVE) {
            return true;
        }
        else {
            return false;
        }
    }
    /*    for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[row][col] = ALIVE) {
                return true;
            }
                else {
                return false;
            }
        }
    }
}*/
    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {
        /*if (totalAliveCells > 0) {
            return true;
        }
        else {
            return false;
        }
    } */

    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == ALIVE) {
                return true;
            }
        }
    }
    return false;
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        int neighborsAlive = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int r = (row + j + grid.length) % grid.length;
                int c = (col + i + grid[row].length) % grid[row].length;

                if (grid[r][c] == ALIVE) {
                    neighborsAlive++;
                }
            }
        }
        if (grid[row][col]==ALIVE){
            neighborsAlive--;
        }
        return neighborsAlive;
    }


    //     for (int i = 0; i <= grid.length; i++) {
    //         for (int j = 0; j <= grid[row].length; j++) {
    //             if (!(i == row && j == col) && /*grid[row][col] == ALIVE &&*/ grid[row%grid.length][((col-1)+grid[row].length)%grid[row].length] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //             else if (!(i == row && j == col) && /*grid[row][col] == ALIVE &&*/ grid[(((row-1)+grid.length)%grid.length)][(((col-1)+grid[row].length)%grid[row].length)] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //             else if (!(i == row && j == col) && /*grid[row][col] == ALIVE &&*/ grid[(((row-1)+grid.length)%grid.length)][(col)%grid[row].length] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //             else if (!(i == row && j == col) && /*grid[row][col] == ALIVE &*/ grid[(((row-1)+grid.length)%grid.length)][((col+1)-grid[row].length)%grid[row].length] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //             else if (!(i == row && j == col) && /*grid[row][col] == ALIVE &&*/ grid[((row)%grid.length)][((col+1)-grid[row].length)%grid[row].length] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //             else if (!(i == row && j == col) && /*grid[row][col] == ALIVE &&*/ grid[(((row+1))%grid.length)][((col+1)-grid[row].length)%grid[row].length] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //             else if (!(i == row && j == col) && /*grid[row][col] == ALIVE &&*/ grid[((row+1))%grid.length][(col)%grid[row].length] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //             else if (!(i == row && j == col) && /*grid[row][col] == ALIVE &&*/ grid[((row+1)-grid.length)%grid.length][((col-1)+grid[row].length)%grid[row].length] == ALIVE) {
    //                 neighborsAlive++;
    //             }
    //     }
    // }
    // return neighborsAlive;
    // }


        /*for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col  - 2; j++) {
                if (!((i < 0 || j < 0) || (i >= grid.length || j >= grid[i].length))) {
                    if (grid[i][j] == ALIVE) {
                        neighborsAlive++;
                    }
                }
            }
        }
        return neighborsAlive;
    }*/

        /*for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < grid.length && j >= 0 && j < grid[i].length && !(i == row && j == col) && grid[i][j] == ALIVE) {
                    neighborsAlive ++;
                }
                if (i == 0 && j == 0 && ) {

                }
            }
        }
        return neighborsAlive;
    }*/
    
    
        /*if (grid[row][col] == true) {
            neighborsAlive = neighborsAlive + 1;
        }
        if (grid[row-1][col-1] == true) {
            neighborsAlive = neighborsAlive + 1;
        }
        if (grid[row+1][col+1] == true) {
            neighborsAlive = neighborsAlive + 1;
        }
        if (grid[row][col-1] == true) {
            neighborsAlive = neighborsAlive + 1;
        }
        if (grid[row-1][col] == true) {
            neighborsAlive = neighborsAlive + 1;
        }
        if (grid[row+1][col-1] == true) {
            neighborsAlive = neighborsAlive + 1;
        }
        if (grid[row-1][col+1] == true) {
            neighborsAlive = neighborsAlive + 1;
        }
    return neighborsAlive;
    }*/

        /*for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == true) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[i%j][j%j] == true) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[(i+1)%j][j%j]) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[(i-1)%j][j%j]) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[(i+1)%(j+1)][(j+1)%j]) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[(i-1)%j][(j+1)%j]) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[(i+1)%(j+1)][(i+1)%(j+1)]) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[i%j][(i+1)%j]) {
                    neighborsAlive = neighborsAlive + 1;
                }
                if (grid[(i-1)%j][(i+1)%j]) {
                    neighborsAlive = neighborsAlive + 1;
                }
            }
        }
        return neighborsAlive; // update this line, provided so that code compiles
    }*/

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {
        
        boolean[][] newGrid= new boolean[grid.length][grid[1].length];
        
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[1].length; col++) {
                if (grid[row][col] == ALIVE && numOfAliveNeighbors(row, col) < 2) {
                    newGrid[row][col] = DEAD;
                }
                else if (grid[row][col] == ALIVE && numOfAliveNeighbors(row, col) < 4) {
                    newGrid[row][col] = ALIVE;
                }
                else if (grid[row][col] == DEAD && numOfAliveNeighbors(row, col) == 3) {
                    newGrid[row][col] = ALIVE;
                }
                else {
                    newGrid[row][col] = DEAD;
                }
                // if (grid[row][col] == ALIVE) { // rules for alive cells
                //     if (numOfAliveNeighbors(row, col) == 1 || numOfAliveNeighbors(row, col) == 0) {
                //         newGrid[row][col] = DEAD; 
                //     }
                //     else if (numOfAliveNeighbors(row, col) == 2 || numOfAliveNeighbors(row, col) == 3) {
                //         newGrid[row][col] = ALIVE;
                //     }
                //     else if (numOfAliveNeighbors(row, col) >= 4) {
                //         newGrid[row][col] = DEAD;
                //     }

                // }
                // if(grid[row][col] == DEAD) {
                //     if (numOfAliveNeighbors(row, col) == 3) {
                //         grid[row][col] = ALIVE;
                //     }
                // }



            }
        }

        return newGrid;
        //return new boolean[1][1];// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        boolean[][] temp = computeNewGrid();

        int count = 0;

        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[1].length; col++) {
                grid[row][col] = temp[row][col];
                if (grid[row][col] == ALIVE) {
                    count++;
                }
            }
        }
        totalAliveCells = count;
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        for (int i = 0; i < n; i++) {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

        int communities = 0;
        int row = grid.length;
        int col = grid[0].length;

        WeightedQuickUnionUF root = new WeightedQuickUnionUF(row, col);

        ArrayList<Integer> numCommunities = new ArrayList<Integer>();

        ArrayList<Integer> finalCommunities = new ArrayList<Integer>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int x = (r + j + grid.length) % grid.length;
                        int y = (c + i + grid[r].length) % grid[r].length;
                        if (grid[x][y] == ALIVE && grid[r][c] == ALIVE) {
                            root.union(x, y, r, c);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int temp = root.find(i, j);
                if (grid[i][j] == ALIVE) {

                    Integer x = temp;
                    if (numCommunities.contains(x) == false) {
                        numCommunities.add(x);
                    }
                }
            }
        }
        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[i].length; j++ )
        //     if (numCommunities.contains(i)) {
        //         finalCommunities.add(i);
        //     }
        // }
        return numCommunities.size();
    }
}

    //             if (grid[r][c] == ALIVE) {
    //                 if (grid[(r + row - 1) % row][(c + col - 1) % col] == ALIVE) {
    //                     root.union(r, c, (r + row - 1) % row, (c + col - 1) % col);
    //             }
    //                 if (grid[(r + row - 1) % row][c] == ALIVE) {
    //                     root.union(r, c, (r + row - 1) % row, c);
    //                 }
    //                 if (grid[(r + row - 1) % row][(c - 1 + col) % col] == ALIVE) {
    //                     root.union(r, c, (r + row - 1) % row, (c - 1 + col) % col);
    //                 }
    //                 if (grid[r][(c + col + 1) % col] == ALIVE) {
    //                     root.union(r, c, r, (c + col + 1) % col);
    //                 }
    //                 if (grid[(r + row + 1) % row][(c + col + 1) % col] == ALIVE) {
    //                     root.union(r, c, (r + row + 1) % row, (c + col + 1) % col);
    //                 }
    //                 if (grid[(r + row + 1) % row][c] == ALIVE) {
    //                     root.union(r, c, (r + row + 1) % row, c);
    //                 }
    //                 if (grid[(r + row + 1) % row][(c - 1 + col) % col] == ALIVE) {
    //                     root.union(r, c, (r + row + 1) % row, (c - 1 + col) % col);
    //                 }
    //                 if (grid[r][(c - 1 + col) % col] == ALIVE) {
    //                     root.union(r, c, r, (c - 1 + col) % col);
    //                 }

    //         }
    //     }
    // }

        // for (int i = -1; i < 2; i++) {
        //     for (int j = -1; j < 2; j++) {
        //         int r = (row + j) % grid.length;
        //         int c = (col + i) % grid[row].length;

        //         if (grid[r][c] == ALIVE) {
        //             root.union(r, c, i, j);
        //         }
        //     }
        // }
    //     for (int i = 0; i <= row; i++) {
    //         for (int j = 0; j <= col; j++) {
    //             numCommunities.add((root.find(i, j)));
    //         }
    //     }
    //     for (int i = 0; i <= numCommunities.size(); i++) {
    //         if (!numCommunities.contains(i)) {
    //             finalCommunities.add(i);
    //         }
    //     }
    //     return finalCommunities.size();
    // }