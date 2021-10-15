#Author: Aditya
@Regression
@PaymentsByServiceType
@RegressionADI_PST
Feature: Payments by service Type report end to end validation

  @Smoke
  @PSTFilterFields
  Scenario: Fields validation PST
    Given I navigate to Report of type "Payments by Service Type"
    Then I validate if all fields are displaying and are enabled in Payments by service type
    And I validate if there are errors exist in the list

  @PSTReportFields
  Scenario:Validating filters and fields generated with single group by and its line items PST
    Given I add a new generic flag if it is not already existing "Automation Flag" and "Its lit" and "Customer"
    Given I add a new customer source if it is not already existing
    Given I add a new division if it is not already existing
    When I create customer with pref paper and residential property
    And I get customer name and customer ID details for Payments by service type report
    And I add properties invoice type, service type, customer source, divisions, include flags and date range to customer
    And I create a subscription of type "After Agreement Signed"
    And I sign the agreement for subscription of type After Agreement Signed
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    When I navigate to Report of type "Payments by Service Type"
    And I group by "Customer Name"
    And I add filters invoice type, service type, customer source, divisions, include flags and date range
    When I search for the billing customer
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @PSTPrefPaperAndPropertyType
  Scenario: Prefer Paper and Property Type validation PST
    When I create customer with balance with prefers paper and residential property type
    And I get customer name and customer ID details for Payments by service type report
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    And I search for customer with pref paper and residential property in "Payments by Service Type"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    When I edit customer to commercial property and not require paper
    And I search for customer without pref paper and commercial property in "Payments by Service Type"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @PSTStandAloneWithPartialPayment
  Scenario: Balance Age validation PST with StandAlone Invoices
    When I validate customer with balance age in Payments by Service Type
    And I validate if there are errors exist in the list

  @PSTCreditMemo
  Scenario: Credit memo validation in PST
    When I create customer with balance with prefers paper and residential property type
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    Then I make partial payment with credit card on file
    And I create a credit memo for an existing invoice
    And I get customer name and customer ID details for Payments by service type report
    And I search "Stand-Alone Invoices" invoice on "Payments by Service Type"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @Smoke_Adi
  @PSTMultiGroupByValidation
  Scenario: Multi Group By filter validation in PST
    When I create customer with balance with prefers paper and residential property type
    And I get customer name and customer ID details for Payments by service type report
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    And I set group by and subgroups to Billing Frequency, Customer ID and Invoice in "Payments by Service Type"
    Then I validate Billing or Payment by service type report generated from Billing Frequency, Customer ID and Invoice
    Then I validate the fields in multi group generated by payments by service type report
    Then I search customer in payment frequency line item
    And I validate data generated from payments by service type report in multi groups
    And I validate line item data in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @PSTAutoPayCCorBothFields
  Scenario: AutoPay validation for CC and CC or ACH in PST
    When I create customer with balance with prefers paper and residential property type
    And I get customer name and customer ID details for Payments by service type report
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I add an customer in auto pay with "CC" and max limit "400"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    And I search customer on the "Payments by Service Type" with AutoPay as "Card AutoPay"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    And I search customer on the "Payments by Service Type" with AutoPay as "Card or ACH AutoPay"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @PSTAutoPayACHorBothFields
  Scenario: AutoPay validation for ACH and CC or ACH in PST
    When I create customer with balance with prefers paper and residential property type
    And I get customer name and customer ID details for Payments by service type report
    And I add an ACH payment option
    And I add an customer in auto pay with "ACH" and max limit "400"
    Then I make payment with ACH Account on file
    And I search customer on the "Payments by Service Type" with AutoPay as "ACH AutoPay"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from ACH payments by service type report
    And I validate line item data for ACH payments in Payments by service type report
    And I search customer on the "Payments by Service Type" with AutoPay as "Card or ACH AutoPay"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from ACH payments by service type report
    And I validate line item data for ACH payments in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @PSTStatusPaymentStatusCCAutoPay
  Scenario: AutoPay validation for CC in PST with Successful payment status
    When I create customer with balance with prefers paper and residential property type
    And I get customer name and customer ID details for Payments by service type report
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I add an customer in auto pay with "CC" and max limit "400"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    And I search customer on the "Payments by Service Type" with AutoPay as "Card AutoPay" and Payment Status as "Successful"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @PSTStatusPaymentStatusACHAutoPay
  Scenario: AutoPay validation for ACH in PST with Successful payment status
    When I create customer with balance with prefers paper and residential property type
    And I get customer name and customer ID details for Payments by service type report
    And I add an ACH payment option
    And I add an customer in auto pay with "ACH" and max limit "400"
    Then I make payment with ACH Account on file
    And I search customer on the "Payments by Service Type" with AutoPay as "ACH AutoPay" and Payment Status as "Successful"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from ACH payments by service type report
    And I validate line item data for ACH payments in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

  @PSTSoldFilter
  Scenario: Sold filters validation PST
    Given I delete a routing group
    Given I add a renewal service
    Given I create a new user if it is not already existing "Office Staff"
    When I create customer with pref paper and residential property
    And I create a subscription with Sales Rep assigned "Automation User - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I close customer card
    And I search customer
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I get customer name and customer ID details for Payments by service type report
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    And I set filter for sold date, scheduler, sale teams, sales reps and service invoice in "Payments by Service Type"
    Then I validate the fields generated by payments by service type report
    Then I validate if the payments by service type report is linked to the customer card
    And I validate data generated from payments by service type report
    And I validate line item data in Payments by service type report
    And I validate the fields are displayed in individual line items in Payment by Service Type
    And I validate if there are errors exist in the list

    #105625 ticket
  @PSTTechnicianGroupByNoTech
  Scenario: Technician group by validation PST with No Tech
    When I create customer with balance with prefers paper and residential property type
    And I get customer name and customer ID details for Payments by service type report
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    Then I set technician as group by in "Payments by Service Type"
    Then I click technician "No Tech" assigned to the invoice
    And I search customer in payment frequency line item
    And I validate the fields are displayed in individual line items in Payment by Service Type

    #105625 ticket
  @PSTTechnicianGroupByWithTech
  Scenario: Technician group by validation PST with Tech
    Given I delete a routing group
    Given I add a renewal service
    Given I create a new user if it is not already existing "Technician"
    When I create customer with first name, last name, email and address
    And I create a subscription of type "After Initial Completion"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    Then I add the technician on the appointment
    And I close customer card
    And I search customer
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    And I get customer name and customer ID details for Payments by service type report
    Then I set technician as group by in "Payments by Service Type"
    And I search for the billing customer
    Then I search and click on the technician assigned to the appointment in PST
    And I search customer in payment frequency line item
    And I validate the fields are displayed in individual line items in Payment by Service Type
