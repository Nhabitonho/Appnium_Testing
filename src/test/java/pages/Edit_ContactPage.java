package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ultilities.Log;

public class Edit_ContactPage extends BaseSetup{
    //TODO:
//    List<WebElement> ele = driver.findElements(By.id("com.android.contacts:id/editors"));
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText[1]")
    public WebElement editFirstname;
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText[2]")
    public WebElement editLastname;
    @FindBy(id = "com.android.contacts:id/menu_edit")
    public WebElement editButton;
    @FindBy(xpath = "(//android.widget.Spinner[@content-desc='Phone'])")
    public WebElement phone;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]")
    public WebElement phoneOptional;

    @FindBy(id = "com.android.contacts:id/editor_menu_save_button")
    public WebElement saveButton;

    public Edit_ContactPage(){
        PageFactory.initElements(driver, this);
    }

    public void sendInfo(String firstname, String lastname){
        editFirstname.clear();
        editFirstname.sendKeys(firstname);
        editLastname.clear();
        editLastname.sendKeys(lastname);
//        phone.click();
//        phoneOptional.click();
    }
    public void saveEditContact(){
        saveButton.click();
        Log.info("Edit contacts successfully");
    }
    public String verifyToastMessDisplay(){
        return helper.getMessToast();
    }

}