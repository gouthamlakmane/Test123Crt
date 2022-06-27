Feature: STO_TC04 - Create a Stock Transfer Order from existing Order, Transfer the goods from one branch facility to another branch facility with partial shipment, Cancel the order for remaining qty,  Update and Post the Invoice(transfer Note).

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@STO_TC04-01
Scenario: Create Stock transfer Order - STO_TC04
Given Create New Stock transfer Order - STO_TC_Four_One
Then Create as New Transport Order - STO_TC_Four_One

@STO_TC04-02	
Scenario: Create a Shipment - STO_TC04
Given Create partial Shipment - STO_TC_Four_Two
Then Cancel the order for remaining qty - STO_TC_Four_Two
Then Create Shipment two - STO_TC_Four_Two
Then Create Shipment Three - STO_TC_Four_Two

@STO_TC04-03
Scenario: Update and post the Stock transfer Note - STO_TC04
Given Invoice Posting for Generated Invoice - STO_TC_Four_three

@STO_TC04-04
Scenario: Receive the Transfer Incoming Shipment - STO_TC04
Given Search the Order No - STO_TC_Four_four
Then Create incoming Shipment one - STO_TC_Four_four

@STO_TC04-05
Scenario: Transactions Validations under Finance Module - STO_TC04
Given Validate Accounting transactions under Finance Module - STO_TC_Four_Five
Then Edit the order - STO_TC_Four_Five