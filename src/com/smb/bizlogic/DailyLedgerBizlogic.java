package com.smb.bizlogic;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;
import com.smb.util.CommonUtil;

public class DailyLedgerBizlogic {
	public static String viewLedgerBetweenDates(String fromDate,String toDate,HttpServletRequest request){
		Connection con = null;
		PreparedStatement ledgerBetweenDetailsQury = null;
		String displayMsg = "";
		if(fromDate.length() > 0 && toDate.length() > 0){
			try{
				con = DBConnection.getDBConnection();					
				ledgerBetweenDetailsQury = con.prepareStatement(CommonConstents.GET_DAILY_LEDGER_DETAILS_BETWEENDATE);
				ledgerBetweenDetailsQury.setString(1, fromDate);
				ledgerBetweenDetailsQury.setString(2, toDate);
				ResultSet r = ledgerBetweenDetailsQury.executeQuery();
				if (!r.isBeforeFirst() ) {
					request.setAttribute("resultType", "NO");
				}
				else{
					request.setAttribute("resultset", r);
					request.setAttribute("resultType", "YES");
					
				}
				ledgerBetweenDetailsQury.close();
				con.close();
				displayMsg = "getLedgerBetweenDates";
			}catch (Exception e) {
				try{
				ledgerBetweenDetailsQury.close();
				con.close();
				}catch (Exception e1) {}
				System.out.println("Exception");displayMsg = "noGetLedgerBetweenDates";
				}
		}		
		return displayMsg;
	}
	public static String  insertDailyLedgerDetails(HttpServletRequest request){
		PreparedStatement insertLedgerStm = null;
		Connection con =null;
		String isInserted = "";
		
		try{
			int cus_id = Integer.parseInt(request.getParameter("cus_id"));
			cus_id = (cus_id > 0)? cus_id : 0;
			String trans_type = request.getParameter("trans_type");
			trans_type = (trans_type != null)?trans_type : "";
			String trans_mode = request.getParameter("trans_mode");
			trans_mode = (trans_mode != null)? trans_mode : "";
			String trans_date = request.getParameter("trans_date");
			trans_date = (trans_date != null)?trans_date : "";
			int trans_amt = Integer.parseInt(request.getParameter("trans_amt"));
			trans_amt = (trans_amt > 0)? trans_amt : 0;
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try{
				date = sdf.parse(trans_date);				 
			}catch (Exception ee) {}
			
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false); 
			insertLedgerStm = con.prepareStatement(CommonConstents.DAILY_LEDGER_INSERT_QUERY);
			insertLedgerStm.setInt(1, cus_id);
			insertLedgerStm.setObject(2, date);
			insertLedgerStm.setString(3, trans_type);
			insertLedgerStm.setString(4, trans_mode);
			insertLedgerStm.setInt(5, trans_amt);
			//System.out.println("Query-->"+CommonConstents.DAILY_LEDGER_INSERT_QUERY);
			insertLedgerStm.executeUpdate();
			
			con.commit();
			insertLedgerStm.close();
			con.close(); 
			isInserted = "inserted";
		}catch (Exception e) {
			try{System.out.println("Exception ::"+e);con.rollback();insertLedgerStm.close();
			con.close(); isInserted = "notInserted";}catch (Exception ex) {} 
		}
		return isInserted;
	}
	public static String downloadLedgerDeatils(String folderName,String inputVal){
		String displayMsg="";
		String TITLE ="SREE MAHALAKSHMI BINDUHASINI FINANCE";
		Connection con = null;
		PreparedStatement selectLedgerDetails = null;
		try{
			con = DBConnection.getDBConnection();
			if(inputVal.length() > 0){
				
				/*if("ShopName".equals(inputVal)){
					selectProductDetails = con.prepareStatement(CommonConstents.SELECT_PRODUCT_DETAILS_ORDERBY_SHOP);
				}
				if("ProductName".equals(inputVal)){
					selectProductDetails = con.prepareStatement(CommonConstents.SELECT_PRODUCT_DETAILS_ORDERBY_ITEM);
				}*/
				
				selectLedgerDetails = con.prepareStatement(CommonConstents.SELECT_LEDGER_DETAILS_ORDERBY_DATE);
				
				ResultSet rs = selectLedgerDetails.executeQuery();
				
				HSSFWorkbook hwb = new HSSFWorkbook();
				HSSFSheet sheet = hwb.createSheet("SMB Finance");
				//HSSFFont headerFont = hwb.createFont(); 
				HSSFRow rowTitle = sheet.createRow((int)0);
				
				//rowTitle.createCell((short)0).setCellValue(TITLE);
				
				
				HSSFRow rowhead = sheet.createRow((int)2);
				rowhead.createCell((short) 0).setCellValue("S NO");
				rowhead.createCell((short) 1).setCellValue("CUS ID");
				rowhead.createCell((short) 2).setCellValue("TRANS DATE");
				rowhead.createCell((short) 3).setCellValue("TRANS TYPE");
				rowhead.createCell((short) 4).setCellValue("TRANS MODE");
				rowhead.createCell((short) 5).setCellValue("DEBIT");
				rowhead.createCell((short) 6).setCellValue("CREDIT");
								
				int index=3;
				int sno=0;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				//String debt_amtStr="",credit_amtStr="";
				int credit_amt=0,debt_amt=0,tot_dedit=0,tot_credit=0;
				while(rs.next()) 
				{
					sno++;
					
					HSSFRow row = sheet.createRow((int)index);
					row.createCell((short) 0).setCellValue(sno);
					row.createCell((short) 1).setCellValue(rs.getInt(1));
					row.createCell((short) 2).setCellValue(sdf.format(rs.getDate(2)));
					row.createCell((short) 3).setCellValue(rs.getString(3));
					row.createCell((short) 4).setCellValue(rs.getString(4));
					
					if(rs.getString(4).trim().equals("Dr")){
						//debt_amtStr=rs.getInt(5);
						debt_amt=rs.getInt(5);
						//System.out.println("debt_amt===>"+debt_amt);
						tot_dedit = tot_dedit+debt_amt;
						row.createCell((short) 5).setCellValue(debt_amt);
					}
					else {
						row.createCell((short) 5).setCellValue("");
					}
					if(rs.getString(4).trim().equals("Cr")){
						//credit_amtStr=""+rs.getInt(5);
						credit_amt=rs.getInt(5);
						tot_credit =tot_credit+credit_amt;
						row.createCell((short) 6).setCellValue(credit_amt);
					}
					else {
						row.createCell((short) 6).setCellValue("");
					}
					//row.createCell((short) 5).setCellValue(debt_amt);
					//row.createCell((short) 6).setCellValue(credit_amt);
					index++;
				}
				HSSFRow row1 = sheet.createRow((short)index);
				row1.createCell((short) 4).setCellValue("Close Balance");
				row1.createCell((short) 5).setCellValue(tot_dedit);
				row1.createCell((short) 6).setCellValue(tot_credit);
				/*HSSFRow row1 = sheet.createRow((short)index);
				saledPricePreStm = con.prepareStatement(CommonConstents.SELECT_TOTAL_SALED_PRICE);
			    ResultSet rs3 = saledPricePreStm.executeQuery();
			    rs3.next();
				row1.createCell((short) 7).setCellValue(rs3.getLong(1));
				
				buyedPricePreStm = con.prepareStatement(CommonConstents.SELECT_TOTAL_BUYED_PRICE);
			    ResultSet rs1 = buyedPricePreStm.executeQuery();
				rs1.next();
				row1.createCell((short) 8).setCellValue(rs1.getLong(1));
				
				totalPricePreStm = con.prepareStatement(CommonConstents.SELECT_TOTAL_PROFIT_PRICE);
			    ResultSet rs2 = totalPricePreStm.executeQuery();
				rs2.next();
				row1.createCell((short) 9).setCellValue(rs2.getFloat(1));*/
				
				FileOutputStream fileOut = new FileOutputStream(CommonUtil.getFullFileName(inputVal, folderName));
				 
				hwb.write(fileOut);
				fileOut.close();
				selectLedgerDetails.close();
				con.close();
				displayMsg = "downloadLedgerDeatilsSuccess";
			}
		}catch(Exception e){
			try{
				selectLedgerDetails.close();
				con.close();
			}catch(Exception e1){}
			System.out.println("Ex ::"+e);
			displayMsg = "downloadProductDeatilsError";
		}
		return displayMsg;
	}
}
