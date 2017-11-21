package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

public class loginPage {

	WebDriver driver;
	@FindBy(id = "login-email")
	WebElement userNameInpt;

	@FindBy(id = "login-password")
	WebElement pwdInpt;

	@FindBy(id = "login-submit")
	WebElement loginBtn;

	@FindBy(css = ".error strong")
	WebElement alertSection;

	@FindBy(id = "dismiss-alert")
	WebElement alertCloseBtn;

	@FindBy(css = ".link-forgot-password")
	WebElement forgotPasswordLnk;

	@FindBy(id = "userName-requestPasswordReset")
	WebElement userNamerequestPasswordReset;

	@FindBy(id = "btnSubmitResetRequest")
	WebElement btnSubmitResetRequest;

	public loginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setUserName(String strUserName) {
		userNameInpt.sendKeys(strUserName);
	}

	public void setPWD(String strPWD) {
		pwdInpt.sendKeys(strPWD);
	}

	public String errorMsg() {
		return alertSection.getText();
	}

	public void clickLogin() {
		loginBtn.click();
	}

	public void clickForgotPassword()

	{
		this.forgotPasswordLnk.click();
	}

	public void loginToLinked(String usr, String pwd) {
		this.userNameInpt.sendKeys(usr);
		this.pwdInpt.sendKeys(pwd);
		this.clickLogin();
	}

	public void invalidCredsLogin() {
		// in order to escape captcha check, we have to type user name and password as
		// if the user was typing them on keyboard.
		this.userNameInpt.sendKeys(Keys.SEMICOLON + Keys.chord("ee@wrongemail.com"));
		this.pwdInpt.sendKeys(Keys.SEMICOLON);
		this.pwdInpt.sendKeys(Keys.ENTER);
	}

	public void resetUserName() {
		this.userNamerequestPasswordReset.sendKeys("helloworld");
		this.btnSubmitResetRequest.click();
	}

	public boolean resetBtnStatus() {
		return this.btnSubmitResetRequest.isEnabled();
	}

	public void dismissAlert() {
		// dismiss warring message if visible
		boolean alert = driver.findElements(By.id("dismiss-alert")).size() != 0;
		if (alert) {
			this.alertCloseBtn.click();
		}
	}
}
