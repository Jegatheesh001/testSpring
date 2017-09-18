package springDemo.test.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("secured")
public class SecuredResource {

	@GET
	public String getSecuredAccess() {
		return "Secured Access";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
