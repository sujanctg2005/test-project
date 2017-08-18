package com.test.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LamdaTest {
	/**
	 * @param args
	 */
	public static void main(String... args) {

		LamdaTest lamdaTest = new LamdaTest();
		// /* Basic lamda example */
		// lamdaTest.basicLamda();

		// lamdaTest.lamdaFunctionReference();
		 lamdaTest.useJDKlamdaFunctionReference();

		
	}

	/* lamdaFunctionReference example 1 */
	public void lamdaFunctionReference() {
		List<Integer> list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);
		Numbers number = new Numbers();
		System.out.println("Without lamda expression");
		number.findNumbers(list, new BiPredicate<Integer, Integer>() {

			@Override
			public boolean test(Integer n1, Integer n2) {

				return Numbers.isMoreThanFifty(n1, n2);
			}
		}).stream().forEach(System.out::println);

		System.out.println("With lamda expression\n");

		number.findNumbers(list, (i1, i2) -> Numbers.isMoreThanFifty(i1, i2)).stream().forEach(System.out::println);

		System.out.println("With  static function  reference\n");
		number.findNumbers(list, Numbers::isMoreThanFifty).stream().forEach(System.out::println);
		;
	}

	/* End */

	/* lamdaFunctionReference example 1 */
	/*
	 * JDK provider Function , BiFunction and TriFunction some defaults
	 * functional interface
	 * 
	 */
	public void useJDKlamdaFunctionReference() {
		List<Shipment> list = Arrays.asList(new Shipment(10), new Shipment(20));

		/* without lamda expression */
		List<Double> shipmentWightList = Shipment.calculateOnShipments(list, new Function<Shipment, Double>() {
			public Double apply(Shipment s) { // The object
				return s.calculateWeight(); // The method
			}
		});
		System.out.println("without lamda expression");
		shipmentWightList.stream().forEach(System.out::println);
		System.out.println("with lamda expression");
		Shipment.calculateOnShipments(list, (s) -> s.calculateWeight()).stream().forEach(System.out::println);
		System.out.println("with lamda object function reference  expression");
		Shipment.calculateOnShipments(list, Shipment::calculateWeight).stream().forEach(System.out::println);
		//////////////////////////////////////////////////////////////////

		System.out.println("without lamda expression");
		BiFunction<String, String, Locale> f = new BiFunction<String, String, Locale>() {

			@Override
			public Locale apply(String lang, String country) {
				// TODO Auto-generated method stub
				return new Locale(lang, country);
			}

		};
		Locale locale = f.apply("en", "UK");

		System.out.println("with lamda expression");
		BiFunction<String, String, Locale> f1 = (lang, country) -> new Locale(lang, country);
		locale = f1.apply("en", "UK");

		BiFunction<String, String, Locale> f2 = Locale::new;
		locale = f2.apply("en", "UK");

	}

	/* End */

	public void basicLamda() {
		MathOperation add = (a, b) -> a + b;
		MathOperation subtract = (a, b) -> a - b;
		MathOperation multiplication = (a, b) -> a * b;
		MathOperation division = (a, b) -> (float) a / b;

		System.out.println("Addition: " + (int) add.operation(100, 300));
		System.out.println("subtract: " + subtract.operation(2, 3));
		System.out.println("multiplication: " + multiplication.operation(2, 3));
		System.out.println("division: " + division.operation(2, 3));

		
		
		Greeting helloGreeting = () -> "Hello Sujan";
		sayHello(helloGreeting);
		sayHello(() -> "sujan");
		sayHello(new ComplexFunctionalInterface() {

			@Override
			public String sayHello() {
				return "complex hello" + this.a;
			}
		});
		sayHello(new Greeting() {

			@Override
			public String sayHello() {
				// TODO Auto-generated method stub
				return "sujan nath";
			}

		});

		List<Person> persions = new ArrayList<>();
		persions.add(new Person("sujan", "ctg"));
		persions.add(new Person("nitun", "feni"));
		persions.add(new Person("amal", "ctg"));
		// Lambda expression with type information removed.
		Collections.sort(persions, (p1, p2) -> p1.getName().compareTo(p2.getName()));
		persions.forEach(p -> System.out.println(p.getName()));
	}

	public static void sayHello(Greeting greeting) {
		System.out.println("Hi: " + greeting.sayHello());
	}
}
