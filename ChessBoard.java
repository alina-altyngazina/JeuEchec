import java.util.ArrayList;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
	board = new Piece[8][8];

	// White pieces
	board[0][0] = new Rook(1, 1, Color.white);
	board[0][7] = new Rook(1, 8, Color.white);

	board[0][1] = new Knight(1, 2, Color.white);
	board[0][6] = new Knight(1, 7, Color.white);

    	board[0][2] = new Bishop(1, 3, Color.white);
	board[0][5] = new Bishop(1, 6, Color.white);

    	board[0][3] = new Queen(1, 4, Color.white);
	board[0][4] = new King(1, 5, Color.white);

	for (int i=0; i< board.length; i++)
	    board[1][i] = new Pawn(2, i+1, Color.white);

	// Black pieces
	board[7][0] = new Rook(8, 1, Color.black);
	board[7][7] = new Rook(8, 8, Color.black);

	board[7][1] = new Knight(8, 2, Color.black);
	board[7][6] = new Knight(8, 7, Color.black);

    	board[7][2] = new Bishop(8, 3, Color.black);
	board[7][5] = new Bishop(8, 6, Color.black);

    	board[7][3] = new Queen(8, 4, Color.black);
	board[7][4] = new King(8, 5, Color.black);

	for (int i=0; i< board.length; i++)
	    board[6][i] = new Pawn(7, i+1, Color.black);
    }

    public Piece getPieceAt(Position pos) {
	if (pos.isValid())
	    return board[pos.getRow()-1][pos.getCol()-1];
	return null;
    }

    public void setPieceAt(Piece p, Position pos) {
	if (pos.isValid()) {
	    board[pos.getRow()-1][pos.getCol()-1] = p;
	}
    }

    public String toString() {
	String str = "    ";
	for (int col=1; col<=8 ; col++)
	    str += col+" ";
	str += "\n    - - - - - - - -    \n";
	for (int row=this.board.length; row >= 1; row--) {
	    str += row+" | ";
	    for (int col=1; col <= this.board[row-1].length; col++)
		str += (board[row-1][col-1] == null ? ". " : (board[row-1][col-1] + " "));
	    str += " | "+row;
	    if (row > 1)
		str += "\n";
	}
	str += "\n    - - - - - - - -    \n    ";
	for (int col=1; col<=8 ; col++)
	    str += col+" ";

	return str;
    }

    public ArrayList<Position> getBetweenSquares(Position from, Position to) {
	if (!(from.isValid() && to.isValid()))
	    return null;

	if (from.equals(to))
	    return null;
	
	ArrayList<Position> betweenSquares = new ArrayList<Position>();
	// A l'exception du cavalier, qui n'est pas concerné par la présence de
	// pièces sur les cases intermédiaires de son mouvement, tous les mouvements
	// sont soit verticaux, soit horizontaux, soit diagonaux.
	// Dans chacun de ces 3 cas on énumère les cases intermédiaires

	// unité d'incrément horizontal : 1 si déplacement vers la droite, -1 si vers la gauche
	int hIncUnit = from.getCol()<=to.getCol() ? 1 : -1;
	// unité d'incrément vertical : 1 si déplacement vers le haut, -1 si vers le bas
	int vIncUnit = from.getRow()<=to.getRow() ? 1 : -1;
	    
	if (Move.isHorizontal(from, to)) {
	    for (int i=from.getCol()+hIncUnit; i!=to.getCol(); i+= hIncUnit)
		betweenSquares.add(new Position(from.getRow(), i));
	    return betweenSquares;
	}

	if (Move.isVertical(from, to)) {
	    for (int i=from.getRow()+vIncUnit; i!=to.getRow(); i+=vIncUnit)
		betweenSquares.add(new Position(i, from.getCol()));
	    return betweenSquares;
	}

	if (Move.isDiagonal(from, to)) {
	    // Le déplacement a autant de cases en vertical qu'en horizontal
	    // on itère arbitrairement en vertical
	    int j = from.getCol();
	    for (int i=from.getRow()+vIncUnit; i!=to.getRow(); i+=vIncUnit) {
		j += hIncUnit;
		betweenSquares.add(new Position(i, j));
	    }
	    return betweenSquares;
	}
	
	// Si on arrive là, le mouvement n'était ni horizontal, ni vertical, ni diagonal
	// on retourne une liste vide
	return betweenSquares;
    }

    public boolean movePossible(Position from, Position to) {
	// Un mouvement est possible si :
	// - il est théoriquement valide pour la pièce, et
	// - les cases intermédiaires ne bloquent pas le déplacement
	//   par la présence de pièces (Knight non concerné)
	// Pour le pion, un mouvement d'une case en diagonale est autorisé si c'est une prise,
	// de plus le mouvement d'une (ou deux) cases en avant est bloqué si la case cible est occupée
	Piece p = this.getPieceAt(from);

	if (p==null) {
	    // il n'y a pas de pièce à bouger ici
	    System.out.println("*** ERREUR : En position "+from+" il n'y a pas de pièce à déplacer");
	    return false;
	}

	if (!p.moveOK(to)) {
	    // le mouvement demandé n'est pas autorisé pour la pièce
	    System.out.println("*** ERREUR : Le mouvement de "+from+" à "+to+
			       " n'est pas autorisé pour la pièce "+p.getClass().getSimpleName());
	    return false;
	}
	
	// Ici le mouvement est conforme à celui de la pièce

	// Si c'est une prise par un pion, on vérifie qu'il y'a bien une pièce à prendre
	if (p instanceof Pawn && ((Pawn) p).captureMove(to) && this.getPieceAt(to) == null) {
	    System.out.println("*** ERREUR : Le mouvement de prise du pion de "+from+" à "+to+
			       " n'est pas autorisé car il n'y a pas de pièce en "+to);
	    return false;
	}
	// Reste maintenant à savoir si des cases intermédiaires
	// empêchent le mouvement d'une pièce
	if (p instanceof Knight && this.getPieceAt(to) == null)
	    // Le cavalier n'est pas concerné, sa prise éventuelle sera gérée
	    // à la fin comme les autres prises
	    return true;

	for (Position pos : getBetweenSquares(from, to))
	    if (this.getPieceAt(pos) != null) {
		System.out.println("*** ERREUR : Il y'a une pièce en "+pos+" qui empèche la pièce "+
				   p.getClass().getSimpleName()+" de se déplacer de "+from+" à "+to);
		return false;
	    }

	// Ici les cases intermédiaires sont libres, et sauf si la pièce
	// est un pion, le mouvement est possible. Si c'est un pion, et qu'il
	// n'est pas en train de prendre en diagonale, il reste en plus
	// à tester que la case d'arrivée est libre car la prise en avant est
	// interdite pour un pion.
	if (p instanceof Pawn && !((Pawn)p).captureMove(to) && this.getPieceAt(to) != null) {
	    System.out.println("*** ERREUR : La prise de "+from+" à "+to+
			       " n'est pas autoriséé car la prise en avant est interdite pour un pion.");
	    return false;
	}

	// Il reste à vérifier qu'en cas de prise, c'est une pièce adverse qui est prise
	if (this.getPieceAt(to) != null && p.isWhite()==this.getPieceAt(to).isWhite()) {
	    System.out.println("La pièce en "+to+" est de votre camp ! Vous n'avez pas le droit de la prendre");
	    return false;
	}
	    

	return true;
    }

    public boolean performMove(Position from, Position to) {
	if (movePossible(from, to)) {
	    this.setPieceAt(this.getPieceAt(from), to);
	    this.getPieceAt(to).updatePosition(to);
	    this.setPieceAt(null, from);
 
	    if (this.getPieceAt(to) instanceof Pawn)
		((Pawn) this.getPieceAt(to)).markAsMoved();

	    return true;
	}
	
	return false;
    }
}
		       
