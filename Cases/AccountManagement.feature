Feature: Account Management
  Actors: Store Owner, Raw Material Supplier

  Scenario: View account details
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I request to view my account details
    Then I should see my account details including username, email, and business information

  Scenario: Update account details
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I update my email to "HadiIrshaid8722@gmail.com" and password to "newpassword123"
    Then my account details should be updated with the new email and password
    And a confirmation message should appear

  Scenario: Update business information
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I update my business name to "New Business Name" and address to "123 New Address, City, Country"
    Then my business information should be updated
    And a confirmation message should appear

  Scenario: Handle invalid update attempts
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I attempt to update my email to an invalid format "invalid-email"
    Then I should receive an error message indicating that the email format is invalid

  Scenario: Change account password
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I change my password to "newpassword123"
    Then my password should be updated successfully
