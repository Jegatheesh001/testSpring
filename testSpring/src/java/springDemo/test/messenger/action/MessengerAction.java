package springDemo.test.messenger.action;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.internal.util.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import springDemo.test.messenger.vo.ErrorMessage;
import springDemo.test.messenger.vo.Message;

public class MessengerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	Integer messageId;

	public String restBase() {
		return SUCCESS;
	}

	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {

		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {

		}
	} };

	HostnameVerifier hv = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};

	public SSLContext getSSLContext() {
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return sc;
	}

	// Client client = ClientBuilder.newClient(); // Normal Connection
	Client client = new JerseyClientBuilder().sslContext(getSSLContext()).withConfig(new ClientConfig())
			.hostnameVerifier(hv).build(); // SSL Connection
	String context = "https://localhost:8080/testSpring";
	WebTarget baseTarget = client.target(context + "/webapi");
	WebTarget messageTarget = baseTarget.path("messages");
	WebTarget singleMessageTarget = messageTarget.path("{messageId}");

	public void getAllMessages() throws IOException {

		Response response = messageTarget.request(MediaType.APPLICATION_JSON).get();

		List<Message> messageList = null;
		ErrorMessage em = new ErrorMessage();

		if (response.getStatus() == 200) {
			messageList = response.readEntity(new GenericType<List<Message>>() {
			});
			for (Message message : messageList)
				this.response.getWriter().write(message.getAuthor() + " : " + message.getMessage());
		} else {
			em = response.readEntity(ErrorMessage.class);
			this.response.getWriter().write(em.getErrorCode() + " : " + em.getErrorMessage());
		}

		this.response.setContentType("text/plain;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}

	public void getMessage() throws IOException {
		if (messageId == null)
			messageId = 1;

		Response response = singleMessageTarget.resolveTemplate("messageId", messageId)
				.request(MediaType.APPLICATION_JSON).get();

		Message message = new Message();
		ErrorMessage em = new ErrorMessage();

		Gson gson = new Gson();

		this.response.setContentType("application/json");
		this.response.setCharacterEncoding("UTF-8");

		if (response.getStatus() == 200) {
			message = response.readEntity(Message.class);
			this.response.getWriter().write(gson.toJson(message));
		} else {
			em = response.readEntity(ErrorMessage.class);
			this.response.getWriter().write(gson.toJson(em));
		}

	}

	public void addMessage() throws IOException {

		Message message = new Message("message", "author");
		// Its only accepts Entity
		Response response = messageTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(message));
		message = response.readEntity(Message.class);
		
		Gson gson = new Gson();

		this.response.setContentType("application/json");
		this.response.setCharacterEncoding("UTF-8");
		this.response.getWriter().write(gson.toJson(message));
	}

	public void updateMessage() throws IOException {

		Message message = new Message("message", "author");
		// Its only accepts Entity
		Response response = singleMessageTarget.request(MediaType.APPLICATION_JSON).put(Entity.json(message));
		message = response.readEntity(Message.class);

		Gson gson = new Gson();

		this.response.setContentType("application/json");
		this.response.setCharacterEncoding("UTF-8");
		this.response.getWriter().write(gson.toJson(message));
	}

	public static <T> T mapJsonToClass(Class<T> clazz, String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		T resp = null;
		try {
			resp = (T) mapper.readValue(jsonString, clazz);
		}
		// If provided String is not Json
		catch (JsonParseException e) {
			e.printStackTrace();
		}
		// If unable to map with given class
		catch (JsonMappingException e) {
			e.printStackTrace();
		}
		// Other errors
		catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {

		Response response = new MessengerAction().messageTarget.request(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic " + Base64.encodeAsString("user:password")).get();

		List<Message> messageList = null;
		ErrorMessage em = new ErrorMessage();

		if (response.getStatus() == 200) {
			messageList = response.readEntity(new GenericType<List<Message>>() {
			});
			for (Message message : messageList)
				System.out.println(message.getAuthor() + " : " + message.getMessage());
		} else {
			em = response.readEntity(ErrorMessage.class);
			System.out.println(em.getErrorCode() + " : " + em.getErrorMessage());
		}

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

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

}
