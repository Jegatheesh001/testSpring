package springDemo.test.mongodb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.mongodb.MongoClient;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.opensymphony.xwork2.ActionSupport;

public class MongoDBAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	public static void main(String[] args) {
		try {

			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("mydb");
			System.out.println("Connect to database successfully");

			db.createCollection("mycol", null);
			System.out.println("Collection created successfully");

			DBCollection dbc = db.getCollectionFromString("mycol");
			System.out.println("Collection mycol selected successfully");

			BasicDBObject doc = new BasicDBObject().append("title", "MongoDB").append("description", "database")
					.append("likes", 100).append("url", "http://www.tutorialspoint.com/mongodb/")
					.append("by", "tutorials point");
			//dbc.insert(doc);
			System.out.println("Record inserted successfully");

			DBObject first = dbc.findOne();
			//dbc.remove(first);
			System.out.println("First Record deleted successfully");

			DBCursor cursor = dbc.find();
			int i = 1;

			while (cursor.hasNext()) {
				System.out.println("Inserted Document: " + i);
				System.out.println(cursor.next());
				i++;
			}

			cursor = dbc.find();
			while (cursor.hasNext()) {
				DBObject updateDocument = cursor.next();
				updateDocument.put("likes", Integer.parseInt(updateDocument.get("likes").toString()) + 10);
				dbc.update(new BasicDBObject().append("_id", updateDocument.get("_id")), updateDocument);
			}

			System.out.println("Document updated successfully");
			cursor = dbc.find();

			i = 1;
			while (cursor.hasNext()) {
				System.out.println("Updated Document: " + i);
				System.out.println(cursor.next());
				i++;
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	// --------------------- Getters & Setters ----------------------//

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
