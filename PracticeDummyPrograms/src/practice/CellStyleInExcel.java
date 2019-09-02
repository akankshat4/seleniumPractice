package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellStyleInExcel {

	public static void main(String[] args) {
		String fileLocation = "C:\\Users\\Akanksha Tomar\\Documents\\CreateEvidenceFile.xlsx";
		try {
			FileInputStream fileInputStream = new FileInputStream(fileLocation);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(1);
			sheet.setColumnWidth(0, 8000);
			Row row = sheet.getRow(0);
			Cell firstCell = row.getCell(0);
			System.out.println(firstCell.getStringCellValue());
			
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			System.out.println(cellStyle.toString());
			
			//to set alignment of cell
			cellStyle.setAlignment(HorizontalAlignment.LEFT);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			
			//to set background color of cell
			cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			//to set rotation
			cellStyle.setRotation((short)45);
			
			//to set font style
			XSSFFont font = workbook.createFont();
			font.setBold(true);
			//font.setFamily(FontFamily.SCRIPT);
			font.setColor(IndexedColors.RED.getIndex());
			font.setFontName("Courier New");
			cellStyle.setFont(font);
			
			firstCell.setCellStyle(cellStyle);
			
			fileInputStream.close();
			FileOutputStream fileOutputStream =  new FileOutputStream(fileLocation);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
