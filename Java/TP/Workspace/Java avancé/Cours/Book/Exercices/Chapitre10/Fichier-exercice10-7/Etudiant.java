/*
#	Le livre de Java, premier langage
#	A. Tasso
#	Chapitre 10 : Collectionner un nombre ind�termin� d'objets 
#	Exercice   : 10.7 
#	Fichier    : Etudiant.java
#	Class      : Etudiant
*/

import java.util.*;
import java.io.*;
public class Etudiant	 implements Serializable	{
	private String nom, pr�nom;
        private ArrayList<Double>  notes;
	private double moyenne;
	public Etudiant()   	{
                Scanner lectureClavier = new Scanner(System.in);
		System.out.print("Entrer le nom de l'etudiant : ");
		nom = lectureClavier.next();
		System.out.print("Entrer le pr�nom de l'etudiant : ");
		pr�nom = lectureClavier.next();
		System.out.print("Combien de notes pour l'etudiant  ");
		System.out.print( pr�nom + " " + nom + " :  ");
		int nombre = lectureClavier.nextInt();
	        notes = new ArrayList<Double> ();
		for (int i = 0; i < nombre; i ++)	{
			System.out.print("Entrer la note  n� "+ (i+1)+ " :  ");
			notes.add(new Double(lectureClavier.nextDouble()));
		}
		moyenne = calculMoyenne();
	}
	public void afficheUnEtudiant()  {
		System.out.print(" Les notes  de "+pr�nom+" "+nom+ " sont : ");
		int nbnotes = notes.size();
		for(Double valeur : notes) 
			System.out.print(" "+ valeur);
		System.out.println();
		System.out.println("Sa moyenne vaut  "+ moyenne);
	}

	private double calculMoyenne() 	{ 
		double somme = 0.0;
		int nbnotes = notes.size();
		for(Double valeur : notes) {
			somme = somme + valeur;
		}
		return somme/nbnotes;
	}

	public String quelNom()	{ 
		return nom;
	}
	public String quelPr�nom()	{ 
		return pr�nom;
	}
	public double quelleMoyenne()	{ 
		return moyenne;
	}
}