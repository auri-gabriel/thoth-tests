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
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UC11 {

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
        String message = "";

        for (int i = 0; i < lineCounter; i++){
            String line = reader.readLine();
            //click in a project
            driver.findElement(By.xpath(line)).click();
            Thread.sleep(50);
            //click Conducting
            driver.findElement(By.xpath("/html/body/div/div/div[1]/a[3]")).click();
            Thread.sleep(50);
            //click Study Selection
            driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/ul/li[1]/a")).click();
            Thread.sleep(200);

            if (i + 1 == lineCounter){
                message = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[5]/div[3]/div[1]/div")).getText();
            }

            else {
                //click in a conflict
                driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[9]/div[2]/div/table/tbody/tr[1]/td[2]")).click();
                Thread.sleep(100);
                //click in combobox for status
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/div[6]/select")).click();
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/div[6]/select")).sendKeys(Keys.ARROW_UP);
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/div[6]/select")).sendKeys(Keys.ARROW_UP);
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[1]/div[6]/select")).sendKeys(Keys.ENTER);
                driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/button[1]")).click();
                Thread.sleep(300);
                //click on "x"
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/button/span")).click();
                Thread.sleep(300);
            }     
            //click on dashboard
            driver.findElement(By.xpath("/html/body/nav/a")).click();      
        }

        Assert.assertEquals(message, "Showing 0 to 0 of 0 entries");
        reader.close();
    }
}