package com.test.automation.uiAutomation.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * File for HSSF testing/examples
 *
 * THIS IS NOT THE MAIN HSSF FILE!! This is a utility for testing functionality.
 * It does contain sample API usage that may be educational to regular API
 * users.
 */
public final class HSSFReadWrite {

	/**
	 * creates an {@link HSSFWorkbook} with the specified OS filename.
	 */
	public static HSSFWorkbook readFile(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		try {
			return new HSSFWorkbook(fis); // NOSONAR - should not be closed here
		} finally {
			fis.close();
		}
	}

	/**
	 * given a filename this outputs a sample sheet with just a set of
	 * rows/cells.
	 */
	private static void testCreateSampleSheet(String outputFilename) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			HSSFSheet s = wb.createSheet();
			HSSFCellStyle cs = wb.createCellStyle();
			HSSFCellStyle cs2 = wb.createCellStyle();
			HSSFCellStyle cs3 = wb.createCellStyle();
			HSSFFont f = wb.createFont();
			HSSFFont f2 = wb.createFont();

			f.setFontHeightInPoints((short) 12);
			f.setColor((short) 0xA);
			f.setBold(true);
			f2.setFontHeightInPoints((short) 10);
			f2.setColor((short) 0xf);
			f2.setBold(true);
			cs.setFont(f);
			cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("($#,##0_);[Red]($#,##0)"));
			cs2.setBorderBottom(BorderStyle.THIN);
			cs2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs2.setFillForegroundColor((short) 0xA);
			cs2.setFont(f2);
			wb.setSheetName(0, "HSSF Test");
			int rownum;
			for (rownum = 0; rownum < 300; rownum++) {
				HSSFRow r = s.createRow(rownum);
				if ((rownum % 2) == 0) {
					r.setHeight((short) 0x249);
				}

				for (int cellnum = 0; cellnum < 50; cellnum += 2) {
					HSSFCell c = r.createCell(cellnum);
					c.setCellValue(rownum * 10000 + cellnum + (((double) rownum / 1000) + ((double) cellnum / 10000)));
					if ((rownum % 2) == 0) {
						c.setCellStyle(cs);
					}
					c = r.createCell(cellnum + 1);
					c.setCellValue(new HSSFRichTextString("TEST"));
					// 50 characters divided by 1/20th of a point
					s.setColumnWidth(cellnum + 1, (int) (50 * 8 / 0.05));
					if ((rownum % 2) == 0) {
						c.setCellStyle(cs2);
					}
				}
			}

			// draw a thick black border on the row at the bottom using BLANKS
			rownum++;
			rownum++;
			HSSFRow r = s.createRow(rownum);
			cs3.setBorderBottom(BorderStyle.THICK);
			for (int cellnum = 0; cellnum < 50; cellnum++) {
				HSSFCell c = r.createCell(cellnum);
				c.setCellStyle(cs3);
			}
			s.addMergedRegion(new CellRangeAddress(0, 3, 0, 3));
			s.addMergedRegion(new CellRangeAddress(100, 110, 100, 110));

			// end draw thick black border
			// create a sheet, set its title then delete it
			wb.createSheet();
			wb.setSheetName(1, "DeletedSheet");
			wb.removeSheetAt(1);

			// end deleted sheet
			FileOutputStream out = new FileOutputStream(outputFilename);
			try {
				wb.write(out);
			} finally {
				out.close();
			}
		} finally {
			wb.close();
		}
	}

	public static Object[][] getCredential() {
		
		List<List<String>> userNamePasswordList = new ArrayList<List<String>>();

		try {
			HSSFWorkbook wb = HSSFReadWrite
					.readFile("src//main//java/com//test//automation//uiAutomation//data//inputData.xls");
			//k is the sheet number
			for (int sheetNumber = 0; sheetNumber < wb.getNumberOfSheets(); sheetNumber++) {
				HSSFSheet sheet = wb.getSheetAt(sheetNumber);
				int maxRows = sheet.getPhysicalNumberOfRows();
				System.out.println("Sheet " + sheetNumber + " \"" + wb.getSheetName(sheetNumber) + "\" has " + maxRows + " row(s).");
				for (int r = 0; r < maxRows; r++) {
					HSSFRow row = sheet.getRow(r);
					if (r == 0) {
						continue; //skip the first row or the header row
					}
					System.out.println(
							"\nROW " + row.getRowNum() + " has " + row.getPhysicalNumberOfCells() + " cell(s).");
					List<String> userNamePassword = new ArrayList<String>();
					
					for (int c = 0; c < row.getLastCellNum(); c++) {
						HSSFCell cell = row.getCell(c);

						if (cell != null) {
							if (cell.getCellTypeEnum() == CellType.STRING) {
								userNamePassword.add(cell.getStringCellValue());
							}
						}

					}
					userNamePasswordList.add(userNamePassword);
				}

			}

		} catch (IOException e) {

		}
		Object[][] returnValues = new Object[userNamePasswordList.size()][2];
		int counter = 0;
		for(List<String> unp: userNamePasswordList)
		{

			returnValues[counter][0] = unp.get(0);
			returnValues[counter][1] = unp.get(1);
			counter++;
		}
		return returnValues;
		
	}
	
}
