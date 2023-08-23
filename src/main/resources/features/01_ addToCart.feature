@smoke

Feature:User add product to cart and verify it's the right one

Scenario:Add product to cart and check the cart
  Given user open browser
    And Click on all and Click on today deals Then Click on 2nd Category
    When Select first product is selected, Select second item and choose 2 qty
    And Click Add to cart then Open cart
  Then Verify correct items are added to the cart
