package springDemo.test.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import springDemo.test.messenger.database.DatabaseClass;
import springDemo.test.messenger.exception.DataNotFoundException;
import springDemo.test.messenger.vo.Comment;
import springDemo.test.messenger.vo.ErrorMessage;
import springDemo.test.messenger.vo.Message;

public class CommentDaoService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllMessages(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return (ArrayList<Comment>) comments.values();
	}

	public Comment getMessage(long messageId, long commentId) {
		ErrorMessage error = new ErrorMessage("Resource :" + messageId + " is not found.", 404, "No Documents");
		Response response= Response.status(Status.NOT_FOUND).entity(error).build();
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}
}
