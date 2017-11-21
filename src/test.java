
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.loginPage;

public class test {
	WebDriver driver;
	loginPage loginPageObj;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "/Users/helloworld/downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.linkedin.com/");
	}

	// Scenario: successfulLogin
	@Test
	public void successfulLogin() {
		// given login page open
		loginPageObj = new loginPage(driver);
		// when click login with valid credentials
		loginPageObj.loginToLinked(“testemail@testemail.com”, “testpassword”);
		// then homepage displayed
		assertTrue(driver.getTitle().equals("LinkedIn"));
	}

	// Scenario: unSuccessfulLogin
	@Test
	public void unsuccessfulLogin() {
		// given login page open
		loginPageObj = new loginPage(driver);
		loginPageObj.dismissAlert();
		// when click login with invalid credentials
		loginPageObj.invalidCredsLogin();
		// then signin page displayed
		assertTrue(driver.getTitle().equals("Sign In to LinkedIn"));
		// and error message displayed
		assertTrue(loginPageObj.errorMsg()
				.equals("There were one or more errors in your submission. Please correct the marked fields below."));
	}

	// Scenario: forgot password
	@Test
	public void forgotPassword() {
		// given login page open
		loginPageObj = new loginPage(driver);
		loginPageObj.dismissAlert();
		// when click forgot password
		loginPageObj.clickForgotPassword();
		// then password recovery page displayed
		assertTrue(driver.getTitle().equals("Password Change | LinkedIn"));
		// and password reset button disabled
		assertFalse(loginPageObj.resetBtnStatus());

		// when reset password button clicked with invalid email address
		loginPageObj.resetUserName();
		// then error message displayed
		assertTrue(loginPageObj.errorMsg().equals("Please correct the marked field(s) below."));

	}

	@AfterMethod
	public void quit() {
		System.out.print("Browser close");
		driver.quit();
	}
}
