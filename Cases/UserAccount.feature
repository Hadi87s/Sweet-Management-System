Feature: User Account Management

  Scenario: Manage personal account details
    Given I am a user logged in to the Sweet System using username "User1" and password "123"
    When I update my personal account information with email "newemail@example.com" and address "123 New Street"
    Then my account information should be updated successfully
    And the email should be "newemail@example.com"
    And the address should be "123 New Street"

  Scenario: Post a new personal dessert creation
    Given I am a user logged in to the Sweet System using username "User1" and password "123"
    When I post a new dessert creation with title "Chocolate Cake" and description "Delicious homemade chocolate cake"
    Then the dessert creation should be posted successfully
    And the title should be "Chocolate Cake"
    And the description should be "Delicious homemade chocolate cake"

  Scenario: Share a personal dessert creation
    Given I am a user logged in to the Sweet System using username "User1" and password "123"
    And I have posted a dessert creation with title "Chocolate Cake" and description "Delicious homemade chocolate cake"
    When I share the dessert creation with username "User2"
    Then the dessert creation should be shared successfully
