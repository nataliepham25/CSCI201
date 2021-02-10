package nqpham_CSCI201_Assignment1;

import java.util.Comparator;


/*
 * Natalie Pham
 * CSCI 201
 * Professor Papa
 * sorting the stocks from A to Z
 */

public class sortAtoZ implements Comparator<CompanyInfo>{

	@Override
	public int compare(CompanyInfo o1, CompanyInfo o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}

}
