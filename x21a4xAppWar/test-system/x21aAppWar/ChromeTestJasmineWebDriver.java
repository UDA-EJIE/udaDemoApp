package x21aAppWar;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTestJasmineWebDriver {
 	private static ChromeDriver driver;
 	static String path;

 @BeforeClass
 public static void openBrowser(){
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
	 List<Integer> results = new ArrayList<Integer>();
	 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
     driver.get(path+"/x21a/scripts/x21aApp/testJasmine/specRunner.html");	
     try{
    	 tests = driver.findElements(By.className("jasmine-bar,jasmine-passed"));
	 }catch (Exception e){
		}
     Assert.assertNotNull(tests);
     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    if(tests != null){
    	Pattern pat = Pattern.compile("[0-9]");
        Matcher m = pat.matcher(tests.get(0).getText());
        while (m.find()) {
            System.out.println("  " + m.group() + " - ");
            results.add(Integer.valueOf(m.group()));
         }
        

        boolean testTodosCorrectos = false;
        if(results.get(1) == 0){
        	testTodosCorrectos = true;
        }
      
        Assert.assertTrue(testTodosCorrectos);
     System.out.println("Test: " + tests.get(0).getText());
    }
 }


 @AfterClass
 public static void closeBrowser(){
	 driver.quit();
	 System.out.println("Final test ");
 }
}
