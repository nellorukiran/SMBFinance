package com.smb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CustomerDownloadBizlogic;
import com.smb.util.CommonUtil;

public class CustomerDownloadAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	private InputStream inputStream;
	private String fileName;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private long contentLength;

	public long getContentLength() {
		return contentLength;
	}

	public String getFileName() {
		return fileName;
	}

	public InputStream getInputStream() throws Exception {
		return inputStream;
	}

	public String userDownloadDeatils() {
		String isCusData = "", successMsg = "", inputVal = "CustomerId";
		System.out.println("userDownloadDeatils");
		/*
		 * String inputVal = request.getParameter("inputVal"); inputVal = (inputVal !=
		 * null)? inputVal:"";
		 * System.out.println("userDownloadDeatils inputVal-->"+inputVal); String
		 * inputType = request.getParameter("inputType"); inputType = (inputType !=
		 * null)? inputType:"";
		 * System.out.println("userDownloadDeatils inputType-->"+inputType); String
		 * folderType = request.getParameter("folderType"); folderType = (folderType !=
		 * null)? folderType:"";
		 * System.out.println("userDownloadDeatils folderType-->"+folderType);
		 */
		// ?inputVal=CustomerId&inputType=monthly&folderType=Monthly Details"
		try {
			if (inputVal.length() > 0) {
				String customerData = CustomerDownloadBizlogic.userDownloadsDeatils(inputVal);

				File fileToDownload = new File(CommonUtil.getFullFileName(inputVal));
				inputStream = new FileInputStream(fileToDownload);
				fileName = fileToDownload.getName();
				contentLength = fileToDownload.length();
				/*
				 * customerData ="downloaded"; if(!"downloaded".equals(customerData)){
				 * successMsg
				 * ="<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>"
				 * ; //addActionError(successMsg);
				 * System.out.println("userDownloadDeatils ::"+successMsg); //isCusData =
				 * "ERROR"; }else{ successMsg
				 * ="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully Order By.</font><font color='red' size='2'>"
				 * +inputVal+"</font></b></div>"; //addActionError(successMsg);
				 * System.out.println("userDownloadDeatils ::"+successMsg); //isCusData =
				 * "SUCCESS"; }
				 */
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return SUCCESS;
	}

	public String userDownloadDeatilsCusId() throws Exception {
		String inputVal = "CustomerId";
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("FIRST_NAME");
		userName = (userName != null) ? userName : "";
		String dateString = "";
		String formatedDate = null;
		java.util.Date date = new java.util.Date();
		Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
		formatedDate = formatter.format(date);
		dateString = formatedDate.replace(":", "-");
		if (userName.length() <= 0) {
			return "invalidUser";
		} else {
			try {
				String customerData = CustomerDownloadBizlogic.userDownloadsDeatils(inputVal);
				if ("downloaded".equals(customerData)) {
					// File fileToDownload = new File(CommonUtil.getFullFileName(inputVal));
					File fileToDownload = new File("OrderBy_" + inputVal + "_" + dateString + ".xls");
					if (fileToDownload.exists()) {
						inputStream = new FileInputStream(fileToDownload);
						fileName = fileToDownload.getName();
						setFileName(fileToDownload.getName());
						fileName = getFileName();
						System.out.println("****************** File Name ::" + fileName);
						contentLength = fileToDownload.length();
					}
				}
			} catch (Exception e) {
				System.out.println("************************ userDownloadDeatilsCusId()" + e);
			}
			return SUCCESS;
		}
	}

	public String userDownloadDeatilsAddress() {
		String inputVal = "Address";

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("FIRST_NAME");
		userName = (userName != null) ? userName : "";
		String dateString = "";
		String formatedDate = null;
		java.util.Date date = new java.util.Date();
		Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
		formatedDate = formatter.format(date);
		dateString = formatedDate.replace(":", "-");
		if (userName.length() <= 0) {
			return "invalidUser";
		} else {
			try {
				String customerData = CustomerDownloadBizlogic.userDownloadsDeatils(inputVal);
				if ("downloaded".equals(customerData)) {
					File fileToDownload = new File("OrderBy_" + inputVal + "_" + dateString + ".xls");
					if (fileToDownload.exists()) {
						inputStream = new FileInputStream(fileToDownload);
						fileName = fileToDownload.getName();
						contentLength = fileToDownload.length();
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return SUCCESS;
		}
	}

	public String userDownloadProfitDeatils() {
		String isCusData = "", successMsg = "";
		String toDate = "", fromDate = "";
		fromDate = request.getParameter("fromDate");
		fromDate = (fromDate != null) ? fromDate : "";
		toDate = request.getParameter("toDate");
		toDate = (toDate != null) ? toDate : "";
		String folderName = "Profit Details", inputVal = "ProfitDetails";
		if (fromDate.length() > 0 && toDate.length() > 0) {
			String customerData = CustomerDownloadBizlogic.userDownloadProfitDeatils(fromDate, toDate, folderName,
					inputVal, request);
			if (!"downloaded".equals(customerData)) {
				successMsg = "<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";
				addActionError(successMsg);
				isCusData = "profiltDetails";
			} else {
				successMsg = "<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully Order By.</font><font color='red' size='2'>"
						+ inputVal
						+ "</font></b><br><b><font color='red' size='2'> File Location :</font><font color='blue'>"
						+ CommonUtil.getFileLocation(inputVal, folderName)
						+ "</font></b><br><b><font color='red' size='2'> File Name : </font><font color='blue'>"
						+ CommonUtil.getFileName(inputVal, folderName) + "</font></b></div>";
				addActionError(successMsg);
				isCusData = "profiltDetails";
			}
		}

		return isCusData;
	}

	public String totalUserDueHistoryDeatils() {
		String isCusData = "", successMsg = "";

		String folderName = "Due History", inputVal = "Due History";
		String customerData = CustomerDownloadBizlogic.totalUserDueHistoryDeatils();
		if (!"totalUserDueHistoryDeatilsSuccess".equals(customerData)) {
			successMsg = "<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";
			addActionError(successMsg);
			isCusData = "totalUserDueHistoryDeatils";
		} else {
			successMsg = "<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully .</font><font color='red' size='2'>"
					+ inputVal
					+ "</font></b><br><b><font color='red' size='2'> File Location :</font><font color='blue'>"
					+ CommonUtil.getFileLocation(inputVal, folderName)
					+ "</font></b><br><b><font color='red' size='2'> File Name : </font><font color='blue'>"
					+ CommonUtil.getFileName(inputVal, folderName) + "</font></b></div>";
			addActionError(successMsg);
			isCusData = "totalUserDueHistoryDeatils";
		}

		return isCusData;
	}

	public String downloadProductDeatils() {
		String isCusData = "", successMsg = "", folderName = "Product Details";
		String inputVal = request.getParameter("inputVal");
		inputVal = (inputVal != null) ? inputVal : "";
		String customerData = CustomerDownloadBizlogic.downloadProductDeatils(inputVal, folderName);
		if (!"downloadProductDeatilsSuccess".equals(customerData)) {
			successMsg = "<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";
			addActionError(successMsg);
			isCusData = "downloadProductDeatils";
		} else {
			successMsg = "<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully .</font><font color='red' size='2'>"
					+ inputVal
					+ "</font></b><br><b><font color='red' size='2'> File Location :</font><font color='blue'>"
					+ CommonUtil.getFileLocation(inputVal, folderName)
					+ "</font></b><br><b><font color='red' size='2'> File Name : </font><font color='blue'>"
					+ CommonUtil.getFileName(inputVal, folderName) + "</font></b></div>";
			addActionError(successMsg);
			isCusData = "downloadProductDeatils";
		}

		return isCusData;
	}

}
