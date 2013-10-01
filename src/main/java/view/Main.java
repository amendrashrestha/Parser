package main.java.view;

import java.sql.SQLException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CreateParser init = new CreateParser();
		try{
			init.parserPostFile();
		}catch(SQLException ex){
			
		}
	}

}
