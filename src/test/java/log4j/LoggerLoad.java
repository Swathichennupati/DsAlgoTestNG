package log4j;

import org.apache.logging.log4j.LogManager;


import org.apache.logging.log4j.Logger;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//public class TestLogger {
//
//	public static WebDriver driver;
//	public static Logger log;
//	public static void main(String[] args) {
//
//		log= LogManager.getLogger(TestLogger.class);
//
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get("https://www.myntra.com/");
//		log.info("Logged into Myntra");
//		try {
//			boolean text= driver.findElement(By.xpath("//a[contains(text(),'Women')]")).isDisplayed();
//		}
//		catch(Exception e) {
//			System.out.print(e.getMessage());
//			log.error("Exception occured", new Exception("Element Not Found"));
//		}
//		finally {
//			driver.quit();
//			log.info("Quitting the driver");
//		}
//	}	
//}

public class LoggerLoad {
	
	private static Logger logger = LogManager.getLogger();
	
	public static void info(String message) 
	{
	logger.info(message);
	}
	
	public static void warn(String message)
	{
	logger.warn(message);
	}
	
	public static void error(String message)
	{
	logger.error(message);
	}
	
	public static void fatal(String message)
	{
	logger.fatal(message);
	}
	
	public static void debug(String message)
	{
	logger.debug(message);
	}
}
