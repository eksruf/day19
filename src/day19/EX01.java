package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

class DB{
	Connection con;
	 PreparedStatement ps;
	
	public DB() {//DB연결하는 것
		//System.out.println("생성자 실행"); 잘돌아감
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//자바에서 오라클 명령어를 수행하기 위한 기능을 등록하는 과정
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con=DriverManager.getConnection(url,"java2","1234");
			//오라클 연결하는 과정
			
			
		} catch (Exception e) { e.printStackTrace(); }
		}//DB메소드 끝(class아님)
	
	public int insert(Info info) {
		String sql = "insert into newst values(?,?,?)";
		int result =0;
		
		try {
		ps = con.prepareStatement(sql);	
		
		ps.setString(1,info.getId());
		ps.setString(2, info.getName());
		ps.setInt(3, info.getAge());
		
		result = ps.executeUpdate();
		} catch (Exception e) {}
		return result;
		}//insert 메소드 끝
	
	
	
	
	public void select() {
		String sql = "select * from newst where id=?";
		Info info = null;  
		
		
	}
	
	
	
	
}//class DB 끝

class Info1{
	private String name;
	private String id;
	private int age;
	
	public String getId() {return id;}
	public void setId(String id) {this.id=id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name=name;}
	
	public int getage() {return age;}
	public void setAge(int age) {this.age=age;}
	
}




//메인 시작점
public class EX01 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	DB db = new DB();
	Info1 info = new Info1();
	String name,id;
	int num,age;
	
	System.out.println("1.등록");
	System.out.println("2.조회");
	System.out.println("3.모두조회");
	num = input.nextInt();
	
	switch(num) {
	case 1: 
		System.out.println("이름");
		name = input.next();
		System.out.println("id");
		id = input.next();
		System.out.println("age:");
		age= input.nextInt();
		
		info.setName(name);
		info.setId(id);
		info.setAge(age);
		
		
		
		break;
	case 2: break;
	case 3: break;
	}
}
}
