package TestSuites;

    import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.time.Duration;

	import java.util.Properties;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver; 

	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.BeforeClass; 


	public class BaseTest {

	    public WebDriver driver;
	    public Properties prop;

	    @BeforeClass(alwaysRun = true)
	    public void setUp() throws IOException {
	        prop = new Properties();
	        InputStream input = null;

	        try {
	            input = new FileInputStream(System.getProperty("user.dir")
	                    + "\\src\\test\\resources\\config\\global.properties");

	            prop.load(input);

	            String browserName = prop.getProperty("browserName");
	            initilizebrowser(browserName);
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
	    }

	    private void initilizebrowser(String browserName) {
	        if (browserName.equals("chrome")) {
	            driver = new ChromeDriver();
	        } else if (browserName.equals("firefox")) {
	            driver = new FirefoxDriver();

	        } else if (browserName.equals("edge")) {
	            driver = new EdgeDriver(); 

	        } else {
	            throw new IllegalArgumentException("Unsupported browser: " + browserName);
	        }

	        driver.manage().window().maximize(); 

	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90)); 

	        driver.get(prop.getProperty("url"));
	    }

	    public void quitDriver() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}


