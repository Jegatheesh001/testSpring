package springDemo.test.rosette.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import springDemo.test.rosette.vo.NameTranslationResponse;

import com.opensymphony.xwork2.ActionSupport;

import springDemo.test.messenger.action.MessengerAction;
import springDemo.test.rosette.vo.NameTranslationRequest;

public class TranslatorMainAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	final static String APIKEY = "f3dfc3f422221321acc6c5d05cca41a6";

	public void translateBase() throws IOException {

		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("https://api.rosette.com:443/rest/v1/name-translation/");
		NameTranslationRequest request = new NameTranslationRequest("Jegatheesh", "ara");
		Response response = baseTarget.request(MediaType.APPLICATION_JSON).header("X-RosetteAPI-Key", APIKEY)
				.post(Entity.json(request));

		System.out.println(response.getStatus());
		String res = response.readEntity(String.class);
		System.out.println(res);

		this.response.getWriter().write(res);
		this.response.setContentType("application/json;charset=UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("https://api.rosette.com:443/rest/v1/name-translation/");
		NameTranslationRequest request = new NameTranslationRequest("Jegatheesh", "ara");
		Response response = baseTarget.request(MediaType.APPLICATION_JSON).header("X-RosetteAPI-Key", APIKEY)
				.post(Entity.json(request));

		System.out.println(response.getStatus());
		String res = response.readEntity(String.class);

		NameTranslationResponse resp = null;
		resp = MessengerAction.mapJsonToClass(NameTranslationResponse.class, res);

		if (response.getStatus() == 200) {
			System.out.println(res);
			// System.out.println(resp.getTranslation());
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
