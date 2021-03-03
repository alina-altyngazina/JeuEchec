public class Ecran {
    public static void main(String[] s) {
	int n;
	n=Clavier.saisirInt();
	afficherln("salut", n, 'c');
	afficherln("toto");
	afficherln();
	afficherln(24);
    }
    
    
    public static void afficher(Object... s) {
	for (Object o : s) {
	    System.out.print(o.toString());
	}
    }
    
    
    public static void afficherln(Object... s) {
	for (Object o : s) {
	    System.out.print(o.toString());
	}
	System.out.println();
    }
    
    
    public static void sautDeLigne() {
	System.out.println();
    }
}
