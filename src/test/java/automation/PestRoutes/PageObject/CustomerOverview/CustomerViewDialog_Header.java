package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.Leads.LeadsPage;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerViewDialog_Header extends BasePage {

    public String overviewTabInDialog = "overviewTab";
    public String infoTabInDialog = "infoTab";
    public String subscriptionTabInDialog = "subscriptionTab";
    public String leadsTabInDialog = "leadsTab";
    public String billingTabInDialog = "billingTab";
    public String notesTabInDialog = "notesTab";
    public String documentsTabInDIalog = "documentsTab";
    public String appointmentsTabInDialog = "appointmentsTab";
    public String invoicesTabInDialog = "invoicesTab";
    public String propertiesTabInDialog = "linkedPropertiesTab";
    public String unitsTabInDialog = "unitsTab";
    public String equipmentTabInDialog = "equipmentsTab";
    public String adminTabInDialog = "adminTab";
    public String structuresTabInDialog = "structuresTab";
    public String tranferButtonInDialog = "//span[text()='Transfer']";
    public String closeButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Close']";
    public String saveButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Save']";
    private By customerSaveButton = By.xpath("//div[@id= 'customerWindow']/following-sibling::div//span[text()='Save']");
    public String closeXButton = "//span[@id= 'ui-id-11']/parent::div/button/span";
    private By xButton = By.xpath("//span[@id='ui-id-11']/parent::div/button[@role='button']");
    public String discardChange = "//span[text()='Discard Changes']";
    public String saveAnyways = "//span[text()='Save Anyways']";
    private By saveChangesButton = By.xpath("//span[text()='Save Changes']");

    //******************** TABS ********************
    private By subscriptionTab = By.xpath("//li[@name='subscriptionTab']/a[text()='Subscription']");
    private By leadsTab = By.xpath("//a[text()='Leads']");
    private By appointmentsTab = By.xpath("//a[text()='Appointments']");
    private By invoicesTab = By.xpath("//a[text()='Invoices']");

    //Notes tab objects
    public String customerContacts_Notes = "//li[text()='Customer Contacts']";
    WebDriverWait wait = new WebDriverWait(driver, 55);

    public void clickCustomerContactsInNotesTab() {
        Utilities.waitUntileElementIsVisible(customerContacts_Notes);
        Utilities.clickElement(customerContacts_Notes, ElementType.XPath);
    }

    public void navigateTo(String chooseTabFromConst) throws InterruptedException {
        Thread.sleep(800);
        Utilities.waitUntileElementIsVisible("//li[@name= '" + chooseTabFromConst + "']");
        Utilities.clickElement("//li[@name = '" + chooseTabFromConst + "']", ElementType.XPath, true, false);
        Thread.sleep(100);
    }

    public void ClickTranferButton() {
        Utilities.clickElement(tranferButtonInDialog, ElementType.XPath);
    }

    public void clickSaveButton() throws InterruptedException {
        Utilities.clickElement(saveButton, ElementType.XPath);
        Thread.sleep(400);
        //Optimized For Encapsulation Below via clickCustomerSaveButton() Using A Private Modifier With By Class
    }

    public void clickCustomerSaveButton () { click(customerSaveButton); }

    public void clickCloseButton() throws InterruptedException {
        Thread.sleep(500);
        Utilities.waitUntileElementIsVisible(closeButton);
        Utilities.clickElement(closeButton, ElementType.XPath);
    }

    public void Click_X_Button() {
        Utilities.clickElement(closeXButton, ElementType.XPath);
        // The XPath for WebElement closeXButton In This Method Returned 2 Values Due To The span tag
        // Optimized For Encapsulation Below via clickXButton() Using A Private Modifier With By Class
    }

    public void clickXButton() { click(xButton); }
//Author Aarbi
    @And("If discard changes dialog displays, I discard unsaved changes")
    public void discardChanges() {
        try{
            if (Utilities.elementIsVisible(discardChange)) {
                Utilities.clickElement(discardChange, ElementType.XPath);
                Thread.sleep(500);
            }
        }catch (Exception e){

        }
    }

    public void saveAnyways() {
        if (Utilities.elementIsVisible(saveAnyways)) {
            Utilities.clickElement(saveAnyways, ElementType.XPath);
        }
    }

    public Boolean clickSaveChangesButton () {
        if (driver.findElements(saveChangesButton).size() > 0) {
            click(saveChangesButton);
            return true;
        }
        else {
            return false;
        }
    }

    public void switchToCustomerCard() {
        driver.switchTo().defaultContent();
    }

    public CustomerViewDialog_SubscriptionTab goToSubscriptionTab () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionTab));
        click(subscriptionTab);
        return new CustomerViewDialog_SubscriptionTab();
    }

    public CustomerviewDialog_AppointmentsTab goToAppointmentsTab () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(appointmentsTab));
        click(appointmentsTab);
        return new CustomerviewDialog_AppointmentsTab();
    }

    public RoutePageInvoicing goToInvoicesTab () throws InterruptedException {
        Thread.sleep(3000);
        click(invoicesTab);
        return new RoutePageInvoicing();
    }

    public LeadsPage goToLeadsTab () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(leadsTab));
        click(leadsTab);
        return new LeadsPage();
    }
}
