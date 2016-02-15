package main_package;
/******************************************************
Cours:  LOG121
Session: E2015
Projet: Squelette du laboratoire #2
Ã‰tudiant(e)s: Julien Lemonde, Alexandre Malo, Marc-Antoine Hebert, Jean-Michel Coupal

Professeur : Francis Cardinal
Nom du fichier: CommBase.java
Date créé: 2015-05-03
*******************************************************
Description de la classe
Base d'une communication via un fil d'exécution parallèle.
*******************************************************
@author Patrice Boucher
 @Modification Julien Lemonde, Alexandre Malo, Marc-Antoine Hebert, Jean-Michel Coupal
 @date 2013/05/04
*******************************************************/ 

import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import Forme.Forme;
import Forme.CreateurFormes;


public class CommBase {
	
	private String input ;
	private final int DELAI = 50;
	@SuppressWarnings("rawtypes")
	private SwingWorker threadComm =null;
	private PropertyChangeListener listener = null;
	private static boolean isActif = false;
	private String hostname;
	private int port;
	private CreateurFormes cf;
	
	/**
	 * Constructeur de base
	 */
	public CommBase(){}
	
	/**
	 * Définir le récepteur de l'information reçue dans la communication avec le serveur
	 * @param listener sera alerté lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * Démarre la communication avec le serveur
	 */
	public void start(){
		input = JOptionPane.showInputDialog("Quel est le nom d'hôte et le port du serveur de formes?","localhost:10000");
		
		String [] infoServeur = input.split(":"); //Sépare la saisie de l'utilisateur afin de récupérer le nom d'hÃ´te et le port
		hostname = infoServeur[0]; //Assigne le hostname
		port = Integer.parseInt(infoServeur[1]); //Assigne le port
		ConnexionServeur.connexion(hostname,port); //Lance la connexion avec le serveur
		isActif = true;
		creerCommunication();
	}
	
	/**
	 * Arrête la communication avec le serveur
	 */
	public void stop(){
				
		if(isActif)
			ConnexionServeur.deconnexion(); //Arrete la communication avec le serveur
		
		if(threadComm!=null)
			threadComm.cancel(true); 
		
		isActif = false;
	}
	
	/**
	 * Créer le nécessaire pour la communication avec le serveur
	 */
	@SuppressWarnings("rawtypes")
	protected void creerCommunication(){		
		// Crée un fil d'exécusion parallèle au fil courant,
				
		threadComm = new SwingWorker(){
			
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("Le fils d'exécution parallèle est lancé !");
				
				// C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
				int index = 0 ;
				while(true && index < 10){
					
					Thread.sleep(DELAI);					
						String forme = ConnexionServeur.getForme(); //Récupère les formes
						Decoder ReponseDuServeur = new Decoder(forme);
						Forme formeADessiner = CreateurFormes.creerForme(ReponseDuServeur); //Créer une nouvelle forme
					
						//La méthode suivante alerte l'observateur 
						if(listener!=null)
						{
							System.out.println(ReponseDuServeur.getTypeForme());
							firePropertyChange("ENVOIE-TEST", null, formeADessiner); //Donne la forme Ã  l'observateur
						}
						index++;		
				}
				stop();	
				return index;
				
				}
			};
		if(listener!=null)
	    threadComm.addPropertyChangeListener(listener); // La méthode "propertyChange" de ApplicationFormes sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'exécution parallèle.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'exécution parallèle est actif
	 */
	public static boolean isActif(){
		return isActif;
	}
	
}
