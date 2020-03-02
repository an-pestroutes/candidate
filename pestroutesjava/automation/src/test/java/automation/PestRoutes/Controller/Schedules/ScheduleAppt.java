package automation.PestRoutes.Controller.Schedules;

import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDIalog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.PageObject.RoutePage.SchedulingAppointmentDialog;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.PageObject.Scheduling.UnitsTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

public class ScheduleAppt extends BaseClass {
	private String routes = "//div[@class = 'route actualRoute route1 ']";
	private String serviceType = "Roach";
	private String serviceAreaProvided = "Exterior Only";
	private String pestTreaded = "Bat";
	private String product = "000 NEW";
	private String applicationMethod = "Direct Spray";
	private String targetIssue = "Bat";
	private String targetArea = "Back Lawn";

	CustomerViewDialog_Header overviewHeader;
	CustomerviewDialog_AppointmentsTab appointmentTab;
	SchedulingTab scheduleDay;
	SchedulingAppointmentDialog confirmAppt;
	RoutePage route;
	Header mainHeader;
	CreateCustomerDIalog customer;
	CustomerViewDialog_OverviewTab customerViewTab;
	UnitsTab unitsTab;

	@Test
	public void createSchedule() throws Exception {

		mainHeader = new Header();
		route = new RoutePage();
		overviewHeader = new CustomerViewDialog_Header();
		scheduleDay = new SchedulingTab();
		confirmAppt = new SchedulingAppointmentDialog();
		appointmentTab = new CustomerviewDialog_AppointmentsTab();
		customer = new CreateCustomerDIalog();
		unitsTab = new UnitsTab();

		changeToMultiUnit();
		addRoute();
		addAppointment();
		addChemicalInUnitTab();
		addChemical();
		verifyChemicalinUnit();
		verifyChemical();

	}

	private void changeToMultiUnit() throws IOException, Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
		customer.clickInfo();
		unitsTab.selectUnit("Multi Unit");
		overviewHeader.ClickSaveButton();
		confirmAppt.navigateToUnitTab();
		unitsTab.newUnitClick();
		unitsTab.setupUnit("Harold", "3", "62534");
	}

	private void addRoute() {
		mainHeader.NavigateTo(mainHeader.schedulingTab);
		scheduleDay.clickScheduleDay();
		route.addGroup();
		route.clickButton(route.addRoutesButton);
		route.addRoutesByQuantity("1");
	}

	private void addAppointment() throws Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
		overviewHeader.ClickScheduleButton();
		int totalCount = Utilities.getElementCount(routes);
		String routesCount = Integer.toString(totalCount);
		System.out.println(routesCount);
		route.scheduleAppointment(routesCount, "08:30");
		confirmAppt.selectServiceType(serviceType);
		confirmAppt.selectInteriorNeededOption(serviceAreaProvided);
		confirmAppt.selectTargetPestsOption(pestTreaded);
	}

	private void addChemicalInUnitTab() {
		unitsTab.clickUnitsScheduleApt();
		unitsTab.AddUnitsSchApt();
		unitsTab.clickDetails();
		confirmAppt.clickAddProduct();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.chooseTargetArea(targetArea);
		confirmAppt.clickScheduleButton();

	}

	private void addChemical() throws Exception {
		mainHeader.Search_A_Customer(getData("userID", generalData));
		overviewHeader.NavigateTo(overviewHeader.appointmentsTabInDialog);
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickStatusButton();
		appointmentTab.clickAddProductButton_InCompletingApptDialog();
		appointmentTab.chooseProduct(product);
		appointmentTab.chooseApplicationMethod(applicationMethod);
		appointmentTab.chooseTargetIssue(targetIssue);
		appointmentTab.chooseTargetArea(targetArea);
		appointmentTab.clickSaveAndCompleteButton();

	}

	private void verifyChemicalinUnit() {
		appointmentTab.clickScheduledService(serviceType);
		appointmentTab.clickUnitName();
		String actualUnitArea = appointmentTab.getUnitAreaTreated();
		String actualUnitPest = appointmentTab.getUnitPestsTreated();
		String actualUnitProductUsed = appointmentTab.getUnitChemicalName();
		assertTrue(actualUnitProductUsed.contains(product));
		Reporter.status(product, actualUnitProductUsed, "Verify added chemicals");
		assertTrue(actualUnitArea.contains(targetArea));
		Reporter.status(targetArea, actualUnitArea, "Verify added chemicals");
		assertTrue(actualUnitPest.contains(targetIssue));
		Reporter.status(targetIssue, actualUnitPest, "Verify added chemicals");

	}

	private void verifyChemical() {
		appointmentTab.clickScheduledService(serviceType);
		String actualProductUsed = appointmentTab.getChemicalName();
		String actualArea = appointmentTab.getTreatedArea();
		String actualPest = appointmentTab.getTreatedPests();
		assertTrue(actualProductUsed.contains(product));
		Reporter.status(product, actualProductUsed, "Verify added chemicals");
		assertTrue(actualArea.contains(targetArea));
		Reporter.status(targetArea, actualArea, "Verify added chemicals");
		assertTrue(actualPest.contains(targetIssue));
		Reporter.status(targetIssue, actualPest, "Verify added chemicals");
	}

}
