package Testes;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class UC06 {
	
	static WebDriver driver;

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
	public void testEditar() throws Exception {
		
		driver.get("http://lesse.com.br/tools/thoth-rp/dashboard");	
		driver.findElement(By.xpath("//*[@id=\"table_my_projects\"]/tbody/tr[2]/td[3]/a[2]")).click();
		//EDITA_TITULO
		driver.findElement(By.id("edit_title")).sendKeys("_EDITADO");
		//EDITA_DESCRICAO
		driver.findElement(By.id("edit_description")).sendKeys("_EDITADO");
		//EDITA_OBJETIVO
		driver.findElement(By.id("edit_objectives")).sendKeys("_EDITADO");
		driver.findElement(By.cssSelector(".btn-success")).click();		
		Thread.sleep(1000);
		assertEquals("Success", driver.findElement(By.id("swal2-title")).getText());
		driver.findElement(By.cssSelector(".swal2-confirm")).click();
		driver.close();
	}


}
