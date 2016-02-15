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
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

/**
 * Créer le menu de la fenêtre de l'application
 */
public class MenuFenetre extends JMenuBar {

	private static final long serialVersionUID = 1536336192561843187L;
	private static final int MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final int  MENU_FICHIER_OBTENIR_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_OBTENIR_TOUCHE_RACC = KeyEvent.VK_W;
	
	private static final String 
	
	
			MENU_AIDE_TITRE = "app.frame.menus.help.title", 
			MENU_AIDE_PROPOS = "app.frame.menus.help.about",
			
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_FICHIER_OBTENIR = "app.frame.menus.file.obtain",
			MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
			
			MENU_ORDRE_TITRE = "app.frame.menus.order.title",
			MENU_ORDRE_NUMSEQCROIS = "app.frame.menus.order.numseqcrois",
			MENU_ORDRE_NUMSEQDECROIS = "app.frame.menus.order.numseqdecrois",
			MENU_ORDRE_AIRECROIS = "app.frame.menus.order.airecrois",
			MENU_ORDRE_AIREDECROIS = "app.frame.menus.order.airedecrois",
			MENU_ORDRE_TYPEFORME = "app.frame.menus.order.typeforme",
			MENU_ORDRE_TYPEFORMEINVERSE = "app.frame.menus.order.typeformeinverse",
			MENU_ORDRE_DISTANCEFORME = "app.frame.menus.order.distance";

	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";
	
	private JMenuItem arreterMenuItem, demarrerMenuItem, obtenirFormeMenu;
	private static final int DELAI_QUITTER_MSEC = 200;
	private JMenuItem obtenirMenuItem;
	private static JRadioButtonMenuItem numSeqCroisMenuRadioItem, numSeqDecroisMenuRadioItem, aireCroisMenuRadioItem, aireDecroisMenuRadioItem, typeFormeMenuRadioItem, typeFormeInverseMenuRadioItem, distanceFormeMenuRadioItem;
	private String MenuSelectionner;
	private PropertyChangeListener listener = null;
	@SuppressWarnings("rawtypes")
	private SwingWorker threadComm = null;
	CommBase comm; // Pour activer/désactiver la communication avec le serveur

	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuFichier();
		addMenuOrdre();
		addMenuAide();

	}
	
	public String getMenuSelected()
	{
		return MenuSelectionner;
	}
	
	public void setPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	protected void addMenuOrdre() {
			JMenu menu = creerMenuRadio(MENU_ORDRE_TITRE, new String[] {MENU_ORDRE_NUMSEQCROIS, MENU_ORDRE_NUMSEQDECROIS, MENU_ORDRE_AIRECROIS, MENU_ORDRE_AIREDECROIS, MENU_ORDRE_TYPEFORME, MENU_ORDRE_TYPEFORMEINVERSE, MENU_ORDRE_DISTANCEFORME});
		
		ActionListener radioActionListener = new ActionListener() {
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
				AbstractButton absButton = (AbstractButton) e.getSource();
				threadComm = new SwingWorker(){
					
					@Override
					protected Object doInBackground() throws Exception {
						
						if(listener!=null)
							firePropertyChange("button", null, absButton.getText()); //Donne la forme à  l'observateur	
						
						
						return 1;
					}
						
					};
				if(listener!=null)
			    threadComm.addPropertyChangeListener(listener); // La méthode "propertyChange" de ApplicationFormes sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 		
				threadComm.execute(); // Lance le fil d'exécution parallèle.
			}
		};
		
		numSeqCroisMenuRadioItem.addActionListener(radioActionListener);
		numSeqDecroisMenuRadioItem.addActionListener(radioActionListener);
		aireCroisMenuRadioItem.addActionListener(radioActionListener);
		aireDecroisMenuRadioItem.addActionListener(radioActionListener);
		typeFormeMenuRadioItem.addActionListener(radioActionListener);
		typeFormeInverseMenuRadioItem.addActionListener(radioActionListener);
		distanceFormeMenuRadioItem.addActionListener(radioActionListener);
		
		add(menu);
	}

	private static JMenu creerMenuRadio(String titleKey,String[] itemKeys) {
		ButtonGroup group = new ButtonGroup();
        JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
        for(int i=0; i < itemKeys.length; ++i) {
        	JRadioButtonMenuItem button = new JRadioButtonMenuItem(LangueConfig.getResource(itemKeys[i]));
        	group.add(button);
            menu.add(button);
           
            switch (itemKeys[i]) {
			case MENU_ORDRE_NUMSEQCROIS:
				numSeqCroisMenuRadioItem = button;
				break;
			case MENU_ORDRE_NUMSEQDECROIS:
				numSeqDecroisMenuRadioItem = button;
				break;
			case MENU_ORDRE_AIRECROIS:
				aireCroisMenuRadioItem = button;
				break;
			case MENU_ORDRE_AIREDECROIS:
				aireDecroisMenuRadioItem = button;
				break;
			case MENU_ORDRE_TYPEFORME:
				typeFormeMenuRadioItem = button;
				break;
			case MENU_ORDRE_TYPEFORMEINVERSE:
				typeFormeInverseMenuRadioItem = button;
				break;
			case MENU_ORDRE_DISTANCEFORME:
				distanceFormeMenuRadioItem = button;
				break;
			default:
				break;
			}

        }
        return menu;
   }
	
	
	/**
	 * Créer le menu "Draw".
	 */
	/*
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
*/
	/**
	 * Créer le menu "File".
	 */
	protected void addMenuFichier() {
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_OBTENIR, MENU_FICHIER_QUITTER });
		menu.getItem(1).addActionListener(new ActionListener() {
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
		
		obtenirMenuItem = menu.getItem(0);
		obtenirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comm.start();
				
			
			}
		});
		
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
						MENU_FICHIER_QUITTER_TOUCHE_MASK));
		menu.getItem(1).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_OBTENIR_TOUCHE_RACC,
						MENU_FICHIER_OBTENIR_TOUCHE_MASK));
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
	/*
	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(!comm.isActif());
		arreterMenuItem.setEnabled(comm.isActif());
	}
*/
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
