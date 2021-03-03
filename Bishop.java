public class Bishop extends Piece {

    public Bishop(int row, int col, Color color) {
	super(row, col, color);
    }

    public Bishop(Position pos, Color color) {
	super(pos, color);
    }
    
    public Bishop(Color color) {
	super(color);
    }

    public String getName() {
	return "Bishop";
    }

    public String toString() {
	return "" + (this.isWhite() ? '\u2657' : '\u265D');
    }

    public boolean moveOK(Position target) {
	return super.moveOK(target) && Move.isDiagonal(this.getPosition(), target);
    }
}
