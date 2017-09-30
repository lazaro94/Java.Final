package sensefilms.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DbFactory {

	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="rosariocdr.ddns.net";
	private String port="3306";
	private String user="sense";
	private String pass="SenseFilms";
	private String db="sense";
	private String dbType="mysql";
	
	private Connection conn;
	private int cantConn=0;
	
	private DbFactory() throws Exception{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException cnfex) {
			throw new Exception("Error del Driver JDBC",cnfex);
		}
	}
	
	private static DbFactory instancia;
	
	public static DbFactory getInstancia() throws Exception{
		if (instancia==null){
			instancia = new DbFactory();
		}
		return instancia;
	}
	
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection("jdbc:"+dbType+"://"+host+":"+port+"/"+
						db+"?user="+user+"&password="+pass);
				cantConn++;
			}
		} catch (SQLException sqlex) {
			new SQLException("Error al conectar a la DB", sqlex);

		}
		return conn;
	}
	
	public void releaseConn() throws Exception{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException sqlex) {
			throw new SQLException("Error al cerrar conexión",sqlex);
		}
	}
}
