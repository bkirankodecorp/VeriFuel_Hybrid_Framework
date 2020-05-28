package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e){

			throw (e);

		}

	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception{

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		}catch (Exception e){

			return"";

		}

	}

	public static int getCellDataNum(int RowNum, int ColNum) throws Exception{

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			int CellData1 = (int) Cell.getNumericCellValue();

			return CellData1;

		}

		catch (Exception e)
		{

			return ColNum ;

		}

	}

	public static Double getCellDataDecimals( int RowNum, int ColNum) throws Exception{

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			Double CellData2 = (Double) Cell.getNumericCellValue();

			return CellData2;

		}

		catch (Exception e)
		{

			return null ;

		}

	}
	
	@SuppressWarnings("null")
	public static float getCellDataDecimalsfloat( int RowNum, int ColNum) throws Exception{

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			float CellData2 = (float) Cell.getNumericCellValue();

			return CellData2;

		}

		catch (Exception e)
		{

			return (Float) null ;

		}

	}

	//This method is to write in the Excel cell, Row num and Col num are the parameters

	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum,org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}




			// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);

			ExcelWBook.write(fileOut);

			fileOut.flush();

			fileOut.close();

		}catch(Exception e){

			throw (e);

		}

	}

	
	public static void setCellValue(int value,  int RowNum, int ColNum) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum,org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(value);

			} else {

				Cell.setCellValue(value);

			}




			// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);

			ExcelWBook.write(fileOut);

			fileOut.flush();

			fileOut.close();

		}catch(Exception e){

			throw (e);

		}

	}
}
