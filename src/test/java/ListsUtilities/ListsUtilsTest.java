package ListsUtilities;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Joakim on 10.12.2016.
 * Test class for ListsUtils class
 */
public class ListsUtilsTest
{
	private ListsUtils listsUtils = new ListsUtils();

	private List<String> testList = Arrays.asList("Hang/in/there",
			"You/can/do/this!",
			"You/will/not/fail/this/class",
			"You/WILL/get/a/job/after/this!");

	@Test
	public void TestwrapTokensInParentheses () throws Exception
	{
		String[] testArray = new String[] {"128", "128", "3"};
		testArray = listsUtils.wrapTokensInParentheses(testArray);

		boolean checkCondition = false;
		for (String s : testArray)
			if (s.contains("(") && s.contains(")"))
				checkCondition = true;

		assertTrue(checkCondition);
	}

	@Test
	public void TestreplaceAllStringWithVarchar () throws Exception
	{
		String[] testArray = new String[] {"STRING", "STRING", "INT"};
		testArray = listsUtils.replaceAllStringWithVarchar(testArray);

		boolean stringFound = false;
		for (String s : testArray)
			if (s.equalsIgnoreCase("string"))
				stringFound = true;

		assertFalse(stringFound);
	}

	@Test
	public void TestsplitMetadataLinesIntoSeparateLists () throws Exception
	{
		List<String[]> metaDataList = listsUtils.splitMetadataLinesIntoSeparateLists(testList, "/");

		String[] metaline1 = metaDataList.get(0);
		for (String s : metaline1)
			System.out.print(s);

		assertTrue(metaDataList.size() == 3);
	}

	@Test
	public void TestgetAllDataFromFileToArrayList () throws Exception
	{
		String filePath = "src/main/resources/test1.txt";
		List<String> testList = listsUtils.getAllDataFromFileToArrayList(filePath);

		assertFalse(testList == null);
		assertTrue("Something went TERRIBLY WRONG HERE", testList.get(0).startsWith("Navn"));
	}

}
