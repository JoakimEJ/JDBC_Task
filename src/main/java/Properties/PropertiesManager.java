package Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Joakim on 10.12.2016.
 */
public class PropertiesManager
{
	private HashMap<String, String> allProperties;

	// Constructor

	public PropertiesManager()
	{
		allProperties = getAllProperties();
	}

	// Getters

	public String getUserName()
	{
		return allProperties.get("user");
	}

	public String getPassword()
	{
		return allProperties.get("password");
	}

	public String getDBname() {return allProperties.get("dbName");}

	public String getServerName() {return allProperties.get("serverName");}

	public String getFilePath() {return allProperties.get("filePath");}

	private HashMap<String, String> getAllProperties()
	{
		Properties prop = new Properties();
		HashMap<String, String> allProperties = new HashMap<>();

		String filename = "src/main/resources/config.properties";
		try(FileInputStream input = new FileInputStream(filename))
		{
			prop.load(input);

			Enumeration<?> allProps = prop.propertyNames();
			while(allProps.hasMoreElements())
			{
				String key = (String) allProps.nextElement();
				String value = prop.getProperty(key);
				allProperties.put(key, value);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return allProperties;
	}
}
