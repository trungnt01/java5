package com.poly.utiliti;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.poly.entities.Category;
import com.poly.entities.Product;
import com.poly.repositories.CategoryRepository;
import com.poly.repositories.ProductRepository;

public class ExcelUtil {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public void importDataToDB(String locationFile) throws Exception {
		try {
			
			List<Product> lstProduct = new ArrayList<Product>();
			
			File file = new File(locationFile); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("product"); 
			
			int rows = sheet.getLastRowNum();
			
			for (int r = 1; r <= rows; r++)
			{
//				int noOfColumns = sheet.getRow(r).getLastCellNum();
//				System.out.println(noOfColumns);
				
			   XSSFRow row = sheet.getRow(r);
			   int id = (int) row.getCell(0).getNumericCellValue();
			   String name = row.getCell(1).getStringCellValue();
			   int category_id = (int) row.getCell(2).getNumericCellValue();
			   int price = (int) row.getCell(3).getNumericCellValue();
			   int available = (int) row.getCell(4).getNumericCellValue();
			   Date createDate = java.sql.Date.valueOf(java.time.LocalDate.now());
			   
			   String image = null;
			   if(row.getCell(7) != null) {
				   image = row.getCell(7).getStringCellValue();
			   }
			    
			   Category category = this.categoryRepo.getById(category_id);
			   
			   Product product = new Product();
			   
			   
			}
			
			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
