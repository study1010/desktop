package com.cninfo.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XpathTest {

	public static void main(String[] args) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();

		InputStream inputStream = DomParser1.class.getClassLoader()
				.getResourceAsStream("student.xml");

		Document document = db.parse(inputStream);

		// XPath

		XPathFactory xpf = XPathFactory.newInstance();

		XPath xpath = xpf.newXPath();
		String sql = "//name";

		//第三个参数指定返回值
		NodeList list = (NodeList) xpath.evaluate(sql, document,
				XPathConstants.NODESET);
		
		for(int i =0;i<list.getLength();i++)
		{
			Node node =list.item(i);
			String value=node.getTextContent();
			System.out.println(value);
			
		}

	}

}
