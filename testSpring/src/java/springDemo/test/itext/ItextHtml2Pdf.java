package springDemo.test.itext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ItextHtml2Pdf {

	public static String HTML = "";
	public static String CSS = "";

	public static final String DEST = "html_page_size.pdf";

	public static void main(String[] args) {

		String contextPath = "E:/apache-tomcat-7.0.73/webapps/testSpring/";
		String filePath = contextPath + "src/java/springDemo/test/itext/";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath + "SampleHTML.html"));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			HTML = contentBuilder.toString();

			in = new BufferedReader(new FileReader(filePath + "SampleCSS.css"));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			CSS = contentBuilder.toString();

			in.close();
		} catch (IOException e) {
		}

		ElementList el = null;
		try {
			el = XMLWorkerHelper.parseToElementList(HTML, CSS);
			System.out.println(el);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// I define a width of 200pt
		float width = 200;
		// I define the height as 10000pt (which is much more than I'll ever
		// need)
		float max = 10000;
		// I create a column without a `writer` (strange, but it works)
		ColumnText ct = new ColumnText(null);
		ct.setSimpleColumn(new Rectangle(width, max));
		for (Element e : el) {
			ct.addElement(e);
		}
		// I add content in simulation mode
		try {
			ct.go(true);
		} catch (DocumentException e2) {
			e2.printStackTrace();
		}
		// Now I ask the column for its Y position
		float y = ct.getYLine();

		Rectangle pagesize = new Rectangle(width, max - y);
		// step 1
		Document document = new Document(pagesize, 0, 0, 0, 0);
		// step 2
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(new File(DEST)));
		} catch (FileNotFoundException | DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// step 3
		document.open();
		// step 4
		ct = new ColumnText(writer.getDirectContent());
		ct.setSimpleColumn(pagesize);
		for (Element e : el) {
			ct.addElement(e);
		}
		try {
			ct.go();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		// step 5
		document.close();
	}

}
