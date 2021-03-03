import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

// Launch with 'java -Dfile.encoding=UTF8 GoChess'

public class GoChess {
    // public static void main(String[] args) throws UnsupportedEncodingException {
    // 	PrintStream out = new PrintStream(System.out, true, "UTF-64");
    // 	Pawn p = new Pawn(Color.white);
    // 	out.println(p);
    // }

    public static void byebye() {
	System.out.println("Merci et à bientôt !");
	System.exit(0);
    }
    
    public static void main(String[] args)  {
	ChessBoard cb = new ChessBoard();
	//p = new Pawn(Color.white);
	char choix='L';
	boolean alternate=false;
	boolean badColor=false;
	
	Ecran.afficher ("Voulez-vous déplacer les pièces librement (tapez 'L' (choix par défaut)), \n"+
			"ou déplacer alternativement les blancs et les noirs (tapez 'A') ?\n");
	Ecran.afficher("Sinon tapez 'Q' pour quitter.\n");
	choix=Clavier.saisirChar();
	if (choix=='q' || choix=='Q')
	    byebye();

	if (choix=='a' || choix=='A') {
	    alternate=true;
	    Ecran.afficher("Les blancs et les noirs jouent à tour de rôle\n");
	}
	
	System.out.println(cb);
	
	
	Position from, to;

	boolean whitePlays = true;

	while (true) {
	    if (alternate)
		if (whitePlays)
		    System.out.println("Les blancs jouent");
		else
		    System.out.println("Les noirs jouent");

	    
	    do {
		badColor=false;
		Ecran.afficher("Entrez une position de depart (ligne=0 et colonne=0 pour quitter)\n");
		Ecran.afficher("\tNumero de ligne ? ");
		int row = Clavier.saisirInt();
		Ecran.afficher("\tNumero de colonne ? ");
		int col = Clavier.saisirInt();
		from = new Position(row, col);
		if (!from.isValid() && !from.equals(new Position(0,0)))
		    Ecran.afficher("La position "+from+" n'est pas une position valide. Recommencez.\n");
		if (cb.getPieceAt(from) == null && !from.equals(new Position(0,0)))
		    Ecran.afficher("La position "+from+" ne contient pas de piece. Recommencez.\n");
		
		if (alternate && cb.getPieceAt(from) != null &&
		    ((whitePlays && !cb.getPieceAt(from).isWhite()) ||
		     (!whitePlays && cb.getPieceAt(from).isWhite()))) {
		    Ecran.afficher("Ce n'est pas au tour des "+
				   (whitePlays ? "noirs" : "blancs")+
				   " de jouer. Recommencez\n");
		    badColor=true;
		}
		//	       if (cb.getPieceAt(from) == null)
		//	   Ecran.afficher("La position "+from+" ne contient pas de piece. Recommencez.\n");
		//	       } while (!from.isValid() || cb.getPieceAt(from) == null);
	    } while (!from.equals(new Position(0,0)) && (badColor || !from.isValid() || cb.getPieceAt(from)==null));
	    if (from.equals(new Position(0,0)))
		byebye();
	    
	    Ecran.afficher("Position de depart = "+from+"\n");
	    
	    do {
		Ecran.afficher("Entrez une position d'arrivee (ligne=0 et colonne=0 pour quitter)\n");
		Ecran.afficher("\tNumero de ligne ? ");
		int row = Clavier.saisirInt();
		Ecran.afficher("\tNumero de colonne ? ");
		int col = Clavier.saisirInt();
		to = new Position(row, col);
		if (!to.isValid() && !to.equals(new Position(0,0)))
		    Ecran.afficher("La position "+to+" n'est pas une position valide. Recommencez.\n");
	    } while (!to.isValid() && !to.equals(new Position(0,0)));
	    if (to.equals(new Position(0,0)))
		byebye();

	    Ecran.afficher("Position d'arrivee = "+to+"\n");
	    
	    
	    if (!cb.performMove(from, to))
		System.out.println("Pas de deplacement possible de "+from+" a "+to);
	    else {
		if (cb.getPieceAt(to) instanceof Pawn)
		    ((Pawn) cb.getPieceAt(to)).markAsMoved();
		if (alternate)
		    whitePlays = !whitePlays;
	    }
	    System.out.println(cb);
	}
	//if (cb.move(
	
    }
    
}
