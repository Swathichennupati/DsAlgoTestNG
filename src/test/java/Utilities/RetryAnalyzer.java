package Utilities;
import org.testng.IAnnotationTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IRetryAnalyzer;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 5;  
    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
        	System.out.println("=================i'm in RetryAnalyzer-------------------------- ");
            retryCount++;
            return true;  
        }
        return false;  
    }
}


