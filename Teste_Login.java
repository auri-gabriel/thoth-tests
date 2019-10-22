package Exemplo_Selenium;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


class Teste_Login {
	// DECLARAÇÃO DE VARIAVEIS
	private  String URL = "http://lesse.com.br/tools/thoth-rp/";
	private  String EMAIL = "ketrin_vargas@outlook.com";
	private  String SENHA = "123456";
	WebDriver driver = new ChromeDriver();
	

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {						
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {		
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.close();
	}
	
	/*
	 * ESSE TESTE TENTA LOGAR NA FERRAMENTA
	 */
	
	
	
	@Test
	void test_Logar() {
		
		driver.get(URL);
		WebElement entrar = driver.findElement(By.linkText("Sign in"));
		entrar.click();
		
		
		WebElement email = driver.findElement(By.id("InputEmail1"));
		email.sendKeys(EMAIL);
		
		WebElement senha = driver.findElement(By.id("InputPassword"));
		senha.sendKeys(SENHA);
		
		WebElement logar = driver.findElement(By.cssSelector(".btn-success"));
		logar.click();		
		
	}
	
	/*
	 * ESSE TESTE TENTA LOGAR E DEPOIS DESLOGAR DA FERRAMENTA
	 */
	@Test
	void test_Logar_Deslogar() {
		
		driver.get(URL);
		
		WebElement entrar = driver.findElement(By.linkText("Sign in"));
		entrar.click();
				
		WebElement email = driver.findElement(By.id("InputEmail1"));
		email.sendKeys(EMAIL);
		
		WebElement senha = driver.findElement(By.id("InputPassword"));
		senha.sendKeys(SENHA);
		
		WebElement logar = driver.findElement(By.cssSelector(".btn-success"));
		logar.click();		
		
		WebElement sair = driver.findElement(By.linkText("Sign out"));
		sair.click();				
		
	}
	
	
}
