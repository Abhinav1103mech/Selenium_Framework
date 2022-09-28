package com.pinelabs.RnD.AndroidUI.POM.IrisApp;

import com.pinelabs.RnD.AndroidUI.Base.AppiumUtilities;
import com.pinelabs.RnD.CommonUtils.CommonUtils;
import com.pinelabs.RnD.AndroidUI.Constants.FilePaths;
import org.openqa.selenium.NotFoundException;

import java.util.*;

public class IrisSettingsPage extends AppiumUtilities {
    static IrisSettingsPage irisSettingsPage;
    static Properties locatorProp, deviceProp;
    static Map<String, String> values;

    private IrisSettingsPage() {

        locatorProp = CommonUtils.readPropertyfile(FilePaths.irisSettingsPropertiesPath);
        deviceProp = CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath);
        values = new HashMap<>();
    }

    public static IrisSettingsPage getInstance() {
        if (irisSettingsPage == null)
            irisSettingsPage = new IrisSettingsPage();
        return irisSettingsPage;
    }

    public Map<String, Object> validateSettingsRoIDAndURLs() {
        String fetchedValue = null;
        List<String> ids = new ArrayList<String>();
        ids.add("RoID");
        ids.add("BpclURL");
        ids.add("PineURL");
        ids.add("VmsURL");
        ids.add("ALPURL");
        Map<String, Object> values = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            fetchedValue = getElementText(locatorProp.getProperty(ids.get(i)));
            System.out.println("Checking: " + ids.get(i));
            String[] ar = fetchedValue.split(", ");
            String key = ar[1];
            String value = ar[0];
            values.put(key, value);
        }
//        for (Map.Entry<String,String> entry : values.entrySet()){
//            System.out.println("KeyM = " + entry.getKey() +
//                    ", ValueM = " + entry.getValue());
        //  }

        return values;
    }

    public void selectCommModeRadioButton(String commMode) {

        if (commMode.equalsIgnoreCase("BLE")) {
            clickOnElement(locatorProp.getProperty("BLECommMode"));
            if (isRadioButtonSelected(locatorProp.getProperty("BLECommMode"), "checked"))
                System.out.println("Selected Radio button is BLE");
        } else if (commMode.equalsIgnoreCase("BPCL")) {
            clickOnElement(locatorProp.getProperty("BPCLCommMode"));
            if (isRadioButtonSelected(locatorProp.getProperty("BPCLCommMode"), "checked"))
                System.out.println("Selected Radio Button is BPCL");
        } else
            throw new NotFoundException("Allowed option for commMode are BPCL and BPE");
    }

    public void selectPrintWidthRadioButton(String printWidth) {
        if (printWidth.equals("24")) {
            clickOnElement(locatorProp.getProperty("24PrintWidth"));
            if (isRadioButtonSelected(locatorProp.get("24PrintWidth").toString(), "checked"))
                System.out.println("Selected PrintWidth is 24");
        } else if (printWidth.equals("40")) {
            clickOnElement(locatorProp.getProperty("40PrintWidth"));
            if (isRadioButtonSelected(locatorProp.get("40PrintWidth").toString(), "checked"))
                System.out.println("Selected PrintWidth is 40");

        } else if (printWidth.equals("48")) {
            clickOnElement(locatorProp.getProperty("48PrintWidth"));
            if (isRadioButtonSelected(locatorProp.get("48PrintWidth").toString(), "checked"))
                System.out.println("Selected PrintWidth is 48");
        }
        else
            throw new NotFoundException("Allowed Options are 24,40,48");
    }

    public String validateDefaultCommModeSelection() {
        if (!isElementDisplayed(locatorProp.getProperty("24PrintWidth"))) {
            scrollDown();
            System.out.println("Scrolled down to radio buttons");
        }
        if (isRadioButtonSelected(locatorProp.getProperty("BLECommMode"), "checked")) {
            System.out.println("Selected radio button is BLE");
            return(getElementText(locatorProp.getProperty("BLECommMode")));

        } else if (isRadioButtonSelected(locatorProp.getProperty("BPCLCommMode"), "checked")) {
            System.out.println("Selected radio button is BPCL");
            return(getElementText(locatorProp.getProperty("BPCLCommMode")));
        } else {
            throw new NotFoundException("No RADIO BUTTON IS SELECTED! PLEASE CHECK");

        }
    }

    public boolean validatePrintwidthRadioButton() {
        if (getAttribute(locatorProp.getProperty("24PrintWidth"), "checked").equalsIgnoreCase("true"))
            return true;
        return false;

    }

    public void clickMasterDataButton() {
       try {
           if (!isElementDisplayed(locatorProp.getProperty("MasterDataButton")));
       }catch(Exception e) {
           scrollDown();
       }
        clickOnElement(locatorProp.getProperty("MasterDataButton"));
    }
}
