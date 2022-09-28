package com.pinelabs.RnD.WebUI.POM;

import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.CommonUtils;
import com.pinelabs.RnD.WebUI.Base.SeleniumUtilities;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;

import java.util.Properties;

public class GoogleDemoPage extends SeleniumUtilities {
    static GoogleDemoPage webUIDemoInstance;
    Properties googleDemoProperties;

    private GoogleDemoPage() {
        googleDemoProperties = CommonUtils.readPropertyfile(FilePaths.googleDemoProperties);
    }

    public static GoogleDemoPage getInstance() {
        if (webUIDemoInstance == null)
            webUIDemoInstance = new GoogleDemoPage();
        return webUIDemoInstance;
    }


    public void searchText(String text) {
        clickOnElement(googleDemoProperties.getProperty("searchBox"));
        setElementText(googleDemoProperties.getProperty("searchBox"), text);
    }

    public void captchatxt(){
        System.out.println("AAAAAAAAAaa" +CommonUtils.getCaptchaText(getElement(googleDemoProperties.getProperty("captcha"))));
    }
}
