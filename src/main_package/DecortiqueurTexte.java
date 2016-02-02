/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : DecortiqueurTexte.java
Date créé : 2016-01-15
Date dern. modif. 2016-01-19
*******************************************************
Historique des modifications
*******************************************************
2016-01-6 Version initiale
*******************************************************/

package main_package;

import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DecortiqueurTexte {
	
	private static String stringServeur = null;	
	private static String nomServeur = null;
	private static int portServeur = 0;
			
			
	public static void decortiqueur() {
	
		
		stringServeur = JOptionPane.showInputDialog("Veuillez entrer l'adresse ainsi que le port du serveur", "localhost:10000");
		Pattern p = Pattern.compile("(.*):\\d");
		Matcher m = p.matcher(stringServeur);
		
		if (m == null){
			decortiqueur();
			
		}
		
		while(m.find()){
		String[] tableauTexte = stringServeur.split(":"); // Sépare la saisie de l'utilisateur en 2 partie afin de récupérer le nom d'hôte et le port
		nomServeur = tableauTexte[0]; // Assigne le hostname
		portServeur = Integer.parseInt(tableauTexte[1]); // Assigne le port
		}
	}
	
	public static String getNomServeur() {
		
		return nomServeur;
		
		
	}
	
	public static int getPortServeur() {
		
		return portServeur;
		
	}
}
