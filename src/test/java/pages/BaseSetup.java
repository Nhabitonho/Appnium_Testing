package pages;

//import driverManager.AndroidDriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultilities.helpers.Helper;
import ultilities.PropertiesReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class BaseSetup {
    public static AndroidDriver driver;
    public static Helper helper;
    public static WebDriverWait wait;
    static String Appium_Node_Path = "C:\\Program Files\\nodejs\\node.exe";
    static String Appium_JS_Path = "C:\\Users\\lonbui\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
//    static String fullUrl = "http://127.0.0.1:4723/wd/hub";
    static String url = "127.0.0.1";
    static String minorUrl = "/wd/hub";
    static DesiredCapabilities cap;
    static AppiumServiceBuilder builder;
    static AppiumDriverLocalService service;
    public static PropertiesReader envProFile;
//    int port = 4723;

    public static AndroidDriver getDriver(){
        return driver;
    }

    public void startAppiumServer(int port) throws InterruptedException {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(url)
                .usingPort(port)
                .withAppiumJS(new File(Appium_JS_Path))
                .usingDriverExecutable(new File(Appium_Node_Path))
                .withArgument(BASEPATH, minorUrl)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
                //.withArgument (GeneralServerFlag.LOG_LEVEL, "debug");
        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("start server");
    }

    public void stopAppiumServer() {
        service.stop();
    }

    public void setupDriver( String deviceName, String udid, String version) throws MalformedURLException {
        helper = new Helper(driver);
        //TODO
        cap = new DesiredCapabilities();

        System.out.println("Launching Android device...");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        cap.setCapability(MobileCapabilityType.UDID, udid);
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
        //Phone
        cap.setCapability("addPackage", "com.android.contacts.common.dialog");
        cap.setCapability("addActivity", ".CallSubjectDialog");


    }
    public HomePage navigateToHomePage(String fullUrl,String envFile) throws IOException {
        URL appiumServer = new URL(fullUrl);
        driver = (new AndroidDriver(appiumServer, cap));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        envProFile = new PropertiesReader(envFile);
        return new HomePage();
        }
    public void tearDownDriver() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
    public String verifyToastMessDisplay(){
        return helper.getMessToast();
    }
}
