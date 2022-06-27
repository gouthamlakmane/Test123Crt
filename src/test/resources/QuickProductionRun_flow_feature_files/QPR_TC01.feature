Feature: QPR_TC01 - Create a Quick production run with the reference of Bill of material > Verify the Cost of transactions.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@QPR_TC01-01 
Scenario: Verify the Stock of the Product -QPR_TC01-01

	Given Verify the Stock of the Products before transactions -QPR_TC01_One
	Then Navigate to manufacturing and select the Quick production run link and Enter the finished goods detils under Production Reporting table- QPR_TC01_One
	Then Once raw material is displaying under the Consumption table, Validate the raw material quantity as per the BOM and complete the task- QPR_TC01_One
	Then declare the production transactions by clicking Quick Complete Production Run button - QPR_TC01_Two

@QPR_TC01-02	
Scenario: Validate the cost of the transaction and Stock of the Products - @QPR_TC01-02
	Then Validate the cost of the transaction - QPR_TC01_Two
	Then Verify the Stock of the Products After transactions - QPR_TC01_Two
	
#@QPR_TC01-03
#Scenario: Transactions Validations under Finance Module -@QPR_TC01-03
#
#    Given Validate Accounting transactions -@QPR_TC01-03