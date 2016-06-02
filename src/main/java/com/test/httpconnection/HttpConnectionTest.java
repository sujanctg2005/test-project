package com.test.httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpConnectionTest {
	private static final int waittime = 200;
	private static final int numberOfThreadsInThePool = 3;

	public void startProcess(int totalRequest) {
		long lStartTime = System.nanoTime();
		ExecutorService threadPool = Executors.newFixedThreadPool(totalRequest);

		CompletionService<String> pool = new ExecutorCompletionService<String>(threadPool);

		for (int i = 0; i < totalRequest; i++) {
			Printer printer = new Printer("http://localhost:8080/oauth2/v1/generatecaptcha");
			pool.submit(printer);
		}

		for (int i = 0; i < totalRequest; i++) {
			try {
				String result = pool.take().get();
				// System.out.println("result : " + result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Compute the result
		}

		long lEndTime = System.nanoTime();

		long difference = lEndTime - lStartTime;
		double diff = (difference / 1000000);
		double avgResponse = diff / totalRequest;
		String result = String.format("Total Request : %d , Elapsed milliseconds: %f ,average response : %f",
				totalRequest, diff, avgResponse);
		System.out.println(result);

		threadPool.shutdown();

	}

	public static void main(String... args) throws InterruptedException {
		/*
		 Total Request : 200 , Elapsed milliseconds: 658.000000 ,average response : 3.290000
        Total Request : 400 , Elapsed milliseconds: 608.000000 ,average response : 1.520000
		 
		 
		 */
		System.out.println("Going to sleep1");
		Thread.sleep(30000);
		
		System.setProperty("http.maxConnections", "3");
		System.setProperty("http.keepalive", "true");
		System.out.println("wake up1");
	//	Thread.sleep(30000);
		HttpConnectionTest httpConnectionTest = new HttpConnectionTest();
		for(int i=0;i<=220;i++){
		httpConnectionTest.startProcess(5);
		System.out.println("Going to sleep");
		Thread.sleep(30000);
		System.out.println("wake up");
		}
		//Thread.sleep(30000);
		// httpConnectionTest.startProcess(40);
		/*
		 * httpConnectionTest.startProcess(60);
		 * httpConnectionTest.startProcess(80);
		 * httpConnectionTest.startProcess(100);
		 * httpConnectionTest.startProcess(120);
		 */

	}

	public static String getResponse(String url) {
		URLConnection conn = null;
		StringBuffer response = new StringBuffer();

		try {
			URL a = new URL(url);
			conn = a.openConnection();
			System.out.println("stop reading");
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			 in.close();
		} catch (IOException e) {
			System.out.println("\n---------------- Error --------------------\n");
			e.printStackTrace();
			try {
				HttpURLConnection hconn = ((HttpURLConnection) conn);
				int respCode = hconn.getResponseCode();
				BufferedReader in = new BufferedReader(new InputStreamReader(hconn.getErrorStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				
				in.close();

			} catch (IOException ex) {
				System.out.println("Error in http catch");
				ex.printStackTrace();
			}
		}

		return response.toString();
	}

	public class Printer implements Callable<String> {

		private final String url;

		public Printer(String url) {
			this.url = url;
		}

		public String call() {
			try {
				Thread.sleep(waittime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getResponse(url);
		}
	}
}
