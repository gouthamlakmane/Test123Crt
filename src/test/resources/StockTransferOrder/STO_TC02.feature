Feature: STO_TC02 - Transfer the goods from one branch facility to another branch facility, partial outgoing and incoming Shipment.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@STO_TC02-01
Scenario: Create Stock transfer Order - STO_TC02
Given Create New Stock transfer Order - STO_TC_Two_One
Then Add Shipping Details - STO_TC_Two_One
Then Add Order Items - STO_TC_Two_One
Then Review & Create STO - STO_TC_Two_One
Then Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_Two_One

@STO_TC02-02	
Scenario: Create a Shipment - STO_TC02
Given Create Shipment One - STO_TC_Two_Two
Then Create Shipment two - STO_TC_Two_Two
Then Create Shipment Three - STO_TC_Two_Two

@STO_TC02-03
Scenario: Update and post the Stock transfer Note - STO_TC02
Given Invoice Posting for Generated Invoices - STO_TC_Two_three

@STO_TC02-04
Scenario: Receive the Transfer Incoming Shipment - STO_TC02
Given Search the Order No - STO_TC_Two_four
Then Create incoming Shipment one - STO_TC_Two_four
Then Create Shipment Two - STO_TC_Two_four
Then Create Shipment Three - STO_TC_Two_four
 

@STO_TC02-05
Scenario: Transactions Validations under Finance Module - STO_TC02
Given Validate Accounting transactions under Finance Module - STO_TC_Two_Five