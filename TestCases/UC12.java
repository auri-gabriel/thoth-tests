package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
		
		//ir para a página inicial
		driver.get("http://lesse.com.br/tools/thoth-rp/");
		
		//entrar na conta		
		driver.findElement(By.linkText("Sign in")).click();				
		driver.findElement(By.id("InputEmail1")).sendKeys("guilhermesamuel79@gmail.com");		
		driver.findElement(By.id("InputPassword")).sendKeys("12345678");
		driver.findElement(By.cssSelector(".btn-success")).click();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    @Test
    public void solveConflictByQuality() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("C:/UC12_parameter.txt"));
        Path path = Paths.get("C:/UC12_parameter.txt");
        Long lineCounter = Files.lines(path).count();

        for (int i = 0; i < lineCounter; i++){
            String line = reader.readLine();
            //click in a project
            driver.findElement(By.xpath(line)).click();
            Thread.sleep(50);
            //click Conducting
            driver.findElement(By.xpath("/html/body/div/div/div[1]/a[3]")).click();
            Thread.sleep(50);
            //click Quality Assessment
            driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[2]/a")).click();
            Thread.sleep(50);
            //click in a conflict
            driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[7]/div[2]/div/table/tbody/tr[1]/td[2]")).click();
            Thread.sleep(50);
            //click in combobox for status
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/div[6]/select")).click();
        }
        reader.close();
    }
}