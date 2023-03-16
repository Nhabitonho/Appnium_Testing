package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Create_ContactPage;
import ultilities.Log;
import ultilities.listeners.ReportListener;

import static org.testng.Assert.assertEquals;
import static pages.BaseSetup.helper;
@Listeners(ReportListener.class)
public class Create_ContactTest extends BaseTest {

    Create_ContactPage create_contactPage;
    @BeforeClass
    public void navigateToPage(){
        create_contactPage = homePage.navigateToCreate();
    }
    //Todo:
    @Test(priority = 1)
    public void sendInfoContact() throws InterruptedException {
        Log.info("Running testcase verify button display");
        create_contactPage.sendInfo(helper.firstName, helper.lastName, helper.phoneNum, helper.email, helper.sip, helper.address);
        create_contactPage.saveContact();
    }
    @Test(priority = 2)
    public void verifyToastMess(){
        String expToastMess = helper.firstName+ " "+ helper.lastName+" saved";
        assertEquals(create_contactPage.verifyToastMessDisplay(), expToastMess, "Toast message is not match");
    }
}
