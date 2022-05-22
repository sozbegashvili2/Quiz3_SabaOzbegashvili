package TestNG;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.testng.ScreenShooter;

import java.util.Objects;

// Take screenshots automatically on failure
@Listeners({ ScreenShooter.class})
public class CheckboxTests extends ConfigTests {
    @BeforeMethod
    public void config(@Optional("http://the-internet.herokuapp.com/checkboxes") String baseUrl) {
        this.setBaseUrl(baseUrl);
    }

    @BeforeMethod
    public void navigateToBaseUrl() {
        ScreenShooter.captureSuccessfulTests = false;
        String baseUrl = this.getBaseUrl();
        open(baseUrl);
    }

    /**
     * SECOND METHOD
     * "Invoke failed testng soft assertion" - this point was kinda too general, did not understand specifically
     *  on where would we use it, so I am just asserting the checkboxes check attribute where expected value is true
     *  and then finally calling assertAll function and in case of failure it takes screenshots
     */
    @Test
    public void checkUncheckedCheckBoxes() {
        SoftAssert softassert = this.getSoftAssert();

        $$("input[type='checkbox']").forEach(el -> {
            softassert.assertEquals(el.getAttribute("checked"), true);

            if (el.getAttribute("checked") == null) {
                el.click();
            }
        });

        softassert.assertAll();
    }

    /**
     * FIRST METHOD which is dependent on SECOND METHOD as Per quiz Acceptance Criteria
     * Same comment here as in above function, regarding invoking soft assertion, it was too general, so making
     * some assertions from my fantasy, overall there will be 2 fails because
     * 1) - first checkbox is unchecked, but we are waiting for true , so it will fail in SECOND METHOD
     * 2) - second checkbox is checked, but we are expecting it to be null, so it will fail in FIRST SECOND
     */
    @Test(dependsOnMethods = "checkUncheckedCheckBoxes", alwaysRun = true)
    public void uncheckCheckedCheckboxes() {
        SoftAssert softassert = this.getSoftAssert();

        $$("input[type='checkbox']").forEach(el -> {
            softassert.assertEquals(el.getAttribute("checked"), null);

            if (Objects.equals(el.getAttribute("checked"), "true")) {
                el.click();
            }
        });

        softassert.assertAll();
    }
}
