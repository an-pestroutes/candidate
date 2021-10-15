#Author: Aditya
@Smoke
@BillingByServiceType
@RegressionADI_BST
Feature: Billing by service Type report end to end validation

  @Smoke
  @BSTFilterFields
  Scenario: Fields validation BST
    Given I navigate to Report of type "Billing by Service Type"
    Then I validate if all fields are displaying and are enabled in Billing by service type
    And I validate if there are errors exist in the list

  @Smoke
  @BSTReportFields
  Scenario: Validating filters and fields generated with single group by and its line items BST
    Given I add a new generic flag if it is not already existing "Automation Flag" and "Its lit" and "Customer"
    Given I add a new customer source if it is not already existing
    Given I add a new division if it is not already existing
    When I create customer with pref paper and residential property
    Then I get customer name and customer ID details for Billing by service type report
    And I add properties invoice type, service type, customer source, divisions, include flags and date range to customer
    And I create a subscription of type "After Agreement Signed"
    And I sign the agreement for subscription of type After Agreement Signed
    When I navigate to Report of type "Billing by Service Type"
    And I group by "Customer Name"
    And I add filters invoice type, service type, customer source, divisions, include flags and date range
    When I search for the billing customer
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTPrefPaperAndPropertyType
  Scenario: Prefer Paper and Property Type validation BST
    When I create customer with balance with prefers paper and residential property type
    And I search for customer with pref paper and residential property in "Billing by Service Type"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    When I edit customer to commercial property and not require paper
    And I search for customer without pref paper and commercial property in "Billing by Service Type"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTStandAlone
  Scenario: Balance Age validation BST with StandAlone Invoices
    When I validate customer with balance age in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTSoldFilter
  Scenario: Sold filters validation BST
    Given I delete a routing group
    Given I create a new user if it is not already existing "Office Staff"
    When I create customer with pref paper and residential property
    Then I get customer name and customer ID details for Billing by service type report
    And I create a subscription with Sales Rep assigned "Automation User - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I set filter for sold date, scheduler, sale teams, sales reps and service invoice in "Billing by Service Type"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTCreditMemo
  Scenario: Credit memo validation is BST
    When I create customer with balance with prefers paper and residential property type
    And I create a credit memo for an existing invoice
    And I search credit memo customer on "Billing by Service Type"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTStandAloneAndWriteOff
  Scenario: Make payment with Coupon or Credit and validate in BST
    When I create customer with balance with prefers paper and residential property type
    And I validate all the fields in the Coupons or Credit window
    And I validate if amounts are displayed correctly in coupons or credits
    Then I make a coupon or credit payment
    And I search standalone write off customer on the BST report
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @Smoke_Adi
  @BSTMultiGroupByValidation
  Scenario: Multi Group By filter validation in BST
    When I create customer with balance with prefers paper and residential property type
    And I set group by and subgroups to Billing Frequency, Customer ID and Invoice in "Billing by Service Type"
    Then I validate Billing or Payment by service type report generated from Billing Frequency, Customer ID and Invoice
    And I validate the fields generated in multi group by billing by service type report
    Then I search customer in billing frequency line item
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTAutoPayCCorBothFields
  Scenario: AutoPay validation for CC and CC or ACH in BST
    When I create customer with balance with prefers paper and residential property type
    Then I get customer name and customer ID details for Billing by service type report
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I add an customer in auto pay with "CC" and max limit "400"
    And I search customer on the "Billing by Service Type" with AutoPay as "Card AutoPay"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I search customer on the "Billing by Service Type" with AutoPay as "Card or ACH AutoPay"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTAutoPayACHorBothFields
  Scenario: AutoPay validation for ACH and CC or ACH in BST
    When I create customer with balance with prefers paper and residential property type
    And I add an ACH payment option
    And I add an customer in auto pay with "ACH" and max limit "400"
    And I search customer on the "Billing by Service Type" with AutoPay as "ACH AutoPay"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I search customer on the "Billing by Service Type" with AutoPay as "Card or ACH AutoPay"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTStatusPaymentStatusACHAutoPay
  Scenario: AutoPay validation for ACH in BST with Successful payment status
    When I create customer with balance with prefers paper and residential property type
    And I add an ACH payment option
    And I add an customer in auto pay with "ACH" and max limit "400"
    And I search customer on the "Billing by Service Type" with AutoPay as "ACH AutoPay" and Payment Status as "Successful"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list

  @BSTStatusPaymentStatusCCAutoPay
  Scenario: AutoPay validation for CC in BST with Successful payment status
    When I create customer with balance with prefers paper and residential property type
    Then I get customer name and customer ID details for Billing by service type report
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I add an customer in auto pay with "CC" and max limit "400"
    And I search customer on the "Billing by Service Type" with AutoPay as "Card AutoPay" and Payment Status as "Successful"
    Then I validate the fields generated by billing by service type report
    Then I validate if the billing by service type report is linked to the customer card
    And I validate data generated from billing by service type report
    And I validate line item data in Billing by service type report
    And I validate the fields are displayed in individual line items in Billing by Service Type
    And I validate if there are errors exist in the list