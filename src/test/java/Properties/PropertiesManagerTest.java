package Properties;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joakim on 11.12.2016.
 */
public class PropertiesManagerTest
{
	private static final PropertiesManager props = new PropertiesManager();

	@Test
	public void TestcheckHashMapForAllValues()
	{
		String userName = props.getUserName();
		String password = props.getPassword();
		String dbName = props.getDBname();
		String serverName = props.getServerName();

		assertTrue(userName.equals("newuser"));
		assertTrue(password.equals("newuser"));
		assertTrue(dbName.equals("test"));
		assertTrue(serverName.equals("localhost"));
	}
}