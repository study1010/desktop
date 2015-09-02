package com.cninfo.desktop;

import java.io.File;
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

		// 1、创建解析器工厂，工厂模式

		SAXParserFactory spf = SAXParserFactory.newInstance();
		

		// 2、由工厂创建出SAXP的解释器

		SAXParser sp = spf.newSAXParser();

		DefaultHandler handler = new MyHandler();
		sp.parse(new File(
				"D:\\JavaProject\\desktop\\src\\main\\resources\\student.xml"),
				handler);

		List<Student> list = ((MyHandler) handler).getList();

		for (Student s : list) {

			System.out.println(s.getId() + "\t" + s.getType() + "\t"
					+ s.getName() + "\t" + s.getBirth());
		}

	}

}

class MyHandler extends DefaultHandler {

	private List<Student> list = new ArrayList<Student>();

	private Student s;

	private String tagName;

	public List<Student> getList() {
		return list;
	}

	@Override
	public void startDocument() throws SAXException {

		System.out.println("开始解析XML文档了");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("结束解析XML文档了");

	}

	@Override
	// uri 名称空间
	// localName 前缀
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub

		if ("student".equals(qName)) {

			this.s = new Student();

			String id = attributes.getValue("id");
			String style = attributes.getValue("style");

			this.s.setId(Integer.parseInt(id));
			this.s.setType(style);

		}

		if ("name".equals(qName)) {

			tagName = "name";
		}

		if ("birth".equals(qName)) {

			tagName = "birth";
		}

	}

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
			this.list.add(this.s);
		}

	}

	// 传递文本类型的节点
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String str = new String(ch, start, length);

		if (tagName != null && tagName.length() > 0
				&& "name".equals(this.tagName)) {
			this.s.setName(str);

		}

		if (tagName != null && tagName.length() > 0
				&& "birth".equals(this.tagName)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {

				this.s.setBirth(sdf.parse(str));
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

	@Override
	public void warning(SAXParseException e) throws SAXException {
		super.warning(e);
	}

}
