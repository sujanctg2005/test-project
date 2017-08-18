package com.test.lamda;

import java.util.ArrayList;
import java.util.List;

class Numbers {
	public static boolean isMoreThanFifty(int n1, int n2) {		
		return (n1 + n2) > 50;
	}

	public  List<Integer> findNumbers(List<Integer> l, BiPredicate<Integer, Integer> p) {
		List<Integer> newList = new ArrayList<>();
		for (Integer i : l) {
			if (p.test(i, i + 10)) {
				newList.add(i);
			}
		}
		return newList;
	}
}