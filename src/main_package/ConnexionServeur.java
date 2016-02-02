/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
�tudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Charg� de labo : Simon Robert
Nom du fichier : ConnexionServeur.java
Date cr�� : 2016-01-12
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
	private static BufferedReader r�ponseServeur = null;
	private static String requeteForme = "GET";
	private static int retry;
	
	/**
	 * Permet la connexion au serveur  
	 * 
	 * @param hostname Le nom d'h�te du serveur applicatif.
	 * @param port Le port de du serveur applicatif. Port par d�faut = 10000

	 */
	
	public static void connexionServeur(String hostname,int port){

		//Connexion au Serveur
		try{
			
			MonClient = new Socket(hostname, port); //Cr�ation d'un nouveau socket
			envoieServeur = new PrintWriter(MonClient.getOutputStream(), true);
			r�ponseServeur = new BufferedReader(new InputStreamReader(MonClient.getInputStream()));
			
		} catch (UnknownHostException e) { //Avertir l'utilisateur lorsque le nom d'h�te est introuvable.
			
		      System.err.println("Le nom d'h�te "+ hostname + " est introuvable avec le serveur ");
		      JOptionPane.showMessageDialog(null,"Le nom d'h�te: " + hostname + " est introuvable");
		      
		      setRetry(JOptionPane.showConfirmDialog(null,
		    		  "Le nom d'h�te "+ hostname + "est introuvable. Voulez-vous r�essayer avec un autre serveur ?", "Oui ou Non?",
                      JOptionPane.YES_NO_OPTION));
		      
		      if (getRetry() == JOptionPane.YES_OPTION) {
		    	  //Demande un nouveau serveur
		    	  nouveauServeur();
		    	  
		      }
		     
		      
		    } catch (IOException e) { //Avertir lorsque le serveur ne r�pond pas sur le port sp�cifi� par l'utilisateur.
		    	
		      System.err.println("Le serveur ne semble pas �tre lanc� sur le port" + port);
		      setRetry(JOptionPane.showConfirmDialog(null,
                      "Le serveur n'est pas d�marr� sur l'adresse suivante: "+ hostname + port + " Voulez-vous r�essayer avec un autre serveur ?", "Oui ou Non?",
                      JOptionPane.YES_NO_OPTION));
		      
		      if (getRetry() == JOptionPane.YES_OPTION) {
		    	  
		    	  //Demande un nouveau serveur
		    	  nouveauServeur();
		      }
		      
		     }
	}
	
	/**
	 * 
	 * M�thode qui permet de demander un nouveau serveur � l'utilisateur.
	 * 
	 */
	
	private static void nouveauServeur() {
		
		String cha�neServeur = JOptionPane.showInputDialog
				("Veuillez entrer l'adresse ainsi que le port du serveur","localhost:10000");
		String [] serveur = cha�neServeur.split(":"); //S�pare la saisie de l'utilisateur en 2 partie afin de r�cup�rer le nom d'h�te et le port
		String hostname = serveur[0]; //Assigne le hostname
		int port = Integer.parseInt(serveur[1]); //Assigne le port
		
		connexionServeur(hostname,port);
		
	}
	
	/**
	 * Requ�te "END" au serveur afin de mettre fin � la connexion.
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
	 * Fonction permettant d'envoyer le commande "GET" au serveur pour g�n�rer des formes.
	 * 
	 * @return Cha�ne de caract�res repr�sentants la forme g�n�r�e par le serveur.
	 * @throws IOException
	 */
	
	public static String getForme() throws IOException {
		
		String forme;
		envoieServeur.println(requeteForme);//Demande au server une nouvelle forme
		r�ponseServeur.readLine(); //Lecture de la commande envoy� par le serveur
		forme = r�ponseServeur.readLine();
		
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
