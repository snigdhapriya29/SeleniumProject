package com.amadeus.helpers;

import java.sql.*;
import java.util.ArrayList;
import org.testng.Assert;
import com.amadeus.utils.BaseClass;

 public class DBHelper {

		
		private static ResultSet rs= null;
		private static Connection conn = null;
		
		private String connString = BaseClass.getValueFromPropertyFile(BaseClass.config, "Server");
	    private String userName = BaseClass.getValueFromPropertyFile(BaseClass.config, "DBUserName");
	    private String password = BaseClass.getValueFromPropertyFile(BaseClass.config, "DBUserName");
	    
	    /**This constructor acquires the SQL DB connection.
		 * @exception Exception: If any exception is found	
		 */ 
	    public DBHelper(){
	    	
	    	try{
	    		Class.forName("oracle.jdbc.driver.OracleDriver");
				conn= DriverManager.getConnection(this.connString, this.userName, this.password);
				System.out.println("Connecting to the Database");
	    	}catch(SQLException strSQLException){
	        		Assert.fail("Database Connection not successful");
	    	}catch(Exception e){    		
	    		BaseClass.printStackTraceOnConsole(e);
	    		Assert.fail("Database Connection not successful");
	    	}
	    }
	    /**This constructor acquires the SQL DB connection with user defined configuration.
		 * @param strConnString- Connection URL
		 * @param strUserName- DB user name
		 * @param strPassword- DB password
		 * @exception Exception: If any exception is found	
		 */ 
	    public DBHelper(String strConnString,String strUserName, String strPassword){
	    	
	    	try{
	    		Class.forName("com.mysql.jdbc.Driver");		
				conn= DriverManager.getConnection(connString, userName, password);
				System.out.println("Connecting to the Database");
	            conn.setAutoCommit(true);
	    	}catch(SQLException strSQLException){
	        		Assert.fail("Database Connection not successful");
	    	}catch(Exception e){
	    		
	    		Assert.fail("Database Connection not successful");
	    		BaseClass.printStackTraceOnConsole(e);
	    	}
	    }
	    	    
	   
	   
	    
	    /**This method provides the SQL insert query execution
		 
		 * @param strSqlQuery: The SQL query which has to be executed
		 * @exception Exception: If any exception is found	
		 */
	    public void executeInsert(String sqlQuery){
	    	
	    	Statement stmt;
	    	try{
	    		stmt = conn.createStatement();
	    		stmt.executeUpdate(sqlQuery);
	    		
	    	}catch(SQLException strSQLException){
	    		strSQLException.printStackTrace();
				BaseClass.logging("Row not inserted");
				Assert.fail("Row not inserted");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
	    		Assert.fail("Row not inserted");
			}
	    }
	    
	    /**This method provides the SQL Update query execution
		 
		 * @param strSqlQuery: The SQL query which has to be executed
		 * @exception Exception: If any exception is found	
		 */
	    public void executeUpdate(String sqlQuery){
	    	
	    	Statement stmt;
	    	try{
	    		stmt = conn.createStatement();
				stmt.executeUpdate(sqlQuery);
	    		
	    	}catch(SQLException strSQLException){
	    		strSQLException.printStackTrace();
				BaseClass.logging("Row not updated");
				Assert.fail("Row not updated");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
	    		Assert.fail("Row not updated");
			}
	    }
	    
	    /**This method provides the SQL Delete query execution
		 
		 * @param strSqlQuery: The SQL query which has to be executed
		 * @exception Exception: If any exception is found	
		 */
	    public void executeDelete(String sqlQuery){
	    	
	    	Statement stmt;
	    	try{
	    		stmt = conn.createStatement();
				stmt.executeUpdate(sqlQuery);
	    		
	    	}catch(SQLException strSQLException){
	    		strSQLException.printStackTrace();
				BaseClass.logging("Row not Deleted");
				Assert.fail("Row not Deleted");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
	    		Assert.fail("Row not Deleted");
			}
	    }
	    
	    /**This method returns the result set for the parameterized SQL Query.
		 
		 * @param strSqlQuery: The SQL query which has to be executed.
		 * @param parameters: Array of user defined parameters
		 * @return: Returns the result-set
		 * @exception Exception: If any exception is found	
		 */
	    public ResultSet getValuesInResultSetParameterisedQuery(String sqlQuery, String...parameters){
	    	
	    	ResultSet rs = null;
	    	try{
	    		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
	    		for(int i=1; i<=parameters.length; i++){
	    			
	    			pstmt.setString(i, parameters[i-1]);
	    		}
	    			rs = pstmt.executeQuery();
	    			
	    			if(rs == null){
	    				
	    				System.out.println("Result Set is null hence closing the connection ");
	    				Assert.fail("Result Set is null hence closing the connection");
	    			}
	    		
	    	}catch(SQLException strSQLException){
	    		strSQLException.printStackTrace();
	    		Assert.fail("DB Error");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
				Assert.fail("DB Error");
			}
	    	
			return rs;
	    }
	    
	    /**This method returns the first row and first column for the parameterized SQL Query.
		 
		 * @param strSqlQuery: The SQL query which has to be executed
		 * @param parameters: Array of user defined parameters
		 * @return: Returns the first row and first column value in String
		 * @exception Exception: If any exception is found	
		 */
	    public String getQueryValueFromFirstRowFirstColParameterisedQuery(String sqlQuery, String...parameters){
	    	
	    	ResultSet rs;
	    	PreparedStatement pstmt;
	    	String stringvalue = null;
	    	try{
	    		pstmt = conn.prepareStatement(sqlQuery);
	    		for(int i=1; i<=parameters.length; i++){
	    			
	    			pstmt.setString(i, parameters[i-1]);
	    		}
	    			rs = pstmt.executeQuery();
	    			
	    			if(rs == null){
	    				
	    				System.out.println("Result Set is null hence closing the connection ");
	    				Assert.fail("Result Set is null hence closing the connection");
	    			}
	    			
	    			rs.next(); 
	    			stringvalue = rs.getString(1);
	    		
	    	}catch(SQLException strSQLException){
				
	    		strSQLException.printStackTrace();
	    		Assert.fail("DB Error");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
				Assert.fail("DB Error");
			}
			
			return stringvalue;
	    }
	    
	    /**This method provides the parameterized SQL insert query execution
		 
		 * @param strSqlQuery: The parameterized SQL query which has to be executed
		 * @param parameters: Array of user defined parameters
		 * @exception Exception: If any exception is found	
		 */
	    public void executeInsertParameterisedQuery(String sqlQuery, String...parameters){
	    	
	    	try{
	    		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
	    		for(int i=1; i<=parameters.length; i++){
	    			
	    			pstmt.setString(i, parameters[i-1]);
	    		}
	    		pstmt.executeUpdate();
	    	}catch(SQLException strSQLException){
				
				BaseClass.logging("Row not inserted");
	    		Assert.fail("Row not inserted");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
	    		Assert.fail("Row not inserted");
			}
	    }
	    
	    /**This method provides the parameterized SQL update query execution
		 
		 * @param strSqlQuery: The parameterized SQL query which has to be executed
		 * @param parameters: Array of user defined parameters
		 * @exception Exception: If any exception is found	
		 */
	    public void executeUpdateParameterisedQuery(String sqlQuery, String...parameters){
	    	
	    	try{
	    		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
	    		for(int i=1; i<=parameters.length;i++){
	    			
	    			pstmt.setString(i, parameters[i-1]);
	    		}
	    			pstmt.executeUpdate();
	    	}catch(SQLException strSQLException){
				
				BaseClass.logging("Row not updated");
	    		Assert.fail("Row not updated");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
	    		Assert.fail("Row not updated");
			}
	    }
	    
	    /**This method provides the parameterized SQL Delete query execution
		 
		 * @param strSqlQuery: The parameterized SQL query which has to be executed
		 * @param parameters: Array of user defined parameters
		 * @exception Exception: If any exception is found	
		 */
	    public void executeDeleteParameterisedQuery(String sqlQuery, String...parameters){
	    	
	    	try{
	    		
	    		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
	    		for(int i=1; i<=parameters.length;i++){
	    			
	    			pstmt.setString(i, parameters[i-1]);
	    		}
	    			pstmt.executeUpdate();    			
	    		
	    	}catch(SQLException strSQLException){
				
				BaseClass.logging("Row not deleted");
	    		Assert.fail("Row not deleted");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
	    		Assert.fail("Row not deleted");
			}
	    }
	    
	     /**This method provides the list of column values from result-set for the user input column names, this methods returns the value from the
	     * first row
		 
		 * @param strSqlQuery: The SQL Query from which the data has to be extracted
		 * @param colName: Array of col names of the table from which data has to be extracted
		 * @return: Returns the  column values in Arraylist
		 * @exception Exception: If any exception is found	
		 */
	    public ArrayList<String> getResultSetColsInArray(String sqlQuery, String...colName){
	    	ArrayList<String> list= new ArrayList<String>();
	    	PreparedStatement pstmt=null;
	    		try {
	    			pstmt = conn.prepareStatement(sqlQuery);
	    			rs = pstmt.executeQuery();
	    			if(rs == null){
	        			System.out.println("Result Set is null hence closing the connection ");
	    				Assert.fail("Sql Query did not fetch any row");
	        		}
	    			if(rs.getRow()>1){
	    				Assert.fail("Row Count is more than one hence failing the Test case");
	    			}
	    			rs.next();
	    			for(int i=0; i<colName.length;i++)
	    			list.add(rs.getString(colName[i]));
	    			rs.close();
	    			
	    		} catch (SQLException e) {
	    			BaseClass.printStackTraceOnConsole(e); 
	    			Assert.fail("DB Error");    			
	    		} catch(Exception e){    			
	    			BaseClass.printStackTraceOnConsole(e);
	    			Assert.fail("DB Error");
	    		} 
	    		
	    	
	    	return list;
	    }
	    
	    
	    //select Product from catalog
	    
	    
	    
	    /**This method closes the DB connection.
		 
		 * @exception Exception: If any exception is found	
		 */
	    public static void closeConnection(){
	    	
	    	try{
	    		conn.close();
	    		
	    	}catch(SQLException strSQLException){
	    		strSQLException.printStackTrace();
				BaseClass.logging("Cannot close connection");
			}catch(Exception e){
				
				//BaseClass.printStackTraceOnConsole(e);
			}
	    }
	    
	   
  public  static String getItemFromFirstRow(String storeid){
	    	
	  Statement stmt = null;
	  String query = "select catd.SHORTDESCRIPTION from CATENTRY cat,CATENTDESC catd where cat.catentry_id IN (select CATENTREL.CATENTRY_ID_PARENT from CATENTREL where CATENTREL.CATENTRY_ID_CHILD IN (select CATENTRY_ID from STORECENT where storeent_id ="+ storeid+ "and rownum <1000)) and cat.MARKFORDELETE = 0 AND cat.CATENTTYPE_ID = 'ProductBean' and cat.CATENTRY_ID = catd.CATENTRY_ID and catd.PUBLISHED=1 and catd.LANGUAGE_ID = -1";
	  String value =null;
	    	
	    	try{
	    		stmt = conn.createStatement();
	    		ResultSet rs = stmt.executeQuery(query);
	    		
	    		Thread.sleep(6);
	   
	    			if(rs == null){
	    				
	    				System.out.println("Result Set is null hence closing the connection ");
	    				Assert.fail("Result Set is null hence closing the connection");
	    			}
	    			
	    			rs.next(); 
	    			value = rs.getString(1).substring(0, 4);
	    			
	    			
	    			
	    	}catch(SQLException strSQLException){
				
	    		strSQLException.printStackTrace();
	    		Assert.fail("DB Error");
			}catch(Exception e){
				
				BaseClass.printStackTraceOnConsole(e);
				Assert.fail("DB Error");
			}
			
	    	return value;
	    }
 }
	 
 
