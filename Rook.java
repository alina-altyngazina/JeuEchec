public class Rook extends Piece {

    public Rook(int row, int col, Color color) {
	super(row, col, color);
    }

    public Rook(Position pos, Color color) {
	super(pos, color);
    }
    
    public Rook(Color color) {
	super(color);
    }

    public String getName() {
	return "Rook";
    }

    public String toString() {
	return "" + (this.isWhite() ? '\u2656' : '\u265C');
    }

    public boolean moveOK(Position target) {
	Position from = this.getPosition(), to = target;

	return super.moveOK(target) &&
	    (Move.isHorizontal(from, to) || Move.isVertical(from, to));
    }
}
