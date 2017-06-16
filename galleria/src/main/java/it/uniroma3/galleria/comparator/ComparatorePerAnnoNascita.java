package it.uniroma3.galleria.comparator;

import java.util.Comparator;

import it.uniroma3.galleria.model.Autore;


public class ComparatorePerAnnoNascita implements  Comparator<Autore> {

	@Override
	public int compare(Autore a1, Autore a2) {
		return a1.getAnnoNascita().compareTo(a2.getAnnoNascita());
	}

}
