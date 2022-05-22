package TestNG;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;


public class ConfigTests {
    private String baseUrl;
    final private SoftAssert softAssert;

    public ConfigTests() {
        this.softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.holdBrowserOpen = true;
        Configuration.reportsFolder = "CheckboxFailedTests";
    }

    @AfterMethod
    public void after() {
        closeWebDriver();
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }

    public void setReportsFolder(String directory) {
        Configuration.reportsFolder = directory;
    }
}
