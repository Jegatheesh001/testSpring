package springDemo.test.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import springDemo.test.messenger.service.ProfileDaoService;
import springDemo.test.messenger.vo.Profile;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileDaoService profileService = new ProfileDaoService();

	@GET
	public List<Profile> getAllProfilesGetJson() {
		List<Profile> messages = profileService.getAllProfiles();
		return messages;
	}

	@GET
	@Path("/{profileName}")
	public Profile getProfileGetJson(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}

	@POST
	public String addProfilePostJson(Profile profile) {
		profile = new Profile(profile.getProfileName(), profile.getFirstName(), profile.getLastName());
		profileService.addProfile(profile);
		return "Inserted";
	}

	@PUT
	@Path("/{profileName}")
	public String updateProfilePutJson(@PathParam("profileName") String profileName, Profile profile) {
		profile = new Profile(profileName, profile.getFirstName(), profile.getLastName());
		profileService.updateProfile(profile);
		return "Updated";
	}

	@DELETE
	@Path("/{profileName}")
	public String removeProfileDeleteText(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
		return "Deleted";
	}

}
