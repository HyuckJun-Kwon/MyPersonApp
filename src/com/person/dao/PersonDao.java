package com.person.dao;

import java.util.List;
import com.person.model.Person;

//DB CRUD
// view -> controller -> dao -> db
// view <- controller <- dao <- db

public interface PersonDao {
	String insert_sql = "insert into person (name, address, phone) values(?,?,?)";
	String update_sql = "update person set name=?, address=?, phone=? where name=?";
	String delete_sql = "delete from person where name=?";
	String select_sql = "select name, address, phone from person";
	String find_sql = "select name, address, phone from person where name=?";
	String page_sql = "select name, address, phone from  person limit ? OFFSET ? ;";
	
	public int deletePerson(Person p);
	public int insertPerson(Person p);
	public int updatePerson(Person p, String oldName);
	public List<Person> selectAllPerson();
	public Person searchByName(Person p);
	public List<Person> getPersonByPage(int page, int size);
}
