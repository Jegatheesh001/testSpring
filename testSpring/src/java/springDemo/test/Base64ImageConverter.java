package springDemo.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64ImageConverter {

	public static void main(String[] args) {
		String base64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEYAAAA3CAIAAAA+Mqu3AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAB9SURBVGhD7c8xCsAwDATB/P/Tjop7gEHERGa2UyGOedZ1IU0IaUJIE0KaEFI9HC/D2yHdTcr9WZlBqpCQOmUGqUJC6pQZpAoJqVNmkCokpE6ZQaqQkDplBqlCQuqUGaQKCalTZk6SjpXh7ZCuJP0/pAkhTQhpQkgTuo601gsPRkHxCuQ/rwAAAABJRU5ErkJggg==";
		convertBase64ToImage(base64, "check.jpg");
		System.out.println("data:image/png;base64,"
				+ convertImageToBase64(new File("").getAbsolutePath() + "/webapps/testSpring/" + "sign.png"));
	}

	public static String convertBase64ToImage(String base64Data, String fileName) {
		String imageData = base64Data.substring(base64Data.indexOf(",") + 1);
		try {
			byte[] imageByteArray = Base64.getDecoder().decode(imageData);
			FileOutputStream imageOutFile = new FileOutputStream(fileName);
			imageOutFile.write(imageByteArray);
			imageOutFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public static String convertImageToBase64(String fileName) {
		String base64Data = "";
		FileInputStream fis = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024]; // 1KB
		try {
			fis = new FileInputStream(fileName);
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			bos.close();
			fis.close();
		} catch (IOException ex) {
		}
		base64Data = Base64.getEncoder().encodeToString(bos.toByteArray());
		return base64Data;
	}

}
