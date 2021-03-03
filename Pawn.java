public class Pawn extends Piece {
    private boolean alreadyMoved;
    private int moveUnit;
		
    public Pawn(int row, int col, Color color) {
	super(row, col, color);
	alreadyMoved = false;
	this.moveUnit = (color==Color.white ? 1 : -1);
    }
    
    public Pawn(Position pos, Color color) {
	super(pos, color);
	alreadyMoved = false;
	this.moveUnit = (color==Color.white ? 1 : -1);
    }
    
    public Pawn(Color color) {
	super(color);
	alreadyMoved = false;
	this.moveUnit = (color==Color.white ? 1 : -1);
    }

    public String getName() {
	return "Pawn";
    }

    public String toString() {
	return "" + (this.isWhite() ? '\u2659' : '\u265F');
    }

	
    public boolean moveOK(Position target) {
	Position from = this.getPosition(), to = target;
	if (!super.moveOK(target))
	    return false;
	if (this.captureMove(to))
	    return true;
	if (alreadyMoved)
	    return to.getCol()==from.getCol() && to.getRow()==from.getRow()+moveUnit;
	return to.getCol()==from.getCol() &&
	    ((to.getRow()==from.getRow()+moveUnit) ||
	     (to.getRow()==from.getRow()+2*moveUnit));

    }

    public boolean captureMove(Position target) {
	// Le pion est autorisé à avancer d'une case en diagonale
	// pour prendre. On vérifie ici juste si le mouvement est d'une
	// case en diagonale. La vérification qu'il y'a bien une pièce à
	// prendre sera réalisée à l'extérieur, par l'échiquier
	Position from = this.getPosition(), to = target;
	return super.moveOK(target) && Move.isDiagonal(from, to) &&
	    Move.horizontalLength(from, to) == 1;
	    // && to.getRow() ==from.getRow()+moveUnit; <-- faux : la prise en arriere est possible
    }

    public void markAsMoved() {
	this.alreadyMoved = true;
    }
}
