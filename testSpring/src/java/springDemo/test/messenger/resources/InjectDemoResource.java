package springDemo.test.messenger.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("/annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
			// Header values
			@HeaderParam("Content-Type") String contentType,
			// cookie value
			@CookieParam("jsessionid") String jsessionid
	// value from form submit //,@FormParam("textbox") String textboxvalue
	) {
		return "matring param - " + matrixParam + "&Input Content-Type - " + contentType;
	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		for (List<String> h : headers.getRequestHeaders().values()) {
			System.out.println(h);
		}
		for (Cookie h : headers.getCookies().values()) {
			System.out.println(h.getName() + "-" + h.getValue());
		}
		return "Context path : " + path;
	}

}
