package main.java.controller;

import java.sql.SQLException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;

import main.java.model.PostBean;

public class PostParser extends ParserHandler {

	private String parsedValue;
	private PostBean post;
	private boolean isPost = false;
	WriteToFile savePost = new WriteToFile();

	/**
	 * parses the XML document file, prints the parse data of XML document file
	 * and adds the parsed data of XML document file to database
	 * 
	 * @param baseDir
	 * @param fileDir
	 * @param fileName
	 */
	public void parsePost(String fileName) throws SQLException {
		post = new PostBean();
		if (parseDocument(fileName)) {
			printDocument();
			System.out.println("\tParsed document successfully.");
		} else {
			System.out.println("\tException occured while parsing document.");
		}
	}

	/**
	 * overriding the default SAX start element method parses each starting XML
	 * element tag and sets the required extracted data
	 * 
	 * @param uri
	 * @param localName
	 * @param elementName
	 * @param attributes
	 */
	public void startElement(String uri, String localName, String elementName,
			Attributes attributes) throws SAXParseException {

		if (elementName.equalsIgnoreCase("sioct:BoardPost")) {
			post.setPost(attributes.getValue("rdf:about"));
		}
		if (elementName.equalsIgnoreCase("sioc:User")) {
			post.setUser(attributes.getValue("rdf:about"));
		}
		if (elementName.equalsIgnoreCase("foaf:Person")) {
			post.setMaker(attributes.getValue("rdf:about"));
		}
	}

	/**
	 * overriding the default SAX end element method parses each ending XML
	 * element tag and sets the required extracted data
	 * 
	 * @param namespaceURI
	 * @param localName
	 * @param elementName
	 */

	public void endElement(String namespaceURI, String localName,
			String elementName) {
		if (elementName.equalsIgnoreCase("dc:title") && isPost) {
			post.setTitle(parsedValue);
			isPost = false;
		}
		if (elementName.equalsIgnoreCase("dcterms:created")) {
			post.setCreated(parsedValue);
		}
		if (elementName.equalsIgnoreCase("sioc:content")) {
			post.setContent(parsedValue);
		}
	}

	/**
	 * holds extracted text data in string object
	 * 
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 */

	public void characters(char ch[], int start, int length) {
		parsedValue = new String(ch, start, length);
	}

	/**
	 * prints parsed data of XML document
	 */
	public void printDocument() {

		String tempPostID = post.getPost();
		String tempMaker = post.getMaker();
		String createdDate = post.getCreated();
		String tempPost = post.getContent().trim();
		int UserID = 0;

		if (tempMaker != null) {
			UserID = (Integer.parseInt(tempMaker.substring(
					tempMaker.indexOf('=') + 1, tempMaker.indexOf('#'))));
			savePost.createFile(UserID, createdDate, tempPost);
		}

		if (tempPost != null) {
			System.out.println("PostLink: " + tempPostID);
			System.out.println("UserLink: " + tempMaker);
			System.out.println("User: " + UserID);
			System.out.println("Created: " + createdDate);
			System.out.println("Content: " + tempPost);
		} else {
			System.out.println("There is nothing to print");
		}		
	}
}
