package Utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataproviderUtilities
{
	 @Test(dataProvider = "TitleValidationTestData")
	    @Parameters("sheetName1")
	    public void testWithSheet1(String col1, String col2, String col3,String col4) {
	        System.out.println("Sheet 1 - Column 1: " + col1 + ", Column 2: " + col2);
	    }
	 

	    @Test(dataProvider = "NumberOfLinksTestData")
	    @Parameters("sheetName2")
	    public void testWithSheet2(String col1, int col2) {
	    	System.out.println("Sheet 2 - Column 1: " + col1 + ", Column 2: " + col2);
	    }
	    @Test(dataProvider = "LoginTestData")
	    @Parameters("sheetName3")
	    public void testWithSheet2(String col1, String col2,String col3,int rownumber) {
	    	System.out.println("Sheet 2 - Column 1: " + col1 + ", Column 2: " + col2 + ", Column 3: " + col3 + ", Column4 : " + rownumber);
	    }

	    @DataProvider(name = "TitleValidationTestData")
	    public Object[][] sheet1DataProvider(ITestContext context) throws IOException {
	        String sheetName1 = context.getCurrentXmlTest().getParameter("sheetName1");
	        return TestDataFromExcelSheet.getTestData(sheetName1);
	    }
	    @DataProvider(name = "NumberOfLinksTestData")
	    public Object[][] sheet2DataProvider(ITestContext context) {
	        String sheetName2 = context.getCurrentXmlTest().getParameter("sheetName2");
	        int rowIndex1 = Integer.parseInt(context.getCurrentXmlTest().getParameter("rowIndex1"));
	        return TestDataFromExcelSheet.getDataFromSheetRowwise(sheetName2, rowIndex1);
	    }
	    @DataProvider(name = "UsernameData")
	    public static Object[][] getLoginData() {
	        return new Object[][]{
	            {"", "Please fill out this field.","username"},
	            {"more than 150 characters", "the username exceeds the character limit of 150","username"},
	            
	        };
	    }
	    @DataProvider(name = "PasswordValidationData")
	    public static Object[][] passwordData() {
	        return new Object[][]{
	            {"@,.,+,-,_", "password","password_mismatch:The two password fields didn’t match."},
	            {"@,.,+,-,_", "123456789","password_mismatch:The two password fields didn’t match."},
	            {"@,.,+,-,_","Af589","password_mismatch:The two password fields didn’t match."},	            
	        };
	    }
	    @DataProvider(name = "PasswordMismatch")
	    public static Object[][] passwordMismatchData() {
	        return new Object[][]{
	        	{"admin1234","Admin@789","password_mismatch:The two password fields didn’t match."}	            
	        };
	    }
	    @DataProvider(name = "dropdownOptions")
	    public static Object[][] selectdropdownOptions() {
	    	return new Object[][]{
	    		{"Arrays","Array"},	        	
	        	{"Linked List","Linked List"},
	        	{"Stack","Stack"},
	        	{"Queue","Queue"},
	        	{"Tree","Tree"},
	        	{"Graph","Graph"}

	        	
	        };

	    }
	  

}
