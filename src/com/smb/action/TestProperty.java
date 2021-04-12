package com.smb.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.smb.util.CommonUtil;

public class TestProperty {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Properties prop = new Properties();
		InputStream is = null;
		try{
			//is = new FileInputStream("myResources.properties");
			is = getClass().getClassLoader().getResourceAsStream("myResources.properties");
			prop.load(is);
			System.out.println(prop.getProperty("firstname"));
			
		}catch (Exception e) {
			System.out.println(e);
		}*/
		
		TestProperty tp = new TestProperty();
		try{
			tp.getProVal();
		}catch (Exception e) {
			System.out.println(""+e);
		}
		
	}
   public String getProVal() throws IOException{
	   String result ="";
	   Properties prop = new Properties();
	   String proName = "myResources.properties";
	   InputStream is = getClass().getClassLoader().getResourceAsStream(proName);
	   if(is != null){
		   System.out.println("hhhf");
		   prop.load(is);
		   System.out.println(prop.getProperty("firstname"));
	   }else{
		   throw new FileNotFoundException("File not found exception");
	   }
	   return "";
   }
}
