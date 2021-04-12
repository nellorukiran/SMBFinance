package com.smb.bizlogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;

public class UserDeleteBizlogic {
	public static String userFinalDueCalculation(HttpServletRequest request){
		String resultStr = "";
		int tot_dues=0,penalty=0,due_amt=0,next_due_amt=0,paid_amt=0,cus_id=0,per_due_amt=0;
	    String cus_name="",phone="",due_date="";
	    try{ 
			cus_id=Integer.parseInt(request.getParameter("cus_id"));
			cus_id = (cus_id > 0)?cus_id:0; 
			cus_name = request.getParameter("cus_name");
			cus_name = (cus_name != null)?cus_name:"";
			phone = request.getParameter("phone");
			phone = (phone != null)?phone:"";
			due_date = request.getParameter("due_date");
			due_date = (due_date != null)?due_date:"";
			tot_dues = Integer.parseInt(request.getParameter("tot_dues"));
			tot_dues = (tot_dues > 0)?tot_dues:0;
			per_due_amt = Integer.parseInt(request.getParameter("per_due_amt"));
			per_due_amt = (per_due_amt > 0)?per_due_amt:0;
			next_due_amt= Integer.parseInt(request.getParameter("next_due_amt"));
			next_due_amt = (next_due_amt > 0)?next_due_amt:0;
			if(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))
			{
				penalty=0;
			}else{
				penalty=Integer.parseInt(request.getParameter("penalty"));		
				penalty = (penalty >0)?penalty:0;
			}
			due_amt =Integer.parseInt(request.getParameter("due_amt"));
			due_amt = (due_amt > 0)?due_amt:0;
	        phone=request.getParameter("phone");
	        phone = (phone != null)?phone:"";
	        paid_amt=Integer.parseInt(request.getParameter("paid_amt"));
	        paid_amt = (paid_amt > 0)?paid_amt:0;
	     
	        
	        //if penalty more than 0 need to add penalty due amount
	        if(penalty > 0){
	        	due_amt = (due_amt-paid_amt)+penalty;
	        	next_due_amt = next_due_amt+penalty;
	        }else{
	        	due_amt = due_amt-paid_amt;
	        }
	        //if  next due amount more than due amount we need to add next due amount to due amount
	        if(next_due_amt > due_amt)next_due_amt=due_amt;
	        
	        //if total dues 0 and due amount is more than 0 we need to add total dues is 1
	        if(tot_dues == 0 && due_amt > 0){
        	  tot_dues =1;
	        }
	        
			request.setAttribute("next_due_amt",next_due_amt);
	        request.setAttribute("tot_dues",tot_dues);
	        request.setAttribute("due_amt",due_amt);
	        request.setAttribute("paid_amt",paid_amt);
	        request.setAttribute("due_date",due_date);
	        //request.setAttribute("yes","yes");
	        resultStr ="userFinalDueCalculation";		
	        
	    }catch (Exception e) {		
	    	System.out.println("### CustomerUpdationBizlogic Exception ::"+e);
			resultStr = "userFinalDueNotCalculation";
	    }        
	    return resultStr;
	}
public static String userFinalDueUpdateDelete(HttpServletRequest request){
		PreparedStatement dueUpdation = null,insertHistory =null,deleteQuery =null,tractionRecStatus=null;
		Connection con = null;	
		String resultStr = "";
		int tot_dues=0,penalty=0,due_amt=0,next_due_amt=0,paid_amt=0,cus_id=0;
	    String cus_name="",phone="",due_date="";
	    
		cus_id=Integer.parseInt(request.getParameter("cus_id"));
		cus_id = (cus_id > 0)?cus_id:0; 
		cus_name = request.getParameter("cus_name");
		cus_name = (cus_name != null)?cus_name:"";
		phone = request.getParameter("phone");
		phone = (phone != null)?phone:"";
		due_date = request.getParameter("due_date");
		due_date = (due_date != null)?due_date:"";
		tot_dues = Integer.parseInt(request.getParameter("tot_dues"));
		tot_dues = (tot_dues > 0)?tot_dues:0;
		penalty = Integer.parseInt(request.getParameter("penalty"));
		penalty = (penalty > 0)?penalty:0;
		next_due_amt= Integer.parseInt(request.getParameter("next_due_amt"));
		next_due_amt = (next_due_amt > 0)?next_due_amt:0;
		if(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))
		{
			penalty=0;
		}else{
			penalty=Integer.parseInt(request.getParameter("penalty"));		
			penalty = (penalty >0)?penalty:0;
		}
		due_amt =Integer.parseInt(request.getParameter("due_amt"));
		due_amt = (due_amt > 0)?due_amt:0;
        phone=request.getParameter("phone");
        phone = (phone != null)?phone:"";
        Date payment_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{payment_date = sdf.parse(due_date);}catch (Exception ee) {}
        
        try{
        int multipleTable =0;
        paid_amt=Integer.parseInt(request.getParameter("paid_amt"));
        paid_amt = (paid_amt > 0)?paid_amt:0;
        request.setAttribute("cus_id",cus_id);
        request.setAttribute("cus_name",cus_name);
		request.setAttribute("next_due_amt",next_due_amt);
        request.setAttribute("penalty",penalty);
        request.setAttribute("tot_dues",tot_dues);
        request.setAttribute("due_amt",due_amt);
        request.setAttribute("phone",phone);
        request.setAttribute("due_date",due_date);
        request.setAttribute("yes", "yes");
		con = DBConnection.getDBConnection();
		con.setAutoCommit(false);
		
		//if(penalty > 0){
			dueUpdation = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_UPDATE_QUERY);
			dueUpdation.setString(1, cus_name);
			dueUpdation.setInt(2, tot_dues);
			dueUpdation.setInt(3, penalty);
			dueUpdation.setInt(4, next_due_amt);
			dueUpdation.setInt(5, due_amt);
			dueUpdation.setString(6, phone);
			dueUpdation.setString(7, "U");
			dueUpdation.setInt(8, cus_id);
		/*}else{
			dueUpdation = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_NON_PENALTY_UPDATE_QUERY);
			dueUpdation.setString(1, cus_name);
			dueUpdation.setInt(2, tot_dues);
			dueUpdation.setInt(3, next_due_amt);
			dueUpdation.setInt(4, due_amt);
			dueUpdation.setString(5, phone);
			dueUpdation.setString(6, "U");
			dueUpdation.setInt(7, cus_id);
		}*/
		dueUpdation.executeUpdate();
		insertHistory = con.prepareStatement(CommonConstents.PAYMENT_HISTORY_INSERT_QUERY);
			insertHistory.setInt(1, cus_id);
			insertHistory.setInt(2, paid_amt);
			insertHistory.setString(3,due_date);
			insertHistory.setObject(4,payment_date);
			insertHistory.setInt(5,due_amt);
			
			insertHistory.executeUpdate();
			
		tractionRecStatus = con.prepareStatement(CommonConstents.TRANSACTION_REC_STATUS_UPDATE);
		tractionRecStatus.setString(1, "D");
		tractionRecStatus.setInt(2, cus_id);
		tractionRecStatus.executeUpdate();
		
		deleteQuery = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DELETE_QUERY);	
		deleteQuery.setInt(1, cus_id);
		multipleTable = deleteQuery.executeUpdate();
		con.commit();  
		dueUpdation.close(); 
		insertHistory.close(); 
		deleteQuery.close(); 
		tractionRecStatus.close(); 
		con.close(); 	
		resultStr ="userFinalUpdateDelete";			
		}catch (Exception e) {	
			try{
				dueUpdation.close(); 
				insertHistory.close(); 
				deleteQuery.close(); 
				tractionRecStatus.close(); 
				con.close();
			}catch (Exception e1) {	}
			System.out.println("### CustomerUpdationBizlogic Exception ::"+e);
			try{con.rollback();resultStr = "duePaymentNotUpdated";}catch (Exception ex) {} 
		}        
		return resultStr;
	}
	public static String userDeleteDetails(HttpServletRequest request){
		String isDelete = "",userType="";
		PreparedStatement deleteQuy=null,itemDetailsQury=null;
		int cus_id =0;
		cus_id= Integer.parseInt(request.getParameter("cus_id"));
		cus_id = (cus_id > 0)?cus_id:0;
		userType= request.getParameter("deleteUser");
		userType = (userType != null)?userType:"";
		Connection con = null;
		try{
			int multipleTable =0,itemTable =0;
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			if("all".endsWith(userType)){
				deleteQuy = con.prepareStatement(CommonConstents.MULTIPLE_TABLE_DELETE_QUERY);
				deleteQuy.setInt(1, cus_id);
				
				itemDetailsQury = con.prepareStatement(CommonConstents.ITEM_DETAILS_DELETE_QUERY);
				itemDetailsQury.setInt(1, cus_id);
				
				multipleTable = deleteQuy.executeUpdate();
				itemTable = itemDetailsQury.executeUpdate();
				
				if(multipleTable == 2 && itemTable ==1){
					con.commit();
					isDelete ="deleted";
				}else{
					isDelete ="notdeleted";
				}
				
			}else{
				deleteQuy = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DELETE_QUERY);
				deleteQuy.setInt(1, cus_id);
				multipleTable = deleteQuy.executeUpdate();
				if(multipleTable == 1){
					con.commit(); 
					isDelete ="deleted";
				}else{
					isDelete ="notdeleted";
				}
			//con.close();
			}
			deleteQuy.close();
			itemDetailsQury.close();
			con.close();
		}catch(Exception e){
			System.out.println("UserDeleteBizlogic Exception e ::"+e);
			try{
				con.rollback();
				deleteQuy.close();
				itemDetailsQury.close();
				con.close();
			}catch (Exception ex) {
				System.out.println("UserDeleteBizlogic Exception ex ::"+ex);
			}
		}
		return isDelete;
	}
	public static JSONArray getAdminUserList(String user_type){		
		Connection con = null;
		PreparedStatement roleUserStm = null;
		JSONArray jsonArray = new JSONArray();
		try{ 
			con = DBConnection.getDBConnection();					
			roleUserStm = con.prepareStatement(CommonConstents.SELECT_USER_NAME_DETAILS_QUERY);	
			roleUserStm.setString(1, user_type);
			ResultSet r = roleUserStm.executeQuery();				
			while(r.next()){
				JSONObject obj = new JSONObject();
				obj.put("USER_NAME",r.getString("USER_NAME"));
				jsonArray.put(obj);
			}		
			roleUserStm.close();
			con.close();
		}catch (Exception e) {
			try{
				roleUserStm.close();
				con.close();
			}catch (Exception e1) {}
			System.out.println("Exception ###"+e);}
		return jsonArray;
	}	
}
