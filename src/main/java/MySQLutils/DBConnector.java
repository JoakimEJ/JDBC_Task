package MySQLutils;

import Properties.PropertiesManager;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Joakim on 10.12.2016.
 * This class has only one method whose purpose is to return a mySQL database connection
 */
public class DBConnector
{
	private static final PropertiesManager props = new PropertiesManager();
	private String dbName, serverName, userName, password;

	public DBConnector ()
	{
		dbName = props.getDBname();
		serverName = props.getServerName();
		userName = props.getUserName();
		password = props.getPassword();
	}

	/**
	 * This method returns a Connection when invoked
	 * @return Connection to a MySQL database
	 * @throws SQLException This exception should be handled where ever the method is invoked
	 */
	public Connection getConnection () throws SQLException
	{
		MysqlDataSource ds = new MysqlDataSource();
		ds.setDatabaseName(dbName);
		ds.setServerName(serverName);
		ds.setUser(userName);
		ds.setPassword(password);
		return ds.getConnection();
	}
}
