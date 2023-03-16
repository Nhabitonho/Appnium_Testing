package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SendMess_ContactPage extends BaseSetup{

    @FindBy(id = "com.android.contacts:id/icon_alternate")
    public WebElement messButton;
//    @FindBy(id = "com.google.android.apps.messaging:id/compose_message_text")
    @FindBy(xpath = "//*[@text='Text message']")
    public WebElement messTextField;

    @FindBy(id = "com.google.android.apps.messaging:id/send_message_button_icon")
    public WebElement sendMessButton;


    public SendMess_ContactPage(){
        PageFactory.initElements(driver, this);
    }

    public void sendMess(String mess1, String mess2) throws InterruptedException {
        messTextField.sendKeys(mess1);
        sendMessButton.click();
        messTextField.sendKeys(mess2);
        sendMessButton.click();
        driver.navigate().back();
        Thread.sleep(5000);
    }

}