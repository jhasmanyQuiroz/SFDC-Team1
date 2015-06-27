package com.salesforce.dev.pages.Home;
import com.salesforce.dev.framework.LoggerManager;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.salesforce.dev.framework.DriverManager;

/**
 * Created by Monica Pardo on 6/4/2015.
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "username")
    WebElement userNameFld;

    @FindBy(id = "password")
    WebElement passwrodFld;

    @FindBy(id = "Login")
    WebElement loginBtn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait=DriverManager.getInstance().getWait();
        PageFactory.initElements(driver,this);
    }

    public void setUserName(String name){
        try {
            wait.until(ExpectedConditions.visibilityOf(userNameFld));
            userNameFld.sendKeys(name);
            LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                    "The UserName value: " +name + " was filled in textInput");
        }
        catch(WebDriverException e){
            LoggerManager.getInstance().addFatalLog(this.getClass().getName(),
                    "The UserName value couldn't be filled in textInput",
                    e.fillInStackTrace());
        }
    }
    public void setPassword(String password){
        try {
            wait.until(ExpectedConditions.visibilityOf(passwrodFld));
            passwrodFld.sendKeys(password);
            LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                    "The Password value was filled in textInput");
        }
        catch(WebDriverException e){
            LoggerManager.getInstance().addFatalLog(this.getClass().getName(),
                    "The Password value couldn't be filled in textInput",
                e.fillInStackTrace());
            }
    }
    public MainPage clickLoginBtn(){
        try {
            wait.until(ExpectedConditions.visibilityOf(loginBtn));
            loginBtn.click();
            LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                "The Login button was clicked");
        }
        catch(WebDriverException e){
            LoggerManager.getInstance().addFatalLog(this.getClass().getName(),
                "The Login button couldn't be find",
                e.fillInStackTrace());
        }
        return new MainPage(this.driver);
    }

    /**
     * Returns the Main Page which contains the TopHeader and Navigation Bar
     * And the Main Page is the one who initializes those objects
     *
     * @author: Jimmy Vargas
     * */
    public MainPage loginAs(String userName,String password){
        this.setUserName(userName);
        this.setPassword(password);
        this.clickLoginBtn();

        // this is the crazy page
        //ICNotNeededPage icNotNeededPage = new ICNotNeededPage(this.driver);
        /*if(icNotNeededPage.isICNotNeededPagePresent()){
            icNotNeededPage.continueButton.click();
        }*/
        return new MainPage(this.driver);
    }

    public boolean isLoginButtonPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginBtn));
            return loginBtn.isDisplayed();
        } catch (WebDriverException e) {
            LoggerManager.getInstance().addFatalLog(this.getClass().getName(),
                    "The Login button couldn't be find",
                    e.fillInStackTrace());
            return false;
        }
    }


}