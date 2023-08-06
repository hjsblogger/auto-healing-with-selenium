import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import static setup.DriverManager.getDriver;
import static setup.DriverManager.status;

public class AutoHealingTest extends BaseTest {
    //WebDriver driver = null;
    private WebDriverWait webDriverWait;
    private Actions actionChains;
    JavascriptExecutor jsExecutor;
    int ELEM_TIMEOUT_DUR = 10;

    @BeforeClass
    @Parameters({"testurl"})
    public void navigateToWebsite(final String testURL) throws InterruptedException
    {
        WebDriver driver = getDriver();

        webDriverWait = new WebDriverWait(driver,
                Duration.ofSeconds(ELEM_TIMEOUT_DUR));
        jsExecutor = (JavascriptExecutor) driver;
        actionChains = new Actions(driver);
        driver.get(testURL);

        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test(description = "Test 1: Auto Healing on LambdaTest", enabled=true)
    public void testAutoHealingCloud() throws InterruptedException
    {
        WebDriver driver = getDriver();
        try
        {
            WebElement searchBoxActual = driver.findElement(By.id("nav-search-submit-button"));
            jsExecutor.executeScript
                    ("document.getElementById('nav-search-submit-button').id='amazonsearchbox'");
            WebElement searchBoxHeal = driver.findElement(By.id("nav-search-submit-button"));
            Assert.assertEquals(searchBoxActual, searchBoxHeal,
                    "Elements not equal");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            status = "failed";
        }
        Thread.sleep(2000);
    }

    @Test(description = "Test 1: Auto Healing on LambdaTest", enabled=true)
    public void testAutoHealing_ecommerce_cart() throws InterruptedException
    {
        WebDriver driver = getDriver();
        try
        {
            WebElement nested_elements = webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(
                    By.xpath("//div[@id='entry_212391']//div[@id='entry_212408']//div[@class='row']"))));

            /* Avoid scrolling, directly scroll into the view */
            /*
            long start_height = (long) jsExecutor.executeScript("return document.documentElement.scrollHeight;");
            jsExecutor.executeScript("window.scrollTo(0, " + start_height + ")");
            Thread.sleep(1000);
             */

            jsExecutor.executeScript
                    ("document.getElementById('mz-product-grid-image-44-212408').id='macbook-air-id'");

            /* New locator works, healed logic is not working fine. This needs to be commented later */
            WebElement elemMacBook = driver.findElement(By.id(
                    "macbook-air-id"));

            Thread.sleep(1000);

            /* Some issue with the healing logic, to be tried later
                jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                elemMacBook, "xpath", newXPath);
             */

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemMacBook);
            Thread.sleep(500);

            actionChains.moveToElement(elemMacBook).perform();

            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            WebElement elemQuickView = driver.findElement(By.cssSelector(
                    ".quick-view-44"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemQuickView));

            /* Try these experiments once the healing logic is fixed */
            /* Experiment - 1: Change the Class Name of quick-view-44 & use the same in code */
            /*
            jsExecutor.executeScript(
                            "document.getElementsByClassName('quick-view-44')[0].className='macbook-air-view'");
             */
            /* Experiment - 2: Change the ID of quick-view-44 & use the same in code */
            /*
            jsExecutor.executeScript(
                    "arguments[0].setAttribute('id', arguments[1]);", elemQuickView, newProductId
            );
            WebElement elemNewQuickView =
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(
                    By.id(newProductId)));
            */

            Thread.sleep(1000);
            elemQuickView.click();

            WebElement elemBuyNowButton;
            elemBuyNowButton = driver.findElement(By.id(
                    "entry_212965"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemBuyNowButton));

            /* Auto-Healing works with id */
            jsExecutor.executeScript
                    ("document.getElementById('entry_212965').id='buy_later'");

            /* elemBuyNowButton = driver.findElement(By.cssSelector(".btn-buynow"); */
            /* elemBuyNowButton = driver.findElement(By.cssSelector(
                    ".text.btn.btn-md.btn-primary.btn-block.btn-buynow.button-buynow.cart-44"));
             */

            /* Reference - https://www.shecodes.io/athena/1902-how-to-set-javascript-attribute-with-setattribute */
            /* jsExecutor.executeScript("document.getElementById('entry_212965').title='BUY LATER'"); */

            Thread.sleep(2000);

            elemBuyNowButton = driver.findElement(By.id("entry_212965"));
            elemBuyNowButton.click();

            WebElement elemContinueButton = driver.findElement(By.cssSelector(
                    "#button-save"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemContinueButton));

            /* We can do some checks later, raise assert if failure */
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            status = "failed";
        }
        Thread.sleep(2000);
    }

    @Test(description = "Test 1: Auto-Healing on E-Commerce Playground Registration Page",
          enabled=true)
    public void testautoheal_ecomm_registration() throws InterruptedException
    {
        WebDriver driver = getDriver();

        try
        {
            WebElement elemFirstName = driver.findElement(By.id("input-firstname"));
            elemFirstName.sendKeys("Testing");

            WebElement elemLastName = driver.findElement(By.id("input-lastname"));
            elemLastName.sendKeys("Testing12345");

            jsExecutor.executeScript
                   ("document.getElementById('input-email').id='email-address'");

            /* Only for testing, verify if the selector has changed from: */
            /* Old: name - email */
            /* New: name - email-address */
            By newEmailAddr = By.id("email-address");
            WebElement tempElement = driver.findElement(newEmailAddr);
            tempElement.sendKeys("teaddressnicehimansnice1@email.com");

            Thread.sleep(3000);

            /* If auto-healing is applied, below mail will be entered in the said location */
            WebElement elemEmail = driver.findElement(By.id("input-email"));
            elemEmail.clear();
            Thread.sleep(2000);
            elemEmail.sendKeys("teaddresshimanshunewoneworkssssss@gmail.com");

            Thread.sleep(1000);

            WebElement elemTel = driver.findElement(By.xpath("//input[@id='input-telephone']"));
            elemTel.sendKeys("12345678");

            WebElement elemPass = driver.findElement(By.name("password"));
            elemPass.sendKeys("password");

            WebElement elemPassConf = driver.findElement(By.name("confirm"));
            elemPassConf.sendKeys("password");

            jsExecutor.executeScript
                    ("document.getElementById('input-newsletter-yes').id='input-newsletter-dont-know'");

            /* Only for testing, verify if the selector has changed from: */
            /* Old: id - input-newsletter-yes */
            /* New: name - input-newsletter-dont-know */
            By newNewsletter = By.id("input-newsletter-dont-know");
            WebElement tempElementNewsletter = driver.findElement(newNewsletter);
            jsExecutor.executeScript("arguments[0].checked = true;",
                    tempElementNewsletter);

            Thread.sleep(3000);

            WebElement elemNewsletter = driver.findElement(By.id("input-newsletter-yes"));
            jsExecutor.executeScript("arguments[0].checked = false;", elemNewsletter);

            WebElement elemNewsletterNo = driver.findElement(By.id("input-newsletter-no"));
            jsExecutor.executeScript("arguments[0].checked = true;", elemNewsletterNo);

            WebElement elemAgree = driver.findElement(By.cssSelector("[for='input-agree']"));
            elemAgree.click();

            Thread.sleep(2000);

            WebElement elemSubmit = driver.findElement
                        (By.xpath("//input[@class='btn btn-primary']"));
            elemSubmit.click();

            webDriverWait.until(d -> ((JavascriptExecutor) driver).
                    executeScript("return document.readyState").toString().equals
                    ("complete"));

            String curURL = driver.getCurrentUrl();
            boolean statusURL = curURL.contains("contains");
            Assert.assertFalse(statusURL,
                    "Success URL is not displayed");

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            status = "failed";
        }
        Thread.sleep(2000);
    }

    @Test(description = "Test 1: Auto Healing on LambdaTest", enabled=true)
    public void testautoheal_local_page() throws InterruptedException
    {
        WebDriver driver = getDriver();

        try
        {
            WebElement elemSignUp = driver.findElement(By.id("signUp"));
            elemSignUp.click();

            //jsExecutor.executeScript
            //        ("document.getElementById('signUp').id='signUp-Button'");

            WebElement elemFirstName = driver.findElement(By.name("first-name"));
            webDriverWait.until(ExpectedConditions.visibilityOf(elemFirstName));

            /*
            jsExecutor.executeScript(
                    "document.getElementById('firstname').setAttribute('className', 'fname')");

            jsExecutor.executeScript
                    ("document.getElementsByName('first-name')[0].name='fname'");
            */

            WebElement elemActFirstName = driver.findElement(By.name("first-name"));
            elemActFirstName.clear();
            elemActFirstName.sendKeys("Himanshu Blogger");

            Thread.sleep(5000);

            driver.get("http://localhost:8080/iframe.html");

            webDriverWait.until(d -> ((JavascriptExecutor) driver).
                    executeScript("return document.readyState").toString().equals
                            ("complete"));

            driver.switchTo().frame("iframe_1");
            Thread.sleep(2000);

            WebElement elemSignUpButton = driver.findElement(By.id("signUp"));
            elemSignUpButton.click();

            WebElement elemiModFirstName = driver.findElement(By.name("first-name"));
            elemiModFirstName.sendKeys("Sheth");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            status = "failed";
        }
        Thread.sleep(2000);
    }
}
