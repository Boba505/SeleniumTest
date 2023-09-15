package RandStuffTest;

import ReadProperties.ConfigProvider;
import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage extends BaseSeleniumPage {

    @FindBy(xpath = "//*[@id=\"page\"]/div/div[1]/div[1]")
    public static WebElement name;

    public UserPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    public String getName(){
        return name.getText();
    }

}
