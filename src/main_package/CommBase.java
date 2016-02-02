package main_package;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date cr��: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/

import java.beans.PropertyChangeListener;

import javax.swing.SwingWorker;

import Forme.CreateurFormes;
import Forme.Forme;

/**
 * Base d'une communication via un fil d'ex�cution parall�le.
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
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le
	 * serveur
	 * 
	 * @param listener
	 *            sera alert� lors de l'appel de "firePropertyChanger" par le
	 *            SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * D�marre la communication
	 */
	public void start() {
		// Lance la connexion avec le serveur
		ConnexionServeur.connexionServeur(DecortiqueurTexte.getNomServeur(), DecortiqueurTexte.getPortServeur());
		if (ConnexionServeur.getRetry() == 0) {
			creerCommunication();
		}
	}

	private enum Status {
		RUNNABLE, NEW;
	}

	/**
	 * Arr�te la communication
	 */
	public void stop() {
		if (threadComm != null && threadComm.getState().equals(Status.RUNNABLE))
			threadComm.cancel(true);
		isActif = false;
		ConnexionServeur.deconnexionServeur();

	}

	/**
	 * Cr�er le n�cessaire pour la communication avec le serveur
	 */
	protected void creerCommunication() {
		// Cr�e un fil d'ex�cusion parall�le au fil courant,
		threadComm = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("Le fils d'execution parallele est lance");
				while (true) {
					Thread.sleep(DELAI);
					String forme = ConnexionServeur.getForme();
					Forme formeADessiner = createurForme.creerForme(forme);

					// La m�thode suivante alerte l'observateur
					if (listener != null)
						firePropertyChange("ENVOIE-TEST", null, formeADessiner);
				}
				// return null;
			}
		};
		if (listener != null)
			threadComm.addPropertyChangeListener(listener);
		/**
		 * La m�thode "propertyChange" de ApplicationFormes sera donc appel�e
		 * lorsque le SwingWorker invoquera la m�thode "firePropertyChanger"
		 */
		threadComm.execute(); // Lance le fil d'ex�cution parall�le.
		isActif = true;
	}

	/**
	 * @return si le fil d'ex�cution parall�le est actif
	 */
	public boolean isActif() {
		return isActif;
	}

}
