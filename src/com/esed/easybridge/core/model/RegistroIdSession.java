package com.esed.easybridge.core.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RegistroIdSession {
	
	long idRegistrazione;
	String cuteCute;
	String idSessione;
	Timestamp dataUltimoAggiornamento;
	
	public RegistroIdSession() {
		super();
	}

	public RegistroIdSession(ResultSet data) throws SQLException {
		if (data == null)
			return;
		int i = 1;
		this.setIdRegistrazione(data.getLong(i++));
		this.setCuteCute(data.getString(i++));
		this.setIdSessione(data.getString(i++));
		this.setDataUltimoAggiornamento(data.getTimestamp(i++));
	}

	public long getIdRegistrazione() {
		return idRegistrazione;
	}

	public void setIdRegistrazione(long idRegistrazione) {
		this.idRegistrazione = idRegistrazione;
	}

	public String getCuteCute() {
		return cuteCute;
	}

	public void setCuteCute(String cuteCute) {
		this.cuteCute = cuteCute;
	}

	public String getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(String idSessione) {
		this.idSessione = idSessione;
	}

	public Timestamp getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Timestamp dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

}
