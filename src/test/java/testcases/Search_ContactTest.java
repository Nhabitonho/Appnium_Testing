package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Search_ContactPage;
import ultilities.listeners.ReportListener;

import static org.testng.Assert.assertEquals;
import static pages.BaseSetup.helper;
@Listeners(ReportListener.class)
public class Search_ContactTest extends BaseTest{
    Search_ContactPage search_contactPage;

    @BeforeClass
    public void navigateToPage(){
        search_contactPage = homePage.navigateToSeachTab();
    }

    @Test(priority = 1)
    public void searchContactInfo(){
        search_contactPage.searchContact("Eric Cartons");
//        search_contactPage.searchContact(helper.searchName);
    }
    @Test(priority = 2)
    public void verifyFirstNameContact(){
//        String expTitle = helper.searchName;
        String expTitle = "Eric Cartons";
        assertEquals(search_contactPage.verifyNameContact(), expTitle, "Name Contact is not match");
    }
}
