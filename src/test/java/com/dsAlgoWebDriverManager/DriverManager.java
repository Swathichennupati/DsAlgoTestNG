package com.dsAlgoWebDriverManager;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverManager {
	
	
	public static Properties prop;
	
	public static InputStream input;
	private static String browserType;
	 
	static {
    	prop=get_Properties_from_configfile();

		
	}

	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tldriver.get();
    }

    public static void initilizedriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
        	ChromeOptions options = new ChromeOptions();
//        	options.addArguments("--headless");
//        	options.addArguments("--disable-extensions");
//        	options.addArguments("--disable-popup-blocking");
//        	options.addArguments("--disable-gpu");
        	tldriver.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("firefox")) {
        	FirefoxOptions options = new FirefoxOptions();
//        	options.addArguments("--headless");
//        	options.addArguments("--disable-extensions");
//        	options.addArguments("--disable-popup-blocking");
//        	options.addArguments("--disable-gpu");
        	tldriver.set(new FirefoxDriver(options));
        }
        	else if (browser.equalsIgnoreCase("edge")) {
        		EdgeOptions options = new EdgeOptions();
//        		options.addArguments("--headless");
//            	options.addArguments("--disable-extensions");
//            	options.addArguments("--disable-popup-blocking");
//            	options.addArguments("--disable-gpu");
            	tldriver.set(new EdgeDriver(options));
        } 
        	else {
            throw new IllegalArgumentException("Browser type not supported");
        }
    }
    
  
    public static void quitDriver() {
        if (tldriver.get() != null) {
        	tldriver.get().quit();
        	tldriver.remove();
        }
    }
	    public static Properties getproperties() {
	        if (prop == null) {
	            prop = get_Properties_from_configfile();
	        }
	        return prop;
	    }
	    
	    
	    
	    public static  Properties get_Properties_from_configfile() {
			prop = new Properties();
			input = null;

			try {
				input = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\config\\global.properties");
				prop.load(input);

				System.out.println(prop.getProperty("browserName"));
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						System.out.println("wrongpath");
						e.printStackTrace();
					}

				}
			}
			return prop;
	}
}



