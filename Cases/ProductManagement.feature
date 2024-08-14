Feature: Product Management
  Actors: Store Owner, Raw Material Supplier

  Scenario: Add a new product
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I request to see the products in the store
    And I add a new product with the name "Chocolate Cake", description "Delicious chocolate cake"
    Then the product should be added to the available products

  Scenario: Update an existing product
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    And a product with the name "Chocolate Cake" exists
    When I update the product "Chocolate Cake" with the new description "Rich chocolate cake"
    Then the product details should be updated

  Scenario: Remove an existing product
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    And a product with the name "Chocolate Cake" exists
    When I remove the product "Chocolate Cake"
    Then the product should be removed from the available products

  Scenario: Monitor sales and profits
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I request the sales and profits
    Then I should see a report of sales and profits for my products

  Scenario: Identify best-selling products
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    When I request to see the best-selling product
    Then I should see a the best selling product in my store

  Scenario: Implement dynamic discount features
    Given I am a store owner logged in to the Sweet System using username "StoreOwner1" and password "SO1"
    Then the product "Chocolate" should have a discount applied
