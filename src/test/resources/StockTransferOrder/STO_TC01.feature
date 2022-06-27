Feature: STO_TC01 - Transfer the goods from one branch facility to another branch facility, full Shipment.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@STO_TC01-01
Scenario: Create Stock transfer Order - STO_TC01
Given Create New Stock transfer Order - STO_TC_One
Then Add Shipping Details - STO_TC_One
Then Add Order Items - STO_TC_One
Then Review & Create STO - STO_TC_One
Then Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_One

@STO_TC01-02	
Scenario: Create a Shipment - STO_TC01
Given  Create Shipment One - STO_TC_One
Then Update the shipment status - STO_TC_One
Then capture invoice id - STO_TC_One

@STO_TC01-03
Scenario: Update and post the Stock transfer Note - STO_TC01
Given Invoice Posting for Generated Invoices - STO_TC_One
Then Validating SubTotal/Tax/GrandTotal for all the Generated Invoices - STO_TC_One