package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Search_ContactPage extends BaseSetup{
    @FindBy(id = "com.android.dialer:id/search_magnifying_glass")
    public WebElement iconSearch;
    @FindBy(id = "com.android.dialer:id/search_view")
    public WebElement searchTextField;

    @FindBy(id = "com.android.dialer:id/photo")
    public WebElement iconContact;

    @FindBy(id = "com.android.contacts:id/large_title")
    public WebElement nameContact;

    public Search_ContactPage(){
        PageFactory.initElements(driver, this);
    }

    public void searchContact(String name){
        iconSearch.click();
        searchTextField.sendKeys(name);
        iconContact.click();
    }
    public String verifyNameContact(){
        return nameContact.getText();
    }

}