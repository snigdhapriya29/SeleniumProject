package com.amadeus.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amadeus.helpers.DBHelper;
import com.amadeus.utils.BaseClass;

public class ShoppingCartPage  extends BasePage{
	
	public By SearchEditBox = By.xpath("//div[@class='container']//input[@class='searchField headerSearch']");
	
	public String StoreId = BaseClass.resourceBundle.getProperty("StoreId");

	
	//public String searchQuery = BaseClass.resourceBundle.getProperty("SearchText");

	
	public By SearchIcon = By.xpath("//div[@class='btn-group']//button[@class='btn btn-search btn-warning']");
	
	public By AddToCartButton = By.xpath("//div[@class='btn-group']//button[@class='btn add-to-cart ']");
	
	public By SuccessAddToCartButton = By.xpath("//div[@class='btn-group']//button[@class='btn add-to-cart btn-success']");
	
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver=BaseClass.driver;
	}	
	
	
	public String searchQueryForSpeificStore(String StoreId){
		
		String search = "select catd.SHORTDESCRIPTION from CATENTRY cat,CATENTDESC catd where cat.catentry_id IN (select CATENTREL.CATENTRY_ID_PARENT from CATENTREL where CATENTREL.CATENTRY_ID_CHILD IN (select CATENTRY_ID from STORECENT where storeent_id ="+ StoreId+ "and rownum <1000)) and cat.MARKFORDELETE = 0 AND cat.CATENTTYPE_ID = 'ProductBean' and cat.CATENTRY_ID = catd.CATENTRY_ID and catd.PUBLISHED=1 and catd.LANGUAGE_ID = -1";
		return search;
	}
	
	
	
	public void SearchItem()
	{
		
		String searchQuery =  DBHelper.getItemFromFirstRow(StoreId);
		
		driver.findElement(SearchEditBox).sendKeys(searchQuery);
		sleep(1);
		/*List<WebElement> listItems = driver.findElements(By.xpath("//div[@class='autocompleteResults typeahead dropdown-menu']//ul//li"));
		
		 listItems.get(2).click();
		sleep(2);*/
		driver.findElement(SearchIcon).click();
		sleep(2);
		driver.findElement(AddToCartButton).click();	
		
	}

}
