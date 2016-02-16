
/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : FenetrePrincipale.java
Date créé : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/
package main_package;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import Forme.CreateurFormes;
import Forme.Forme;

/**
 * Cette classe représente la fenêtre principale de l'application
 * 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = -1210804336046370508L;
	private CreateurFormes cf;
	private FenetreFormes ff;
	private String linereceived;
	private CommBase commBase;
	private ConnexionServeur connexionserveur;

	/**
	 * Constructeur
	 */
	public FenetrePrincipale(CommBase comm) {

		JMenuBar menu = new MenuFenetre(comm);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH);
		// Ajoute le listener
		((MenuFenetre)menu).setPropertyChangeListener(this);
		ff = new FenetreFormes();
		this.add(ff, BorderLayout.CENTER); // Ajoute la fenêtre de
											// forme à la fenètre
											// principale
		this.pack(); // Ajuste la dimension de la fenêtre principale selon
						// celle de ses composants
		this.setVisible(true); // Rend la fenêtre principale visible.
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		/*
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if(commBase.isActif())
					connexionserveur.deconnexionServeur();
				System.exit(0);
			}
		});
		*/
		}
		 
	

	// Appelé lorsque le sujet lance "firePropertyChanger"
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("ENVOIE-TEST")) {
			ff.addForme((Forme) arg0.getNewValue());
		}
		// Si l'argument recu est d'un bouton
		if (arg0.getPropertyName().equals("button")) {
			ff.Trier(arg0.getNewValue().toString());
		}
	}
}
