package org.example;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class Main {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    static String Appium_Node_Path = "C:\\Program Files\\nodejs\\node.exe";
    static String Appium_JS_Path = "C:\\Users\\lonbui\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    static String fullUrl = "http://127.0.0.1:4723/wd/hub";
    static String url = "127.0.0.1";
    static String minorUrl = "/wd/hub";
    static DesiredCapabilities cap;
    static AppiumServiceBuilder builder;
    static AppiumDriverLocalService service;


    @BeforeClass
    public void startServer() throws InterruptedException {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(url)
                .usingPort(4723)
                .withAppiumJS(new File(Appium_JS_Path))
                .usingDriverExecutable(new File(Appium_Node_Path))
                .withArgument(BASEPATH, minorUrl)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        Thread.sleep(10000);
    }
    @AfterClass
    public void stopAppiumServer() {
        service.stop();
    }

    @BeforeMethod
    public void startSession() throws MalformedURLException {
        cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "sdk_gphone64_x86");
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        cap.setCapability("noReset", "true");
        //For calculator
//        cap.setCapability("addPackage", "com.android.calculator2");
//        cap.setCapability("addActivity", "com.android.calculator2.Calculator");
        //clock
//        cap.setCapability("addPackage", "com.android.deskclock");
//        cap.setCapability("addActivity", "com.android.deskclock.DeskClock");
        //Phone
        cap.setCapability("addPackage", "com.android.contacts.common.dialog");
        cap.setCapability("addActivity", ".CallSubjectDialog");

//        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);
//        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        driver = new AndroidDriver(new URL(fullUrl), cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


//    @Test()
//    public static void createExampleContacts() {
//        driver.findElement(By.id("com.android.dialer:id/contacts_tab")).click();
//        driver.findElement(By.id("com.android.dialer:id/contact_name")).click();
//        driver.findElement(By.xpath("//*[@text='First name']")).sendKeys("E");
//        driver.navigate().back();
//        driver.findElement(By.xpath("//*[@text='Phone']")).sendKeys("12345");
//        driver.findElement(By.id("com.android.contacts:id/editor_menu_save_button")).click();
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
//        String toastMess = driver.findElement(By.xpath("/hierarchy/android.widget.Toast")).getText();
//        System.out.println(toastMess);
//
//        System.out.println("Create contacts successfully");
//    }

    @Test(priority = 1)
    public static void createContacts() throws InterruptedException {

        driver.findElement(By.id("com.android.dialer:id/contacts_tab")).click();
        driver.findElement(By.id("com.android.dialer:id/contact_name")).click();
        driver.findElement(By.xpath("//*[@text='First name']")).sendKeys("Eric1");
        driver.navigate().back();
        driver.findElement(By.xpath("//*[@text='Last name']")).sendKeys("Cartoons1");
        driver.findElement(By.xpath("//*[@text='More fields']")).click();
        scrollDown();
        driver.findElement(By.xpath("//*[@text='Phone']")).sendKeys("123456789");
//        driver.findElement(By.id("com.android.contacts:id/editors")).sendKeys("123456789");
        driver.findElement(By.xpath("(//android.widget.Spinner[@content-desc='Phone'])")).click();
//        List<WebElement> ele = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView"));
//        System.out.println( ele.get(1).getText());
//        ele.get(1).click();
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")).click();
        driver.findElement(By.xpath("//*[@text='Email']")).sendKeys("Eric@gmail.com");
        driver.findElement(By.xpath("(//android.widget.Spinner[@content-desc='Email'])")).click();
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")).click();
        scrollDown();
        driver.findElement(By.xpath("//*[@text='SIP']")).sendKeys("123333333");
        driver.findElement(By.xpath("//*[@text='Address']")).sendKeys("123 LA America");
        driver.findElement(By.id("com.android.contacts:id/editor_menu_save_button")).click();
        //Handle for get text from toast message
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
        String toastMess = driver.findElement(By.xpath("/hierarchy/android.widget.Toast")).getText();
        System.out.println(toastMess);
        System.out.println("Create contacts successfully");
    }

    @Test(priority = 2)
    public void editContacts() {
        driver.findElement(By.id("com.android.contacts:id/menu_edit")).click();
        List<WebElement> ele = driver.findElements(By.className("android.widget.EditText"));
        ele.get(0).sendKeys("editFirstName");
        ele.get(1).sendKeys("editLastName");
        driver.findElement(By.xpath("(//android.widget.Spinner[@content-desc='Phone'])")).click();
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]")).click();
        driver.findElement(By.id("com.android.contacts:id/editor_menu_save_button")).click();
        System.out.println("Edit contacts successfully");

    }

    @Test(priority = 3)
    public void sendMessage() {
        driver.findElement(By.id("com.android.contacts:id/icon_alternate")).click();
        driver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("Hello");
        driver.findElement(By.id("com.google.android.apps.messaging:id/send_message_button_icon")).click();
        driver.findElement(By.id("com.google.android.apps.messaging:id/compose_message_text")).sendKeys("Hello123");
        driver.findElement(By.id("com.google.android.apps.messaging:id/send_message_button_icon")).click();
        driver.navigate().back();
    }

    @Test(priority = 4)
    public void deleteContacts() {
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='More options']")).click();
        driver.findElement(By.xpath("//*[@text='Delete']")).click();
        driver.findElement(By.id("android:id/button1")).click();
        System.out.println("Delete contacts successfully");
    }

    @Test(priority = 5)
    public void searchContacts() {
        driver.findElement(By.id("com.android.dialer:id/search_magnifying_glass")).click();
        driver.findElement(By.id("com.android.dialer:id/search_view")).sendKeys("Eric Cartons");
        driver.findElement(By.id("com.android.dialer:id/header")).click();
        String contactText = driver.findElement(By.id("com.android.contacts:id/large_title")).getText();
        System.out.println(contactText);
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }



    public static void scrollDown() {
        int x = driver.manage().window().getSize().width / 2;
        int start_y = (int) (driver.manage().window().getSize().height * 0.1);
        int end_y = (int) (driver.manage().window().getSize().height * 0.9);
        TouchAction dragNDrop = new TouchAction(driver)
                .press(PointOption.point(x, end_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x, start_y))
                .release();
        dragNDrop.perform();
    }
}