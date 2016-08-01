package com.amadeus.helpers;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.amadeus.utils.BaseClass;

@SuppressWarnings("serial")
class Elementpresence extends Exception
  {
	Elementpresence(String s)
	{super(s);}
  }
class Elementenable extends Exception
{
		Elementenable(String s)
		{super(s);}
}

public class Validation 
{
	static WebElement locator = null;
  public static void  elementPresent(By by,String Locator,String ErrorMessage)   
	{   
	  try
	     {   
		  if(!(BaseClass.resourceBundle.containsKey(Locator))||(BaseClass.resourceBundle.getProperty(Locator)).isEmpty())
			     throw new noLocatorexceptions(Locator);
		   else{
			      locator = BaseClass.driver.findElement(by); 
		 		  locator.isDisplayed();
		        }
		  }	  
	catch(NoSuchElementException e) {
		BaseClass.logging(ErrorMessage);
		screenShot(ErrorMessage);
		Assert.fail(ErrorMessage);
		} 
	catch(noLocatorexceptions e) {
			BaseClass.logging(ErrorMessage+ "ID/ Xpath Locator not found ");
			screenShot(Locator);
			Assert.fail("Xpath Locator not found");
		} 
	catch (Exception e) {
		e.printStackTrace();
		BaseClass.logging(ErrorMessage);
		Assert.fail(Locator);
			}
}
			
 public static void  elementNotPresent(By by,String Locator,String ErrorMessage)   
		{  
	       Boolean present=false;
		  try
		   {  
			  BaseClass.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			 if(!(BaseClass.resourceBundle.containsKey(Locator))||(BaseClass.resourceBundle.getProperty(Locator).isEmpty()))
			 throw new noLocatorexceptions(Locator);
			 else
			 {
				 locator = BaseClass.driver.findElement(by); 
		 		 locator.isDisplayed();
		       if(present)	
				throw new  Elementpresence(Locator);
			   }
		    }
		    catch(noLocatorexceptions e){BaseClass.logging(ErrorMessage);Assert.fail("nmkli0op");}
			catch(Elementpresence e1){screenShot(Locator);Assert.fail(ErrorMessage);}
		    catch(NoSuchElementException e) {;}
			catch (Exception e) {;}
			}

 public  static void screenShot(String name)
{   
  String name1=System.getProperty("user.dir")+"/test-output/"+ name +".png";
 try {
       File source = ((TakesScreenshot)BaseClass.driver).getScreenshotAs(OutputType.FILE);
       File destFile=new File(name1);
       FileUtils.copyFile(source,  destFile);
       String image ="file:///"+name1;
       Reporter.log("<a href='" + image  + "'>ScreenshotLink</a>"); 
     }
    catch(IOException e) {
       BaseClass.logging( "Failed to capture screenshot: " + e.getMessage());
    }
    
}
public static boolean  compareTwoStrings(String Actual,String Expected,String ErrorMessage)
{  
	
boolean result =false;
try
 {
    if(Actual.equalsIgnoreCase(Expected))
	result=true;
    else
	result=false;
    //BaseClass.logging(ErrorMessage);
    if(!result) throw new Elementpresence("");
  
    }catch(Elementpresence e){
    	BaseClass.logging(ErrorMessage);
    	Validation.screenShot(Expected);
    	Assert.fail(ErrorMessage);
    }catch(Exception e){;}
return result;
} 
public static boolean  compareTwoStringsFalse(String Actual,String Expected,String ErrorMessage)
{  
	
boolean result =false;
try
 {
    if(Actual.equalsIgnoreCase(Expected))
	result=true;
    else
	result=false;
    if(result) throw new Elementpresence("");
  
    }catch(Elementpresence e){
    	BaseClass.logging(ErrorMessage);
    	Validation.screenShot(Expected);
    	Assert.fail(ErrorMessage);
    }catch(Exception e){;}
return result;
} 

public static boolean  compareTwoValues(Integer Actual,Integer Expected,String ErrorMessage)
{  
	boolean result =false;
	try
	 {
	   if(Actual==Expected)
		result=true;
	   else
		result=false;
	  if(!result) throw new Elementpresence("");
	}
	catch(Elementpresence e){
		BaseClass.logging(ErrorMessage);
		Assert.fail(ErrorMessage);}
	
	catch(Exception e){;}
	return result;
	}

public static boolean checkBoolean(Boolean actual,Boolean expected,String ErrorMessage)
{  
boolean result=false;
try
  {
   if(actual==expected)
	result=true;
   else
	result=false;
  if(!result) throw new Elementpresence("");
  }
catch(Elementpresence e){
	BaseClass.logging(ErrorMessage);
	Assert.fail(ErrorMessage);
}
catch(Exception e){;}

return result;
}

public void checkBooleanValue(int actual)
{  
boolean result=false;
if(actual==0)
	result=true;
else if(actual>0)
		result=false;
}


public static  void WaitForLoadMaskToComplete(String locator, int timeOut) throws Exception 
{
	try
	{
      int i;
      for(i=0;i<=20;i++){
      Thread.sleep(1000);
      WebElement Mask =BaseClass.driver.findElement(By.id(BaseClass.resourceBundle.getProperty(locator)));
      String status=Mask.getAttribute("class");
      if(status.equalsIgnoreCase("")){
      Thread.sleep(500); 
      break;
         }
       }              
	 }catch(NoSuchElementException e) {
		BaseClass.logging(locator+"");
		screenShot("Screenshotlink");
		Assert.fail(locator+"");
		} 
     catch(Exception e) {
		BaseClass.logging(locator+e.getMessage());
		screenShot("Screenshotlink");
		Assert.fail(e.getMessage());
		}
}





public static void  validateButtonDisable(By by,String Locator,String errorMessage) throws Exception
{     
	Boolean value=false;
	 try{
		 if(!(BaseClass.resourceBundle.containsKey(Locator))||(BaseClass.resourceBundle.getProperty(Locator)).isEmpty())
	   	throw new noLocatorexceptions(Locator);
	 else
	    {  locator=BaseClass.driver.findElement(by);
	       value=locator.isEnabled();
	       if(value)
	       throw new Elementenable("");}
	    }
	        catch(Elementenable e) {
			BaseClass.logging(errorMessage);
			screenShot(errorMessage);
			Assert.fail(errorMessage);
	     }
	       catch (noLocatorexceptions e) 
		    {
		  	BaseClass.logging(errorMessage+ " ID/Xpath not found ");
		  	screenShot(errorMessage);
			Assert.fail("ID/Xpath not found");
			}
	       catch(NoSuchElementException e) {
			BaseClass.logging(errorMessage);
			screenShot(errorMessage);
			Assert.fail(errorMessage);
			} 
	     catch (Exception e) {
			e.printStackTrace();
			BaseClass.logging(errorMessage);
				}
}
	 public static void  validateButtonEnable(By by,String Locator,String errorMessage) throws Exception
	 {     
	 	Boolean value=false;
	 	 try{
	 		 if(!(BaseClass.resourceBundle.containsKey(Locator))||(BaseClass.resourceBundle.getProperty(Locator)).isEmpty())
	 	   	throw new noLocatorexceptions(Locator);
	 	 else
	 	    {  locator=BaseClass.driver.findElement(by);
	 	       value=locator.isEnabled();
	 	       if(!value)
	 	       throw new Elementenable("");}
	 	    }
	 	        catch(Elementenable e) {
	 			BaseClass.logging(errorMessage);
	 			screenShot(errorMessage);
	 			Assert.fail(errorMessage);
	 	     }
	 	       catch (noLocatorexceptions e) 
	 		    {
	 		  	BaseClass.logging(errorMessage+ " ID/Xpath not found ");
	 		  	screenShot(errorMessage);
	 			Assert.fail("ID/Xpath not found");
	 			}
	 	       catch(NoSuchElementException e) {
	 			BaseClass.logging(errorMessage);
	 			screenShot(errorMessage);
	 			Assert.fail(errorMessage);
	 			} 
	 	     catch (Exception e) {
	 			e.printStackTrace();
	 			BaseClass.logging(errorMessage);
	 				}
	 }
      public boolean CheckBooleanNotSame(Boolean actual,Boolean expected,String ErrorMessage)
	 	{  
	 	boolean result=false;
	 	try
	 	  {
	 	   if(actual!=expected)
	 		result=false;
	 	   else
	 		result=true;
	 	   if(!result) throw new Elementpresence("");
	 	   }
	 	catch(Elementpresence e){
	 		BaseClass.logging(ErrorMessage);
	 		Assert.fail(ErrorMessage);}
	 	
	 	catch(Exception e){;}
	 	
	 	return result;
	 	}


	 }

	   