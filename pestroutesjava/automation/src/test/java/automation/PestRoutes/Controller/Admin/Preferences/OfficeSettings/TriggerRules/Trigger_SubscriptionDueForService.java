package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Footer;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionDueForServiceTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class Trigger_SubscriptionDueForService extends BaseClass {
	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	RenewalTab renewalTab;
	Actions actions = new Actions();
	ARTab ar;
	SubscriptionStatusTab subscriptionStatus;
	SubscriptionDueForServiceTab subscriptionDueForService;
	Service service;
	CreateNewCustomer createCustomer;
	CustomerViewDialog_SubscriptionTab subscription;
	ValidateRenewal validateRenewal;
	CustomerViewDialog_Header overviewHeader;
	ReminderTab reminder;
	Footer footer;

	public List list = new ArrayList<String>();

	private String descriptionTrigger = "trigger_subscriptionDueForService_all_actions";
	private String days_before_afterDueDate_InputField_Value = "1";
	private String SMSMAppointmentSubscriptionNote = "Sent to: ";
	private String employeeEmailValue = Utilities.generateRandomString(5) + "@gmail.com";
	private String alertTextinNotes = "Alert - Customer Status (UnRead)";
	private String taskTextinNotes = "Task - Customer Status (Pending)";
	private String employeeVoiceinNotes = "help unable to send message [bad phone number]";
	private String removePaymentinNotes = "Removed Payment Method from Trigger: Card";
	private String CCInfoBillingTab = "No Payment Info";

	@Test
	public void createSubscriptionDueForService() throws Exception {
		createTrigger_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		SMSAction_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		voiceAction_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		emailAction_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		snailMailAction_SubscriptionDueForService();
		/*
		 * searchTrigger_subscriptionDueForService();
		 * webhookAction_SubscriptionDueForService();
		 */
		searchTrigger_subscriptionDueForService();
		sendEmployeeEmail_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		addAlert_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		addTask_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		sendEmployeeSMS_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		sendEmployeeVoice_SubscriptionDueForService();
		searchTrigger_subscriptionDueForService();
		removePaymentProfile_subscriptionForService();
		searchTrigger_subscriptionDueForService();
		assertActions_SubscriptionDueForService();
		createRenewalServiceType();
		searchTrigger_subscriptionDueForService();
		editTrigger_beforeDueDate();
		createCustomer();
		createSubscription_beforeDueDate();
		hitTriggerSubscriptionDueForService_Query();
		assertSMSLog();
		validateIfFailureExist();
	}

	public void createTrigger_SubscriptionDueForService() throws Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		renewalTab = new RenewalTab();
		subscriptionStatus = new SubscriptionStatusTab();
		ar = new ARTab();
		subscriptionDueForService = new SubscriptionDueForServiceTab();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setDescription(descriptionTrigger);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown,
				triggerAdmin.triggerType_SubscriptionDueforService);
		triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(subscriptionDueForService.before_afterDueDate,
				subscriptionDueForService.afterDueDate_dueDateType);
		triggerAdmin.selectDropdown(subscriptionDueForService.before_afterDueDate,
				subscriptionDueForService.beforeDueDate_dueDateType);
		triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
		triggerAdmin.selectDropdown(renewalTab.hasInitialService_Renewal, renewalTab.hasInitialService_Any_Renewal);
		subscriptionDueForService.setdays_before_afterDueDate_InputField(days_before_afterDueDate_InputField_Value);
		triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_CommercialOnly);
		triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
		triggerAdmin.clickSaveButton();
	}

	// Search Subscription Due For Service Trigger
	public void searchTrigger_subscriptionDueForService() {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionTrigger);
		result(descriptionTrigger, triggerAdmin.getDescriptionText(descriptionTrigger), "Search Customer",
				"Subscription Due For Service Creation");
		triggerAdmin.clickEditTrigger(descriptionTrigger);
	}

	// Create a SMS action
	public void SMSAction_SubscriptionDueForService() throws InterruptedException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_Yes);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setMessageinAction_Type1(actions.sendSMSMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Voice Subscription Due For Service action
	public void voiceAction_SubscriptionDueForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.preRecordedMessageVoice_Reminder);
		// triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_Reminder,
		// "Pest Promotion");
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.newMessage_Voice);
		actions.setMessageinAction_Type1(actions.sendVoiceMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Email Subscription Due For Service Action
	public void emailAction_SubscriptionDueForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.EmailMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setEmailTitle(Utilities.generateRandomString(5));
		actions.setMessageinAction_Type2(actions.EmailMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create SnailMail Subscription Due For Service Action
	public void snailMailAction_SubscriptionDueForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.snailMailMessageType_Action);
		actions.setMessageinAction_Type2(actions.snailMailMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Webhook
	public void webhookAction_SubscriptionDueForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.webhookMessageType_Action);
		triggerAdmin.selectDropdown(actions.webhook_MethodType, actions.webhookMethod_GET);
		triggerAdmin.selectDropdown(actions.webhook_MethodType, actions.webhookMethod_POST);
		actions.messageInWebhook(actions.URLMessage_Wehbook, actions.getPlaceHolders());
		actions.messageInWebhook(actions.requestHeaderMessage_Webhook, actions.getPlaceHolders());
		actions.messageInWebhook(actions.requestBodyMessage_Webhook, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Send Employee Email
	public void sendEmployeeEmail_SubscriptionDueForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeEmail_SubscriptionStatus);
		actions.setEmailTitle_SubscriptionStatus(Utilities.generateRandomString(5));
		actions.setEmailAddress_SubscriptionStatus(employeeEmailValue);
		actions.setMessageinAction_Type2(actions.sendEmployeeEmail_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Alert
	public void addAlert_SubscriptionDueForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.addAlert_SubscriptionStatus);
		actions.setMessageinAction_Type1(actions.addAlert_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Add Task
	public void addTask_SubscriptionDueForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.addTask_SubscriptionStatus);
		actions.setDaysTillDueAddTask_SubscriptionStatus(Double.toString(Utilities.generateRandomInteger(1)));
		actions.setMessageinAction_Type1(actions.addTask_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee SMS
	public void sendEmployeeSMS_SubscriptionDueForService() throws IOException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeSMS_SubscriptionStatus);
		actions.setEmployeePhoneNumber_SubscriptionStatus(actions.sendEmployeeSMS_SubscriptionStatus,
				getData("phoneNumber", generalData));
		actions.setMessageinAction_Type1(actions.sendEmployeeSMS_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Action Send Employee Voice
	public void sendEmployeeVoice_SubscriptionDueForService() throws IOException {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmployeeVoice_SubscriptionStatus);
		actions.setEmployeePhoneNumber_SubscriptionStatus(actions.sendEmployeeVoice_SubscriptionStatus,
				getData("phoneNumber", generalData));
		triggerAdmin.selectDropdown(actions.voiceType_SubscriptionStatus, actions.preRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(actions.voiceType_SubscriptionStatus, actions.newMessage_Voice);
		actions.setMessageinAction_Type1(actions.sendEmployeeVoice_SubscriptionStatus, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create active for Remove Payment Profile Action
	public void removePaymentProfile_subscriptionForService() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.removePaymentProfile_SubscriptionStatus);
		actions.paymentType_removePaymentProfile(actions.CCandACH);
		triggerAdmin.clickSaveButton();
	}

	// Assert all created actions
	public void assertActions_SubscriptionDueForService() {
		ar = new ARTab();
		renewalTab = new RenewalTab();
		subscriptionStatus = new SubscriptionStatusTab();
		result(actions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Action",
				"Subscription Due For Service Creation");
		result(actions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Action",
				"Subscription Due For Service Creation");
		result(actions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action",
				"Subscription Due For Service Creation");
		result(actions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
				"Subscription Due For Service Creation");
		result(actions.sendEmployeeEmail_SubscriptionStatus, subscriptionStatus.getSendEmployeeEmailActionTextValue(),
				"Send Employee Email Action", "Subscription Due For Service Creation");
		result(actions.addAlert_SubscriptionStatus, subscriptionStatus.getAddAlertActionTextValue(), "Add Alert Action",
				"Subscription Due For Service Creation");
		result(actions.addTask_SubscriptionStatus, subscriptionStatus.getAddTaskActionTextValue(), "Add Task Action",
				"Subscription Due For Service Creation");
		result(actions.sendEmployeeSMS_SubscriptionStatus, subscriptionStatus.getSendEmploeeSMSActionTextValue(),
				"Send Employee SMS Action", "Subscription Due For Service Creation");
		result(actions.sendEmployeeVoice_SubscriptionStatus, subscriptionStatus.getSendEmployeeVoiceActionTextValue(),
				"Send Employee Voice Action", "Subscription Due For Service Creation");

	}

	// Create service type with Renewal Service
	public void createRenewalServiceType() throws Exception {
		service = new Service();
		service.workWithService();
	}

	// Create customer with Renewal Subscription
	public void createCustomer() throws Exception {
		header = new Header();
		header.NavigateTo(header.schedulingTab);
		createCustomer = new CreateNewCustomer();
		createCustomer.createCustomerWithAddress();
		createCustomer.validateCreatedCustomerNameAndAddress();

	}

	// Update Trigger Before Due date
	public void editTrigger_beforeDueDate() {
		subscriptionDueForService = new SubscriptionDueForServiceTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(subscriptionDueForService.before_afterDueDate,
				subscriptionDueForService.beforeDueDate_dueDateType);
		triggerAdmin.clickSaveButton();
	}

	// Create Subscription for Due Date set to tomorrow
	public void createSubscription_beforeDueDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		subscription.clickSubscription(subscription.getSubscriptionID(validateRenewal.serviceType));
		subscription.setCustomDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.ClickSaveButton();
	}

	// Hit the Script
	public void hitTriggerSubscriptionDueForService_Query() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerServiceDue.php");
	}

	// Navigate to customer and validate the SMS log
	public void assertSMSLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result(SMSMAppointmentSubscriptionNote + getData("phoneNumber", generalData),
				reminder.SMSConfirmationNote(getData("phoneNumber", generalData)), "SMS Notification Affirmative",
				"Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Email log
	public void assertEMailLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		createCustomer = new CreateNewCustomer();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result("Sent to: " + createCustomer.email, reminder.getEmailValue(), "Email Notification Affirmative",
				"Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Voice log
	public void assertVoiceLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result(SMSMAppointmentSubscriptionNote + getData("phoneNumber", generalData), reminder.getVoiceText(),
				"Voice Notification Affirmative", "Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Snail Mail log
	public void assertSnailMailLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		createCustomer = new CreateNewCustomer();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result("Sent to: " + createCustomer.streetAddress, reminder.getSnailMailValue(),
				"Snail Mail Notification Affirmative", "Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Employee EMmail log
	public void assertEmployeeEMailLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		createCustomer = new CreateNewCustomer();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result("Sent to: " + employeeEmailValue, reminder.getEmployeeEMailValue(employeeEmailValue),
				"Employee EMail Notification Affirmative", "Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Alert log
	public void assertAlertLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		createCustomer = new CreateNewCustomer();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result(alertTextinNotes, reminder.getAlertValue(), "Alert Notification Affirmative",
				"Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Task log
	public void assertTaskLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		createCustomer = new CreateNewCustomer();
		footer = new Footer();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result(taskTextinNotes, reminder.getTaskValue(), "Task Notification Affirmative",
				"Subscription Due For Service Creation");
		footer.NavigateTo(footer.tasks);
		result(getData("customerName", generalData), reminder.customerNameinTask(getData("customerName", generalData)),
				"Task Notification Affirmative", "Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Employee Voice log
	public void assertEmplopeeVoiceLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		createCustomer = new CreateNewCustomer();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result(employeeVoiceinNotes, reminder.getEmployeeVoiceValue(), "Employee Voice Notification Affirmative",
				"Subscription Due For Service Creation");
	}

	// Navigate to customer and validate the Employee Voice log
	public void assertRemovePaymentLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		createCustomer = new CreateNewCustomer();
		header.Search_A_Customer(getData("customerName", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		result(removePaymentinNotes, reminder.getRemovedPaymentValue(), "Remove Payment Notification Affirmative",
				"Subscription Due For Service Creation");
		overviewHeader.NavigateTo(overviewHeader.billingTabInDialog);
		result(CCInfoBillingTab, reminder.getCCInfoBilling(), "Remove Payment Notification Affirmative",
				"Subscription Due For Service Creation");
	}

	@SuppressWarnings("unchecked")
	public void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.hardAssert(expected, actual, stepName).size() > 0) {
			list.add(AssertException.hardAssert(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	public void validateIfFailureExist() {
		AssertException.asserFailure(list);
	}
}