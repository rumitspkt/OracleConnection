package com.rum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class OracleConnUtils {
	private static String hostName;
	private static String service;
	private static String uname;
	private static String pwd;
 public static Connection getOracleConnection() throws SQLException,
         ClassNotFoundException {
     String hostName = OracleConnUtils.getHostName();
     String sid = OracleConnUtils.getService();;
     String userName = OracleConnUtils.getUname();;
     String password = OracleConnUtils.getPwd();;
 
     return getOracleConnection(hostName, sid, userName, password);
 }
 
 public static Connection getOracleConnection(String hostName, String sid,
         String userName, String password) throws ClassNotFoundException,
         SQLException {
     Class.forName("oracle.jdbc.driver.OracleDriver");
     String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
     System.out.println(connectionURL);
     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }

public static String getHostName() {
	return hostName;
}

public static void setHostName(String hostName) {
	OracleConnUtils.hostName = hostName;
}

public static String getService() {
	return service;
}

public static void setService(String service) {
	OracleConnUtils.service = service;
}

public static String getUname() {
	return uname;
}

public static void setUname(String uname) {
	OracleConnUtils.uname = uname;
}

public static String getPwd() {
	return pwd;
}

public static void setPwd(String pwd) {
	OracleConnUtils.pwd = pwd;
}
}