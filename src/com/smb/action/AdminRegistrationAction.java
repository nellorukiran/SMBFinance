package com.smb.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CustomerRegistrationBizlogic;
import com.smb.bizlogic.UserDeleteBizlogic;

public class AdminRegistrationAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}	
	public String adminRegistration(){
		String isCusData="",successMsg="";  
	
			String user_type=request.getParameter("user_type");
			user_type = (user_type != null)?user_type:"";
			if(user_type.length() > 0){ 
				String customerData = CustomerRegistrationBizlogic.adminRegistration(request);
			
				if(!"adminRegistration".equals(customerData)){			
					successMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>Registration is error</font></div>";	
					addActionError(successMsg);		
					isCusData = "adminRegistration";
				}else{			
					successMsg ="<div style=\"float:left;\"><b><font color='green' size='2'>Registration is success</font></div>";	
					addActionError(successMsg);		
					isCusData = "adminRegistration";
				}
			}else{
				isCusData = "adminRegistration";
			}
	
		return isCusData;
	}
	public String viewAdminUsersPage(){
		String resultType="";
			String result = CustomerRegistrationBizlogic.viewAdminUsers(request);
			if(!"viewAdminUsers".equals(result)){
				resultType ="viewAdminUsersPage";
			}
			else{
				request.setAttribute("yes", "yes");
				resultType ="viewAdminUsersPage";
			}
		return resultType;
	}
	public String userAdminDeletePage(){
		return "userAdminDeletePage";
	}
	public String userAdminDelete(){
		String disMsg="",resultType="",user_name="",user_type="";
		user_name= request.getParameter("user_name");
		user_name = (user_name !=  null)?user_name:"";
		user_type= request.getParameter("user_type");
		user_type = (user_type !=  null)?user_type:"";
		if(user_name.length() > 0 && user_type.length() >0){
			String isDelete = CustomerRegistrationBizlogic.userAdminDelete(request); 
			if(!"deleted".equals(isDelete)){
				disMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>User dose not exit</font></div>";
				addActionError(disMsg);	
				resultType ="userAdminDelete";
			}
			else{
				disMsg ="<div style=\"float:left;\"><b><font color='green' size='2'>"+user_name+" delete successfully</font></div>";
				addActionError(disMsg);
				resultType ="userAdminDelete";
			}
		}else{
			resultType ="userAdminDelete";
		}
		return resultType;
	}
	public String getAdminUserList(){		
		String user_type = request.getParameter("user_type");
		user_type = (user_type != null)?user_type:"";
		String resultType ="";
		if(user_type.length() > 0){
			JSONArray objArray = UserDeleteBizlogic.getAdminUserList(user_type);
			if(objArray != null){
				request.setAttribute("JSONOBJ", objArray);
				resultType = "getAdminUserList" ;
			}
		}else{
			resultType = "getAdminUserList" ;
		}
		return resultType;
	}
	
}
