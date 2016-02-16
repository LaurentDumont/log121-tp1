package main_package;
/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
�tudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Charg� de labo : Simon Robert
Nom du fichier : CreateurForme.java
Date cr�� : 2016-01-12
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
 * le serveur (Connexion, d�connexion, demande de forme, etc)
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
	 * @param hostname Le nom d'h�te du serveur
	 * @param port Le port du serveur
	 * 
	 */
	public static void connexionServeur(String hostname,int port){
	
		//Tentative de connexion au serveur
		try{
			client = new Socket(hostname, port); //Cr�ation d'un nouveau socket avec les informations fournis
			printwriter = new PrintWriter(client.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));			
		} 
		
		// Si l'hostname n'existe pas 
		catch (UnknownHostException e) 
		{ 
		      //Demande � l'utilisateur s'il veut recommencer
		      retry = JOptionPane.showConfirmDialog(null,"Le nom d'h�te ''"+ hostname + "'' est introuvable. Voulez-vous sp�cifier un autre serveur ?", "Application Formes",JOptionPane.YES_NO_OPTION);		   		      
		      System.out.println("Le nom d'h�te ''"+ hostname + "'' est introuvable");

		      if(retry == 0) //retry == 0 si l'utilisateur veux recommencer, sinon on quitte l'application
		    	  nouvelleConnexion();
		      else 
		    	  System.exit(0); 		      	     
		}
		
		
		
		//Si le port sp�cifi� est hors de port�
		catch (IllegalArgumentException e) 
		{
		      //Demande �l'utilisateur s'il veut recommencer ou non
		      retry = JOptionPane.showConfirmDialog(null,"Le port num�ro : "+ port + ", est hors de port�. Voulez-vous sp�cifier un autre port ?", "Application Formes",JOptionPane.YES_NO_OPTION);		   		      
		      System.out.println("Le port num�ro ''"+ port + "'' est hors de port�");

		      if(retry == 0) //recommencer == 0 si l'utilisateur veux recommencer
		    	  nouvelleConnexion();
		      else //sinon on quitte l'application
		    	  System.exit(0); 		      	     
		}
		
		
		
		// Si le serveur ne r�pond pas ou si le port est mauvais 
		catch (IOException e) 
		{ 
		      //Demande à l'utilisateur s'il d�sir recommencer ou non
		      retry = JOptionPane.showConfirmDialog(null,"Le serveur ne r�pond pas à l'adresse suivante: ''"+ hostname + ":" + port + "''. Voulez-vous sp�cifier un autre serveur:port ?", "Application Formes",JOptionPane.YES_NO_OPTION);		     		      
		      System.out.println("Le serveur n'est pas d�marr� ");

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
	 * @return La forme en chaine de caract�re envoy� par le serveur
	 * @throws IOException
	 */
	public static String getForme() throws IOException {
		String forme;
		printwriter.println("GET");//Demande au serveur une nouvelle forme
		bufferedReader.readLine(); //Lecture de la forme envoy� par le serveur
		forme = bufferedReader.readLine();
		
		return forme;
	}
	
	
	/**
	 * M�thode pour essayer une nouvelle connexion avec le serveur lorsque 
	 * l'utilisateur demande de le faire
	 */
	private static void nouvelleConnexion() {
		String NomDeServeur = JOptionPane.showInputDialog("Quel est l'adresse du serveur?","localhost:10000");
		if (DecortiqueurTexte.decortiqueur(NomDeServeur)){
		String [] serveur = NomDeServeur.split(":"); //S�pare la chaine de caract�re afin de r�cup�rer le nom d'h�te et le port
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
