package com.rum.main;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.rum.jdbc.OracleConnUtils;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class OracleConnection {

	private JFrame frame;
	private JTextField hostname;
	private JTextField service;
	private JTextField uname;
	private JTextField pwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OracleConnection window = new OracleConnection();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OracleConnection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 459, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHostname = new JLabel("Hostname");
		lblHostname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHostname.setBounds(68, 60, 109, 14);
		frame.getContentPane().add(lblHostname);
		
		hostname = new JTextField();
		hostname.setBounds(203, 60, 134, 20);
		frame.getContentPane().add(hostname);
		hostname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("SID/Service");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(68, 118, 109, 14);
		frame.getContentPane().add(lblNewLabel);
		
		service = new JTextField();
		service.setBounds(203, 118, 134, 20);
		frame.getContentPane().add(service);
		service.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(68, 176, 109, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(68, 241, 109, 14);
		frame.getContentPane().add(lblPassword);
		
		uname = new JTextField();
		uname.setBounds(203, 176, 134, 20);
		frame.getContentPane().add(uname);
		uname.setColumns(10);
		
		pwd = new JTextField();
		pwd.setBounds(203, 241, 134, 20);
		frame.getContentPane().add(pwd);
		pwd.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Connect to Oracle Database");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setBounds(93, 11, 244, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Connect");
		
		btnNewButton.setBounds(345, 344, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox<String> tables = new JComboBox<String>();
		tables.setBounds(203, 307, 134, 20);
		frame.getContentPane().add(tables);
		
		JLabel lblNewLabel_3 = new JLabel("Tables");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(68, 307, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel result = new JLabel(" ");
		result.setBounds(68, 348, 185, 14);
		frame.getContentPane().add(result);
		
		JLabel exception = new JLabel(" ");
		exception.setBounds(68, 373, 366, 14);
		frame.getContentPane().add(exception);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hostName, serviceName, username, password;
				hostName = hostname.getText();
				serviceName = service.getText();
				username = uname.getText();
				password = pwd.getText();
				result.setForeground(Color.RED);
				exception.setForeground(Color.RED);
				result.setText("");
				exception.setText("");
				if(hostName.equals("") || serviceName.equals("") || username.equals("") || password.equals("")) {				
					result.setText("Please entering all fields");
					System.out.println("please");
					return;
				}
				try {
					Connection conn = OracleConnUtils.getOracleConnection(hostName, serviceName, username, password);
					Statement statement = conn.createStatement();
					String sql = "SELECT table_name FROM all_tables where owner = '" + username.toUpperCase() + "'";
					System.out.println(sql);
					ResultSet rs = statement.executeQuery(sql);
					
					while (rs.next()) {
						tables.addItem(rs.getString(1));					
				    }
					result.setForeground(Color.GREEN);
					result.setText("Successful!!");
				} catch (ClassNotFoundException e1) {
					result.setText("Failed!!");
				} catch (SQLException e1) {
					result.setText("Failed!!");
					exception.setText(e1.getMessage());
				}
			}
		});
	}
}
