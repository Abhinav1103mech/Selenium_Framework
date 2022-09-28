package com.pinelabs.RnD.WebUI.POM;

import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.CommonUtils;
import com.aventstack.extentreports.ExtentTest;
import com.pinelabs.RnD.WebUI.Base.SeleniumUtilities;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;

import ch.qos.logback.classic.Logger;

import java.util.Properties;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.ExtentSparkReport;
import org.apache.commons.lang.UnhandledException;
import org.bson.Document;
import org.testng.Assert;

public class HardwareOperationPage_Microservice extends SeleniumUtilities {
	static HardwareOperationPage_Microservice webUIDemoInstance;
	public static ExtentTest test, extentLogger;
	public static String UIerror, DBerror, element;

	Properties HardwareOperationProperties;

	private HardwareOperationPage_Microservice() {
		HardwareOperationProperties = CommonUtils.readPropertyfile(FilePaths.hardwareOperationProperties);
	}

	public static HardwareOperationPage_Microservice getInstance() {
		if (webUIDemoInstance == null)
			webUIDemoInstance = new HardwareOperationPage_Microservice();
		return webUIDemoInstance;
	}

	public void selectIRISMicroservice() {
		select(HardwareOperationProperties.getProperty("SelectProgram"), "Iris Microservice");

	}

	public void enterHardwareID(String HardwareID) {
		select(HardwareOperationProperties.getProperty("SelectProgram"), "Iris Microservice");
		clickOnElement(HardwareOperationProperties.getProperty("EnterHardwareID"));
		setElementText(HardwareOperationProperties.getProperty("EnterHardwareID"), HardwareID);

	}

	public void clickOnSearch() {
		clickOnElement(HardwareOperationProperties.getProperty("SearchButton"));

	}

	public void validateHardwareID(String HardwareID) throws UnhandledException

	{

		try {

			Document HardwareRecord = CommonUtils.findRowBasedOnHardwareID("PL_IRIS_APP_BASIC_PARAMETERS_COLLECTIONS",
					HardwareID);
			
			if (HardwareRecord == null) {

				DBerror = "No records found";
				UIerror = getTextOfElement(HardwareOperationProperties.getProperty("ErrorMesage"));
			}

		} catch (UnhandledException e) {

			System.out.println(e.getMessage());
		}
	}

	public void validateElement() {
		element = getTextOfElement(HardwareOperationProperties.getProperty("ParameterName"));
		System.out.println(element);

	}
	
	/*public void establishMongoDBConnection()
	{
	// MongoCollection<Document> globalCollection=CommonUtils.findCollectionFromDb("PL_IRIS_FUNCTIONALITY_GLOBAL_FLAG_COLLECTIONS");
	Document hardwarerecord= CommonUtils.findRowBasedOnHardwareID("PL_IRIS_APP_BASIC_PARAMETERS_COLLECTIONS","0821397985");
	System.out.println(hardwarerecord);
	}
	*/
	   public void getHardwareDetailsFromDB(String HardwareID,String ParamName)
	    {
	    //	MongoCollection<Document> globalCollection=CommonUtils.findCollectionFromDb("PL_IRIS_FUNCTIONALITY_GLOBAL_FLAG_COLLECTIONS");
	   Document Hardwarerecord= CommonUtils.findRowBasedOnHardwareID("PL_IRIS_APP_BASIC_PARAMETERS_COLLECTIONS",HardwareID);	   
	   Object paramvalue=Hardwarerecord.get(ParamName);
	   System.out.println(Hardwarerecord);
	   System.out.println(paramvalue);
	    } 	
	   
	
	   public void validateUIParameterValues() throws InterruptedException
	   {
		String updateButton=  scrollToElement(HardwareOperationProperties.getProperty("updateCollection")).getText(); 
		System.out.println(updateButton);
		
		Thread.sleep(3000);
	
		  
	   }
	   
	   public void setNewParamValue(String newParamValue) throws InterruptedException
	   {
		   clear(HardwareOperationProperties.getProperty("paramValue"));
		   Thread.sleep(2000);
		   clickOnElement(HardwareOperationProperties.getProperty("paramValue"));
		   setElementText(HardwareOperationProperties.getProperty("paramValue"),newParamValue);  
		   clickOnElement(HardwareOperationProperties.getProperty("updateCollection"));
		 
	   }
	  
	    
}
