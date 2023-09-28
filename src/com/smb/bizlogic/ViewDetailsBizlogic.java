package com.smb.bizlogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.smb.common.CommonConstents;
import com.smb.common.DBConnection;

public class ViewDetailsBizlogic {

	public static String viewPaymentHistory(HttpServletRequest request) {
		Connection con = null;
		Connection awsCon = null;
		ResultSet r = null, userDetailsRs = null;
		String cusName = "", address = "";
		PreparedStatement historyDetailsQury = null, userDetailsQury = null;
		String resultType = "";
		int cus_id = Integer.parseInt(request.getParameter("inputVal"));
		cus_id = (cus_id > 0) ? cus_id : 0;
		try {
			awsCon = DBConnection.getAWSDBConnection();
			if (awsCon != null) {
				historyDetailsQury = awsCon.prepareStatement(CommonConstents.SELECT_CUS_ID_FROM_HISTORY);
				historyDetailsQury.setInt(1, cus_id);
				r = historyDetailsQury.executeQuery();
				userDetailsQury = awsCon.prepareStatement(CommonConstents.TRANSACTION_DETAILS_SELECT_QUERY);
				userDetailsQury.setInt(1, cus_id);
				userDetailsRs = userDetailsQury.executeQuery();
			} else {
				try {
					con = DBConnection.getDBConnection();
					if (con != null) {
						historyDetailsQury = con.prepareStatement(CommonConstents.SELECT_CUS_ID_FROM_HISTORY);
						historyDetailsQury.setInt(1, cus_id);
						r = historyDetailsQury.executeQuery();
						userDetailsQury = con.prepareStatement(CommonConstents.TRANSACTION_DETAILS_SELECT_QUERY);
						userDetailsQury.setInt(1, cus_id);
						userDetailsRs = userDetailsQury.executeQuery();
					}
				} catch (Exception e1) {
					resultType = "noUserDetails";
					System.out.println("Exception while DB Connect / Operation #####-1" + e1);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception while AWS DB Connect / Operation #####-2" + e);
			try {
				con = DBConnection.getDBConnection();
				if (con != null) {
					historyDetailsQury = con.prepareStatement(CommonConstents.SELECT_CUS_ID_FROM_HISTORY);
					historyDetailsQury.setInt(1, cus_id);
					r = historyDetailsQury.executeQuery();
					userDetailsQury = con.prepareStatement(CommonConstents.TRANSACTION_DETAILS_SELECT_QUERY);
					userDetailsQury.setInt(1, cus_id);
					userDetailsRs = userDetailsQury.executeQuery();
				}
			} catch (Exception e1) {
				System.out.println("Exception while DB Connect / Operation #####-3" + e1);
			}
		}
		try {
			if (r.isBeforeFirst()) {
				if (r != null)
					request.setAttribute("resultset", r);
				if (userDetailsRs.isBeforeFirst()) {
					while (userDetailsRs.next()) {
						cusName = userDetailsRs.getString("cus_name");
						address = userDetailsRs.getString("ADDRESS");
					}
					request.setAttribute("cusName", cusName);
					request.setAttribute("address", address);
				}
				resultType = "viewHistory";
			} else {
				resultType = "noHistory";
			}
		} catch (Exception e) {
			System.out.println("Exception While getting records in ViewDetailsBizlogic ####-4" + e);
			resultType = "noHistory";
		}
		return resultType;
	}

	public static String showUserDetails(String inputType, HttpServletRequest request) {
		Connection con = null;
		Connection awsCon = null;
		ResultSet r = null;
		ResultSet r1 = null;
		PreparedStatement userDetailsQury = null;
		PreparedStatement userDetailsQury1 = null;
		String resultType = "";
		try {
			awsCon = DBConnection.getAWSDBConnection();
			if (awsCon != null) {
				if (inputType.equals("PAYMENTS")) {
					userDetailsQury = awsCon.prepareStatement(CommonConstents.PAYMENT_DETAILS_TABLE_QUERY);
					r = userDetailsQury.executeQuery();
					resultType = "userPaymentDetails";
					request.setAttribute("resultset", r);
				} else if (inputType.equals("TRANSACTION")) {
					userDetailsQury1 = awsCon.prepareStatement(CommonConstents.TRANSACTION_DETAILS_TABLE_QUERY);
					r1 = userDetailsQury1.executeQuery();
					resultType = "userTransactionDetails";
					request.setAttribute("resultset", r1);
				}
				request.setAttribute("yes", "yes");
			} else {
				try {
					con = DBConnection.getDBConnection();
					if (con != null) {
						if (inputType.equals("PAYMENTS")) {
							userDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_TABLE_QUERY);
							r = userDetailsQury.executeQuery();
							resultType = "userPaymentDetails";
							request.setAttribute("resultset", r);
						} else if (inputType.equals("TRANSACTION")) {
							userDetailsQury1 = con.prepareStatement(CommonConstents.TRANSACTION_DETAILS_TABLE_QUERY);
							r1 = userDetailsQury1.executeQuery();
							resultType = "userTransactionDetails";
							request.setAttribute("resultset", r1);
						}
						request.setAttribute("yes", "yes");
					}
				} catch (Exception e1) {
					resultType = "noUserDetails";
					System.out.println("Exception while DB Connect / Operation #####-1" + e1);
				}
			}
		} catch (Exception e) {
			resultType = "noUserDetails";
			System.out.println("Exception while AWS DB Connect / Operation #####-2" + e);
			try {
				con = DBConnection.getDBConnection();
				if (con != null) {
					if (inputType.equals("PAYMENTS")) {
						userDetailsQury = con.prepareStatement(CommonConstents.PAYMENT_DETAILS_TABLE_QUERY);
						r = userDetailsQury.executeQuery();
						resultType = "userPaymentDetails";
						request.setAttribute("resultset", r);
					} else if (inputType.equals("TRANSACTION")) {
						userDetailsQury1 = con.prepareStatement(CommonConstents.TRANSACTION_DETAILS_TABLE_QUERY);
						r1 = userDetailsQury1.executeQuery();
						resultType = "userTransactionDetails";
						request.setAttribute("resultset", r1);
					}
					request.setAttribute("yes", "yes");
				}
			} catch (Exception e1) {
				resultType = "noUserDetails";
				System.out.println("Exception while DB Connect / Operation #####-3" + e1);
			}
		}
		return resultType;
	}

	public static String viewCustomerProfitDetails(String fromDate, String toDate, HttpServletRequest request) {
		Connection con = null;
		Connection awsCon = null;
		PreparedStatement profitDetailsQury = null;
		String displayMsg = "";
		ResultSet r = null;
		if (fromDate.length() > 0 && toDate.length() > 0) {
			try {
				awsCon = DBConnection.getAWSDBConnection();
				if (awsCon != null) {
					profitDetailsQury = awsCon.prepareStatement(CommonConstents.GET_TRANSACTION_DETAILS_BETWEENDATE);
					profitDetailsQury.setString(1, fromDate);
					profitDetailsQury.setString(2, toDate);
					r = profitDetailsQury.executeQuery();
				} else {
					con = DBConnection.getDBConnection();
					if (con != null) {
						profitDetailsQury = con.prepareStatement(CommonConstents.GET_TRANSACTION_DETAILS_BETWEENDATE);
						profitDetailsQury.setString(1, fromDate);
						profitDetailsQury.setString(2, toDate);
						r = profitDetailsQury.executeQuery();
					}
				}
			} catch (Exception e) {
				displayMsg = "noViewCustomerProfitDetails";
				System.out.println("Exception while AWS DB Connect / Operation #####-2" + e);
				try {
					con = DBConnection.getDBConnection();
					if (con != null) {
						profitDetailsQury = con.prepareStatement(CommonConstents.GET_TRANSACTION_DETAILS_BETWEENDATE);
						profitDetailsQury.setString(1, fromDate);
						profitDetailsQury.setString(2, toDate);
						r = profitDetailsQury.executeQuery();
					}
				} catch (Exception e1) {
					displayMsg = "noViewCustomerProfitDetails";
					System.out.println("Exception while DB Connect / Operation #####-3" + e1);
				}
			}
			try {
				if (!r.isBeforeFirst()) {
					request.setAttribute("resultType", "NO");
				} else {
					request.setAttribute("resultset", r);
					request.setAttribute("resultType", "YES");
				}
				displayMsg = "viewCustomerProfitDetails";
			} catch (Exception e) {
				try {
					profitDetailsQury.close();
					con.close();
				} catch (Exception e1) {
					System.out.println("Exception while DB Connect / Operation #####-4" + e1);
					displayMsg = "noViewCustomerProfitDetails";
				}
				System.out.println("Exception while DB Connect / Operation #####-5" + e);
				displayMsg = "noViewCustomerProfitDetails";
			}
		}
		return displayMsg;
	}

	public static String viewMonthlyCollectionDetails(String fromDate, String toDate, HttpServletRequest request) {
		Connection con = null;
		Connection awsCon = null;
		PreparedStatement profitDetailsQury = null;
		String displayMsg = "";
		ResultSet r = null;
		if (fromDate.length() > 0 && toDate.length() > 0) {
			try {
				awsCon = DBConnection.getAWSDBConnection();
				if (awsCon != null) {
					profitDetailsQury = awsCon.prepareStatement(CommonConstents.GET_MONTHLY_DUE_DETAILS_BETWEENDATE);
					profitDetailsQury.setString(1, fromDate);
					profitDetailsQury.setString(2, toDate);
					r = profitDetailsQury.executeQuery();
				} else {
					con = DBConnection.getDBConnection();
					if (con != null) {
						profitDetailsQury = con.prepareStatement(CommonConstents.GET_MONTHLY_DUE_DETAILS_BETWEENDATE);
						profitDetailsQury.setString(1, fromDate);
						profitDetailsQury.setString(2, toDate);
						r = profitDetailsQury.executeQuery();
					}
				}
			} catch (Exception e) {
				displayMsg = "noViewCustomerProfitDetails";
				System.out.println("Exception while AWS DB Connect / Operation #####-2" + e);
				try {
					con = DBConnection.getDBConnection();
					if (con != null) {
						con = DBConnection.getDBConnection();
						profitDetailsQury = con.prepareStatement(CommonConstents.GET_TRANSACTION_DETAILS_BETWEENDATE);
						profitDetailsQury.setString(1, fromDate);
						profitDetailsQury.setString(2, toDate);
						r = profitDetailsQury.executeQuery();
					}
				} catch (Exception e1) {
					displayMsg = "noViewCustomerProfitDetails";
					System.out.println("Exception while DB Connect / Operation #####-3" + e1);
				}
			}
			try {
				if (!r.isBeforeFirst()) {
					request.setAttribute("resultType", "NO");
				} else {
					request.setAttribute("resultset", r);
					request.setAttribute("resultType", "YES");

				}
				displayMsg = "viewMonthlyCollection";
			} catch (Exception e) {
				try {
					profitDetailsQury.close();
					con.close();
				} catch (Exception e1) {
					System.out.println("Exception while DB Connect / Operation #####-4" + e1);
					displayMsg = "noViewCustomerProfitDetails";
				}
				System.out.println("Exception while DB Connect / Operation #####-5" + e);
				displayMsg = "noViewMonthlyCollection";
			}
		}
		return displayMsg;
	}
}
