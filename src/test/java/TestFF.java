import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestFF {
    WebDriver wd;
    @Test
    public void test(){
        wd = new ChromeDriver();
    }
}