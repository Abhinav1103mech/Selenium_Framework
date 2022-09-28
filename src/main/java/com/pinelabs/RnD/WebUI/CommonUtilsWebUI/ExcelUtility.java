package com.pinelabs.RnD.WebUI.CommonUtilsWebUI;

import com.pinelabs.RnD.WebUI.Constants.FilePaths;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ExcelUtility.java- This class reads the excel file by fetching the
 * excelFileName provided by user in config.properties
 *
 * @methods -getExcelSheetPath(), getExcelFile(),getExcelSheetName(),
 *          getExcelSheetNumber(), getExcelSheetFormat(),
 *          launchWorkbook(),getSheetFromExcel(), excelFileReading().
 * @author Vanshika Chauhan
 * @version 1.0
 *
 */

public class ExcelUtility {
	static Properties configProp;
	static String excelFileName;
	static Workbook book;
	static FileInputStream fis;

	/**
	 * getExcelSheetPath()- User needs to put the required testData/ Excel file
	 * in resources folder.This function will return the path of excelFile
	 * mentioned in config.properties.
	 *
	 * @param -None
	 * @return - Path of ExcelFile
	 * @throws -IOException
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */
	private static String getExcelSheetPath() {
		String excelFileName = getExcelFile();
		return FilePaths.excelDataFile + File.separator + excelFileName;

	}

	/**
	 * getExcelFile()- Reads the key 'ExcelFileName' from config.properties and
	 * returns it's value.The expected value of 'ExcelFileName' will be the name
	 * of the .xlsx/xls file.
	 *
	 * @param -None
	 * @return -Value of ExcelFileName from config.properties
	 * @throws -IOException
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */
	private static String getExcelFile()  {
		configProp = CommonUtils.readPropertyfile(FilePaths.excelDataProperties);
		excelFileName = configProp.getProperty("ExcelFileName");
		return excelFileName;

	}

	/**
	 * getExcelSheetName()- Reads the key 'ExcelSheetName' from
	 * config.properties and returns it's value.
	 *
	 * @param -None
	 * @return -Value of ExcelSheetName from config.properties
	 * @throws -Nothing
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */

	private static String getExcelSheetName() {
		String sheetName = configProp.getProperty("ExcelSheetName");

		return sheetName;

	}

	/**
	 * getExcelSheetNumber()- Reads the key 'ExcelSheetNumber' from
	 * config.properties convert it to integer and returns it's value.Indexing
	 * of sheet will begin from 0.
	 *
	 * @param -None
	 * @return -Value of ExcelSheetNumber from config.properties
	 * @throws -No
	 *             Exception
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */

	private static int getExcelSheetNumber() {
		String sNumber = configProp.getProperty("ExcelSheetNumber");
		return Integer.parseInt(sNumber);
	}

	/**
	 * getExcelSheetNumber()- This method reads the key 'ExcelSheetNumber' from
	 * config.properties and returns it's value.The expected value of
	 * 'ExcelSheetNumber' will be the sheet number of excel file returned from
	 * getExcelFile() function.
	 *
	 * @param -None
	 * @return -Value of ExcelSheetName from config.properties
	 * @throws -Nothing
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */
	private static String getExcelSheetFormat() {
		return excelFileName.substring(excelFileName.indexOf("."));
	}

	/**
	 * launchWorkbook()- Constructs the XSSF/HSSFworkbook as per the format of
	 * excel file and return the instance of Workbook so created.
	 *
	 * @param -None
	 * @return -Instance of Workbook
	 * @throws -IOException
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */

	private static Workbook launchWorkbook()  {
		String excelSheetPath = getExcelSheetPath();
		System.out.println("Excel sheet path is " + excelSheetPath);

		 File f = new File(excelSheetPath);
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String fileExtensionName = getExcelSheetFormat();
		System.out.println("File extension is " + fileExtensionName);
		try{
			if (fileExtensionName.equalsIgnoreCase(".xlsx"))
				book = new XSSFWorkbook(fis);
			else if (fileExtensionName.equalsIgnoreCase(".xls"))
				book = new HSSFWorkbook(fis);

	} catch (IOException e) {
		e.printStackTrace();
	}
		return book;
	}

	/**
	 * getSheetFromExcel()- Reads the sheetName or sheetNumber mentioned in
	 * config.properties and returns the appropriate sheet of excel workbook.
	 *
	 * @param -None
	 * @return -sheet
	 * @throws -IOException
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */

	private static Sheet getSheetFromExcel() {
		String sheetName = null;
		int sheetNumber;
		Workbook myBook;
		Sheet sheet;
		String excelFileName = getExcelFile();
		System.out.println("ExcelFile name is " + excelFileName);
		sheetName = getExcelSheetName();
		if (!sheetName.isEmpty()) {
			myBook = launchWorkbook();
			sheet = myBook.getSheet(sheetName);
			System.out.println("Sheet name is " + sheetName);
		} else {
			sheetNumber = getExcelSheetNumber();
			myBook = launchWorkbook();
			sheet = myBook.getSheetAt(sheetNumber);
		}
		return sheet;
	}

	/**
	 * excelFileReading()- Reads the excel file cell by cell from first Row to
	 * last row which have content in it and returns the 2D object array of the
	 * entire excel sheet further closing the connection at the end.
	 *
	 * @param -None
	 * @return -sheet
	 * @throws -IOException
	 * @author -Vanshika Chauhan
	 * @version -1.0
	 */

	public static Object[][] excelFileReading() {
		Object[][] arr = null;
		launchWorkbook();
		Sheet sheet = getSheetFromExcel();

		int firstRow = sheet.getFirstRowNum();
		System.out.println("First row is " + firstRow);
		int lastRow = sheet.getLastRowNum();
		System.out.println("last row is " + lastRow);
		for (int i = firstRow; i < lastRow; i++) {
			// since the value of first row is 0 and we don't have to read the
			// header so getRow is fetching the 0+1 =1st row
			Row rows = sheet.getRow(i + 1);
			int firstCell = rows.getFirstCellNum();
			System.out.println("First cell is" + firstCell);
			int lastCell = sheet.getRow(i).getLastCellNum();
			System.out.println("Last cell is " + lastCell);
			for (int j = firstCell; j <= lastCell - 1; j++) {
				Cell cell = rows.getCell(j);
				System.out.println("Cell is " + cell);
				arr = new Object[lastRow][lastCell];
				try {
					//This will check if the type of value in cell.
					switch (cell.getCellType()) {

					case NUMERIC:
						arr[i][j] = cell.getNumericCellValue();
						System.out.println("In case numeric: value is :" + arr[i][j]);
						break;
					case STRING:
						System.out.println("i is " + i + "j is " + j);
						arr[i][j] = cell.getStringCellValue();
						System.out.println("In case String: value is :" + arr[i][j]);
						break;
					case BLANK:
						arr[i][j] = "";
						System.out.println("In Case Blank: cell is empty :" + arr[i][j]);
						j++;
						break;

					default:
						break;
					}
				} catch (NullPointerException ne) {
					System.out.println("In null pointer exception");
				}
				//System.out.println("Value in arr is " + arr[i][j]);
			}
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;

	}

}
