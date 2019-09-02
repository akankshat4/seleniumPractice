package excelPrograms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApplyFormulaInExcel {

	public static void main(String[] args) {
		String fileLocation = "C:\\Users\\Akanksha Tomar\\Documents\\CreateEvidenceFile.xlsx";
		
		try {
			FileInputStream fileInputStream = new FileInputStream(fileLocation);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row =  sheet.createRow(1);
			XSSFCell cell = row.createCell(2);
			String formula = "SUM(B4:C4)";
			
			cell.setCellFormula(formula);
			cell.setCellType(CellType.FORMULA);
			//cell.setCellFormula("");
			System.out.println(cell.getCellFormula());
			
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
