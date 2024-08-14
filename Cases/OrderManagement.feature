Feature: Order Management
  Actors: Store Owner, User

  Scenario: Process a new order
    Given I am a user logged in to the Sweet System using username "User1" and password "123"
    When I place an order for the product "Chocolate Cake" with quantity 2 and an ID "12345"
    Then the order should be processed
    And the order status should be "Processed"


  Scenario: Update order status
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    And an order with order ID "12345" exists
    When I update the status of order "12345" to "Shipped"
    Then the order status should be "Shipped"

  Scenario: Cancel an order
    Given I am a user logged in to the Sweet System using username "User1" and password "123"
    And I have placed an order with order ID "12345"
    When I cancel the order "12345"
    Then the order status should be "Cancelled"
