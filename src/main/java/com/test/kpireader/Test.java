package com.test.kpireader;

public class Test {
	static {
		System.out.println("static");
	}
	{
		System.out.println("non static");
	}

	public static void main(String[] args) {
		
		String [] datas="OAuthOperation-pl-10.log.2016-05-17-00:2016-05-17 00:00:21.636,ae7781c6725e09154bd83f204dbd1efef,,716,601,P-CnMsS1Xag41,A-3amGd14-iz0,traffic-10,1,,,,,67,2,authentication_failure,The email/MSISDN/username or password is not correct.,,,1463468323722_ijxmd_690138491,desktop,,,mailto:mbundy412002@yahoo.com,".split(",");
		System.out.println("L:" +datas.length);
		int i=1;
		for(String data :datas) System.out.println( (i++) + " : " + data);
	}

}

