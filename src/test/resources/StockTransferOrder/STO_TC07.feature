Feature: STO_TC07 - Transfer the goods from one branch facility to another branch facility, full Shipment with batch enabled product.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@STO_TC07-01
Scenario: Create Stock transfer Order - STO_TC07
Given Create New Stock transfer Order - STO_TC_SEVEN_One
Then Add Shipping Details - STO_TC_SEVEN_One
Then Add Order Items - STO_TC_SEVEN_One
Then Review & Create STO - STO_TC_SEVEN_One
Then Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_SEVEN_One

@STO_TC07-02	
Scenario: Create a Shipment - STO_TC07
Given  Create Shipment One - STO_TC_SEVEN_Two
Then Update the shipment status - STO_TC_SEVEN_Two
Then capture invoice id - STO_TC_SEVEN_Two

@STO_TC07-03
Scenario: Update and post the Stock transfer Note - STO_TC07
Given Invoice Posting for Generated Invoices - STO_TC_SEVEN_three

@STO_TC07-04
Scenario: Receive the Transfer Incoming Shipment - STO_TC07
Given Search the Order No - STO_TC_SEVEN_four
Then Create the incoming Shipment - STO_TC_SEVEN_four
Then Receive the inventory - STO_TC_SEVEN_four

@STO_TC07-05
Scenario: Transactions Validations under Finance Module - STO_TC07
Given Validate Accounting transactions under Finance Module - SO_TC_Seven_Five
	