@AfterInitialCompletion
Feature: Customer Status

  @CreateInitialInvoiceAfterInitialCompletion
  Scenario: Create Initial Invoice of type After Initial Completion
    Given I sign in to pestroutes domain
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Initial Completion"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    And I validate initial invoice created on invoice tab
    And I validate if there are errors exist in the list