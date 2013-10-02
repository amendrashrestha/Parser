package main.java.view;

import java.sql.SQLException;

/**
 * This is the main class for parsing XML files.
 * @author ITE
 *
 */
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
