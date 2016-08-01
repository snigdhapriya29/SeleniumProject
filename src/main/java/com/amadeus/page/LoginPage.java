package com.amadeus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import com.amadeus.helpers.Validation;
import com.amadeus.utils.BaseClass;

public class LoginPage extends BasePage {
	
	//public By LoginLink = By.className("btn-group-login");
	//public By LoginLink = By.linkText("Login");

	public By LoginLink = By.xpath("//div[@class='btn-group-login']//a[@class='header-btn btn-login']");
	
	public By UserNameEditBox = By.name("logonId");
	public By PasswordEditBox = By.id("logonPassword");
	public By LoginButton = By.name("logOnButton");
	public By MyProfileLink = By.xpath("//div[@class='btn-group-profile']//a[@class='header-btn btn-profile']");
	public By MyProfileUserName = By.xpath("//div[@class='profileLayerBottom']//table[@class='table unstyled']//tr[1]/td[2]");
	public By LoginError = By.xpath("//div[@class='container bodyContainer ']//div[@class='alert loginError']");
	
	public By LogoutLink = By.linkText(BaseClass.resourceBundle.getProperty("LogoutLink"));
	


	public LoginPage(WebDriver driver) {
		this.driver=BaseClass.driver;
	}	
			
	
	public void LoginToStore(String username, String password){
		driver.findElement(LoginLink).click();
		
		driver.findElement(UserNameEditBox).sendKeys(username);
		driver.findElement(PasswordEditBox).sendKeys(password);
		driver.findElement(LoginButton).click();
		sleep(3);
	}

	public void GetTheLoggedInUserName(String username){
		driver.findElement(MyProfileLink).click();
		String actualUsername = driver.findElement(MyProfileUserName).getText();
		Assert.assertEquals(actualUsername, username);	
	}
	
	public void ClickOnLogoutLink(){
		driver.findElement(LogoutLink).click();
		sleep(2);
	}
	
	public void ClickOnLogoutLinkWhichIsNotPresent(){
		Validation.elementPresent(By.linkText(BaseClass.resourceBundle.getProperty("LogoutLink")), "LogoutLink", "FAIL Logout link not found");
		BaseClass.logging("Logout Link not prsent");
		System.out.println("Taken screenshot successfully");
		
		
	}
	
	
	public String RetrieveTheLoginFailedError(){
		
	String loginFailedError = driver.findElement(LoginError).getText();
	
	return  loginFailedError;
			
	
	}
	
	
	}
	

