package com.test.lamda.optional;

import java.util.Optional;

public class MobileService {

  public Integer getMobileScreenWidth(Optional<Mobile> mobile){
	return mobile.flatMap(Mobile::getDisplayFeatures)
		 .flatMap(DisplayFeatures::getResolution)
		 .map(ScreenResolution::getWidth)
		 .orElse(0);

  }

  
  public Integer getMobileScreenHeight(Optional<Mobile> mobile){
	  
	return mobile.flatMap(Mobile::getDisplayFeatures)
			.flatMap(DisplayFeatures::getResolution)
			.map(ScreenResolution::getHeight).orElse(0);
	  
  }
  
  
}