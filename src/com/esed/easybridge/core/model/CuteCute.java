package com.esed.easybridge.core.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CuteCute {

	long idRegistrazione;
	String cuteCute;
	String urlsPSPReturnURL;
	
	Timestamp dataUltimoAggiornamento;
	String operationUserName;

	public CuteCute() {
		super();
	}

	public CuteCute(ResultSet data) throws SQLException {
		if (data == null)
			return;
		int i = 1;
		this.setIdRegistrazione(data.getLong(i++));
		this.setCuteCute(data.getString(i++));
		this.setUrlsPSPReturnURL(data.getString(i++));
		this.setDataUltimoAggiornamento(data.getTimestamp(i++));
		this.setOperationUserName(data.getString(i++));
	}

	public String getCuteCute() {
		return cuteCute;
	}

	public void setCuteCute(String cuteCute) {
		this.cuteCute = cuteCute;
	}

	public long getIdRegistrazione() {
		return idRegistrazione;
	}

	public void setIdRegistrazione(long idRegistrazione) {
		this.idRegistrazione = idRegistrazione;
	}

	public String getUrlsPSPReturnURL() {
		return urlsPSPReturnURL;
	}

	public void setUrlsPSPReturnURL(String urlsPSPReturnURL) {
		this.urlsPSPReturnURL = urlsPSPReturnURL;
	}
	public Timestamp getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Timestamp dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperationUserName() {
		return operationUserName;
	}

	public void setOperationUserName(String operationUserName) {
		this.operationUserName = operationUserName;
	}
}
