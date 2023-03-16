package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Edit_ContactPage;
import ultilities.listeners.ReportListener;

import static org.testng.Assert.assertEquals;
import static pages.BaseSetup.helper;
@Listeners(ReportListener.class)
public class Edit_ContactTest extends BaseTest {
    Edit_ContactPage edit_contactPage;

    @BeforeClass
    public void navigateToPage(){
        edit_contactPage = homePage.navigateToEdit();
    }
    @Test(priority = 1)
    public void sendEditInfo(){
        edit_contactPage.sendInfo(helper.editFirstName, helper.editLastName);
        edit_contactPage.saveEditContact();
    }
    @Test(priority = 2)
    public void verifyToastMess(){
        String expTitle = helper.editFirstName+" "+ helper.editLastName+" saved";
        assertEquals(edit_contactPage.verifyToastMessDisplay(), expTitle, "Toast message is not match");
    }
}
