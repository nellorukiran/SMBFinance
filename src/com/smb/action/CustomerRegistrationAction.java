package com.smb.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CustomerRegistrationBizlogic;

public class CustomerRegistrationAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public String paymentCalculation(){
		String isCusData="";
		String customerData = CustomerRegistrationBizlogic.customerCreation(request);
		if(!"userDataRev".equals(customerData)){
			addActionError("Saled price less than Actual price!");			
			isCusData = "userRegister";
		}else{
			isCusData = "userDataReviced";
		}
		return isCusData;
	}
	public String updateDate(){
		return "updateDate";
	}
	public String paymentSave(){
		String dataSave="",disMsg="";
		String isSave = CustomerRegistrationBizlogic.customerCreationSave(request);
		if(!"saved".equals(isSave)){			
			disMsg = "Data Not Saved Successfully !";
			dataSave = "userDataReviced";
		}else{
			disMsg = "Data Saved Successfully !";			
			dataSave = "paymentSave";
		}
		addActionError(disMsg);
		return dataSave;
	}
}
