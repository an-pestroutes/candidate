package automation.PestRoutes.Controller.Admin.Preferences.TriggerRules;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;

public class Trigger_Reminder extends BaseClass {
	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	ReminderTab reminder;
	RenewalTab renewalTab;
	ARTab ar;
	Actions actions;

	private String descriptionTrigger = "trigger_reminder_all_reminders";
	private String numberOfDays_Before_Reminder = Double.toString(Utilities.generateRandomInteger(1));
	
	@Test
	public void createReminderRule() throws Exception {
		createTrigger_Reminder();
		searchTrigger_Reminder();
		emailAction_Reminder();
		searchTrigger_Reminder();
		SMSAction_Reminder();
		searchTrigger_Reminder();
		voiceAction_Reminder();
	}

	// Create Reminder Trigger
	public void createTrigger_Reminder() throws Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		reminder = new ReminderTab();
		renewalTab = new RenewalTab();
		ar = new ARTab();
		actions = new Actions();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_Reminders);
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setDescription(descriptionTrigger);
		triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		reminder.setdaysBefore_Reminder(numberOfDays_Before_Reminder);
		triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
		triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
		triggerAdmin.selectDropdown(reminder.hasInitialServiceDropdown_Reminder,
				reminder.hasInitialService_Any_Reminder);
		triggerAdmin.clickSaveButton();
	}

	// Search Reminder Trigger
	public void searchTrigger_Reminder() {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionTrigger);
		triggerAdmin.clickEditTrigger(descriptionTrigger);
	}

	// Create an Reminder action
	public void emailAction_Reminder() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSReminder);
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmailReminder);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.emailType_Reminder, actions.standardReminderEmail_Reminder);
		triggerAdmin.selectDropdown(actions.emailType_Reminder, actions.customReminderEmail_Reminder);
		actions.setMessageinAction_Type2(actions.sendEmailReminder, actions.getPlaceHolders());
		actions.removeAction(actions.sendSMSReminder);
		triggerAdmin.clickSaveButton();
	}

	// Create second Reminder action
	public void SMSAction_Reminder() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSReminder);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.SMSType_Reminder, actions.standardReminderSMS_Reminder);
		triggerAdmin.selectDropdown(actions.SMSType_Reminder, actions.customSMS_Reminder);
		actions.setMessageinAction_Type1(actions.sendSMSReminder, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create third Reminder action
	public void voiceAction_Reminder() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceReminder);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.standardReminderVoice_Reminder);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.prerRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(actions.preRecordedMessage_Message_Reminder, "Pest Promotion");
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.standardReminderVoice_Reminder);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.customReminderVoice_Reminder);
		actions.setMessageinAction_Type1(actions.sendVoiceReminder, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}
}
