public class Knight extends Piece {

    public Knight(int row, int col, Color color) {
	super(row, col, color);
    }

    public Knight(Position pos, Color color) {
	super(pos, color);
    }
    
    public Knight(Color color) {
	super(color);
    }

    public String getName() {
	return "Knight";
    }

    public String toString() {
	return "" + (this.isWhite() ? '\u2658' : '\u265E');
    }

    public boolean moveOK(Position target) {
	Position from = this.getPosition(), to = target;

	return super.moveOK(target) &&
	    ((Move.verticalLength(from, to) == 1 && Move.horizontalLength(from, to) == 2)
	     || (Move.verticalLength(from, to) == 2 && Move.horizontalLength(from, to) == 1));
    }
}
