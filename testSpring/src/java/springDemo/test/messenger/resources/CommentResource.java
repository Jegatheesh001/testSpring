package springDemo.test.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import springDemo.test.messenger.service.CommentDaoService;
import springDemo.test.messenger.vo.Comment;
import springDemo.test.messenger.vo.MessageFilterBean;

@Path(value = "/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	CommentDaoService commentService = new CommentDaoService();

	@GET
	public List<Comment> getAllComments(@BeanParam MessageFilterBean filter) {
		return commentService.getAllMessages(filter.getMessageId());
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(@BeanParam MessageFilterBean filter) {
		return commentService.getMessage(filter.getMessageId(), filter.getCommentId());
	}

}
