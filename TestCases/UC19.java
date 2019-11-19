package Testes;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class UC19 {
	
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
	public void testOverview() throws Exception {
		//abre a pagina do projeto
		driver.get("http://lesse.com.br/tools/thoth-rp/open/187");
		//clica no Export
		driver.findElement(By.xpath("/html/body/div/div/div[1]/a[5]")).click();
		//seleciona quais dados deseja exportar
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div[1]")).click();
		driver.findElement(By.cssSelector(".swal2-confirm")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div[2]")).click();
		driver.findElement(By.cssSelector(".swal2-confirm")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div[3]")).click();
		driver.findElement(By.cssSelector(".swal2-confirm")).click();
		Thread.sleep(1000);
		//Aciona o botão New Project in Overleaf
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/form/button")).click();
		//pegar as guias do navegados
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//mudar a guia ativa do navegador
		driver.switchTo().window(browserTabs.get(1));
		Thread.sleep(1000);
		//verifica que o overleaf foi aberto e termina o teste
		assertEquals("Log in to Overleaf", driver.findElement(By.xpath("/html/body/div[2]/main/div/div[1]/h1")).getText());
		Thread.sleep(1000);
		//fecha a janela do overleaf
		driver.close();
		//fecha a janela do thoth
		driver.switchTo().window(browserTabs.get(0));
		driver.close();
	}

}
