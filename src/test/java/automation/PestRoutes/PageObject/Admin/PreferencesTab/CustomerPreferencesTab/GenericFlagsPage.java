package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GenericFlagsPage extends BasePage {

    private By genericFlagButton = By.xpath("//div[@id='newPreferenceBody']//div[text()='+ Generic Flag']");
    private By genericFlagCodeField = By.xpath("//div[@id='newPreferenceBody']//input[@name='code']");
    // //div[@id="newPreferenceBody"]//div[text()='Automation Trigger Rule']
    private By descriptionField = By.xpath("//div[@id='newPreferenceBody']//input[@name='description']");
    private By saveButton = By.xpath("//div[@id='newPreferenceBody']//div[text()='save']");
    private By genericFlagCodeColumnValues = By.xpath("//div[@id='newPreferenceBody']//li/form/div[1]");

    public void clickGenericFlagButton() {
        click(genericFlagButton);
    }

    public void typeGenericFlagCode(String genericFlagCode) {
        type(genericFlagCode, genericFlagCodeField);
    }

    public void typeGenericFlagDescription(String description) {
        type(description, descriptionField);
    }

    public void clickSave() {
        click(saveButton);
    }

    public void createCustomerGenericFlag(String flagCode, String flagDescription) {
        boolean genericFlagExist = isGenericFlagDisplayed(flagCode);

        if(genericFlagExist == false) {
            clickGenericFlagButton();
            typeGenericFlagCode(flagCode);
            typeGenericFlagDescription(flagDescription);
            clickSave();
        }
    }

    public boolean isGenericFlagDisplayed(String flagCode) {
        List<WebElement> allGenericFlags = findElements(genericFlagCodeColumnValues);

        for (WebElement genericFlag : allGenericFlags) {
            String convertedFlagCode = genericFlag.getText().replaceAll(".+\n", "");
            if (convertedFlagCode.equalsIgnoreCase(flagCode)) {
                return true;
            }
        }
        return false;
    }

    public String getGenericFlagCode() {
        return getText(genericFlagCodeField);
    }
}