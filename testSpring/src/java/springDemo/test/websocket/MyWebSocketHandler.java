package springDemo.test.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class MyWebSocketHandler {
	Session session;
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
		sendResponse("Close: statusCode=" + statusCode + ", reason=" + reason);
	}

	@OnWebSocketError
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		this.session = session;
		System.out.println("Connect: " + session.getRemoteAddress().getAddress());
		boolean present = false;
		for (Session client : clients) {
			if (client.equals(session)) {
				present = false;
				break;
			}
		}
		if (!present)
			clients.add(session);
		sendResponse("You are Online");

	}

	@OnWebSocketMessage
	public void onMessage(String message) {
		System.out.println("Message: " + message);
		if (message.equals("##00##")) {
			sendResponse("Connection Closed");
		} else
			sendResponse(message);
	}

	private void sendResponse(String response) {
		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				try {
					client.getRemote().sendString(response);
				} catch (IOException e) {
				}
			}
		}
	}
}
