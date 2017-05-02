package springDemo.test.messenger.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

import jersey.repackaged.com.google.common.collect.Iterables;
import springDemo.test.EstablishURLConnection;
import springDemo.test.messenger.vo.ErrorMessage;
import springDemo.test.messenger.vo.Message;

public class MessengerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	public String restBase() {
		return SUCCESS;
	}

	Client client = ClientBuilder.newClient();
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
		} else {
			em = response.readEntity(ErrorMessage.class);
			this.response.getWriter().write(em.getErrorCode() + " : " + em.getErrorMessage());
		}

		this.response.setContentType("text/plain;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}

	public void getMessage() throws IOException {

		Response response = singleMessageTarget.resolveTemplate("messageId", 1).request(MediaType.APPLICATION_JSON)
				.get();

		Message message = new Message();
		ErrorMessage em = new ErrorMessage();

		if (response.getStatus() == 200) {
			message = response.readEntity(Message.class);
			this.response.getWriter().write(message.getId() + " : " + message.getMessage() + "," + message.getAuthor());
		} else {
			em = response.readEntity(ErrorMessage.class);
			this.response.getWriter().write(em.getErrorCode() + " : " + em.getErrorMessage());
		}

		this.response.setContentType("text/plain;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}

	public void addMessage() throws IOException {

		Message message = new Message("message", "author");
		// Its only accepts Entity
		Response response = messageTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(message));
		message = response.readEntity(Message.class);

		this.response.setContentType("text/plain;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
		this.response.getWriter().write(message.getId() + " : " + message.getMessage() + "," + message.getAuthor());
	}
	
	public void updateMessage() throws IOException {

		Message message = new Message("message", "author");
		// Its only accepts Entity
		Response response = singleMessageTarget.request(MediaType.APPLICATION_JSON).put(Entity.json(message));
		message = response.readEntity(Message.class);

		this.response.setContentType("text/plain;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
		this.response.getWriter().write(message.getId() + " : " + message.getMessage() + "," + message.getAuthor());
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

	public static void main(String[] args) {
		Response response = new MessengerAction().messageTarget.request(MediaType.APPLICATION_JSON).get();

		List<Message> messageList = null;
		ErrorMessage em = new ErrorMessage();

		if (response.getStatus() == 200) {
			messageList = response.readEntity(new GenericType<List<Message>>() {
			});
		} else {
			em = response.readEntity(ErrorMessage.class);
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

}
