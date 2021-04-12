package com.smb.bizlogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;

public class CustomerUpdationBizlogic {
			
	public static String updateDateInTransDetails(HttpServletRequest request){
		Date date = new Date();
		
		PreparedStatement transactionStatement = null,paymentStatement=null,itemStatement=null;
		Connection con =null;String isSaved="";
		int cus_id = Integer.parseInt(request.getParameter("cus_id"));		
		cus_id = (cus_id >0)?cus_id:0;
		String buy_date = request.getParameter("new_date");
		buy_date = (buy_date != null)?buy_date:"";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
			date = sdf.parse(buy_date);
		}catch (Exception ee) {}
		
		try{
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false); 
			transactionStatement=con.prepareStatement(CommonConstents.TRANSACTION_DATE_UPDATE);
			
			transactionStatement.setObject(1, date);
			transactionStatement.setString(2, buy_date);
			transactionStatement.setInt(3, cus_id);
			transactionStatement.executeUpdate();
			
			paymentStatement=con.prepareStatement(CommonConstents.PAYMENT_DATE_UPDATE);
			
			paymentStatement.setString(1, buy_date);
			paymentStatement.setInt(2, cus_id);
			paymentStatement.executeUpdate();
			
			
			itemStatement=con.prepareStatement(CommonConstents.ITEM_DATE_UPDATE);
			
			itemStatement.setString(1, buy_date);
			itemStatement.setInt(2, cus_id);
			itemStatement.executeUpdate();
			
			con.commit(); 
			isSaved = "dateUpdated";
			System.out.println("["+cus_id+"] Date is updated");
		}catch (Exception e) {
			try{
				transactionStatement.close();
				con.close(); 
			}catch (Exception e1) {}
			try{System.out.println("Exception ::"+e);con.rollback();isSaved = "notsaved";}catch (Exception ex) {} 
		}
		
		
		return isSaved;
	}
	public static String getCustomerDetails(HttpServletRequest request){		
		String resultStr ="";
		PreparedStatement selectDue=null;
		Connection con = null;
		int cus_id=Integer.parseInt(request.getParameter("cus_id"));
		cus_id = (cus_id > 0)?cus_id:0; 
		try{
			con = DBConnection.getDBConnection();
			selectDue = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_SELECT_QUERY);
			selectDue.setInt(1, cus_id);
			ResultSet rs = selectDue.executeQuery();
			if(rs.isBeforeFirst()){
				while(rs.next()){
					int per_due_amt=0,due_amt=0;
					per_due_amt = rs.getInt("per_due_amt");
					due_amt = rs.getInt("due_amt");
					if(per_due_amt > due_amt)per_due_amt = due_amt;
					request.setAttribute("due_amt", due_amt);
					request.setAttribute("tot_dues", rs.getInt("tot_dues"));
					request.setAttribute("per_due_amt", per_due_amt);				
					request.setAttribute("next_due_amt", rs.getInt("next_due_amt"));
					request.setAttribute("penalty", rs.getInt("penality"));
					request.setAttribute("tot_dues", rs.getInt("tot_dues"));
					request.setAttribute("cus_name", rs.getString("cus_name"));
					request.setAttribute("phone", rs.getString("phone"));
					request.setAttribute("yes", "yes");
				}
				resultStr ="updateData";
			}
			else{
				resultStr ="NoData";
			}
		}catch (Exception e) {	
			try{
				selectDue.close();
				con.close();
			}catch (Exception e1) {	}
		System.out.println("Exception ::"+e);resultStr ="NoData";
		}
		return resultStr;
	}
	public static String getCustomerTransDetails(HttpServletRequest request){		
		String resultStr ="";
		PreparedStatement selectDue=null;
		Connection con = null;
		int cus_id=Integer.parseInt(request.getParameter("cus_id"));
		cus_id = (cus_id > 0)?cus_id:0; 
		try{
			con = DBConnection.getDBConnection();
			selectDue = con.prepareStatement(CommonConstents.TRANSACTION_DETAILS_SELECT_QUERY);
			selectDue.setInt(1, cus_id);
			ResultSet rs = selectDue.executeQuery();
			if(rs.isBeforeFirst()){
				while(rs.next()){
					request.setAttribute("cus_name", rs.getString("cus_name"));
					request.setAttribute("purchase_date", rs.getString("purchase_date"));
					request.setAttribute("yes", "yes");
				}
				resultStr ="updateData";
			}
			else{
				resultStr ="NoData";
			}
		}catch (Exception e) {	
			try{
				selectDue.close();
				con.close();
			}catch (Exception e1) {	}
		System.out.println("Exception ::"+e);resultStr ="NoData";
		}
		return resultStr;
	}
	public static String userDueCalculation(HttpServletRequest request){
		String resultStr = "";
		int tot_dues=0,penalty=0,due_amt=0,next_due_amt=0,paid_amt=0,cus_id=0,per_due_amt=0,oldPenality=0;
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
			if(request.getParameter("oldPenality") == null || request.getParameter("oldPenality").equals(""))
			{
				oldPenality=0;
			}else{
				oldPenality=Integer.parseInt(request.getParameter("oldPenality"));		
				oldPenality = (oldPenality >0)?oldPenality:0;
			}
			due_amt =Integer.parseInt(request.getParameter("due_amt"));
			due_amt = (due_amt > 0)?due_amt:0;
	        phone=request.getParameter("phone");
	        phone = (phone != null)?phone:"";
	        paid_amt=Integer.parseInt(request.getParameter("paid_amt"));
	        paid_amt = (paid_amt > 0)?paid_amt:0;
	     
	        int due_balance =0;
	        if(penalty > 0 && oldPenality == 0){
	        	if(paid_amt > penalty){
	        		due_amt = due_amt - paid_amt ;
	        		if(next_due_amt > due_amt){
	        			next_due_amt = due_amt+penalty;
	        			per_due_amt = due_amt;
	        		}else{
	        			next_due_amt = per_due_amt+penalty;
	        		}
	        	}else if(paid_amt < penalty){
	        		penalty = penalty-paid_amt;
	        		next_due_amt = per_due_amt +penalty;
	        	}
	        }else{
	        	if(paid_amt >= oldPenality){
	        		due_balance = paid_amt - oldPenality ;
	        		due_amt = due_amt - due_balance ;
	        		penalty = 0;
	        		oldPenality = 0;
	        		if(next_due_amt > due_amt){
	        			next_due_amt = due_amt+penalty;
	        			per_due_amt = due_amt;
	        		}else{
	        			next_due_amt = per_due_amt+penalty;
	        		}
	        	}else if(paid_amt <= oldPenality){
	        		penalty = oldPenality-paid_amt;
	        		next_due_amt = per_due_amt +penalty;
	        	}
	        }
	        //if  next due amount more than due amount we need to add next due amount to due amount
	        
	        //if total dues 0 and due amount is more than 0 we need to add total dues is 1
	        if(tot_dues <= 0 && due_amt > 0){
        	  tot_dues =1;
	        }
			request.setAttribute("next_due_amt",next_due_amt);
	        request.setAttribute("tot_dues",tot_dues);
	        request.setAttribute("due_amt",due_amt);
	        request.setAttribute("paid_amt",paid_amt);
	        request.setAttribute("penalty",penalty);
	        request.setAttribute("per_due_amt",per_due_amt);
	        request.setAttribute("due_date",due_date);
	        request.setAttribute("phone",phone);
	        resultStr ="dueUserCalculation";		
	        
	    }catch (Exception e) {		
	    	System.out.println("### CustomerUpdationBizlogic Exception ::"+e);
			resultStr = "dueUserNotCalculation";
	    }        
	    return resultStr;
	}
	public static String saveCustomerUpdation(HttpServletRequest request){
		 PreparedStatement dueUpdation = null,insertHistory =null,tractionRecStatus=null;
		Connection con = null;	
		String resultStr = "";
		int tot_dues=0,penalty=0,due_amt=0,next_due_amt=0,paid_amt=0,cus_id=0,bal_due=0,per_due_amt=0;
	    String cus_name="",phone="",due_date="";
	    
		cus_id=Integer.parseInt(request.getParameter("cus_id"));
		cus_id = (cus_id > 0)?cus_id:0; 
		cus_name = request.getParameter("cus_name");
		cus_name = (cus_name != null)?cus_name:"";
		phone = request.getParameter("phone");
		phone = (phone != null)?phone:"";
		due_date = request.getParameter("due_date");
		due_date = (due_date != null)?due_date:"";
		penalty = Integer.parseInt(request.getParameter("penalty"));
		penalty = (penalty > 0)?penalty:0;
		per_due_amt = Integer.parseInt(request.getParameter("per_due_amt"));
		tot_dues = Integer.parseInt(request.getParameter("tot_dues"));
		tot_dues = (tot_dues > 0)?tot_dues:0;
		next_due_amt= Integer.parseInt(request.getParameter("next_due_amt"));
		next_due_amt = (next_due_amt > 0)?next_due_amt:0;
		due_amt =Integer.parseInt(request.getParameter("due_amt"));
		due_amt = (due_amt > 0)?due_amt:0;
        phone=request.getParameter("phone");
        phone = (phone != null)?phone:"";
        paid_amt=Integer.parseInt(request.getParameter("paid_amt"));
        paid_amt = (paid_amt > 0)?paid_amt:0;
		/*if(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))
		{
			penalty=0;
		}else{
			penalty=Integer.parseInt(request.getParameter("penalty"));		
			penalty = (penalty >0)?penalty:0;
		}
		if(penalty == 0){
			next_due_amt = per_due_amt;
		}
		if(due_amt < per_due_amt){
			per_due_amt = due_amt;
			next_due_amt = due_amt;
		}*/
		if(penalty == 0 && due_amt > per_due_amt){
			if(paid_amt > next_due_amt){
				bal_due = paid_amt-next_due_amt;
			}else{
				bal_due = next_due_amt-paid_amt;
			}
	    }else{
	        bal_due = next_due_amt-paid_amt;
	    }
		
        Date payment_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{payment_date = sdf.parse(due_date);}catch (Exception ee) {}
        try{
        
        request.setAttribute("cus_id",cus_id);
        request.setAttribute("cus_name",cus_name);
		request.setAttribute("next_due_amt",next_due_amt);
        request.setAttribute("penalty",penalty);
        request.setAttribute("tot_dues",tot_dues);
        request.setAttribute("due_amt",due_amt);
        request.setAttribute("phone",phone);
        request.setAttribute("due_date",due_date);
        request.setAttribute("yes", "yes");
        request.setAttribute("paid_amt", paid_amt);
        request.setAttribute("per_due_amt", per_due_amt);
       
		con = DBConnection.getDBConnection();
		con.setAutoCommit(false);
		
		
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
			insertHistory.setInt(5,bal_due);
			
		insertHistory.executeUpdate();
		
		tractionRecStatus = con.prepareStatement(CommonConstents.TRANSACTION_REC_STATUS_UPDATE);
		tractionRecStatus.setString(1, "U");
		tractionRecStatus.setInt(2, cus_id);
		
		tractionRecStatus.executeUpdate();
		
		con.commit();  	
		resultStr ="duePaymentUpdated";			
		}catch (Exception e) {	
			try{
				dueUpdation.close();
				insertHistory.close();
				tractionRecStatus.close();
				con.close(); 
			}catch (Exception e1) {	}
			System.out.println("### CustomerUpdationBizlogic Exception ::"+e);
			try{con.rollback();resultStr = "duePaymentNotUpdated";}catch (Exception ex) {} 
		} finally{
			try{
				dueUpdation.close();
				insertHistory.close();
				tractionRecStatus.close();
				con.close(); 
			}catch (Exception e1) {	}
		}
		return resultStr;
	}
}
