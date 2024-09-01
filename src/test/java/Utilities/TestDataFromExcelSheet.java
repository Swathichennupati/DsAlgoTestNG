package Utilities;
	import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

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

        List<Object[]> data = new ArrayList<>(); // Exclude the header row


        // Read each row and store the data in the Object[][]
        for (int i = 1; i < numberOfRows; i++) { // Start from 1 to skip the header row
            Row row = sheet.getRow(i);
            if (isRowEmpty(row)) {
                continue; // Skip empty rows
            }
            Object[] rowData = new Object[numberOfColumns];
            for (int j = 0; j < numberOfColumns; j++) {
            	Cell cell = row.getCell(j);
            	rowData[j] = cell != null ? cell.toString() : "";// Store each cell value as String
            }
            data.add(rowData);
        }


//        workbook.close();
//        file.close();

        return data.toArray(new Object[data.size()][]);
    }
	public static Object[][] getDataFromSheetRowwise(String sheetName, int rowIndex) {
        List<Object[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet " + sheetName + " does not exist.");
                return new Object[0][0];
            }

            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Object[] rowData = new Object[row.getLastCellNum()];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData[j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    rowData[j] = cell.getDateCellValue();
                                } else {
                                	double numericValue = cell.getNumericCellValue();
                                    if (numericValue == (int) numericValue) {
                                        rowData[j] = (int) numericValue; // Cast to int if applicable
                                    } else {
                                        rowData[j] = numericValue; // Use double for non-integer numeric values
                                    }                                }
                                break;
                            case BOOLEAN:
                                rowData[j] = cell.getBooleanCellValue();
                                break;
                            case FORMULA:
                                rowData[j] = cell.getCellFormula();
                                break;
                            default:
                                rowData[j] = "Unknown Value";
                                break;
                        }
                    }
                }
                data.add(rowData);
            } else {
                System.out.println("Row " + rowIndex + " does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }

    
    public  void removeTestData() {
        try {
			testData.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private static boolean isRowEmpty(Row row) {
	    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
	        Cell cell = row.getCell(j);
	        if (cell != null && cell.getCellType() != CellType.BLANK) {
	            return false;
	        }
	    }
	    return true;
	}

}