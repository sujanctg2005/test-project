package com.test.fileread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UrlReadFromFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int sum=0;
		for (int i=1 ; i<=100;i++){
			sum=sum+i;
		}
		System.out.println(sum);
		
		/*try(BufferedReader br = new BufferedReader(new FileReader("C:\\docs\\url.csv"))) {
		   int i=1;
			for(String line; (line = br.readLine()) != null; ) {
		    	String data[]=line.split(",");
		    	 
		    String a=	"{\n"+
		    		"\"id\": "+(i++)+" ,\n"+
		    		"\"title\": '"+data[0]+"',\n"+
		    		 "\"url_string\": \"/"+data[1]+"\"\n"+
		    		 "},";
		    	System.out.println(a);
		    	
		    }
		    // line is not visible here.
		}*/

	}

}
