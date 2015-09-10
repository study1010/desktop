package com.cninfo.xml;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;


//可以只解析一部分的数据，跳过其他的部分
public class STAXParseTest {
	private static List<Student> list = new ArrayList<Student>();

	private static Student student;

	private static String tagName = "";

	public static void main(String[] args) throws Exception {

		// 创建解析工厂STAX
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		//必须做一个输入流
		InputStream input = STAXParseTest.class.getClassLoader()
				.getResourceAsStream("student.xml");

		
		//流也可以跳过，跳过250M
		// input.skip(1024 * 1024 * 250); // 按照大小略过

		//字符流的对象
		XMLStreamReader xmlStreamReader = inputFactory
				.createXMLStreamReader(input);

		// 跳过一个student不解析
		for (int i = 1; i <= 13; i++) {
			xmlStreamReader.next(); // 按照节点个数跳过，每个节点一次，一次next读一次数据
		}

		while (xmlStreamReader.hasNext())

		{
			int event = xmlStreamReader.next();//返回一个事件，这个事件使用数字表示的
			
			
			
			
			//元素开始

			if (event == XMLStreamConstants.START_ELEMENT) {

				QName name = xmlStreamReader.getName(); //取元素节点

				String str = name.getLocalPart(); //取元素节点的字符串

				
				
				if ("student".equals(str)) {
					student = new Student();
					//取元素的属性
					for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {

						String key = xmlStreamReader.getAttributeLocalName(i);
						String value = xmlStreamReader.getAttributeValue(i);

						if ("id".equals(key)) {

							student.setId(Integer.parseInt(value));
						}

						if ("style".equals(key)) {

							student.setType(value);
						}

					}

				}

				if ("name".equals(str)) {

					tagName = "name";

				}

				if ("birth".equals(str)) {

					tagName = "birth";

				}

				if ("student".equals(str)) {

					list.add(student);
				}

			}
			
			//元素结束

			if (event == XMLStreamConstants.END_ELEMENT) {

				QName name = xmlStreamReader.getName();

				String str = name.getLocalPart();

				if ("name".equals(str)) {

					tagName = "";

				}

				if ("birth".equals(str)) {

					tagName = "";

				}
			}
			
			//遇到了文本类型的节点

			if (event == XMLStreamConstants.CHARACTERS) {

				String str = xmlStreamReader.getText(); //取文本节点的名字

				if (tagName != null && tagName.length() > 0
						&& "name".equals(tagName)) {
					student.setName(str);

				}

				if (tagName != null && tagName.length() > 0
						&& "birth".equals(tagName)) {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					try {

						student.setBirth(sdf.parse(str));
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}

		for (Student s : list) {
			System.out.println(s.getId() + "\t" + s.getType() + "\t"
					+ s.getName() + "\t" + s.getBirth());
		}

	}

}
