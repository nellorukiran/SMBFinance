package com.smb.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SMBForwardAction extends ActionSupport implements ServletRequestAware {
	static final long serialVersionUID = 42L;
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public String homeLink(){
		return "userLogin";
	}
	public String userRegister(){
		return "userRegister";
	}
	public String userDelete(){
		return "userDelete";
	}
	public String userUpdateDelete(){
		return "userUpdateDelete";
	}
	public String logoutAction(){
		Map session = ActionContext.getContext().getSession();
		session.remove("logedin");
		session.remove("USER_TYPE");
		session.remove("USER_NAME");
		session.remove("FIRST_NAME");
		return "logoutAction";
	}
	public String userUpdation(){
		return "userUpdation";
	}
	public String userDownload(){
		return "userDownload";
	}
	public String viewUserDetails(){
		return "viewUserDetails";
	}
	public String productDownload(){
		return "productDownload";
	}
	public String historyDownload(){
		return "historyDownload";
	}
	public String paymentCalculation(){
		int buy_price = 0,saled_price=0;
		buy_price= Integer.parseInt(request.getParameter("buy_price"));
		buy_price = (buy_price > 0)?buy_price:0;
		saled_price= Integer.parseInt(request.getParameter("saled_price"));
		saled_price = (saled_price > 0)?saled_price:0;
		if(buy_price < saled_price){
			addFieldError("saled_price", "Please enter Correct Saled price.");
			return "userRegister";
		}
		return "paymentCalculation";
	}
}
