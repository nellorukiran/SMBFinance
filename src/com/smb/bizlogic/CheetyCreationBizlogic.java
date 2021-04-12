package com.smb.bizlogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;
import com.smb.util.CommonUtil;
 
public class CheetyCreationBizlogic {
	public static String createCheetyType(HttpServletRequest request){
		Connection con = null;
		PreparedStatement profitDetailsQury = null;
		String displayMsg = "";
		try{
			con = DBConnection.getDBConnection();					
			profitDetailsQury = con.prepareStatement("");	
			profitDetailsQury.setString(1, "");
			ResultSet r = profitDetailsQury.executeQuery();
			request.setAttribute("resultset", r);
			profitDetailsQury.close();
			con.close();
			displayMsg = "gotProfiltDetails";
		}catch (Exception e) {
			try{
				
				profitDetailsQury.close();
				con.close();
			}
			catch (Exception e1) {}
			displayMsg = "notGotProfiltDetails";}
		return displayMsg;
	}
	/*public static int getCheetyId(){
		int ch_id = 0;
		Format formatter=null;
		String formatedDate=null;
		java.util.Date date = new java.util.Date();
		formatter = new SimpleDateFormat("dd-MM-yy");
		formatedDate =formatter.format(date);
		formatedDate = formatedDate.replace("-", "");
		ch_id = Integer.parseInt(formatedDate);
		return ch_id;
	}*/
	public static String  insertCheetyDetails(HttpServletRequest request){
		PreparedStatement insertCheety = null,inserSeqMember=null;
		Connection con =null;
		String isInserted = "";
		
		try{
			int CH_ID = Integer.parseInt(request.getParameter("CH_ID"));
			CH_ID = (CH_ID > 0)? CH_ID : 0;
			String cheetyName = request.getParameter("CH_NAME");
			cheetyName = (cheetyName != null)?cheetyName : "";
			int cheetyAmt = Integer.parseInt(request.getParameter("CH_RANGE"));
			cheetyAmt = (cheetyAmt > 0)? cheetyAmt : 0;
			String ownerName = request.getParameter("OWNER_NAME");
			ownerName = (ownerName != null)?ownerName : "";
			String cheetyDate = request.getParameter("CH_DATE");
			cheetyDate = (cheetyDate != null)?cheetyDate : "";
			int totMembers = Integer.parseInt(request.getParameter("TOT_MEMBERS"));
			totMembers = (totMembers > 0)? totMembers : 0;
			int totMonths = Integer.parseInt(request.getParameter("TOT_MONTHS"));
			totMonths = (totMonths > 0)? totMonths : 0;
			int pataNumber = 0;
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try{
				date = sdf.parse(cheetyDate);				 
			}catch (Exception ee) {}
			
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false); 
			insertCheety = con.prepareStatement(CommonConstents.INSERT_CHEETY_PACKAGES_QUERY);
			insertCheety.setInt(1, CH_ID);
			insertCheety.setString(2, cheetyName);
			insertCheety.setInt(3, cheetyAmt);
			insertCheety.setString(4, ownerName);
			insertCheety.setObject(5, date);
			insertCheety.setInt(6, totMembers);
			insertCheety.setInt(7, totMonths);
			insertCheety.setInt(8, pataNumber);
			
			insertCheety.executeUpdate();
			
			inserSeqMember = con.prepareStatement(CommonConstents.UPDATE_CHEETY_SEQ_CHEETY_IDS_QUERY);
			inserSeqMember.setInt(1, CH_ID);
			inserSeqMember.setInt(2, CH_ID-1);
			
			inserSeqMember.executeUpdate();
			
			con.commit();  
			insertCheety.close();
			inserSeqMember.close();
			con.close(); 
			isInserted = "inserted";
		}catch (Exception e) {
			try{
				System.out.println("Exception ::"+e);con.rollback();isInserted = "notInserted";
				insertCheety.close();
				inserSeqMember.close();
				con.close(); 
			}catch (Exception ex) {} 
		}
		return isInserted;
	}
	public static String  insertCheetyMemberDetails(HttpServletRequest request){
		PreparedStatement insertCheety = null,inserSeqMember=null;
		Connection con =null;
		String isInserted = "";
		
		try{
			int CH_ID = Integer.parseInt(request.getParameter("CH_ID"));
			CH_ID = (CH_ID > 0)? CH_ID : 0;
			int MEM_ID = Integer.parseInt(request.getParameter("MEM_ID"));
			MEM_ID = (MEM_ID > 0)? MEM_ID : 0;
			String MEM_NAME = request.getParameter("MEM_NAME");
			MEM_NAME = (MEM_NAME != null)?MEM_NAME : "";
			String MEM_TYPE = request.getParameter("MEM_TYPE");
			MEM_TYPE = (MEM_TYPE !=null)? MEM_TYPE : "";
			String ADDRESS = request.getParameter("ADDRESS");
			ADDRESS = (ADDRESS != null)?ADDRESS : "";
			String MEM_DATE = request.getParameter("MEM_DATE");
			MEM_DATE = (MEM_DATE != null)?MEM_DATE : "";
			String PHONE = request.getParameter("PHONE");
			PHONE = (PHONE != null)?PHONE : "";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try{
				date = sdf.parse(MEM_DATE);				 
			}catch (Exception ee) {}
			int pataNumber = 0;
			String status = "No";
			
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false); 
			insertCheety = con.prepareStatement(CommonConstents.INSERT_CHEETY_MEMBERS_QUERY);
			insertCheety.setInt(1, CH_ID);
			insertCheety.setInt(2, MEM_ID);
			insertCheety.setString(3, MEM_NAME);
			insertCheety.setString(4, ADDRESS);
			insertCheety.setString(5, PHONE);
			insertCheety.setObject(6, date);
			insertCheety.setInt(7, pataNumber);
			insertCheety.setString(8, status);
			
			insertCheety.executeUpdate();
			
			inserSeqMember = con.prepareStatement(CommonConstents.UPDATE_CHEETY_SEQ_MEM_IDS_QUERY);
			inserSeqMember.setInt(1, MEM_ID);
			inserSeqMember.setInt(2, MEM_ID-1);
			System.out.println("MEM_ID ::["+MEM_ID+"] MEM_ID ::["+(MEM_ID-1)+"]");
			
			inserSeqMember.executeUpdate();
			
			con.commit();  
			insertCheety.close();
			inserSeqMember.close();
			con.close(); 
			isInserted = "inserted";
		}catch (Exception e) {
			try{
				System.out.println("Exception ::"+e);con.rollback();
				insertCheety.close();
				inserSeqMember.close();
				con.close(); 
				isInserted = "notInserted";
				}catch (Exception ex) {} 
		}
		return isInserted;
	}
	public static int getCheetyUpdate(){
		int ch_id = 0;
		Format formatter=null;
		String formatedDate=null;
		java.util.Date date = new java.util.Date();
		formatter = new SimpleDateFormat("dd-MM-yy");
		formatedDate =formatter.format(date);
		formatedDate = formatedDate.replace("-", "");
		ch_id = Integer.parseInt(formatedDate);
		return ch_id;
	}
	public static int getCheetyMemberId(){
		int mem_id = 0 ,increMemId=0;
		Connection con = null;
		PreparedStatement getMemberId = null;
		try{ 
			con = DBConnection.getDBConnection();
			getMemberId = con.prepareStatement(CommonConstents.SELECT_CHEETY_SEQ_IDS_QUERY);
			ResultSet r = getMemberId.executeQuery();	
			while(r.next()){
				mem_id = r.getInt("MEMBER_ID");
			}
			increMemId = mem_id+1;	
		}catch (Exception e) {
			System.out.println(e);
		}
		return increMemId;
	}
	public static int getCheetyId(){
		int CHEETY_ID = 0 ;
		Connection con = null;
		PreparedStatement getMemberId = null;
		try{ 
			con = DBConnection.getDBConnection();
			getMemberId = con.prepareStatement(CommonConstents.SELECT_CHEETY_SEQ_IDS_QUERY);
			ResultSet r = getMemberId.executeQuery();	
			while(r.next()){
				CHEETY_ID = r.getInt("CHEETY_ID");
			}	
			getMemberId.close();
			con.close();
		}catch (Exception e) {
			try{
				getMemberId.close();
				con.close();
			}catch (Exception e1) {}
			System.out.println(e);
		}
		return CHEETY_ID+1;
	}
	public static JSONArray getCheetyIdList(){		
		Connection con = null;
		PreparedStatement profitDetailsQury = null;
		JSONArray jsonArray = new JSONArray();
		try{ 
			con = DBConnection.getDBConnection();
			profitDetailsQury = con.prepareStatement(CommonConstents.SELECT_CHEETY_IDS_QUERY);	
			ResultSet r = profitDetailsQury.executeQuery();	
			while(r.next()){
				JSONObject obj = new JSONObject();
				obj.put("CHEETY_ID",r.getInt("CHEETY_ID"));
				obj.put("CHEETY_NAME",r.getString("CHEETY_NAME"));
				jsonArray.put(obj);
			}	
			profitDetailsQury.close();
			con.close();
		}catch (Exception e) {
			try{
				profitDetailsQury.close();
				con.close();
			}
			catch (Exception e1) {}
			System.out.println("Exception ###"+e);
		}
		return jsonArray;
	}
	public static JSONArray createCheetyMemberIdList(String ch_code){		
		Connection con = null;
		PreparedStatement profitDetailsQury = null;
		JSONArray jsonArray = new JSONArray();
		try{ 
			con = DBConnection.getDBConnection();					
			profitDetailsQury = con.prepareStatement(CommonConstents.SELECT_CHEETY_UPDATE_DETAILS_QUERY);	
			profitDetailsQury.setInt(1, Integer.parseInt(ch_code));
			ResultSet r = profitDetailsQury.executeQuery();				
			while(r.next()){
				JSONObject obj = new JSONObject();
				obj.put("MEM_NAME",r.getString("MEMBER_NAME"));
				obj.put("MEM_ID",r.getInt("MEMBER_ID"));
				jsonArray.put(obj);
			}		
			profitDetailsQury.close();
			con.close();
		}catch (Exception e) {
			try{
				profitDetailsQury.close();
				con.close();
			}catch (Exception e1) {}
			System.out.println("Exception ###"+e);
			}
		return jsonArray;
	}		
	public static String cheetyCalculate(HttpServletRequest request){							
		String resultType = CommonUtil.getCheetyDueData(request);
		if(!resultType.equals("dataCalculated")){
			resultType = "dataNotCalculated";
		}else{
			resultType = "dataCalculated";			
		}
		return resultType;
	}
	public static String cheetyUpdate(HttpServletRequest request){	
		String resultType ="";	
		Connection con = null;
		PreparedStatement profitDetailsQury = null,getPataNumber=null,updateCheetyDetails=null;
		try{ 
					
			int CH_CODE = Integer.parseInt(request.getParameter("CH_CODE"));
			CH_CODE = (CH_CODE > 0)?CH_CODE:0;
			int MEM_CODE = Integer.parseInt(request.getParameter("MEM_CODE"));
			MEM_CODE = (MEM_CODE > 0)?MEM_CODE:0;
			int CHEETY_AMOUNT = Integer.parseInt(request.getParameter("CHEETY_AMOUNT"));
			CHEETY_AMOUNT = (CHEETY_AMOUNT > 0 )?CHEETY_AMOUNT:0;
			int TOT_MONTHS = Integer.parseInt(request.getParameter("TOT_MONTHS"));
			TOT_MONTHS = (TOT_MONTHS > 0)?TOT_MONTHS:0;
			int TOT_MEM = Integer.parseInt(request.getParameter("TOT_MEM"));
			TOT_MEM = (TOT_MEM >0)?TOT_MEM:0;
			float TOT_DUE = Float.parseFloat(request.getParameter("TOT_DUE"));
			TOT_DUE = (TOT_DUE > 0)?TOT_DUE:0;
			float DUE_AMOUNT = Float.parseFloat(request.getParameter("DUE_AMOUNT"));
			DUE_AMOUNT = (DUE_AMOUNT > 0)?DUE_AMOUNT:0;
			int PATA_AMOUNT = Integer.parseInt(request.getParameter("PATA_AMOUNT"));
			PATA_AMOUNT = (PATA_AMOUNT > 0)?PATA_AMOUNT:0;
			float TOPU_AMOUNT = Float.parseFloat(request.getParameter("TOPU_AMOUNT"));
			TOPU_AMOUNT = (TOPU_AMOUNT > 0)?TOPU_AMOUNT:0;
			String PATA_DATE = request.getParameter("PATA_DATE");
			PATA_DATE = (PATA_DATE != null)?PATA_DATE:"";
			
	
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			
			getPataNumber = con.prepareStatement(CommonConstents.SELECT_PATA_NUMER_QUERY);
			getPataNumber.setInt(1, CH_CODE);
			
			ResultSet rs = getPataNumber.executeQuery();
			
			int PATA_NUMBER = 0;
			float TAKE_OF_AMOUNT=0;
			while(rs.next()){
				PATA_NUMBER = rs.getInt(1);
			}
			PATA_NUMBER += 1;  
			TAKE_OF_AMOUNT =DUE_AMOUNT*20; 
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try{
				date = sdf.parse(PATA_DATE);				 
			}catch (Exception ee) {}
			
			updateCheetyDetails = con.prepareStatement(CommonConstents.UPDATE_PATA_NUMBER_QUERY);
			updateCheetyDetails.setInt(1, PATA_NUMBER);
			updateCheetyDetails.setInt(2, CH_CODE); 
			
			updateCheetyDetails.executeUpdate();
			
			//System.out.println(CommonConstents.UPDATE_CHEETY_ID_DETAILS_QUERY);
			
			profitDetailsQury = con.prepareStatement(CommonConstents.UPDATE_CHEETY_MEMBERS_DETAILS_QUERY);					
						
			profitDetailsQury.setString(1, "YES");			
			profitDetailsQury.setFloat(2, DUE_AMOUNT);			
			profitDetailsQury.setObject(3, date);			
			profitDetailsQury.setFloat(4, TOT_DUE);			
			profitDetailsQury.setFloat(5, TOPU_AMOUNT);			
			profitDetailsQury.setInt(6, PATA_AMOUNT);			
			profitDetailsQury.setInt(7, PATA_NUMBER);	
			profitDetailsQury.setFloat(8, TAKE_OF_AMOUNT);
			profitDetailsQury.setInt(9, CH_CODE);			
			profitDetailsQury.setInt(10, MEM_CODE);			
			
			profitDetailsQury.executeUpdate();

			con.commit();  
			profitDetailsQury.close();
			getPataNumber.close();
			updateCheetyDetails.close();
			con.close(); 
			resultType = "cheetyUpdate";	
		}catch (Exception e) {
			try{
				con.rollback();
				profitDetailsQury.close();
				getPataNumber.close();
				updateCheetyDetails.close();
				con.close(); 
				resultType = "cheetyNotUpdate";
			}
			catch (Exception ex) {} 
			System.out.println("Exception ###"+e);
		}		
		return resultType;
	}
	public static String getCheetyAmount(int che_id){		
		Connection con = null;
		PreparedStatement profitDetailsQury = null;
		
		int cheetyAmount = 0,tot_months= 0,tot_mem=0;
		String cheetyDetails = "";
		try{ 
			con = DBConnection.getDBConnection();					
			profitDetailsQury = con.prepareStatement(CommonConstents.SELECT_CHEETY_PACKAGE_AMOUNT_QUERY);	
			profitDetailsQury.setInt(1, che_id);
			ResultSet r = profitDetailsQury.executeQuery();	
			while(r.next()){
				System.out.println("getCheetyAmount ()");
				cheetyAmount = r.getInt("CHEETY_AMOUNT");
				tot_months = r.getInt("TOT_MONTHS");
				tot_mem = r.getInt("TOT_MEMBERS");
				cheetyDetails = ""+cheetyAmount+"#"+tot_months+"#"+tot_mem+"" ;
			}	
			profitDetailsQury.close();
			con.close();
		}catch (Exception e) {
			try{
				profitDetailsQury.close();
				con.close();
			}catch(Exception e1) {
				
			}
			System.out.println("Exception ###"+e);
			}
		return cheetyDetails;
	} 
	public static ResultSet viewCheetyDetails(int che_id,HttpServletRequest request){		
		Connection con = null;
		PreparedStatement profitDetailsQury = null,profitDetailsQury1 = null;
		ResultSet rs = null,rs1 = null;
		try{ 
			con = DBConnection.getDBConnection();					
			profitDetailsQury = con.prepareStatement(CommonConstents.SELECT_CHEETY_MEMBERS_AMOUNT_QUERY);
			profitDetailsQury.setInt(1, che_id);
			rs = profitDetailsQury.executeQuery();	
			profitDetailsQury1 = con.prepareStatement(CommonConstents.SELECT_CHEETY_PACKAGE_AMOUNT_QUERY);
			profitDetailsQury1.setInt(1, che_id);
			rs1 = profitDetailsQury1.executeQuery();
			while(rs1.next()){
				request.setAttribute("CreateDate", rs1.getString("CHEETY_DATE"));
				request.setAttribute("OwnerName", rs1.getString("OWNER_NAME"));
				request.setAttribute("CheetyAmt", rs1.getInt("CHEETY_AMOUNT"));
				request.setAttribute("TotMonths", rs1.getInt("TOT_MONTHS"));
				request.setAttribute("TotMembers", rs1.getInt("TOT_MEMBERS"));
			}
			profitDetailsQury.close();
			profitDetailsQury1.close();
			con.close();
		}catch (Exception e) {
			try{
				profitDetailsQury.close();
				profitDetailsQury1.close();
				con.close();
			}catch (Exception e1) {}
			System.out.println("Exception ###"+e);
			}
		return rs; 
	}
}
