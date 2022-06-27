Feature: STO_TC06 - Transfer the goods between  same branch facilities, full Shipment.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@STO_TC05-01
Scenario: Create Stock transfer Order - STO_TC05
Given Create New Stock transfer Order - STO_TC_Five_One
Then Add Shipping Details - STO_TC_Five_One
Then Add Order Items - STO_TC_Five_One
Then Review & Create STO - STO_TC_Five_One
Then Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_Five_One

@STO_TC05-02	
Scenario: Create a Shipment - STO_TC05
Given  Create Shipment One - STO_TC_Five_Two
Then Update the shipment status - STO_TC_Five_Two
Then capture invoice id - STO_TC_Five_Two

@STO_TC05-03
Scenario: Update and post the Stock transfer Note - STO_TC05
Given Invoice Posting for Generated Invoices - STO_TC_Five_three

@STO_TC05-04
Scenario: Receive the Transfer Incoming Shipment - STO_TC05
Given Search the Order No - STO_TC_Five_four
Then Create the incoming Shipment - STO_TC_Five_four
Then Receive the inventory - STO_TC_Five_four 

@STO_TC05-05
Scenario: Transactions Validations under Finance Module - STO_TC05
Given Validate Accounting transactions under Finance Module - STO_TC_Five_Five