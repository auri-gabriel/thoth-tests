package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UC01 {

	static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Setando localização do arquivo executável
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://lesse.com.br/tools/thoth-rp/");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Execução efetuada com sucesso");
	}

	@Test
	public void testSignUpExistent() throws IOException, InterruptedException {
		BufferedReader reader = new BufferedReader(new FileReader("C:/UC01_parameter.txt"));

		for (int i = 0; i < 2; i++){
			String line1 = reader.readLine();
			String line2 = reader.readLine();
			String line3 = reader.readLine();
			driver.findElement(By.linkText("Sign up")).click();
			driver.findElement(By.name("name")).sendKeys(line1);
			driver.findElement(By.name("email")).sendKeys(line2);
			driver.findElement(By.name("password")).sendKeys(line3);
			driver.findElement(By.className("form-signup")).findElement(By.tagName("BUTTON")).click();
			Thread.sleep(1500);
			driver.findElement(By.xpath("/html/body/nav/div/ul[2]/li[2]/a/span")).click(); //Sign out
		}

		driver.findElement(By.linkText("Sign up")).click();
		driver.findElement(By.name("name")).sendKeys("Ketrin Vargas");
		driver.findElement(By.name("email")).sendKeys("ketrin_vargas@outlook.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/form/button")).click(); //create a new account
		String error = driver.findElement(By.xpath("/html/body/div[1]/strong")).getText(); //error message
		Assert.assertEquals("Email already used!", error);
	}
}