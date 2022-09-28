package com.pinelabs.RnD.WebUITest;

import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.ExtentSparkReport;
import com.pinelabs.RnD.WebUI.POM.CommonPage;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestUtils extends ExtentSparkReport {
    CommonPage commonPage;

    @BeforeSuite
    public void extents() {
        ExtentSparkReport.initialise();
    }

    @BeforeMethod
    public void setMethodAndClassDetails(Method method, ITestContext result) {
        Test test = method.getAnnotation(Test.class);
        setMethod(method, test);
    }

    public CommonPage getCommonPageInstance() {
        return CommonPage.getInstance();
    }

   // @AfterClass
    public void terminateBrowser() {
        getCommonPageInstance().closeBrowser();
    }

}
