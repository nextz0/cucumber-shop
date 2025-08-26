Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Milk" with price 25.00 and stock of 3 exists

Scenario: Buy one product
    When I buy "Bread" with quantity 2
    Then total should be 41.00

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    Then total should be 121.00

Scenario: Buy three different products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    And I buy "Milk" with quantity 3
    Then total should be 196.00

Scenario: Cannot buy more than stock
    When I try to buy "Bread" with quantity 6
    Then I should see an out of stock error for "Bread"
    And stock for "Bread" should be 5