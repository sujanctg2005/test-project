package com.test.lamda.optional;


import java.util.Optional;

public class MobileTesterWithOptional {

  public static void main(String[] args) {
	ScreenResolution resolution = new ScreenResolution(750,1334);
	DisplayFeatures dfeatures = new DisplayFeatures("4.7", Optional.of(resolution));
	Mobile mobile = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));

	MobileService mService = new MobileService();

	int width = mService.getMobileScreenWidth(Optional.of(mobile));
	//System.out.println("Apple iPhone 6s Screen Width = " + width);

	Mobile mobile2 = new Mobile(2015001, "Apple", "iPhone 6s", Optional.empty());
	int width2 = mService.getMobileScreenWidth(Optional.of(mobile2));
	System.out.println("Apple iPhone 16s Screen Width = " + width2);
	
	
	Mobile mobile3 = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));
	int height = mService.getMobileScreenWidth(Optional.of(mobile3));
	System.out.println("Apple iPhone 16s Screen height = " + height);
	
	//---------------------------- Example 2------------------
	
	Optional<String> nonEmptyGender = Optional.of("male");
    Optional<String> emptyGender = Optional.empty();

    System.out.println("Non-Empty Optional:: " + nonEmptyGender.map(String::toUpperCase));
    System.out.println("Empty Optional    :: " + emptyGender.map(String::toUpperCase));

    Optional<Optional<String>> nonEmptyOtionalGender = Optional.of(Optional.of("male"));
    System.out.println("Optional value   :: " + nonEmptyOtionalGender);
    System.out.println("Optional.map     :: " + nonEmptyOtionalGender.map(gender -> gender.map(String::toUpperCase)));
    System.out.println("Optional.flatMap :: " + nonEmptyOtionalGender.flatMap(gender -> gender.map(String::toUpperCase)));

	//------------------- Example 3 --------------
    Optional<String> gender = Optional.of("MALE");
    Optional<String> emptyGender1 = Optional.empty();

    //Filter on Optional
    System.out.println(gender.filter(g -> g.equals("male")).isPresent()); //Optional.empty
    System.out.println(gender.filter(g -> g.equalsIgnoreCase("MALE"))); //Optional[MALE]
    System.out.println(emptyGender1.filter(g -> g.equalsIgnoreCase("MALE"))); //Optional.empty

	
  }
}
