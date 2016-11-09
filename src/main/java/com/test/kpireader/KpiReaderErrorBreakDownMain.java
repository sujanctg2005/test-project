package com.test.kpireader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KpiReaderErrorBreakDownMain {
	static final Logger logger = LogManager.getLogger(KpiReaderErrorBreakDownMain.class.getName());

	public static void main(String... args) {

		String[] errorsCode = { "authentication_failure", "incorrect_captcha_response", "iam_user_account_soft_locked",
				"iam_account_hard_locked", "server_error", "line_account_soft_locked", "line_account_hard_locked",
				"invalid_request" };
		// String[] errorsCode = { "authentication_failure"};

		for (String errorCode : errorsCode) {

			// String[] clientIdList = { "A-3amGd14-iz0", "A-SGlGd14-iz0",
			// "A-hrlGd14-iz0", "A-kIkGd14-iz0", "A-XXkGd14-iz0" };
			Map<String, String> clinetNameMap = new HashMap<String, String>();

			clinetNameMap.put("A-3amGd14-iz0", "MYTMO");
			//clinetNameMap.put("kyobiPL01", "TMO Tuesday");
			/*clinetNameMap.put("A-vWlGd14-iz0", "TIBCO-ODP4");
			clinetNameMap.put("A-SGlGd14-iz0", "EUI PM");

			clinetNameMap.put("Assurant", "Assurant");
			clinetNameMap.put("A-30uiP64-iz0", "DSPA");
			clinetNameMap.put("mwPhone2-0", "middleware");*/

			String[] clientIdList = clinetNameMap.keySet().toArray(new String[clinetNameMap.keySet().size()]);

			String[] dateList ={"2016-10-25","2016-10-26","2016-10-27","2016-10-28","2016-10-29","2016-10-30","2016-10-31","2016-11-01"};
			//String[] dateList = { "2016-10-04"};
			//String apis = "Authenticate-SUCCESS,Authenticate-ERROR,Authorize-SUCCESS,Authorize-ERROR,UserInfo-SUCCESS,UserInfo-ERROR";
			// String apis = "UserInfo-SUCCESS,UserInfo-ERROR";
		     String apis ="Authenticate-ERROR";
			// "UpdateServicePermissionFlag-SUCCESS,UpdateServicePermissionFlag-ERROR,ValidateToken-SUCCESS,ValidateToken-ERROR";
			String[] apiList = apis.split(",");

			ExecutorService threadPool = Executors.newFixedThreadPool(5);

			CompletionService<KpiResult> pool = new ExecutorCompletionService<KpiResult>(threadPool);
			int count = 0;

			for (String clientId : clientIdList) {
				//System.out.println("Client id: " + clientId);
				for (String date : dateList) {
					for (String api : apiList) {
						KpiModel kpiModel = new KpiModel();
						kpiModel.setApiName(api.split("-")[0]);
						kpiModel.setClientId(clientId);
						kpiModel.setResultCode(api.split("-")[1]);
						kpiModel.setErrorCode(errorCode);
						kpiModel.setDate(date);
						kpiModel.setType("Counts");
						KpiReadTask task = new KpiReadTask(kpiModel, "http://10.92.28.199:8081/counts");
						pool.submit(task);
						count = count + 1;
					}
				}
			}
			//System.out.println("Request submit done , total request:" + count);
			StringBuffer allResult = new StringBuffer();
			allResult.append("Client Id,Date,API,TPS\n");

			Map<String, Float> resultMap = new HashMap<String, Float>();

			for (int i = 0; i < count; i++) {

				try {
					KpiResult kpiResult = pool.take().get();
					String key = kpiResult.getKpiModel().getClientId() + "/" + kpiResult.getKpiModel().getDate() + "/"
							+ kpiResult.getKpiModel().getApiName() + "-" + kpiResult.getKpiModel().getResultCode();

					resultMap.put(key, kpiResult.getData());

				} catch (InterruptedException e) {

					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			}
			// System.out.println("Result Map size:" + resultMap.size());
			StringBuffer report = new StringBuffer();
			for (String clientId : clientIdList) {
				String clientName = clinetNameMap.get(clientId);

				report.append(
						" Client Id," + clientId + ",Client Name," + clientName + ", Error Code ," + errorCode + "\n");

				// report.append(" Client Id," + clientId + ",Client Name," +
				// clientName + "\n");

				report.append("," + apis + "\n");
				for (String date : dateList) {
					report.append(date + ",");
					for (String api : apiList) {

						String key = clientId + "/" + date + "/" + api.split("-")[0] + "-" + api.split("-")[1];
						report.append(resultMap.get(key) + ",");
					}
					report.append("\n");

				}
			}

			System.out.println(report);
			threadPool.shutdown();
			resultMap = null;
		}

		// singleRequest();
	}

	/*
	 * public static void singleRequest(){
	 * 
	 * KpiModel kpiModel = new KpiModel(); //
	 * http://10.92.28.199:8081/chart?api_name=Authenticate&client_id=A-3amGd14-
	 * iz0&result_code=SUCCESS&dates=2016-05-02&type=TPS
	 * kpiModel.setApiName("Authenticate");
	 * kpiModel.setClientId("A-3amGd14-iz0"); kpiModel.setResultCode("SUCCESS");
	 * kpiModel.setDate("2016-05-02"); kpiModel.setType("TPS"); KpiReadTask task
	 * = new KpiReadTask(kpiModel, "http://10.92.28.199:8081/counts");
	 * logger.info("Data :" + task.call().getData());
	 * 
	 * }
	 */

}
