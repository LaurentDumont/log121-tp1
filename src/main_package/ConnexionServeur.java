package main_package;
/******************************************************
Cours:  LOG121
Session: E2015
Projet: Squelette du laboratoire #2
Ã‰tudiant(e)s: Julien Lemonde, Alexandre Malo, Marc-Antoine Hebert, Jean-Michel Coupal

Professeur : Francis Cardinal
Nom du fichier: Connexion.java
Date créé: 2015-05-03
*******************************************************
Description de la classe
Classe permettant la gestion et la communication avec 
le serveur (Connexion, déconnexion, demande de forme, etc)
*******************************************************
@author Julien Lemonde, Alexandre Malo, Marc-Antoine Hebert, Jean-Michel Coupal
2015-05-03 Version initiale
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
	private static int recommencer;
	
	/**
	 * Permet la connexion au serveur incluant une gestion des erreurs
	 * 
	 * @param hostname Le nom d'hôte du serveur
	 * @param port Le port du serveur
	 */
	public static void connexion(String hostname,int port){
	
		//Tentative de connexion au serveur
		try{
			client = new Socket(hostname, port); //Création d'un nouveau socket avec les informations fournis
			printwriter = new PrintWriter(client.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));			
		} 
		
		//----------- Si l'hostname n'existe pas -------------
		catch (UnknownHostException e) 
		{ 
		      //Demande Ã  l'utilisateur s'il désir recommencer ou non
		      recommencer = JOptionPane.showConfirmDialog(null,"Le nom d'hôte ''"+ hostname + "'' est introuvable. Voulez-vous spécifier un autre serveur ?", "Application Formes",JOptionPane.YES_NO_OPTION);		   		      
		      System.out.println("Le nom d'hôte ''"+ hostname + "'' est introuvable");

		      if(recommencer == 0) //recommencer == 0 si l'utilisateur veux recommencer
		    	  nouvelleConnexion();
		      else //sinon on quitte l'application
		    	  System.exit(0); 		      	     
		}//------------------------------------------------------
		
		
		
		//------- Si le port spécifié est hors de porté --------
		catch (IllegalArgumentException e) 
		{
		      //Demande Ã  l'utilisateur s'il désir recommencer ou non
		      recommencer = JOptionPane.showConfirmDialog(null,"Le port numéro : "+ port + ", est hors de porté. Voulez-vous spécifier un autre port ?", "Application Formes",JOptionPane.YES_NO_OPTION);		   		      
		      System.out.println("Le port numéro ''"+ port + "'' est hors de porté");

		      if(recommencer == 0) //recommencer == 0 si l'utilisateur veux recommencer
		    	  nouvelleConnexion();
		      else //sinon on quitte l'application
		    	  System.exit(0); 		      	     
		}//-------------------------------------------------------
		
		
		
		//---- Si le serveur ne répond pas ou si le port est mauvais ----
		catch (IOException e) 
		{ 
		      //Demande Ã  l'utilisateur s'il désir recommencer ou non
		      recommencer = JOptionPane.showConfirmDialog(null,"Le serveur ne répond pas Ã  l'adresse suivante: ''"+ hostname + ":" + port + "''. Voulez-vous spécifier un autre serveur:port ?", "Application Formes",JOptionPane.YES_NO_OPTION);		     		      
		      System.out.println("Le serveur n'est pas démarré ");

		      if(recommencer == 0) //recommencer == 0 si l'utilisateur veux recommencer
		    	  nouvelleConnexion();
		      else //sinon on quitte l'application
		    	  System.exit(0); 
		 }//-------------------------------------------------------
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
	 * Méthode pour retenter la connexion avec le serveur lorsque 
	 * l'utilisateur demande de le faire
	 */
	private static void nouvelleConnexion() {
		String NomDeServeur = JOptionPane.showInputDialog("Quel est l'adresse du serveur?","localhost:10000");
		
		String [] serveur = NomDeServeur.split(":"); //Sépare la chaine de caractère afin de récupérer le nom d'hôte et le port
		String hostname = serveur[0]; //Assigne le hostname
		int port = Integer.parseInt(serveur[1]); //Assigne le port
		
		connexion(hostname,port);		
	}
	
}
