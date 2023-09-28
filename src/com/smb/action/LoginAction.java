package com.smb.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CustomerRegistrationBizlogic;

public class LoginAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String userLogin() {
		String resultType = "";
		String username = "", password = "";
		username = request.getParameter("username");
		username = (username != null) ? username.trim() : "";
		password = request.getParameter("password");
		password = (password != null) ? password.trim() : "";
		if (username.length() > 0 && password.length() > 0) {
			String isValidUser = CustomerRegistrationBizlogic.checkIsValidUser(request);

			if (isValidUser != null && isValidUser.equals("validUser")) {
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("logedin", "true");
				session.put("USER_TYPE", (String) request.getAttribute("USER_TYPE"));
				session.put("FIRST_NAME", (String) request.getAttribute("FIRST_NAME"));
				resultType = "userLogin";
			} else if (isValidUser != null && isValidUser.equals("notMatched")) {
				addFieldError("password", "Password is not matched!");
				resultType = "error";
			} else {
				addActionError("Username does not exist.");
				resultType = "error";
			}
		} else {
			resultType = "error";
		}
		return resultType;
	}

}
