# Author: Rex Jones II
# AR Trigger Rule
#@RegressionREX
@TriggerRule
@TriggerRules
@AR_TriggerRule

Feature: AR Trigger Rule

  AR is an acronym for Account Receivables.
  AR Trigger Rule is a rule that automatically
  As a user, I want the AR Trigger Rule to be a rule that automatically
  runs to perform an action. It has 2 parts Filter/Condition and Action.
  If a condition happens then the rule performs an action.

  @VerifyAgeSendEmailForTriggerRuleAR
  Scenario: Verify AR Age Sends Email To A Customer
    Given I Set Up A Customer "AR Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "AR" Trigger Type That Has "Age" Filter With "No Extra" Filter
    And   I Complete An Action To "Send Email" With "Email Statement" Details
    When  I Add "AR Automation Trigger Rule" Flag To The Customer With A New Invoice
    And   I Execute Trigger "triggerEvents"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyDaysPastDueSendSMSForTriggerRuleAR
  Scenario: Verify AR Days Past Due Sends SMS To A Customer
    Given I Set Up A Customer "AR Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "AR" Trigger Type That Has "Days Past Due" Filter With "No Extra" Filter
    And   I Complete An Action To "Send SMS" With "No" Details
    When  I Add "AR Automation Trigger Rule" Flag To The Customer With A New Invoice
    And   I Execute Trigger "triggerEvents"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyAgeSendVoiceForTriggerRuleAR
  Scenario: Verify AR Age Sends Voice Message To A Customer
    Given I Set Up A Customer "AR Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "AR" Trigger Type That Has "Age" Filter With "No Extra" Filter
    And   I Complete An Action To "Send Voice" With "New Message" Details
    When  I Add "AR Automation Trigger Rule" Flag To The Customer With A New Invoice
    And   I Execute Trigger "triggerEvents"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger

  @VerifyDaysPastDueFreezeCustomersForTriggerRuleAR
  Scenario: Verify AR Days Past Due Freezes A Customer
    Given I Set Up A Customer "AR Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "AR" Trigger Type That Has "Days Past Due" Filter With "Maximum Balance" Filter
    And   I Complete An Action To "Freeze Customers" With "No Contact" Details
    When  I Add "AR Automation Trigger Rule" Flag To The Customer With A New Invoice
    And   I Execute Trigger "triggerEvents"
    Then  I Verify The Customer Received "No Contact" Note After Executing The Trigger

  @VerifyDaysPastDueAddFlagsForTriggerRuleAR
  Scenario: Verify AR Days Past Due Adds A Flag To The Customer
    Given I Set Up A Customer "AR Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "AR" Trigger Type That Has "Days Past Due" Filter With "Minimum Balance" Filter
    And   I Complete An Action To "Add Flags" With "Automation Flag" Details
    When  I Add "AR Automation Trigger Rule" Flag To The Customer With A New Invoice
    When  I close customer card
    And   I Execute Trigger "triggerEvents"
    Then  I Verify The Customer Received "Automation Flag" After Executing The Trigger