package edu.cis232.inClassLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowResultsApp {

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
			
			while (result.next())
	         {
	            System.out.printf("%s %s %s %s %s %s%n", result.getString("customerNumber"), 
	            										result.getString("name"),
	            										result.getString("address"),
	            										result.getString("city"),
	            										result.getString("state"),
	            										result.getString("zip"));
	         }
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
