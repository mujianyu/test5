package team.swust.st.experment4;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.opentest4j.AssertionFailedError;

/**
 * 
 * @author mjy
 *
 */
class BMITest {
	/**
	 * 
	 */
	public static WebDriver driver;
	/**
	 * 
	 */
	public String baseUrl;
	/**
	 * 
	 */
	public boolean acceptNextAlert = true;
	/**
	 * 
	 */
	public static  StringBuffer verificationErrors = new StringBuffer();
	
    
	
	/**
	 * 
	 * @throws Exception 错误
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "F:\\Googledownload\\geckodriver-v0.24.0-win64\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	/**
	 * 
	 * @throws Exception 错误
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	    	fail(verificationErrorString);
	    }
	}

	
	
	/**
	 * 
	 * @throws Exception 错误
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	
	/**
	 * 
	 * @throws Exception 错误
	 */
	@AfterEach
	void tearDown() throws Exception {
	}


	
	/**
	 * 
	 * @param i 输入值
	 * @param u 进制
	 * @param x1 二进制
	 * @param x2 十进制
	 * @param x3 8进制
	 * @param x4  16进制
	 */
	@ParameterizedTest
	@CsvFileSource(resources="/st_data.csv",numLinesToSkip=1,encoding="GBK")
	void test(String i,String u,String x1,String x2,String x3,String x4) {
		driver.get("http://192.168.217.30:20006/");
		driver.findElement(By.linkText("进制转换")).click();
		driver.switchTo().frame("main");
		driver.findElement(By.id("sNum")).click();
		driver.findElement(By.id("sNum")).clear();
		driver.findElement(By.id("sNum")).sendKeys(i);
		driver.findElement(By.className("layui-select-title")).click();
		String up="";
		switch(u) {
			case "2":
		    	up="1";
		    	break;
		    case "8":
		    	up="2";
		    	break;
		    case "10":
		    	up="3";
		    	break;
		    case "16":
		    	up="4";
		    	break;
		}
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='选择进制：'])[1]/following::dd["+up+"]")).click();
	    driver.findElement(By.xpath("//input[@value='计算']")).click();
		WebElement w1=driver.findElement(By.id("scale_2"));
		String b="转换的数据格式不对";
	    boolean f=false;
		Integer c=0;
		try {
		    assertEquals(x1, driver.findElement(By.id("scale_2")).getAttribute("value"));
		    if(x1.equals(b)) {
		    	f=true;
		    }
		}catch(AssertionFailedError e) {
			verificationErrors.append("转二进制错误/");
		    SimpleDateFormat formatter= new SimpleDateFormat("HH_mm_ss");
		    Date date = new Date(System.currentTimeMillis());
		    String m=formatter.format(date);
		    Snapshot.takeSnap((TakesScreenshot)driver,m+c.toString()+".jpg");
		    c++;
		}
		driver.findElement(By.id("scale_10"));
		try {
			if(!f) {
				assertEquals(x2, driver.findElement(By.id("scale_10")).getAttribute("value"));
		    }
		}catch(AssertionFailedError e) {
			verificationErrors.append("转十进制错误/");
			SimpleDateFormat formatter= new SimpleDateFormat("HH_mm_ss");
			Date date = new Date(System.currentTimeMillis());
			String m=formatter.format(date);
			Snapshot.takeSnap((TakesScreenshot)driver,m+c.toString()+".jpg");
			c++;
		}
		driver.findElement(By.id("scale_8"));
		try {
		    if(!f) {
		        assertEquals(x3, driver.findElement(By.id("scale_8")).getAttribute("value"));
		    }
		}catch(AssertionFailedError e) {
			verificationErrors.append("转八进制错误/");
			SimpleDateFormat formatter= new SimpleDateFormat("HH_mm_ss");
			Date date = new Date(System.currentTimeMillis());
			String m=formatter.format(date);
			Snapshot.takeSnap((TakesScreenshot)driver,m+c.toString()+".jpg");
			c++;
		}
		driver.findElement(By.id("scale_16"));
		try {
		    if(!f) {
		        assertEquals(x4, driver.findElement(By.id("scale_16")).getAttribute("value"));
		    }
		}catch(AssertionFailedError e) {
			    verificationErrors.append("转16进制错误");
			    SimpleDateFormat formatter= new SimpleDateFormat("HH_mm_ss");
			    Date date = new Date(System.currentTimeMillis());
			    String m=formatter.format(date);
			    Snapshot.takeSnap((TakesScreenshot)driver,m+c.toString()+".jpg");
			    c++;
		}
		
	}

	/**
	 * 
	 * @param by 参数
	 * @return 正误
	 */
	 public boolean isElementPresent(By by) {
		 try {
			 driver.findElement(by);
		     return true;
		 } catch (NoSuchElementException e) {
			 return false;
		 }
	}
	
	 /**
	  * 
	  * @return tf
	  */
	 public boolean isAlertPresent() {
		 try {
			 driver.switchTo().alert();
		     return true;
		 } catch (NoAlertPresentException e) {
			 return false;
		 }
	 }
	
	 /**
	  * 
	  * @return tf
	  */
	 public  String closeAlertAndGetItsText() {
		 try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		 } finally {
		      acceptNextAlert = true;
		 }
	}
}
