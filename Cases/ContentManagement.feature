Feature: Content Management
  Actor: Admin

  Scenario: Manage recipes and posts
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I request to see the content
    Then I should see a list of all recipes and posts
    And I should be able to add a new recipe
    And I should be able to edit an existing recipe
    And I should be able to delete an existing recipe
    And I should be able to add a new post
    And I should be able to edit an existing post
    And I should be able to delete an existing post

  Scenario: Manage user feedback
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I request to see the users feedback
    Then I should see a list of all users feedback
