package com.cninfo.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser1 {

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

		InputStream inputStream = DomParser1.class.getClassLoader()
				.getResourceAsStream("student.xml");

		Document document = db.parse(inputStream); // document指向了document的根节点

		
		// 获根据标签名取节点的list
		NodeList list = document.getElementsByTagName("student");
		
	

		for (int i = 0; i < list.getLength(); i++) {
			
			//Element是Node的子接口
			Node node = list.item(i);

			//node.getNodeName() 获取节点的标签名
			System.out.println(node.getNodeName() + "\t");

			// 获取节点的属性
			NamedNodeMap map = node.getAttributes();

			for (int j = 0; j < map.getLength(); j++) {

				Attr attr = (Attr) map.item(j);

				System.out.println(attr.getName() + "\t" + attr.getValue());
			}

			

		}

	}

}
