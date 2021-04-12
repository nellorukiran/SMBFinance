package com.smb.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CustomerDownloadBizlogic;
import com.smb.bizlogic.DailyLedgerBizlogic;
import com.smb.util.CommonUtil;

public class DailyLedgerAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public String dailyLedger(){
		return "dailyLedger";
	}
	public String downloadLedgerPage(){
		return "downloadLedgerPage";
	}
	public String viewLedger(){
		return "viewLedger";
	}
	public String getLedgerBetweenDates(){
		String isCusData="",successMsg="";  
		String toDate="",fromDate="";
		fromDate= request.getParameter("fromDate");
		fromDate=(fromDate !=null)?fromDate:"";
		toDate= request.getParameter("toDate");
		toDate=(toDate !=null)?toDate:"";
		if(fromDate.length()>0 && toDate.length()>0){
			String customerData = DailyLedgerBizlogic.viewLedgerBetweenDates(fromDate, toDate, request);
			if(!"getLedgerBetweenDates".equals(customerData)){
				//successMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";	
				addActionError(successMsg);			
				isCusData = "getLedgerBetweenDates";
			}else{
				//successMsg ="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully Order By.</font><b><font color='red' size='2'> File Location :</font></b><font color='red' size='2'> File name :</font></b><font color='blue'></font></div>";				
				addActionError(successMsg);				
				isCusData = "noGetLedgerBetweenDates";
			}
		}
		
		return isCusData;
	}
	public String downloadLedgerDeatils(){
		String isCusData="",successMsg="";  
		
		String folderName = "Ledger Details",inputVal="Ledger Details";
			String customerData = DailyLedgerBizlogic.downloadLedgerDeatils(folderName,inputVal);
			if(!"downloadLedgerDeatilsSuccess".equals(customerData)){
				successMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";	
				addActionError(successMsg);			
				isCusData = "downloadLedgerDeatils";
			}else{
				successMsg ="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully .</font><font color='red' size='2'>"+inputVal+"</font></b><br><b><font color='red' size='2'> File Location :</font><font color='blue'>"+CommonUtil.getFileLocation(inputVal, folderName)+"</font></b><br><b><font color='red' size='2'> File Name : </font><font color='blue'>"+CommonUtil.getFileName(inputVal, folderName)+"</font></b></div>";				
				addActionError(successMsg);				
				isCusData = "downloadLedgerDeatils";
			}
		
		return isCusData;
	}
	public String createDailyLedger(){		
		//System.out.println("DailyLedgerAction createDailyLedger()");
		String LedgerInsert="",disMsg="",resType="";
		LedgerInsert = DailyLedgerBizlogic.insertDailyLedgerDetails(request);
		if(!LedgerInsert.equals("inserted")){
			disMsg = "Ledger Not Inserted Successfully";
			addActionError(disMsg);
			resType = "createDailyLedger";
		}else{
			disMsg = "Ledger Inserted Successfully";
			addActionError(disMsg);
			resType = "createDailyLedger";
		}
		request.setAttribute("disMsg", disMsg);
		//System.out.println("resType ::"+resType);
		return resType;
	}
}
