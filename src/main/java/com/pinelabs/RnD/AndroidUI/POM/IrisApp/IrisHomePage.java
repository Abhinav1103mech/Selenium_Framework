package com.pinelabs.RnD.AndroidUI.POM.IrisApp;

import com.pinelabs.RnD.AndroidUI.Base.AppiumUtilities;
import com.pinelabs.RnD.CommonUtils.CommonUtils;
import com.pinelabs.RnD.AndroidUI.Constants.FilePaths;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class IrisHomePage extends AppiumUtilities {
    static IrisHomePage irisHomePage;
    static Properties locatorProp, deviceProp;
    static Map<String, String> values;

    private IrisHomePage() {

        locatorProp = CommonUtils.readPropertyfile(FilePaths.irisHomePropertiesPath);
        deviceProp = CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath);
        values = new HashMap<>();
    }

    public static IrisHomePage getInstance() {
        if (irisHomePage == null)
            irisHomePage = new IrisHomePage();
        return irisHomePage;
    }

    public void navigateToSettings() {
        clickOnElement(locatorProp.getProperty("settingsIcon"));
        System.out.println("Clicked on Settings icon");
    }


}
