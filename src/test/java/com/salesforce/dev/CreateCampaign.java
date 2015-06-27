package com.salesforce.dev;

import com.salesforce.dev.framework.LoggerManager;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Campaigns.CampaignDetail;
import com.salesforce.dev.pages.Campaigns.CampaignForm;
import com.salesforce.dev.pages.Campaigns.CampaignsHome;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.Base.SearchLookupBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Marcelo.Vargas on 6/15/2015.
 */
public class CreateCampaign {

    private String campaignName = "Campaign Name 03";
    private String campaignType = "Webinar"; //Conference, Webinar, Trade Show, Public Relations, Partners, Referral Program, Advertisement, Banner Ads, Direct Mail, Email, Telemarketing, Other
    private String campaignStatus = "Completed"; //--None--, Planned, In Progress, Completed, Aborted
    private String startDate = "6/15/2016";
    private String endDate = "6/17/2016";
    private String expectedRevenue = "2500";
    private String budgetedCost = "2000";
    private String actualCost = "3500";
    private String expectedResponse = "78";
    private String numSent = "10";
    private String parentCampaign = "CampaignParent";

    private CampaignsHome campaignsHome;
    private CampaignDetail campaignDetail;
    private CampaignForm campaignForm;
    private HomePage homePage;
    private MainPage mainPage;
    private NavigationBar navigationBar;

    private SearchLookupBase searchLookup;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        mainPage = homePage.loginAsPrimaryUser();
    }

    @Test
    public void testCreateCampaign() {
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCamapaignsHome();
        campaignForm = campaignsHome.clickNewBtn();
        campaignForm.setCampaignName(campaignName);
        campaignForm.checkActiveCheckbox();
        campaignForm.setTypeSelect(campaignType);
        campaignForm.setStatusSelect(campaignStatus);
        campaignForm.setStartDate(startDate);
        campaignForm.setEndDate(endDate);
        campaignForm.setExpectedRevenue(expectedRevenue);
        campaignForm.setBudgetedCost(budgetedCost);
        campaignForm.setActualCost(actualCost);
        campaignForm.setExpectedResponse(expectedResponse);
        campaignForm.setNumSent(numSent);
        /*
        searchLookup = campaignForm.clickLookupParentCampaign();
        searchLookup.searchText(parentCampaign);
        campaignForm = searchLookup.goToCampaignForm();
        */
        campaignDetail = campaignForm.clickSaveBtn();

        LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                "Campaign was created");

        //Assert.assertTrue(contactDetail.VerifyContact(lastName), "contact was not Created");
    }

    @AfterMethod
    public void tearDown() {
        campaignDetail.clickDeleteBtn(true);
        LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                "Campaign  was created");

    }
}
