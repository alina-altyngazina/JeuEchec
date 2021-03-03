public class King extends Piece {

    public King(int row, int col, Color color) {
	super(row, col, color);
    }

    public King(Position pos, Color color) {
	super(pos, color);
    }
    
    public King(Color color) {
	super(color);
    }

    public String getName() {
	return "King";
    }

    public String toString() {
	return "" + (this.isWhite() ? '\u2654' : '\u265A');
    }


    public boolean moveOK(Position target) {
	Position from = this.getPosition(), to = target;
	
	return super.moveOK(target) &&
	    (
	     (Move.horizontalLength(from, to) == 1 && Move.verticalLength(from, to) == 0)
	     || (Move.horizontalLength(from, to) == 0 && Move.verticalLength(from, to) == 1)
	     || (Move.horizontalLength(from, to) == 1 && Move.verticalLength(from, to) == 1)
	     );
    }
}
