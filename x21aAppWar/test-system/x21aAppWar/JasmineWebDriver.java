package x21aAppWar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class JasmineWebDriver {
 	private static ChromeDriver driver;
 	WebElement element;
 	static String path;

 @BeforeClass
 public static void openBrowser(){
	 //System.setProperty("webdriver.chrome.driver", "C:/bea/chromedriver.exe");
	 driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     Properties p = new Properties();
     try {
		p.load(new FileReader(new File("..\\x21aConfig\\x21a.properties")));
		path = p.getProperty("statics.path");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	} 

 @Test
 public void valid_testComponentUda(){

	 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
     driver.get(path+"/3x/x21a/scripts/x21aApp/testJasmine/specRunner.html");	
     try{
		 element = driver.findElement (By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='14 specs, 0 failures'])[1]"));
	 }catch (Exception e){
		}
     Assert.assertNotNull(element);
     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
 }


 @AfterClass
 public static void closeBrowser(){
	 driver.quit();
	 System.out.println("Final test ");
 }
}
