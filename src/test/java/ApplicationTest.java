import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationTest {

    private WebDriver driver;

    @BeforeAll
    static void webdriverConfig() {
        System.setProperty("webdriver.chrome.config", "./driver/win/chromedriver");
    }

    @BeforeEach
    void createBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void applicationTest() {

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

}