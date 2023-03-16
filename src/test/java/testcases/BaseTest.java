package testcases;

import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BaseSetup;
import pages.HomePage;
import ultilities.extentreports.ExtentTestManager;

import java.io.IOException;
import java.net.MalformedURLException;

import static pages.BaseSetup.driver;
import static ultilities.extentreports.ExtentTestManager.getBase64Screenshot;


public class BaseTest {
    public static BaseSetup setup;
    public static HomePage homePage;


    public AndroidDriver getDriver(){
        return driver;
    }

    @Parameters({"port"})
    @BeforeSuite
    public void beforeSuite(@Optional("4723") int port) throws InterruptedException {
        setup = new BaseSetup();
        setup.startAppiumServer(port);
    }
    @Parameters({"device-url", "device-Name", "udid", "device-Version", "evn"})
    @BeforeTest
    public void initTest(@Optional("http://127.0.0.1:4723/wd/hub") String fullUrl, @Optional("sdk_gphone64_x86") String deviceName,@Optional("emulator-5554") String udid,@Optional("11") String version, @Optional("dev") String environment) throws IOException {
        setup.setupDriver(deviceName, udid, version);
        homePage = setup.navigateToHomePage(fullUrl, environment);
    }
    @AfterMethod
    public void takeScreenshot(ITestResult result) throws Exception {
        // Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Step
        // passed = SUCCESS và failed = FAILURE
        // cap nhung testcase fail to 1 folder
//        if (ITestResult.FAILURE == result.getStatus()) {
//                CaptureHelpers.captureScreenshot(driver, result.getName());
//        }

        if (result.getStatus() == ITestResult.FAILURE) {
            String path = getBase64Screenshot();
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        setup.tearDownDriver();
    }
    @AfterSuite
    public void afterSuite(){
        setup.stopAppiumServer();
    }
}
