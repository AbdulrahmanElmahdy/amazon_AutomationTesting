@smoke

Feature:User can see lists screen

  Scenario:Check orders, addresses, and lists
    Given user open browser
    And Select your orders
    And Click on your addresses
    When Select your list
    Then Verify that user see the lists screen