package com.amadeus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import com.amadeus.helpers.DBHelper;
import com.amadeus.helpers.Xls_Reader;



public class BaseClass 
{	
	public static Properties config = null;
	public static Properties resourceBundle = null;
	public static Properties PageObject = null;
	public static WebDriver wbDv = null;
	public static EventFiringWebDriver driver = null;
	public static boolean loggedIn = false;
	public static Xls_Reader datatable = null;
	public static DBHelper dataconnection =null;
	
	
	
	//public static Logger APP_Logger = Logger.getLogger("MyLogger");
	
	
	
	
	public static JavascriptExecutor js;

	
	
// override initiateDriver method to get only reource bundle where there is not need to read browser ype and luanch Browser
	
/*	public static void initiateDriver(String resourceBundle){
		initiateDriver(resourceBundle, "appconfig.properties");
	}*/
	
	
	
	/**This method is used to initiate the Driver - Configuration file, Object Repository, Browser type
	 * 
	 * @param resourceBundle The Name of the Object Repository with the (.properties) extension
	 * @exception Exception: If any exception is found	
	 */
	public static void initiateDriver(String strORFileName, String loadConfigFile){
		try{	
			
			// loading all the configuration values
			config = loadConfigFile(loadConfigFile);
			
			
			// loading the Object Repository file
			resourceBundle = loadObjectRepositoryFile(strORFileName);
			

			//Connecting To Database
			Assert.assertTrue(connectToDatabase());
			
			
			//Assert.assertTrue(loadController(), "The Controller Excel Sheet is not loaded successfully");
			
			if(!config.equals(null) && !resourceBundle.equals(null)){
				
				// checking the type of browser
				if(config.getProperty("browserType").equalsIgnoreCase("Chrome")){
					
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\bnlict3076\\workspace\\kramp-demo\\src\\main\\resources\\chromedriver.exe");
					 ChromeOptions options = new ChromeOptions();
				        options.addArguments("--test-type");
				  
					wbDv = new ChromeDriver(options);
					
				}else if(config.getProperty("browserType").equalsIgnoreCase("Firefox")){
					
					wbDv = new FirefoxDriver();
					
				}else if(config.getProperty("browserType").equalsIgnoreCase("IE")){
					
					System.setProperty("webdriver.ie.driver", "C:\\Users\\bnlict3076\\workspace\\kramp-demo\\src\\main\\resources\\IEDriverServer.exe");
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					//caps.setCapability("ignoreZoomSetting", true);
					caps.setCapability("InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);
					caps.setCapability("elementScrollBehavior",1);
					wbDv = new InternetExplorerDriver(caps);
					
				}else if(config.getProperty("browserType").equalsIgnoreCase("Firefox_Profile")){
					
					File profiler = new File(System.getProperty("user.dir")+"\\src\\com\\late\\rooms\\firefoxProfile\\Test.FirefoxProfile");
					FirefoxProfile profile = new FirefoxProfile(profiler);
					wbDv = new FirefoxDriver(profile);
				}
				
				driver = new EventFiringWebDriver(wbDv);
				
				// putting an implicit wait after every Action or Event
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				// opening the browser and the specific store URL
				String baseUrl = config.getProperty("applicationURL");
				String language = config.getProperty("language");
				String languageToLoad = language.replace('_', '/');
				
				String storeid = config.getProperty("storeId");
				
				
				driver.navigate().to(baseUrl+storeid+"-"+languageToLoad);
				
				driver.manage().window().maximize();
				Thread.sleep(3000);
			}
			else{
				
				//App_Logger.debug("Either config file or OR is returning null");
				
				Reporter.log("Either config file or OR is returning null");
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		//	APP_Logger.debug(" The initiateDriver function is failing ");
			Reporter.log("The initiateDriver function is failing");
			Assert.fail("The initiateDriver function is failing");
		}
	}
	
	
	/**This method is used to load the Object Repository file.
	 * 
	 * 
	 * @param strORFileName The Name of the Object Repository with the (.properties) extension
	 * @return Properties it will return the instance of property file after loading
	 * @exception IOException: If there is any Input/Output Exception is found
	 */
	
	
	 
	 public enum TestCondition
	  {
	  WBtn,WChbox,WEdit,Wlink,CBX;
	  }
	 

	public static Properties loadObjectRepositoryFile(String strORFileName){
		
		try{
			resourceBundle = new Properties();
			String language = config.getProperty("language");
			String storeid = config.getProperty("storeId");
			//String languageToLoad = language.replace('_', '/');
			
			if(isFilePresent(System.getProperty("user.dir")+"\\src\\test\\resources\\ResourceBundles\\"+strORFileName+"_"+storeid+"_"+language+".properties"))
			{
				
				FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\ResourceBundles\\"+strORFileName+"_"+storeid+"_"+language+".properties");
				resourceBundle.load(fp);
				
				
		//	APP_Logger.debug("The Object Repository file" + strORFileName + "is successfully loaded");
				Reporter.log("The Object Repository file "+ strORFileName +" is successfully loaded");
				
			}else{
				
			//	APP_Logger.debug("The Object Repository file" + strORFileName + " loading is failed");
				Reporter.log("The Object Repository file "+ strORFileName +" loading is failed");
			}
			
		}catch(IOException strInputOutputException){
			
		//	APP_Logger.debug("IOException occured in loading the Configuration file");
			Reporter.log("IOException occured in loading the Configuration file");
			Assert.fail("IOException occured in loading the Configuration file");
			
		}catch(Exception e){
			
			e.printStackTrace();
		//	APP_Logger.debug("Some Exception occured in loading the Configuration file");
			Reporter.log("Some Exception occured in loading the Configuration file");
			Assert.fail("Some Exception occured in loading the Configuration file");
		}
		
		return resourceBundle;
	}
	
	
	/**This method is used to load the Configuration file.
	 * 
	 * @ @param The name of the configuration file
	 * @return Properties it will return the instance of property file after loading
	 * @exception IOException: If there is any Input/Output Exception is found
	 */
	
	
	
	public static Properties loadConfigFile(String loadController){
		
		try{
			config = new Properties();
			if(isFilePresent(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\"+loadController)){
				
				FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\"+loadController);
				config.load(fp);
		//		
			//	APP_Logger.debug("The configuration file is successfully loaded");
				Reporter.log("The configuration file is successfully loaded");
				
			}else{
				
				//APP_Logger.debug("The configuration file loading is failed");
				Reporter.log("The configuration file loading is failed");
				
			}
			
		}catch(IOException strInputOutputException){
			
			//APP_Logger.debug("IOException occured in loading the Configuration file");
			Reporter.log("IOException occured in loading the Configuration file");
			Assert.fail("IOException occured in loading the Configuration file");
			
		}catch(Exception e){
			
			e.printStackTrace();
			//APP_Logger.debug("Some Exception occured in loading the Configuration file");
			Reporter.log("Some Exception occured in loading the Configuration file");
			Assert.fail("Some Exception occured in loading the Configuration file");
		}
		
		return config;
	}
	
	public static Properties loadResourceBundleConfigFile(String resourceConfig, String language){
		
		try{
			config = new Properties();
			if(isFilePresent(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\ResourceBundles\\"+resourceConfig+"+"+language+".properties")){
				
				FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\ResourceBundles\\"+resourceConfig+"+"+language);
				config.load(fp);
		//		
			//	APP_Logger.debug("The configuration file is successfully loaded");
				Reporter.log("The configuration file is successfully loaded");
				
			}else{
				
				//APP_Logger.debug("The configuration file loading is failed");
				Reporter.log("The configuration file loading is failed");
				
			}
			
		}catch(IOException strInputOutputException){
			
			//APP_Logger.debug("IOException occured in loading the Configuration file");
			Reporter.log("IOException occured in loading the Configuration file");
			Assert.fail("IOException occured in loading the Configuration file");
			
		}catch(Exception e){
			
			e.printStackTrace();
			//APP_Logger.debug("Some Exception occured in loading the Configuration file");
			Reporter.log("Some Exception occured in loading the Configuration file");
			Assert.fail("Some Exception occured in loading the Configuration file");
		}
		
		return config;
		}
	
	
	/**This method is used to load the Controller.xls file.
	 * 
	 * @param loadController The name of the controller file
	 * @return True/False (True if the Controller.xlsx is loaded successfully, false otherwise)
	 * @exception Exception: If there is any Exception is found
	 */
	public static boolean loadController(String loadController){
		
		try{
			String strControllerPath = System.getProperty("user.dir")+"\\src\\com\\prakat\\config\\"+ loadController; 
			if(isFilePresent(strControllerPath)){
				
				datatable = new Xls_Reader(strControllerPath);
				//APP_Logger.debug("The Controller sheet is successfully loaded");
				Reporter.log("The Controller sheet is successfully loaded");
				return true;
				
			}else{
				
				//APP_Logger.debug("The Controller sheet loading is failed");
				Reporter.log("The Controller sheet loading is failed");
				return false;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			//APP_Logger.debug("Some Exception occured in loading the Configuration file");
			Reporter.log("Some Exception occured in loading the Configuration file");
			Assert.fail("Some Exception occured in loading the Configuration file");
			return false;
		}
	}
	
	
	/**This method is used to Read data from the OR Property file.
	 * 
	 * 
	 * @param prop The Name of the Property file
	 * @param key The key for which value needs to be fetched
	 * @return String it will return the value against the Key
	 * @exception IllegalArgumentException/NullPointerException/Exception: If there is any exception occurs
	 */
	public static String getValueFromPropertyFile(Properties prop, String key){
		
		try{
			
			return prop.getProperty(key);
			
		}catch(IllegalArgumentException strIllegalArgsException){
			
			//APP_Logger.debug(key + "The argument is illegal");
			Reporter.log(key + " The argument is not legal");
			Assert.fail(key + " Illegal Argument Exception");
			return null;
			
		}catch(NullPointerException strNullPointerException){
			
			//APP_Logger.debug(key + " is null or not present in the Object Repository");
			Reporter.log(key + " is null or not present in the Object Repository");
			Assert.fail(key + " Null pointer Exception");
			return null;
		}catch(Exception e){
			
			e.printStackTrace();
			Assert.fail(key + " is throwing Exception");
			return null;
		}
		
	}
	

	
	/**This method is used to check whether the File is present on the path passed as parameter.
	 * 
	 * 
	 * @param key The key for which value needs to be fetched
	 * @param strObjLabel The Object identifier or label displayed in the application
	 * @return True/False (True if the file is present on the path, false otherwise)
	 * @exception Exception: If there is any exception occurs
	 */
	public static boolean isFilePresent(String strFilePath){
		
		try{
			if((strFilePath).trim() == ""){
				
			//	APP_Logger.debug("The passed file path paramenter is blank");
				Reporter.log("The passed file path paramenter is blank");
				return false;
				
			}else{
				
				File file=new File(strFilePath);
				  boolean exists = file.exists();
				  
				  if(exists) {
					  
					  return true;
					  
				  }else{
					  
					  return false;
				  }
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	public static void writeLog(String errorMessage) {
		
		if(config.getProperty("testNgReporter_Status").equalsIgnoreCase("ON")){
			Reporter.log(errorMessage);			
		}
		
	}
	
	public static void applicationLog(String errorMessage) {
		if(config.getProperty("testNgReporter_Status").equalsIgnoreCase("ON")){
			//APP_Logger.debug(errorMessage);
		}	
	}
	
	public static void logging(String errorMessage) {
		  writeLog(errorMessage);
		  applicationLog(errorMessage);
		  }
	
	public static void printStackTraceOnConsole(Exception strExceptionStackTrace){
		  
		  String strStackTrace = getValueFromPropertyFile(config, "CONFIG_PRINT_STACK_TRACE");
		  if(strStackTrace.trim().equalsIgnoreCase("ON")){
		   
		   strExceptionStackTrace.printStackTrace();
		  }
		 }	
	
public static boolean connectToDatabase(){
		
		try{
			
			dataconnection = new DBHelper();	
				
				Reporter.log("The Database  is Connected successfully");
				return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			Reporter.log("Some exception ocured while conneting to Database");
			Assert.fail("Some exception ocured while conneting to Database");
			return false;
		}
	}
		
	
}
