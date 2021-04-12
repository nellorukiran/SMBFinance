package com.smb.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ForwardAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
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
	public String userUpdation(){
		return "userUpdation";
	}
	public String userDownload(){
		return "userDownload";
	}
	public String viewUserDetails(){
		return "viewUserDetails";
	}
	public String profitDownload(){
		return "profitDownload";
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
