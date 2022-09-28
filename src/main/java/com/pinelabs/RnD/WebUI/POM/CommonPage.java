package com.pinelabs.RnD.WebUI.POM;

import com.pinelabs.RnD.WebUI.Base.SeleniumUtilities;

public class CommonPage extends SeleniumUtilities {
    private static CommonPage commonPage;


    public static CommonPage getInstance() {
        if (commonPage == null)
            commonPage = new CommonPage();
        return commonPage;
    }

    public void closeBrowser() {
        tearDown();
    }


}
