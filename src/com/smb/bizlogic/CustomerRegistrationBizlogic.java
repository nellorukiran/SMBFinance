package com.smb.bizlogic;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;

public class CustomerRegistrationBizlogic {
	static String isSaved = "";
	public static String customerCreation(HttpServletRequest request){
		int cus_id=0,buy_price=0,advance=0,tot_price=0,tot_dues=0,saled_price=0,profit=0,bal_amt=0,penalty=0;		
		String cus_name="",address="",item_name="",due_time="",buy_date="",shop_name="",model_name="",phone="";		
		float due_amt=0f,per_due_amt=0f,next_due_amt=0f,intrest_amt=0f , tot_profit=0f ,doc_amt=0f;
		
		try{
			cus_id = Integer.parseInt(request.getParameter("cus_id"));  
			cus_id = (cus_id > 0)?cus_id:0;
			cus_name = request.getParameter("cus_name");
			cus_name = (cus_name != null)?cus_name:"";
			address= request.getParameter("address");
			address = (address != null)?address:"";
			buy_date = request.getParameter("buy_date");
			buy_date = (buy_date != null)?buy_date:"16-05-1985";
			shop_name= request.getParameter("shop_name");
			shop_name = (shop_name != null)?shop_name:"GEMS";
			model_name = request.getParameter("model_name");
			model_name = (model_name != null)?model_name:"";
			buy_price= Integer.parseInt(request.getParameter("buy_price"));
			buy_price = (buy_price > 0)?buy_price:0;
			saled_price= Integer.parseInt(request.getParameter("saled_price"));
			saled_price = (saled_price > 0)?saled_price:0;
			item_name = request.getParameter("item_name");
			item_name = (item_name != null)?item_name:"";
			advance=Integer.parseInt(request.getParameter("advance"));
			advance = (advance > 0)?advance:0;
			tot_dues =Integer.parseInt(request.getParameter("tot_dues"));
			tot_dues = (tot_dues >0)?tot_dues:0;
			if(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))
			{
				penalty=0;
			}else{
				penalty=Integer.parseInt(request.getParameter("penalty"));		
				penalty = (penalty >0)?penalty:0;
			}
			due_time = request.getParameter("due_time");
			due_time = (due_time != null)?due_time:"";
			phone= request.getParameter("phone");
			phone = (phone != null)?phone:"";
			if(cus_id < 0){
				return "errorcal";
			}

			if(saled_price >= buy_price){
				tot_price=saled_price;
				bal_amt=tot_price-advance;
				profit=saled_price-buy_price;	
				if(penalty == 0){
					due_amt=((bal_amt*0.02f)*tot_dues)+bal_amt+penalty;
					intrest_amt = ((bal_amt*0.02f)*tot_dues);
					per_due_amt=due_amt/tot_dues;
					next_due_amt=per_due_amt;
				}else{
					due_amt=((bal_amt*0.02f)*tot_dues)+bal_amt;
					intrest_amt = ((bal_amt*0.02f)*tot_dues);
					per_due_amt=due_amt/tot_dues;
					next_due_amt=per_due_amt+penalty;
				}
				if(bal_amt > 6000){
					doc_amt = (bal_amt / 1000)*50; 
				}else{
					doc_amt = 300;
				}
				tot_profit = intrest_amt + doc_amt + profit ;
		
				request.setAttribute("tot_price",tot_price);
				request.setAttribute("due_amt",due_amt);
				request.setAttribute("per_due_amt",per_due_amt);
				request.setAttribute("profit",profit);
				request.setAttribute("bal_amt",bal_amt);
				request.setAttribute("next_due_amt",next_due_amt);
				request.setAttribute("intrest_amt",intrest_amt);
				request.setAttribute("tot_profit",tot_profit);
				request.setAttribute("doc_amt",doc_amt);
				
			}else{
				return "errorcal";
			}
		}catch (Exception e) {
			System.out.println("Exception in CustomerRegistrationBizlogic customerCreation()"+e);
		}
		return "userDataRev";
	}
	public static String customerCreationSave(HttpServletRequest request){
		
		PreparedStatement transactionStatement = null,paymentDetailsStatement=null,itemDetailsStatement=null;
		Connection con =null;
		Date date = new Date();
		int cus_id=0,buy_price=0,advance=0,tot_dues=0,saled_price=0,penalty=0;
		String cus_name="",address="",item_name="",due_time="",buy_date="",shop_name="",model_name="",phone="";		
		float doc_amt=0f,next_due_amt=0f,due_amt=0f,profit=0f,tot_price=0f,bal_amt=0f,per_due_amt=0f,intrest_amt=0f,tot_profit=0f;
		
		cus_id = Integer.parseInt(request.getParameter("cus_id"));		
		cus_id = (cus_id >0)?cus_id:0;
		cus_name = request.getParameter("cus_name");
		cus_name = (cus_name != null)?cus_name:"";
		address= request.getParameter("address");
		address = (address != null)?address:"";
		if(address.length() >20)address.substring(0,20);
		buy_date = request.getParameter("buy_date");
		buy_date = (buy_date != null)?buy_date:"";
		shop_name= request.getParameter("shop_name");
		shop_name = (shop_name != null)?shop_name:"";
		model_name = request.getParameter("model_name");
		model_name = (model_name != null)?model_name:"";
		buy_price= Integer.parseInt(request.getParameter("buy_price"));
		buy_price = (buy_price >0)?buy_price:0;
		saled_price= Integer.parseInt(request.getParameter("saled_price"));
		saled_price = (saled_price >0)?saled_price:0;
		item_name = request.getParameter("item_name");
		item_name = (item_name != null)?item_name:"";
		advance=Integer.parseInt(request.getParameter("advance"));
		advance = (advance >0)?advance:0;
		tot_dues =Integer.parseInt(request.getParameter("tot_dues"));
		tot_dues = (tot_dues >0)?tot_dues:0;
		due_time = request.getParameter("due_time");
		due_time = (due_time != null)?due_time:"";
		phone= request.getParameter("phone");
		phone = (phone != null)?phone:"";
		if(request.getParameter("penalty") == null || request.getParameter("penalty").equals(""))
		{
			penalty=0;
		}else{
			penalty=Integer.parseInt(request.getParameter("penalty"));		
			penalty = (penalty >0)?penalty:0;
		}
		tot_price= Float.parseFloat(request.getParameter("saled_price"));
		tot_price = (tot_price >0)?tot_price:0;
		bal_amt= Float.parseFloat(request.getParameter("bal_amt"));	
		bal_amt = (bal_amt >0)?bal_amt:0;
		due_amt= Float.parseFloat(request.getParameter("due_amt"));	
		due_amt = (due_amt >0)?due_amt:0;
		per_due_amt= Float.parseFloat(request.getParameter("per_due_amt"));	
		per_due_amt = (per_due_amt >0)?per_due_amt:0;
		profit= Float.parseFloat(request.getParameter("profit"));	
		profit = (profit >0)?profit:0;
		next_due_amt= Float.parseFloat(request.getParameter("next_due_amt"));
		next_due_amt = (next_due_amt >0)?next_due_amt:0;
		intrest_amt= Float.parseFloat(request.getParameter("intrest_amt"));		
		intrest_amt = (intrest_amt >0)?intrest_amt:0;
		tot_profit= Float.parseFloat(request.getParameter("tot_profit"));	
		tot_profit = (tot_profit >0)?tot_profit:0;
		doc_amt= Float.parseFloat(request.getParameter("doc_amt"));
		doc_amt = (doc_amt >0)?doc_amt:0;
		
		//For Any error exist 
		request.setAttribute("tot_price",tot_price);
		request.setAttribute("due_amt",due_amt);
		request.setAttribute("per_due_amt",per_due_amt);
		request.setAttribute("profit",profit);
		request.setAttribute("bal_amt",bal_amt);
		request.setAttribute("next_due_amt",next_due_amt);
		request.setAttribute("intrest_amt",intrest_amt);
		request.setAttribute("tot_profit",tot_profit);
		request.setAttribute("doc_amt",doc_amt);
		//For Any error exist 
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{date = sdf.parse(buy_date);}catch (Exception ee) {}
		try{
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false); 
			//System.out.println(CommonConstents.TRANSACTION_DETAILS_INSERT_QUERY);
			transactionStatement=con.prepareStatement(CommonConstents.TRANSACTION_DETAILS_INSERT_QUERY);
			
				transactionStatement.setInt(1, cus_id);	
				transactionStatement.setString(2, cus_name);
				transactionStatement.setString(3, address);
				transactionStatement.setString(4, buy_date);
				transactionStatement.setString(5, due_time);
				transactionStatement.setString(6, item_name);
				transactionStatement.setString(7, shop_name);
				transactionStatement.setString(8, model_name);
				transactionStatement.setInt(9, buy_price);
				transactionStatement.setFloat(10, profit);
				transactionStatement.setFloat(11, tot_price);
				transactionStatement.setInt(12, advance);
				transactionStatement.setFloat(13, bal_amt);			
				transactionStatement.setFloat(14, due_amt);
				transactionStatement.setInt(15, tot_dues);
				transactionStatement.setFloat(16, per_due_amt);
				transactionStatement.setInt(17, penalty);
				transactionStatement.setString(18, phone);
				transactionStatement.setFloat(19, intrest_amt);			
				transactionStatement.setFloat(20, tot_profit);
				transactionStatement.setFloat(21, doc_amt);
				transactionStatement.setObject(22, date);
				transactionStatement.setString(23, "I");
			
			transactionStatement.executeUpdate();
			
			paymentDetailsStatement=con.prepareStatement(CommonConstents.PAYMENT_DETAILS_INSERT_QUERY);
			
				paymentDetailsStatement.setInt(1, cus_id);	
				paymentDetailsStatement.setString(2, cus_name);
				paymentDetailsStatement.setString(3, address);
				paymentDetailsStatement.setString(4, item_name);	
				paymentDetailsStatement.setString(5, buy_date);
				paymentDetailsStatement.setString(6, due_time);
				paymentDetailsStatement.setFloat(7, due_amt);	
				paymentDetailsStatement.setInt(8, tot_dues);
				paymentDetailsStatement.setFloat(9, per_due_amt);
				paymentDetailsStatement.setFloat(10, next_due_amt);
				paymentDetailsStatement.setInt(11, penalty);
				paymentDetailsStatement.setString(12, phone);
				paymentDetailsStatement.setString(13, "I");
			
			paymentDetailsStatement.executeUpdate();
			
			itemDetailsStatement=con.prepareStatement(CommonConstents.ITEM_DETAILS_INSERT_QUERY);
			
				itemDetailsStatement.setInt(1, cus_id);	
				itemDetailsStatement.setString(2, cus_name);
				itemDetailsStatement.setString(3, buy_date);
				itemDetailsStatement.setString(4, item_name);	
				itemDetailsStatement.setString(5, shop_name);
				itemDetailsStatement.setString(6, model_name);
				itemDetailsStatement.setInt(7, buy_price);	
				itemDetailsStatement.setInt(8, saled_price);
				itemDetailsStatement.setFloat(9, profit);
			
			itemDetailsStatement.executeUpdate();
			
			con.commit(); 
			isSaved = "saved";
			System.out.println("["+cus_id+"] Customer Inserted");
		}catch (Exception e) {
			try{
				transactionStatement.close();paymentDetailsStatement.close();itemDetailsStatement.close();
				con.close(); 
			}catch (Exception e1) {}
			try{System.out.println("Exception ::"+e);con.rollback();isSaved = "notsaved";}catch (Exception ex) {} 
		}
		return isSaved;
	}
	public static String adminRegistration(HttpServletRequest request){
		String user_type=request.getParameter("user_type");
		user_type = (user_type != null)?user_type:"";
		String user_name=request.getParameter("user_name");
		user_name = (user_name != null)?user_name:"";
		String first_name=request.getParameter("first_name");
		first_name = (first_name != null)?first_name:"";
		String user_password=request.getParameter("user_password");
		user_password = (user_password != null)?user_password:"";
		String confirm_password=request.getParameter("confirm_password");
		confirm_password = (confirm_password != null)?confirm_password:"";
		Connection con = null;
		PreparedStatement insertAdminCreation = null;
			try{
				con = DBConnection.getDBConnection();	
				con.setAutoCommit(false); 
				insertAdminCreation = con.prepareStatement(CommonConstents.INSERT_ADMIN_CREATION_QUERY);	
				insertAdminCreation.setString(1, user_type);	
				insertAdminCreation.setString(2, user_name);
				insertAdminCreation.setString(3, user_password);
				insertAdminCreation.setString(4, confirm_password);	
				insertAdminCreation.setString(5, first_name);
				//System.out.println(CommonConstents.INSERT_ADMIN_CREATION_QUERY);
				insertAdminCreation.executeUpdate();
				con.commit();  
				isSaved = "adminRegistration";
				System.out.println("Admin Creation Successfully");
			}catch (Exception e) {
				try{
					insertAdminCreation.close();
					con.close();
				}catch(Exception e1){
					
				}
				System.out.println("Admin Creation not Successfully");
				System.out.println(e);
			}
		return isSaved;
	}
	public static String viewAdminUsers(HttpServletRequest request){
		Connection con = null;
		ResultSet userDetailsRs=null;
		PreparedStatement userDetailsQury=null;
		String resultType = "";
		try{
			con = DBConnection.getDBConnection();	
			userDetailsQury = con.prepareStatement(CommonConstents.SELECT_ADMIN_USER_ROLES);	
			userDetailsRs = userDetailsQury.executeQuery();
			if(userDetailsRs.isBeforeFirst()){
				if(userDetailsRs != null)request.setAttribute("resultset", userDetailsRs);
				resultType = "viewAdminUsers";
			}else{
				resultType = "noViewAdminUsers";
			}
		}catch (Exception e) {
			try{
				userDetailsQury.close();
				con.close();
			}catch (Exception e1) {}
			System.out.println(e);
			resultType = "noViewAdminUsers";
		}
		return resultType;
	}
	public static String userAdminDelete(HttpServletRequest request){
		String isDelete = "",userType="",user_name ="";
		PreparedStatement deleteQuy=null;
		user_name= request.getParameter("user_name");
		user_name = (user_name != null )?user_name:"";
		userType= request.getParameter("user_type");
		userType = (userType != null)?userType:"";
		Connection con = null;
		try{
			int userDeleted =0;
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			
				deleteQuy = con.prepareStatement(CommonConstents.ADMIN_USER_DELETE_QUERY);
				deleteQuy.setString(1, userType);
				deleteQuy.setString(2, user_name);
				userDeleted = deleteQuy.executeUpdate();
				
				if(userDeleted ==1){
					con.commit();
					isDelete ="deleted";
				}else{
					isDelete ="notdeleted";
				}
				
		}catch(Exception e){
			try{
				con.rollback();
				deleteQuy.close();
				con.close();
			}catch (Exception ex) {
				System.out.println("UserDeleteBizlogic Exception ex ::"+ex);
			}
		}
		return isDelete;
	}
	public static String checkIsValidUser(HttpServletRequest request){
		String username="",password="",isValid="",userPassword="",userType="",user_name="",first_name="";
		username= request.getParameter("username");
		username = (username != null)?username.trim():"";
		password = request.getParameter("password");
		password = (password != null)?password.trim():"";
		
		Connection con = null;
		PreparedStatement selectUserProfile = null;
		
		
			try{
				con = DBConnection.getDBConnection();	
				//con.setAutoCommit(false); 
				selectUserProfile = con.prepareStatement(CommonConstents.SELECT_SMB_USER_QUERY);	
				selectUserProfile.setString(1, username);	
				ResultSet rs = selectUserProfile.executeQuery();
				if(rs.next()){
					userPassword = rs.getString("USER_PASSWORD");
					userPassword = (userPassword != null)?userPassword.trim():"";
					userType = rs.getString("USER_TYPE");
					userType = (userType != null)?userType.trim():"";
					user_name = rs.getString("USER_NAME");
					user_name = (user_name != null)?user_name.trim():"";
					first_name = rs.getString("FIRST_NAME");
					first_name = (first_name != null)?first_name.trim():"";
					request.setAttribute("USER_TYPE", userType);
					request.setAttribute("FIRST_NAME", first_name);
				}
				if(userPassword.length() > 0) {
					if(userPassword.equals(password)){
						isValid = "validUser";
					}else{
						isValid = "notMatched";
					}
				}else{
					isValid = "notValid";
				}
			}catch (Exception e) {
				try{
					selectUserProfile.close();
					con.close();
				}catch (Exception e1) {}
				System.out.println("##### CustomerRegistrationBizlogic Exception:::"+e);
			}
		return isValid;
	}
}
