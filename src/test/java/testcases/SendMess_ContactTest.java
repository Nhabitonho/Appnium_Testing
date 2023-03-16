package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SendMess_ContactPage;
import ultilities.listeners.ReportListener;

import static pages.BaseSetup.helper;
@Listeners(ReportListener.class)
public class SendMess_ContactTest extends BaseTest{
    SendMess_ContactPage sendMess_contactPage;
    @BeforeClass
    public void navigateToPage() throws InterruptedException {
        sendMess_contactPage = homePage.navigateToMessTab();
    }

    @Test(priority = 1)
    public void sendMessContact()throws InterruptedException {
        sendMess_contactPage.sendMess("Hello", helper.message);
    }
}
