/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : CreateurForme.java
Date créé : 2016-01-12
Date dern. modif. 2016-01-19
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/

package Forme;

import ca.etsmtl.log.util.IDLogger;
import main_package.Decoder;

public class CreateurFormes {

	public Forme creerForme(String chaineForme) {
		Decoder code = new Decoder(chaineForme);
		IDLogger logger = IDLogger.getInstance();
		logger.logID(code.getID());
		if (code.getForme().equals("LIGNE")) {
			return new Line(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("CARRE")) {
			return new Square(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("RECTANGLE")) {
			return new Rectangle(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("OVALE")) {
			return new Oval(code.getData1(), code.getData2(), code.getData3(), code.getData4());
		}

		else if (code.getForme().equals("CERCLE")) {
			return new Circle(code.getData1(), code.getData2(), code.getData3());
		} else
			System.out.println(code.getForme() + "est une forme invalide");
			return null;
	}
}
