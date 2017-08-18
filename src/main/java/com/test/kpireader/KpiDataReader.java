package com.test.kpireader;

import java.net.URLEncoder;

public class KpiDataReader {
	public static void main(String[] args) throws Exception {
		 KpiSource kpiSource1= new KpiSource("","","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfoV1 UserInfoV2&result_code=ERROR SUCCESS&dates=2017-05-01 2017-05-02 2017-05-03 2017-05-04 2017-05-05 2017-05-06 2017-05-07 2017-05-08&type=MaxDailyTPS&interval=600",1); 
		KpiSource kpiSource2= new KpiSource("A-3amGd14-iz0","MYTMO","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfoV1 UserInfoV2&client_id=A-3amGd14-iz0&result_code=ERROR SUCCESS&dates=2017-05-01 2017-05-02 2017-05-03 2017-05-04 2017-05-05 2017-05-06 2017-05-07 2017-05-08&type=MaxDailyTPS&interval=600",2);
		KpiSource kpiSource3= new KpiSource("kyobiPL01","TMO Tuesday","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfoV1 UserInfoV2&client_id=kyobiPL01&result_code=ERROR SUCCESS&dates=2017-05-01 2017-05-02 2017-05-03 2017-05-04 2017-05-05 2017-05-06 2017-05-07 2017-05-08&type=MaxDailyTPS&interval=600",3); 
		KpiSource kpiSource4= new KpiSource("A-30uiP64-iz0","Middleware","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfoV1 UserInfoV2&client_id=A-30uiP64-iz0&result_code=ERROR SUCCESS&dates=2017-05-01 2017-05-02 2017-05-03 2017-05-04 2017-05-05 2017-05-06 2017-05-07 2017-05-08&type=MaxDailyTPS&interval=600",4); 
		KpiSource kpiSource5= new KpiSource("A-SGlGd14-iz0","EUI PM","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfoV1 UserInfoV2&client_id=A-SGlGd14-iz0&result_code=ERROR SUCCESS&dates=2017-05-01 2017-05-02 2017-05-03 2017-05-04 2017-05-05 2017-05-06 2017-05-07 2017-05-08&type=MaxDailyTPS&interval=600",5); 
		KpiSource kpiSource6= new KpiSource("Assurant","Assurant","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfoV1 UserInfoV2&client_id=Assurant&result_code=ERROR SUCCESS&dates=2017-05-01 2017-05-02 2017-05-03 2017-05-04 2017-05-05 2017-05-06 2017-05-07 2017-05-08&type=MaxDailyTPS&interval=600",6);
		KpiSource kpiSource7= new KpiSource("","TMO Tuesday Authorize  hourly counts.","http://10.92.28.199:8081/counts?api_name=Authorize&client_id=kyobiPL01&result_code=ERROR%20SUCCESS&dates=2017-05-08&type=Counts&interval=3600",7); 
		KpiSource kpiSource8= new KpiSource("","","http://10.92.28.199:8081/counts?api_name=Authorize&result_code=ERROR%20SUCCESS&dates=2017-05-08&type=Counts&interval=86400",8);
		
		KpiSource kpiSource9= new KpiSource("","","http://10.92.28.199:8081/counts?api_name=Authenticate+ValidateToken&client_id=A-3amGd14-iz0&result_code=ERROR+SUCCESS&dates=2017-05-01+2017-05-02+2017-05-03+2017-05-04+2017-05-05+2017-05-06+2017-05-07+2017-05-08&type=Counts&interval=86400",9);
		KpiSource kpiSource10= new KpiSource("","","http://10.92.28.199:8081/counts?api_name=CreateProfile+GenerateCaptcha+GetAllowedPasswordResetMethods+LinkMsisdn+ResetPassword+SendSmsPinToMsisdn+Validate2ndFactorAnswers+ValidateCaptcha+ValidateToken&client_id=A-SGlGd14-iz0&result_code=ERROR+SUCCESS&dates=2017-05-01+2017-05-02+2017-05-03+2017-05-04+2017-05-05+2017-05-06+2017-05-07+2017-05-08&type=Counts&interval=86400",10);
		KpiSource kpiSource11= new KpiSource("","","http://10.92.28.199:8081/counts?api_name=AuthenticateUser+ValidateToken&client_id=A-kIkGd14-iz0&result_code=ERROR+SUCCESS&dates=2017-05-01+2017-05-02+2017-05-03+2017-05-04+2017-05-05+2017-05-06+2017-05-07+2017-05-08&type=Counts&interval=86400",11);
		
		
		
		
		KpiSource kpiSourceList[]= { kpiSource1,kpiSource2,kpiSource3,kpiSource4,kpiSource5,kpiSource6,kpiSource7,kpiSource8,kpiSource9,kpiSource10,kpiSource11}; 
	//	KpiSource kpiSourceList[]= { kpiSource8};
		HttpRequest httpRequest = new HttpRequest();
		for(KpiSource kpiSource:kpiSourceList){			
			String url=kpiSource.getUrl().replace(" ", "%20");
			//System.err.println(url);
			String response = httpRequest.getResponse(url);
			
			System.out.println("--------------------- Report" +kpiSource.getReportNumber() + "-----------------------");
			if(kpiSource.getClientId()!=""){
				 System.out.println("Client Id :" +kpiSource.getClientId() + " Client Name :"+kpiSource.getClientName() + ",");
				 response=response.replace( ": "+kpiSource.getClientId() ,"") ;
			} 
			System.out.println(response);
		}
		
	}

}
