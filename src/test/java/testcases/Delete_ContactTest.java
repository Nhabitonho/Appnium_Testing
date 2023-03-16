package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Delete_ContactPage;
import ultilities.listeners.ReportListener;

import static org.testng.Assert.assertEquals;
import static pages.BaseSetup.helper;
@Listeners(ReportListener.class)
public class Delete_ContactTest extends BaseTest{
    Delete_ContactPage delete_contactPage;
    @BeforeClass
    public void navigateToPage(){
        delete_contactPage = homePage.navigateToDelete();
    }

    @Test(priority = 1)
    public void deleteContactInfo(){
        delete_contactPage.deleteContact();
    }
    @Test(priority = 2)
    public void verifyToastMess(){
        String expTitle = helper.editFirstName+" "+ helper.editLastName+" deleted";
        assertEquals(delete_contactPage.verifyToastMessDisplay(), expTitle, "Toast message is not match");
    }
}
