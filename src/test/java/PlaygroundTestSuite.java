import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlaygroundTestSuite
{
    WebDriver webDriver;
    HomePage homePage;

    @BeforeEach
    public void beforeExampleTest()
    {
        webDriver = new ChromeDriver();
        webDriver.get("https://d1iw6mb9di5l9r.cloudfront.net/");
        webDriver.manage().window().maximize();
        homePage = new HomePage(webDriver);
    }

    @Test
    public void exampleTest()
    {
        homePage.sendForenameKeys("Logan").clickForenameSubmitButton();
    }

    @Test
    public void webDriverWaitTest()
    {
        homePage.clickFakeLoginButton().waitForFakeLoginMessage();
    }

    @Test
    public void userLoginTest()
    {
        homePage.clickUserButton().waitForLoginDialog();
        homePage.sendLoginUserKeys("username").sendLoginPasswordKeys("password");
        homePage.clickLoginButton().waitForLoginMessage();
        Assertions.assertEquals(homePage.getLoginMessage(), "Invalid user and password");
    }

    @Test
    public void formsModernErrorTest()
    {
        homePage.clickFormsButton();
        homePage.sendFormNameKeys("a")
                .sendFormEmailKeys("a@a.aa");

        homePage.clickAgreeCheckbox();
        homePage.selectStateDropdown("QLD");

        homePage.clickFormSubmitButton().waitForFormMessage();
    }

    @AfterEach
    public void afterExampleTest()
    {
        //webDriver.quit();
    }
}
