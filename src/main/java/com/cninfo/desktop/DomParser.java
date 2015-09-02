package com.cninfo.desktop;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		// 1、创建解析器工厂，工厂模式

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// 2、由工厂创建出DOM的解释器

		DocumentBuilder db = dbf.newDocumentBuilder();

		// 3、将xml文档变成内存中的dom树

		File file = new File(
				"D:\\JavaProject\\desktop\\src\\main\\resources\\student.xml");

		Document document = db.parse(file);

		NodeList list = document.getElementsByTagName("student");

		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);

			System.out.println(node.getNodeName() + "\t");

//			NamedNodeMap map = node.getAttributes();
//
//			for (int j = 0; j < map.getLength(); j++) {
//
//				Attr attr = (Attr) map.item(j);
//
//				System.out.println(attr.getName() + "\t" + attr.getValue());
//
//			}
			
			
			Element el = (Element) node;
			System.out.println(node.getNodeName()+"\t" +el.getAttribute("style"));
			
			
			
			Node n = el.getFirstChild();
			if(n.getNodeType() == node.TEXT_NODE)
			{
				n = n.getNextSibling();
				String name = n.getTextContent();//取夹住的数据
				System.out.println(name);
				
			}
		}

	}

}
