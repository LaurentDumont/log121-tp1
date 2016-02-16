package main_package;
/******************************************************
Cours:  LOG121
Session: E2015
Projet: Squelette du laboratoire #2
Étudiant(e)s: Bach Nguyen-Ngoc, Laurent Dumont

Professeur : Francis Cardinal
Nom du fichier: CommBase.java
Date cr��: 2015-05-03
*******************************************************
Description de la classe
Base d'une communication via un fil d'ex�cution parall�le.
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
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le serveur
	 * listener sera alert� lors de l'appel de "firePropertyChanger"
	 * @param listener un actionlistener
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * D�marre la communication avec le serveur
	 */
	public void start(){
		// Lance la connexion avec le serveur
				ConnexionServeur.connexionServeur(DecortiqueurTexte.getNomServeur(), DecortiqueurTexte.getPortServeur());
				if (ConnexionServeur.getRetry() == 0) {
					creerCommunication();
				}
	}
	
	/**
	 * Arr�te la communication avec le serveur
	 */
	public void stop(){
				
		if(isActif)
			ConnexionServeur.deconnexion(); //Arrete la communication avec le serveur
		
		if(threadComm!=null)
			threadComm.cancel(true); 
		
		isActif = false;
	}
	
	/**
	 * Cr�er le n�cessaire pour la communication avec le serveur
	 */
	@SuppressWarnings("rawtypes")
	protected void creerCommunication(){		
		// Cr�e un fil d'ex�cusion parall�le au fil courant,
				
		threadComm = new SwingWorker(){
			
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("Le fils d'ex�cution parall�le est lanc� !");
				
				// Boucle qui recevra les 10 formes et les stockera
				int index = 0 ;
				while(true && index < 10){
					
					Thread.sleep(DELAI);					
						String forme = ConnexionServeur.getForme(); //R�cup�re les formes
						Decoder ReponseDuServeur = new Decoder(forme);
						Forme formeADessiner = CreateurFormes.creerForme(ReponseDuServeur); //Cr�er une nouvelle forme
					
						//La m�thode suivante alerte l'observateur 
						if(listener!=null)
						{
							System.out.println(ReponseDuServeur.getTypeForme());
							firePropertyChange("ENVOIE-TEST", null, formeADessiner); //Donne la forme à l'observateur
						}
						index++;		
				}
				stop();	
				return index;
				
				}
			};
		if(listener!=null)
	    threadComm.addPropertyChangeListener(listener); // La m�thode "propertyChange" de ApplicationFormes sera donc appel�e lorsque le SwinkWorker invoquera la m�thode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'ex�cution parall�le.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'ex�cution parall�le est actif
	 */
	public static boolean isActif(){
		return isActif;
	}
	
}
