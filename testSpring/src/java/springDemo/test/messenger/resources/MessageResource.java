package springDemo.test.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import springDemo.test.messenger.service.MessengerDaoService;
import springDemo.test.messenger.vo.Link;
import springDemo.test.messenger.vo.Message;
import springDemo.test.messenger.vo.MessageFilterBean;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class MessageResource {

	private @BeanParam MessageFilterBean filter;
	MessengerDaoService messengerService = new MessengerDaoService();

	@GET
	public List<Message> getAllMessagesGetJson() {
		if (filter.getYear() > 0) {
			return messengerService.getAllMessagesForYear(filter.getYear());
		}
		if (filter.getSize() > 0) {
			return messengerService.getAllPaginated(filter.getStart(), filter.getSize());
		}
		List<Message> messages = messengerService.getAllMessages();
		return messages;
	}

	@GET
	@Path("/{messageId}")
	public Message getMessageGetJson() {
		Message message = messengerService.getMessage(filter.getMessageId());
		message.setLinks(new ArrayList<Link>());
		message.addLink(getUriForSelf(filter, message), "self");
		message.addLink(getUriForProfile(filter, message), "profile");
		message.addLink(getUriForComments(filter, message), "comments");
		return message;
	}

	private String getUriForSelf(MessageFilterBean filter, Message message) {
		String uri = filter.getUriInfo().getBaseUriBuilder().path(MessageResource.class)
				.path(filter.getMessageId() + "").build().toString();
		return uri;
	}

	private String getUriForProfile(MessageFilterBean filter, Message message) {
		String uri = filter.getUriInfo().getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor())
				.build().toString();
		return uri;
	}

	private String getUriForComments(MessageFilterBean filter, Message message) {
		String uri = filter.getUriInfo().getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource").path(CommentResource.class)
				// For MessageId
				.resolveTemplate("messageId", message.getId()).build().toString();
		return uri;
	}

	@POST
	public Response addMessagePostJson(Message message) throws Throwable {
		message = new Message(message.getMessage(), message.getAuthor());
		message = messengerService.addMessage(message);
		URI uri = filter.getUriInfo().getAbsolutePathBuilder().path(message.getId() + "").build();
		// set location and custom response
		return Response.created(uri).entity(message).build();
	}

	@PUT
	@Path("/{messageId}")
	public String updateMessagePutJson(Message message) {
		long messageId = filter.getMessageId();
		message = new Message(messageId, message.getMessage(), message.getAuthor());
		messengerService.updateMessage(message);
		return "Updated";
	}

	@DELETE
	@Path("/{messageId}")
	public String removeMessageDeleteText() {
		long messageId = filter.getMessageId();
		messengerService.removeMessage(messageId);
		return "Deleted";
	}

	// For Comments
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}
