public abstract class Piece {
    private Color color;
    private Position pos;

    public Piece(int row, int col, Color color) {
	this.pos = new Position(row, col);
	if (!pos.isValid())
	    pos = new Position();
	this.color = color;
    }
    
    public Piece(Position pos, Color color) {
	this(pos.getRow(), pos.getCol(), color);
    }

    public Piece(Color color) {
	this.pos = null;
	this.color = color;
    }

    public abstract String getName();

    public boolean isWhite() {
	return color==Color.white;
    }

    public boolean isBlack() {
	return color==Color.black;
    } 

    public boolean moveOK(Position target) {
	return this.pos.isValid() && target.isValid() &&
	    Move.isNotNull(pos, target);
    }

    public Position getPosition() {
	return this.pos;
    }

    public void updatePosition(Position pos) {
	this.pos = pos;
    }
}
