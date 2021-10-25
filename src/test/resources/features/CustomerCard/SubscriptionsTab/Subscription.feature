#Author Aarbi
#@Smoke
Feature: Subscription tab

  Scenario: Validate subscription upcoming appointments
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    When I start a regular subscription
    Then I validate upcoming appointments per each day
    And I validate if there are errors exist in the list

#  Duplicate
#  Scenario: Validate initial invoice
#  	When I start a regular subscription
#  	Then I validate initial invoice
#  	And I validate if there are errors exist in the list