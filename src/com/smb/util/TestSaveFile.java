package com.smb.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.omg.CORBA.COMM_FAILURE;

import com.smb.bizlogic.CustomerDownloadBizlogic;
import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;

public class TestSaveFile {

	public static void main(String[] args) {
		TestSaveFile testSaveFile = new TestSaveFile();
		
		CustomerDownloadBizlogic bizlogic = new CustomerDownloadBizlogic();
		
		//bizlogic.userDownloadsDeatils("CustomerId","Monthly Details","monthly");
		
		String sb = "TEST CONTENT";
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("D://kiran"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
	            fw.write(sb.toString());
	            fw.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }


	}
		public String userDownloadsDeatils(String inputVal,String folderName,String inputType){
			String TITLE ="SREE MAHALAKSHMI BINDUHASINI FINANCE";
			Connection con = null;
			PreparedStatement paymentDetailsQury = null;
			PreparedStatement maxdueAmtQury = null ,maxPerDueAmtQury = null ,maxNextDueAmtqury =null ;
			
			String displayMsg="";				
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
					HSSFSheet sheet = hwb.createSheet("new sheet");
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
					
					JFileChooser chooser = new JFileChooser();
					CommonUtil util = new CommonUtil();
				    chooser.setCurrentDirectory(new File(util.getProperty("drive.location")));
				    chooser.showSaveDialog(null);
				    
					FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+".xls");
					
					
					hwb.write(fileOut);
					fileOut.close();
					
					paymentDetailsQury.close();
					maxdueAmtQury.close();
					maxPerDueAmtQury.close();
					maxNextDueAmtqury.close();
					con.close();
					
					displayMsg = "downloaded";
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
					System.out.println(e);
					//displayMsg="<div style=\"float:left;\"><b><font color='red' size='2'> Not Downloaded Successfully Order By.</font><font color='red' size='2'>"+inputVal+"</font></b></div>";
				}			
			
			}
			return displayMsg;
		}
		public void FileSave(final String title,final String name)
		{
		      final JFileChooser chooser=new JFileChooser();
		    //  chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		      chooser.setDialogTitle(title);
		      chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.dir")));
		      chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
		      {
		         public boolean accept(final File f)
		        {
		          return f.isDirectory();
		        }
		        public String getDescription(){
		          return "Folder To Save In";
		        }
		      }
		    );
		      final int r=chooser.showSaveDialog(null);
		      File file;

		      if (r == JFileChooser.APPROVE_OPTION) 
		      {
		        if (name != null) 
		        {
		            file=new File(chooser.getSelectedFile().getPath() + File.separator + name);
		        }
		        else 
		        {
		            // file=new File(filename);
		          file=new File(chooser.getSelectedFile().getPath());
		        }
		      }
		    }
		
}
