package com.cninfo.desktop;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DomParse2 {

	public static void main(String[] args) throws Exception {

		// 1、创建解析器工厂，工厂模式

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// 2、由工厂创建出DOM的解释器

		DocumentBuilder db = dbf.newDocumentBuilder();

		// 3、将xml文档变成内存中的dom树

		File file = new File(
				"D:\\JavaProject\\desktop\\src\\main\\resources\\student.xml");

		Document document = db.parse(file);
		
		Element root = document.getDocumentElement();

		visitNode(root);
	}

	// 深度优先搜索
	public static  void visitNode(Node el) {

		System.out.println(el.getNodeName());
		NamedNodeMap map = el.getAttributes();

		if(map != null)
		{
			for (int i = 0; i < map.getLength(); i++) {

				Attr attr = (Attr) map.item(i);

				System.out.println(attr.getName() + "\t" + attr.getValue());

			}
		}
	

		Node node = el.getFirstChild();
		while (node != null) {
			
			visitNode(node);
			node = node.getNextSibling();
		}

	}

}
