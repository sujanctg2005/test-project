package com.test.utility.benchmarks;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Start {
	public static void main(String... args) {

		Options opt = new OptionsBuilder().include(HelloWorld.class.getSimpleName()).forks(1).build();

		try {
			new Runner(opt).run();
		} catch (RunnerException e) {

			e.printStackTrace();
		}
	}


 
}