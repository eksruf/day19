package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClass {
	
	Connection con; //연결이 이루어진 객체
	
	PreparedStatement ps; //쿼리문 전송을 하기 위한 전송 객체
	
	ResultSet rs; //select의 결과 값을 받기 위한 객체
	
	public DBClass() {
		System.out.println("생성자 실행");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//자바에서 오라클 명령어를 수행하기 위한 기능을 등록하는 과정
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con=DriverManager.getConnection(url,"java2","1234");
			//오라클 연결하는 과정
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public int insert (Info info) {
		String sql="insert into newst values(?,?,?)"; //?= 나중에 채워넣을거다
	
		int result =0;
		
		try {	//연결된 객체를 이용해서 명령어 전송객체를 얻어온다
			ps = con.prepareStatement(sql);
			
			ps.setString(1,info.getId());  //?자리에 각 값을 채워주는 역할(숫자는 물음표의 위치)
			ps.setString(2,info.getName());
			ps.setInt(3,info.getAge());
			//명령어를 전송하겠다는 의미이다
			//executeUpdate 보통 select를 제외한 나머지에 사용
			//성공이면 1 , 실패하면0 또는 에러
			result= ps.executeUpdate();
			
		} catch (Exception e) {
			//e.printStackTrace(); //주석으로 코드를 가리면 sql에러경고가 나오지 않음
			
		}
		return result;
	}

	
	public Info selectOne(String id) {
		String sql = "select * from newst where id='"+id+"'";//물음표가 하나일때(id=?) 편하게 바로 입력
		Info info = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				info = new Info();
				info.setId(rs.getString("id"));
				info.setName(rs.getString("name"));
				info.setAge(rs.getInt("age"));
				
//				System.out.println(rs.getString("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getInt("age"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
		
		
	}
	
	public ArrayList<Info> select() {
		String sql = "select *from newst"; //쿼리문을 실행 전 디비에서 확인 후 그대로 작성
		ArrayList<Info> list = new ArrayList<Info>();
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Info info = new Info();
				info.setId(rs.getString("id"));
				info.setName(rs.getString("name"));
				info.setAge(rs.getInt("age"));
				list.add(info);
//				System.out.println(rs.getString("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getInt("age"));
//				System.out.println("---------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
