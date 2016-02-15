/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : Decoder.java
Date créé : 2016-01-12
Date dern. modif. 2016-01-19
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/

package main_package;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {
	private int id;
	private String forme;
	private String coordonne;
	
	/**
	 * Constructeur de la reponse du serveur
	 * @param Balise Reponse non traiter provenant du serveur
	 */
	public Decoder(String Balise)
	{
		//Definition de la facon dont la balise est constituer afin de sortir les informations
		Pattern p = Pattern.compile("(.*) <(.*)> (.*) </\\2>");
		Matcher m = p.matcher(Balise);
		
		//Si le pattern correspont avec la balise, Les valeurs vont etre rajoute au variable de la classe
		if(m.find())
		{
		id = Integer.parseInt(m.group(1));
		forme = m.group(2);
		coordonne = m.group(3);
		}
		
		
		
	}
	
	/**
	 * Accesseur du numero de sequence
	 * @return Retourne le numero de sequence en int
	 */
	public int getID()
	{
		return id;
	}
	/**
	 * Accesseur du type de forme
	 * @return Retourne le type de forme en string
	 */
	public String getTypeForme()
	{
		return forme;
	}
	/**
	 * Accesseur des coordonnes
	 * @return Retourne une string contenant les coordonnees, il ne s'agit pas encore d'un tableau
	 */
	public String getCoordonne()
	{
		return coordonne;
	}
	
	
	

}
