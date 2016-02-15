package main_package;
/******************************************************
Cours:  LOG121
Session: E2015
Projet: Squelette du laboratoire #2
Étudiant(e)s: Julien Lemonde, Alexandre Malo, Marc-Antoine Hebert, Jean-Michel Coupal

Professeur : Francis Cardinal
Nom du fichier: CommBase.java
Date cr��: 2015-05-03
*******************************************************
Description de la classe
Base d'une communication via un fil d'ex�cution parall�le.
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
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le serveur
	 * @param listener sera alert� lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * D�marre la communication avec le serveur
	 */
	public void start(){
		input = JOptionPane.showInputDialog("Quel est le nom d'h�te et le port du serveur de formes?","localhost:10000");
		
		String [] infoServeur = input.split(":"); //S�pare la saisie de l'utilisateur afin de r�cup�rer le nom d'hôte et le port
		hostname = infoServeur[0]; //Assigne le hostname
		port = Integer.parseInt(infoServeur[1]); //Assigne le port
		ConnexionServeur.connexion(hostname,port); //Lance la connexion avec le serveur
		isActif = true;
		creerCommunication();
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
				
				// C'EST DANS CETTE BOUCLE QU'ON COMMUNIQUE AVEC LE SERVEUR
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
