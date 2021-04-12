package com.smb.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CustomerUpdationBizlogic;

public class CustomerUpdationAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;String userLogedIn="";
	Map session = ActionContext.getContext().getSession();
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public String getUserDetailsUpdation(){
		String isCusData="";
		
		String cus_id=request.getParameter("cus_id");
		cus_id = (cus_id != null)?cus_id:""; 
		if(cus_id.length() >0){  
			String customerData = CustomerUpdationBizlogic.getCustomerDetails(request);
			if(!"updateData".equals(customerData)){
				addActionError("Customer id not exist ");			
				isCusData = "getUserDetailsUpdation";
			}else{
				isCusData = "getUserDetailsUpdation";
			}
		}else{
			addActionError("Please enter Customer Id!");	
			isCusData = "getUserDetailsUpdation";
		}
		return isCusData;
	}
	public String getUserDetailsDateUpdation(){
		String isCusData="";
		System.out.println("getCustomerTransDetails########");
		String cus_id=request.getParameter("cus_id");
		cus_id = (cus_id != null)?cus_id:""; 
		if(cus_id.length() >0){  
			String customerData = CustomerUpdationBizlogic.getCustomerTransDetails(request);
			if(!"updateData".equals(customerData)){
				addActionError("Customer id not exist ");			
				isCusData = "getUserDetailsDateUpdation";
			}else{
				isCusData = "getUserDetailsDateUpdation";
			}
		}else{
			addActionError("Please enter Customer Id!");	
			isCusData = "getUserDetailsDateUpdation";
		}
		return isCusData;
	}
	public String updateNewDate(){
		String isCusData="",actionMsg="";
		System.out.println("updateNewDate########");
		String cus_id=request.getParameter("cus_id");
		cus_id = (cus_id != null)?cus_id:""; 
			String customerData = CustomerUpdationBizlogic.updateDateInTransDetails(request);
			if(!"dateUpdated".equals(customerData)){	
				actionMsg = "New Date Not Updated Successfully !";
				isCusData = "updateDate";
			}else{
				actionMsg = "New Date Updated Successfully !";
				isCusData = "getUserDetailsDateUpdation";
			}
			addActionError(actionMsg);	
			isCusData = "getUserDetailsDateUpdation";
		
		return isCusData;
	}
	public String userDueCalculation(){
		String isCusData=""; 
		String customerData = CustomerUpdationBizlogic.userDueCalculation(request);
		if(!"dueUserCalculation".equals(customerData)){		
			isCusData = "getUserDetailsUpdation";
		}else{
			isCusData = "userDueCalculation";
		}
		return isCusData;
	}
	public String updateUserDueDetails(){
		String isCusData="",actionMsg="";		 
		String customerData = CustomerUpdationBizlogic.saveCustomerUpdation(request);
		if(!"duePaymentUpdated".equals(customerData)){
			actionMsg = "Due Details Not Updated Successfully !";			
			isCusData = "userDueCalculation";
		}else{
			actionMsg = "Due Details Updated Successfully !";	
			isCusData = "updateUserDueDetails";
		}		
		System.out.println(actionMsg);
		addActionError(actionMsg);
		return isCusData;
	}
}
