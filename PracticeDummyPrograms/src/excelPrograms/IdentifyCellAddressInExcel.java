package excelPrograms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IdentifyCellAddressInExcel {
	public static void main(String args[]) {
		String fileLocation = "C:\\Users\\Akanksha Tomar\\Documents\\CreateEvidenceFile.xlsx";
		String searchString = "Vaibhav";
		
		try {
			FileInputStream fileInputStream = new FileInputStream(fileLocation);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(1);
			CellAddress address =null;
			
			//identify the number of rows an column
			int lastRowNum = sheet.getLastRowNum()+1;
			System.out.println("lastRowNum : "+lastRowNum);
			
			for (int i=0; i<lastRowNum; i++){
				Row row = sheet.getRow(i);
				int lastCellNum =-1;
				try{
				lastCellNum= row.getLastCellNum();
				}catch(NullPointerException e){
					
				}
				System.out.println("lastCellNum : "+lastCellNum);
				if (lastCellNum != -1){
				for (int j=1; j<=lastCellNum; j++){
					System.out.println("Inside Cell Loop");
					Cell cell = row.getCell(j);
					String cellValue ;
					try{
					cellValue = cell.getStringCellValue();
					}catch (NullPointerException e){
						cellValue = "";
					}
					System.out.println("Cell Value : "+cellValue);
					if (cellValue.equals(searchString)){
						 address = cell.getAddress();
						 System.out.println("Cell Address : " +address.formatAsString());
					}
				}
				}
				System.out.println("==================================");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
