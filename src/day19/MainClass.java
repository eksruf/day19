package day19;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
public static void main(String[] args) {
	DBClass db = new DBClass();
	Scanner input = new Scanner(System.in);
	String id,name;
	int age, num, result=0;  //데이터베이스 형식(테이블)에 맞춰서 변수 설정
	
	Info info = new Info();
	
	while(true) {
		System.out.println("1.등록");
		System.out.println("2.조회");
		System.out.println("3.모든 목록");
		System.out.println("4.종료");
		num= input.nextInt();
		switch(num) {
		case 1:
			System.out.println("아이디입력");
			id = input.next();
			System.out.println("이름입력");
			name = input.next();
			System.out.println("나이입력");
			age = input.nextInt();
			
			info.setId(id);
			info.setName(name);
			info.setAge(age);
			
			result= db.insert(info);
			
			if(result ==1) {
				System.out.println("저장성공");
				
			}else {
				System.out.println("저장실패");
			}
			break;
			
			
		case 2:
			System.out.println("검색 아이디 입력");
			id= input.next();
			info = db.selectOne(id);
			if(info == null) {
				System.out.println("해당 아이디는 없음");
			}else {
				System.out.println("아이디: "+info.getId());
				System.out.println("이름: "+info.getName());
				System.out.println("나이: "+info.getAge());
				}
			
			break;
			
			
		case 3: 
			
			ArrayList<Info> list = db.select();
			if(list.size()==0) {
				System.out.println("저장 정보 없음");
			}else {
					/*
					for(int i=0; i<list.size();i++) {
				
					info = list.get(i);
					//System.out.println("id: "+list.get(i).getId());
					System.out.println("id : "+info.getId());
					System.out.println("name : "+info.getName());
					System.out.println("age : "+info.getAge());
					System.out.println("---------------");
				}
				*/	
					
				for(Info in : list) {
					System.out.println(in.getId());
					System.out.println(in.getName());
					System.out.println(in.getAge());
					System.out.println("---------------");
				}
			}
			break;
			
			
		case 4: break;
		}//스위치 끝
	}//while 끝

	}//main 끝
}
