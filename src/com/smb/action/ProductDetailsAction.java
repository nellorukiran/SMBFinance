package com.smb.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.ProductDetailsBizlogic;

public class ProductDetailsAction  extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public String createProduct(){
		return "createProduct";
	}
	public String saveProduct(){
		String prod_name = request.getParameter("prod_name");
		prod_name = (prod_name != null)?prod_name:"";
		String resultType= "" ,resultStr = "";
		if(prod_name.length() > 0){
			String isSaved= ProductDetailsBizlogic.saveProductName(prod_name, request);
			if(!"saveProduct".equals(isSaved)){
				resultType = "saveProduct";
				resultStr = "Product Not Saved Successfully ";
				addActionError(resultStr);
			}else{
				resultType = "saveProduct";
				resultStr = "Product Saved Successfully ";
				addActionError(resultStr);
			}
		}else{
			resultStr = "Please enter the Product Name";
			addActionError(resultStr);
			resultType = "saveProduct";
		}
		return resultType;
	}
}
