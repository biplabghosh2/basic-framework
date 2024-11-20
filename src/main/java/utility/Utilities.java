package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.cucumber.java.Scenario;

public class Utilities {
	
	public static void takeSnapShot(WebDriver webdriver, Scenario scenario)
	{
		if(scenario.isFailed())
		{
			TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
			byte[] SrcFile = scrShot.getScreenshotAs(OutputType.BYTES);
			
			scenario.attach(SrcFile, "image/png", scenario.getName());
		}
	}
	
    public static String readProperties(String key) {
        Properties properties = new Properties();
        String propertiesFileName = "src\\test\\resources\\properties\\common.properties";
        String dbUrl = null;
        try (InputStream inputStream = new FileInputStream(propertiesFileName)) {
            // Load the properties file
            properties.load(inputStream);

            // Retrieve properties
            dbUrl = properties.getProperty(key);
            
            System.out.println(dbUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return dbUrl;
    }
	
	public static String readExcel(String firstColumnValue, String headerName) {
		String excelFilePath = "src\\test\\resources\\dataTable\\data.xlsx";
		String cellValue = null;

		try {
			InputStream excelFile = new FileInputStream(excelFilePath);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0); // Get first sheet

			int headerRowNum = 0; // Assuming headers are in the first row
			Row headerRow = sheet.getRow(headerRowNum);

			int colNum = getColumnIndex(headerRow, headerName);

			int rowNum = getRowIndex(sheet, firstColumnValue);

			Row row = sheet.getRow(rowNum);
			Cell cell = row.getCell(colNum);

			cellValue = getCellValue(cell);
			

			workbook.close();
			excelFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
	}

	private static int getColumnIndex(Row headerRow, String headerName) {
		for (Cell cell : headerRow) {
			if (cell.getStringCellValue().equalsIgnoreCase(headerName)) {
				return cell.getColumnIndex();
			}
		}
		return -1;
	}

	private static int getRowIndex(Sheet sheet, String firstColumnValue) {
		for (Row row : sheet) {
			Cell cell = row.getCell(0); // Assuming the first column is at index 0
			if (cell != null && cell.getStringCellValue().equalsIgnoreCase(firstColumnValue)) {
				return row.getRowNum();
			}
		}
		return -1;
	}

	private static String getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "";
		default:
			return "Unknown Cell Type";
		}
	}

}
