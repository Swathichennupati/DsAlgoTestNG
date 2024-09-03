package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        // Configure the reporter (e.g., theme, report name, etc.) if needed

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // Set system information or other global configurations if needed

        return extent;
    }
    
    public static ExtentReports getExtentReport() {
		return extent;

    } 
}