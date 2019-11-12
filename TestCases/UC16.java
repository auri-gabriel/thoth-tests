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

public class UC16 {

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
    public void rateStudyInSelecion() throws IOException, InterruptedException{
        BufferedReader reader = new BufferedReader(new FileReader("C:/UC16_parameter_projects.txt"));
        driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.name("email")).sendKeys("ketrin_vargas@outlook.com");
		driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.className("form-signin")).findElement(By.tagName("BUTTON")).click();

        for (int i = 0; i < 2; i++) {
            String line = reader.readLine();
            //Seleciona o projeto
            driver.findElement(By.xpath(line)).click();
            Thread.sleep(50);
            //Conducting 
            driver.findElement(By.xpath("/html/body/div/div/div[1]/a[3]")).click();
            Thread.sleep(50);
            //Quality Assessment
            driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[3]/a")).click();
            Thread.sleep(2000);
            //Selecionando um estudo
            driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[4]/table/tbody/tr[1]/td[2]")).click();
            Thread.sleep(5000);
            //Fechando o estudo selecionado
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/button")).click();
            //Retorna para a dashboard
            driver.findElement(By.xpath("/html/body/nav/a")).click();
        }
        //Selecionando o terceiro projeto de teste
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/table/tbody/tr[10]/td[3]/a[1]")).click();
        Thread.sleep(50);
        //Conducting 
        driver.findElement(By.xpath("/html/body/div/div/div[1]/a[3]")).click();
        Thread.sleep(50);
        //Quality Assessment
        driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[3]/a")).click();
        //Captura a mensagem de erro
        String error = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/ul/li")).getText();
        //Retorna para a dashboard
        driver.findElement(By.xpath("/html/body/nav/a")).click();
        Assert.assertEquals("Evaluate at least one paper in the selection step to move to the quality step", error);
        reader.close();
    }
}