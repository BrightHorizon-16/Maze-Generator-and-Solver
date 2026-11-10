public class Cell {
    private final int row;
    private final int col;
    private boolean topWall = true;
    private boolean bottomWall = true;
    private boolean leftWall = true;
    private boolean rightWall = true;
    private boolean visited = false;
    private State state = State.UNCARVED;
    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasTopWall() { return topWall; }
    public boolean hasBottomWall() { return bottomWall; }
    public boolean hasLeftWall() { return leftWall; }
    public boolean hasRightWall() { return rightWall; }

    public void removeTopWall() { topWall = false; }
    public void removeBottomWall() { bottomWall = false; }
    public void removeLeftWall() { leftWall = false; }
    public void removeRightWall() { rightWall = false; }
    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    @Override
    
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}

public enum State {
    UNCARVED,
    NORMAL,
    ACTIVE,
    EXPLORED,
    PATH
}
