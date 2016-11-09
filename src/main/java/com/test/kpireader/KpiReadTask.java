package com.test.kpireader;

import java.util.concurrent.Callable;

public class KpiReadTask implements Callable<KpiResult> {
	public KpiModel kpiModel;
	public String serviceEndPoint;

	public KpiReadTask(KpiModel kpiModel, String serviceEndPoint) {
		this.kpiModel = kpiModel;
		this.serviceEndPoint = serviceEndPoint;
	}

	@Override
	public KpiResult call() {
		KpiResult kpiResult = new KpiResult();
		kpiResult.setKpiModel(this.kpiModel);
		try {
			// http://10.92.28.199:8081/counts
			HttpRequest httpRequest = new HttpRequest();
			// ?api_name=Authenticate&client_id=A-3amGd14-iz0&result_code=ERROR&dates=2016-05-02&type=TPS",
			// arg1)
			String queryParam;
			if (kpiModel.getClientId() == null || "".equals(kpiModel.getClientId())) {
				queryParam = String.format("?api_name=%s&result_code=%s&dates=%s&type=%s&interval=86400",
						kpiModel.getApiName(),kpiModel.getResultCode(), kpiModel.getDate(),
						kpiModel.getType());
			} else {
				// Counts type=TPS&

			
				/*queryParam = String.format(
				 "?api_name=%s&client_id=%s&result_code=%s&dates=%s&type=%s&error_code=%s&interval=86400",
				 kpiModel.getApiName(), kpiModel.getClientId(),
				 kpiModel.getResultCode(), kpiModel.getDate(),
				kpiModel.getType(),kpiModel.getErrorCode());	*/	

				queryParam = String.format("?api_name=%s&client_id=%s&result_code=%s&dates=%s&type=%s&interval=86400",
						kpiModel.getApiName(), kpiModel.getClientId(), kpiModel.getResultCode(), kpiModel.getDate(),
						kpiModel.getType());
			}
			String url = serviceEndPoint + queryParam;

			//System.out.println("url :  " + url);
			String response = httpRequest.getResponse(url);

			String[] responeLines = response.split("\n");
			float data = 0;

			// TPS

			/*for (String responeLine : responeLines) {
				if (!responeLine.contains("date")) {
					float tmpData = Float.parseFloat(responeLine.split(",")[1]);
					if (data < tmpData) {
						data = tmpData;
					}
				}

			}
			 */
			// Counts
			for (String responeLine : responeLines) {
				//System.out.println(responeLine);
				if (!responeLine.contains("date")) {
					String date = responeLine.split(",")[0];
					float tmpData = Float.parseFloat(responeLine.split(",")[1]);
					if (date.contains(kpiModel.getDate())) {
						data = tmpData;
					}
				}

			}

			kpiResult.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in " + this.getClass().getName() + " Error :" + e.getMessage());

		}

		return kpiResult;
	}

}
