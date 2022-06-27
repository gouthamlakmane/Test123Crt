Feature: Creating Sub-Contracting order > Stock Issue > Stock Receipt and posting the invoice for the Same order

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
Scenario: Create Sub-Contracting Order
Given Create new Sub-Contracting order

Scenario: Create Stock Issue 
Given Create Stock Issue for Sub-con order

Scenario: Create Stock Receipt 
Given Create Stock Receipt for Sub-con order

Scenario: Validation of Sub-Contracting order status
Given Validating the order status

Scenario: Posting the Invoice
Given Posting the Invoice for created order