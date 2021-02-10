package nqpham_CSCI201_Assignment1;

import java.util.Comparator;

/*
 * Natalie Pham
 * CSCI 201
 * Professor Papa
 * Sorting the stocks from Z to A
 */

public class sortZtoA implements Comparator<CompanyInfo>{

	@Override
	public int compare(CompanyInfo o1, CompanyInfo o2) {
		// TODO Auto-generated method stub
		return o2.getName().compareTo(o1.getName());
	}

}
