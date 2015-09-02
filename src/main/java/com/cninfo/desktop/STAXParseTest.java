package com.cninfo.desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;


public class STAXParseTest {
	private static List<Student> list = new ArrayList<Student>();

	private static Student student;

	private static String tagName = "";

	public static void main(String[] args) throws Exception {

		// 创建解析工厂
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		
		InputStream input = STAXParseTest.class.getClassLoader()
				.getResourceAsStream("student.xml");
		
		//InputStream input = new FileInputStream(new File(
				//"D:\\JavaProject\\desktop\\src\\main\\resources\\student.xml"));

		//input.skip(1024 * 1024 * 250); // 按照大小略过

		XMLStreamReader xmlStreamReader = inputFactory
				.createXMLStreamReader(input);

		// 跳过一个节点不解析
		for (int i = 1; i <= 13; i++) {
			xmlStreamReader.next(); // 按照节点个数跳过
		}

		while (xmlStreamReader.hasNext())

		{
			int event = xmlStreamReader.next();

			if (event == XMLStreamConstants.START_ELEMENT) {

				QName name = xmlStreamReader.getName();

				String str = name.getLocalPart();

				if ("student".equals(str)) {
					student = new Student();

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

			if (event == XMLStreamConstants.CHARACTERS) {

				String str = xmlStreamReader.getText();

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
