package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteToExcel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileLocation = "C:\\Users\\Akanksha Tomar\\Documents\\CreateEvidenceFile.xlsx";
		FileInputStream fileInputStream;
		try {
			File inputFile = new File(fileLocation);
			fileInputStream = new FileInputStream(inputFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			//XSSFSheet sheet = workbook.getSheet(0);
			workbook.setSheetName(0, "New Sheet");
			XSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println("new sheet created");
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue("Hello Akanksha");
			fileInputStream.close();
			
			FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
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
