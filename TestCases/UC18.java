package Testes;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class UC18 {
	
	static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//setup do selenium				
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//ir para a pagina inicial
		driver.get("http://lesse.com.br/tools/thoth-rp/");
		
		//ir para o login
		try {
			driver.findElement(By.xpath("/html/body/nav/button")).click();
			driver.findElement(By.xpath("/html/body/nav/div/ul[2]/li[1]/a")).click();
		} catch (Exception e) {
			// TODO: handle exception
			driver.findElement(By.xpath("/html/body/nav/div/ul[2]/li[1]/a")).click();
		}
		
		

		
		
		//entrar na conta								
		driver.findElement(By.id("InputEmail1")).sendKeys("auri_gabriel@outlook.com");		
		driver.findElement(By.id("InputPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		//abre a pagina do projeto
		driver.get("http://lesse.com.br/tools/thoth-rp/open/187");
		//clica no reporting
		driver.findElement(By.xpath("/html/body/div/div/div[1]/a[4]")).click();

	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();//termina o teste
	}

	@Before
	public void setUp() throws Exception {
		
		//abre a pagina do projeto
		driver.get("http://lesse.com.br/tools/thoth-rp/open/187");
		//clica no reporting
		driver.findElement(By.xpath("/html/body/div/div/div[1]/a[4]")).click();

		
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(500);
	}

	@Test
	public void testOverview() throws Exception {
		
		//clica em Overview
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[1]/a")).click();
		Thread.sleep(500);
		//verifica se os graficos existe
		assertTrue(driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div[1]/div[1]")).isDisplayed() && driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div[1]/div[2]")).isDisplayed());
		Thread.sleep(500);
		

	}

	@Test
	public void testImport() throws Exception {
		
		//clica em Import Studies
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[2]/a")).click();
		Thread.sleep(500);
		//verifica se o grafico existe
		assertTrue(driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div[2]/div/div")).isDisplayed());
		Thread.sleep(500);
		

	}
	
	@Test
	public void testSelection() throws Exception {
		
		//clica em Study Selection
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[3]/a")).click();
		Thread.sleep(500);
		//verifica se o grafico existe
		assertTrue(driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div[3]/div[2]")).isDisplayed());
		Thread.sleep(500);
		
	}
	
	@Test
	public void testQuality() throws Exception {
		
		//clica em Quality Assessment
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[4]/a")).click();
		Thread.sleep(500);
		//verifica se os graficos existem
		assertTrue(driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div[4]/div[3]/div")).isDisplayed() && driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div[4]/div[4]")).isDisplayed());
		Thread.sleep(500);
		

	}
}
