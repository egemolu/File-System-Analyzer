import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class Main {
	private static int lessThanTenKBCounter = 0;
	private static long totalSizeLessThan10KB = 0;
	private static int tenToFiftyKBCounter = 0;
	private static long totalSize10To50KB = 0;
	private static int fiftyToHundredKBCounter = 0;
	private static long totalSize50To100KB = 0;
	private static int hundredToTwoHundredKBCounter = 0;
	private static long totalSize100To200KB = 0;
	private static int twoHundredToFiveHundredKBCounter = 0;
	private static long totalSize200To500KB = 0;
	private static int fiveHundredToThousandKBCounter = 0;
	private static long totalSize500To1000KB = 0;
	private static int thousandToTwoThousandKBCounter = 0;
	private static long totalSize1000To2000KB = 0;
	private static int twoThousandToFiveThousandKBCounter = 0;
	private static long totalSize2000To5000KB = 0;
	private static int fiveThousandToTenThousandKBCounter = 0;
	private static long totalSize5000To10000KB = 0;
	private static int tenThousandToTwentyThousandKBCounter = 0;
	private static long totalSize10000To20000KB = 0;
	private static int twentyThousandToFiftyThousandKBCounter = 0;
	private static long totalSize20000To50000KB = 0;
	private static int biggerThanFiftyThousandKBCounter = 0;
	private static long totalSizeBiggerThan50000KB = 0;

	private static HashMap<String, Integer> map = new HashMap<>();

	public static void main(String[] args) {
		// long startTime = System.nanoTime(); Measure Start Time
		// System.out.println(walk("/Users/", map)); Print HashMap
		walk("/Users", map);
		createExcelSheet(map);
		// long endTime = System.nanoTime(); //Measure Finish Time
		// System.out.println("Took " + (endTime - startTime) + " ns"); //Measure
		// Compile Time in NanoSec
	}

	private static HashMap<String, Integer> walk(String path, HashMap<String, Integer> map) {
		File root = new File(path);
		File[] fileList = root.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				walk(file.getAbsolutePath(), map);
				// System.out.println("Dir:" + file.getAbsoluteFile()); // Printing Directory
				// Name
			} else {
				// System.out.println("Extension == " + getFileExtension(file)); // Print File
				// Extension
				//System.out.println("File:" + file.getAbsoluteFile()); // Printing File
				// Directory
				//System.out.println("Size of file " + toKB(file));
				long size = toKB(file);
				setMapsWithSize(size);
				String extension = getFileExtension(file);
				if (!map.containsKey(extension)) {
					map.put(extension, 1);
				} else {
					int counter = map.get(extension) + 1;
					map.put(extension, counter);
				}
			}
		}
		return map;
	}

	private static void setMapsWithSize(long size) {
		if (size < 10) {
			lessThanTenKBCounter++;
			totalSizeLessThan10KB += size;
		} else if (10 <= size && size < 50) {
			tenToFiftyKBCounter++;
			totalSize10To50KB += size;
		} else if (50 <= size && size < 100) {
			fiftyToHundredKBCounter++;
			totalSize50To100KB += size;
		} else if (100 <= size && size < 200) {
			hundredToTwoHundredKBCounter++;
			totalSize100To200KB += size;
		} else if (200 <= size && size < 500) {
			twoHundredToFiveHundredKBCounter++;
			totalSize200To500KB += size;
		} else if (500 <= size && size < 1000) {
			fiveHundredToThousandKBCounter++;
			totalSize500To1000KB += size;
		} else if (1000 <= size && size < 2000) {
			thousandToTwoThousandKBCounter++;
			totalSize1000To2000KB += size;
		} else if (2000 <= size && size < 5000) {
			twoThousandToFiveThousandKBCounter++;
			totalSize2000To5000KB += size;
		} else if (5000 <= size && size < 10000) {
			fiveThousandToTenThousandKBCounter++;
			totalSize5000To10000KB += size;
		} else if (10000 <= size && size < 20000) {
			tenThousandToTwentyThousandKBCounter++;
			totalSize10000To20000KB += size;
		} else if (20000 <= size && size < 50000) {
			twentyThousandToFiftyThousandKBCounter++;
			totalSize20000To50000KB += size;
		} else if (50000 <= size) {
			biggerThanFiftyThousandKBCounter++;
			totalSizeBiggerThan50000KB += size;
		}
	}

	private static String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return ""; // Empty extension
		}
		return name.substring(lastIndexOf);
	}

	// Must add "POI" Jar files to project library. Unless You cannot use Excel
	// functions.
	private static void createExcelSheet(HashMap<String, Integer> map) {
		try {
			String filename = "/Users/egemolu/Desktop/CS350.xls"; // Location of Excel file where you want to save.
			HSSFWorkbook workbook = new HSSFWorkbook(); // Create new Workbook
			HSSFSheet sheet = workbook.createSheet("Data"); // Create new Excel Sheet with a specific name.
			HSSFRow rowhead = sheet.createRow((short) 0); // Create first row
			rowhead.createCell(0).setCellValue("File Extension"); // Set first column name
			rowhead.createCell(1).setCellValue("File Count"); // Set second column name
			int row_counter = 1; // Row Counter initialized with 1 because we already created first row
			for (Entry<String, Integer> entry : map.entrySet()) {
				HSSFRow row = sheet.createRow((short) row_counter);
				row.createCell(0).setCellValue(entry.getKey());
				row.createCell(1).setCellValue(entry.getValue());
				row_counter++;
			}
			setRows(sheet);
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			// System.out.println(map.size()); //File Extension Count
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void setRows(HSSFSheet sheet) {
		HSSFRow row0 = sheet.getRow(0);
		HSSFRow row1 = sheet.getRow(1);
		HSSFRow row2 = sheet.getRow(2);
		HSSFRow row3 = sheet.getRow(3);
		HSSFRow row4 = sheet.getRow(4);
		HSSFRow row5 = sheet.getRow(5);
		HSSFRow row6 = sheet.getRow(6);
		HSSFRow row7 = sheet.getRow(7);
		HSSFRow row8 = sheet.getRow(8);
		HSSFRow row9 = sheet.getRow(9);
		HSSFRow row10 = sheet.getRow(10);
		HSSFRow row11 = sheet.getRow(11);
		HSSFRow row12 = sheet.getRow(12);
		row0.createCell(5).setCellValue("Size Range");
		row0.createCell(6).setCellValue("Count");
		row0.createCell(7).setCellValue("Total KBytes");

		row1.createCell(5).setCellValue("0 < Size < 10 KB ");
		row1.createCell(6).setCellValue(lessThanTenKBCounter);
		row1.createCell(7).setCellValue(totalSizeLessThan10KB);

		row2.createCell(5).setCellValue("10 KB <= Size < 50 KB ");
		row2.createCell(6).setCellValue(tenToFiftyKBCounter);
		row2.createCell(7).setCellValue(totalSize10To50KB);

		row3.createCell(5).setCellValue("50 KB <= Size < 100 KB ");
		row3.createCell(6).setCellValue(fiftyToHundredKBCounter);
		row3.createCell(7).setCellValue(totalSize50To100KB);

		row4.createCell(5).setCellValue("100 KB <= Size < 200 KB ");
		row4.createCell(6).setCellValue(hundredToTwoHundredKBCounter);
		row4.createCell(7).setCellValue(totalSize100To200KB);

		row5.createCell(5).setCellValue("200 KB <= Size < 500 KB ");
		row5.createCell(6).setCellValue(twoHundredToFiveHundredKBCounter);
		row5.createCell(7).setCellValue(totalSize200To500KB);

		row6.createCell(5).setCellValue("500 KB <= Size < 1000 KB ");
		row6.createCell(6).setCellValue(fiveHundredToThousandKBCounter);
		row6.createCell(7).setCellValue(totalSize500To1000KB);

		row7.createCell(5).setCellValue("1000 KB <= Size < 2000 KB ");
		row7.createCell(6).setCellValue(thousandToTwoThousandKBCounter);
		row7.createCell(7).setCellValue(totalSize1000To2000KB);

		row8.createCell(5).setCellValue("2000 KB <= Size < 5000 KB ");
		row8.createCell(6).setCellValue(twoThousandToFiveThousandKBCounter);
		row8.createCell(7).setCellValue(totalSize2000To5000KB);

		row9.createCell(5).setCellValue("5000 KB <= Size < 10000 KB ");
		row9.createCell(6).setCellValue(fiveThousandToTenThousandKBCounter);
		row9.createCell(7).setCellValue(totalSize5000To10000KB);

		row10.createCell(5).setCellValue("10000 KB <= Size < 20000 KB ");
		row10.createCell(6).setCellValue(tenThousandToTwentyThousandKBCounter);
		row10.createCell(7).setCellValue(totalSize10000To20000KB);

		row11.createCell(5).setCellValue("20000 KB <= Size < 50000 KB ");
		row11.createCell(6).setCellValue(twentyThousandToFiftyThousandKBCounter);
		row11.createCell(7).setCellValue(totalSize20000To50000KB);

		row12.createCell(5).setCellValue("50000 <= Size");
		row12.createCell(6).setCellValue(biggerThanFiftyThousandKBCounter);
		row12.createCell(7).setCellValue(totalSizeBiggerThan50000KB);

	}

	public static long toKB(File file) {
		// Get length of file in bytes
		long fileSizeInBytes = file.length();
		// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
		long fileSizeInKB = fileSizeInBytes / 1024;
		return fileSizeInKB;
	}
}
