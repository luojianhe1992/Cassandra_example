
import com.datastax.driver.core.Session;
import com.datastax.driver.core.ResultSet;


public class Student {
	
	
	static Session session = null;
	static int max_id = 0;
	
	private int id;
	String first_name;
	String last_name;
	int age;
	int grade;
	
	public Student() {
		super();
		max_id++;
	}

	
	public Student(int id, String first_name, String last_name, int age,
			int grade) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.grade = grade;
	}

	public static int getMax_id() {
		return max_id;
	}

	public static void setMax_id(int max_id) {
		Student.max_id = max_id;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [first_name=" + first_name + ", last_name=" + last_name
				+ ", age=" + age + ", grade=" + grade + "]";
	}
	
	
	public static void create_table(Session session){
		
		// assign the session		
		Student.session = session;
		session.execute("create table Student(id int primary key, first_name varchar, last_name varchar, age int, grade int)");
	}
	
	
	
	public static void add_element(String first_name, String last_name, int age, int grade){
		
		String insert_string = "INSERT INTO Student (id, first_name, last_name, age, grade)";
		
		Student.max_id++;
		
		String value_string = "VALUES(" + Student.max_id + ", '" + first_name + "', '" + last_name + "', " + age + ", " + grade + ")";
		
		String execute_string = insert_string + value_string;
				
		Student.session.execute(execute_string);
	}
	
	public static ResultSet show_all(){
		
		String execute_string = "SELECT * FROM Student";
		
		return Student.session.execute(execute_string);
	}
	
	
	
	
	
	
	
}
