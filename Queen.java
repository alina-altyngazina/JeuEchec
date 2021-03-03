public class Queen extends Piece {

    public Queen(int row, int col, Color color) {
	super(row, col, color);
    }

    public Queen(Position pos, Color color) {
	super(pos, color);
    }
    
    public Queen(Color color) {
	super(color);
    }

    public String getName() {
	return "Queen";
    }

    public String toString() {
	return "" + (this.isWhite() ? '\u2655' : '\u265B');
    }


    public boolean moveOK(Position target) {
	Position from = this.getPosition(), to = target;
	
	return super.moveOK(target) &&
	    (Move.isHorizontal(from, to) || Move.isDiagonal(from, to) || Move.isVertical(from, to));
    }
}
