package main_package;

import Forme.Forme;

public class Noeud {
	private Forme valeur;
	private Noeud next;
	private Noeud prev;
	
	public Noeud(Forme forme){
		setValeur(forme);
		setNext(null);
		setPrev(null);
	}
	
	public Noeud(Forme forme, Noeud noeud){
		setValeur(forme);
		setNext(noeud.getNext());
		setPrev(noeud.getPrev());
		
	}

	public Forme getValeur() {
		return valeur;
	}

	public void setValeur(Forme valeur) {
		this.valeur = valeur;
	}

	public Noeud getPrev() {
		return prev;
	}

	public void setPrev(Noeud prev) {
		this.prev = prev;
	}

	public Noeud getNext() {
		return next;
	}

	public void setNext(Noeud next) {
		this.next = next;
	}
	
}
