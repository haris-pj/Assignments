Feature: Dropdown Selection
Scenario: Select country and city from dropdown
		Given I open the leafground dropdown page
		When I select "India" from the country dropdown
		And I select "Chennai" from city dropdown
		Then I verify selection was successful
