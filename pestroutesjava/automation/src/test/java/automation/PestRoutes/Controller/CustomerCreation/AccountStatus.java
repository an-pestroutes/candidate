package automation.PestRoutes.Controller.CustomerCreation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CreateCustomer.StatusChange;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;


public class AccountStatus extends BaseClass {
	static ExtentTest test;
	CreateCustomerDIalog customer = new CreateCustomerDIalog();
	CustomerViewDialog_Header dialog = new CustomerViewDialog_Header();
	CustomerViewDialog_OverviewTab overview = new CustomerViewDialog_OverviewTab();
	Header header = new Header();
	StatusChange statusChange = new StatusChange();
	List list = new ArrayList<String>();

	@Test
	public void validateAccountStatus() throws Exception {
		//createCustomer();
		validateActiveStatus();
		validateFrozenStatus();
		AssertException.asserFailure(list);
	}

	// Creates customer
	public void createCustomer() throws Exception {
		String expectedAlert = "Required: You must fill in the customer's last name or company name!";
		String fName = Utilities.generateRandomString(7);
		String lName = Utilities.generateRandomString(6);
		header.NavigateTo(header.newCustomerTab);
		customer.setFirstName(fName);
		customer.selectUnit("Multi Unit");
		dialog.ClickSaveButton();
		String alert = Utilities.getAlertText();
		list.add(AssertException.result(expectedAlert, alert, "Validate required field"));
		Utilities.acceptAlert();
		customer.setLastName(lName);
		dialog.ClickSaveButton();
		Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
		String customerNameInHeader = overview.getCustomerNameFromHeader();
		System.out.println("Customer Name found is " + customerNameInHeader);
		list.add(AssertException.result(fName, customerNameInHeader, "Validate Customer Creation"));
		String id = overview.getCustomerIDFromHeader();
		String newId = id.replaceAll("[^a-zA-Z0-9]+", "");
		System.out.println(newId);
		addData("userID", newId, generalData);
	}

	// Change the account to Active and assert
	private void validateActiveStatus() throws Exception {
		header.Search_A_Customer(getData("userID", generalData));
		dialog.NavigateTo(dialog.adminTabInDialog);
		statusChange.changeAccountStatus_Active();
		statusChange.getAccountStatus();
		String actualStatus = statusChange.getAccountStatus().substring(16, 57);
		System.out.println(statusChange.getAccountStatus().substring(16, 57));
		System.out.println(statusChange.PDTDate());
		list = AssertException.result("status: [Frozen] was changed to [Active]", actualStatus, "Frozen Status");
		Reporter.status("Activate Status", "status: [Frozen] was changed to [Active]", actualStatus, "Frozen Status");

	}

	// Change the account to Frozen and assert
	private void validateFrozenStatus() throws IOException, Exception {
		header.Search_A_Customer(getData("userID", generalData));
		dialog.NavigateTo(dialog.adminTabInDialog);
		statusChange.changeAccountStatus_Frozen(statusChange.medical, statusChange.cancelServiceProps);
		statusChange.getAccountStatus();
		String actualStatus = statusChange.getAccountStatus().substring(16, 57);
		System.out.println(statusChange.getAccountStatus().substring(16, 57));
		System.out.println(statusChange.PDTDate());
		list = AssertException.result("status: [Active] was changed to [Frozen]", actualStatus, "Frozen Status");
		Reporter.status("Activate Status", "status: [Active] was changed to [Frozen]", actualStatus, "Frozen Status");

	}
	

}