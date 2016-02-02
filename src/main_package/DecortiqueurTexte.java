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
		String[] tableauTexte = stringServeur.split(":"); // S�pare la saisie de l'utilisateur en 2 partie afin de r�cup�rer le nom d'h�te et le port
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
