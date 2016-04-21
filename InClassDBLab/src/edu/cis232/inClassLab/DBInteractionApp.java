package edu.cis232.inClassLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBInteractionApp {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner (System.in);
		
		final String DB_URL = "jdbc:hsqldb:file:CoffeeDB/coffee;ifexists=true";
		String custNum, name, address, city, state,zip;
		
		System.out.print("enter customer number:");
		custNum = keyboard.nextLine();
		
		try {

			Connection conn = DriverManager.getConnection(DB_URL);

			String sql = "select * from Customer where CustomerNumber = ?";

			PreparedStatement selectCustomer = conn.prepareStatement(sql);

			selectCustomer.setString(1, custNum);

			ResultSet result = selectCustomer.executeQuery();
			
			if(result.next()){
				
				System.out.println("Enter name: ");
				name = keyboard.nextLine();
				System.out.println("Enter address: ");
				address = keyboard.nextLine();
				System.out.println("Enter city: ");
				city = keyboard.nextLine();
				System.out.println("Enter state: ");
				state = keyboard.nextLine();
				System.out.println("Enter zip: ");
				zip = keyboard.nextLine();
				
				sql = "UPDATE Customer SET Name = ?,"
										+ "Address = ?,"
										+ "City = ?,"
										+ "State = ?,"
										+ "Zip = ? "
										+ "WHERE CustomerNumber = ?";
				
				PreparedStatement updateCustomer = conn.prepareStatement(sql);
				updateCustomer.setString(1, name);
				updateCustomer.setString(2, address);
				updateCustomer.setString(3, city);
				updateCustomer.setString(4, state);
				updateCustomer.setString(5, zip);
				updateCustomer.setString(6, custNum);
				
				updateCustomer.execute();
			}else{
				 sql = "insert into Customer (CustomerNumber) values = ?";
				PreparedStatement addCustomer = conn.prepareStatement(sql);
				addCustomer.setString(1, custNum);
				System.out.println("Your customer number was added.");
			}
			 sql = "select * from Customer where CustomerNumber = ?";

			 selectCustomer = conn.prepareStatement(sql);

			selectCustomer.setString(1, custNum);

			 result = selectCustomer.executeQuery();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
