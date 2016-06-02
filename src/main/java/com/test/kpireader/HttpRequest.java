package com.test.kpireader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpRequest {
//	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";
	private static final String ACCEPT_CONTENT = "text/csv; charset=utf-8";

	public String getResponse(String url) throws Exception {
		//System.out.println("Requesting URL:" + url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept", ACCEPT_CONTENT);
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);		

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			
			response.append(inputLine +"\n" );
			
		}
		reader.close();

		httpClient.close();
		
		return response.toString();
	}

}
