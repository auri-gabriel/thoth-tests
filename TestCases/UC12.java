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



public class UC12 {
	
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
		driver.findElement(By.id("InputEmail1")).sendKeys("auri_gabriel@outlook.com");		
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
	public void testQuality() throws Exception {
		
		driver.get("http://lesse.com.br/tools/thoth-rp/open/177");//abre a pagina do projeto
		driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/a[3]")).click();//clica no conducting
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[2]/a")).click();//clica no review quality
		driver.findElement(By.xpath("//*[@id=\"table_conf_paper_selection\"]/tbody/tr[1]")).click();//seleciona o conflito
		driver.findElement(By.id("status_conflict")).click();//clica no status do conflito
		driver.findElement(By.xpath("//*[@id=\"status_conflict\"]/option[1]")).click();//clica na opcao do status do conflito
		Thread.sleep(1000);//espera a tela de successo
		assertEquals("Accepted", driver.findElement(By.id("swal2-title")).getText());//confere se o resultado é o esperado
		driver.findElement(By.cssSelector(".swal2-confirm")).click(); //fecha o popup de sucesso
		driver.close();//termina o teste
	}


}
