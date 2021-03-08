package automation.PestRoutes.Controller.Equipment;

import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Equipment;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;

import java.util.ArrayList;
import java.util.List;

public class AddEquipment {

    CustomerViewDialog_Header customerDialogHeader;
    CustomerViewDialog_Equipment customerViewDialogEquipment;
    AssertException assertException;

    @And("I add new equipment with barcode required {string} and {string} and {string} and {string} and {string} and {string} " +
            "and {string} and {string} and {string}")
    public void addNewEquipmentWithBarcode(String needDescription, String needEquipmentType, String needEquipmentFlagType, String needDeviceID,
                                String needApplicationMethod, String needBarcode, String needEquipmentTargetArea, String needEquipmentTargetIssue,
                                String needNotes) throws InterruptedException {
        customerDialogHeader = new CustomerViewDialog_Header();
        customerViewDialogEquipment = new CustomerViewDialog_Equipment();

        customerDialogHeader.navigateTo(customerDialogHeader.equipmentTabInDialog);
        customerDialogHeader = new CustomerViewDialog_Header();
        customerViewDialogEquipment = new CustomerViewDialog_Equipment();
        customerViewDialogEquipment.clickButton(customerViewDialogEquipment.addEquipmentButton);
        customerViewDialogEquipment.setItem(customerViewDialogEquipment.addDescription, needDescription);
        customerViewDialogEquipment.selectItemFromDropDown(customerViewDialogEquipment.addEquipmentType, needEquipmentType);
        customerViewDialogEquipment.selectItemFromDropDown(customerViewDialogEquipment.addEquipmentFlag, needEquipmentFlagType);
        customerViewDialogEquipment.setItem(customerViewDialogEquipment.addDeviceId, needDeviceID);
        customerViewDialogEquipment.selectItemFromDropDown(customerViewDialogEquipment.addApplicationMethod, needApplicationMethod);
        customerViewDialogEquipment.selectItemFromDropDown(customerViewDialogEquipment.addEquipmentTargeArea, needEquipmentTargetArea);
        customerViewDialogEquipment.selectItemFromDropDown(customerViewDialogEquipment.addEquipmentTargetIssue, needEquipmentTargetIssue);
        customerViewDialogEquipment.setItem(customerViewDialogEquipment.addNotes, needNotes);
        customerViewDialogEquipment.clickButton(customerViewDialogEquipment.saveButton);
        if (needBarcode.length() > 0){
            Utilities.acceptAlert();
            customerViewDialogEquipment.setItem(customerViewDialogEquipment.addBarcode, needBarcode);
            customerViewDialogEquipment.clickButton(customerViewDialogEquipment.saveButton);
        }

    }

    @And("I verify that the equipment was added {string}")
    public void verifyEquipmentWithBarcodeAdded(String needEquipmentFlagType) throws InterruptedException {
        customerDialogHeader = new CustomerViewDialog_Header();
        customerViewDialogEquipment = new CustomerViewDialog_Equipment();
        assertException = new AssertException();

        customerDialogHeader.navigateTo(customerDialogHeader.equipmentTabInDialog);
        String expectedEquipmentFlagType = needEquipmentFlagType;
        String actualEquipmentFlagType = customerViewDialogEquipment.getItem();
        assertException.result(expectedEquipmentFlagType, actualEquipmentFlagType, "Validate equipment added", "add equipment");
    }
}
