package MySQLutils;

import ListsUtilities.ListsUtils;

import java.util.List;

/**
 * Created by Joakim on 10.12.2016.
 * This class will contain all methods relevant for creating MySQL queries
 */
public class QueryManager
{
	private ListsUtils listsUtils;

	private List<String> fileDataList;
	private List<String[]> splitFileDataList;
	private String[] metaLine1;
	private String[] metaLine2;
	private String[] metaLine3;


	public QueryManager(String fileToWorkOn)
	{
		listsUtils = new ListsUtils();

		fileDataList = listsUtils.getAllDataFromFileToArrayList(fileToWorkOn);
		splitFileDataList = listsUtils.splitMetadataLinesIntoSeparateLists(fileDataList, "/");

		metaLine1 = splitFileDataList.get(0);
		metaLine2 = listsUtils.replaceAllStringWithVarchar(splitFileDataList.get(1));
		metaLine3 = listsUtils.wrapTokensInParentheses(splitFileDataList.get(2));
	}

	/**
	 * This method creates a create-table query and returns it
	 * @param tableName The name you want for your new table
	 * @return Returns a string which is formed in a way that it can be used to create a MySQL table
	 */
	public String createCreateTableQuery(String tableName)
	{
		StringBuilder createTableQueryBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
		createTableQueryBuilder.append(mergeListToFormCreateTableQuery()).append(");");

		return createTableQueryBuilder.toString();
	}

	/**
	 * This method creates a insert-into-table query and returns it
	 * @param tableName The name of the table you want to insert data into
	 * @return A string which can be executes as a MySQL query for inserting data into table
	 */
	public String createInsertIntoTableQuery(String tableName)
	{
		StringBuilder sb = new StringBuilder("INSERT INTO " + tableName + " (");
		for (String s : metaLine1)
			sb.append(s + ", ");
		sb.replace(sb.length()-2, sb.length(), ")").append(" VALUES ");

		for (int i = 3; i < fileDataList.size(); i++)
		{
			sb.append("(");
			String[] temp = fileDataList.get(i).split("/");
			for (int j = 0; j < temp.length; j++)
			{
				if (Character.isDigit(temp[j].charAt(0)))
				{
					sb.append(temp[j]);
					if (j == temp.length-1)
						sb.append("),");
					else sb.append(",");
				}
				else
				{
					sb.append("'").append(temp[j]);
					if (j == temp.length-1)
						sb.append("'),");
					else sb.append("',");
				}
			}
		}
		sb.replace(sb.length()-1, sb.length(), ";");
		return sb.toString();
	}

	/**
	 * This method merges 3 arrays of type String to form part of a create-table query.
	 * @return returns a String which is part of a sql query.
	 */
	private String mergeListToFormCreateTableQuery ()
	{
		StringBuilder createTableQueryBuilder = new StringBuilder();

		for (int i = 0; i < metaLine1.length; i++)
		{
			createTableQueryBuilder.append(metaLine1[i]).append(" ").append(metaLine2[i]).append(" ").append(metaLine3[i]).append(", ");
		}
		createTableQueryBuilder.replace(createTableQueryBuilder.length()-2, createTableQueryBuilder.length(), "");

		return createTableQueryBuilder.toString();
	}
}
