public class Position {
    private int row;
    private int col;
    
    Position() {
	row=1;
	col=1;
    }
    
    Position(int row, int col) {
	this.row=row;
	this.col=col;
    }
    
    public int getRow() { 
	return row;
    }
    
    public int getCol() { 
	return col;
    }
    
    public boolean isValid() {
	return (1 <= row && row <= 8 && 1 <= col && col <= 8);
    }

    public boolean equals(Position that) {
	return this.row == that.row && this.col == that.col;
    }

    public String toString() {
	return "["+row+"]["+col+"]";
    }
}
