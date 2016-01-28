package main_package;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/

import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import Forme.CreateurFormes;
import Forme.Forme;

/**
 * Base d'une communication via un fil d'exécution parallèle.
 */
public class CommBase {

	private String stringServeur = null;
	private String nomServeur = null;
	private int portServeur = 0;
	private final int DELAI = 1000;
	private SwingWorker threadComm = null;
	private PropertyChangeListener listener = null;
	private boolean isActif = false;
	private CreateurFormes createurForme;

	/**
	 * Constructeur
	 */
	public CommBase() {
		createurForme = new CreateurFormes();
	}

	/**
	 * Définir le récepteur de l'information reçue dans la communication avec le
	 * serveur
	 * 
	 * @param listener
	 *            sera alerté lors de l'appel de "firePropertyChanger" par le
	 *            SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * Démarre la communication
	 */
	public void start() {
		DecortiqueurTexte.decortiqeur();
		ConnexionServeur.connexionServeur(DecortiqueurTexte.getNomServeur(),DecortiqueurTexte.getPortServeur()); // Lance la connexion avec le serveur
		creerCommunication();
	}

	/**
	 * ArrÃªte la communication
	 */
	public void stop() {
		if (threadComm != null)
			threadComm.cancel(true);
		isActif = false;
	}

	/**
	 * Créer le nécessaire pour la communication avec le serveur
	 */
	protected void creerCommunication() {
		// Crée un fil d'exécusion parallèle au fil courant,
		threadComm = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("Le fils d'execution parallele est lance");
				while (true) {
					Thread.sleep(DELAI);
					String forme = ConnexionServeur.getForme();
					Forme formeADessiner = createurForme.creerForme(forme);

					// La méthode suivante alerte l'observateur
					if (listener != null)
						firePropertyChange("ENVOIE-TEST", null, formeADessiner);
				}
				// return null;
			}
		};
		if (listener != null)
			threadComm.addPropertyChangeListener(listener); 
		/**
		 La méthode "propertyChange" de ApplicationFormes sera donc appelée lorsque
		 le SwingWorker invoquera la méthode "firePropertyChanger"
		 */
		threadComm.execute(); // Lance le fil d'exécution parallèle.
		isActif = true;
	}

	/**
	 * @return si le fil d'exécution parallèle est actif
	 */
	public boolean isActif() {
		return isActif;
	}

}
