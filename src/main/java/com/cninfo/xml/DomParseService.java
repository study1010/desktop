package com.cninfo.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParseService {

	public static void main(String[] args) {

		List<Book> list = getBooks();

		for (Iterator<Book> iterator = list.iterator(); iterator.hasNext();) {
			Book book = iterator.next();

			System.out.println(book.getId() + "\t" + book.getName() + "\t"
					+ book.getPrice());

		}

	}

	public static List<Book> getBooks() {
		List<Book> list = new ArrayList<Book>();

		// 创建解析器工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2、由工厂创建出DOM的解释器

		DocumentBuilder builder = null;
		
		
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// 3、将xml文档变成内存中的dom树

		InputStream input = DomParseService.class.getClassLoader()
				.getResourceAsStream("book.xml");
		Document document = null;
		try {
			document = builder.parse(input);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		NodeList bookNodes = document.getElementsByTagName("book");

		for (int i = 0; i < bookNodes.getLength(); i++) {
			Element bookElement = (Element) bookNodes.item(i);

			Book book = new Book();

			book.setId(Integer.parseInt(bookElement.getAttribute("id")));
			NodeList childNodes = bookElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {

				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

					if ("name".equals(childNodes.item(j).getNodeName())) {

						book.setName(childNodes.item(j).getFirstChild()
								.getNodeValue());
					} else if ("price".equals(childNodes.item(j).getNodeName()))
						book.setPrice(Float.parseFloat(childNodes.item(j)
								.getFirstChild().getNodeValue()));

				}

			}

			list.add(book);
		}

		return list;
	}

}
