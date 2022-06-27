Feature: PRDORDER_TC01 - Single level production order, with multiple raw material and complete production report with different cost calculation.

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
	@PRDORDER_TC01-01
	Scenario: Create a production order - PRDORDER_TC01-01
	Given Create a production order - PRDORDER_TC01-one
	Then Validate BOM qty - PRDORDER_TC01-one
	Then Confirm the order - PRDORDER_TC01-one
	Then Start the order - PRDORDER_TC01-one
	
	
	@PRDORDER_TC01-02
	Scenario: Issue the raw terial as per production order requeast - PRDORDER_TC01-02
	Given Create a stock issue for production order - PRDORDER_TC01-02
	
	@PRDORDER_TC01-03
	Scenario: Update the production order Task - PRDORDER_TC01-03
	When Material status is fully issued - PRDORDER_TC01-03
	Then Update the order task status - PRDORDER_TC01-03
	Then complete the task - PRDORDER_TC01-03