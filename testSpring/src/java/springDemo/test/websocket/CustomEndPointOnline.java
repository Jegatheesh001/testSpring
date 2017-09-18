package springDemo.test.websocket;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/messenger/online")
public class CustomEndPointOnline {

	private static Map<String, Session> queue = new LinkedHashMap<String, Session>();
	static Runnable r = () -> {
		while (true) {
			try {
				getUsers();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	static {
		new Thread(r).start();
	}

	@OnOpen
	public void open(Session session) {
		queue.put(session.getId(), session);
		getUsers();
	}

	@OnMessage
	public void onMessage(Session session, String msg) {
	}

	@OnError
	public void error(Session session, Throwable t) {
		queue.remove(session.getId());
	}

	@OnClose
	public void closedConnection(Session session) {
		queue.remove(session.getId());
		getUsers();
	}

	private static void getUsers() {
		Map<String, String> users = new CustomEndPoint().getUsers();
		int size = users.size();
		JsonObjectBuilder obj = Json.createObjectBuilder().add("online", "" + size);
		int i = 0;
		for (Entry<String, String> pair : users.entrySet()) {
			obj.add("user" + i++, pair.getValue());
		}
		JsonObject data = obj.build();
		broadcast(data);
	}

	private static void broadcast(Object data) {
		JsonObject bcData = (JsonObject) data;
		Session session;
		for (Entry<String, Session> pair : queue.entrySet()) {
			session = pair.getValue();
			if (!session.isOpen()) {
				System.err.println("Closed session: " + session.getId());
				queue.remove(session.getId());
			} else {
				sendText(session, bcData.toString());
			}
		}
		System.out.println("Braodcast Message : " + bcData);
		System.out.println("Send to " + queue.size() + " clients");
	}

	private static void sendText(Session session, String msg) {
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
