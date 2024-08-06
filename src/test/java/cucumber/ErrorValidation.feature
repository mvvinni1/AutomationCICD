
@tag
Feature: Error Validation
  I want to use this template for my feature file
  
 Background:
Given I landed on Ecommerce page 

  @ErrorValidation
  Scenario Outline: Error message validation
  Given Logged in with username <name> and password <password>
  Then "Incorrect email or password." message is displayed

    Examples: 
      | name  											 | password 			 |
      | vinay@rahulshettyacademy.com | Vinay@rahul     |