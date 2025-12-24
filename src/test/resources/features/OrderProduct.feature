@tag
Feature: Order a product from a ecomerce website 

	Background:
	Given Land on the Ecommerce website
	
	@Regression
	Scenario Outline: Positive Test for ordering a product
		Given Login with email <email> and password <password>
		When  Add product <productName> to the Cart
		And Checkout the product <productName> and place the order
		Then Verify the success message "THANKYOU FOR THE ORDER."
		
		Examples:
		| email                | password  | productName |
		| abhishek.k@gmail.com | Aa@123456 | ZARA COAT 3 |
		


