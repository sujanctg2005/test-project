package com.test.kpireader;

public class KpiSource {
	private String clientId;
	private String ClientName;
	private String url;
	private Integer reportNumber;

	public KpiSource(String clientId, String ClientName, String url, Integer reportNumber) {
		super();
		this.clientId = clientId;
		this.ClientName = ClientName;
		this.url = url;
		this.reportNumber = reportNumber;
	}

	public String getClientName() {
		return ClientName;
	}

	public void setClientName(String clientName) {
		ClientName = clientName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(Integer reportNumber) {
		this.reportNumber = reportNumber;
	}

}
