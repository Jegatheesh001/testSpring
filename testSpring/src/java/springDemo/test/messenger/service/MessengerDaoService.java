package springDemo.test.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import springDemo.test.messenger.database.DatabaseClass;
import springDemo.test.messenger.exception.DataNotFoundException;
import springDemo.test.messenger.vo.Message;

public class MessengerDaoService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}

	public List<Message> getAllPaginated(int start, int size) {
		List<Message> list = new ArrayList<Message>(messages.values());
		if (list.size() >= start + size)
			return list.subList(start, start + size);
		else
			return list;
	}

	public Message getMessage(Long messageId) {
		Message message = messages.get(messageId);
		if (message == null) {
			throw new DataNotFoundException("Mesage Id:" + messageId + " is not found.");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId((long) (messages.size() + 1));
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(Long id) {
		return messages.remove(id);
	}

}
