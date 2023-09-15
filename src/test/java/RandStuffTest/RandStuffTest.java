package RandStuffTest;

import core.BaseSeleniumTest;
import helpers.TestValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandStuffTest extends BaseSeleniumTest {

    @Test
    public void checkButton() {
        MainPage mainPage = new MainPage();
        UserPage userPage = new UserPage();
        mainPage.userAuthorization(TestValues.TEST_EMAIL, TestValues.TEST_PASS);
        Assertions.assertEquals(userPage.getName(), TestValues.TEST_NAME);
    }
}