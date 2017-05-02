package springDemo.test.messenger.vo;

import java.util.Date;
import java.util.List;

public class MessageEntity {

	Long id;
	String message;
	Date created;
	String author;
	String href;
	List<Link> links;
}
