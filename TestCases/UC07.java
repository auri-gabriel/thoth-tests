package Testes;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UC07 {
	
	static WebDriver driver;
	private BufferedReader reader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//setup do selenium				
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//ir para a pagina inicial
		driver.get("http://lesse.com.br/tools/thoth-rp/");
		
		//entrar na conta		
		driver.findElement(By.linkText("Sign in")).click();				
		driver.findElement(By.id("InputEmail1")).sendKeys("ketrin_vargas@outlook.com");		
		driver.findElement(By.id("InputPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector(".btn-success")).click();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddmember() throws IOException, Exception {
		
		driver.get("http://lesse.com.br/tools/thoth-rp/dashboard");
		
		reader = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\RP22\\src\\Testes\\UC07_PARAMETROS.txt"));
		String email = reader.readLine();
		String funcao = reader.readLine();

		driver.findElement(By.xpath("//*[@id=\"table_my_projects\"]/tbody/tr[4]/td[3]/a[3]")).click();
		//EMAIL
		driver.findElement(By.xpath("//*[@id=\"select2-add_email_user-container\"]")).click();
		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(email);
		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
		
		//FUNCAO
		driver.findElement(By.xpath("//*[@id=\"select2-add_level_user-container\"]")).click();
		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(funcao);
		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/button")).click();
		Thread.sleep(1000);
		assertEquals("Add member", driver.findElement(By.id("swal2-title")).getText());
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".swal2-confirm")).click();
		
		driver.close();
	}
}
