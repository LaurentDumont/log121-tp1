/******************************************************
Cours : LOG121
Session : H2016
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : Laurent Dumont, Bach Nguyen Ngoc
Code(s) perm. : DUML04059004, NGUB08049302	
Professeur : Dominic St-Jacques
Chargé de labo : Simon Robert
Nom du fichier : TriSelonMenu.java
Date créé : 2016-01-12
Date dern. modif. 2016-02-15
*******************************************************
Historique des modifications
*******************************************************
2016-01-12 Version initiale
*******************************************************/

package main_package;

import java.lang.reflect.Method;

import Forme.Forme;

public class TriSelonMenu {
	private static final String
	
	MENU_ORDRE_NUMSEQCROIS = "app.frame.menus.order.numseqcrois",
	MENU_ORDRE_NUMSEQDECROIS = "app.frame.menus.order.numseqdecrois",
	MENU_ORDRE_AIRECROIS = "app.frame.menus.order.airecrois",
	MENU_ORDRE_AIREDECROIS = "app.frame.menus.order.airedecrois",
	MENU_ORDRE_TYPEFORME = "app.frame.menus.order.typeforme",
	MENU_ORDRE_TYPEFORMEINVERSE = "app.frame.menus.order.typeformeinverse",
	MENU_ORDRE_DISTANCEFORME = "app.frame.menus.order.distance";

	


private static ListeChainee listeDeFormeTriee = new ListeChainee();

/**
* Methode qui Trie une liste rentrer en paramÃ¨tre selon le menu qui a ete selectionne
* @param MenuSelectionne String du menu selectionne
* @param listeRecu Liste de forme recu par le serveur
* @return Une liste de formes et d'encadrer trie et pres a etre affiche
* @throws Exception Exception concernant java.lang.reflect
*/
public static ListeChainee selection(String MenuSelectionne, ListeChainee listeRecu)throws Exception{

//Cree une nouvelle liste et copie les formes de la liste recu par le serveur
//Afin de ne pas modifier directement la liste
ListeChainee listeDeFormeRecu = new ListeChainee();
listeDeFormeRecu = CopierListe(listeDeFormeRecu, listeRecu);

//Comparaison pour savoir laquelle des methodes de trie il faut executer selon le menu selectionne
if (LangueConfig.getResource(MENU_ORDRE_NUMSEQCROIS).equals(MenuSelectionne)) {
	listeDeFormeTriee = numSeqCroissant(listeDeFormeRecu);
} else if (LangueConfig.getResource(MENU_ORDRE_NUMSEQDECROIS).equals(MenuSelectionne)) {
	listeDeFormeTriee = numSeqDecroissant(listeDeFormeRecu);
} else if (LangueConfig.getResource(MENU_ORDRE_AIRECROIS).equals(MenuSelectionne)) {
	listeDeFormeTriee = AireCroissant(listeDeFormeRecu);
} else if (LangueConfig.getResource(MENU_ORDRE_AIREDECROIS).equals(MenuSelectionne)) {
	listeDeFormeTriee = AireDecroissant(listeDeFormeRecu);
} else if (LangueConfig.getResource(MENU_ORDRE_TYPEFORME).equals(MenuSelectionne)) {
	listeDeFormeTriee = TypeDeForme(listeDeFormeRecu);
} else if (LangueConfig.getResource(MENU_ORDRE_TYPEFORMEINVERSE).equals(MenuSelectionne)) {
	listeDeFormeTriee = TypeDeFormeInverse(listeDeFormeRecu);
} else if (LangueConfig.getResource(MENU_ORDRE_DISTANCEFORME).equals(MenuSelectionne)) {
	listeDeFormeTriee = DistanceFormeDiagonale(listeDeFormeRecu);
} else {
}
//Retourne la liste prete a l'affichage
return listeDeFormeTriee ;
}
/**
* Methode pour trier les formes selon le numero de sequence croissant
* @param listATriee Copie de la liste recu par le serveur
* @return Liste trier selon numero de sequence croissant
* @throws Exception java.lang.reflect
*/
private static ListeChainee numSeqCroissant(ListeChainee listATriee)throws Exception{
return listATriee = TrierSelonChoix(listATriee, "getNumSeq",true);
}
/**
* Methode pour trier les formes selon le numero de sequence decroissant
* @param listATriee Copie de la liste recu par le serveur
* @return Liste trier selon numero de sequence decroissant
* @throws Exception java.lang.reflect
*/
private static ListeChainee numSeqDecroissant(ListeChainee listATriee)throws Exception{
return listATriee = TrierSelonChoix(listATriee, "getNumSeq",false);
}
/**
* Methode pour trier les formes selon l'aire croissant
* @param listATriee Copie de la liste recu par le serveur
* @return Liste trier selon l'aire croissant
* @throws Exception java.lang.reflect
*/
private static ListeChainee AireCroissant(ListeChainee listATriee)throws Exception{
return listATriee = TrierSelonChoix(listATriee, "calculeAire",true);
}
/**
* Methode pour trier les formes selon l'aire decroissant
* @param listATriee Copie de la liste recu par le serveur
* @return Liste trier selon l'aire decroissant
* @throws Exception java.lang.reflect
*/
private static ListeChainee AireDecroissant(ListeChainee listATriee)throws Exception{
return listATriee = TrierSelonChoix(listATriee, "calculeAire", false);
}
/**
* Methode pour trier les formes selon le type de forme
* @param listATriee Copie de la liste recu par le serveur
* @return Liste trier selon le type de forme
* @throws Exception java.lang.reflect
*/
private static ListeChainee TypeDeForme(ListeChainee listATriee) throws Exception{
return listATriee = TrierSelonChoix(listATriee, "getTypeForme", true); 
}
/**
* Methode pour trier les formes selon le type de forme inverse
* @param listATriee Copie de la liste recu par le serveur
* @return Liste trier selon le type de forme inverse
* @throws Exception java.lang.reflect
*/
private static ListeChainee TypeDeFormeInverse(ListeChainee listATriee) throws Exception{
return listATriee = TrierSelonChoix(listATriee, "getTypeForme", false);
}
/**
* Methode pour trier les formes selon la longueur de la plus longue diagonale croissant
* @param listATriee Copie de la liste recu par le serveur
* @return Liste trier selon la longueur de la plus longue diagonale
* @throws Exception java.lang.reflect
*/
private static ListeChainee DistanceFormeDiagonale(ListeChainee listATriee)throws Exception{
return listATriee = TrierSelonChoix(listATriee, "getDiagonale", true);
}
/**
* Methode bien positionner les formes dans le Graphics. Elle ne trie pas la liste, elle creer les
* encadrer et positionne le tout
* @param listATriee Liste deja trier selon une des methodes de trie
* @return La liste prete a affichage
*/
private static ListeChainee MettreToutBeau(ListeChainee listATriee){
int nbElement = listATriee.getNbELement();
listATriee.PositionDebut();
for (int i = 0; i < nbElement; i++) {
	int temp = i * 40;
	((Forme) (listATriee.defilePositionCourant())).setPosition(temp,temp);
	listATriee.enfile(((Forme) (listATriee.defilePositionCourant())).getEncadree());
	listATriee.suivant();
}
return listATriee;

}

/**
* Methode pour copier les informations d'une liste dans une nouvelle
* @param ListeARemplir La nouvelle liste a remplir
* @param ListeACopier La liste contenant les informations que nous avons besoin
* @return Une liste contenant les informations de l'ancienne liste
*/
private static ListeChainee CopierListe(ListeChainee ListeARemplir, ListeChainee ListeACopier){
ListeARemplir = new ListeChainee();
int index = 0;
while(index < ListeACopier.getNbELement())
{
	ListeARemplir.enfile(ListeACopier.defilePositionCourant());
	ListeACopier.suivant();
	index++;
}
return ListeARemplir;
}
/**
* Methode qui va trier une liste recue en parametre selon different parametre
* @param listATriee La liste devant etre trier
* @param ChoixMethode La methode des formes que nous voulons comparer
* @param isCroissant Indique si le trie se fait croissant ou decroissant
* @return Une liste prete pour l'affichage
* @throws Exception Java.lang.reflect
*/
private static ListeChainee TrierSelonChoix(ListeChainee listATriee, String ChoixMethode, boolean isCroissant)throws Exception{
ListeChainee listtrier = new ListeChainee();
Forme formeATrie;
Forme formeAVerifier;
double ValeurATrie;
double ValeurAVerifier;






while(listATriee.nbElement!=0){
	formeATrie = (Forme) listATriee.defile();
	
	if(listtrier.getNbELement()==0)
		listtrier.enfile(formeATrie);
	else
	{
	
		if(isCroissant)
		listtrier.PositionDebut();
		else
			listtrier.PositionFin();
		
		formeAVerifier = (Forme)listtrier.defilePositionCourant();
		
		Method methodeATrie = formeATrie.getClass().getDeclaredMethod(ChoixMethode);
		Method methodeAVerifier = formeAVerifier.getClass().getDeclaredMethod(ChoixMethode);
		try{
			//S'il n'y a pas d'erreur, il s'agit d'un double sinon un int qui doit etre caster en double
			ValeurATrie = (double) methodeATrie.invoke(formeATrie);
			ValeurAVerifier = (double) methodeAVerifier.invoke(formeAVerifier);
		}
		catch(ClassCastException e)
		{
			ValeurATrie = (double) (int)methodeATrie.invoke(formeATrie);
			ValeurAVerifier = (double) (int)methodeAVerifier.invoke(formeAVerifier);
		}
		

		while(ValeurATrie > ValeurAVerifier)
		{
			
			if(isCroissant)
			{
				if(listtrier.getSuivantnull())
				{
					listtrier.insererApres(formeATrie);
					break;
				}
				listtrier.suivant();
			}
			else
			{
				if(listtrier.getPrecedentnull())
				{
					listtrier.insererAvant(formeATrie);
					break;
				}
				listtrier.precedent();
			}
				
					
				formeAVerifier = (Forme)listtrier.defilePositionCourant();
				methodeAVerifier = formeAVerifier.getClass().getDeclaredMethod(ChoixMethode);
				try
				{
					ValeurAVerifier = (double) methodeAVerifier.invoke(formeAVerifier);
				}
				catch(ClassCastException e)
				{
					ValeurAVerifier = (double) (int)methodeAVerifier.invoke(formeAVerifier);
				}
				
				
			
		}
		if(ValeurATrie < ValeurAVerifier)
		{
			if(isCroissant)
				listtrier.insererAvant(formeATrie);
			else
				listtrier.insererApres(formeATrie);
				
		}
	}
	
	
}
listtrier = MettreToutBeau(listtrier);
return listtrier;
}



}
