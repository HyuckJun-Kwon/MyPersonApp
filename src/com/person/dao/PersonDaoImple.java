package com.person.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.person.model.Person;
import static common.JDBCTemplate.*;

//DB CRUD
// view -> controller -> dao -> db
// view <- controller <- dao <- db

public class PersonDaoImple implements PersonDao {
	//1. insert
	public int insertPerson(Person p) {
		int res = 0;
		Connection conn = getConnection();
		PreparedStatement pstm = null;
				
		try {
			pstm = conn.prepareStatement(insert_sql);  //쿼리 준비
			pstm.setString(1, p.getName());
			pstm.setString(2, p.getAddress());
			pstm.setString(3, p.getPhone());
			 
			res = pstm.executeUpdate();
			commit(conn);
		} 
		catch (SQLException e) {
			rollback(conn);
			System.out.println("SQL 삽입 실패!!");
			e.printStackTrace();
		}
		finally {
			Close(conn);
			Close(pstm);
		}
		
		return res;
	}
	
	//2. delete
	public int deletePerson(Person p) {
		int res = 0;
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(delete_sql);
			pstm.setString(1, p.getName());
			res = pstm.executeUpdate();
			commit(conn);
		} 
		catch (SQLException e) {
			rollback(conn);
			System.out.println("SQL 제거 실패!!");
			e.printStackTrace();
		}
		finally {
			Close(conn);
			Close(pstm);
		}
		
		return res;
	}
	
	//3. update
	public int updatePerson(Person p, String oldName) {
		int res = 0;
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(update_sql);
			pstm.setString(1, p.getName());
			pstm.setString(2, p.getAddress());
			pstm.setString(3, p.getPhone());
			pstm.setString(4, oldName);

	        res = pstm.executeUpdate();
	        commit(conn);
	    } 
		catch (SQLException e) {
			rollback(conn);
	        System.out.println("SQL 수정 실패!!");
	        e.printStackTrace();
	    } 
		finally {
	        Close(conn);
	        Close(pstm);
	    }

	    return res;
	}
	
	//4. select
	public List<Person> selectAllPerson() {
		List<Person> all = new ArrayList<>();// 전체 레코드를 Person으로 담아서 리턴
		Connection conn = getConnection();//연결
		Statement stmt = null;//명령 객체 선언
		ResultSet rs = null;//명령 실행 결과 select를 참조할 객체 선언
		
		//Person person = null; - case 2 이용 시 필요
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(select_sql);
			 			 
			 while(rs.next()) {//로우데이터가 있으면 한줄씩 읽어서 리턴해줘
				 //매개인자 생성자로 값을 전달 후 add
				 all.add(new Person(
						 rs.getString(1), 
						 rs.getString(2), 
						 rs.getString(3)));
				 
				 /*case 2 : 기본  생성자 객체 생성 후 setter로 값 전달 후 add
				 person = new Person();
				 person.setName(rs.getString(1));
				 person.setAddress(rs.getString(2));
				 person.setPhone(rs.getString(3));
				 all.add(person);
				 */
			 }
		} 
		catch (Exception e) {
			System.out.println("SQL 조회 실패!!" + e);
		}
		finally {
			Close(rs);
			Close(stmt);
			Close(conn);
		}
		
		return all;
	}

	@Override
	public Person searchByName(Person p) {
		Connection conn = getConnection();//연결
		PreparedStatement pstmt = null;//명령 객체 선언
		ResultSet rs = null;//명령 실행 결과 select를 참조할 객체 선언
		Person person = null;
		
		try {
			pstmt = conn.prepareStatement(find_sql); //쿼리준비
			pstmt.setString(1, p.getName());//데이터 바인딩
			
			rs = pstmt.executeQuery();//쿼리 실행
			 			
			while(rs.next()) {
				person = new Person();
				person.setName(rs.getString(1));
				person.setAddress(rs.getString(2));
				person.setPhone(rs.getString(3));
			}	 
				 
		}
		catch (Exception e) {
			System.out.println("SQL 조회 실패!!" + e);
		}
		finally {
			Close(rs);
			Close(pstmt);
			Close(conn);
		}
		
		return person;
	}

	@Override
	public List<Person> getPersonByPage(int page, int size) {
		List<Person> list = new ArrayList<>();
	    Connection conn = getConnection();
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    Person person = null;

	    try {
	        pstm = conn.prepareStatement(page_sql);
	        pstm.setInt(1, page);
	        pstm.setInt(2, size);

	        rs = pstm.executeQuery();

	        while (rs.next()) {
	            person = new Person();
	            person.setName(rs.getString(1));
	            person.setAddress(rs.getString(2));
	            person.setPhone(rs.getString(3));
	            list.add(person);
	        }
	    } catch (SQLException e) {
	        System.out.println("페이징 조회 실패!!");
	        e.printStackTrace();
	        
	    } finally {
	        Close(rs);
	        Close(pstm);
	        Close(conn);
	    }

	    return list;
	}


}
