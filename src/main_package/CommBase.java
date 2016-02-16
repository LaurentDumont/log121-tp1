package main_package;
/******************************************************
Cours:  LOG121
Session: E2015
Projet: Squelette du laboratoire #2
Ã‰tudiant(e)s: Bach Nguyen-Ngoc, Laurent Dumont

Professeur : Francis Cardinal
Nom du fichier: CommBase.java
Date créé: 2015-05-03
*******************************************************
Description de la classe
Base d'une communication via un fil d'exécution parallèle.
********************************************************/

import java.beans.PropertyChangeListener;

import javax.swing.SwingWorker;

import Forme.Forme;
import Forme.CreateurFormes;


public class CommBase {
	
	private final int DELAI = 50;
	@SuppressWarnings("rawtypes")
	private SwingWorker threadComm =null;
	private PropertyChangeListener listener = null;
	private static boolean isActif = false;
	
	/**
	 * Constructeur de base
	 */
	public CommBase(){}
	
	/**
	 * Définir le récepteur de l'information reçue dans la communication avec le serveur
	 * listener sera alerté lors de l'appel de "firePropertyChanger"
	 * @param listener un actionlistener
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * Démarre la communication avec le serveur
	 */
	public void start(){
		// Lance la connexion avec le serveur
				ConnexionServeur.connexionServeur(DecortiqueurTexte.getNomServeur(), DecortiqueurTexte.getPortServeur());
				if (ConnexionServeur.getRetry() == 0) {
					creerCommunication();
				}
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
				
				// Boucle qui recevra les 10 formes et les stockera
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
