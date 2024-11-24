Feature: Validate chrome cpu percentage in dynamic table
Scenario: Verify chrome cpu percentage matches yellow label
		Given I open the dynamic table page
		When I find the cpu column
		And I find the row for "Chrome"
		Then I compare the chrome cpu value with the yellow label
