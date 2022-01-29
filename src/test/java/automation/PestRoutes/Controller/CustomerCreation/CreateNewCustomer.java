package automation.PestRoutes.Controller.CustomerCreation;

import java.io.IOException;

import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.CustomerOverview.*;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Search.SearchBox;
import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.Given;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static automation.PestRoutes.Utilities.AssertException.result;

public class CreateNewCustomer extends AppData {
    CreateCustomerDialog customer;
    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_Header customerDialog_Header;
    CustomerViewDialog_OverviewTab overview;
    CustomerViewDialog_Admin adminTab;

    Header header;
    CustomerViewDialog_InfoTab customerViewDialog_infoTab;
    SearchBox searchBox;

    // if fName and lName length is changed, update method validateEmailAddressInSearch() as well
    public String fName = Utilities.generateRandomString(3);
    public String lName = Utilities.generateRandomString(4);
    String expectedAlert = "Required: You must fill in the customer's last name or company name!";

    public String streetAddress = Integer.toString(Utilities.generateRandomNumber(4)) + " " + Utilities.generateRandomString(5);
    public String city = Utilities.generateRandomString(4);
    public String zipcode = "77008";
    public String email = Utilities.generateRandomString(3) + "." + Utilities.generateRandomString(4) + "" + "@gmail.com";
    public String primaryPhoneNumber = "6" + Integer.toString(Utilities.generateRandomNumber(9));

    public static String customerName = "";
    public static String customerFirstName;
    public static String propertyAddress;
    public static String cityStateZip;
    public static String emailAddress;
    public static String customerAccountID;

    @Test
    public void createCustomer() throws Exception {
        createCustomerWithOutRequiredField();
        validateRequiredFieldError();
        createCustomerWithAddress();
        validateCreatedCustomerNameAndAddress();

    }

    @When("I create customer without required last name field")
    public void createCustomerWithOutRequiredField() throws InterruptedException {
        String fName = Utilities.generateRandomString(7);
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.selectUnit("Multi Unit");
        customerDialog_Header.clickSaveButton();
    }

    @Then("I validate alert")
    public void validateRequiredFieldError() throws InterruptedException {
        String alert = Utilities.getAlertText();
        Utilities.acceptAlert();
        result(expectedAlert, alert, "required field while creating customer ", "Customer creation");
    }

    @When("I create customer with first name, last name and address")
    public void createCustomerWithAddress() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode("75098");
        Utilities.acceptAlert();
        customer.setEmailAddress(email);
        customer.setCellPhone(primaryPhoneNumber);
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerName = getCustomerFullName();
    }

    // Author : Aditya
    @When("I create customer with first name, last name and dynamic zip")
    public void createCustomerWithDynamicZip() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        Utilities.acceptAlert();
        customer.setEmailAddress(email);
        customer.setCellPhone(primaryPhoneNumber);
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerName = getCustomerFullName();
    }

    //**Author Aarbi
    public void createACustomer(String needFirstName, String lastName) throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.adminTab);
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(needFirstName);
        customer.setLastName(lastName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode("77008");
        Utilities.acceptAlert();
        customer.setEmailAddress(email);
        customer.setCellPhone(primaryPhoneNumber);
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerName = getCustomerFullName();
    }

    //**Author Aarbi**
    @And("I edit zipcode in info tab {string}")
    public void editCustomerZip(String needZip) throws InterruptedException {
        customer = new CreateCustomerDialog();
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        Utilities.highLight(customer.zipCodeInputField);
        customer.setZipCode(needZip);
        customerDialog_Header.clickSaveButton();
    }

    //**Author Aarbi**
    @Then("I validate if tax rate is same")
    public void validateTaxRate() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        customer.clickOverrideTaxCheckBox();
        String taxRate = customer.getTaxRate(customer.taxPercentageInputField);
        customerDialog_Header.navigateTo(customerDialog_Header.subscriptionTabInDialog);
        customerDialog_Header.clickSaveButton();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String actualTaxRate = customer.getTaxRate(customer.taxPercentageInputField);
        result(taxRate, actualTaxRate, "Created customer name ", "Customer creation");
    }

    //**Author Aarbi**
    @And("I validate if agent display in the list after clicking on transfer button {string}")
    public void validateTransferAccountOption(String needAgent) {
        customerDialog_Header = new CustomerViewDialog_Header();
        Utilities.clickElement(customerDialog_Header.tranferButtonInDialog, ElementType.XPath);
        WebElement agent = FindElement.elementByAttribute("//p[text() = '" + needAgent + "']", FindElement.InputType.XPath);
        AssertException.conditionResult(agent);
    }

    @And("I validate search customer with first name")
    public void validateFirstNameInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getFirstName());
        searchBox = new SearchBox();
        result(fName, searchBox.autoCompleteSearch(fName), "Validate first name in search", "Customer creation");
    }

    @And("I validate search customer with last name")
    public void validateLastNameInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getLastName());
        searchBox = new SearchBox();
        result(lName, searchBox.autoCompleteSearch(lName), "Validate last name in search", "Customer creation");
    }

    @And("I validate search customer with phone number")
    public void validatePhoneNumberInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getPrimaryPhoneNumber().replaceAll("[^0-9]", ""));
        searchBox = new SearchBox();
        String phoneNumberInSearchBox = searchBox.autoCompleteSearch(customerViewDialog_infoTab.getPrimaryPhoneNumber()).substring(0, 14);
        result(customerViewDialog_infoTab.getPrimaryPhoneNumber(), phoneNumberInSearchBox, "Validate phone number in search", "Customer creation");
    }

    @And("I validate search customer with last four of phone number")
    public void validateSearchLastFourPhoneNumber() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getPrimaryPhoneNumber().replaceAll("[^0-9]", "").substring(6, 10));
        searchBox = new SearchBox();
        String phoneNumberInSearchBox = searchBox.autoCompleteSearch(customerViewDialog_infoTab.getPrimaryPhoneNumber().substring(0, 9)).substring(0, 9);
        result(customerViewDialog_infoTab.getPrimaryPhoneNumber().substring(0, 9), phoneNumberInSearchBox, "Validate with last four of phone number in search", "Customer creation");
    }

    @And("I validate search customer with zip code")
    public void validateZipCodeInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getZip());
        searchBox = new SearchBox();
        result(zipcode, searchBox.autoCompleteSearch(zipcode), "Validate zip code in search", "Customer creation");
    }

    @And("I validate search customer with CustomerID")
    public void validateCustomerIDInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        overview = new CustomerViewDialog_OverviewTab();
        header.searchCustomer_SearchField(overview.getCustomerIDFromHeader());
        searchBox = new SearchBox();
        result(overview.getCustomerIDFromHeader(), searchBox.autoCompleteSearch(overview.getCustomerIDFromHeader()), "Validate customer ID in search", "Customer creation");
    }

    @And("I validate search customer with street address")
    public void validateStreetAddressInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getStreetAddress());
        searchBox = new SearchBox();
        result(streetAddress, searchBox.autoCompleteSearch(streetAddress), "Validate street address in search", "Customer creation");
    }

    // Author : Adi
    @And("I validate search customer with email address")
    public void validateEmailAddressInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getEmail());
        searchBox = new SearchBox();
        result(fName, searchBox.containsInAutoCompleteSearch(fName).substring(0, 8), "Validate first name in search", "Customer creation");
        result(lName, searchBox.containsInAutoCompleteSearch(lName).substring(8, 15), "Validate last name in search", "Customer creation");
    }

    @Then("^I create customer with address and ZipCode and I verify Main Tax, State Tax, City Tax, County Tax, Custom Tax, District1 Tax, District2 Tax" +
            ", District3 Tax, District4 Tax, District5 Tax Rates \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" " +
            "and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verifyCustomerWithAddressTaxRate(String needStreetAddress, String needZipCode, String needMainTax, String needStateTax,
                                                 String needCityTax, String needCountyTax, String needCustomTax, String needDistrict1Tax, String needDistrict2Tax,
                                                 String needDistrict3Tax, String needDistrict4Tax, String needDistrict5Tax) throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setAddress(needStreetAddress);
        customer.setZipCode(needZipCode);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        customer.clickOverrideTaxCheckBox();
        result(needMainTax, customer.getTaxRate(customer.mainTaxPercentage), "Entered Main Tax", "Tax Rate Validation");
        result(needStateTax, customer.getTaxRate(customer.stateTaxPercentage), "Entered State Tax", "Tax Rate Validation");
        result(needCityTax, customer.getTaxRate(customer.cityTaxPercentage), "Entered City Tax", "Tax Rate Validation");
        result(needCountyTax, customer.getTaxRate(customer.countyTaxPercentage), "Entered County Tax", "Tax Rate Validation");
        result(needCustomTax, customer.getTaxRate(customer.customTaxPercentage), "Entered Custom Tax", "Tax Rate Validation");
        result(needDistrict1Tax, customer.getTaxRate(customer.district1TaxPercentage), "Entered District1 Tax", "Tax Rate Validation");
        result(needDistrict2Tax, customer.getTaxRate(customer.district2TaxPercentage), "Entered District2 Tax", "Tax Rate Validation");
        result(needDistrict3Tax, customer.getTaxRate(customer.district3TaxPercentage), "Entered District3 Tax", "Tax Rate Validation");
        result(needDistrict4Tax, customer.getTaxRate(customer.district4TaxPercentage), "Entered District4 Tax", "Tax Rate Validation");
        result(needDistrict5Tax, customer.getTaxRate(customer.district5TaxPercentage), "Entered District5 Tax", "Tax Rate Validation");
    }

    @When("I create customer with first name, last name, address and generic flag {string}")
    public void createCustomerWithGenericFlag(String needFlagName) throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        Utilities.acceptAlert();
        //customer.setCity(city);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.selectSource(getData("customerSource", generalData));
        customer.selectGenericFlag(needFlagName);
        customer.setMapCode(getData("mapCode", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerName = getCustomerFullName();

    }

    @When("I create customer with pref paper")
    public void createCustomerWithPrefPaper() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode("75098");
        //Utilities.acceptAlert();
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customer.clickPrefersPaperCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerName = getCustomerFullName();

    }

    @When("I create customer with pref paper and residential property")
    public void createCustomerWithPrefPaperAndResidentialProperty() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode("77008");
        Utilities.acceptAlert();
        customer.clickOverrideTaxCheckBox();
        customer.setEmailAddress(email);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customer.setMapCode(getData("mapCode", generalData));
        customer.clickPrefersPaperCheckBox();
        customer.selectProperty(customer.residentialProperty);
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerName = getCustomerFullName();

    }

    @And("I search customer")
    public void searchCustomer() throws Exception {
        header = new Header();
        //header.searchCustomer_History(lName + ", " + fName);
        header.searchCustomerWithName(customerName);
    }

    @When("I create customer with first name, last name, email and address")
    public void createCustomerWithEmail() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setEmailAddress(email);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode("75098");
        //Utilities.acceptAlert();
        //customer.setCity(city);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerName = getCustomerFullName();
    }

    @And("I add properties customer source, property type, prefers paper, division and generic flag to the customer")
    public void addAdditionalProperties() throws IOException, InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        customer.selectSource(getData("customerSource", generalData));
        customer.selectProperty(customer.commercialProperty);
        customer.clickPrefersPaperCheckBox();
        customer.selectDivision(getData("division", generalData));
        customer.selectGenericFlag(getData("flag", generalData));
        customerDialog_Header.clickSaveButton();
    }

    @And("I add properties customer source, property type, prefers paper, division, purple dragon and generic flag to the customer")
    public void addAdditionalPropertiesTwo() throws IOException, InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        customer.selectSource(getData("customerSource", generalData));
        customer.selectProperty(customer.commercialProperty);
        customer.clickPrefersPaperCheckBox();
        customer.selectDivision(getData("division", generalData));
        customer.selectGenericFlag(getData("flag", generalData));
        customer.clickPurpleDragonCheckBox();
        customerDialog_Header.clickSaveButton();
    }

    @When("I create customer with first name and last name {string} {string} if not already existing")
    public void createCustomerWithoutAddress(String firstName, String lastName) throws Exception {

        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        searchBox = new SearchBox();
        header.searchCustomer_SearchField(firstName + " " + lastName);
        try {
            if (searchBox.containsInAutoCompleteSearch(firstName + " " + lastName).contains(firstName + " " + lastName)) {
                System.out.println("Customer found");
                Utilities.clickElement("//li[@role='presentation']//span[contains(text(),'"+firstName + " " + lastName+"')]", ElementType.XPath);
            }
        }catch (Exception e){
            System.out.println("Creating customer");
            header.navigateTo(header.newCustomerTab);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.selectUnit("Multi Unit");
            customer.setCellPhone(getData("phoneNumber", generalData));
            customer.clickSmsCheckBox();
            customerDialog_Header.clickSaveButton();
            alertCondition();
        }
    }


    @Given("I close customer card")
    public void closeCustomerCard() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.clickCloseButton();
    }

    @Then("I refresh the browser")
    public void refreshPage() {
        Utilities.refreshPage();
    }

    @When("I create customer with first name, last name, address, email and Structure")
    public void createCustomerWithStructure() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setEmailAddress(email);
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        Utilities.acceptAlert();
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customer.selectUnit("Structures");
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerDialog_Header.saveAnyways();
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String customerNameInHeader = customerViewDialog_infoTab.getFirstName();
        customerName = getCustomerFullName();
        result(fName, customerNameInHeader, "Created customer ", "Structure Validation");
        String newId = overview.getCustomerIDFromHeader();
        addData("strutureUID", newId, generalData);
        AssertException.assertFailure(Utilities.list);
    }

    @Then("I validate if customer name and address match in overview tab")
    public void validateCreatedCustomerNameAndAddress() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        overview = new CustomerViewDialog_OverviewTab();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String expectedAddress = streetAddress + " " + customerViewDialog_infoTab.getCity() + ", " + customerViewDialog_infoTab.getState() + " " + zipcode;
        customerDialog_Header.navigateTo(customerDialog_Header.overviewTabInDialog);
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        String actualAddress = overview.getFullAddress();
        result(expectedAddress, actualAddress, "Created customer address", "Customer creation");
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String customerNameInInfo = customerViewDialog_infoTab.getFirstName() + " " + customerViewDialog_infoTab.getLastName();
        String actualName = fName + " " + lName;
        result(actualName, customerNameInInfo, "Created customer name ", "Customer creation");
    }

    @Then("I validate if customer name match in overview tab")
    public void validateCreatedCustomerName() {
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        String customerNameInHeader = overview.getCustomerNameFromHeader();
        result(fName, customerNameInHeader, "Created customer name ", "Customer creation");
    }

    @And("I navigate to Subscription Tab")
    public void navigateToSubscriptionTab() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        Utilities.waitUntileElementIsVisible("//li[@name = '" + customerDialog_Header.subscriptionTabInDialog + "']");
        customerDialog_Header.navigateTo(customerDialog_Header.subscriptionTabInDialog);
    }

    public void editCustomer_NoPaper_CommercialProperty() throws InterruptedException {
        customer = new CreateCustomerDialog();
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        customer.clickPrefersPaperCheckBox();
        customer.selectProperty(customer.commercialProperty);
        customerDialog_Header.clickSaveButton();
    }

    private void alertCondition() throws Exception {
        int i = 0;
        while (i++ < 5) {
            try {
                Alert alert = Utilities.alertPopUp();
                String actionAlert = Utilities.getAlertText();
                String expected = "Action Required!";
                if (actionAlert.contains(expected)) {
                    alert.accept();
                    Utilities.clickElement("//div[text()='Save Anyways']", ElementType.XPath);
                }
                break;
            } catch (NoAlertPresentException e) {
                Thread.sleep(500);
                continue;
            }
        }
    }

    private void captureUserIdAndFullName() throws Exception {
        String newId = overview.getCustomerIDFromHeader();
        addData("userID", newId, generalData);
        addData("customerName", fName + " " + lName, generalData);
    }

    public String getCustomerName(String previousCustomerNumber) throws Exception {
        header = new Header();
        header.searchCustomerInOrder(previousCustomerNumber);
        customerDialog_Header = new CustomerViewDialog_Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        return customerViewDialog_infoTab.getFirstName() + " " + customerViewDialog_infoTab.getLastName();
    }

    public String getCustomerFullName() {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        Utilities.waitUntileElementIsVisible(customerViewDialog_infoTab.state);
        return customerViewDialog_infoTab.getFirstName() + " " + customerViewDialog_infoTab.getLastName();
    }

    @Then("I remove the customer")
    public void removeCustomer() {
        header = new Header();
        customerDialog_Header = new CustomerViewDialog_Header();
        adminTab = new CustomerViewDialog_Admin();
        overview = new CustomerViewDialog_OverviewTab();
        header.searchCustomerWithName(customerName);
        customerDialog_Header.navigateTo(customerDialog_Header.adminTabInDialog);
        Utilities.waitUntileElementIsVisible(adminTab.removeButton);
        Utilities.clickElement(adminTab.removeButton, ElementType.XPath);
        Utilities.waitUntileElementIsVisible(adminTab.confirmRemoveButton);
        Utilities.clickElement(adminTab.confirmRemoveButton, ElementType.XPath);
        customerDialog_Header.discardChanges();
    }

    //Author FK
    public void searchCustomer_SearchField(String needSearchField) {
        createNewCustomer = new CreateNewCustomer();
        header = new Header();
        String convertedCustomerName = header.convertName(customerName);
        Utilities.clickElement(needSearchField, ElementType.XPath);
        FindElement.elementByAttribute(needSearchField, FindElement.InputType.XPath).sendKeys(convertedCustomerName);
    }

    //Author: FWhite
    @Given("I create customer with {string},{string},{string},{string},{string}, and {string}, if not already exist")
    public String createCustomerWithNameEmailAddrStreetAddrPhNumZipCode(String firstName, String lastName, String emailAddr, String phNum, String streetAddr, String zipCode) throws Exception {

        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        searchBox = new SearchBox();
        header.searchCustomer_SearchField(firstName + " " + lastName);

        //Separate Address parts
        String[] addressArray = streetAddr.split(",");
        String addrStreet = addressArray[0];
        String addrCity = addressArray[1];
        String addrStateAbbrev = addressArray[2];

        try {
            if (searchBox.containsInAutoCompleteSearch(firstName + " " + lastName).contains(firstName + " " + lastName)) {
                System.out.println("Customer found: " + firstName + " " + lastName);
                Utilities.clickElement("//li[@role='presentation']//span[contains(text(),'"+firstName + " " + lastName+"')]", ElementType.XPath);
                customerName =  firstName + " " + lastName;
            }
        }catch (Exception e){
            System.out.println("Creating customer: " + firstName + " " + lastName);
            header.navigateTo(header.newCustomerTab);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.selectUnit("Multi Unit");
            customer.setCellPhone(phNum);
            customer.setEmailAddress(emailAddr);
            customer.setAddress(addrStreet);
            customer.setZipCode(zipCode);
            customer.clickSmsCheckBox();
            customerDialog_Header.clickSaveButton();
            alertCondition();
            customerName = getCustomerFullName();

        }
        return customerName;
    }//createCustomerWithNameEmailAddrStreetAddrPhNumZipCode()

    @Given("I Create A Customer With Basic Information")
    public void createCustomerWithBasicInfo() {
        DashboardPage userOnDashboard = new DashboardPage();
        CreateCustomerDialog userCreateNewCustomer = new CreateCustomerDialog();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();

        userOnDashboard.goToNewCustomerComponent();
        userCreateNewCustomer.typeFirstName(fName);
        customerFirstName = userCreateNewCustomer.getCustomerFirstName();
        userCreateNewCustomer.typeLastName(lName);
        customerName = userCreateNewCustomer.getCustomerFullName();
        userCreateNewCustomer.typePropertyAddress(streetAddress);
        propertyAddress = userCreateNewCustomer.getPropertyAddress();
        userCreateNewCustomer.typeCity(city);
        userCreateNewCustomer.typeZipCode(zipcode);
        cityStateZip = userCreateNewCustomer.getCityStateZip();
        userCreateNewCustomer.typeEmailAddress(email);
        emailAddress = userCreateNewCustomer.getEmailAddress();
        sameUser.clickCustomerSaveButton();
    }

    @Given("I Create A Customer With A Subscription")
    public void automateCreatingCustomerWithSubscription() {
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        BillingPage userOnBillingTab = new BillingPage();
        AddSubscription testSubscription = new AddSubscription();

        createCustomerWithBasicInfo();
        sameUser.goToBillingTab();
        customerAccountID = userOnBillingTab.getCustomerAccountID();
        testSubscription.createNewSubscriptionWithBasicInfo();
    }
}
