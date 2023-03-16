package pages;

import org.openqa.selenium.By;

public class HomePage extends BaseSetup{
    public Create_ContactPage navigateToCreate(){
        driver.findElement(By.id("com.android.dialer:id/contacts_tab")).click();
        driver.findElement(By.id("com.android.dialer:id/contact_name")).click();
        return new Create_ContactPage();
    }
    public Edit_ContactPage navigateToEdit(){
        driver.findElement(By.id("com.android.contacts:id/menu_edit")).click();
        return new Edit_ContactPage();
    }
    public Delete_ContactPage navigateToDelete(){
        return new Delete_ContactPage();
    }
    public SendMess_ContactPage navigateToMessTab() throws InterruptedException {
        driver.findElement(By.id("com.android.contacts:id/icon_alternate")).click();
        Thread.sleep(5000);
        return new SendMess_ContactPage();
    }
    public Search_ContactPage navigateToSeachTab(){
        return new Search_ContactPage();
    }

}
