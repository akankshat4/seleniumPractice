package com.practice.testsuite;

import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.practice.base.WebDriverInstantiation;
import com.practice.utility.DBConnectivity;

public class ProductSearchValidation extends WebDriverInstantiation{
	private DBConnectivity connectivity = new DBConnectivity();

	@Test (dataProvider="listOfProducts")
	public void searchProduct(String productName, String noOfResults, String dateTime, ITestContext context){
		loginFunctions.cancelLogin();
		homeFunctions.searchProduct(productName);
		context.setAttribute("tcName", productName);
		extentTest = extentReports.createTest("searchProduct_"+productName);
		
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		 LocalDateTime now = LocalDateTime.now();  
		 System.out.println(dtf.format(now));
		 
		 String sql = "update search_result set timeStamp = \""+dtf.format(now)+"\" where product = \""+productName+"\"";
		 //System.out.println(sql);
		 connectivity.executeUpdate(sql);
	}
	
	
	@DataProvider(name="listOfProducts")
	public Object[][] getProductsFromDB(){
		System.out.println("Inside dataprovider");
		String [][] results = null;
		String sql = "select * from search_result";
		results = connectivity.getSearchResults(sql);
		return results;
	}

}
