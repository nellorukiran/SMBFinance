package com.smb.bizlogic;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;
import com.smb.util.CommonUtil;

public class DownloadBizlogic {
	static String TITLE ="SREE MAHALAKSHMI BINDUHASINI FINANCE";
	static String displayMsg = "";
	public static HSSFWorkbook downloadProductDeatils(){
		HSSFWorkbook hwb = null;
		Connection con = null;
		PreparedStatement selectProductDetails = null,saledPricePreStm=null,buyedPricePreStm=null,totalPricePreStm=null;
		try{
			con = DBConnection.getDBConnection();
				selectProductDetails = con.prepareStatement(CommonConstents.SELECT_PRODUCT_DETAILS_ORDERBY_ITEM);
				//System.out.println(CommonConstents.SELECT_PRODUCT_DETAILS_ORDERBY_ITEM);
				ResultSet rs = selectProductDetails.executeQuery();
				
				hwb = new HSSFWorkbook();
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
				//ResultSet rs2 = stmt.executeQuery(sumprofit);
				totalPricePreStm = con.prepareStatement(CommonConstents.SELECT_TOTAL_PROFIT_PRICE);
			    ResultSet rs2 = totalPricePreStm.executeQuery();
				rs2.next();
				row1.createCell((short) 9).setCellValue(rs2.getFloat(1));
				
				
				/*JFileChooser chooser = new JFileChooser();
				CommonUtil util = new CommonUtil();
			    chooser.setCurrentDirectory(new File(util.getProperty("drive.location")));
			    chooser.showSaveDialog(null);*/
				    
				FileOutputStream fileOut = new FileOutputStream(CommonUtil.getFullFileName("ProductName"));
				//FileOutputStream fileOut = new FileOutputStream("productExcel.xls");
				 
				hwb.write(fileOut);
				//fileOut.close();
				
				selectProductDetails.close();
				totalPricePreStm.close();
				buyedPricePreStm.close();
				saledPricePreStm.close();
				con.close();
				displayMsg = "downloadProductDeatilsSuccess";
			
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
		return hwb;
	}
}
