/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
�tudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Charg� de labo : Simon Robert
Nom du fichier : CreateurForme.java
Date cr�� : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/

package Forme;


import ca.etsmtl.log.util.IDLogger;
import main_package.Decoder;

public class CreateurFormes {

	/**
	 * Methode static qui va prendre la forme du serveur et en crée une a partir des classes formes
	 * @param reponseServeurTraite Classe contenant la forme traiter par le serveur
	 * @return Forme
	 */
	public static Forme creerForme(Decoder reponseServeurTraite){
		
		//Cree un tableau avec la string de coordonnee recu
		String [] tabForme = reponseServeurTraite.getCoordonne().split(" ");
		
		Forme formeADessiner = null;
		switch (reponseServeurTraite.getTypeForme()) {
		case "RECTANGLE":
			formeADessiner = new Rectangle(reponseServeurTraite, tabForme);
			break;
		case "CARRE":
			formeADessiner= new Square(reponseServeurTraite, tabForme);
			break;
		case "OVALE":
			formeADessiner = new Oval(reponseServeurTraite, tabForme);
			break;
		case "LIGNE":
			formeADessiner = new Line(reponseServeurTraite, tabForme);
		break;
		case "CERCLE":
			formeADessiner = new Circle(reponseServeurTraite, tabForme);
			break;
		
		default:
			break;
		}
		
		return formeADessiner;
		
		}	
	
}
