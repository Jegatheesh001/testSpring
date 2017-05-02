package springDemo.test.messenger.database;

import java.util.HashMap;
import java.util.Map;

import springDemo.test.messenger.vo.Message;
import springDemo.test.messenger.vo.Profile;

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
}
