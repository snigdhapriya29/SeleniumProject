package com.amadeus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import  com.amadeus.utils.BaseClass;

public class LaunchWebsite extends BasePage {
	
	String language = BaseClass.config.getProperty("language");
	String languageToLoad = language.replace('_', '/');

	
	//public By LanguageLink = By.partialLinkText(languageToLoad);
	public By LanguageLink = By.xpath("//span/a[contains(@href,'"+languageToLoad +"')]");
	public By PageToLoad = By.linkText(BaseClass.resourceBundle.getProperty("PageToLoad"));
  
	public LaunchWebsite(WebDriver driver) {
		this.driver=BaseClass.driver;
	}


	
	public void LaunchTheSpecifiedUrl() throws InterruptedException{
		driver.findElement(LanguageLink).click();
		sleep(2);	
		driver.findElement(PageToLoad).click();
  }
	
 }