Feature: MF_TC01 - Single level Bill Of material with multiple raw material

Background: Login to the Application 
	Given Launch the Application 
	Then Login with valid Credentials 
	
@MF_TC01-01
Scenario: Create BOM - MF01
Given Enter the Finish Good Id - MF_one
Then Map the Raw materials - MF_one

@MF_TC01-02
Scenario: BOM Simulate - MF01
Given Simulate created  BOM - MF_one
Then verify the Similuted raw material quatity - MF_one

@MF_TC01-03
Scenario: Edit the existing BOM- MF01
Given Edit the existing BOM - MF_one
Then verify raw material quatity in the Components of product table - MF_one