package main_package;
/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
�tudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Charg� de labo : Simon Robert
Nom du fichier : ApplicationFormes.java
Date cr�� : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/

/**
 * Cette classe repr�sente l'application dans son ensemble. 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class ApplicationFormes{
	
	/**
	 * main de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationFormes application = new ApplicationFormes();
	}
	
	/**
	 * Constructeur
	 */
	public ApplicationFormes(){
		CommBase comm = new CommBase();
		FenetrePrincipale fenetre = new FenetrePrincipale(comm);
		comm.setPropertyChangeListener(fenetre);
	}
}
