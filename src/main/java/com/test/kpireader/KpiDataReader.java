package com.test.kpireader;

import java.net.URLEncoder;

public class KpiDataReader {
	public static void main(String[] args) throws Exception {
		 KpiSource kpiSource1= new KpiSource("","","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfo&result_code=ERROR SUCCESS&dates=2016-10-25 2016-10-26 2016-10-27 2016-10-28 2016-10-29 2016-10-30 2016-10-31 2016-11-01&type=MaxDailyTPS&interval=600",1); 
		KpiSource kpiSource2= new KpiSource("A-3amGd14-iz0","MYTMO","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfo&client_id=A-3amGd14-iz0&result_code=ERROR SUCCESS&dates=2016-10-25 2016-10-26 2016-10-27 2016-10-28 2016-10-29 2016-10-30 2016-10-31 2016-11-01&type=MaxDailyTPS&interval=600",2);
		KpiSource kpiSource3= new KpiSource("kyobiPL01","TMO Tuesday","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfo&client_id=kyobiPL01&result_code=ERROR SUCCESS&dates=2016-10-25 2016-10-26 2016-10-27 2016-10-28 2016-10-29 2016-10-30 2016-10-31 2016-11-01&type=MaxDailyTPS&interval=600",3); 
		KpiSource kpiSource4= new KpiSource("A-30uiP64-iz0","Middleware","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfo&client_id=A-30uiP64-iz0&result_code=ERROR SUCCESS&dates=2016-10-25 2016-10-26 2016-10-27 2016-10-28 2016-10-29 2016-10-30 2016-10-31 2016-11-01&type=MaxDailyTPS&interval=600",4); 
		KpiSource kpiSource5= new KpiSource("A-SGlGd14-iz0","EUI PM","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfo&client_id=A-SGlGd14-iz0&result_code=ERROR SUCCESS&dates=2016-10-25 2016-10-26 2016-10-27 2016-10-28 2016-10-29 2016-10-30 2016-10-31 2016-11-01&type=MaxDailyTPS&interval=600",5); 
		KpiSource kpiSource6= new KpiSource("Assurant","Assurant","http://10.92.28.199:8081/counts?api_name=Authenticate Authorize UserInfo&client_id=Assurant&result_code=ERROR SUCCESS&dates=2016-10-25 2016-10-26 2016-10-27 2016-10-28 2016-10-29 2016-10-30 2016-10-31 2016-11-01&type=MaxDailyTPS&interval=600",6);
		KpiSource kpiSource7= new KpiSource("","TMO Tuesday Authorize  hourly counts.","http://10.92.28.199:8081/counts?api_name=Authorize&client_id=kyobiPL01&result_code=ERROR%20SUCCESS&dates=2016-11-01&type=Counts&interval=3600",7); 
		KpiSource kpiSource8= new KpiSource("","","http://10.92.28.199:8081/counts?api_name=Authorize&result_code=ERROR%20SUCCESS&dates=2016-11-01&type=Counts&interval=86400",8);
		KpiSource kpiSourceList[]= { kpiSource1,kpiSource2,kpiSource3,kpiSource4,kpiSource5,kpiSource6,kpiSource7,kpiSource8}; 
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
