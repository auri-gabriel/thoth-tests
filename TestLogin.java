import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OpenNavigator {

	//Instanciando a classe webdriver
	static WebDriver webDriver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Setando localização do arquivo executável
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.get("http://lesse.com.br/tools/thoth-rp/dashboard");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//Fecha apenas a aba que ele usou
		webDriver.close();
	}
	
	@Test
	public void testSignIn() {
		webDriver.findElement(By.linkText("Sign in")).click();
		webDriver.findElement(By.name("email")).sendKeys("ketrin_vargas@outlook.com");
		webDriver.findElement(By.name("password")).sendKeys("123456");
		webDriver.findElement(By.className("form-signin")).findElement(By.tagName("BUTTON")).click();
	}

	@Test
	public void testSignOut() {
		webDriver.findElement(By.xpath("//*[@id=\"menu\"]/ul[2]/li[2]/a")).click();;
	}
}