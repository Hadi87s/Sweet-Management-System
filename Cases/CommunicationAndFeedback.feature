Feature: Communication and Feedback
  Actor: user

  Scenario: Directly communicate with store owner and suppliers
    Given  I am a user logged in to the Sweet System using username "User1" and password "123"
    When I have an inquiry or need assistance
    Then I should be able to send a message describing my problem with store owners and suppliers


  Scenario: Provide feedback on purchased products and shared recipes
    Given  I am a user logged in to the Sweet System using username "User1" and password "123"
    When I want to share my experience
    Then I should be able to provide my feedback



