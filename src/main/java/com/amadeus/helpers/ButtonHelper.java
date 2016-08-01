package com.amadeus.helpers;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.base.Function;
import com.amadeus.utils.BaseClass;

/* 
 * Project    :Amadeus
 * Script     : ButtonHelper
 * Author     : Meera
 * Date       : July.28.2016
 */

@SuppressWarnings("serial")
class noLocatorexceptions  extends Exception
{
	noLocatorexceptions(String s)
	{super(s);}
}

public class ButtonHelper{ 
	public static boolean present;	

	/** This method is used for clicking a button by xpath.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */
	public static void clickButtonByXpath(String locator, String objLocator){
		BaseClass.logging("Executing Click Button event");
		try{   
			if(!(BaseClass.resourceBundle.containsKey(locator))||(BaseClass.resourceBundle.getProperty(locator)).isEmpty())   
			{throw  new noLocatorexceptions(locator);}
			else{
				//ButtonHelper.LoadingMaskAndServerMessageValidation();				
				BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator))).click();
				BaseClass.logging("Clicked on"+locator);
				System.out.println("Clicked on"+locator);
				//ButtonHelper.LoadingMaskAndServerMessageValidation();
			}
		} 
		catch(NoSuchElementException e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
			Validation.screenShot(locator);
			BaseClass.logging("++++++++++++++++++++++++++++++ERROR+++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++ERROR+++++++++++++++++++++++++");
			System.out.println(locator+" Button not found ");
			Assert.fail(locator+" Button not found ");
		} 
		catch(noLocatorexceptions e) {
			BaseClass.logging(objLocator  +  " Button locator not found");
			e.printStackTrace();
			Validation.screenShot(locator);
			BaseClass.logging("++++++++++++++++++++++++++++++ERROR+++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++ERROR+++++++++++++++++++++++++");
			System.out.println(locator+" Button not found ");
			Assert.fail(objLocator+" Button  not found ");
		} 
		catch(Exception e) {
			Validation.screenShot(locator);
			BaseClass.logging("++++++++++++++++++++++++++++++ERROR+++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++ERROR+++++++++++++++++++++++++");
			System.out.println(locator+" Button not found ");
			Assert.fail(objLocator+" Button  not found ");
			e.printStackTrace();
		}
		System.out.println("+++++++++++++++++++++++++++++ERROR+++++++++++++++++++++++++");
		BaseClass.logging("++++++++++++++++++++++++++++++ERROR+++++++++++++++++");

	}
	public static void clickByXpath(String locator, String objLocator){
		BaseClass.logging("Executing Click Button event");
		try{   
			if(!(BaseClass.resourceBundle.containsKey(locator))||(BaseClass.resourceBundle.getProperty(locator)).isEmpty())   
			{throw  new noLocatorexceptions(locator);}
			else{

				visibilityOfElementLocatedbyid(locator);
				BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator))).click();
				BaseClass.logging("Clicked on"+objLocator);
				System.out.println("Clicked on"+objLocator);

			}
		} 
		catch(NoSuchElementException e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
			Validation.screenShot(locator);
			Assert.fail(locator+" Button is not found ");
		} 
		catch(noLocatorexceptions e) {
			BaseClass.logging(objLocator  +  " Xpath locator not found");
			e.printStackTrace();
			Assert.fail(objLocator+" Xpath Locator  not found ");
		} 
		catch(Exception e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
		} 
	}
	public static ExpectedCondition<WebElement> visibilityOfElementLocatedbyid(final String locator) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement toReturn = null;
				try {
					toReturn = BaseClass.wbDv.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
					if(toReturn.isDisplayed()) {
						return toReturn;
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
					Assert.fail("Element is not found on the web page");
				}

				return toReturn;
			}
		};
	}


	/***********************/
	public static void clickButtonByDynamicXpath(String locator, String text) {
		//LoadingMaskAndServerMessageValidation();
		String Locator=null;
		try {

			String[] TXT=BaseClass.resourceBundle.getProperty(locator).split("\\+");
			Locator=TXT[0]+text+TXT[1];
			BaseClass.driver.findElement(By.xpath(Locator)).click();

		}catch (Exception e)
		{
			BaseClass.logging("Element "+locator+" not found");


		}
		//LoadingMaskAndServerMessageValidation();
	}

	/** This method is used for clicking a button by id.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */
	public static void clickButtonById (String locator, String objLocator){
		BaseClass.logging("Executing Click Button event");
		try{   
			if(!(BaseClass.resourceBundle.containsKey(locator))||(BaseClass.resourceBundle.getProperty(locator)).isEmpty())
			{throw  new noLocatorexceptions(locator);}	
			else	
			{
				//ButtonHelper.LoadingMaskAndServerMessageValidation();
				ButtonHelper.WaitForLoadMaskToComplete("WBtn_Right_Panel_id", 10);
				WebElement elems=BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));//Menu Item
				if(elems.isEnabled()){   
					Actions builder = new Actions(BaseClass.driver);
					builder.moveToElement(elems).build().perform();
					Thread.sleep(1000);
					System.out.println("Clicked on"+objLocator+"Button");
					BaseClass.logging("Clicked on"+objLocator+"Button");
					elems.click();
					ButtonHelper.WaitForLoadMaskToComplete("WBtn_Right_Panel_id", 10);
					//ButtonHelper.LoadingMaskAndServerMessageValidation();
					
				}
			}
		}
		//BaseClass.wbDv.findElement(By.id(BaseClass.OR.getProperty(locator))).click();
		catch(NoSuchElementException e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
			Validation.screenShot(locator);
			Assert.fail(locator+" Button is not found ");
		} 
		catch(noLocatorexceptions e) {
			BaseClass.logging(objLocator  +  " locator not found");
			e.printStackTrace();
			Assert.fail(locator+"  Locator  not found ");
		} 
		catch(Exception e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
		}
	}
	public static void clickbyId (String locator, String objLocator){
		BaseClass.logging("Executing Click Button event");
		try{   
			if(!(BaseClass.resourceBundle.containsKey(locator))||(BaseClass.resourceBundle.getProperty(locator)).isEmpty())
			{throw  new noLocatorexceptions(locator);}	
			else	
			{

				WebElement elems=BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));//Menu Item
				if(elems.isEnabled()){   
					Actions builder = new Actions(BaseClass.driver);
					builder.moveToElement(elems).build().perform();
					Thread.sleep(1000);
					elems.click();

					BaseClass.logging("Clicked on"+locator);
				}
			}
		}
		//BaseClass.wbDv.findElement(By.id(BaseClass.OR.getProperty(locator))).click();
		catch(NoSuchElementException e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
			Validation.screenShot(locator);
			Assert.fail(locator+" Button is not found ");
		} 
		catch(noLocatorexceptions e) {
			BaseClass.logging(objLocator  +  " Xpath locator not found");
			e.printStackTrace();
			Assert.fail(locator+" Xpath Locator  not found ");
		} 
		catch(Exception e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
		}
	}
	public static void clickById (String locator, String objLocator){
		BaseClass.logging("Executing Click Button event");
		try{   
			if(!(BaseClass.resourceBundle.containsKey(locator))||(BaseClass.resourceBundle.getProperty(locator)).isEmpty())
			{throw  new noLocatorexceptions(locator);}	
			else	
			{

				WebElement elems=BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));//Menu Item
				if(elems.isEnabled()){   
					Actions builder = new Actions(BaseClass.driver);
					builder.moveToElement(elems).build().perform();
					Thread.sleep(1000);
					elems.click();

					BaseClass.logging("Clicked on"+locator);
				}
			}
		}
		catch(NoSuchElementException e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
			Validation.screenShot(locator);
			Assert.fail(locator+" Button is not found ");
		} 
		catch(noLocatorexceptions e) {
			BaseClass.logging(objLocator  +  " Xpath locator not found");
			e.printStackTrace();
			Assert.fail(locator+" Xpath Locator  not found ");
		} 
		catch(Exception e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
		}
	}

	/** This method is used for asserting enable button by xpath.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */


	/** This method is used for asserting enable button by Id.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */


	/** This method is used for checking whether button is disable by xpath.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */


	/** This method is used for checking whether button is disabled by id.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */


	public static boolean isButtonPresentById(String locator, String objLocator) {
		try {
			BaseClass.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			boolean flag= BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator))).isDisplayed();
			return flag;
		}
		catch (NoSuchElementException e)
		{
			BaseClass.logging(objLocator+" not present "+objLocator);
			return false;

		}
		catch (Exception e)
		{
			BaseClass.logging(objLocator+" not present "+objLocator);
			return false;

		}}
	public static String assertDisableButtonID(String locator, String objLocator, String ErrorMsg) {
		BaseClass.logging("Executing assert disable button");
		try {
			WebElement button = BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
			if(!button.isEnabled()) {
			}
		} 
		catch (NoSuchElementException e) {
			BaseClass.logging(objLocator+"The button is Enabled ");
			BaseClass.logging(ErrorMsg);

			e.printStackTrace();
		} 
		catch (Exception e) {
			return "Fail - The button is Enabled ";
		}
		return "Pass - Button is Disabled ";
	}

	/** This method is used for checking whether button is present by xpath.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */
	public static String assertButtonPresentByXpath(String locator,String objLocator){
		BaseClass.logging("Executing assert button present ");
		try {
			visibilityOfElementLocatedbyid(locator);
			BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
			present = true;
		} 
		catch (NoSuchElementException e) {
			e.printStackTrace();
			BaseClass.logging(objLocator + "Button not present");
			Assert.fail(objLocator+" Button not present");
		} 
		catch (Exception e) {
			present = false;
			return "Fail - Button not present";
		}
		return "Pass";
	}
	public static boolean isNotPresentById(String locator, String objLocator) {
		try {
			BaseClass.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);


			boolean flag= BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator))).isDisplayed();

			return flag;


		}
		catch (NoSuchElementException e)
		{
			BaseClass.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			BaseClass.logging("element not present "+objLocator);
			return false;

		}
		catch (Exception e)
		{
			BaseClass.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			BaseClass.logging("element not present "+objLocator);
			return false;
		}

	}
	/** This method is used for checking whether button is present by id.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */
	public static String assertButtonPresentById(String locator,String objLocator){
		BaseClass.logging("Executing assert button present ");
		try {
			visibilityOfElementLocatedbyid(locator);
			BaseClass.wbDv.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
			present = true;
		} 
		catch (NoSuchElementException e) {
			e.printStackTrace();
			BaseClass.logging(objLocator + "Button not present");
			Assert.fail(objLocator+" Button not present");
		} 
		catch (Exception e) {
			present = false;
			return "Fail - Button not present";
		}
		return "present";
	}


	/** This method is used for clicking a button by CSSImage.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */


	public static String ButtonPresentByID(String locator,String objLocator,String ErrorMsg) {
		BaseClass.logging("Checking for an element present");
		try { 
			WebElement elem = BaseClass.wbDv.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
			if(elem.isDisplayed()){
				BaseClass.logging(objLocator+"Element is present ");    
			}
		}
		catch (NoSuchElementException e) {
			BaseClass.logging(objLocator+"element not present ");
			BaseClass.logging(ErrorMsg);
			e.printStackTrace();
		}
		catch (Exception e) {
			BaseClass.logging(ErrorMsg);
			Assert.fail(objLocator+"element not present");


		}
		return locator;

	}


	public static String ButtonPresentByXpath(String locator,String objLocator,String ErrorMsg) {
		BaseClass.logging("Checking for an element present");
		try { 
			WebElement elem = BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
			if(elem.isDisplayed()){
				BaseClass.logging(objLocator+"Element is present ");    
			}
		}
		catch (NoSuchElementException e) {
			BaseClass.logging(objLocator+"element not present ");
			BaseClass.logging(ErrorMsg);
			e.printStackTrace();
		}
		catch (Exception e) {
			BaseClass.logging(ErrorMsg);
			Assert.fail(objLocator+"element not present");


		}
		return locator;

	}



	/** This method is used for checking an element is present or not.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * 
	 */


	/*public void explixitWait() {
		int i = 100;
		WebDriverWait wdw = new WebDriverWait(BaseClass.wbDv, i);
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
	 */


	/** This method is used for clicking a button.
	 * 
	 * @Param : locator
	 * @param : objLocator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 * 
	 * @Reviewed By : 
	 */

	public static boolean verifyElementAbsent(String locator,String LocatorName,String ErrorMessage) throws Exception  {
		BaseClass.logging("Checking for an element present");

		boolean present;
		try {
			BaseClass.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			// WebElement object = BaseClass.driver.findElement(By.id(BaseClass.OR.getProperty(locator)));
			present=BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator))).isDisplayed();
			if(present)
			{
				BaseClass.logging(ErrorMessage);
				Assert.fail(locator+"is present");
			}
		} 
		catch (NoSuchElementException e) {
			present = true;


		}
		return present;
	}

	public static boolean verifyElementAbsentbyXpath(String locator,String LocatorName,String ErrorMessage) throws Exception  {
		BaseClass.logging("Checking for an element present");

		boolean present;
		try {

			WebElement object = BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));

			present = false;
			BaseClass.logging(ErrorMessage);
			Assert.fail(locator+"is present");

		} 
		catch (NoSuchElementException e) {
			present = true;


		}
		return present;
	}

	public static String WAITforbutton(String locator) {
		BaseClass.logging("Checking for an element present");
		BaseClass.logging("Checking for an element present");
		WebDriverWait wait = new WebDriverWait(BaseClass.driver, 30);
		try { 
			WebElement elem=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(BaseClass.resourceBundle.getProperty(locator))));
			if(elem.isEnabled()){
				BaseClass.logging("Button is present "); 
			}
		}
		catch (NoSuchElementException e) {
			BaseClass.logging("Button not present ");
			e.printStackTrace();
		}
		catch (Exception e) {
			Assert.fail("Button not present");

		}

		return locator; 

	}  















	public static boolean waitForPageFullyLoaded(String locator, int timeoutMs) throws InterruptedException 
	{
		try{     	   
			int previous;
			int current = 0;
			int timeSliceMs = 1000;
			do 
			{
				previous = current;
				Thread.sleep(timeSliceMs);
				timeoutMs -= timeSliceMs;
				current = BaseClass.driver.findElements(By.xpath(BaseClass.resourceBundle.getProperty(locator))).size();
			} 
			while(current > previous && timeoutMs > 0);
		}catch(NoSuchElementException e)
		{		
			BaseClass.logging(locator+"element not present ");
			Validation.screenShot(locator);
			e.printStackTrace();
		}
		if(timeoutMs > 0) 
		{
			return true;
		}
		return false;

	}


	public static boolean waitForPageFullyLoadedID(String locator, int timeoutMs) throws InterruptedException {
		try
		{
			int previous;
			int current = 0;
			int timeSliceMs = 1000;
			do
			{
				previous = current;
				Thread.sleep(timeSliceMs);
				timeoutMs -= timeSliceMs;
				current = BaseClass.driver.findElements(By.id(BaseClass.resourceBundle.getProperty(locator))).size();
			} while(current > previous && timeoutMs > 0);
		}catch(NoSuchElementException e){		
			BaseClass.logging(locator+"element not present ");
			Validation.screenShot(locator);
			e.printStackTrace();
		}
		if(timeoutMs > 0) {
			return true;
		}
		return false;
	}
	public static WebElement findElementWithTimeoutWait(By by, long timeOut) throws InterruptedException { 
		WebElement e = null; 
		ButtonHelper.LoadingMaskAndServerMessageValidation();
		try 
		{ 
			long elapsedTime = 0; 
			while(elapsedTime < timeOut){ 
				elapsedTime++; 
				Thread.sleep(1000); 
				e = BaseClass.driver.findElement(by); 
				break; 

			} 
		}catch (NoSuchElementException nse) 
		{
			Assert.fail("element not present");
			BaseClass.logging("element not present ");
			Validation.screenShot(null);
		} 

		return e;
	} 
	public static boolean waitForElementPresentByXpath(String locator, int i) {
		//System.out.println("Inside function waitForByXpath");
		try {
			WebDriverWait wait;
			wait = new WebDriverWait(BaseClass.driver, i);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BaseClass.resourceBundle.getProperty(locator))));
			System.out.println("found " +locator);

			return true;
		} catch (TimeoutException e) {
			BaseClass.logging("Element "+locator+" not found");
			return false;
		}
		catch (Exception e)
		{
			BaseClass.logging("Element "+locator+" not found");
			return false;
		}
	}
	public static boolean waitForElementPresentByID(String locator, int i) {
		//System.out.println("Inside function waitForByXpath");
		try {
			ButtonHelper.LoadingMaskAndServerMessageValidation();
			WebDriverWait wait;
			wait = new WebDriverWait(BaseClass.driver, i);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(BaseClass.resourceBundle.getProperty(locator))));
			System.out.println("found " +locator);

			return true;
		} catch (TimeoutException e) {
			BaseClass.logging("Element "+locator+" not found");
			return false;
		}
		catch (Exception e)
		{
			BaseClass.logging("Element "+locator+" not found");
			return false;

		}}

	public static  boolean WaitForLoadMaskToComplete(String locator, int timeOut) throws Exception 
	{
		BaseClass.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Boolean flag = false;
		//System.out.println("Inside function waitForById");
		try {


			WebDriverWait wait;
			wait = new WebDriverWait(BaseClass.driver, timeOut);
			flag = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(BaseClass.config.getProperty("WBtn_LoadingMask"))));
		} catch (Exception e) {
			BaseClass.logging("Loading masks still presnt"+timeOut);
		}
		BaseClass.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return flag;
	}


	public static  boolean LoadMaskid(String locator, int timeOut) throws Exception 
	{
		try
		{
			int i;
			for(i=0;i<=timeOut;i++){
				Thread.sleep(1000);
				boolean abc =BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator))).isDisplayed();

				if(abc)
				{
					Thread.sleep(500);	
					break;
				}
			}
		}

		catch(NoSuchElementException e) 
		{
			BaseClass.logging(locator+"element not present ");
			Validation.screenShot(locator);
		}
		return present; 
	}


	public static  boolean ModalWindowWaitForLoadMaskToComplete(int timeOut) throws Exception 
	{
		BaseClass.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Boolean flag = false;
		//System.out.println("Inside function waitForById");
		try {

			//int a=BaseClass.driver.findElements(By.xpath("//a[contains(text(),'Insight Test')]")).size();
			WebDriverWait wait;
			wait = new WebDriverWait(BaseClass.driver, timeOut);
			flag = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(BaseClass.config.getProperty("Wbtn_modalwindow_Loadingmask"))));
		} catch (Exception e) {
			BaseClass.logging("Element still present");
		}
		BaseClass.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return flag;
	}


	public static  void JobWindowWaitForLoadMaskToComplete(String locator, int timeOut) throws Exception 
	{
		try
		{
			int i;
			for(i=0;i<=20;i++){
				Thread.sleep(1000);
				WebElement Mask =BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
				String status=Mask.getAttribute("class");
				if(status.equalsIgnoreCase("ui-dialog-content ui-widget-content"))
				{
					Thread.sleep(500);	
					break;
				}
			}
		}

		catch(NoSuchElementException e) 
		{
			BaseClass.logging(locator+"element not present ");
			Validation.screenShot(locator);
		} 
	}

	public static  void ViewersWaitForLoadMaskToComplete(String locator, int timeOut) throws Exception {
		try{ 
			int i;
			for(i=0;i<=timeOut;i++){
				Thread.sleep(1000);
				WebElement Mask =BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
				String status=Mask.getAttribute("class");
				if(status.equalsIgnoreCase(""))
				{
					Thread.sleep(500);	
					break;
				}}}
		catch(NoSuchElementException e) 
		{
			BaseClass.logging(locator+"element not present ");
			Validation.screenShot(locator);
		}      
	}


	public static  void DetailPageWaitForLoadMaskToComplete(String locator, int timeOut) throws Exception {int i;
	try{
		for(i=0;i<=20;i++)
		{
			Thread.sleep(1000);
			WebElement Mask =BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
			String status=Mask.getAttribute("class");
			if(status.equalsIgnoreCase("baseBody"))
			{	
				Thread.sleep(1500);	
				/*BaseClass.driver.switchTo().frame("iframe_viewer1");
					ButtonHelper.ViewersWaitForLoadMaskToComplete("Wbtn_ann_viewers_xpath", 15);
					BaseClass.driver.switchTo().window("reviewWindow");*/
				break;
			}
		}} 
	catch(NoSuchElementException e) 
	{
		BaseClass.logging(locator+"element not present ");
		Validation.screenShot(locator);
	}
	}



	public  static void screenShot(String name)
	{   
		String name1=System.getProperty("user.dir")+"/test-output/"+ name +".png";
		try {
			File source = ((TakesScreenshot)BaseClass.driver).getScreenshotAs(OutputType.FILE);
			File destFile=new File(name1);
			FileUtils.copyFile(source,  destFile);
			String image ="file:///"+name1;
			Reporter.log("<a href=" + image  + ">ScreenshotLink</a>"); 
		}
		catch(IOException e) {
			BaseClass.logging( "Failed to capture screenshot: " + e.getMessage());
		}

	}



	public static boolean LoadingMaskAndServerMessageValidation() {
		BaseClass.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Boolean flag1 = true;
		int timeout=15;
		try {
			WebDriverWait wait;
			wait = new WebDriverWait(BaseClass.driver, timeout);
			flag1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(BaseClass.config.getProperty("WBtn_LoadingMask"))));
		} catch (TimeoutException e) {
			flag1=false;
			Assert.fail("Time out for waiting for Loading Mask to disappear for"+timeout+"Seconds");
		}catch (Exception e) {
			BaseClass.logging("Loading mask still present even after  :"+timeout+"Seconds");
		}

		boolean flag2=true;

		try { 
			WebElement elem = BaseClass.driver.findElement(By.xpath(BaseClass.config.getProperty("Wlbl_ServerMessageCloseIcon")));
			if(elem.isDisplayed()){
				flag2= false;
				Validation.screenShot("SERVER ERROR");
				Assert.fail("Server Error message is present");
			}
		}
		catch (NoSuchElementException e) {
			// BaseClass.logging("Error Notification  present ");
		}
		catch (Exception e) {
			// BaseClass.logging("Error Notification  present ");
		}
		BaseClass.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return flag1&&flag2;
	}


	public static boolean waitForElementNotPresentById(String locator, int time) {
		BaseClass.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Boolean flag = false;
		//System.out.println("Inside function waitForById");
		try {
			WebDriverWait wait;
			wait = new WebDriverWait(BaseClass.driver, time);
			flag = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(BaseClass.resourceBundle.getProperty(locator))));
		} catch (Exception e) {
			BaseClass.logging("Element "+locator+" still present");
		}
		BaseClass.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return flag;

	}



	public static boolean isButtonPresentByXpath(String locator,String objLocator) {
		{
			try {
				BaseClass.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				boolean flag= BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator))).isDisplayed();
				return flag;
			}
			catch (NoSuchElementException e)
			{
				BaseClass.logging("element not present "+objLocator);
				return false;

			}
			catch (Exception e)
			{
				BaseClass.logging("element not present "+objLocator);
				return false;

			}

		}

	}
	public static boolean waitForElementNotPresentByXpath(String locator,int time) 
	{
		Boolean flag = false;

		try {
			WebDriverWait wait;
			wait = new WebDriverWait(BaseClass.driver, time);

			flag = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(BaseClass.resourceBundle.getProperty(locator))));
		} catch (Exception e) {
			BaseClass.logging("Element "+locator+" still present");
		}
		return flag;
	}

	public static boolean  compareTwoValuesFalse(Integer Actual,Integer Expected,String ErrorMessage)
	{  
		boolean result =false;
		try
		{
			if(Actual==Expected)
				result=true;
			else
				result=false;
			if(result) throw new Elementpresence("");
		}
		catch(Elementpresence e){
			BaseClass.logging(ErrorMessage);
			Assert.fail(ErrorMessage);}

		catch(Exception e){;}
		return result;
	}


	public static  void HtmlViewersWaitForLoadMaskToComplete(String locator, int timeOut) throws Exception {
		int i;
		for(i=0;i<=timeOut;i++){
			Thread.sleep(1000);
			WebElement Mask =BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
			String status=Mask.getAttribute("class");
			if(status.equalsIgnoreCase("viewer")){
				Thread.sleep(500);	
				break;
			}
		}}
	public static WebElement ButtonpresentByXpath(String locator,String objLocator,String ErrorMsg) {
		BaseClass.logging("Checking for an element present");
		WebElement elem=null;
		try { 
			elem = BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
			if(elem.isDisplayed()){
				BaseClass.logging(objLocator+"Element is present ");    
			}
		}
		catch (NoSuchElementException e) {
			BaseClass.logging(objLocator+"element not present ");
			BaseClass.logging(ErrorMsg);
			e.printStackTrace();
		}
		catch (Exception e) {
			BaseClass.logging(ErrorMsg);
			Assert.fail(objLocator+"element not present");


		}
		
		return elem;

	}
	public static String ButtonPresent(String locator,String objLocator,String ErrorMsg) {
		// BaseClass.logging("Checking for an element present");
		try { 
			WebElement elem = BaseClass.wbDv.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
			if(elem.isDisplayed()){
				BaseClass.logging(objLocator+"Element is present ");    
			}
		}
		catch (NoSuchElementException e) {
			Assert.fail(objLocator+"element not present");
			BaseClass.logging(objLocator+"element not present ");
			BaseClass.logging(ErrorMsg);
			e.printStackTrace();
		}
		catch (Exception e) {
			BaseClass.logging(ErrorMsg);
			Assert.fail(objLocator+"element not present");


		}


		return locator;
	}

	public static boolean isclickButtonById (String locator, String objLocator){
		BaseClass.logging("Executing Click Button event");
		try{   
			if(!(BaseClass.resourceBundle.containsKey(locator))||(BaseClass.resourceBundle.getProperty(locator)).isEmpty())
			{throw  new noLocatorexceptions(locator);}	
			else	
			{
				ButtonHelper.LoadingMaskAndServerMessageValidation();
				WebElement elems=BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));//Menu Item
				if(elems.isEnabled()){   
					Actions builder = new Actions(BaseClass.driver);
					builder.moveToElement(elems).build().perform();
					Thread.sleep(1000);
					System.out.println("Clicked on"+objLocator+"Button");
					BaseClass.logging("Clicked on"+objLocator+"Button");
					elems.click();
					ButtonHelper.LoadingMaskAndServerMessageValidation();
					
				}
			}
		}
		//BaseClass.wbDv.findElement(By.id(BaseClass.OR.getProperty(locator))).click();
		catch(NoSuchElementException e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
			Validation.screenShot(locator);
			Assert.fail(locator+" Button is not found ");
		} 
		catch(noLocatorexceptions e) {
			BaseClass.logging(objLocator  +  " locator not found");
			e.printStackTrace();
			Assert.fail(locator+"  Locator  not found ");
		} 
		catch(Exception e) {
			BaseClass.logging(objLocator  +  " Button not found");
			e.printStackTrace();
		}
		return present;
	}
	public static void clickByIdUsingJavaScriptExecutor(String locator, String LocatorName) {
		try {
			WebElement element = BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
			JavascriptExecutor executor = (JavascriptExecutor)BaseClass.driver;
			executor.executeScript("arguments[0].click();", element);
			BaseClass.logging("Clicked on element "+LocatorName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	public static void clickByXpathUsingJavaScriptExecutor(String locator, String LocatorName) {
		try {
			WebElement element = BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
			JavascriptExecutor executor = (JavascriptExecutor)BaseClass.driver;
			executor.executeScript("arguments[0].click();", element);
			BaseClass.logging("Clicked on element "+LocatorName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

	

	
	public static void clickByDynamicXpathUsingJavaScriptExecutor(String locator, String text) {
		String Locator=null;
		try {

			String[] TXT=BaseClass.resourceBundle.getProperty(locator).split("\\+");
			Locator=TXT[0]+text+TXT[1];
			WebElement element = BaseClass.driver.findElement(By.xpath(Locator));
			JavascriptExecutor executor = (JavascriptExecutor)BaseClass.driver;
			executor.executeScript("arguments[0].click();", element);
			BaseClass.logging("Clicked on element "+Locator);
		} catch (Exception e) {
			System.out.println(e.getMessage());}
		}


	public static  void FoldersWaitForLoadMaskToComplete(String locator, int timeOut) throws Exception {
		int i;
		for(i=0;i<=timeOut;i++){
			Thread.sleep(1000);
			WebElement Mask =BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
			Thread.sleep(500);
			String status=Mask.getAttribute("class");
			if(status.equalsIgnoreCase("ui-dialog-content ui-widget-content")){
				Thread.sleep(500);	
				break;
			}
		}
		
		}
	




public static boolean isConfirmationPresentByXpath(String locator,int i) 
		{
			try {
				BaseClass.driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				boolean flag= BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator))).isDisplayed();
				return flag;
			}
			catch (NoSuchElementException e)
			{
				BaseClass.logging("element not present "+i);
				return false;

			}
			catch (Exception e)
			{
				BaseClass.logging("element not present "+i);
				return false;

			}

		}}
