package automation.PestRoutes.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporter {
	static ExtentHtmlReporter htmlReport;
	static ExtentReports extent;
	static ExtentTest test;
	
	public static void status(String expectedResult, String actualResult, String testName) {
		htmlReport = new ExtentHtmlReporter("Results.html");
		htmlReport.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		ExtentTest test = extent.createTest(testName);
		if(expectedResult.contains(actualResult)) {
			test.log(Status.PASS, "Actual result  => "+ actualResult+ " matched with expected result  => " +expectedResult);
		} else {
			test.log(Status.FAIL, "Actual result  => "+ actualResult+ " did not match with expected result  => " +expectedResult);
		}
		
	}
	
	public static void flushReport() {
		extent.flush();
	}

}
