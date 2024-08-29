package Utilities;
	import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestDataFromExcelSheet {

		
	    private static String EXCEL_FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\DsAlgoTestData.xlsx";
	    private  ThreadLocal<Map<String, String>> testData = new ThreadLocal<>();

	    public  static Map<String, String> getTestData(String sheetName, int rowNum) throws IOException {
	        Map<String, String> dataMap = new HashMap<>();
        FileInputStream file = new FileInputStream(EXCEL_FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet with name " + sheetName + " does not exist.");
        }

        // Read header row
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            throw new IllegalArgumentException("Header row is missing.");
        }

        // Get the specified row
        Row dataRow = sheet.getRow(rowNum);
        if (dataRow == null) {
            throw new IllegalArgumentException("Row number " + rowNum + " does not exist.");
        }

        // Read each cell in the row
        for (int i = 0; i < dataRow.getLastCellNum(); i++) {
            String key = headerRow.getCell(i) != null ? headerRow.getCell(i).toString() : "";
            String value = dataRow.getCell(i) != null ? dataRow.getCell(i).toString() : "";
            dataMap.put(key, value);
        }
    
    return dataMap;
}

	    

	    public  void removeTestData() {
	        try {
				testData.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
