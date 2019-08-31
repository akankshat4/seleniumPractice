package com.practice.utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileOperationsUtility {
	private FileInputStream fileInputStream;
	private Workbook workbook;
	private Sheet sheet;
	private String fileLocation;
	private String sheetName;
	
	 public FileOperationsUtility(String inputFileLocation, String sheetName) {
		super();
		this.fileLocation = inputFileLocation;
		this.sheetName = sheetName;
	}
	
	 public void initialize(){
		 try {
				fileInputStream = new FileInputStream(fileLocation);
				workbook = new XSSFWorkbook(fileInputStream);
			
		 sheet = workbook.getSheet(sheetName);
		 //System.out.println(sheet.getSheetName());
		 //System.out.println("Sheet Name: "+sheet.getSheetName());
		 }catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 public String [][] getUserDetailsFromExcel(){
		 String[][] userDetails = null;
		 initialize();
		 //System.out.println(sheet.getSheetName());
		 int rowNum = sheet.getLastRowNum();
		 //System.out.println(rowNum);
		 int colNum = sheet.getRow(0).getLastCellNum();
		 //System.out.println(colNum);
		 userDetails = new String[rowNum][colNum];
		 
		 for(int i=1; i<=rowNum; i++){
			 Row row = sheet.getRow(i);
			 for (int j=0; j<colNum; j++){
				 Cell cell = row.getCell(j);
				 String cellValue = cell.getStringCellValue();
				 userDetails[i-1][j]=cellValue;
			 }
		 }	
		 
		 try {
			fileInputStream.close();
			 workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return userDetails;
	 }
	

	 public void markStatus(String testCaseName,String status){
		 try {
			System.out.println("Inside markStatus method");
			System.out.println("Status is : "+status);
			initialize();
			Row headerRow = sheet.getRow(0);
			
			int rowNum = sheet.getLastRowNum();
			System.out.println(sheet.getLastRowNum());
			int colNum = headerRow.getLastCellNum();
			System.out.println(colNum);
			int testCaseNameCellNo = 0;
			int statusCellNo = 0;
			
			for (int i=0; i<colNum; i++){
				 Cell cell = headerRow.getCell(i);
				 String cellData=cell.getStringCellValue();
				 if (cellData.equals("testCaseName"))
					 testCaseNameCellNo = i;
				 if (cellData.equals("status"))
					 statusCellNo = i;
			}
			
			System.out.println(testCaseNameCellNo);
			System.out.println(statusCellNo);
			for (int i=1; i<=rowNum; i++){
				Row  row = sheet.getRow(i);
				Cell cell = row.getCell(testCaseNameCellNo);
				Cell cellStatus = row.getCell(statusCellNo);
				String cellValueTestCaseName = cell.getStringCellValue();
				System.out.println(cellValueTestCaseName.equals(testCaseName));
				if (cellValueTestCaseName.equals(testCaseName))
					cellStatus.setCellValue(status);
				System.out.println(cellStatus.getStringCellValue());
			}
			
			fileInputStream.close();
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
