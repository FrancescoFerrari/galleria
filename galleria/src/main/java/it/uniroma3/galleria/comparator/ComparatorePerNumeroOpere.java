package it.uniroma3.galleria.comparator;

import java.util.Comparator;

import it.uniroma3.galleria.model.Stanza;

public class ComparatorePerNumeroOpere implements Comparator<Stanza>{

	@Override
	public int compare(Stanza s1, Stanza s2) {
		return s2.getOpere().size() - s1.getOpere().size();
	}

}
