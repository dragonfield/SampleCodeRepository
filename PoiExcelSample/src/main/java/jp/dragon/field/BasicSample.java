package jp.dragon.field;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class BasicSample {

	public static void main(String[] args) throws Exception {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
        cell.setCellValue("datetime");
		cell = row.createCell(1);
        cell.setCellValue("session");
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("2012/02/03 05:00:00");
        cell = row.createCell(1);
        cell.setCellValue("5");

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("2012/02/03 05:00:00");
        cell = row.createCell(1);
        cell.setCellValue("7");
        
		FileOutputStream fileOut = new FileOutputStream("result.xls");
        workbook.write(fileOut);
        fileOut.close();
        
        System.out.println("finished");
	}

}
