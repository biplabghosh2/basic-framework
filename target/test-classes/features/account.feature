Feature: Account feature

@Account
Scenario Outline: Check account should create with dafult accoune type
Given User is on login page
When User enters "<username>" and "<password>"
And click on login button
And Create a new account with default account type
Then Account should created with account type Savings

Examples:
|username|password|
|mngr570407|epYmyry|