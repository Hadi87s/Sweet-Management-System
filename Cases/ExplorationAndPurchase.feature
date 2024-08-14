Feature: Exploration and Purchase
  Actor: user

  Scenario: Browse and search for dessert recipes
    Given  I am a user logged in to the Sweet System using username "User1" and password "123"
    When I want to explore dessert options
    Then I should be able to browse and search for dessert recipes
    And List of dessert recipes I'm looking for should appear.

  Scenario: Filter recipes based on dietary needs or food allergies
    Given  I am a user logged in to the Sweet System using username "User1" and password "123"
    When I have specific dietary needs or food allergies
    Then I should be able to filter recipes based on those requirements
    And List of dessert recipes I'm looking for should appear.

  Scenario: Purchase desserts directly from store owners
    Given  I am a user logged in to the Sweet System using username "User1" and password "123"
    When I want to buy dessert
    Then I should be able to purchase dessert directly from store owners
