package MySQLutils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by Joakim on 10.12.2016.
 */
public class DBConnectorTest
{
	@Test
	public void TestgetConnection () throws Exception
	{
		DBConnector dbConnector = new DBConnector();
		Connection conn = dbConnector.getConnection();

		assertFalse(conn == null);
		conn.close();
	}
}
