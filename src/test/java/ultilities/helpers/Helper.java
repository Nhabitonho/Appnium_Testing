package ultilities.helpers;

import com.github.javafaker.Faker;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BaseSetup;

import java.time.Duration;

public class Helper extends BaseSetup {
    Faker faker = new Faker();
    public String firstName = faker.name().firstName();
    public String editFirstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String editLastName = faker.name().lastName();
    public String phoneNum = faker.phoneNumber().cellPhone();
    public String email = firstName+lastName+"gmail.com";
    // Fake 9 number
    public String sip = faker.regexify("\\d{9}");
    public String address = faker.address().streetAddress();
    public String searchName = editFirstName+ " "+ editLastName;
    public String message = "I am " + searchName;


    public Helper(AndroidDriver driver) {
        this.driver = driver;
    }

    public void scrollDown() {
        int x = driver.manage().window().getSize().width / 2;
        int start_y = (int) (driver.manage().window().getSize().height * 0.1);
        int end_y = (int) (driver.manage().window().getSize().height * 0.9);
        TouchAction dragNDrop = new TouchAction(driver)
                .press(PointOption.point(x, end_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x, start_y))
                .release();
        dragNDrop.perform();
    }
    public String getMessToast(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
        String toastMess = driver.findElement(By.xpath("/hierarchy/android.widget.Toast")).getText();
        System.out.println(toastMess);

        return toastMess;
    }
}
