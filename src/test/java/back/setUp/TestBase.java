package back.setUp;

import back.extentReport.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;


public class TestBase {
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeMethod
    public void registerTest(Method method) {
        extent = ExtentManager.getInstance();  // Doim kafolatli extent olamiz
        test = extent.createTest(method.getName());
    }

    @AfterSuite
    public void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else {
            test.skip(result.getThrowable());
        }
    }
}
