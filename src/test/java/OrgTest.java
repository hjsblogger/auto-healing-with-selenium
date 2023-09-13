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

public class OrgTest extends BaseTest {
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
        /* Not a good practice, used for testing */
        actionChains = new Actions(driver);
        driver.get(testURL);

        driver.manage().window().maximize();
        /* Recommend using explicit wait */
        /* Blocking wait used only for testing */
        Thread.sleep(2000);
    }

    @Test(description = "Test 1: Auto Healing on LambdaTest - Ecommerce Registration Page",
            enabled=true)
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

            /* Commented for now */
            /* jsExecutor.executeScript
                    ("document.getElementById('mz-product-grid-image-44-212408').id='macbook-air-id'");
            */

            /* New locator works, healed logic is not working fine. This needs to be commented later */
            WebElement elemMacBook = driver.findElement(By.id(
                    "mz-product-grid-image-44-212408"));

            Thread.sleep(1000);

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

            /* Works Fine, instead I used XPath * /
            WebElement elemQuickView = driver.findElement(By.cssSelector(
                    ".quick-view-44"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemQuickView));
             */

            /* Commented for now */
            /* jsExecutor.executeScript(
                    "document.getElementsByClassName('btn btn-quick-view quick-view-44')[0].className=" +
                            "'btn btn-quick-view quick-view-99'");
            */

            WebElement elemQuickView = driver.findElement(By.xpath(
                    "//button[@class='btn btn-quick-view quick-view-44']"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemQuickView));

            Thread.sleep(1000);
            elemQuickView.click();

            WebElement elemBuyNowButton;
            elemBuyNowButton = driver.findElement(By.id(
                    "entry_212965"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemBuyNowButton));

            /* Change the ID and than auto-heal with the original ID */
            /* Commented for now */
            /* jsExecutor.executeScript
                    ("document.getElementById('entry_212965').id='buy_later'");
            */

            /* Change the innerText of the Button, only for Testing */
            /* Commented for now */
            /* jsExecutor.executeScript(
                    "document.getElementsByClassName('text btn btn-md btn-primary btn-block btn-buynow button-buynow cart-44')[0].innerText='BUY LATER'");
            */

            Thread.sleep(2000);

            elemBuyNowButton = driver.findElement(By.id("entry_212965"));
            elemBuyNowButton.click();

            WebElement elemContinueButton = driver.findElement(By.cssSelector(
                    "#button-save"));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elemContinueButton));

            /* We can do some checks later, raise assert if failure */
            String curURL = driver.getCurrentUrl();
            boolean statusURL = curURL.contains("checkout");
            Assert.assertTrue(statusURL,
                    "Checkout page is displayed");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            status = "failed";
        }
        Thread.sleep(2000);
    }

    @Test(description = "Test 2: Auto-Healing on E-Commerce Playground Registration Page",
          enabled=true)
    public void testAutoHealing_ecomm_registration() throws InterruptedException
    {
        WebDriver driver = getDriver();

        try
        {
            WebElement elemFirstName = driver.findElement(By.id("input-firstname"));
            elemFirstName.sendKeys("Testing");

            WebElement elemLastName = driver.findElement(By.id("input-lastname"));
            elemLastName.sendKeys("Testing12345");

            /* Commented for now */
            /* jsExecutor.executeScript
                   ("document.getElementById('input-email').id='email-address'");
            */

            /* Only for testing, verify if the selector has changed from: */
            /* Old: name - email */
            /* New: name - email-address */
            /* Commented for now */
            /* By newEmailAddr = By.id("email-address"); */
            By newEmailAddr = By.id("input-email");
            WebElement tempElement = driver.findElement(newEmailAddr);
            tempElement.sendKeys("testingemail4@gmail.com");

            /* Not a good practice, should be avoided. Used only for testing */
            Thread.sleep(1000);

            /* If auto-healing is applied, below mail will be entered in the said location */
            WebElement elemEmail = driver.findElement(By.id("input-email"));
            elemEmail.clear();
            Thread.sleep(1000);
            elemEmail.sendKeys("testingemail5@gmail.com");

            Thread.sleep(1000);

            WebElement elemTel = driver.findElement(By.xpath("//input[@id='input-telephone']"));
            elemTel.sendKeys("12345678");

            /* Commented for now */
            /* jsExecutor.executeScript
                    ("document.getElementsByName('password')[0].name='password-new'");
            */

            WebElement elemPass = driver.findElement(By.name("password"));
            elemPass.sendKeys("password");

            WebElement elemPassConf = driver.findElement(By.name("confirm"));
            elemPassConf.sendKeys("password");

            /* Commented for now */
            /* jsExecutor.executeScript
                    ("document.getElementById('input-newsletter-yes').id='input-newsletter-dont-know'");
            */

            /* Only for testing, verify if the selector has changed from: */
            /* Old: id - input-newsletter-yes */
            /* New: name - input-newsletter-dont-know */
            By newNewsletter = By.id("input-newsletter-yes");
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

    @Test(description = "Test 3: [Local Page] Auto Healing on LambdaTest", enabled=true)
    public void testAutoHealing_local_page() throws InterruptedException
    {
        WebDriver driver = getDriver();

        try
        {
            /* Healing 1 : Change the ID of the Sign-up button */

            WebElement elemSignUp = driver.findElement(By.id("signUp"));
            elemSignUp.click();

            /* Healing 2 : Name of the text-box is changed from first-name to first-name-new */
            WebElement elemFirstName = driver.findElement(By.name("first-name"));
            webDriverWait.until(ExpectedConditions.visibilityOf(elemFirstName));

            /* Clear the existing text and enter the details */
            elemFirstName.clear();
            elemFirstName.sendKeys("Himanshu Blogger");

            Thread.sleep(2000);

            /* Scenario 2: The iFrame contains the page where the ID's are changed */
            driver.get("http://localhost:8080/iframe.html");

            webDriverWait.until(d -> ((JavascriptExecutor) driver).
                    executeScript("return document.readyState").toString().equals
                            ("complete"));

            driver.switchTo().frame("iframe_1");
            Thread.sleep(2000);

            /* Healing 3 : ID of the button was changed earlier */
            WebElement elemSignUpButton = driver.findElement(By.id("signUp"));
            elemSignUpButton.click();

            /* Healing 4 : Name of the button was changed in the base HTML file */
            WebElement elemModFirstName = driver.findElement(By.name("first-name"));
            elemModFirstName.sendKeys("Himanshu Sheth");

            /* Change the name of the Email text-box, healing will be done in index.html */
            /* This is because the parent page is in the iFrame */
            /* Commented for now */
            /* jsExecutor.executeScript
                    ("document.getElementsByClassName('email-address')[0].name='mail'");
            */

            WebElement elemEmail = driver.findElement(By.name("email"));
            elemEmail.sendKeys("himanshu.blogger@gmail.com");
            Thread.sleep(500);

            /* Change the ClassName of the password field, healing will not be done */
            /* This is because the parent page is in the iFrame */
            /* Commented for now */
            /* jsExecutor.executeScript
                    ("document.getElementsByName('password')[0].className='new-password'");
            */

            WebElement elemNewPwd = driver.findElement(By.className("password"));
            elemNewPwd.sendKeys("Password");
            Thread.sleep(500);

            /* Click Sign-up button */
            elemSignUpButton = driver.findElement(By.cssSelector(".sign-up-container button"));
            elemSignUpButton.click();

            Thread.sleep(3000);

            String curURL = driver.getPageSource();
            boolean statusURL = curURL.contains("SignUp iFrame");
            Assert.assertFalse(statusURL,
                    "Sign Up was not successful");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            status = "failed";
        }
        Thread.sleep(2000);
    }
}
