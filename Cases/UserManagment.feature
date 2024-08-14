Feature: its an Admin feature to control adding a new Store owner or a new raw material Supplier
  Actor: Admin


  Scenario: Adding a Store Owner
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I add a store owner with a username "StoreOwner1" and a password "SO1" and an email "storeOwner1@example.com"
    Then the store owner should be added to the Sweet System
    And a confirmation message will appear


  Scenario: Removing a Store Owner
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    And a store owner with username "StoreOwner1" exists in the Sweet System
    When I remove the store owner with a username "StoreOwner1"
    Then the store owner should be removed from the Sweet System
    And a confirmation message will appear

  Scenario: Adding a Supplier
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I add a supplier with a username "Supplier1" and a password "RMS1" and an email "supplier1@example.com"
    Then the supplier should be added to the Sweet System
    And a confirmation message will appear


  Scenario: Removing a Supplier
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    And a supplier with username "Supplier1" exists in the Sweet System
    When I remove a supplier with a username "Supplier1"
    Then the supplier should be removed from the Sweet System
    And a confirmation message will appear

  Scenario: View all users
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I view the user list
    Then I should see the list of all store owners and raw material suppliers and normal users