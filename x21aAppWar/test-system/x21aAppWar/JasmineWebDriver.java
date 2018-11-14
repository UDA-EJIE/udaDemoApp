package x21aAppWar;

import java.util.List;
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
   
     path = System.getProperty("otc.proyecto.url.base");
     if(path == null || path.equals("")){
    	 path = "https://www.ejie.ejiedes.eus/rup-cac/x21aStatics/WebContent";
     }
 }

 @Test
 public void valid_testComponentUda(){
	 List<WebElement> tests = null;
	 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
     driver.get(path+"/3x/x21a/scripts/x21aApp/testJasmine/specRunner.html");	
     try{
		 element = driver.findElement (By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='2 specs, 0 failures'])[1]"));
		 tests = driver.findElements(By.className("jasmine-bar jasmine-passed"));
	 }catch (Exception e){
		}
     Assert.assertNotNull(element);
     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName() + " -- " +element.getText());
    if(tests != null){
     System.out.println("Test: " +tests.size() + " - " + tests.get(0).getText());
    }
 }


 @AfterClass
 public static void closeBrowser(){
	// driver.quit();
	 System.out.println("Final test ");
 }
}
