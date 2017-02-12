package ListsUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Joakim on 10.12.2016.
 * The methods in this class is a collection of utilities used for creating and formatting lists
 * in a way that yields results needed in this solution.
 */
public class ListsUtils
{

	public ListsUtils ()
	{

	}

	/**
	 * Gets all data from a file and puts it in an ArrayList
	 * @param filePath the path to the file
	 * @return ArrayList\<String> of all data from file
	 */
	public List<String> getAllDataFromFileToArrayList(String filePath)
	{
		ArrayList<String> listOfAllFileData = new ArrayList<>();
		try(Scanner scanner = new Scanner(new File(filePath)))
		{
			while (scanner.hasNextLine())
			{
				String s = scanner.nextLine();
				listOfAllFileData.add(s);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return listOfAllFileData;
	}

	/**
	 * This method splits the 3 first elements in a list into separate lists and returns a new list
	 * containing the 3 new String arrays.
	 * @param listToSplit the list you want to split
	 * @return List containing 3 String arrays
	 */
	public List<String[]> splitMetadataLinesIntoSeparateLists(List<String> listToSplit, String separatorToSplitOn)
	{
		List<String[]> listOfLists = new ArrayList<>();
		String[] metadata1 = listToSplit.get(0).split(separatorToSplitOn);
		String[] metadata2 = listToSplit.get(1).split(separatorToSplitOn);
		String[] metadata3 = listToSplit.get(2).split(separatorToSplitOn);

		listOfLists.add(metadata1);
		listOfLists.add(metadata2);
		listOfLists.add(metadata3);

		return listOfLists;
	}

	/**
	 * This method replaces all occurrences of STRING with VARCHAR
	 * @param arrayToWork The array you want to do work on
	 * @return The array you gave in parameters with all occurrences of STRING replaced with VARCHAR
	 */
	public String[] replaceAllStringWithVarchar(String[] arrayToWork)
	{
		List<String> tempList = new ArrayList<>();
		for (String s : arrayToWork)
		{
			s = s.replaceAll("STRING", "VARCHAR");
			tempList.add(s);
		}

		int size = tempList.size();
		String[] listReplaced = new String[size];

		for (int i = 0; i < size; i++)
			listReplaced[i] = tempList.get(i);

		return listReplaced;
	}

	/**
	 * This method wraps all elements in the given array in parentheses
	 * @param arrayToWork The array you want to do work on
	 * @return The array you gave in parameters with all elements wrapped in parentheses
	 */
	public String[] wrapTokensInParentheses(String[] arrayToWork)
	{
		String[] newArray = new String[arrayToWork.length];
		for (int i = 0; i < arrayToWork.length; i++)
		{
			String wrappedString = "(" + arrayToWork[i] + ")";
			newArray[i] = wrappedString;
		}
		return newArray;
	}

}
