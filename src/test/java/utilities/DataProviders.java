package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider-1
	
	   @DataProvider (name = "LoginData")
		public String[][] getData() throws IOException {
			String path = ".\\testData\\Opencart_LoginData.xlsx";
			
			ExcelUtility xlutil = new ExcelUtility(path);  // creating an object for ExcelUtility class
			
			int totalRows = xlutil.getRowCount("Sheet1");
			int totalCols = xlutil.getCellCount("Sheet1", 1);
			
			String logindata[][] = new String[totalRows][totalCols];  // Created for two dimensional array which can store
			
			for(int r=1;r<=totalRows;r++) {  // 1
				for(int c=0;c<totalCols;c++) {  // 0
					logindata[r-1][c] = xlutil.getCellData("Sheet1", r, c);  // 1, 0
				}
			}
				return logindata;  // returns two dimensional array 
		}

	   
	   // DataProvider-2
	   
	   // DataProvider-3
	   
	   // DataProvider-4
}
