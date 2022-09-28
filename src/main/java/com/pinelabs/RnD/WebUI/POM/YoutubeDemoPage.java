package com.pinelabs.RnD.WebUI.POM;

import com.pinelabs.RnD.CommonUtils.CommonUtils;
import com.pinelabs.RnD.WebUI.Base.SeleniumUtilities;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;

import java.util.Properties;

public class YoutubeDemoPage extends SeleniumUtilities {

    static YoutubeDemoPage youTubeDemoInstance;
    Properties youTubeDemoProperties;

    private YoutubeDemoPage() {
        youTubeDemoProperties = CommonUtils.readPropertyfile(FilePaths.youTubeDemoProperties);
    }

    public static YoutubeDemoPage getInstance() {
        if (youTubeDemoInstance == null)
            youTubeDemoInstance = new YoutubeDemoPage();
        return youTubeDemoInstance;
    }

    public void launchYoutube() {
        initialization("https://www.youtube.com");
    }

    public void clickOnSearchBox() {
        clickOnElement(youTubeDemoProperties.getProperty("searchbox"));
    }

    public void searchText(String text) {
        setElementText(youTubeDemoProperties.getProperty("searchbox"), text);

    }
}
