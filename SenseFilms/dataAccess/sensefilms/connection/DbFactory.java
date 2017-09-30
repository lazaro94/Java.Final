package sensefilms.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import sensefilms.utils.ConfigHandler;

import java.sql.Connection;

public class DbFactory {

	private String dbDriver;
	private String host;
	private String port;
	private String user;
	private String pass;
	private String db;
	private String dbType;
	
	private Connection conn;
	private int cantConn=0;
	
	private DbFactory() throws Exception
	{
		try 
		{
			initData();
			Class.forName(dbDriver);
		} 
		catch (ClassNotFoundException cnfex) 
		{		
			throw new Exception("Error del Driver JDBC",cnfex);
		}
	}
	
	private static DbFactory instancia;
	
	public static DbFactory getInstancia() throws Exception
	{
		if (instancia==null)
		{
			instancia = new DbFactory();
		}
		return instancia;
	}
	
	public Connection getConn()
	{
		try 
		{
			if(conn==null || conn.isClosed())
			{
				conn = DriverManager.getConnection("jdbc:"+dbType+"://"+host+":"+port+"/"+
						db+"?user="+user+"&password="+pass);
				cantConn++;
			}
		} 
		catch (SQLException sqlex) 
		{
			new SQLException("Error al conectar a la DB", sqlex);
		}
		return conn;
	}
	
	public void releaseConn() throws Exception
	{
		try 
		{
			cantConn--;
			if(cantConn==0)
			{
				conn.close();
			}
		} 
		catch (SQLException sqlex) 
		{
			throw new SQLException("Error al cerrar conexión",sqlex);
		}
	}
	
	private void initData() throws Exception
	{
		try 
		{
			dbDriver=ConfigHandler.getPropertieValue("dbDriver");
			host=ConfigHandler.getPropertieValue("host");
			port=ConfigHandler.getPropertieValue("port");
			user=ConfigHandler.getPropertieValue("user");
			pass=ConfigHandler.getPropertieValue("pass");
			db=ConfigHandler.getPropertieValue("db");
			dbType=ConfigHandler.getPropertieValue("dbType");			
		}
		catch(Exception ex) 
		{
			throw ex;
		}	
	}
}
