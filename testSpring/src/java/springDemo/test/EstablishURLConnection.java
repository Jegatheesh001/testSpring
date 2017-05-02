package springDemo.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import springDemo.admin.action.URLConnection;

public class EstablishURLConnection extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	public static void main(String[] args) {
		String mobile = "971588103632";
		String email = "jegatheesh93@gmail.com";
		String auth = "mobile=" + mobile + "&password=336699";
		new EstablishURLConnection().checkBalance("http://doo.ae/api/balance.php?" + auth, "");
		new EstablishURLConnection().getPassword("http://doo.ae/api/forgetPassword.php?" + "mobile=" + mobile, "");
		// new EstablishURLConnection().getPassword("http://doo.ae/api/forgetPassword.php?" + "email=" + email, "");
		new EstablishURLConnection()
				.sendSMS("http://doo.ae/api/msgSend.php?" + auth + "&numbers=971588105612" + "&sender=Test%20Msg"
						+ "&msg=062706470644062700200648063306470644062700200628064300200645063900200064006F006F002E00610065"
						+ "&applicationType=3", "");
	}

	public String establishConnection(String url, String urlParameters) {
		String response = null;
		try {
			response = new URLConnection().sendGet(url, urlParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String checkBalance(String url, String urlParameters) {
		String response = new EstablishURLConnection().establishConnection(url, urlParameters);
		StringBuilder out = new StringBuilder("");
		String[] splits = response.split("/");
		if (response != null) {
			if (splits.length == 2) {
				out.append("Balance : ");
				out.append(splits[1]);
				out.append(" - ");
				out.append("Total : ");
				out.append(splits[0]);
			} else {
				String output = null;
				switch (response) {
				case "1": {
					output = "Your mobile number is incorrect.";
					break;
				}
				case "2": {
					output = "Your Password is incorrect.";
					break;
				}
				default: {
					output = "UnKnown Response From Server.";
					break;
				}
				}
				out.append(output);
			}
		}

		System.out.println(out.toString());
		return out.toString();
	}

	public String sendSMS(String url, String urlParameters) {
		String response = new EstablishURLConnection().establishConnection(url, urlParameters);
		String output = "";
		switch (response) {
		case "-2": {
			output = "connection failed to Doo.ae server.";
			break;
		}
		case "-1": {
			output = "connection failed to Doo.ae Database.";
			break;
		}
		case "1": {
			output = "SMS message sent successfully.";
			break;
		}
		case "2": {
			output = "No Balance.";
			break;
		}
		case "3": {
			output = "Your balance is not enough.";
			break;
		}
		case "4": {
			output = "Your mobile number is incorrect.";
			break;
		}
		case "5": {
			output = "Your Password is incorrect.";
			break;
		}
		default: {
			output = "UnKnown Response From Server.";
			break;
		}
		}
		System.out.println(output);
		return output;
	}

	public String getPassword(String url, String urlParameters) {
		String response = new EstablishURLConnection().establishConnection(url, urlParameters);
		String output = "";
		switch (response) {
		case "1": {
			output = "Your mobile number is incorrect.";
			break;
		}
		case "2": {
			output = "Your email is incorrect.";
			break;
		}
		case "3": {
			output = "Password sent to mobile number successfully.";
			break;
		}
		case "4": {
			output = "Your account is not enough to send the new password as SMS.";
			break;
		}
		case "5": {
			output = "Password sent to email successfully.";
			break;
		}
		default: {
			output = "UnKnown Response From Server.";
			break;
		}
		}
		System.out.println(output);
		return output;
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
