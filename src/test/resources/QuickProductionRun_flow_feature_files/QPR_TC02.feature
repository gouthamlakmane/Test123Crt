Feature: QPR_TC02 - Create a Quick production run by adding additional raw material > Cancel raw material with respect to the BOM > Verify the Cost of transactions.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 

	
@QPR_TC02-01 
Scenario: Verify the Stock of the Product and create a quick production run transaction - QPR_TC02-01

	Given Verify the Stock of the Products before transactions - QPR_TC02_One
	Then add a finished goods id under production Reporting table - QPR_TC02_One
	And add additional raw material - QPR_TC02_One
	Then create the transaction - QPR_TC02_One
	Then cancel the raw material which is derived from BOM and create a transaction - QPR_TC02_One
	Then declare the production transactions by clicking Quick Complete Production Run button - QPR_TC02_One

@QPR_TC02-02	
Scenario: Validate the cost and Stock of the Products - @QPR_TC02-02
	Then Validate the cost of the transaction - QPR_TC02_Two
	Then Verify the Stock of the Products After transactions - QPR_TC02_Two
	
#@QPR_TC02-03
#Scenario: Transactions Validations under Finance Module -@QPR_TC02-03
#
#    Given Validate Accounting transactions -@QPR_TC02-03