package MySQLutils;

import Properties.PropertiesManager;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Joakim on 11.12.2016.
 */
public class QueryManagerTest
{
	private static final String filePath = "src/main/resources/test1.txt";
	private static final QueryManager qm = new QueryManager(filePath);

	@Test
	public void TestcreateCreateTableQuery ()
	{
		String testString = qm.createCreateTableQuery("TESTONTHIS");

		assertTrue(testString.startsWith("CREATE TABLE IF NOT EXISTS TESTONTHIS"));
		assertTrue(testString.contains("Navn") && testString.contains("Alder"));
		assertTrue(testString.endsWith(");"));
	}

	@Test
	public void TestcreateInsertIntoTableQuery () throws Exception
	{
		String testString = qm.createInsertIntoTableQuery("TESTONTHIS");

		assertTrue(testString.startsWith("INSERT INTO TESTONTHIS"));

		assertTrue(testString.contains("Donald Duck")
				&& testString.contains("Flakseveien"));

		assertTrue(testString.endsWith(");"));
	}

}