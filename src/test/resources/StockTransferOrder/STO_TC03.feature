Feature: STO_TC03 - Create a Stock Transfer Order from existing Order, Edit the order, by changing qty, Transfer the goods from one branch facility to another branch facility with full shipment.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@STO_TC03-01
Scenario: Create Stock transfer Order - STO_TC03
Given Create New Stock transfer Order - STO_TC_Three_One
Then Add Shipping Details - STO_TC_Three_One
Then Add Order Items - STO_TC_Three_One
Then Review & Create STO - STO_TC_Three_One
Then Validating SubTotal,Tax_Consolidated_PopupBtn, GrandTotal - STO_TC_Three_One
Then Create as New Transport Order - STO_TC_Three_One
Then Edit the order - STO_TC_Three_One

@STO_TC03-02	
Scenario: Create a Shipment - STO_TC03
Given Create Shipment One - STO_TC_Three_Two

@STO_TC03-03
Scenario: Update and post the Stock transfer Note - STO_TC03
Given Invoice Posting for Generated Invoice - STO_TC_Three_three

@STO_TC03-04
Scenario: Receive the Transfer Incoming Shipment - STO_TC03
Given Search the Order No - STO_TC_Three_four
Then Create incoming Shipment one - STO_TC_Three_four

@STO_TC03-05
Scenario: Transactions Validations under Finance Module - STO_TC03
Given Validate Accounting transactions under Finance Module - STO_TC_Three_Five