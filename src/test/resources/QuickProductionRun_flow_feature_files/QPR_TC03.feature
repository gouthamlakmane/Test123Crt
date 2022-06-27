Feature: QPR_TC03 - Create a Quick production run > update the raw material > Verify the Cost of transactions.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 

	
@QPR_TC03-01 
Scenario: Verify Stock of the Product and create a quick production run transaction - QPR_TC03-01

	Given Verify Stock of the Products before transactions - QPR_TC03_One
	Then add a finished goods id - QPR_TC03_One
	Then create the production run transaction - QPR_TC03_One
	Then update the raw material quantity - QPR_TC03_One
	Then declare the production run - QPR_TC03_One

@QPR_TC03-02	
Scenario: Validate the cost and Stock of the Products - @QPR_TC03-02
	Then Validate the cost of the transaction - QPR_TC03_Two
	Then Verify the Stock of the Products After transactions - QPR_TC03_Two
	
#@QPR_TC03-03
#Scenario: Transactions Validations under Finance Module -@QPR_TC03-03

#    Given Validate Accounting transactions -@QPR_TC03-03