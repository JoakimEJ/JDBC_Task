import MySQLutils.DBService;
import Properties.PropertiesManager;

/**
 * Created by Joakim on 10.12.2016.
 * This is the main class in this solution. This is where we run the program,
 * calling all resources needed, when the solution is complete
 */
public class Client
{
	public static void main (String[] args)
	{
		PropertiesManager props = new PropertiesManager();
		DBService dbService = new DBService();


		dbService.dropTableIfExists("testingTable"); // Comment this out if you want to keep table after program has run

		dbService.copyFile("testingTable", props.getFilePath());

		dbService.showTable("testingTable");

		dbService.truncateTable("testingTable"); // Comment out this line if you want the data to persist in table after program has run
	}
}
