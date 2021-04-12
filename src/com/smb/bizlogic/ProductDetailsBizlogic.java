package com.smb.bizlogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;
import com.smb.util.CommonUtil;

public class ProductDetailsBizlogic {
	public static String saveProductName(String prodName,HttpServletRequest request){
		Connection con = null;
		ResultSet r = null,userDetailsRs=null;
		String cusName = "",address="",resultType="";
		PreparedStatement saveProductStm=null;
		String seqName = "PRODUCT_TABLE";
		int prod_id ,resultInt;
		String seqUpdate = "";
		try{
			con = DBConnection.getDBConnection();	
			con.setAutoCommit(false);
			prod_id = CommonUtil.getSequence(seqName);
			if(prod_id > 0){
				saveProductStm = con.prepareStatement(CommonConstents.PRODUCT_INSERT_QUERY);
				saveProductStm.setInt(1, prod_id);
				saveProductStm.setString(2, prodName);
				resultInt = saveProductStm.executeUpdate();
				if(resultInt == 1){
					seqUpdate = CommonUtil.updateSequence(prod_id, seqName,con);
					if("updateSeq".equals(seqUpdate)){
						con.commit();
						resultType = "saveProduct";
					}else{
						resultType = "notSaveProduct";
					}
				}else{
					resultType = "notSaveProduct";
				}
			}else{
				resultType = "notSaveProduct";
			}
			saveProductStm.close();
			con.close();
		}catch (Exception e) {
			try{
				saveProductStm.close();
				con.close();
			}catch (Exception e1) {}
			System.out.println(e);
			resultType = "notSaveProduct";
		}
		System.out.println("resultType ::"+resultType);
		return resultType;
	}
}
