package MySQLutils;

import ListsUtilities.ListsUtils;
import java.sql.*;
import java.util.List;

/**
 * Created by Joakim on 11.12.2016.
 * This class contains all methods that wil make changes to the database in some way
 */
public class DBService
{
	private DBConnector dbConnector;
	private ListsUtils listsUtils;
	private String filePath;

	public DBService ()
	{
		dbConnector = new DBConnector();
		listsUtils = new ListsUtils();
	}

	public void truncateTable(String tableName)
	{
		String query = "TRUNCATE TABLE " + tableName;

		try(Connection connection = dbConnector.getConnection();
				PreparedStatement ps = connection.prepareStatement(query))
		{
			ps.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void dropTableIfExists (String tableName)
	{
		String query = "DROP TABLE IF EXISTS " + tableName;

		try(Connection connection = dbConnector.getConnection();
			PreparedStatement ps = connection.prepareStatement(query))
		{
			ps.executeUpdate(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a table in a database from the provided data if its valid.
	 * @param tableName The name for your new table
	 * @param filePath Which file the program should read data from
	 */
	public void copyFile(String tableName, String filePath)
	{
		this.filePath = filePath;
		QueryManager queryManager = new QueryManager(filePath);

		String createTableString = queryManager.createCreateTableQuery(tableName);

		String insertIntoTableString = queryManager.createInsertIntoTableQuery(tableName);

		try(Connection connection = dbConnector.getConnection())
		{
			PreparedStatement preparedStatement = connection.prepareStatement(createTableString);
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(insertIntoTableString);
			preparedStatement.executeUpdate();

			preparedStatement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method can be called after creating a method if you want to show the results in the terminal
	 * @param tableName the name of the table you want to show
	 */
	public void showTable(String tableName)
	{
		List<String> allDataFromFile = listsUtils.getAllDataFromFileToArrayList(filePath);

		if (filePath == null)
			throw new NullPointerException("You need to run the copyFile method from DBService before you run this method");
		else
		{
			String[] columnNames = allDataFromFile.get(0).split("/");

			try (Connection connection = dbConnector.getConnection())
			{
				StringBuilder sb = new StringBuilder("SELECT ");
				for (String s : columnNames)
					sb.append(s).append(", ");
				sb.replace(sb.length()-2, sb.length(), " FROM ").append(tableName).append(";");

				PreparedStatement ps = connection.prepareStatement(sb.toString());

				try (ResultSet rs = ps.executeQuery())
				{
					ResultSetMetaData resultSetMetaData = rs.getMetaData();
					int numberOfColumns = resultSetMetaData.getColumnCount();

					for (int i = 1; i <= numberOfColumns; i++)
					{
						System.out.print(resultSetMetaData.getColumnName(i) + " | ");
					}
					System.out.println();

					while (rs.next())
					{
						for (int i = 1; i <= numberOfColumns; i++)
						{
							if (i > 1)
							{
								System.out.print(", ");
							}
							String columnData = rs.getString(i);
							System.out.print(columnData);
						}
						System.out.println("");
					}
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
