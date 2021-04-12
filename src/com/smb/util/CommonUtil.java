package com.smb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.smb.bizlogic.CheetyCreationBizlogic;
import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;

public class CommonUtil {
	public static final String FILE_PATH="D:\\Excel\\";
	public static int getSequence(String seqName){
		int seqId=0;
		PreparedStatement getSeqNoStm=null;
		ResultSet rs = null ;
		Connection con = null ;
		try{
			con = DBConnection.getDBConnection();
			getSeqNoStm = con.prepareStatement(CommonConstents.SELECT_PRODUCT_SEQ_ID);
			getSeqNoStm.setString(1, seqName);
			rs = getSeqNoStm.executeQuery();
			if(rs.isBeforeFirst()){
				while(rs.next()){
					seqId = rs.getInt("SEQ_ID");
				}
			}
		}catch (Exception e) {
			System.out.println("Exception ::"+e);
		}
		return seqId;
	}
	public static String updateSequence(int seqId , String seqName,Connection con){
		int seqResult;
		PreparedStatement updateSeqNoStm=null;
		String resultType="";
		try{
			seqId = seqId+1;
			updateSeqNoStm = con.prepareStatement(CommonConstents.PRODUCT_SEQ_UPDATE_QUERY);
			System.out.println(CommonConstents.PRODUCT_SEQ_UPDATE_QUERY);
			updateSeqNoStm.setInt(1, seqId);
			updateSeqNoStm.setString(2, seqName);
			seqResult = updateSeqNoStm.executeUpdate();
			resultType = "updateSeq";
		}catch (Exception e) {
			System.out.println("Exception ::"+e);
			resultType = "NotUpdateSeq";
		}
		return resultType;
	}
	public static String getFileName(String inputType ,String folderName){
		String FILE_NAME ="",fileName=""; 
		fileName =folderName.replace(" ", "_");
		if(inputType.equals("ProfitDetails")){
			FILE_NAME = fileName+"_DateWise_"+inputType+"_"+getDateString()+".xls";
		}else{
			FILE_NAME = fileName+"_OrderBy_"+inputType+"_"+getDateString()+".xls";
		}
		
		return FILE_NAME;
	}
	public String getProperty(String key)throws IOException{
			   String result ="";
			   Properties prop = new Properties();
			   String proName = "ApplicationResources.properties";
			   InputStream is = getClass().getClassLoader().getResourceAsStream(proName);
			   if(is != null){
				   prop.load(is);
				   result= prop.getProperty(key);
			   }else{
				   throw new FileNotFoundException("File not found exception");
			   }
			  
		return result;
	}
	public static String getFullFileName(String inputType ,String folderName){
		String FILE_NAME ="",fileName="";
		fileName =folderName.replace(" ", "_");
		if(inputType.equals("ProfitDetails")){
			FILE_NAME = getFileLocation(inputType,folderName)+"\\"+fileName+"_DateWise_"+getDateString()+".xls";
		}else{
			FILE_NAME = getFileLocation(inputType,folderName)+"\\"+fileName+"_OrderBy_"+inputType+"_"+getDateString()+".xls";
		}
		return FILE_NAME;
	}
	public static String getFullFileName(String inputVal){
		String FILE_NAME ="";
		FILE_NAME = "OrderBy_"+inputVal+"_"+getDateString()+".xls";
		System.out.println("FILE_NAME -->"+FILE_NAME);
		return FILE_NAME;
	}
	public static String getFileCheety(){
		String FILE_NAME ="";
		String folderName="CHEETY";
		FILE_NAME = getCheetyFileLocation(folderName)+"\\Cheety_"+getDateString()+".xls";
		return FILE_NAME;
	}
	public static String getFileLocation(String inputType ,String folderName){
		String FILE_LOCATION ="";
		FILE_LOCATION = FILE_PATH+folderName;
		return FILE_LOCATION;
	}
	public static String getCheetyFileLocation(String folderName){
		String FILE_LOCATION ="";
		FILE_LOCATION = FILE_PATH+folderName;
		return FILE_LOCATION;
	}
	public static String getDateString(){
		String dateString ="";
		Format formatter=null;
		String formatedDate=null;
		java.util.Date date = new java.util.Date();
		//formatter = new SimpleDateFormat("dd-MMM-yyyy:HH:mm:SS");
		formatter = new SimpleDateFormat("dd-MMM-yyyy");
		formatedDate =formatter.format(date);
		dateString =formatedDate.replace(":", "-");
		return dateString;
	}
	public static void createDirt(String folderName){
		String folderLocation =FILE_PATH+folderName;
		new File(folderLocation).mkdir();
	}
	public static String getCheetyDueData(HttpServletRequest request){
		int cheetyAmount =0,tot_months=0,tot_mem=0,pata_amount=0;
		float tot_due=0,due_amount=0,topu_amount=0;
		String mem_code="",pata_date="",ch_code="",resultType="";
		try{
			ch_code = request.getParameter("ch_code");
			mem_code = request.getParameter("mem_code");
			pata_amount = Integer.parseInt(request.getParameter("TAKE_AMT"));
			pata_date = request.getParameter("TAKE_DATE");
			String getCheetyDetails = CheetyCreationBizlogic.getCheetyAmount(Integer.parseInt(ch_code));
			
			if(getCheetyDetails.length() > 0){
				String[] str = new String[5];
				System.out.println("str :"+str);
				str = getCheetyDetails.split("#"); 
				if(str.length > 0){				
					cheetyAmount = Integer.parseInt(str[0]);
					tot_months = Integer.parseInt(str[1]);
					tot_mem = Integer.parseInt(str[2]);
					tot_due = cheetyAmount/tot_mem;				
					topu_amount = pata_amount/tot_mem;
					if(tot_due > topu_amount)due_amount = tot_due-topu_amount;				
				}
				request.setAttribute("CH_CODE", ch_code);
				request.setAttribute("MEM_CODE", mem_code);
				request.setAttribute("PATA_AMOUNT", pata_amount);
				request.setAttribute("PATA_DATE", pata_date);
				request.setAttribute("CHEETY_AMOUNT", cheetyAmount);
				request.setAttribute("TOT_MONTHS", tot_months);
				request.setAttribute("TOT_MEM", tot_mem);
				request.setAttribute("TOT_DUE", tot_due);
				request.setAttribute("TOPU_AMOUNT", topu_amount);
				request.setAttribute("DUE_AMOUNT", due_amount);
				resultType ="dataCalculated";
			}			
		}catch (Exception e) {resultType ="dataNotCalculated";}
		return resultType;
	}
}
