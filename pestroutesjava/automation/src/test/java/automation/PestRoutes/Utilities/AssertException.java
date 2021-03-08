package automation.PestRoutes.Utilities;

import static automation.PestRoutes.Utilities.Utilities.list;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertException {
	public static List<String> result(String expected, String actual, String testName) {
		try {
			assertTrue((actual.replaceAll("\\s", "")).equals(expected.replaceAll("\\s", "")));
		} catch (AssertionError e) {
			System.out.println(testName);
			System.out.println("Expected was : '" + expected.replaceAll("\\s", "") + "'");
			System.out.println("Actual is : '" + actual.replaceAll("\\s", "") + "'");
			list.add(testName + ":" + e.getMessage());
		}
		return list;
	}

	public static List<String> conditionResult(WebElement element) {
		List list = new ArrayList<String>();
		try {
			if (element == null) {
				Assert.fail();
			}
		} catch (AssertionError e) {
			list.add(e.getMessage());
		}
		return list;
	}

	public static List<String> hardAssert(String expected, String actual, String testName) {
		List list = new ArrayList<String>();
		try {
			assertTrue(actual.equals(expected));
		} catch (AssertionError e) {
			list.add(testName + ":" + e.getMessage());
		}
		return list;
	}

	public static void assertFailure(List needListName) {
		if (needListName.size() > 0) {
			System.out.println(needListName.size());
			System.out.println(needListName);
			throw new AssertionError();
		}
	}

	public static void result(String expected, String actual, String stepName, String testName) {
		List list = new ArrayList<String>();
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	public static void validateFieldEnabled(String[] needArray) {
		for (int i = 0; i < needArray.length; i++) {
			WebElement field = FindElement.elementByAttribute(needArray[i], FindElement.InputType.XPath);
			if (field.isEnabled()) {
				Assert.assertTrue(true);
				Reporter.getInstance("Validate fields in account receivable").log(Status.PASS, needArray[i] + " displayed");
			} else {
				Reporter.getInstance("Validate fields in account receivable").log(Status.FAIL, needArray[i] + " is missing");
				Assert.assertFalse(true);
			}
		}

	}

	@And("I validate if there are errors exist in the list")
	public static void validateIfFailureExist() {
		AssertException.assertFailure(list);
	}
}

