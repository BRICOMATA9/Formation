/*
#	Le livre de Java, premier langage
#	A. Tasso
#	Chapitre 11 : Dessiner des objets 
#	Exercice   :  Support pour r�aliser l'exercice 11.5 
#	Fichier    : GestionFenetre.java
#	Class      : GestionFenetre
*/

import java.awt.event.*;
public class GestionFenetre extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}