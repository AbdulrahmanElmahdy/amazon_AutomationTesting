@smoke

Feature:User cannot log in with valid but not registered email

  Scenario:login with valid mail but not registered
    Given user open browser
    And Click sign in and insert mail then click continue
    When Insert password then click sign in
    Then Verify that user cannot log in with valid not registered mail