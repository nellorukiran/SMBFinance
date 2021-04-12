package com.smb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;

public class AlterDateValueUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		ResultSet r = null;
		int cusId = 0,paid_amt=0,due_no=0;
		String dueDate = "",newDueDate="",newMonth="",due_date="";
		PreparedStatement historyDetailsQury = null,insertQuery=null;
		
		try{
			con = DBConnection.getDBConnection();	
			historyDetailsQury = con.prepareStatement("SELECT * FROM payment_history where pay_date is null");	
			//historyDetailsQury = con.prepareStatement("SELECT * FROM payment_history where cus_id between 1000 and 1240 order by cus_id desc");	
			//historyDetailsQury = con.prepareStatement("select * from payment_history where due_date !='null' and PAY_DATE is null");	
			//historyDetailsQury = con.prepareStatement("select * from payment_history where due_date ='null'");	
			//historyDetailsQury = con.prepareStatement("select * from payment_history where due_date ='null' and PAY_DATE is null");	
			r = historyDetailsQury.executeQuery();
			Date payment_date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			while(r.next()){
				cusId = r.getInt("cus_id");
				due_no = r.getInt("due_no");
				dueDate = r.getString("due_date"); 
				dueDate = (dueDate != null)?dueDate:"";
				dueDate = "03-10-2014";
				paid_amt = r.getInt("paid_amt");
				//System.out.println(""+dueDate);
				//String[] dateArray = null;
				//dateArray = new String[55];
				/*if(dueDate != null && dueDate.length() > 0){
					dateArray = dueDate.split("-");
					//System.out.println("dateArray ::"+dateArray[0]+"/"+dateArray[1]+"/"+dateArray[2]);
					//System.out.println("dateArray ::"+dateArray[1].length()+"cusId ::"+cusId);
					if(dateArray[1].length() > 2){
						//System.out.println("newMonth 1::"+dateArray[1]);
						newMonth = dateArray[1].substring(1, 3);
						//System.out.println("newMonth ::"+newMonth);
					}else{
						newMonth = dateArray[1];
					}
					
					newDueDate = dateArray[0]+"-"+newMonth+"-"+dateArray[2];*/
					//System.out.println("New Due Date :"+newDueDate);
				//try{payment_date = sdf.parse(dueDate);}catch (Exception ee) {}
					try{payment_date = sdf.parse(dueDate);}catch (Exception ee) {}
					if(newDueDate != null){
						insertQuery  = con.prepareStatement("UPDATE  payment_history SET PAY_DATE=? where paid_amt=? and cus_id=? and due_no=?");
						/*System.out.println(payment_date);
						System.out.println(dueDate);
						System.out.println(cusId);*/
						//insertQuery.setObject(1, payment_date);
						insertQuery.setObject(1, payment_date);
						insertQuery.setInt(2, paid_amt);
						insertQuery.setInt(3, cusId);
						insertQuery.setInt(4, due_no);
						int result = insertQuery.executeUpdate();
						System.out.println("Success ::"+result+"Cus ID ::"+cusId+"Due Date ::"+dueDate);
					}
					
				//}
			}
			
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}catch(Exception e1){
			System.out.println(e1);
		}
	}

}
