package com.test.lamda;

@FunctionalInterface
public interface ComplexFunctionalInterface extends Greeting {
  default public void doSomeWork(){
    System.out.println("Doing some work in interface impl...");
  }
  default public void doSomeOtherWork(){
    System.out.println("Doing some other work in interface impl...");
  }
}