Feature: STO_TC06 - Transfer the goods from one branch facility to another branch facility, full Shipment with recalculate the order feature.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@STO_TC06-01
Scenario: Create Stock transfer Order - STO_TC06
Given Create New Stock transfer Order - STO_TC_SIX_One
Then Add Shipping Details - STO_TC_SIX_One
Then Add Order Items - STO_TC_SIX_One
Then Recalculate Order - STO_TC_SIX_One
Then Review & Create STO - STO_TC_SIX_One
Then Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_SIX_One

@STO_TC06-02	
Scenario: Create a Shipment - STO_TC06
Given  Create Shipment One - STO_TC_SIX_Two
Then Update the shipment status - STO_TC_SIX_Two
Then capture invoice id - STO_TC_SIX_Two

@STO_TC06-03
Scenario: Update and post the Stock transfer Note - STO_TC06
Given Invoice Posting for Generated Invoices - STO_TC_SIX_three

@STO_TC06-04
Scenario: Receive the Transfer Incoming Shipment - STO_TC06
Given Search the Order No - STO_TC_SIX_four
Then Create the incoming Shipment - STO_TC_SIX_four
Then Receive the inventory - STO_TC_SIX_four 

@STO_TC06-05
Scenario: Transactions Validations under Finance Module - STO_TC06
Given Validate Accounting transactions under Finance Module - STO_TC_Six_Five