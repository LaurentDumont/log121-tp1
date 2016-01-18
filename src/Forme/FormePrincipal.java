/******************************************************
Cours:   LOG121
Session: H2013
Groupe:  
Projet: Laboratoire #1
Étudiant(e)s: Max St-Onge
              
              
Professeur : Francis Cardinal
Nom du fichier: ConnectionServeur.java
Date créé: 2014-01-15
Date dern. modif. 2013-02-04
*******************************************************
Historique des modifications
*******************************************************
2014-01-30 Version initiale
*******************************************************/  
package Forme;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * Classe abstraite qui regroupera les Formes et pourra les gérer.
 * 
 * @author stongema
 *
 */
public abstract class FormePrincipal {
	
	public FormePrincipal forme;
	
	protected int numeroSequence; //Numero qui identifie la forme
	
	protected String nomDeForme; //Nom de la forme
	
	protected Color couleurForme; //Couleur de la forme
	
	public void dessinerForme(Graphics g) {}
	

}
