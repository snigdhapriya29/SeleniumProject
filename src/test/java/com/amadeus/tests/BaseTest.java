package com.amadeus.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.amadeus.utils.BaseClass;

public class BaseTest
{
	public WebDriver driver;
	
	@BeforeMethod
	public void BaseTestVerify()
	{
		com.amadeus.utils.BaseClass.initiateDriver("PageObjects", "appconfig.properties");
		this.driver = BaseClass.driver;
		
	}
	
    //Launch the sepcified languagte URL
	
	public void  urlToLaunch() {
		
		
	}
	

	public void sleep(int seconds) {
	    try {
	        Thread.sleep(seconds * 1000);
	    } catch (InterruptedException e) {

	    }
	}
	
	@AfterMethod
    public void CleanUp()
    {
		driver.close();
		driver.quit();
    }
}
