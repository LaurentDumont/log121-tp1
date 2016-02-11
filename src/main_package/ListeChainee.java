package main_package;

import Forme.Forme;

public class ListeChainee {
	private Noeud current;
	
	public Forme getForme(){
		return current.getValeur();
		
	}
	
	public void next(){
		if (current != null){
			current = current.getPrev();
		}
	}
	
	public void show(){
		if (current == null)
			return;
		Noeud temp = current;
		
		while (temp != null){
			System.out.println(temp.getValeur() + " ");
			temp = temp.getPrev();
		}
	}
	
	public boolean isSingle(){
		if (current != null)
			return current.getPrev() == current;
		else
			return false;
	}
	
	public void add(Forme value){
		Noeud nd = new Noeud(value);
		if (current == null){
			current = nd;
		}
		else {
			nd.setPrev(current);
			current.setNext(nd);
			current = nd;
		}
	}
	
	public void delete(){
		if (current != null){
			Noeud temp = current;
			Noeud temp2 = current;
			while (temp.getNext() != null){
				temp2 = temp;
				temp.setNext(temp);
			}
			temp2.setNext(null);
			temp.setNext(null);
		}
	}
	
	public int nombreElements(){
		int compteur = 0;
		Noeud temp = current;
		
		while (temp != null){
			compteur++;
			temp.setPrev(temp);
		}
		return compteur;
	}
}

