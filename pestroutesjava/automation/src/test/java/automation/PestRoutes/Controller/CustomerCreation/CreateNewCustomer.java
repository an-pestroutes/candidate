package automation.PestRoutes.Controller.CustomerCreation;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;


public class CreateNewCustomer extends BaseClass{
	WebDriver driver = GetWebDriver.getInstance();
	CreateCustomerDIalog customer;
	CustomerViewDialog_Header dialog;
	CustomerViewDialog_OverviewTab overview;
	Header header;
	
	
	@Test
	public void CreateCustomer() throws Exception {
		String fName = "automation36";
		String lName = "newTest20";
		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDIalog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.setLastName(lName);
		//customer.selectUnit("Multi Unit");
		dialog.ClickSaveButton();
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		System.out.println("Customer Name found is "+customerNameInHeader);
		//assertTrue(customerNameInHeader.contains(fName));
		Reporter.status(customerNameInHeader, fName, "Validate customer creation");
		String id = overview.getCustomerIDFromHeader();
		String newId = id.replaceAll("[^a-zA-Z0-9]+","");
		System.out.println(newId);
		addData("userID", newId, generalData);
		
	}

}
