package Utilities;
	import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestDataFromExcelSheet {

	
	private static String EXCEL_FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\DsAlgoTestData.xlsx";
    private  ThreadLocal<Map<String, String>> testData = new ThreadLocal<>();
    static Workbook book;
	static Sheet sheet;
	public static Object[][] getTestData(String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(EXCEL_FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet with name " + sheetName + " does not exist.");
        }

        int numberOfRows = sheet.getPhysicalNumberOfRows();
        int numberOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[numberOfRows - 1][numberOfColumns]; // Exclude the header row

        // Read each row and store the data in the Object[][]
        for (int i = 1; i < numberOfRows; i++) { // Start from 1 to skip the header row
            Row row = sheet.getRow(i);
            for (int j = 0; j < numberOfColumns; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell != null ? cell.toString() : ""; // Store each cell value as String
            }
        }

//        workbook.close();
//        file.close();

        return data;
    }
    
    

    public  void removeTestData() {
        try {
			testData.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

