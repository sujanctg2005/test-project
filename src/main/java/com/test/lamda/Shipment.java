package com.test.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Shipment {
	double weight = 0;
	public Shipment(){}
	public Shipment(double weight){
		this.weight=weight;
	}
	public double calculateWeight() {
		
		// Calculate weight
		return weight;
	}

	public static List<Double> calculateOnShipments(List<Shipment> l, Function<Shipment, Double> f) {
		List<Double> results = new ArrayList<>();
		for (Shipment s : l) {
			results.add(f.apply(s));
		}
		return results;
	}
}
