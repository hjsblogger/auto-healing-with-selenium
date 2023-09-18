/* Source Code inspiration: Selenium4POC: https://bit.ly/ManagerSelenium */

package setup;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static java.text.MessageFormat.format;

public class DriverManager
{
    private static final ThreadLocal<WebDriver> driverLocal = new ThreadLocal<> ();
    /* Get UserName and Access Key from https://accounts.lambdatest.com/security */
    /* Use getEnv if env variables are exported using export or set */
    private static final String LT_USERNAME= System.getenv("LT_USERNAME");
    private static final String LT_ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    /* Use System.getProperty if env variables are set using the mvn command-line */
    /* This helped https://stackoverflow.com/a/5525589/126105 */
    // private static final String LT_USERNAME= System.getProperty("LT_USERNAME");
    // private static final String LT_ACCESS_KEY = System.getProperty("LT_ACCESS_KEY");
    private static final String GRID_URL = "@hub.lambdatest.com/wd/hub";
    public static String status = "passed";

    public static void quitDriver() {
        if (null != getDriver()) {
            ((JavascriptExecutor) getDriver()).executeScript("lambda-status=" + status);
            getDriver().quit();
        }
    }

    public static void createDriver(final String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            setupChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            setupFirefoxDriver();
        } else if (browser.equalsIgnoreCase("remote-chrome")) {
            setupRemoteChromeDriver();
        } else {
            System.out.println("Browser driver is not available!");
        }
        setupBrowserTimeouts();
    }

    public static WebDriver getDriver () {
        return driverLocal.get();
    }

    private static void setupChromeDriver () {
        final ChromeOptions options = new ChromeOptions ();
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-dev-shm-usage");
        options.addArguments ("--window-size=1050,600");
        options.addArguments("--safebrowsing-disable-download-protection");
        driverLocal.set(new ChromeDriver(options));
    }

    private static void setupFirefoxDriver () {

        final FirefoxOptions options = new FirefoxOptions ();
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-dev-shm-usage");
        options.addArguments("--window-size=1050,600");
        driverLocal.set(new FirefoxDriver(options));
    }

    private static void setupRemoteChromeDriver ()
    {
        final ChromeOptions browserOptions = new ChromeOptions();
        final HashMap<String, Object> ltOptions = new HashMap<String, Object>();

        browserOptions.setPlatformName ("Windows 11");
        ltOptions.put ("username", LT_USERNAME);
        ltOptions.put ("accessKey", LT_ACCESS_KEY);
        /* ltOptions.put ("selenium_version", "3.141.0"); */
        ltOptions.put ("resolution", "2560x1440");
        ltOptions.put ("build", "[Build] Demo: Auto-healing using Selenium on LambdaTest");
        ltOptions.put ("name", "Demo: Auto-healing using Selenium on LambdaTest");
        ltOptions.put ("project", "[Project] Auto-Healing Functionality");
        ltOptions.put ("plugin", "java-testNG");
        ltOptions.put("ACCEPT_INSECURE_CERTS", false);
        ltOptions.put("ACCEPT_SSL_CERTS", false);
        ltOptions.put("tunnel", true);
        ltOptions.put ("w3c", true);
        ltOptions.put("autoHeal", true);

        browserOptions.setCapability ("LT:Options", ltOptions);

        try
        {
            driverLocal.set(new RemoteWebDriver(
                    new URL(format("https://{0}:{1}{2}", LT_USERNAME, LT_ACCESS_KEY, GRID_URL)),
                        browserOptions));
        }
        catch (final MalformedURLException e)
        {
            throw new Error(e);
        }
    }

    private static void setupBrowserTimeouts () {
        WebDriver driver = driverLocal.get();
        driver.manage ()
                .timeouts ()
                .implicitlyWait (Duration.ofSeconds (30));
    }
}
