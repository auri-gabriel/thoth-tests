package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UC15 {

    static WebDriver driver;

    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Setando localização do arquivo executável
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://lesse.com.br/tools/thoth-rp/");
    }
    
    @AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
    }
    
    @Test
    public void testCheckDuplicated() throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new FileReader("C:/UC15_parameter.txt"));
        driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.name("email")).sendKeys("ketrin_vargas@outlook.com");
		driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.className("form-signin")).findElement(By.tagName("BUTTON")).click();
        String duplicates = "";
        for (int i = 0; i < 3; i++) { 
            String line = reader.readLine();
            //Seleciona o projeto
            driver.findElement(By.xpath(line)).click();
            Thread.sleep(50);
            //Conducting 
            driver.findElement(By.xpath("/html/body/div/div/div[1]/a[3]")).click();
            Thread.sleep(50);
            //Study Selection
            driver.findElement(By.xpath("/html/body/div/div/div[2]/ul/li[2]/a")).click();
            Thread.sleep(3000);
            //Check duplicates
            driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[4]/div[1]/button[6]")).click();
            Thread.sleep(1000);

            if (line.equals("/html/body/div/div/div[2]/div/div[2]/div/table/tbody/tr[10]/td[3]/a[1]")){
                duplicates = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]")).getText();
            }
            //Pressiona "OK"
            driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();
            Thread.sleep(1000);
            //Retorna para a dashboard
            driver.findElement(By.xpath("/html/body/nav/a")).click();
        }
        assertEquals("The 1 papers was duplicate", duplicates);
        reader.close();
    }
}