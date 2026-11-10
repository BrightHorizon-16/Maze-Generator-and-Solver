import java.util.ArrayList;

public class Maze {
    private final int rows;
    private final int cols;
    private final Cell[][] grid;

    public Maze(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("Maze must be at least 1 x 1 (got " + rows + " x " + cols + ")");
        }

        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Cell getStart() { return grid[0][0]; }
    public Cell getExit() { return grid[rows - 1][cols - 1]; }

    public boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    public Cell getCell(int r, int c) {
        if (!inBounds(r, c)) {
            throw new IllegalArgumentException("Cell (" + r + "," + c + ") is outside the grid");
        }
        return grid[r][c];
    }

    public ArrayList<Cell> getUnvisitedNeighbours(Cell cell) {
        
        ArrayList<Cell> result = new ArrayList<>();

        int r = cell.getRow();
        int c = cell.getCol();

        addIfUnvisited(result, r - 1, c);
        addIfUnvisited(result, r, c + 1);
        addIfUnvisited(result, r + 1, c);
        addIfUnvisited(result, r, c - 1);
        return result;
    }

    private void addIfUnvisited(ArrayList<Cell> list, int r, int c) {
        if (inBounds(r, c) && !grid[r][c].isVisited()) {
            list.add(grid[r][c]);
        }
    }

    public ArrayList<Cell> getOpenNeighbours(Cell cell) {
        
        ArrayList<Cell> result = new ArrayList<>();

        int r = cell.getRow();
        int c = cell.getCol();

        if (!cell.hasTopWall() && inBounds(r - 1, c)) result.add(grid[r - 1][c]);
        if (!cell.hasRightWall() && inBounds(r, c + 1)) result.add(grid[r][c + 1]);
        if (!cell.hasBottomWall() && inBounds(r + 1, c)) result.add(grid[r + 1][c]);
        if (!cell.hasLeftWall() && inBounds(r, c - 1)) result.add(grid[r][c - 1]);
        return result;
    }

    public void removeWallBetween(Cell a, Cell b) {
        int dr = b.getRow() - a.getRow();
        int dc = b.getCol() - a.getCol();
        
        if (dr == -1 && dc == 0) {
            a.removeTopWall();
            b.removeBottomWall();
        } else if (dr == 1 && dc == 0) {
            a.removeBottomWall();
            b.removeTopWall();
        } else if (dr == 0 && dc == 1) {
            a.removeRightWall();
            b.removeLeftWall();
        } else if (dr == 0 && dc == -1) {
            a.removeLeftWall();
            b.removeRightWall();
        } else {
            throw new IllegalArgumentException("Cells " + a + " and " + b + " are not adjacent");
        }
    }

    public void resetVisited() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c].setVisited(false);
            }
        }
    }

    public void setAllStates(Cell.State state) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c].setState(state);
            }
        }
    }

    public int countPassages() {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (c < cols - 1 && !grid[r][c].hasRightWall()) count++;
                if (r < rows - 1 && !grid[r][c].hasBottomWall()) count++;
            }
        }
        return count;
    }

    public String toString() {
        return render(null);
    }

    public String render(ArrayList<Cell> path) {
        
        boolean[][] onPath = new boolean[rows][cols];
        
        if (path != null) {
            for (Cell cell : path) {
                onPath[cell.getRow()][cell.getCol()] = true;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sb.append('+').append(grid[r][c].hasTopWall() ? "---" : "   ");
            }
            sb.append("+\n");

            for (int c = 0; c < cols; c++) {
                sb.append(grid[r][c].hasLeftWall() ? '|' : ' ');
                sb.append(onPath[r][c] ? " * " : "   ");
            }
            sb.append(grid[r][cols - 1].hasRightWall() ? '|' : ' ').append('\n');
        }
        for (int c = 0; c < cols; c++) {
            sb.append('+').append(grid[rows - 1][c].hasBottomWall() ? "---" : "   ");
        }
        sb.append("+\n");
        return sb.toString();
    }
}
