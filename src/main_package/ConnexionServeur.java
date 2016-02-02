/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : ConnexionServeur.java
Date créé : 2016-01-12
Date dern. modif. 2016-01-19
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/


package main_package;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;


public class ConnexionServeur {
	
	private static Socket MonClient;
	private static String hostname = null;
	private static int port = 0;
	private static PrintWriter envoieServeur = null;
	private static BufferedReader réponseServeur = null;
	private static String requeteForme = "GET";
	private static int retry;
	
	/**
	 * Permet la connexion au serveur  
	 * 
	 * @param hostname Le nom d'hôte du serveur applicatif.
	 * @param port Le port de du serveur applicatif. Port par défaut = 10000

	 */
	
	public static void connexionServeur(String hostname,int port){

		//Connexion au Serveur
		try{
			
			MonClient = new Socket(hostname, port); //Création d'un nouveau socket
			envoieServeur = new PrintWriter(MonClient.getOutputStream(), true);
			réponseServeur = new BufferedReader(new InputStreamReader(MonClient.getInputStream()));
			
		} catch (UnknownHostException e) { //Avertir l'utilisateur lorsque le nom d'hôte est introuvable.
			
		      System.err.println("Le nom d'hôte "+ hostname + " est introuvable avec le serveur ");
		      JOptionPane.showMessageDialog(null,"Le nom d'hôte: " + hostname + " est introuvable");
		      
		      setRetry(JOptionPane.showConfirmDialog(null,
		    		  "Le nom d'hôte "+ hostname + "est introuvable. Voulez-vous réessayer avec un autre serveur ?", "Oui ou Non?",
                      JOptionPane.YES_NO_OPTION));
		      
		      if (getRetry() == JOptionPane.YES_OPTION) {
		    	  //Demande un nouveau serveur
		    	  nouveauServeur();
		    	  
		      }
		     
		      
		    } catch (IOException e) { //Avertir lorsque le serveur ne répond pas sur le port spécifié par l'utilisateur.
		    	
		      System.err.println("Le serveur ne semble pas être lancé sur le port" + port);
		      setRetry(JOptionPane.showConfirmDialog(null,
                      "Le serveur n'est pas démarré sur l'adresse suivante: "+ hostname + port + " Voulez-vous réessayer avec un autre serveur ?", "Oui ou Non?",
                      JOptionPane.YES_NO_OPTION));
		      
		      if (getRetry() == JOptionPane.YES_OPTION) {
		    	  
		    	  //Demande un nouveau serveur
		    	  nouveauServeur();
		      }
		      
		     }
	}
	
	/**
	 * 
	 * Méthode qui permet de demander un nouveau serveur à l'utilisateur.
	 * 
	 */
	
	private static void nouveauServeur() {
		
		String chaîneServeur = JOptionPane.showInputDialog
				("Veuillez entrer l'adresse ainsi que le port du serveur","localhost:10000");
		String [] serveur = chaîneServeur.split(":"); //Sépare la saisie de l'utilisateur en 2 partie afin de récupérer le nom d'hôte et le port
		String hostname = serveur[0]; //Assigne le hostname
		int port = Integer.parseInt(serveur[1]); //Assigne le port
		
		connexionServeur(hostname,port);
		
	}
	
	/**
	 * Requête "END" au serveur afin de mettre fin à la connexion.
	 */
	public static void deconnexionServeur() {
		
		try {
			envoieServeur = new PrintWriter(MonClient.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		envoieServeur.println("END");
			
	}
	/**
	 * Fonction permettant d'envoyer le commande "GET" au serveur pour générer des formes.
	 * 
	 * @return Chaîne de caractères représentants la forme générée par le serveur.
	 * @throws IOException
	 */
	
	public static String getForme() throws IOException {
		
		String forme;
		envoieServeur.println(requeteForme);//Demande au server une nouvelle forme
		réponseServeur.readLine(); //Lecture de la commande envoyé par le serveur
		forme = réponseServeur.readLine();
		
		return forme;
	}

	/**
	 * Permet de retourner le statut de retry.
	 * @return retry
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
