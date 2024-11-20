#Author: Biplab Ghosh
#Description: These test cases to validate login page of BOB

Feature: Login Guru99 bank page

Scenario Outline: Check login is successful with valid credential
Given User is on login page
When User enters "<username>" and "<password>"
And click on login button
Then User is navigated to Home Page

Examples:
|username|password|
|mngr570407|epYmyry|

@Smoke
Scenario Outline: Check login is successful with invalid credential
Given User is on login page
When User enters "<username>" and "<password>"
And click on login button
Then User received error message

Examples:
|username|password|
|mngr570407|epYmyry77|
|mr5704071|epYmyry|

