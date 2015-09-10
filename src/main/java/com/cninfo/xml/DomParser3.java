package com.cninfo.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DomParser3 {

	public static void main(String[] args) throws Exception {

		// 1、创建解析器工厂，工厂模式

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// 2、由工厂创建出DOM的解释器

		DocumentBuilder db = dbf.newDocumentBuilder();

		// 3、将xml文档变成内存中的dom树

		InputStream input = DomParser3.class.getClassLoader()
				.getResourceAsStream("student.xml");

		Document document = db.parse(input);

		// 直接返回文档节点的子节点，也就是students节点，节点一旦取，就是取一对的
		Element root = document.getDocumentElement();

		visitNode(root);
	}

	// 采用递归实现树的深度优先搜索
	public static void visitNode(Node el) {

		// 打印根节点的名字，文本节点没有标签名，有的话只有值
		System.out.println(el.getNodeName());
		// 获取跟节点的属性
		NamedNodeMap map = el.getAttributes();

		// 遍历属性,有些节点是没有属性，需要判断一下null值
		if (map != null) { 
			for (int i = 0; i < map.getLength(); i++) {

				Attr attr = (Attr) map.item(i);

				System.out.println(attr.getName() + "\t" + attr.getValue());

			}
		}

		Node node = el.getFirstChild();// 遍历第一个孩子
		while (node != null) {

			visitNode(node);// 遍历第一个孩子的后代
			node = node.getNextSibling();// 遍历第一个孩子的兄弟
		}

	}

}
