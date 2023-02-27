package com.esed.easybridge.core.dao;

import java.sql.*;
import java.util.*;

import com.esed.easybridge.core.model.*;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;

public class EyBridgeDao {

	Connection connection;
	String schema;
	
	protected CallableStatement callableStatementInsertCuteCute = null;
	protected CallableStatement callableStatementSelectCuteCute = null;
	
	protected CallableStatement callableStatementInsertRegistroIdSession = null;
	protected CallableStatement callableStatementSelectRegistroIdSession = null;

	Properties attributes = new Properties();
	
	public int getIntegerAttribute(String name) {
		return Integer.parseInt(attributes.getProperty(name));
	}

	public String getStringAttribute(String name) {
		return attributes.getProperty(name);
	}

	public EyBridgeDao(Connection connection, String schema){
		this.connection = connection;
		this.schema = schema;
	}

	//inizio LP 20190611 BUG connection open
	public void CloseConnection(){
		try {
			if (connection != null && !connection.isClosed()) { 
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("EyBridge::CloseConnection failed!");
		}
	}
	//fine LP 20190611

	public boolean testConnection()
	{
		try {
			if (callableStatementSelectCuteCute == null) {
				callableStatementSelectCuteCute = Helper.prepareCall(connection, schema, "EYCFGTB_SEL");
			}
		} catch (Exception e){
			System.out.println("EyBridge::testConnection failed!");
			return false;
		}
		//inizio LP PG21XX04 Leak
		finally
		{
			if (callableStatementSelectCuteCute != null) {
				DAOHelper.closeIgnoringException(callableStatementSelectCuteCute);
				callableStatementSelectCuteCute = null;
			}
		}
		//fine LP PG21XX04 Leak
		return true;
	}

	public boolean insertCuteCute(CuteCute bean) throws Exception
	{
		if(bean == null)return false;
		try
		{	
			if (callableStatementInsertCuteCute == null) {
				callableStatementInsertCuteCute = Helper.prepareCall(connection, schema, "EYCFGTB_INS");
			}
		    int i = 0;
		    
		    callableStatementInsertCuteCute.setLong(++i, bean.getIdRegistrazione());
		    callableStatementInsertCuteCute.setString(++i, bean.getCuteCute());
		    callableStatementInsertCuteCute.setString(++i, bean.getUrlsPSPReturnURL());
		    callableStatementInsertCuteCute.setString(++i, bean.getOperationUserName());
		    
		    callableStatementInsertCuteCute.registerOutParameter(++i, Types.INTEGER);

		    callableStatementInsertCuteCute.executeUpdate();
			if (callableStatementInsertCuteCute.getInt(i) == 1) return true;
			else return false;
			
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		} catch (HelperException x) {
			throw new Exception(x);
		}
		finally
		{
			if (callableStatementInsertCuteCute != null)
			//inizio LP PG21XX04 Leak
			{
			//fine LP PG21XX04 Leak
				DAOHelper.closeIgnoringException(callableStatementInsertCuteCute);
			//inizio LP PG21XX04 Leak
				callableStatementInsertCuteCute = null;
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public CuteCute selectCuteCute(String cuteCute) throws Exception
	{
		if (callableStatementSelectCuteCute == null) {
			callableStatementSelectCuteCute = Helper.prepareCall(connection, schema, "EYCFGTB_SEL");
		}
		ResultSet data = null;
		try	{
			callableStatementSelectCuteCute.setString(1, cuteCute);
			if (callableStatementSelectCuteCute.execute()) {
				data = callableStatementSelectCuteCute.getResultSet();
				if (data.next())
					return new CuteCute(data);
			}
			return null;
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		}
		finally {
			if (callableStatementSelectCuteCute != null)
			//inizio LP PG21XX04 Leak
			{
			//fine LP PG21XX04 Leak
				DAOHelper.closeIgnoringException(callableStatementSelectCuteCute);
				//inizio LP PG21XX04 Leak
				callableStatementSelectCuteCute = null;
			}
			//fine LP PG21XX04 Leak
			//inizio LP PG21XX04 Leak
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public boolean insertRegistroIdSession(RegistroIdSession bean) throws Exception
	{
		if(bean == null)return false;
		try
		{	
			if (callableStatementInsertRegistroIdSession == null) {
				callableStatementInsertRegistroIdSession = Helper.prepareCall(connection, schema, "EYRPTTB_INS");
			}
		    int i = 0;
		    callableStatementInsertRegistroIdSession.setLong(++i, bean.getIdRegistrazione());
		    callableStatementInsertRegistroIdSession.setString(++i, bean.getCuteCute());
		    callableStatementInsertRegistroIdSession.setString(++i, bean.getIdSessione());
		    callableStatementInsertRegistroIdSession.registerOutParameter(++i, Types.INTEGER);
		    callableStatementInsertRegistroIdSession.executeUpdate();
			if (callableStatementInsertRegistroIdSession.getInt(i) == 1) return true;
			else return false;
			
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		} catch (HelperException x) {
			throw new Exception(x);
		}
		finally
		{
			if (callableStatementInsertRegistroIdSession != null)
			//inizio LP PG21XX04 Leak
			{
			//fine LP PG21XX04 Leak
				DAOHelper.closeIgnoringException(callableStatementInsertRegistroIdSession);
			//inizio LP PG21XX04 Leak
				callableStatementInsertRegistroIdSession = null;
			}
			//fine LP PG21XX04 Leak
		}
	}

	public RegistroIdSession selectRegistroIdSession(String idSessione) throws Exception
	{
		if (callableStatementSelectRegistroIdSession == null) {
			callableStatementSelectRegistroIdSession = Helper.prepareCall(connection, schema, "EYRPTTB_SEL_SES");
		}
		ResultSet data = null;
		try	{
			callableStatementSelectRegistroIdSession.setString(1, idSessione);
			
			if (callableStatementSelectRegistroIdSession.execute()) {
				data = callableStatementSelectRegistroIdSession.getResultSet();
				if (data.next())
					return new RegistroIdSession(data);
			}
			return null;
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		}
		finally {
			if (callableStatementSelectRegistroIdSession != null)
			//inizio LP PG21XX04 Leak
			{
			//fine LP PG21XX04 Leak
				DAOHelper.closeIgnoringException(callableStatementSelectRegistroIdSession);
				//inizio LP PG21XX04 Leak
				callableStatementSelectRegistroIdSession = null;
			}
			//fine LP PG21XX04 Leak
			//inizio LP PG21XX04 Leak
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
}
