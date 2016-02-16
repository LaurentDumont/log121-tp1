package main_package;
/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : CreateurForme.java
Date créé : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/ 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * Classe permettant la gestion et la communication avec 
 * le serveur (Connexion, déconnexion, demande de forme, etc)
 *
 */
public class ConnexionServeur {

	private static Socket client;
	private static PrintWriter printwriter = null;
	private static BufferedReader bufferedReader = null;
	private static int retry;

	
	/**
	 * Permet la connexion au serveur incluant une gestion des erreurs
	 * 
	 * @param hostname Le nom d'hôte du serveur
	 * @param port Le port du serveur
	 * 
	 */
	public static void connexionServeur(String hostname,int port){
	
		//Tentative de connexion au serveur
		try{
			client = new Socket(hostname, port); //Création d'un nouveau socket avec les informations fournis
			printwriter = new PrintWriter(client.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));			
		} 
		
		// Si l'hostname n'existe pas 
		catch (UnknownHostException e) 
		{ 
		      //Demande à  l'utilisateur s'il veut recommencer
		      retry = JOptionPane.showConfirmDialog(null,"Le nom d'hôte ''"+ hostname + "'' est introuvable. Voulez-vous spécifier un autre serveur ?", "Application Formes",JOptionPane.YES_NO_OPTION);		   		      
		      System.out.println("Le nom d'hôte ''"+ hostname + "'' est introuvable");

		      if(retry == 0) //retry == 0 si l'utilisateur veux recommencer, sinon on quitte l'application
		    	  nouvelleConnexion();
		      else 
		    	  System.exit(0); 		      	     
		}
		
		
		
		//Si le port spécifié est hors de porté
		catch (IllegalArgumentException e) 
		{
		      //Demande à l'utilisateur s'il veut recommencer ou non
		      retry = JOptionPane.showConfirmDialog(null,"Le port numéro : "+ port + ", est hors de porté. Voulez-vous spécifier un autre port ?", "Application Formes",JOptionPane.YES_NO_OPTION);		   		      
		      System.out.println("Le port numéro ''"+ port + "'' est hors de porté");

		      if(retry == 0) //recommencer == 0 si l'utilisateur veux recommencer
		    	  nouvelleConnexion();
		      else //sinon on quitte l'application
		    	  System.exit(0); 		      	     
		}
		
		
		
		// Si le serveur ne répond pas ou si le port est mauvais 
		catch (IOException e) 
		{ 
		      //Demande Ã  l'utilisateur s'il désir recommencer ou non
		      retry = JOptionPane.showConfirmDialog(null,"Le serveur ne répond pas Ã  l'adresse suivante: ''"+ hostname + ":" + port + "''. Voulez-vous spécifier un autre serveur:port ?", "Application Formes",JOptionPane.YES_NO_OPTION);		     		      
		      System.out.println("Le serveur n'est pas démarré ");

		      if(retry == 0) //recommencer == 0 si l'utilisateur veux recommencer
		    	  nouvelleConnexion();
		      else //sinon on quitte l'application
		    	  System.exit(0); 
		 }
	}

	/**
	 * Envoie la commande "END" afin d'arreter le serveur
	 * 
	 */
	public static void deconnexion(){
		try {
			printwriter = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		printwriter.println("END");//Dit au serveur de fermer la connexion
		
	}

	/**
	 * Envoie la commande "GET" afin d'obtenir des formes
	 * 
	 * @return La forme en chaine de caractère envoyé par le serveur
	 * @throws IOException
	 */
	public static String getForme() throws IOException {
		String forme;
		printwriter.println("GET");//Demande au serveur une nouvelle forme
		bufferedReader.readLine(); //Lecture de la forme envoyé par le serveur
		forme = bufferedReader.readLine();
		
		return forme;
	}
	
	
	/**
	 * Méthode pour essayer une nouvelle connexion avec le serveur lorsque 
	 * l'utilisateur demande de le faire
	 */
	private static void nouvelleConnexion() {
		String NomDeServeur = JOptionPane.showInputDialog("Quel est l'adresse du serveur?","localhost:10000");
		if (DecortiqueurTexte.decortiqueur(NomDeServeur)){
		String [] serveur = NomDeServeur.split(":"); //Sépare la chaine de caractère afin de récupérer le nom d'hôte et le port
		String hostname = serveur[0]; //Assigne le hostname
		int port = Integer.parseInt(serveur[1]); //Assigne le port
		connexionServeur(hostname,port);
		}
		else {
			nouvelleConnexion();
		}
	}
	
	/**
	 * 
	 * @return retry Si l'utilisateur reessaie ou non (1 ou 0)
	 */
	public static int getRetry() {
		
		return retry;
	
	}
	
	/**
	 * Permet de changer la valeur de la variable retry.
	 * @param retry
	 */
	
	public static void setRetry(int retry) {
		
		ConnexionServeur.retry = retry;
	
	}
}
