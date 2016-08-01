package com.amadeus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class BasePage 
{
	public WebDriver driver;
	
	//If it a shopping Web site containing category and sub-category in the home page
	public void HoverMainGroupAndClickOnSubGroup(By mainGroup, By subGroup)
	{
		WebElement elems=driver.findElement(mainGroup);//Menu Item
	    Actions action = new Actions(driver);
	    action.moveToElement(elems).build().perform();
	    this.driver.findElement(subGroup).click();
	    this.sleep(3);	
	}
	
	public void sleep(int seconds) 
	{
	    try {
	        Thread.sleep(seconds * 1000);
	    } catch (InterruptedException e) {

	    }
	}
}
