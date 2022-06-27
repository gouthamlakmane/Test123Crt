Feature: QPR_TC04 - Create a Quick production run by adding raw material and additional raw material > adding the byproduct > Verify the Cost of transactions.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 

	
@QPR_TC04-01 
Scenario: Verify the Stock of the Product and create a quick production run transaction - QPR_TC04-01

	Given Verify the Stock of the Products before transactions - QPR_TC04_One
	Then add a finished goods id under production Reporting table - QPR_TC04_One
	And add additional raw material - QPR_TC04_One
	Then create the transaction - QPR_TC04_One
	Then adding the byproduct - QPR_TC04_One
	Then declare the production - QPR_TC04_One

@QPR_TC04-02	
Scenario: Validate the cost and Stock of the Products - QPR_TC04-02
	Then Validate the cost of the transaction - QPR_TC04_Two
	Then Verify the Stock of the Products After transactions - QPR_TC04_Two
	
#@QPR_TC04-03
#Scenario: Transactions Validations under Finance Module -QPR_TC04-03
#
#    Given Validate Accounting transactions -QPR_TC04-03