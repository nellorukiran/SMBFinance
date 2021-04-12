package com.smb.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Result;
import com.smb.bizlogic.CustomerDownloadBizlogic;
import com.smb.bizlogic.ViewDetailsBizlogic;

public class ViewDetailsAction  extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public String viewPaymentHistoryDetails(){
		String resultString = "",resultType="";
		String cus_id = request.getParameter("inputVal");
		cus_id = (cus_id != null)?cus_id:""; 
		if(cus_id.length() >0){  
			String result = ViewDetailsBizlogic.viewPaymentHistory(request);
			if(!"viewHistory".equals(result)){
				resultString ="User dose not exit.";
				addActionError(resultString);	
				request.setAttribute("cus_id", cus_id);
				resultType ="viewPaymentHistoryDetails";
			}
			else{
				//resultString ="<font color=\"green\"> User delete successfully.</font>";
				//addActionError(resultString);
				request.setAttribute("yes", "yes");
				resultType ="viewPaymentHistoryDetails";
			}
		}else{
			resultString ="Please enter user id.";
			addActionError(resultString);
		}
		
		return resultType;
	}
	public String viewUserDetailsPage(){
		return "viewUserDetailsPage";
	}
	public String viewUserDetails(){
		//System.out.println("### ViewDetailsAction viewUserDetails()");
		String resultString = "",resultType="",result="";
		String inputType = request.getParameter("inputVal");
		inputType = (inputType != null)?inputType:"";
		if(inputType.length() >0){
			result = ViewDetailsBizlogic.showUserDetails(inputType,request);
		}
		if("userPaymentDetails".equals(result)){
			resultType ="userPaymentDetails";
		}
		else if("userTransactionDetails".equals(result)){
			resultType ="userTransactionDetails";
		}else{
			resultString ="No Data Availble .";
			addActionError(resultString);
		}
		return resultType;
	}
	public String viewCustomerProfitDetails(){
		String isCusData="",successMsg="";  
		String toDate="",fromDate="";
		fromDate= request.getParameter("fromDate");
		fromDate=(fromDate !=null)?fromDate:"";
		toDate= request.getParameter("toDate");
		toDate=(toDate !=null)?toDate:"";
		if(fromDate.length()>0 && toDate.length()>0){
			String customerData = ViewDetailsBizlogic.viewCustomerProfitDetails(fromDate, toDate, request);
			if(!"viewCustomerProfitDetails".equals(customerData)){
				//successMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";	
				addActionError(successMsg);			
				isCusData = "viewCustomerProfitDetails";
			}else{
				//successMsg ="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully Order By.</font><b><font color='red' size='2'> File Location :</font></b><font color='red' size='2'> File name :</font></b><font color='blue'></font></div>";				
				addActionError(successMsg);				
				isCusData = "noViewCustomerProfitDetails";
			}
		}
		
		return isCusData;
	}
	public String viewMonthlyCollectionDetails(){
		String isCusData="",successMsg="";  
		String toDate="",fromDate="";
		fromDate= request.getParameter("fromDate");
		fromDate=(fromDate !=null)?fromDate:"";
		toDate= request.getParameter("toDate");
		toDate=(toDate !=null)?toDate:"";
		try{
		if(fromDate.length()>0 && toDate.length()>0){
			String customerData = ViewDetailsBizlogic.viewMonthlyCollectionDetails(fromDate, toDate, request);
			if("viewMonthlyCollection".equals(customerData)){
				//successMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";	
				//addActionError(successMsg);
				request.setAttribute("fromDate", fromDate);
				request.setAttribute("toDate", toDate);
				isCusData = "viewMonthlyCollection";
			}else{
				//successMsg ="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully Order By.</font><b><font color='red' size='2'> File Location :</font></b><font color='red' size='2'> File name :</font></b><font color='blue'></font></div>";				
				addActionError("No Data Available between dates");				
				isCusData = "noViewMonthlyCollection";
			}
		}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return isCusData;
	}
}
