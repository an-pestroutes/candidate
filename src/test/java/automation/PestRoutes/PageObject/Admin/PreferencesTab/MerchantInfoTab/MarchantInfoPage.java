package automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;

import java.util.Locale;

public class MarchantInfoPage extends BasePage {

    //**Default vault settings
    public String defaultSettingsEditButton = "//form[@name='defaultSettings']/div[text()='edit']";
    public String defaultSettingsSaveButton = "//form[@name='defaultSettings']/div[text()='save']";
    public String defaultPaymentGatewayValue = "//select[@name='paymentGateway']/preceding-sibling::span[1]";
    public String ccGatewaysDropdown = "//select[@name='paymentGateway']";
    private By dropdwnCreditCardGateWay= By.xpath("//select[@name='paymentGateway']");
    public String achGatewaysDropdown = "//select[@name='achGateway']";
    private By editDefaultSettings = By.xpath("//form[@name='defaultSettings']/div[text()='edit']");
    private By saveDefaultSettings = By.xpath("//form[@name='defaultSettings']/div[text()='save']");
    private By creditCardGatewayDropDown = By.xpath("//div[@id='merchantInfo']//select[@name='paymentGateway']");

     public final String GATEWAY_BRAINTREE = "Braintree";
    public final String GATEWAY_ELEMENT = "Element";
    public final String GATEWAY_SPREEDLY = "Spreedly";
    public final String GATEWAY_NMI = "NMI";
    public final String GATEWAY_PESTROUTES_PAYMENTS = "PestRoutes Payments";


    public void select(String needDropdown, String needText){
        Utilities.selectValueFromDropDownByValue(needDropdown, needText);
    }

    public void click(String needElement){
        Utilities.clickElement(needElement, Utilities.ElementType.XPath);
    }

    public void clickEditForDefaultSettings(){
        click(editDefaultSettings);
    }

    public void selectCreditCardGateway(String creditCardGateway){
        selectFromDropDown(creditCardGateway, creditCardGatewayDropDown);
    }

    public void clickSaveForDefaultSettings(){
        click(saveDefaultSettings);
    }

    public String getDefaultCreditCardGateway(){
        String  merchantGateway = find(dropdwnCreditCardGateWay).getAttribute("value").toUpperCase(Locale.ROOT);
        String  defaultConfiguredGateway;

        switch (merchantGateway){
            case "BRAIN":
                 defaultConfiguredGateway = GATEWAY_BRAINTREE;
                break;
            case "ELEMENT":
                defaultConfiguredGateway = GATEWAY_ELEMENT;
                break;
            case "SPREEDLY":
                defaultConfiguredGateway = GATEWAY_SPREEDLY;
                break;
            case "NMI":
                defaultConfiguredGateway = GATEWAY_NMI;
                break;
            case "PAYRIX":
                defaultConfiguredGateway = GATEWAY_PESTROUTES_PAYMENTS;
                break;
            default:
                defaultConfiguredGateway ="UNKNOWN GATEWAY";
        }
        return  defaultConfiguredGateway;

    }//getDefaultCreditCardGateway()
}
