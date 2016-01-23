package main_package;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetrePrincipale.java
Date cr��: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import Forme.CreateurFormes;
import Forme.Forme;

/**
 * Cette classe repr�sente la fen�tre principale de l'application
 * 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = -1210804336046370508L;
	private CreateurFormes cf;
	private FenetreFormes ff;
	private String linereceived;

	/**
	 * Constructeur
	 */
	public FenetrePrincipale(CommBase comm) {

		JMenuBar menu = new MenuFenetre(comm);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH);
		ff = new FenetreFormes();
		this.add(ff, BorderLayout.CENTER); // Ajoute la fen�tre de
											// forme � la fen�tre
											// principale
		this.pack(); // Ajuste la dimension de la fen�tre principale selon
						// celle de ses composants
		this.setVisible(true); // Rend la fen�tre principale visible.
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	// Appel� lorsque le sujet lance "firePropertyChanger"
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("ENVOIE-TEST") && arg0.getNewValue() instanceof Forme) {
			Forme forme = (Forme) arg0.getNewValue();
			ff.addForme(forme);
		}
	}
}
