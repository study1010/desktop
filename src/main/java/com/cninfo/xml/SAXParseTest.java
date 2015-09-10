package com.cninfo.xml;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParseTest {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		// 1、创建解析器工厂，SAX工厂模式

		SAXParserFactory spf = SAXParserFactory.newInstance();

		// 2、由工厂创建出SAXP的解释器

		SAXParser sp = spf.newSAXParser();

		// DefaultHandler 处理事件的对象
		DefaultHandler handler = new MyHandler();

		sp.parse(
				SAXParseTest.class.getClassLoader().getResourceAsStream(
						"student.xml"), handler);

		List<Student> list = ((MyHandler) handler).getList();
		
		SimpleDateFormat dateFormet=new SimpleDateFormat("yyyy-MM-dd");

		for (Student s : list) {

			System.out.println(s.getId() + "\t" + s.getType() + "\t"
					+ s.getName() + "\t" + dateFormet.format(s.getBirth()));
		}

	}

}

class MyHandler extends DefaultHandler {

	private List<Student> list;

	private Student student;

	private String tagName;

	public List<Student> getList() {
		return list;
	}

	// 这两个方法一般用的很少
	@Override
	public void startDocument() throws SAXException {

		list = new ArrayList<Student>();
		System.out.println("开始解析XML文档了");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("结束解析XML文档了");

	}

	@Override
	// uri 名称空间,一般为空
	// localName 前缀：一般为空
	// qName 节点的名字
	// attributes 节点的属性
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if ("student".equals(qName)) {

			this.student = new Student();

			String id = attributes.getValue("id");
			String style = attributes.getValue("style");

			this.student.setId(Integer.parseInt(id));
			this.student.setType(style);

		}

		if ("name".equals(qName)) {

			tagName = "name";
		}

		if ("birth".equals(qName)) {

			tagName = "birth";
		}

	}

	// 元素结束标签没有传递属性
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if ("name".equals(qName)) {
			this.tagName = "";
		}

		if ("birth".equals(qName)) {
			this.tagName = "";
		}

		if ("student".equals(qName)) {
			this.list.add(this.student);
		}

	}

	
	//静态的方法只能访问静态的内部类
	
	// 传递文本类型的节点
	@Override
	public void characters(char[] ch, int start, int length)// 传递文本的字符串，字符串从start开始，从end结束
			throws SAXException {

		String str = new String(ch, start, length);

		if (tagName != null && tagName.length() > 0
				&& "name".equals(this.tagName)) {
			this.student.setName(str);

		}

		if (tagName != null && tagName.length() > 0
				&& "birth".equals(this.tagName)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {

				this.student.setBirth(sdf.parse(str));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	public void error(SAXParseException e) throws SAXException {
		super.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		super.fatalError(e);
	}

	// 解析器在解析XML的过程中如果出现了警告，则调用这个方法，或者写日志
	@Override
	public void warning(SAXParseException e) throws SAXException {
		super.warning(e);
	}

}
