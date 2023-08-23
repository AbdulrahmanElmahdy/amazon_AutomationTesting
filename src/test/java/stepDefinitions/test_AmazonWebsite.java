package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.assertTrue;

public class test_AmazonWebsite {
    public static WebDriver driver ;
    public static String actualName ;
    public static String actualPrice;
    public static Double qtyD;
    public static String actualQty;

        @Given("user open browser")
        public static void openBrowser ()
        {
        // Open Browser and Go to https://www.amazon.eg/
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://www.amazon.eg/-/en");
            driver.manage().window().maximize();
        }

        // Scenario 1 (Verify that user cannot log in with valid but not registered email)

        @And("Click sign in and insert mail then click continue")
        public static void loginMail () throws InterruptedException {
            // Sign in
            driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();

            // Insert email
            driver.findElement(By.id("ap_email")).sendKeys("amazon@gmail.com");

            // Click continue button
            Thread.sleep(3000);
            driver.findElement(By.id("continue")).click();
        }
        @When("Insert password then click sign in")
        public static void loginPassword () throws InterruptedException {
            // Insert password
            driver.findElement(By.id("ap_password")).sendKeys("123456789");

            // Click Sign in
            Thread.sleep(3000);
            driver.findElement(By.id("signInSubmit")).click();
            Thread.sleep(3000);
        }
        @Then("Verify that user cannot log in with valid not registered mail")
        public static void verifyValidMail ()
        {
            // Verify user can’t log in with valid but not registered email
            boolean result = driver.findElement(By.className("a-list-item")).getText().equals("Your password is incorrect");
            assertTrue(result);

            driver.quit();
        }

        @And("Click on all and Click on today deals Then Click on 2nd Category")

        public static void clickProducts () throws InterruptedException {
            // Click on all
            driver.findElement(By.id("nav-hamburger-menu")).click();

            // Click on today deals
            Thread.sleep(4000);
            driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[17]/a")).click();

            //Click on 2nd Category
            Thread.sleep(4000);
            driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[2]/span[3]/ul/li[2]/label/input")).click();
        }
        @When("Select first product is selected, Select second item and choose 2 qty")
        public static void selectProducts () throws InterruptedException {

            //Select first product
            Thread.sleep(4000);
            driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[3]/div/div[1]/div/div")).click();

            //Select second item
            Thread.sleep(4000);
            driver.findElement(By.xpath("//*[@id=\"octopus-dlp-asin-stream\"]/ul/li[2]/span/div/div[2]/div[1]/a")).click();
            Thread.sleep(4000);
            actualName = driver.findElement(By.id("productTitle")).getText();
            actualPrice = driver.findElement(By.xpath("//*[@id=\"corePrice_feature_div\"]/div/span[1]")).getText().replaceAll("\n", ".").replaceAll("EGP", "");

            //Select 2 qty
            Select qty = new Select(driver.findElement(By.id("quantity")));
            qty.selectByValue("2");
            actualQty = driver.findElement(By.xpath("//*[@id=\"selectQuantity\"]/span/div/div/input")).getAttribute("value");
            qtyD = Double.valueOf(actualQty);

        }
        @And("Click Add to cart then Open cart")
        public static void addCart () throws InterruptedException {

            //Add to cart
            driver.findElement(By.id("add-to-cart-button")).click();

            //Open cart
            Thread.sleep(4000);
            driver.findElement(By.className("a-button-text")).click();
        }
        @Then("Verify correct items are added to the cart")
        public static void verifyProduct () {
            // Verify correct items are added to the cart (name, price, qty and subtotal is correct)
            String expectedPrice = driver.findElement(By.className("sc-badge-price-to-pay")).getText().replaceAll(" ", "").replaceAll("EGP", "");
            Double actualPriceD = Double.valueOf(actualPrice);
            String totalPriceR = driver.findElement(By.xpath("//*[@id=\"sc-subtotal-amount-buybox\"]/span")).getText().replaceAll("EGP", "").replaceAll(" ", "");
            Double totalPrice = Double.valueOf(totalPriceR);

            boolean result2 = actualName.equals(driver.findElement(By.className("a-truncate-cut")).getText()) &&
                    actualPrice.equals(expectedPrice) &&
                    actualQty.equals(driver.findElement(By.className("a-dropdown-prompt")).getText()) &&
                    totalPrice.equals(actualPriceD * qtyD);
            assertTrue(result2);

            driver.quit();
        }

        @And("Select your orders")
        public static void selectOrders () {
            // Select your orders
            Actions act2 = new Actions(driver);

            //Retrieve WebElement 'Hello, sign in' to perform mouse hover
            WebElement menuOption2 = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
            act2.moveToElement(menuOption2).perform();
            driver.findElement(By.id("nav_prefetch_yourorders")).click();
        }
        @And("Click on your addresses")
        public static void clickOnAddresses () {
            // Click on your addresses
            driver.navigate().back();
            Actions act3 = new Actions(driver);

            //Retrieve WebElement 'Hello, sign in' to perform mouse hover
            WebElement menuOption3 = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
            act3.moveToElement(menuOption3).perform();
            driver.findElement(By.id("nav_prefetch_youraddresses")).click();
        }
        @When("Select your list")
        public static void selectLists () {

            // Select your list
            driver.navigate().back();
            Actions act1 = new Actions(driver);

            //Retrieve WebElement 'Hello, sign in' to perform mouse hover
            WebElement menuOption1 = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
            act1.moveToElement(menuOption1).perform();

            // Click on your list
            driver.findElement(By.className("nav-text")).click();
        }
        @Then("Verify that user see the lists screen")
        public static void verifyListsDisplayed () {

            // Verify that user see the screen
            boolean result3 = driver.findElement(By.className("al-intro-banner-header")).getText().equals("Lists");
            assertTrue(result3);
            driver.quit();
        }

}
