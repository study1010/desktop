package com.cninfo.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser2 {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		// 1、创建解析器工厂，工厂模式

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// 2、由工厂创建出DOM的解释器，java对象

		DocumentBuilder db = dbf.newDocumentBuilder();

		// 3、将xml文档变成内存中的dom树

		// 文件在项目中可以使用相对路径，可以随着项目一起打包

		// File file=new File("student.xml");
		// db.parse(file);

		InputStream inputStream = DomParser2.class.getClassLoader()
				.getResourceAsStream("student.xml");

		Document document = db.parse(inputStream); // document指向了document的根节点

		// 获根据标签名取节点的list
		NodeList list = document.getElementsByTagName("student");

		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);

			Element el = (Element) node;

			// node.getNodeName() 获取节点的标签名
			System.out.println(el.getNodeName() + "\t"
					+ el.getAttribute("style"));

			// 获取当前节点的第一个孩子
			Node firstChirldNode = el.getFirstChild();
			
			if (firstChirldNode.getNodeType() == Node.TEXT_NODE) {
				Node nameNode = firstChirldNode.getNextSibling();//获取第一个孩子的兄弟节点
				
				
				//取一个标签夹的数据的两种方法：
				
				System.out.println(nameNode.getFirstChild().getNodeName()+":"+nameNode.getFirstChild().getNodeValue());// 取文本数据的两种方法

				String name = nameNode.getTextContent();// 取夹住的数据
				System.out.println(nameNode.getNodeName()+"标签夹的数据是："+name);

				System.out.println("");

			}

		}

	}

}
