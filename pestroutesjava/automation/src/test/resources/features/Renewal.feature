#Author Aarbi
@Renewal
Feature: Renewal

  Scenario: Validate Renewal
    Given I sign in to pestroutes domain
    Given I delete a routing group
    Given I add a renewal service
    When I create customer with first name, last name and address
    And I validate if renewal fields display in Subscription tab if I choose renewal as service type
    And I create a renewal subscription
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    Then I validate if the renewal date has posted
    And I freeze the subscription
    And I get the subscription total
    And I navigate to Subscription Tab
    And I search customer
    And I pay the subscription
    And I search customer
    Then I validate if renewal date and account status changed
    Given I close customer card
    And I validate if there are errors exist in the list

  Scenario: Close browser
    And I quit driver