package TestNG;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// Take screenshots automatically on failure
@Listeners({ ScreenShooter.class})
public class RadioButtonTests extends ConfigTests {
    @BeforeMethod
    public void config(@Optional("https://demoqa.com/radio-button") String baseUrl) {
        this.setBaseUrl(baseUrl);
        this.setReportsFolder("RadioButtonFailedTests");
    }

    @BeforeMethod
    public void navigateToBaseUrl() {
        ScreenShooter.captureSuccessfulTests = false;
        String baseUrl = this.getBaseUrl();
        open(baseUrl);
    }

    @Test
    public void selectYes() {
        SelenideElement el = $("label[for='yesRadio']");
        el.click();
        SoftAssert softAssert = this.getSoftAssert();

        softAssert.assertEquals($(".text-success").getText(),"Yes");
        softAssert.assertAll();
    }

    @Test
    public void noMethodAvailable() {
        SelenideElement el = $("#noRadio");

        SoftAssert softAssert = this.getSoftAssert();
        // if disabled is not available we will get null, if true, its disabled meaning it is failed case
        softAssert.assertEquals(el.getAttribute("disabled"),null);
        softAssert.assertAll();
    }
}
