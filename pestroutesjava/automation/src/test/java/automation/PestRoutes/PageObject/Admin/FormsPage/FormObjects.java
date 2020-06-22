package automation.PestRoutes.PageObject.Admin.FormsPage;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class FormObjects {
	
	public String addFormButton = "//div[text() = '+ Form Template']";
	public String htmlButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='html']";
	public String previewButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='preview']";
	public String deleteButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='delete']";
	public String cancelButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='cancel']";
	public String saveButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='save']";
	
	//Form objects
	public String nameField = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='description']";
	public String requiredApprovalDropdown = "//div[@id='preferenceHeader']/following-sibling::form//select[@name='requireApproval']";
	public String htmlInputField = "//div[@id='preferenceHeader']/following-sibling::form//textarea[@name = 'content']";
	
	
	
	
	public void clickButton(String needAttribute) {
		Utilities.waitUntileElementIsVisible(needAttribute);
		Utilities.clickElement(needAttribute, ElementType.XPath);
	}
	public void clickEditButton(String needFormName) {
		Utilities.waitUntileElementIsVisible("//input[@value = '"+needFormName+"']/following-sibling::div[text() = 'edit']");
		Utilities.clickElement("//input[@value = '"+needFormName+"']/following-sibling::div[text() = 'edit']", ElementType.XPath);
	}
	public void selectValueFromDropdown(String needValue) {
		Utilities.selectValueFromDropDownByValue(requiredApprovalDropdown, needValue);
	}
	public void setInputField(String needInputField, String needValue) {
		Utilities.waitUntileElementIsVisible(needInputField);
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(needValue);
	}

}