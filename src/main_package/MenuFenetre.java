package main_package;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * Créer le menu de la fenêtre de l'application
 */
public class MenuFenetre extends JMenuBar {

	private static final long serialVersionUID = 1536336192561843187L;
	private static final int MENU_DESSIN_ARRETER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC = KeyEvent.VK_A;
	private static final int MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	private static final int MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final int MENU_OBTENIR_FORME_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_OBTENIR_FORME_TOUCHE_RACC = KeyEvent.VK_G;
	private static final String 
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_FICHIER_QUITTER = "app.frame.menus.file.exit", 
			MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
			MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start", 
			MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
			MENU_AIDE_TITRE = "app.frame.menus.help.title", 
			MENU_AIDE_PROPOS = "app.frame.menus.help.about",
			MENU_OBTENIRFORME = "app.frame.menus.obtenir";

	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";

	private JMenuItem arreterMenuItem, demarrerMenuItem;
	private static final int DELAI_QUITTER_MSEC = 200;
	CommBase comm; // Pour activer/désactiver la communication avec le serveur
	

	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuDessiner();
		addMenuFichier();
		addMenuObtenirFormes();
		addMenuAide();
	}
	

	/**
	 * Créer le menu "Draw".
	 */
	protected void addMenuDessiner() {
		JMenu menu = creerMenu(MENU_DESSIN_TITRE, new String[] { MENU_DESSIN_DEMARRER, MENU_DESSIN_ARRETER });

		demarrerMenuItem = menu.getItem(0);
		demarrerMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stringServeur;
				do {
					stringServeur = JOptionPane.showInputDialog(
							"Veuillez entrer l'adresse ainsi que le port du serveur", "localhost:10000");
					if (stringServeur == null)
						return;

				} while (!DecortiqueurTexte.decortiqueur(stringServeur));
				comm.start();

				rafraichirMenus();
			}
		});
		demarrerMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(MENU_DESSIN_DEMARRER_TOUCHE_RACC, MENU_DESSIN_DEMARRER_TOUCHE_MASK));

		arreterMenuItem = menu.getItem(1);
		arreterMenuItem.setEnabled(false);
		arreterMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comm.stop();
				ConnexionServeur.deconnexionServeur();
				rafraichirMenus();
			}
		});

		arreterMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(MENU_DESSIN_ARRETER_TOUCHE_RACC, MENU_DESSIN_ARRETER_TOUCHE_MASK));
		add(menu);
	}
	
	/**
	 * Créer le menu "ObtenirFormes".
	 */
	protected void addMenuObtenirFormes() {
		JMenu menu = creerMenu(MENU_OBTENIRFORME, new String[] { MENU_OBTENIRFORME });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Get formes");
			}
		});
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_OBTENIR_FORME_TOUCHE_RACC, MENU_OBTENIR_FORME_TOUCHE_MASK));
		add(menu);
	}
	
	/**
	 * Créer le menu "File".
	 */
	protected void addMenuFichier() {
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_QUITTER });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				comm.stop();
				try {
					Thread.sleep(DELAI_QUITTER_MSEC);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);

			}
		});
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC, MENU_FICHIER_QUITTER_TOUCHE_MASK));
		add(menu);
	}
	
	/**
	 * Créer le menu "Help".
	 */
	private void addMenuAide() {
		JMenu menu = creerMenu(MENU_AIDE_TITRE, new String[] { MENU_AIDE_PROPOS });
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, LangueConfig.getResource(MESSAGE_DIALOGUE_A_PROPOS),
						LangueConfig.getResource(MENU_AIDE_PROPOS), JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(menu);
	}

	/**
	 * Activer ou désactiver les items du menu selon la sélection.
	 */
	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(!comm.isActif());
		arreterMenuItem.setEnabled(comm.isActif());
	}

	/**
	 * Créer un élément de menu à  partir d'un champs principal et ses éléments
	 * 
	 * @param titleKey
	 *            champs principal
	 * @param itemKeys
	 *            éléments
	 * @return le menu
	 */
	private static JMenu creerMenu(String titleKey, String[] itemKeys) {
		JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
		for (int i = 0; i < itemKeys.length; ++i) {
			menu.add(new JMenuItem(LangueConfig.getResource(itemKeys[i])));
		}
		return menu;
	}

}
