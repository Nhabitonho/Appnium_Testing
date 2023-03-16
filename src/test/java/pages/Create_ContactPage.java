package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ultilities.Log;

public class Create_ContactPage extends BaseSetup {
    @FindBy(id = "com.android.dialer:id/contacts_tab")
    public WebElement ContactTab;

    @FindBy(id = "com.android.dialer:id/contact_name")
    public WebElement CreateContact;

//      img
//    @FindBy(xpath = "//*[@text='Last name']")
//    public WebElement addImg;

    @FindBy(xpath = "//*[@text='First name']")
    public WebElement FirstName;

    @FindBy(xpath = "//*[@text='Last name']")
    public WebElement LastName;

    @FindBy(xpath = "//*[@text='More fields']")
    public WebElement More;

    @FindBy(xpath = "//*[@text='Phone']")
    public WebElement Phone;

    @FindBy(xpath = "(//android.widget.Spinner[@content-desc='Phone'])")
    public WebElement Mobile;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")
    public WebElement MobileOptional;

    @FindBy(xpath = "//*[@text='Email']")
    public WebElement Email;

    @FindBy(xpath = "(//android.widget.Spinner[@content-desc='Email'])")
    public WebElement Home;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")
    public WebElement EmailOptional;

    @FindBy(xpath = "//*[@text='SIP']")
    public WebElement Sip;

    @FindBy(xpath = "//*[@text='Address']")
    public WebElement Address;

    @FindBy(id = "com.android.contacts:id/editor_menu_save_button")
    public WebElement saveButton;

    public Create_ContactPage(){
        PageFactory.initElements(driver, this);
    }

    public void sendInfo(String firstname, String lastname, String phoneNum, String email,String sip, String address) throws InterruptedException {
        FirstName.sendKeys(firstname);
        LastName.sendKeys(lastname);
        driver.navigate().back();
        More.click();
        helper.scrollDown();
        Phone.sendKeys(phoneNum);
        Mobile.click();
        MobileOptional.click();
        Email.sendKeys(email);
        Home.click();
        EmailOptional.click();
        helper.scrollDown();
        Sip.sendKeys(sip);
        Address.sendKeys(address);
    }

    public void saveContact(){
        saveButton.click();
        Log.info("Create contacts successfully");
    }

}
