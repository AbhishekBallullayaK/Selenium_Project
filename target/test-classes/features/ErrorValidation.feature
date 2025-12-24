@tag
Feature: Error Validation on Login Page 

	
	@ErrorValidation
	Scenario Outline: Validating login with incorrect credentials
		Given Land on the Ecommerce website
		When Login with email <email> and password <password>
		Then Verify the error message "Incorrect email or password."
		
		Examples:
		| email                | password   | 
		| abhishek.k@gmail.com | Aa@1234567 |
		


