package com.pinelabs.RnD.AndroidTest;

import com.pinelabs.RnD.CommonUtils.CommonUtils;
import com.pinelabs.RnD.AndroidUI.POM.DeviceHomePage;
import com.pinelabs.RnD.AndroidUI.POM.IrisApp.IrisHomePage;
import com.pinelabs.RnD.AndroidUI.POM.IrisApp.IrisSettingsPage;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class IrisAppTest extends TestUtils {

    DeviceHomePage deviceHomePage;
    IrisHomePage irisHomePage;
    IrisSettingsPage irisSettingsPage;
    Document dbValues;

    @BeforeClass
    public void initiation() {
        deviceHomePage = getDeviceHomePageInstance();
        irisSettingsPage = getIrisSettingsPageInstance();
        irisHomePage = getIrisHomePageInstance();
        dbValues = CommonUtils.findRowBasedOnColumn("PL_IRIS_APP_BASIC_PARAMETERS_COLLECTIONS", "HARDWARE_ID", "0820686904");
    }

    @Test(priority = 1)
    public void validateTextBoxes() {
        deviceHomePage.openIrisApp();
        irisHomePage.navigateToSettings();
        Map<String, Object> value = irisSettingsPage.validateSettingsRoIDAndURLs();
       //String v=dbValues.get("ROID");
      //  Assert.assertEquals(value.get("RO Id"), dbValues.get("ROID").toString());
        Assert.assertEquals(value.get("Enter Bpcl Cloud Base Url"), (dbValues.get("BPCL_CLOUD_URL")));
        Assert.assertEquals(value.get("Enter Pine Cloud Base Url"), (dbValues.get("PINE_CLOUD_URL")));
        Assert.assertEquals(value.get("Enter Vms Cloud Base Url"), (dbValues.get("VMS_CLOUD_URL")));
        Assert.assertEquals(value.get("Enter ALP Cloud Base Url"), (dbValues.get("ALP_CLOUD_URL")));

    }
    @Test(priority = 2)
    public void validateRadioButtons(){
        irisSettingsPage.selectCommModeRadioButton("BLE");
        irisSettingsPage.selectPrintWidthRadioButton("24");
        irisSettingsPage.validateDefaultCommModeSelection();
        Assert.assertEquals(irisSettingsPage.validateDefaultCommModeSelection(),"BPCL");
        Assert.assertTrue(irisSettingsPage.validatePrintwidthRadioButton());

    }
    @Test(priority = 3)
    public void getMasterData(){
        irisSettingsPage.clickMasterDataButton();
    }
}
