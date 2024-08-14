Feature: Communication and Notification
  Actors: Store Owner, Raw Material Supplier, User

  Scenario: Use the messaging system to communicate with users and other suppliers
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I send a message to user "User1" with the content "Your order is ready for pickup"
    Then the user "User1" should receive the message with the content "Your order is ready for pickup"

  Scenario: Use the messaging system to communicate with other suppliers
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I send a message to supplier "Supplier1" with the content "Please deliver 50kg of sugar"
    Then the supplier "Supplier1" should receive the message with the content "Please deliver 50kg of sugar"

  Scenario: Receive notifications for special requests
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    And I have enabled email notifications
    When a special request is made by user "User1" to store owner "StoreOwner1"
    Then I should receive an email notification with the content "Special request made by User1"
