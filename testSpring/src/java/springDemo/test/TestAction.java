package springDemo.test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;
	FTPClient ftpClient = null;

	public static void main(String[] args) {

		TestAction obj = new TestAction();
		obj.ftpClient = new FTPClient();
		// obj.ftpClient.connect("10.162.3.185", 22);
		try {
			obj.ftpClient.connect("192.168.1.12", 22);
			obj.ftpClient.login("MEDAS/jegatheesh", "jegatheesh2017");
			obj.ftpClient.enterLocalPassiveMode();
			// if(obj.ftpClient.isConnected())
			// System.out.println("success :: connection established");
			// else
			// System.out.println("error :: could not connect to server");
			obj.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			OutputStream outputStream1 = new BufferedOutputStream(
					new FileOutputStream("C:\\Users\\admin\\Downloads\\MPR_October_2016_Arjun.docx"));// file
																										// to
																										// recieve
			boolean status = obj.ftpClient.retrieveFile(
					"D:\\ICAS\\gpf_middle_ware_folder\\recieved_MPR_October_2016_Arjun.docx", outputStream1);// new
																												// file
																												// name
			outputStream1.close();
			if (obj.ftpClient.isConnected()) {
				obj.ftpClient.logout();
				obj.ftpClient.disconnect();
			}
			System.out.println("status for ftp transfer : " + status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int[] doubleDig(String cardNum, int length) {
		int[] nums = new int[length];
		int num;
		for (int x = 0; x < cardNum.length(); x++) {
			nums[x] = Integer.parseInt(cardNum.substring(x, x + 1));
		} // makes array
		System.out.println(nums.length);
		return nums;

	}

	// --------------------- Getters & Setters ----------------------//

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
