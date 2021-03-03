package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.WebElement;

public class InvoiceImplementation {

    // Values on Invoicing Landing Page
    public String accountSummaryButton = "//div[@id='billingPanel']//li[contains(text(),'Account Summary')]";
    public String invoiceAccountSummaryClick = "//ul[@id='invoiceGroupListContainer']/ul/li";
    public String initialInvoice = "//span[text()='Initial Balance']";
    public String accountStatementReport = "//li[text()='Account Statement Report']";

    // Invoice Amount
    private String newInvoice = "//form[@id=\"newInvoiceParams\"]//input[@type=\"number\"]";
    private String serviceSelect = "//form[@id=\"newInvoiceParams\"]//select[@name = \"serviceID\"]";
    private String service = "//form[@id=\"newInvoiceParams\"]/select/option[21]";
    private String create = "//span[contains(@class,'ui-button-text') and contains(text(),'Create')]";

    // Assertion Invoice Values
    private String invoiceCost = "//div[@id=\"serviceTicket\"]//div[contains (text(), '@ $5,000.00')]";
    private String totalValueCharges = "//div[@id=\"invoiceDetails\"]//div[@class = 'ticketTotal totalBoxValue']";

    // Payment Status
    //Do not have any other XPath for initialPaymentStatus
    private String initialPaymentStatus = "//ul[@id=\"invoiceGroupListContainer\"]/ul/li[1]/div[2]/div[2]";
    private String paymentBalance = "//form[@id = 'singlePaymentForm']//div[@id = 'SubStatus']";

    //Distribution Details
    public String limitedToCustomer = "//span[text()='Any Customer']";
    public String limitedToSubscription = "//span[text()='Any Subscription']";
    public String applyToFirst = "//span[text()='Any Invoice']";
    public String paymentWarning = "//div[text()='Prepayment Amount: ']/following-sibling::div[2]";
    public String renewalDateField = "//div[text()='Renewal Date: ']/following-sibling::input[@name = 'renewalDate']";

    // Cash tab
    private String paymentAmountField = "//div[text() = 'Payment Amount:']/following-sibling::input[@name = 'amount']";
    public String confirmPymtAmtField = "//input[@name = 'confirmCashAmount']";
    private String custPaymentNotes = "//textarea[@name = \"customerNotes2\"]";
    private String recordPayment = "//form[@id=\"singlePaymentForm\"]//div[contains (text(), 'Record Payment')]";

    // Payment Result
    private String successfulCharge = "//h2[contains(@class,'bold aCenter clr font24') and contains(text(),'Successfully Charged Cash!')]";
    private String successfulChargeAmount = "//form[@id=\"singlePaymentForm\"]//h2[@class=\"bold aCenter clr\"]";

    //Create New Invoice
    private String createNewInvoice_date = "//input[@name='date']";

    //Account Summary Objects
    public String accountBalance = "//div[@id='billingPanel']//div[@id='SubStatus']";
    public String printInvoiceAmountDue = "//th[text()='Amount Due']//following-sibling::td[1]";
    public String printInvoiceAmountDue1= "//span[text()='Sub Total']/parent::th/following-sibling::td/span[2]";

    //Initial Invoice Objects
    public String paymentsBalance = "//div[text()='Balance']/following-sibling::div";
    public String printInvoicePaymentBalance = "//th[text()='Amount Paid']//following-sibling::td[1]";
    public String printInvoicePaymentBalance1= "//span[text()='Amount Paid']/parent::th/following-sibling::td/span[2]";

    // Charges Objects
    public String serviceCostBeforeTax  = "//div[not(@ticketid='0')and@subscriptionid='0']//div[@serviceid]/input";
    public String subTotalValue = "//div[not(@ticketid='0')and@subscriptionid='0']//following-sibling::div//div[text()='Sub Total']/following-sibling::div[1]";
    public String taxValue = "//div[not(@ticketid='0')and@subscriptionid='0']//following-sibling::div//div[text()='Tax']/following-sibling::div[1]";
    public String chargesTotalValue = "//div[not(@ticketid='0')and@subscriptionid='0']//following-sibling::div//div[text()='Total']/following-sibling::div[1]";
    public String initialDiscountValue = "//div[@subscriptionid='0']//div[text()='Initial Discount']/following-sibling::input[@name='amount']";

    //Ticket Info Objects
    public String invoiceDate = "//div[text()='Invoice Date']/following-sibling::div[1]";
    public String dueDate = "//div[text()='Due Date']/following-sibling::div[1]";
    public String appointmentDate = "//div[text()='Appointment Date']/following-sibling::div[1]";

    // Account Statement Report Objects
    public String dateRange = "//input[@name='dateRange-accountStatementFilterParams']";
    public String reportType = "//select[@name='filterType']";
    public String refreshButton = "//div[text()='Refresh']";
    public String scrollLeftButton = "//div[@id='accountStatementReportScrollButtons']//div[contains(@class,'scrollLeft')]//i";
    public String scrollRightButton = "//div[@id='accountStatementReportScrollButtons']//div[contains(@class,'scrollRight')]//i";
    public String invoiceActionButton = "//div[@id='billingPanel']//div[@class='toggleActions right tableButton']";
    public String invoicePrintButton = "//div[@id='printInvoiceButton']";
    public String statementActionButton = "//div[@id='conditionsActionsBtn']";
    public String statementPrintButton = "//div[text()='Print']";

    //Print Invoice Objects
    public String additionalNotes ="//div[contains(@id,'additionalNotes')]//following-sibling::textarea";
    public String markLetterSentButton = "//div[@id='markPrintButton']";
    public String printInvoiceDate = "//div[contains(@id,'checkLen')]//following-sibling::div//tr[6]/td";
    public String printInvoiceMainAmounDue = "//th[text()='Amount Due']//following-sibling::td";
    public String printInvoiceMainAmounDue1= "//span[text()='Amount Due:']/ancestor::tr/td/span[2]";


    public String checkPaymentStatus() {
        Utilities.waitUntileElementIsVisible(initialPaymentStatus);
        return Utilities.getElementTextValue(initialPaymentStatus, ElementType.XPath);

    }

    public void newInvoiceDetails(String amount, String date) {
        Utilities.waitUntileElementIsVisible(createNewInvoice_date);
        FindElement.elementByAttribute(createNewInvoice_date, InputType.XPath).clear();
        FindElement.elementByAttribute(createNewInvoice_date, InputType.XPath).sendKeys(date);
        Utilities.clickElement(newInvoice, ElementType.XPath);
        FindElement.elementByAttribute(newInvoice, InputType.XPath).clear();
        FindElement.elementByAttribute(newInvoice, InputType.XPath).sendKeys(amount);
        Utilities.waitUntileElementIsVisible(serviceSelect);
        Utilities.clickElement(serviceSelect, ElementType.XPath);
        Utilities.waitUntileElementIsVisible(service);
        Utilities.clickElement(service, ElementType.XPath);
        Utilities.waitUntileElementIsVisible(create);
        Utilities.clickElement(create, ElementType.XPath);
    }

    public void insertPaymentAmount(String pAmount, String cAmount) {
        Utilities.waitUntileElementIsVisible(paymentAmountField);
        FindElement.elementByAttribute(paymentAmountField, InputType.XPath).clear();
        FindElement.elementByAttribute(paymentAmountField, InputType.XPath).sendKeys(pAmount);
        Utilities.waitUntileElementIsVisible(confirmPymtAmtField);
        FindElement.elementByAttribute(confirmPymtAmtField, InputType.XPath).sendKeys(cAmount);
        Utilities.waitUntileElementIsVisible(custPaymentNotes);
        FindElement.elementByAttribute(custPaymentNotes, InputType.XPath).sendKeys("This is just a test");

    }

    public void clickrecordPayment() {
        Utilities.waitUntileElementIsVisible(recordPayment);
        Utilities.clickElement(recordPayment, ElementType.XPath);
    }

    public void invoiceAccountSummaryClick() {
        Utilities.waitUntileElementIsVisible(invoiceAccountSummaryClick);
        Utilities.clickElement(invoiceAccountSummaryClick, ElementType.XPath);
    }

    public void clickInvoice(String needServiceName) {
        Utilities.waitUntileElementIsVisible("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]");
        Utilities.clickElement("//ul[@id='invoiceGroupListContainer']//div[contains(text(),'" + needServiceName + "')]", ElementType.XPath);
    }

    public void clickInitialInvoice(){
        Utilities.waitUntileElementIsVisible(initialInvoice);
        Utilities.clickElement(initialInvoice, ElementType.XPath);
    }

    public void clickRecurringInvoice(String recurringInvoiceTotal){
        Utilities.waitUntileElementIsVisible("//ul[@id='invoiceGroupListContainer']//span[text()='Remaining Balance']/parent::div[contains(text(),'"+recurringInvoiceTotal+"')]");
        Utilities.clickElement("//ul[@id='invoiceGroupListContainer']//span[text()='Remaining Balance']/parent::div[contains(text(),'"+recurringInvoiceTotal+"')]", ElementType.XPath);
    }

    public void clickAccountStatementReport(){
        Utilities.waitUntileElementIsVisible(accountStatementReport);
        Utilities.clickElement(accountStatementReport, ElementType.XPath);
    }

    public void clickAccountSummary(){
        Utilities.waitUntileElementIsVisible(accountSummaryButton);
        Utilities.clickElement(accountSummaryButton, ElementType.XPath);
    }

    public void selectDateRange(String day){
        Utilities.waitUntileElementIsVisible(dateRange);
        Utilities.clickElement(dateRange, ElementType.XPath);
        Utilities.waitUntileElementIsVisible( "//div[contains(@style,'block')]//li[text()='"+day+"']");
        Utilities.clickElement( "//div[contains(@style,'block')]//li[text()='"+day+"']", ElementType.XPath);
    }

    public void selectReportType(String needReportType){
        Utilities.waitUntileElementIsVisible(reportType);
        Utilities.selectValueFromDropDownByValue(reportType, needReportType);
    }

    public void scrollRight(){
        Utilities.waitUntileElementIsVisible(scrollRightButton);
        Utilities.clickElement(scrollRightButton, ElementType.XPath);
    }

    public void refreshAccountStatementReport(){
        Utilities.waitUntileElementIsVisible(refreshButton);
        Utilities.clickElement(refreshButton, ElementType.XPath);
    }

    public void printInvoice(){
        Utilities.waitUntileElementIsVisible(invoiceActionButton);
        Utilities.hoverElement(invoiceActionButton, invoicePrintButton);
    }

    public void printAccountStatement(){
        Utilities.waitUntileElementIsVisible(accountStatementReport);
        Utilities.clickElement(accountStatementReport, ElementType.XPath);
        Utilities.hoverElement(statementActionButton, statementPrintButton);
    }

    public void markLetterAsSent(){
        Utilities.waitUntileElementIsVisible(markLetterSentButton);
        Utilities.clickElement(markLetterSentButton, ElementType.XPath);
    }

    // Getters

    public int getInvoiceCost() {
        Utilities.waitUntileElementIsVisible(invoiceCost);
        return Utilities.removeSpecialChars(invoiceCost);
    }

    public Integer getTotalValueCharges() {
        Utilities.waitUntileElementIsVisible(totalValueCharges);
        return Utilities.removeSpecialChars(totalValueCharges);
    }

    public Integer getTotalValuePayments() {
        Utilities.waitUntileElementIsVisible(chargesTotalValue);
        return Utilities.removeSpecialChars(chargesTotalValue);
    }

    public String getInvoiceDate_accountStatementReport(String needServiceType){
        Utilities.waitUntileElementIsVisible("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][2]");
        return Utilities.getElementTextValue("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][2]", ElementType.XPath);
    }

    public String getInvoiceAmount_accountStatementReport(String needServiceType){
        Utilities.waitUntileElementIsVisible("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][4]");
        return Utilities.getElementTextValue("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][4]", ElementType.XPath);
    }

    public String getInvoiceBalance_accountStatementReport(String needServiceType){
        Utilities.waitUntileElementIsVisible("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][5]");
        return Utilities.getElementTextValue("//td[contains(text(),'"+needServiceType+"')]/following-sibling::td[text()][5]", ElementType.XPath);
    }

    public String getBalance(String balanceType){
        Utilities.waitUntileElementIsVisible("//td[contains(text(),'"+balanceType+"')]/following-sibling::td[text()]");
        return Utilities.getElementTextValue("//td[contains(text(),'"+balanceType+"')]/following-sibling::td[text()]", ElementType.XPath);
    }

    public String getResponsibleBalance(String balanceType){
        Utilities.waitUntileElementIsVisible("//td[contains(text(),'"+balanceType+"')]/following-sibling::td[text()]/following-sibling::td");
        return Utilities.getElementTextValue("//td[contains(text(),'"+balanceType+"')]/following-sibling::td[text()]/following-sibling::td", ElementType.XPath);
    }

    public String getSuccessfulChargeAmount() {
        String successfulChargeText = Utilities.getElementTextValue(successfulCharge, ElementType.XPath)
                + Utilities.getElementTextValue(successfulChargeAmount, ElementType.XPath);
        System.out.println("Charge is successful " + successfulChargeText);
        return successfulChargeText;
    }

    public int getPaymentBalance() {
        return Utilities.removeSpecialChars(paymentBalance);
    }

    public String getPaymentWarning() {
        return Utilities.getElementTextValue(paymentWarning, ElementType.XPath);
    }

    public String getAccountBalance(){
        Utilities.waitUntileElementIsVisible(accountBalance);
        return Utilities.getElementTextValue(accountBalance, ElementType.XPath);
    }

    public String getAccountTotalAmountDue(){
        try {
            Utilities.waitUntileElementIsVisible(printInvoiceAmountDue);
            return Utilities.getElementTextValue(printInvoiceAmountDue, ElementType.XPath);
        } catch (Exception e) {
            Utilities.waitUntileElementIsVisible(printInvoiceAmountDue1);
            return Utilities.getElementTextValue(printInvoiceAmountDue1, ElementType.XPath);
        }
    }

    public String getChargesBalance(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(chargesTotalValue, ElementType.XPath);
    }

    public String getPrintInvoiceMainAmountDue() {
        try {
            Utilities.waitUntileElementIsVisible(printInvoiceDate);
            return Utilities.getElementTextValue(printInvoiceMainAmounDue, ElementType.XPath);
        } catch(Exception e){
            Utilities.waitUntileElementIsVisible(printInvoiceMainAmounDue1);
            return Utilities.getElementTextValue(printInvoiceMainAmounDue1, ElementType.XPath);
        }
    }

    public String getChargesBalance_customSchedule(String initialAmountWithoutTax){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue("//div[text()='Sub Total']/following-sibling::div[contains(text(),'"+initialAmountWithoutTax+"')]/following-sibling::div[4]", ElementType.XPath);
    }

    public String getPaymentsBalance(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(paymentsBalance, ElementType.XPath);
    }

    public String getPrintInvoicePaymentBalance() {
        try {
            Utilities.waitUntileElementIsVisible(printInvoicePaymentBalance);
            return Utilities.getElementTextValue(printInvoicePaymentBalance, ElementType.XPath);
        } catch (Exception e) {
            Utilities.waitUntileElementIsVisible(printInvoicePaymentBalance1);
            return Utilities.getElementTextValue(printInvoicePaymentBalance1, ElementType.XPath);
        }
    }

    public String getServiceCostBeforeTax(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue(serviceCostBeforeTax,"value");
    }

    public String getAddOnValue(String addOn){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue("//div[not(@ticketid='0')and@subscriptionid='0']//div[text()='"+addOn+"']/following-sibling::input","value");
    }

    public String getSubTotalValue(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(subTotalValue, ElementType.XPath);
    }

    public String getTaxValue(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(taxValue, ElementType.XPath);
    }

    public String getInitialDiscountValue(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getAttributeValue(initialDiscountValue,"value");
    }

    public String getInvoiceDate(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(invoiceDate, ElementType.XPath);
    }

    public String getDueDate(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(dueDate, ElementType.XPath);
    }

    public String getAppointmentDate(){
        Utilities.waitUntileElementIsVisible(invoiceDate);
        return Utilities.getElementTextValue(appointmentDate, ElementType.XPath);
    }

    // Setters

    public void setAdditionalNotes(String needNotes){
        try {
            WebElement elm = FindElement.elementByAttribute(additionalNotes, InputType.XPath);
            Utilities.scrollToElementJS(additionalNotes);
            Utilities.waitUntileElementIsVisible(additionalNotes);
            Utilities.clickElement(additionalNotes, ElementType.XPath);
            FindElement.elementByAttribute(additionalNotes, InputType.XPath).sendKeys(needNotes);
        } catch (Exception e) {
            Utilities.scrollToElementJS(printInvoicePaymentBalance1);
            System.out.println("Unable to find additional notes section");
        }
    }

    public void setLimitedToSubscription(String needServiceName) {
        Utilities.clickElement(limitedToSubscription, ElementType.XPath);
        Utilities.clickElement("//label[contains (text(), '" + needServiceName + " Subscription Invoices')]", ElementType.XPath);
    }

}