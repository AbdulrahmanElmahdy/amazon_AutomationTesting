Amazon automamtion test :

using Java 20 Selelnium , cucumber
all dependecies in pom.xml 

Test code (Selenium)
Test report (Cucumber report)
Test Screenshot (Hooks)(export to test-output folder)
Test execution (TestRunner)

Test scenarios (Cucumber):
Scenario 1: Add product to cart and check the cart
- user open browser
- Click on all and Click on today deals 
- Click on 2nd Category
- Select first product is selected, Select second item and choose 2 qty
- Click Add to cart then Open cart
- Verify correct items are added to the cart

Scenario 2: Check orders, addresses, and lists
- User open browser
- Select your orders
- Click on your addresses
- Select your list
- Verify that user see the lists screen

Scenario 3: login with valid mail but not registered
- user open browser
- Click sign in and insert mail then click continue
- Insert password then click sign in
- Verify that user cannot log in with valid not registered mail
