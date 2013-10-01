package main.java.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class WriteToFile {
	
	public void createFile(int ID, String date, String post){
		String fileName = String.valueOf(ID) + ".txt";
		String fileLocation = "C:/Users/ITE/PostFiles/";

		File postFile = new File(fileLocation + fileName);
		try {
			PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(postFile, true)));
			write.println(date);
			write.println(post);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
