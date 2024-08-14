Feature: Monitoring and Reporting
  Actor: Admin

  Scenario: Monitor profits and generate financial reports
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I generate a financial report
    Then I should see the total profits


  Scenario: Identify best-selling products in each store
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I request a report of best-selling products
    Then I should see the list of best-selling products in each store
    And the quantity sold for each product should be displayed

  Scenario: Gather and display statistics on registered users by City
    Given I am an admin logged in to the Sweet System using username "Admin" and password "Admin"
    When I request statistics on registered users by city
    Then I should see the number of registered users in each city