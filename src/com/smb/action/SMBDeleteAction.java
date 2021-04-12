package com.smb.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CustomerUpdationBizlogic;
import com.smb.bizlogic.UserDeleteBizlogic;

public class SMBDeleteAction  extends ActionSupport implements ServletRequestAware {
	static final long serialVersionUID = 42L;
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public String getUserDetailsForDelete(){
		String isCusData="";
		String cus_id=request.getParameter("cus_id");
		cus_id = (cus_id != null)?cus_id:""; 
		if(cus_id.length() >0){  
			String customerData = CustomerUpdationBizlogic.getCustomerDetails(request);
			if(!"updateData".equals(customerData)){
				addActionError("Customer id not exist ");			
				isCusData = "getUserDetailsForDelete";
			}else{
				isCusData = "getUserDetailsForDelete";
			}
		}else{
			addActionError("Please enter Customer Id!");	
			isCusData = "getUserDetailsForDelete";
		}
		return isCusData;
	}
	public String userFinalDueCalculation(){
		String isCusData=""; 
		String customerData = UserDeleteBizlogic.userFinalDueCalculation(request);
		if(!"userFinalDueCalculation".equals(customerData)){		
			isCusData = "getUserDetailsForDelete";
		}else{
			isCusData = "userFinalDueCalculation";
		}
		return isCusData;
	}
	public String userFinalDueUpdateDeleteDetails(){
		String isCusData="",actionMsg="";		 
		String customerData = UserDeleteBizlogic.userFinalDueUpdateDelete(request);
		if(!"userFinalUpdateDelete".equals(customerData)){
			actionMsg = "Due Details Not Updated Successfully !";			
			isCusData = "userFinalDueCalculation";
		}else{
			actionMsg = "Final Due Updated and Deleted Successfully !";	
			isCusData = "userFinalDueUpdateDeleteDetails";
		}		
		addActionError(actionMsg);
		System.out.println(actionMsg);
		return isCusData;
	}
	public String deleteDetails(){
		String disMsg="",resultType="",cus_id="";
		cus_id= request.getParameter("cus_id");
		cus_id = (cus_id !=  null)?cus_id:"";
		if(cus_id.length() > 0){
			String isDelete = UserDeleteBizlogic.userDeleteDetails(request); 
			if(!"deleted".equals(isDelete)){
				disMsg ="User dose not exit .";
				addActionError(disMsg);	
				request.setAttribute("cus_id", cus_id);
				resultType ="deleteDetails";
			}
			else{
				disMsg =""+cus_id+" User delete successfully .";
				addActionError(disMsg);
				request.setAttribute("cus_id", cus_id);
				resultType ="deleteDetails";
			}
		}else{
			resultType ="deleteDetails";
		}
		return resultType;
	}
}
