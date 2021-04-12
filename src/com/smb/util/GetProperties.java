package com.smb.util;

public class GetProperties {
	
	public static String getProperty(String key){
		String strVal ="";
		CommonUtil util = new CommonUtil();
		try{
			if(util != null){
				strVal = util.getProperty(key);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return strVal;
	}

}
