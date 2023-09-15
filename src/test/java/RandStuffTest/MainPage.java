package RandStuffTest;

import ReadProperties.ConfigProvider;
import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {

    @FindBy(xpath = "//*[@id=\"header\"]/div/div[2]/ul/li[10]/a/span")
    public WebElement userAccount;

    @FindBy(xpath = "//*[@id=\"page\"]/div/form/input[1]")
    public WebElement emailField;

    @FindBy(xpath = "//*[@id=\"page\"]/div/form/input[2]")
    public WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"page\"]/div/form/button")
    public WebElement login;

    public MainPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    public MainPage userAuthorization(String userEmail, String userPassword){
        userAccount.click();
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        login.click();
        return this;
    }
}

