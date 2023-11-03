package com.smb.bizlogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;
import com.smb.util.CommonUtil;

public class CustomerDownloadBizlogic {
	static String TITLE ="SREE MAHALAKSHMI BINDUHASINI FINANCE";
	static String displayMsg = "";
	
	public static String totalUserDueHistoryDeatils(){
		//String displayMsg="";
		//System.out.println("h1");
		Connection con = null;
		String folderName = "Due History";
		PreparedStatement paymentStmt = null ,historyStmt =null,paymentRowCount=null;
		ResultSet paymentRslt = null ,historyRslt = null,paymentcntRslt=null;
		int maxDueNum =0;
		try{
			con = DBConnection.getDBConnection();
			paymentRowCount = con.prepareStatement(CommonConstents.SELECT_MAX_DUES);
			paymentcntRslt = paymentRowCount.executeQuery();
			while(paymentcntRslt.next()){
				maxDueNum = paymentcntRslt.getInt("maxId");
				maxDueNum = (maxDueNum > 7)?maxDueNum:7;
			}
			int CUS_ID =0,headCellNo=2,rowNo=3,sNo=1,paid_amt=0,resultCell=2;
			String paid_dateStr="";
			Date paid_date = null;

			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("SMB Finance");
			HSSFRow rowTitle = sheet.createRow((short)0);
			HSSFRow rowhead = sheet.createRow((short)2);
			rowTitle.createCell((short)0).setCellValue(TITLE);
			rowhead.createCell((short)0).setCellValue("SNO");
			rowhead.createCell((short)1).setCellValue("USER ID");
			for(int i=1;i<maxDueNum;i++){
				rowhead.createCell((short)headCellNo).setCellValue("DUE-"+i+"(Date)");
				headCellNo++;
			}
			try{
				paymentStmt = con.prepareStatement(CommonConstents.TOTAL_PAYMENT_DUE_HISTORY_DETAILS);
				paymentRslt = paymentStmt.executeQuery();
				while(paymentRslt.next()){
					CUS_ID = paymentRslt.getInt("cus_id");
					CUS_ID = (CUS_ID > 0)?CUS_ID:0;
					HSSFRow row = sheet.createRow((int)rowNo);
					if(CUS_ID > 0){
						historyStmt = con.prepareStatement(CommonConstents.SELECT_CUS_ID_FROM_HISTORY);
						historyStmt.setInt(1, CUS_ID);
						historyRslt = historyStmt.executeQuery();
						row.createCell((short)0).setCellValue(sNo);
						row.createCell((short)1).setCellValue(CUS_ID);
						while(historyRslt.next()){
							paid_amt = historyRslt.getInt("PAID_AMT");
							paid_amt = (paid_amt > 0)?paid_amt:0;
							paid_date = (Date)historyRslt.getObject("PAY_DATE");
							SimpleDateFormat dt2 = new SimpleDateFormat("dd-MM-yyyy");
							if(paid_date ==null){
								paid_dateStr = "";
							}
							if(paid_date !=null){
								paid_dateStr = "("+dt2.format(paid_date)+")";
							}else{
								paid_dateStr="";
							}
							if(paid_amt > 0){
								row.createCell((short)resultCell).setCellValue(paid_amt+paid_dateStr);
							}else{
								row.createCell((short)resultCell).setCellValue("Empty");
							}
							resultCell++;
						}//while(historyRslt.next())
						
					}//if(cus_id >0)
					resultCell=2;
					sNo++;
					rowNo++;
				}//while(paymentRslt.next())
				
				//FileOutputStream fileOut = new FileOutputStream(CommonUtil.getFullFileName("", folderName));
				JFileChooser chooser = new JFileChooser();
				CommonUtil util = new CommonUtil();
			    chooser.setCurrentDirectory(new File(util.getProperty("drive.location")));
			    chooser.showSaveDialog(null);
				    
				FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+".xls");
				
				hwb.write(fileOut);
				fileOut.close();
				historyStmt.close();
				paymentRowCount.close();
				paymentRowCount.close();
				con.close();
				displayMsg = "totalUserDueHistoryDeatilsSuccess";
			}catch(Exception e){
				historyStmt.close();
				paymentRowCount.close();
				paymentRowCount.close();
				con.close();
				System.out.println("Ex ::"+e);
				displayMsg = "totalUserDueHistoryDeatilsError";
			}
		}catch (Exception e) {
			try{
				historyStmt.close();
				paymentRowCount.close();
				paymentRowCount.close();
				con.close();
			}catch (Exception e1) {}
			System.out.println("Ex ::"+e);
		}
		return displayMsg;
	}
	public static String downloadProductDeatils(String inputVal,String folderName){
		Connection con = null;
		PreparedStatement selectProductDetails = null,saledPricePreStm=null,buyedPricePreStm=null,totalPricePreStm=null;
		try{
			con = DBConnection.getDBConnection();
			if(inputVal.length() > 0){
				
				if("ShopName".equals(inputVal)){
					selectProductDetails = con.prepareStatement(CommonConstents.SELECT_PRODUCT_DETAILS_ORDERBY_SHOP);
				}
				if("ProductName".equals(inputVal)){
					selectProductDetails = con.prepareStatement(CommonConstents.SELECT_PRODUCT_DETAILS_ORDERBY_ITEM);
				}
				ResultSet rs = selectProductDetails.executeQuery();
				
				HSSFWorkbook hwb = new HSSFWorkbook();
				HSSFSheet sheet = hwb.createSheet("SMB Finance");
				HSSFRow rowTitle = sheet.createRow((int)0);
				rowTitle.createCell((short)0).setCellValue(TITLE);
				
				HSSFRow rowhead = sheet.createRow((int)2);
				rowhead.createCell((short) 0).setCellValue("S NO");
				rowhead.createCell((short) 1).setCellValue("CUS ID");
				rowhead.createCell((short) 2).setCellValue("CUS_NAME");
				rowhead.createCell((short) 3).setCellValue("BUY DATE");
				rowhead.createCell((short) 4).setCellValue("ITEM NAME");
				rowhead.createCell((short) 5).setCellValue("SHOP NAME");
				rowhead.createCell((short) 6).setCellValue("MODEL NAME");
				rowhead.createCell((short) 7).setCellValue("BUY PRICE");
				rowhead.createCell((short) 8).setCellValue("SALED PRICE");
				rowhead.createCell((short) 9).setCellValue("PROFIT");
				
				int index=3;
				int sno=0;
				//String name="";

				while(rs.next()) 
				{
				sno++;
				
				HSSFRow row = sheet.createRow((int)index);
				row.createCell((short) 0).setCellValue(sno);
				row.createCell((short) 1).setCellValue(rs.getInt(1));
				row.createCell((short) 2).setCellValue(rs.getString(2));
				String buy_date =rs.getString(3);
				buy_date = (buy_date != null )?buy_date:"EMPTY";
				row.createCell((short) 3).setCellValue(buy_date);
				row.createCell((short) 4).setCellValue(rs.getString(4));
				row.createCell((short) 5).setCellValue(rs.getString(5));
				row.createCell((short) 6).setCellValue(rs.getString(6));
				row.createCell((short) 7).setCellValue(rs.getInt(7));
				row.createCell((short) 8).setCellValue(rs.getInt(8));
				row.createCell((short) 9).setCellValue(rs.getInt(9));
				index++;
				
				}
				HSSFRow row1 = sheet.createRow((short)index);
				//String sumPrice="select sum(buy_price) from item_details";
				//rs = stmt.executeQuery(sumPrice);
				saledPricePreStm = con.prepareStatement(CommonConstents.SELECT_TOTAL_SALED_PRICE);
			    ResultSet rs3 = saledPricePreStm.executeQuery();
			    rs3.next();
				row1.createCell((short) 7).setCellValue(rs3.getLong(1));
				
				
				//String sumdue_amount="select sum(saled_price) from item_details";
				//ResultSet rs1 = stmt.executeQuery(sumdue_amount);
				buyedPricePreStm = con.prepareStatement(CommonConstents.SELECT_TOTAL_BUYED_PRICE);
			    ResultSet rs1 = buyedPricePreStm.executeQuery();
				rs1.next();
				row1.createCell((short) 8).setCellValue(rs1.getLong(1));
				
				//String sumprofit="select sum(profit) from item_details";
				//ResultSet rs2 = stmt.executeQuery(sumprofit	);
				totalPricePreStm = con.prepareStatement(CommonConstents.SELECT_TOTAL_PROFIT_PRICE);
			    ResultSet rs2 = totalPricePreStm.executeQuery();
				rs2.next();
				row1.createCell((short) 9).setCellValue(rs2.getFloat(1));
				
				
				JFileChooser chooser = new JFileChooser();
				CommonUtil util = new CommonUtil();
			    chooser.setCurrentDirectory(new File(util.getProperty("drive.location")));
			    chooser.showSaveDialog(null);
				    
				FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+".xls");
				//FileOutputStream fileOut = new FileOutputStream(CommonUtil.getFullFileName(inputVal, folderName));
				 
				hwb.write(fileOut);
				fileOut.close();
				
				selectProductDetails.close();
				totalPricePreStm.close();
				buyedPricePreStm.close();
				saledPricePreStm.close();
				con.close();
				displayMsg = "downloadProductDeatilsSuccess";
			}
		}catch(Exception e){
			try{
			
			selectProductDetails.close();
			totalPricePreStm.close();
			buyedPricePreStm.close();
			saledPricePreStm.close();
			con.close();
			}catch(Exception e2){}
			System.out.println("Ex ::"+e);
			displayMsg = "downloadProductDeatilsError";
		}
		return displayMsg;
	}
	public static String userDownloadProfitDeatils(String fromDate,String toDate,String folderName,String inputVal,HttpServletRequest request){
		Connection con = null;
		PreparedStatement profitDetailsQury = null;
		int cus_id=0 ,buy_price=0,saled_price=0,advance=0,profit_on_item=0,count=0;
		 String item_name="";
		 float tot_profit=0f,doc_amt=0f,itrest_amt=0f;
		 Date purchaseDate =null;
		 float tot_doc=0f,tot_intrest=0f,tot_profits=0f;
		 
		if(fromDate.length() > 0 && toDate.length() > 0){
			try{
				con = DBConnection.getDBConnection();					
				//profitDetailsQury = con.prepareStatement("SELECT * FROM TRANSACTION_DETAILS WHERE PURCHASE_DATE BETWEEN STR_TO_DATE('"+fromDate+"','%D-%M-%Y') AND STR_TO_DATE('"+toDate+"','%D-%M-%Y')");	
				profitDetailsQury = con.prepareStatement(CommonConstents.GET_TRANSACTION_DETAILS_BETWEENDATE);
				profitDetailsQury.setString(1, fromDate);
				profitDetailsQury.setString(2, toDate);
				ResultSet rs = profitDetailsQury.executeQuery();
				//request.setAttribute("resultset", r);
				HSSFWorkbook hwb = new HSSFWorkbook();
				 HSSFSheet sheet = hwb.createSheet("new sheet");
				 HSSFRow rowTitle = sheet.createRow((short)0);
				 HSSFRow rowhead = sheet.createRow((short)2);
				 rowTitle.createCell((short)0).setCellValue(TITLE);
				 rowhead.createCell((short) 0).setCellValue("SNO");
				 rowhead.createCell((short) 1).setCellValue("CUS ID");
				 rowhead.createCell((short) 2).setCellValue("DATE");
				 rowhead.createCell((short) 3).setCellValue("ITEM");
				 rowhead.createCell((short) 4).setCellValue("BUYED");
				 rowhead.createCell((short) 5).setCellValue("SALED");
				 rowhead.createCell((short) 6).setCellValue("PROFIT");
				 rowhead.createCell((short) 7).setCellValue("DOCUMET");
				 rowhead.createCell((short) 8).setCellValue("INTREST");
				 rowhead.createCell((short) 9).setCellValue("TOT PROFIT");
				 int sno=0;
				 int index=3;
				 SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				 while(rs.next()){
					 sno++;
					    HSSFRow row = sheet.createRow((short)index);
					    cus_id = rs.getInt("cus_id");
						purchaseDate = rs.getDate("purchase_date");
						item_name = rs.getString("item_name"); 
						buy_price = rs.getInt("buy_price"); 
						saled_price = rs.getInt("tot_price"); 
						profit_on_item = rs.getInt("profit");
						doc_amt = rs.getFloat("doc_amt");
						itrest_amt = rs.getFloat("intrest_amt"); 
						tot_profit = rs.getFloat("tot_profit"); 
						
						tot_doc+=doc_amt;
						tot_intrest+=itrest_amt;
						tot_profits+=tot_profit;
						
						row.createCell((short) 0).setCellValue(sno);
						row.createCell((short) 1).setCellValue(cus_id);
						row.createCell((short) 2).setCellValue(df.format(purchaseDate));
						row.createCell((short) 3).setCellValue(item_name);
						row.createCell((short) 4).setCellValue(buy_price);
						row.createCell((short) 5).setCellValue(saled_price);
						row.createCell((short) 6).setCellValue(profit_on_item);
						row.createCell((short) 7).setCellValue(doc_amt);
						row.createCell((short) 8).setCellValue(itrest_amt);
						row.createCell((short) 9).setCellValue(tot_profit);				
						
						count++;
						index++;
				 }
				 HSSFRow row1 = sheet.createRow((short)index);
				 row1.createCell((short) 6).setCellValue("TOTAL PROFIT");
				 row1.createCell((short) 7).setCellValue(tot_doc);
				 row1.createCell((short) 8).setCellValue(tot_intrest);
				 row1.createCell((short) 9).setCellValue(tot_profits);
				 	
				 
				/*FileOutputStream fileOut = new FileOutputStream(CommonUtil.getFullFileName(inputVal, folderName));*/
				JFileChooser chooser = new JFileChooser();
				CommonUtil util = new CommonUtil();
			    chooser.setCurrentDirectory(new File(util.getProperty("drive.location")));
			    chooser.showSaveDialog(null);
				    
				FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+".xls");
				 
				hwb.write(fileOut);
				fileOut.close();
				
				profitDetailsQury.close();
				con.close();
				displayMsg = "downloaded";
			}catch (Exception e) {
				try{
					
					profitDetailsQury.close();
					con.close();
				}catch (Exception e1) {}
				displayMsg = "notdownloaded";
				}
		}
		
		return displayMsg;
	}
	public static String userDownloadDeatils(String inputVal,String folderName){
		System.out.println("userDownloadDeatils #####");
		Connection con = null;
		PreparedStatement paymentDetailsQury = null;
		PreparedStatement maxdueAmtQury=null,maxPerDueAmtQury = null ,maxNextDueAmtqury =null ;;
		String displayMsg="";				
	
			try{
				con = DBConnection.getDBConnection();			
				if("CustomerId".equals(inputVal)){
					paymentDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_CUSID);						
				}
				if("Address".equals(inputVal)){
					paymentDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_ADDRESS);				
				}								
				ResultSet rs = paymentDetailsQury.executeQuery();
				
				HSSFWorkbook hwb = new HSSFWorkbook();
				HSSFSheet sheet = hwb.createSheet("new sheet");
				HSSFRow rowTitle = sheet.createRow((short)0);
				rowTitle.createCell((short)0).setCellValue(TITLE);
			
				HSSFRow rowhead = sheet.createRow((short)2);
				rowhead.createCell((short) 0).setCellValue("S NO");
				rowhead.createCell((short) 1).setCellValue("CUS_ID");
				rowhead.createCell((short) 2).setCellValue("CUSTMER NAME");
				rowhead.createCell((short) 3).setCellValue("ADDRESS");
			
				rowhead.createCell((short) 4).setCellValue("BUY DATE");
				rowhead.createCell((short) 5).setCellValue("DUE TIME");
				rowhead.createCell((short) 6).setCellValue("TOT DUE");
				rowhead.createCell((short) 7).setCellValue("DUES");
				rowhead.createCell((short) 8).setCellValue("P DUE AMT");
				rowhead.createCell((short) 9).setCellValue("FINE");
				rowhead.createCell((short) 10).setCellValue("NEXT DUE");
				rowhead.createCell((short) 11).setCellValue("PHONE");
				rowhead.createCell((short) 12).setCellValue("RUFF");
				int index=3;int sno=0;				
				while(rs.next()){
					sno++;
			
					HSSFRow row = sheet.createRow((short)index);
					row.createCell((short) 0).setCellValue(sno);
					row.createCell((short) 1).setCellValue(rs.getInt(1));
					row.createCell((short) 2).setCellValue(rs.getString(2));
					row.createCell((short) 3).setCellValue(rs.getString(3));
					String buy_date =rs.getString(4);
					buy_date = (buy_date != null)?buy_date:"";
					row.createCell((short) 4).setCellValue(buy_date);
					row.createCell((short) 5).setCellValue(rs.getString(5));
					row.createCell((short) 6).setCellValue(rs.getInt(6));
					row.createCell((short) 7).setCellValue(rs.getInt(7));
					row.createCell((short) 8).setCellValue(rs.getInt(8));
					row.createCell((short) 9).setCellValue(rs.getInt(9));
					row.createCell((short) 10).setCellValue(rs.getInt(10));
					row.createCell((short) 11).setCellValue((rs.getString(11)));
					row.createCell((short) 12).setCellValue("");
					index++;
				}
				HSSFRow row1 = sheet.createRow((int)index);				
				maxdueAmtQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_SUM_DUE_AMT_QUERY);
				maxPerDueAmtQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_SUM_PER_DUE_AMT_QUERY);
				maxNextDueAmtqury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_SUM_NEXT_DUE_AMT_QUERY);
			
				rs = maxdueAmtQury.executeQuery();
				rs.next();
				row1.createCell((short) 6).setCellValue(rs.getFloat(1));
			
				ResultSet rs1 = maxPerDueAmtQury.executeQuery();
				rs1.next();
				row1.createCell((short) 8).setCellValue(rs1.getFloat(1));
			
				ResultSet rs2 = maxNextDueAmtqury.executeQuery();
				rs2.next();
				row1.createCell((short) 10).setCellValue(rs2.getFloat(1));
			
				FileOutputStream fileOut = new FileOutputStream(CommonUtil.getFullFileName(inputVal, folderName));
				
				hwb.write(fileOut);
				fileOut.close();
				displayMsg = "downloaded";
				
				maxdueAmtQury.close();
				paymentDetailsQury.close();
				maxPerDueAmtQury.close();maxNextDueAmtqury.close();
				con.close();
				
				//displayMsg="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully Order By.</font><font color='red' size='2'>"+inputVal+"</font></b><br><b><font color='red' size='2'> File Location :</font></b><font color='blue'>"+fileLocation+"</font><br><b><font color='red' size='2'> File name :</font></b><font color='blue'>"+filename.substring(24)+"</font></div>";
			}catch(Exception e){
				try{
					
					paymentDetailsQury.close();
					maxdueAmtQury.close();
					maxPerDueAmtQury.close();
					maxNextDueAmtqury.close();
					con.close();
				}catch(Exception e1){}
				displayMsg = "notdownloaded";
				//displayMsg="<div style=\"float:left;\"><b><font color='red' size='2'> Not Downloaded Successfully Order By.</font><font color='red' size='2'>"+inputVal+"</font></b></div>";
			}			
		
		return displayMsg;
	}
public static String userDownloadsDeatils(String inputVal){
		System.out.println("################ userDownloadsDeatils");
		Connection con = null;
		PreparedStatement paymentDetailsQury = null;
		//PreparedStatement maxdueAmtQury = null ,maxPerDueAmtQury = null ,maxNextDueAmtqury =null ;
		
		String displayMsg="";			
		FileOutputStream fileOut = null;
		String dateString ="";
		String formatedDate=null;
		java.util.Date date = new java.util.Date();
		Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
		formatedDate =formatter.format(date);
		dateString =formatedDate.replace(":", "-");
		if(inputVal.length() > 0){		
			try{
				con = DBConnection.getDBConnection();			
				if("CustomerId".equals(inputVal)){
					paymentDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_CUSID);						
				}
				if("Address".equals(inputVal)){
					paymentDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_DOWNLOAD_QUERY_ADDRESS);				
				}								
				ResultSet rs = paymentDetailsQury.executeQuery();
				
				HSSFWorkbook hwb = new HSSFWorkbook();
				HSSFSheet sheet = hwb.createSheet("SMB Finance");
				HSSFRow rowTitle = sheet.createRow((short)0);
				rowTitle.createCell((short)0).setCellValue(TITLE);
			
				HSSFRow rowhead = sheet.createRow((short)2);
				rowhead.createCell((short) 0).setCellValue("S NO");
				rowhead.createCell((short) 1).setCellValue("CUS ID");
				rowhead.createCell((short) 2).setCellValue("REMARKS");
				rowhead.createCell((short) 3).setCellValue("CUSTMER NAME");
				rowhead.createCell((short) 4).setCellValue("ADDRESS");
				rowhead.createCell((short) 5).setCellValue("ITEM");
				rowhead.createCell((short) 6).setCellValue("BUY DATE");
				rowhead.createCell((short) 7).setCellValue("D TIME");
				rowhead.createCell((short) 8).setCellValue("T DUE");
				rowhead.createCell((short) 9).setCellValue("DUES");
				rowhead.createCell((short) 10).setCellValue("D AMT");
				rowhead.createCell((short) 11).setCellValue("FINE");
				rowhead.createCell((short) 12).setCellValue("NEXT D");
				rowhead.createCell((short) 13).setCellValue("PHONE");
				int index=3;int sno=0;				
				while(rs.next()){
					sno++;
			
					HSSFRow row = sheet.createRow((short)index);
					row.createCell((short) 0).setCellValue(sno);
					row.createCell((short) 1).setCellValue(rs.getInt(1));
					row.createCell((short) 2).setCellValue("");
					row.createCell((short) 3).setCellValue(rs.getString(2));
					row.createCell((short) 4).setCellValue(rs.getString(3));
					row.createCell((short) 5).setCellValue(rs.getString(4));
					String buy_date =rs.getString(5);
					buy_date = (buy_date != null)?buy_date:"";
					
					row.createCell((short) 6).setCellValue(buy_date);
					row.createCell((short) 7).setCellValue(rs.getString(6));
					row.createCell((short) 8).setCellValue(rs.getInt(7));
					row.createCell((short) 9).setCellValue(rs.getInt(8));
					row.createCell((short) 10).setCellValue(rs.getInt(9));
					row.createCell((short) 11).setCellValue(rs.getInt(10));
					row.createCell((short) 12).setCellValue(rs.getInt(11));
					row.createCell((short) 13).setCellValue((rs.getString(12)));
					
					index++;
				}
				
				/*HSSFRow row1 = sheet.createRow((int)index);				
				maxdueAmtQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_SUM_DUE_AMT_QUERY);
				maxPerDueAmtQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_SUM_PER_DUE_AMT_QUERY);
				maxNextDueAmtqury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_SUM_NEXT_DUE_AMT_QUERY);
			
				rs = maxdueAmtQury.executeQuery();
				rs.next();
				row1.createCell((short) 6).setCellValue(rs.getFloat(1));
			
				ResultSet rs1 = maxPerDueAmtQury.executeQuery();
				rs1.next();
				row1.createCell((short) 8).setCellValue(rs1.getFloat(1));
			
				ResultSet rs2 = maxNextDueAmtqury.executeQuery();
				rs2.next();
				row1.createCell((short) 10).setCellValue(rs2.getFloat(1));*/
				try{
					//fileOut = new FileOutputStream(CommonUtil.getFullFileName(inputVal));
					System.out.println("###################################### FileOutputStream");
					fileOut = new FileOutputStream("OrderBy_"+inputVal+"_"+dateString+".xls");
					hwb.write(fileOut);
					fileOut.close();
				}catch(FileNotFoundException e){
					System.out.println("######################################");
					e.printStackTrace();
				}
					
				
				/*JFileChooser chooser = new JFileChooser();
				CommonUtil util = new CommonUtil();
			    chooser.setCurrentDirectory(new File(util.getProperty("drive.location")));
			    chooser.showSaveDialog(null);
				    
				FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+".xls");*/
				
				
				
				paymentDetailsQury.close();
				/*maxdueAmtQury.close();
				maxPerDueAmtQury.close();
				maxNextDueAmtqury.close();*/
				con.close();
				displayMsg = "downloaded";
				//displayMsg="<div style=\"float:left;\"><b><font color='green' size='2'> Downloaded Successfully Order By.</font><font color='red' size='2'>"+inputVal+"</font></b><br><b><font color='red' size='2'> File Location :</font></b><font color='blue'>"+fileLocation+"</font><br><b><font color='red' size='2'> File name :</font></b><font color='blue'>"+filename.substring(24)+"</font></div>";
			}catch(Exception e){
				try{
					
					paymentDetailsQury.close();
					/*maxdueAmtQury.close();
					maxPerDueAmtQury.close();
					maxNextDueAmtqury.close();*/
					con.close();
				}catch(Exception e1){}
				displayMsg = "notdownloaded";
				System.out.println("*************************"+e);
				e.printStackTrace();
				//displayMsg="<div style=\"float:left;\"><b><font color='red' size='2'> Not Downloaded Successfully Order By.</font><font color='red' size='2'>"+inputVal+"</font></b></div>";
			}			
		
		}
		return displayMsg;
	}
	public static String cheetyDownloadDetails(int che_id){
	String displayMsg = "";
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
		
		HSSFWorkbook hwb = new HSSFWorkbook();
		HSSFSheet sheet = hwb.createSheet("new sheet");
		HSSFRow rowTitle = sheet.createRow((short)0);
		rowTitle.createCell((short)0).setCellValue(TITLE);
		HSSFRow rowheader = sheet.createRow((short)2);
		while(rs1.next()){
			rowheader.createCell((short) 0).setCellValue("CHEETY :");
			rowheader.createCell((short) 1).setCellValue(rs1.getInt("CHEETY_AMOUNT"));
			rowheader.createCell((short) 2).setCellValue("DATE :");
			rowheader.createCell((short) 3).setCellValue(rs1.getString("CHEETY_DATE"));
			rowheader.createCell((short) 4).setCellValue("MEMBERS :");
			rowheader.createCell((short) 5).setCellValue(rs1.getInt("TOT_MEMBERS"));
			rowheader.createCell((short) 6).setCellValue("MONTHS :");
			rowheader.createCell((short) 7).setCellValue(rs1.getInt("TOT_MONTHS"));
			rowheader.createCell((short) 8).setCellValue("OWNER :");
			rowheader.createCell((short) 9).setCellValue(rs1.getString("OWNER_NAME"));
		}
		HSSFRow rowhead = sheet.createRow((short)3);
		rowhead.createCell((short) 0).setCellValue("SNO");
		rowhead.createCell((short) 1).setCellValue("NAME");
		rowhead.createCell((short) 2).setCellValue("DATE");
		rowhead.createCell((short) 3).setCellValue("STATUS");
		rowhead.createCell((short) 4).setCellValue("PATA NUM");
		rowhead.createCell((short) 5).setCellValue("PATA AMT");
		rowhead.createCell((short) 6).setCellValue("TOPU AMT");
		rowhead.createCell((short) 7).setCellValue("DUE AMT");
		rowhead.createCell((short) 8).setCellValue("TAKE AMT");
		rowhead.createCell((short) 9).setCellValue("PHONE");
		
		int index=4;int sno=0,PATA_NUMBER=0,PATA_AMOUNT=0,TOPU_AMOUNT=0,DUE_AMOUNT=0,TAKE_OF_AMOUNT=0;
		String NAME = "",DATE="",STATUS="",PHONE="";
		while(rs.next()){
			NAME = rs.getString("MEMBER_NAME");
			NAME = (NAME != null)?NAME:"";
			//if(NAME.length() > 19)NAME = NAME.substring(0, 19);
			DATE = rs.getString("PATA_DATE");
			DATE = (DATE == null)?"-":DATE;
			PATA_NUMBER = rs.getInt("PATA_NUMBER");
			PATA_NUMBER = (PATA_NUMBER > 0)?PATA_NUMBER:0;
			STATUS = rs.getString("STATUS");
			STATUS = (STATUS != null)?STATUS:"";
			PATA_AMOUNT = rs.getInt("PATA_AMOUNT");
			PATA_AMOUNT = (PATA_AMOUNT > 0)?PATA_AMOUNT:0;
			TOPU_AMOUNT = rs.getInt("TOPU_AMOUNT");
			TOPU_AMOUNT = (TOPU_AMOUNT > 0)?TOPU_AMOUNT:0;
			DUE_AMOUNT = rs.getInt("DUE_AMOUNT");
			DUE_AMOUNT = (DUE_AMOUNT > 0)?DUE_AMOUNT:0;
			TAKE_OF_AMOUNT = rs.getInt("TAKE_OF_AMOUNT");
			TAKE_OF_AMOUNT = (TAKE_OF_AMOUNT > 0)?TAKE_OF_AMOUNT:0;
			PHONE = rs.getString("PHONE");
			PHONE = (PHONE != null)?PHONE:"";
			sno++;
			HSSFRow row = sheet.createRow((short)index);
			row.createCell((short) 0).setCellValue(sno);
			row.createCell((short) 1).setCellValue(NAME);
			row.createCell((short) 2).setCellValue(DATE);
			row.createCell((short) 3).setCellValue(STATUS);
			row.createCell((short) 4).setCellValue(PATA_NUMBER);
			row.createCell((short) 5).setCellValue(PATA_AMOUNT);
			row.createCell((short) 6).setCellValue(TOPU_AMOUNT);
			row.createCell((short) 7).setCellValue(DUE_AMOUNT);
			row.createCell((short) 8).setCellValue(TAKE_OF_AMOUNT);
			row.createCell((short) 9).setCellValue(PHONE);
			index++;
		}
		//FileOutputStream fileOut = new FileOutputStream(CommonUtil.getFileCheety());
		JFileChooser chooser = new JFileChooser();
		CommonUtil util = new CommonUtil();
	    chooser.setCurrentDirectory(new File(util.getProperty("drive.location")));
	    chooser.showSaveDialog(null);
		    
		FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+".xls");
		//out.println(file);
		hwb.write(fileOut);
		fileOut.close();
		profitDetailsQury.close();profitDetailsQury1.close();
		con.close();
		displayMsg = "downloaded";
	}catch (Exception e) {
		try{
		profitDetailsQury.close();profitDetailsQury1.close();
		con.close();
		}catch (Exception e1) {}
		System.out.println("Exception ###"+e);displayMsg = "notdownloaded";}
	return displayMsg;
}
}
