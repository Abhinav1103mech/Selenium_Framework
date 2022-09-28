package com.pinelabs.RnD.WebUI.POM;

import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.CommonUtils;
import com.pinelabs.RnD.WebUI.Base.SeleniumUtilities;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;
import java.util.Properties;

import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;



public class IRISLoginPage extends SeleniumUtilities {
    static IRISLoginPage webUIDemoInstance;
    Properties iRISLoginPageProperties;

    private IRISLoginPage() {
        iRISLoginPageProperties = CommonUtils.readPropertyfile(FilePaths.iRISLoginPageProperties);
    }

    public static IRISLoginPage getInstance() {
        if (webUIDemoInstance == null)
            webUIDemoInstance = new IRISLoginPage();
        return webUIDemoInstance;
    }


    public void enterUsername(String text) {
        clickOnElement(iRISLoginPageProperties.getProperty("Username"));
        setElementText(iRISLoginPageProperties.getProperty("Username"), text);
    }
    
    public void enterPassword(String text) {
        clickOnElement(iRISLoginPageProperties.getProperty("Password"));
        setElementText(iRISLoginPageProperties.getProperty("Password"), text);
        
        
    }

    public void enterCaptchatxt(){
      String captchatext= CommonUtils.getCaptchaText(getElement(iRISLoginPageProperties.getProperty("captchatext")));
	  System.out.println(CommonUtils.getCaptchaText(getElement(iRISLoginPageProperties.getProperty("captchatext"))));
    	clickOnElement(iRISLoginPageProperties.getProperty("EnterCaptchtext"));
	    setElementText(iRISLoginPageProperties.getProperty("EnterCaptchtext"),captchatext);
    }
    
    public void performLogin()  {
    	
    	clickOnElement(iRISLoginPageProperties.getProperty("LoginButton"));
    	while(getListOfElements(iRISLoginPageProperties.getProperty("CaptchaError")).size()!=0) {
    		clear(iRISLoginPageProperties.getProperty("EnterCaptchtext"));
    		clickOnElement(iRISLoginPageProperties.getProperty("ReloadCaptcha"));
    		String captchatext= CommonUtils.getCaptchaText(getElement(iRISLoginPageProperties.getProperty("captchatext")));
    		System.out.println(CommonUtils.getCaptchaText(getElement(iRISLoginPageProperties.getProperty("captchatext"))));
    	    clickOnElement(iRISLoginPageProperties.getProperty("EnterCaptchtext"));
    		setElementText(iRISLoginPageProperties.getProperty("EnterCaptchtext"),captchatext);
    	}
    	Assert.assertTrue(elementDisplayed(iRISLoginPageProperties.getProperty("Logout")), "Login Failed");
    	//Assert.assertEquals(getTextOfElement(iRISLoginPageProperties.getProperty("EnterCaptchtext")), getAlertText());
    }
  
    public void getHardwareDetails()
    {
    //	MongoCollection<Document> globalCollection=CommonUtils.findCollectionFromDb("PL_IRIS_FUNCTIONALITY_GLOBAL_FLAG_COLLECTIONS");
    	Document hardwarerecord= CommonUtils.findRowBasedOnHardwareID("PL_IRIS_APP_BASIC_PARAMETERS_COLLECTIONS","0821397985");
    	System.out.println(hardwarerecord);
    	
    	System.out.println(hardwarerecord.get("BPCL_CLOUD_URL"));
    } 
    
    
   
    
}