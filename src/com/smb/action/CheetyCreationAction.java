package com.smb.action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.smb.bizlogic.CheetyCreationBizlogic;
import com.smb.bizlogic.CustomerDownloadBizlogic;
import com.smb.util.CommonUtil;

public class CheetyCreationAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}	
	public String getCheetyId(){
		int isCheety = 0;		
		isCheety = CheetyCreationBizlogic.getCheetyId();
		
		request.setAttribute("CH_ID", isCheety);
			
		return "getCheetyId";
	}
	public String cheetyPackageInsert(){
		String cheetyInsert = "",resType="",disMsg="";		 
		cheetyInsert = CheetyCreationBizlogic.insertCheetyDetails(request);
		if(!cheetyInsert.equals("inserted")){
			disMsg = "<div style=\"float:left;\"><b><font color='red' size='2'>Cheety Package Not Inserted Successfully !</font></div>";
			addActionError(disMsg);
			resType = "cheetyPackageInsert";
		}else{
			disMsg = "<div style=\"float:left;\"><b><font color='green' size='2'>Cheety Package Inserted Successfully !</font></div>";
			addActionError(disMsg);
			resType = "cheetyPackageInsert";
		}
		System.out.println("resType ::"+resType);
		return resType;
	}
	public String cheetyMemberInsert(){
		String cheetyInsert = "",resType="",disMsg="";		 
		cheetyInsert = CheetyCreationBizlogic.insertCheetyMemberDetails(request);
		if(!cheetyInsert.equals("inserted")){
			disMsg = "<div style=\"float:left;\"><b><font color='red' size='2'>Cheety Members Not Inserted Successfully !</font></div>";
			addActionError(disMsg);
			resType = "cheetyMemberInsert";
		}else{
			disMsg = "<div style=\"float:left;\"><b><font color='green' size='2'>Cheety Members Inserted Successfully !</font></div>";
			addActionError(disMsg);
			resType = "cheetyMemberInsert";
		}
		System.out.println("resType ::"+resType);
		return resType;
	}
	public String createCheetyMemberIdList(){		
		String ch_code = request.getParameter("chCodeVal");
		ch_code = (ch_code != null)?ch_code:"";
		String resultType ="";
		JSONArray objArray = CheetyCreationBizlogic.createCheetyMemberIdList(ch_code);
		if(objArray != null){
			request.setAttribute("JSONOBJ", objArray);
			resultType = "createCheetyMemberIdList" ;
		}
		return resultType;
	}
	public String getCheetyUpdate(){
		int isCheety = 0;String resultType="";		
		isCheety = CheetyCreationBizlogic.getCheetyUpdate();
		if(isCheety > 0){
			request.setAttribute("CH_ID", isCheety);
			resultType = "getCheetyId";
		}
		return resultType;
	}
	public String getCheetyMemberId(){
		int memId = 0;String resultType="";		
		memId = CheetyCreationBizlogic.getCheetyMemberId();
		if(memId > 0){
			request.setAttribute("MEM_ID", memId);
			resultType = "getCheetyMemberId";
		}
		return resultType;
	}
	public String createCheetyMember(){
		String resultType ="";
		JSONArray objArray = CheetyCreationBizlogic.getCheetyIdList();
		if(objArray != null){
			request.setAttribute("JSONOBJ", objArray);
			resultType = "createCheetyMember" ;
		}
		return resultType;
	}
	public String cheetyCalculation(){				
		String resultType ="";
		resultType = CheetyCreationBizlogic.cheetyCalculate(request);
		if(!resultType.equals("dataCalculated")){
			resultType = "dataNotCalculated";
		}else{
			resultType = "dataCalculated";			
		}
		return resultType;
	}
	public String cheetyUpdate(){				
		String resultType ="",outputMsg="";
		resultType = CheetyCreationBizlogic.cheetyUpdate(request);
		if(!resultType.equals("cheetyUpdate")){
			outputMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>Due Not saved Successfully.</font></div>";	
			addActionError(outputMsg);
			resultType = "cheetyUpdate";
		}else{
			outputMsg ="<div style=\"float:left;\"><b><font color='green' size='2'>Due Successfully Saved.</font></div>";	
			addActionError(outputMsg);	
			resultType = "cheetyUpdate";			
		}
		return resultType;
	}
	public String viewCheetyDetails(){				
		String resultType ="",outputMsg="";
		ResultSet rs = null;
		int ch_code = Integer.parseInt(request.getParameter("chCodeVal"));
		ch_code = (ch_code > 0l)?ch_code:0; 
		rs = CheetyCreationBizlogic.viewCheetyDetails(ch_code,request);
		if(rs == null){ 
			outputMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>No Result Found.</font></div>";	
			addActionError(outputMsg);
			resultType = "viewCheetyDetails";
		}else{
			try{
				if(rs != null){
					request.setAttribute("resultSet", rs);
					resultType = "viewCheetyDetails";	
				}		
			}catch (Exception e) {}
		}
		return resultType;
	}
	public String cheetydownload(){
		String isCusData="",successMsg="",folderName="CHEETY";  
		int che_id = Integer.parseInt(request.getParameter("cheety_id"));
		che_id = (che_id > 0)?che_id:0;
		String customerData = CustomerDownloadBizlogic.cheetyDownloadDetails(che_id);
		if(!"downloaded".equals(customerData)){
			successMsg ="<div style=\"float:left;\"><b><font color='red' size='2'>Not Downloade Successfully.</font></div>";	
			addActionError(successMsg);			
			isCusData = "cheetydownload";
		}else{
			successMsg ="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully </font></b><br><b><font color='red' size='2'> File Location :</font><font color='blue'>"+CommonUtil.getFileCheety()+"</font></b><br><b><font color='red' size='2'> File Name : </font><font color='blue'>"+CommonUtil.getCheetyFileLocation(folderName)+"</font></b></div>";				
			addActionError(successMsg);				
			isCusData = "cheetydownload";
		}
		return isCusData;
	}
}
