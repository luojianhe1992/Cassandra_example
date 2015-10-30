import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GettingStarted {

	public static void main(String[] args) {
	
		Cluster cluster;
		Session session;
		
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		
		try {
			session = cluster.connect("demo");
			
			session.execute("create table users(lastname varchar, age int, city varchar, email varchar, firstname varchar primary key)");
			
			// call the execute function in the session object
			session.execute("INSERT INTO users (lastname, age, city, email, firstname) VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob')");
			
			System.out.println("****************************************");
			
			ResultSet results = session.execute("SELECT * FROM users");

			for (Row row : results) {
				System.out.format("%s %s %d\n", row.getString("firstname"), row.getString("lastname"), row.getInt("age"));
			}
			
			System.out.println("****************************************");
		
			
			
			System.out.println("using api.");
			System.out.println("*************************************************************");
			
			Student.create_table(session);
			
			Student.add_element("Jianhe", "Luo", 18, 60);
			Student.add_element("Xingyu", "Yan", 19, 100);
			
			ResultSet results2 = Student.show_all();
			
			for (Row row : results2) {
				System.out.format("%s %s \n", row.getString("first_name"), row.getString("last_name"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		cluster.close();
		
		
	}
	
}
