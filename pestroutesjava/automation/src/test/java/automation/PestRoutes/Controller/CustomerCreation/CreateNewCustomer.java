package automation.PestRoutes.Controller.CustomerCreation;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class CreateNewCustomer extends BaseClass {
	static ExtentTest test;
	CreateCustomerDIalog customer;
	CustomerViewDialog_Header dialog;
	CustomerViewDialog_OverviewTab overview;
	Header header;
	List list = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Test(groups = "createCustomer")
	public void CreateCustomer() throws Exception {

		String expectedAlert = "Required: You must fill in the customer's last name or company name!";
		String fName = Utilities.generateRandomString(7);
		String lName = Utilities.generateRandomString(6);
		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.selectUnit("Multi Unit");
		dialog.ClickSaveButton();
		String alert = Utilities.getAlertText();
		if (AssertException.result(expectedAlert, alert, "Validate required field").size() > 0) {
			list.add(AssertException.result(expectedAlert, alert, "Validate required field"));
		}
		//list.add(AssertException.result(expectedAlert, alert, "Validate required field"));
		Reporter.status("required field while creating customer ", expectedAlert, alert, "Customer creation");
		Utilities.acceptAlert();
		customer.setLastName(lName);
		dialog.ClickSaveButton();
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		System.out.println("Customer Name found is " + customerNameInHeader);
		if (AssertException.result(fName, customerNameInHeader, "Validate Customer Creation").size() > 0) {
			list.add(AssertException.result(fName, customerNameInHeader, "Validate Customer Creation"));
		}
		//list.add(AssertException.result(fName, customerNameInHeader, "Validate Customer Creation"));

		Reporter.status("Created customer ", fName, customerNameInHeader, "Customer creation");
		String id = overview.getCustomerIDFromHeader();
		String newId = id.replaceAll("[^a-zA-Z0-9]+", "");
		System.out.println(newId);
		addData("userID", newId, generalData);
		addData("customerName", fName + " " + lName, generalData);
		AssertException.asserFailure(list);
	}

}
