package main_package;

import javax.swing.JOptionPane;

public class DecortiqueurTexte {
	
	private static String stringServeur = null;	
	private static String nomServeur = null;
	private static int portServeur = 0;
			
			
	public static void decortiqeur() {
	
		stringServeur = JOptionPane.showInputDialog("Veuillez entrer l'adresse ainsi que le port du serveur", "localhost:10000");
		String[] tableauTexte = stringServeur.split(":"); // Sépare la saisie de l'utilisateur en 2 partie afin de récupérer le nom d'hôte et le port
		nomServeur = tableauTexte[0]; // Assigne le hostname
		portServeur = Integer.parseInt(tableauTexte[1]); // Assigne le port
	
	}
	
	public static String getNomServeur() {
		
		return nomServeur;
		
		
	}
	
	public static int getPortServeur() {
		
		return portServeur;
		
	}
}
