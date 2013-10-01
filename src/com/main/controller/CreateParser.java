package com.main.controller;

import java.sql.SQLException;

public class CreateParser {
	
	private String FILE_PATH = "C:/Users/ITE/Desktop/000/";  //put the path of the folder
    FileDirectoryHandler handle = new FileDirectoryHandler();
    String[] filesList;
    private final String START_MESSAGE = "Start parsing post files";
    private final String END_MESSAGE = "Finished parsing post files";
    PostParser parser = new PostParser();

    public CreateParser() {
    }

    public void parserPostFile() throws SQLException {
        handle.rootlist(FILE_PATH);
        filesList = new String[FileDirectoryHandler.getList().size()];
        FileDirectoryHandler.list.toArray(filesList);
        for (int i = 0; i < filesList.length; i++) {
            String fileName = filesList[i];
            System.out.println(START_MESSAGE);
            parser.parsePost(fileName);
            System.out.println(END_MESSAGE);
            System.out.println("--------------------");
        }
    }

}
