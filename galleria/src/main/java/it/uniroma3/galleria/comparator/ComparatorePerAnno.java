package it.uniroma3.galleria.comparator;

import java.util.Comparator;

import it.uniroma3.galleria.model.Opera;

public class ComparatorePerAnno implements Comparator<Opera> {

	@Override
	public int compare(Opera o1, Opera o2) {
		return o1.getAnno().compareTo(o2.getAnno());
	}

}
