package com.amadeus.helpers;


import java.util.concurrent.TimeUnit;
import com.amadeus.utils.BaseClass;
import com.google.common.base.Function;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


/* 
 * Project    : Amadeus 
 * Script     : Text Box Helper
 * Author     : Meera
 * Date       : July.28.2016
 */

public class TextBoxHelper extends BaseClass {	
	/** This method is used for asserting enable text box.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */
	public static String assertEnableTextBox(String locator){
		try {
			WebElement textBox = BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
			if(textBox.isEnabled()){
				
			}
		}catch (NoSuchElementException e){
			e.printStackTrace();
		} catch (Exception e) {
		return "Fail- Textbox is not enabled";
		}
		return "Pass";
	}
	
	/** This method is used for asserting disable text.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */
	
	public static String verifyTextbyid(String actual, String expected) {
		  BaseClass.logging("Executing verify text");

		  try {
		   String Actual = BaseClass.wbDv.findElement(By.id(BaseClass.resourceBundle.getProperty(actual))).getText();
		   String Expected = expected;
		   if(Actual.equals(Expected)) {
		    BaseClass.logging("Actual text is same as Expected text");
		    BaseClass.logging("Actual text is " + Actual);
		    BaseClass.logging("Expected text is " + Expected);
		    return "Pass";
		   } else {  
		    BaseClass.logging("Actual text is not same as Expected text");
		    BaseClass.logging("Actual text is" + Actual);
		    BaseClass.logging("Expected text is" +  Expected);
		    return "Fail - Actual text is not same as Expected text";
		    }
		   }
		  catch(Exception e) {
		    e.printStackTrace();    
		    //BaseClass.logging("Actual text is not same as Expected text");
		    return "Fail - text not matched ";
		   }  
		 }
	
	public static String TextBoxPresent(String locator,String objLocator,String ErrorMsg) {
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
	
	public static String verifyTextNotPresentbyid(String actual, String expected) {
		  BaseClass.logging("Executing verify text");

		  try {
		   String Actual = BaseClass.wbDv.findElement(By.id(BaseClass.resourceBundle.getProperty(actual))).getText();
		   String Expected = expected;
		   if(Actual!=Expected) {
		    BaseClass.logging("Actual text is not same as Expected text");
		    BaseClass.logging("Actual text is "+ Actual);
		    BaseClass.logging("Expected text is "+ Expected);
		    return "Pass";
		   } else {
		    BaseClass.logging("Actual text is same as Expected text");
		    BaseClass.logging("Actual text is "+ Actual);
		    BaseClass.logging("Expected text is "+ Expected);
		    return "Fail - Actual text is same as Expected text";
		    }
		   }
		  catch(Exception e) {
		    e.printStackTrace();    
		    BaseClass.logging("Actual text is not same as Expected text");
		    return "Fail - text not matched ";
		    
		   }  
		 }
	public static String getValueForTextBoxbyXPATH(String elementID){
		String returnValue="";
		try{
			returnValue = BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(elementID))).getAttribute("value");
			BaseClass.logging("Fetched text by value :"+returnValue);

		}catch (NoSuchElementException e){
			BaseClass.logging("getValueForTextBoxbyID Exception:"+e.getMessage());
			//Assert.fail("Link Text not found");
		} catch (Exception e) {
			BaseClass.logging("getValueForTextBoxbyID Exception:"+e.getMessage());
		}
		return  returnValue ;
	}
	public static String verifyTextbyXpath(String actual, String expected) {
		  BaseClass.logging("Executing verify text");

		  try {
		   String Actual = BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(actual))).getText();
		   String Expected = expected;
		   if(Actual.equals(Expected)) {
		    BaseClass.logging("Actual text is same as Expected text");
		    BaseClass.logging("Actual text is " + Actual);
		    BaseClass.logging("Expected text is " + Expected);
		    return "Pass";
		   } else {  
		    BaseClass.logging("Actual text is not same as Expected text");
		    BaseClass.logging("Actual text is" + Actual);
		    BaseClass.logging("Expected text is" +  Expected);
		    return "Fail - Actual text is not same as Expected text";
		    }
		   }
		  catch(Exception e) {
		    e.printStackTrace();    
		    //BaseClass.logging("Actual text is not same as Expected text");
		    return "Fail - text not matched ";
		   }  
		 }
	public static String assertDisableTextBox(String locator){
		try {
			WebElement textBox = BaseClass.wbDv.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator)));
			if (textBox.isDisplayed()){
			}
		}catch (NoSuchElementException e){
			e.printStackTrace();
		} catch (Exception e) {
			return "Fail - Textbox is not disabled";
		}
		return "Pass";	
	}    
	
	/** This method is used for typing text.
	 * 
	 * @Param : locator
	 * @Param : locator1
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 */
	public static String typeText(String locator, String locator1) {
	//	APP_Logger.debug("Executing typetext method");
		try {
			//ButtonHelper.LoadingMaskAndServerMessageValidation();
			BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator))).clear();
			BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator))).sendKeys(locator1);
			BaseClass.logging("entered text into textbox "+locator);
		} catch(NoSuchElementException e){
		//	APP_Logger.debug("Executing typeText method is failed ");
			BaseClass.logging("Text box not found "+locator);
			e.printStackTrace();
		} catch(Exception e) {
			Assert.fail("Textbox not found "+locator);
			return "Fail - textbox not found";
		}
		return "Pass";
		
	}
	
	
	
		
		
	/** This method is used for verifying text.
	 * 
	 * @Param : locator
	 * @exception Exception: If any exception is found
	 * @exception NoSuchElementException : If element is not found
	 *//*
	public boolean verifyTextPresent(WebElement rootElement, String locator,String text) {
		List elements = rootElement.findElements(By.xpath(BaseClass.OR.getProperty(locator))); 
		boolean match = false;
		for (WebElement elem : elements){
			String elementText = elem.getText();
			if (elementText.contains(text)){
				match = true;
				break;
			}
		} try {
			Assert.assertTrue(match); 
		} catch (Exception e) {
			
		}
		return match;
		}*/
	
	 /** This method is used for typing text in text box.
		  * 
		  * @Param : locator
		  * @Param : objlocator
		  * @param : locator1
		  * @return 
		  * @exception Exception: If any exception is found
		  * @exception NoSuchElementException : If element is not found
		  */
		 public static boolean type(String txtboxLocator, String locator1,String objlocator) {
		  
		  String path_locator= BaseClass.resourceBundle.getProperty(txtboxLocator);
		  String locator= path_locator.split(",")[1];
		   String identifier=path_locator.split(",")[0];
		  
		  BaseClass.logging("Entering data in " + objlocator + " field" );
		  
		  try {
		   if(identifier.equalsIgnoreCase("id")){
		    BaseClass.driver.findElement(By.id((locator))).sendKeys(locator1);
		    return true;
		    } 
		   else if(identifier.equalsIgnoreCase("xpath")) {
		    BaseClass.driver.findElement(By.xpath((locator))).sendKeys(locator1);
		    return true;
		    }
		   else{  
		    
		    BaseClass.logging("OR is returning null");
		    return true;
		   }
		   }
		  
		  catch(NoSuchElementException e){
		   BaseClass.logging(objlocator+"Text couldnot be typed");
		   e.printStackTrace();
		   Assert.fail(objlocator+"Text couldnot be typed");
		   return false;
		   } 
		  
		  catch(Exception e) {
		   Assert.fail(objlocator+"Textbox not found ");
		   return false;
		  }
		  
		 }
		 
		 public static String TextBoxClearByID(String locator) {
				BaseClass.logging("Executing assert disable button by xpath");
				try {
					WebElement textbox = BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
					textbox.clear();
					} 
				catch (NoSuchElementException e) {
					BaseClass.logging("The textbox is Enabled ");
					e.printStackTrace();
					} 
				catch (Exception e) {
					return "Fail - The textbox is Enabled ";
					}
				return "Pass - Button is Disabled ";
				}
		 public static WebElement fluentWait(final By locator){
		        Wait<WebDriver> wait = new FluentWait<WebDriver>(BaseClass.driver)
		                .withTimeout(30, TimeUnit.SECONDS)
		                .pollingEvery(5, TimeUnit.SECONDS)
		                .ignoring(NoSuchElementException.class);

		        WebElement foo = wait.until(
		new Function<WebDriver, WebElement>() {
		            public WebElement apply(WebDriver driver) {
		                        return driver.findElement(locator);
		                }
		                }
		);
		                           return  foo;              }		

			/** This method is used for fetching the text present in the text field by fetching value attribute
			 * @author Vijayendra
			 * @return String
			 * 
			 **/


			public static String getValueForTextBoxbyID(String elementID){
				String returnValue="";
				try{
					returnValue = BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(elementID))).getAttribute("value");
					BaseClass.logging("Fetched text by value :"+returnValue);

				}catch (NoSuchElementException e){
					BaseClass.logging("getValueForTextBoxbyID Exception:"+e.getMessage());
					//Assert.fail("Link Text not found");
				} catch (Exception e) {
					BaseClass.logging("getValueForTextBoxbyID Exception:"+e.getMessage());
				}
				return  returnValue ;
			}
		 
		 public static WebElement findElementWithTimeoutWait(By by, long timeOut) throws InterruptedException { 
             WebElement e = null; 
             long elapsedTime = 0; 
             while(elapsedTime < timeOut) { 
                 try { 
                     elapsedTime++; 
                     Thread.sleep(1000); 
                     e = BaseClass.driver.findElement(by); 
                     break; 
                 } catch (NoSuchElementException nse) { 
                 } 
             } 
             return e; 
         }
	
		 public static String typeTextbyXpath(String locator, String locator1) {
					try {
						BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator))).clear();
						BaseClass.driver.findElement(By.xpath(BaseClass.resourceBundle.getProperty(locator))).sendKeys(locator1);
					} catch(NoSuchElementException e){
						e.printStackTrace();
					} catch(Exception e) {
						Assert.fail(" Textbox not found ");
						return "Fail - textbox not found";
					}
					return "Pass";		
				}
		 public static boolean isTextboxPresentById(String locator, String objLocator) {
			   try {
				   BaseClass.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			    boolean flag= BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator))).isDisplayed();
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
		 public static boolean isTextboxPresentByxpath(String locator, String objLocator) {
			   try {
				   BaseClass.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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

	
		
	
	
