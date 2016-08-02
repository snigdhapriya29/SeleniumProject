package com.amadeus.helpers;

import java.io.*;
public class LogResult
{
	public static BufferedWriter logb;
	public static FileWriter fw;
	public static int PASSCNT;
	public static int FAILCNT;
	public static int WARNCNT;
	//static String  
public void CreateLog(String logFileName) throws IOException
{
	fw = new FileWriter(logFileName);
	logb = new BufferedWriter(fw);	
	
}

 public void WriteLog(String data)
 {
	try
	{
		
		logb.write(data);
		logb.newLine();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
 }
 public void CloseLogFile() throws IOException
 {
	 System.out.println("CloseLogFile--------------------" );
	
	 WriteLog("------------------------------------------------");
	 WriteLog("Pass Count =" + PASSCNT + "  Fail Count =" + FAILCNT);
	 if (FAILCNT >0)
	 {
		 WriteLog(" ");
		 WriteLog("TestCase Status= Fail");
	 }
	 else
	 {
		 WriteLog(" ");
		 WriteLog("TestCase Status= Pass");
	 }
	 WriteLog("------------------------------------------------");
	 logb.close();

 }
}

