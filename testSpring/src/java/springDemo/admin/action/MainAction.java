package springDemo.admin.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import springDemo.admin.vo.Mails;
import springDemo.admin.vo.UserBean;
import springDemo.test.Base64ImageConverter;
import springDemo.test.messenger.action.MessengerAction;

import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";

	HttpServletRequest request;
	HttpServletResponse response;

	File[] image;
	String[] imageFileName;
	String[] imageContentType;
	String[] imageDescription;
	String users;

	String inputString;
	Integer inputInteger;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	Mails mails;
	MainThread mainThread;
	MailSender mailService = new MailSender();

	public String mainPage() {
		return "success";
	}

	public String openMail() {
		if (mails != null) {
			Authenticator auth = new SMTPAuthenticator();
			return "view";
		}
		return "success";
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(mails.getMailId(), mails.getPassword());
		}
	}

	public String viewMail() {
		if (mails == null) {
			mails = new Mails();
		}

		// ------------For Mail------------------//
		mails.setSubject("Audit Report Issued : ");
		String msgcontent = "Dear Sir/Madam," + "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "Audit Report of AFS Year " + "2015-2016" + " has been Issued."
				/*
				 * +
				 * "<br/><br/><a href='http://aims.ksad.kerala.gov.in/esubmission'>Click here</a> to view details."
				 */
				+ "<br/><br/><br/><br/>" + "Regards,<br/><i>Kerala State Audit Department.</i>" + "";

		mails.setDetails(msgcontent);
		mails.setMailto("jegatheesh93@gmail.com,mj.promotionid@gmail.com");
		mails.setCc("jegatheesh93@gmail.com,mj.promotionid@gmail.com");
		mails.setBcc("jegatheesh93@gmail.com,mj.promotionid@gmail.com");

		return "success";
	}

	public String sendMail() {
		// Thread
		mainThread = new MainThread();
		mainThread.setThreadName("sendMail");
		mainThread.setMails(mails);
		mainThread.start();
		// ----------------------------//
		return "success";
	}

	public String triggerApplication() {
		String cmd = "C:/Program Files (x86)/Notepad++/notepad++.exe";

		try {
			Runtime rt;
			rt = Runtime.getRuntime();
			Process p = rt.exec(cmd);
			System.out.println("Exit Value = " + p.waitFor());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String readFiles() {
		File folder = new File("E:/apache-tomcat-7.0.73/webapps/testSpring/src/java");
		String path = request.getParameter("path");
		listFilesForFolder(folder);
		List<String> filePaths = listFilesForFolderWithExtension(folder, "global_ar", "properties");
		System.out.println(files);
		readFile(filePaths.get(0));
		return SUCCESS;
	}

	public void listFilesForFolder(File folder) {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
			}
		}
	}

	List<String> files;
	List<String> filePaths;

	public List<String> listFilesForFolderWithExtension(File folder, String startsWith, String extension) {
		if (files == null) {
			files = new ArrayList<String>();
			filePaths = new ArrayList<String>();
		}
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolderWithExtension(fileEntry, startsWith, extension);
			} else {
				if (startsWith == null && extension == null) {
					files.add(fileEntry.getName());
					filePaths.add(fileEntry.getAbsolutePath());
				} else if (startsWith != null && extension != null) {
					if (fileEntry.getName().startsWith(startsWith) && fileEntry.getName().endsWith("." + extension)) {
						files.add(fileEntry.getName());
						filePaths.add(fileEntry.getAbsolutePath());
					}
				} else if (startsWith == null) {
					if (fileEntry.getName().endsWith("." + extension)) {
						files.add(fileEntry.getName());
						filePaths.add(fileEntry.getAbsolutePath());
					}
				} else {
					if (fileEntry.getName().startsWith(startsWith)) {
						files.add(fileEntry.getName());
						filePaths.add(fileEntry.getAbsolutePath());
					}
				}
			}
		}
		return filePaths;
	}

	public void readFile(String filePath) {
		StringBuilder fileContents = new StringBuilder("");
		String line = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while ((line = br.readLine()) != null) {
				fileContents.append(line + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			fileContents = new StringBuilder("Error at : " + line);
			e.printStackTrace();
		}
		System.out.println(fileContents);
	}

	public String viewServlet() {
		return "success";
	}

	public String regexPage() {
		return "success";
	}

	public String uploadMultipleFiles() {
		return SUCCESS;
	}

	public String multipleFileUpload() {
		if (image != null) {
			imageDescription = new String[image.length];
			for (int i = 0; i < image.length; i++) {
				imageDescription[i] = imageFileName[i] + " - " + imageContentType[i];
			}
		}
		return SUCCESS;
	}

	public String testAngularJS() {
		return SUCCESS;
	}

	public String customTagLib() {
		return SUCCESS;
	}

	public String decryptString() {
		System.out.println(inputString);
		System.out.println(inputInteger);
		return SUCCESS;
	}

	public String viewGoogleMaps() {
		return SUCCESS;
	}

	public String viewChart() {
		return SUCCESS;
	}

	public String dencode() {
		return SUCCESS;
	}

	public String htmlDragger() {
		return SUCCESS;
	}
	
	public String base64ToImage() {
		return SUCCESS;
	}
	
	public String createImageFromBase64() {
		String base64Data = request.getParameter("base64Data");
		String fileName = request.getParameter("fileName");
		new Base64ImageConverter().convertBase64ToImage(base64Data, fileName);
		return SUCCESS;
	}
	
	public void getBase64FromImage() throws IOException {
		String fileName = request.getParameter("fileName");
		String base64Data = "data:image/png;base64," + new Base64ImageConverter().convertImageToBase64(fileName);
		this.response.setContentType("text/plain;charset=UTF-8");
		// this.response.setCharacterEncoding("UTF-8");
		this.response.getWriter().write(base64Data);
	}
	
	public String wsClient() {
		return SUCCESS;
	}

	public String postJSONArray() {
		if (users != null) {
			UserBean user = new UserBean();
			List<UserBean> userList = new ArrayList<UserBean>();
			JSONArray array = new JSONArray(users);
			for (Object obj : array) {
				user = MessengerAction.mapJsonToClass(UserBean.class, obj.toString());
				userList.add(user);
				System.out.println(user.getUserName());
			}
		}
		return SUCCESS;
	}

	public static void main(String[] args) {
		String cmd = "C:/Program Files (x86)/Notepad++/notepad++.exe";
		try {
			Runtime rt;
			rt = Runtime.getRuntime();
			Process p = rt.exec(cmd);
			System.out.println("Exit Value = " + p.waitFor());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// --------------------- Getters & Setters ----------------------//

	/**
	 * @return the mails
	 */
	public Mails getMails() {
		return mails;
	}

	/**
	 * @param mails
	 *            the mails to set
	 */
	public void setMails(Mails mails) {
		this.mails = mails;
	}

	/**
	 * @return the image
	 */
	public File[] getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(File[] image) {
		this.image = image;
	}

	/**
	 * @return the imageFileName
	 */
	public String[] getImageFileName() {
		return imageFileName;
	}

	/**
	 * @param imageFileName
	 *            the imageFileName to set
	 */
	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * @return the imageContentType
	 */
	public String[] getImageContentType() {
		return imageContentType;
	}

	/**
	 * @param imageContentType
	 *            the imageContentType to set
	 */
	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	/**
	 * @return the imageDescription
	 */
	public String[] getImageDescription() {
		return imageDescription;
	}

	/**
	 * @param imageDescription
	 *            the imageDescription to set
	 */
	public void setImageDescription(String[] imageDescription) {
		this.imageDescription = imageDescription;
	}

	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public Integer getInputInteger() {
		return inputInteger;
	}

	public void setInputInteger(Integer inputInteger) {
		this.inputInteger = inputInteger;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

}
