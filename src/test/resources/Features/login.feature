Feature: logging in to amazon retail app

#  Background: setting up driver for execution
#    Given User setup driver instance for automation

  Scenario: Logging in functionality verification of amazon retail website
    Given User moves to amazon retail website
    And User moves to login page of amazon
    When User gives email for login as "it.choudhary.rahul@gmail.com"
    And User gives password for login as "Rahul@1991"
    Then User should be verified to be logged in successfully

  Scenario Outline: Logging in functionality with variety of invalid Logins
    Given User moves to amazon retail website
    And User moves to login page of amazon
    When User gives email for login as "<email>"
    And User gives password for login as "<password>"
    Then User should not be able to login with error message as "<errorMessage>"
    Examples:
      |email|password|errorMessage|
      |it.choudhary.rahul@gmail.com|Test@123|Your password is incorrect|
      |abc@gmail.com               |random@123|Your password is incorrect|

  Scenario Outline: Try login with invalid email Address
    Given User moves to amazon retail website
    And User moves to login page of amazon
    When User gives email for login as "<email>"
    Then User should not be able to login with error message as "<errorMessage>"
    Examples:
      |email|errorMessage|
      |testinggmail|We cannot find an account with that email address|

  Scenario: Testing of dataProvider
    Given User searches for dataprovider
    |Fruit|Colour|
    |Orange|Orange|
    |Apple |Red   |

