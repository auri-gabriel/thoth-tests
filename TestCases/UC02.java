package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UC02 {

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
	public void testSearchProject() throws IOException, InterruptedException {
		BufferedReader reader = new BufferedReader(new FileReader("C:/UC02_parameter.txt"));
		driver.findElement(By.linkText("Sign in")).click();
			driver.findElement(By.name("email")).sendKeys("ketrin_vargas@outlook.com");
			driver.findElement(By.name("password")).sendKeys("123456");
			driver.findElement(By.className("form-signin")).findElement(By.tagName("BUTTON")).click();
		for (int i = 0; i < 2; i++){
			String line1 = reader.readLine();
			driver.findElement(By.name("search")).sendKeys(line1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/form/button")).click();
		}
		String error = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/table/tbody/tr/td")).getText(); //error message
		Assert.assertEquals("No data available in table", error);
		reader.close();
	}
}
