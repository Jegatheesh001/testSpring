package springDemo.test.messenger.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import springDemo.test.messenger.vo.ErrorMessage;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URI_PREFIX = "secured";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URI_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				String userName = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				if (userName.equals("user") && password.equals("password")) {
					return;
				}
			}
			Response unauthorizedresponse = Response.status(Response.Status.UNAUTHORIZED)
					.entity(new ErrorMessage("Authorization denied.", 401, "No Documents")).build();
			requestContext.abortWith(unauthorizedresponse);
		}
	}
}
