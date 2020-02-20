package automation.PestRoutes.PageObject.CustomerOverview;

import org.openqa.selenium.WebElement;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomerViewDialog_SubscriptionTab {
	
	//********************Objects in subscription tab********************
	//***Sales Info/Billing Options objects***
	public String newSubscriptionButton = "//div[text()=  '+ New Subscription']";
	public String salesR3pDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo']";
	public String second_SalesRepDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='creditTo3']";
	public String sourceDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='sourceID']";
	public String contractLengthDropdown = "//h3[text()=  'Sales Info']/following-sibling::select[@name='agreementLength']";
	public String expirationDateInputField = "//input[@name='expirationDate']";
	public String pO_NumberInputField = "//input[@name='poNumber']";
	public String notesInputField = "//textarea[@name='subNotes']";
	public String netBillingDaysInputField = "//input[@name='netBillingDaysDisplay']";
	public String billingFrequencyDropdown = "//select[@name='billingFrequency']";
	//***Recurring Services objects***
	public String serviceTypeDropdown = "//select[@name='recurringServiceType']";
	public String serviceFrequencyDropdown = "//select[@name='frequency']";
	public String serviceDurationDropdown = "//select[@name='duration']";
	public String callAheadDropdown = "//select[@name='callAhead']";
	public String seasonalDropdown = "//select[@name='isSeasonal']";
	//***Schedule Options objects***
	public String autoScheduleDropdown = "//select[@name='autoSchedule']";
	public String initialFollowupDropdown = "//select[@name='followupDelay']";
	public String customDateInputField = "//input[@name='customDate']";
	public String routineRegionDropdown = "//select[@name='regionID']";
	public String preferredTechDropdown = "//select[@name='preferredTech']";
	public String preferredDayDropdown = "//select[@name='preferredDays']";
	public String preferredTimeDropdown = "//select[@name='hasPreferredTime']";
	//***Appointment times slots objects***
	public String firstUpcomingAppointment = "//div[@id='scheduleWrapper']/div[1]/h4";
	public String secondUpcomingAppointment = "//div[@id='scheduleWrapper']/div[2]/h4";
	public String thirdUpcomingAppointment = "//div[@id='scheduleWrapper']/div[3]/h4";
	public String fourthUpcomingAppointment = "//div[@id='scheduleWrapper']/div[4]/h4";
	public String fifthUpcomingAppointment = "//div[@id='scheduleWrapper']/div[5]/h4";
	public String sixthUpcomingAppointment = "//div[@id='scheduleWrapper']/div[6]/h4";
	public String seventhUpcomingAppointment = "//div[@id='scheduleWrapper']/div[7]/h4";
	//***Initial invoice template objects***
	public String initialInvoice_AddTicketItemButton = "//h3[text()='Initial Invoice Template']/following-sibling::div[text()='+ Add Ticket Item']";
	public String initialQuoteInputField = "//div[text()=  'Initial Quote']/following-sibling::input";
	public String initialDiscountInputField = "//div[text()=  'Initial Discount']/following-sibling::input";
	public String initialSubTotalValue = "//div[@id='initialTicket']//div[@class='ticketSummary']/div[2]";
	public String initialTaxValue = "//div[@id='initialTicket']//div[@class='ticketSummary']/div[4]";
	public String initialTotalValue = "//div[@id='initialTicket']//div[@class='ticketSummary']/div[6]";
	//***Recurring invoice template objects***
	public String recurringInvoice_AddTicketItemButton = "//h3[text()='Recurring Invoice Template']/following-sibling::div[text()='+ Add Ticket Item']";
	public String recurringInvoiceInputField = "//div[text()='Invoice']/following-sibling::input";
	public String recurringSubTotalValue="//div[@id='recurringServices']//div[@class='ticketSummary']/div[2]";
	public String recurringTaxValue = "//div[@id='recurringServices']//div[@class='ticketSummary']/div[4]";
	public String recurringTotalValue = "//div[@id='recurringServices']//div[@class='ticketSummary']/div[6]";
	
	
	//********************Functional methods by objects********************
	/*
	 * Click actions
	 * Below methods click or select objects
	 */
	public void clickNewSubscriptionButton() {
		Utilities.clickElement(newSubscriptionButton, ElementType.XPath);
	}
	
	public void selectSalesRep(String needSalesRap) {
		Utilities.selectValueFromDropDownByValue(salesR3pDropdown, needSalesRap);
	}
	
	public void selectSalesRep2(String needSalesRap) {
		Utilities.selectValueFromDropDownByValue(second_SalesRepDropdown, needSalesRap);
	}
	
	public void selectSource(String needSource) {
		Utilities.selectValueFromDropDownByValue(sourceDropdown, needSource);
	}
	
	public void selectContractLength(String needContractLength) {
		Utilities.selectValueFromDropDownByValue(contractLengthDropdown, needContractLength);
	}
	
	public void selectBillingFrequency(String needBillingFrequency) {
		Utilities.selectValueFromDropDownByValue(billingFrequencyDropdown, needBillingFrequency);
	}
	
	public void selectServiceType(String needServiceType) {
		Utilities.selectValueFromDropDownByValue(serviceTypeDropdown, needServiceType);
	}
	
	public void selectServiceFrequency(String needServiceFrequency) {
		Utilities.selectValueFromDropDownByValue(serviceFrequencyDropdown, needServiceFrequency);
	}
	
	public void selectServiceDuration(String needDuration) {
		Utilities.selectValueFromDropDownByValue(serviceDurationDropdown, needDuration);
	}
	
	public void selectCallAheadOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(callAheadDropdown, needOption);
	}
	
	public void selectSeasonalOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(seasonalDropdown, needOption);
	}
	
	public void selectAutoScheduleOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(autoScheduleDropdown, needOption);
	}
	
	public void selectInitialFollowupOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(initialFollowupDropdown, needOption);
	}
	
	public void selectRoutineRegionOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(routineRegionDropdown, needOption);
	}
	
	public void selectPreferTechOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(preferredTechDropdown, needOption);
	}
	
	public void selectPreferredDayOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(preferredDayDropdown, needOption);
	}
	
	public void selectPrefferedTimeOption(String needOption) {
		Utilities.selectValueFromDropDownByValue(preferredTimeDropdown, needOption);
	}
	
	public void selectAdditionalItem_ToInitialInvoice(String needItem) {
		Utilities.clickElement(initialInvoice_AddTicketItemButton, ElementType.XPath);
		Utilities.waitUntileElementIsVisible("//span[text()=  '"+needItem+"']");
		Utilities.clickElement("//span[text()=  '"+needItem+"']", ElementType.XPath);
	}
	
	public void selectAdditionalItem_ToRecurringInvoice(String needItem) {
		Utilities.clickElement(recurringInvoice_AddTicketItemButton, ElementType.XPath);
		Utilities.waitUntileElementIsVisible("//span[text()=  '"+needItem+"']");
		Utilities.clickElement("//span[text()=  '"+needItem+"']", ElementType.XPath);
	}
	
	/*
	 * Setter methods
	 * Below methods insert values in the object input field
	 */
	public void setExpDate(String needExpDate) {
		FindElement.elementByAttribute(expirationDateInputField, InputType.XPath).sendKeys(needExpDate);
	}
	
	public void set_PO_Number(String needPoNum) {
		FindElement.elementByAttribute(pO_NumberInputField, InputType.XPath).sendKeys(needPoNum);
	}
	
	public void setSubscriptionNotes(String needNotes) {
		FindElement.elementByAttribute(notesInputField, InputType.XPath).sendKeys(needNotes);
	}
	
	public void setNetBillingDays(String needBillingDays) {
		FindElement.elementByAttribute(netBillingDaysInputField, InputType.XPath).sendKeys(needBillingDays);
	}
	
	public void setCustomDate(String needCustomDate) {
		FindElement.elementByAttribute(customDateInputField, InputType.XPath).sendKeys(needCustomDate);
	}
	
	public void setInitialServiceQuote(String needAmount) {
		FindElement.elementByAttribute(initialQuoteInputField, InputType.XPath).clear();
		FindElement.elementByAttribute(initialQuoteInputField, InputType.XPath).sendKeys(needAmount);
	}
	
	public void setInitialServiceDiscount(String needAmount) {
		FindElement.elementByAttribute(initialDiscountInputField, InputType.XPath).clear();
		FindElement.elementByAttribute(initialDiscountInputField, InputType.XPath).sendKeys(needAmount);
	}
	
	public void setServiceQuote(String needService, String needAmount) {
		FindElement.elementByAttribute("//div[text()= '"+needService+"']/following-sibling::input", InputType.XPath).clear();
		FindElement.elementByAttribute("//div[text()= '"+needService+"']/following-sibling::input", InputType.XPath).sendKeys(needAmount);
	}

	/*
	 * Getter methods
	 * Below methods get string value of given object
	 */
	public String getUpcomingAppt(String chooseAppt) {
		Utilities.waitUntileElementIsVisible(chooseAppt);
		return Utilities.getElementTextValue(chooseAppt, ElementType.XPath);
	}
	
	public double getInitialService_NewTicketItemPrice(String needTicketItem) {
		WebElement elm = FindElement.elementByAttribute("//div[@id='initialTicket']//div[text() = '"+needTicketItem+"']/following-sibling::input", InputType.XPath);
		String val = elm.getAttribute("value");
		double attributeValue = Double.parseDouble(val);
		return attributeValue;
	}

	public double getInitialSubTotal() {
		String elm = Utilities.getElementTextValue(initialSubTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}
	
	public double getInitialTax() {
		String elm = Utilities.getElementTextValue(initialTaxValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}
	
	public double getInitialTotal() {
		String elm = Utilities.getElementTextValue(initialTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}
	
	public double getRecurringService_NewTicketItemPrice(String needTicketItem) {
		WebElement elm = FindElement.elementByAttribute("//div[@id='recurringServices']//div[text() = '"+needTicketItem+"']/following-sibling::input", InputType.XPath);
		String val = elm.getAttribute("value");
		double attributeValue = Double.parseDouble(val);
		return attributeValue;
	}
	
	public double getRecurringSubTotal() {
		String elm = Utilities.getElementTextValue(recurringSubTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}
	
	public double getRecurringTax() {
		String elm = Utilities.getElementTextValue(recurringTaxValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}
	
	public double getRecurringTotal() {
		String elm = Utilities.getElementTextValue(recurringTotalValue, ElementType.XPath);
		String newElm = elm.replace("$", "");
		double attributeValue = Double.parseDouble(newElm);
		return attributeValue;
	}

}
