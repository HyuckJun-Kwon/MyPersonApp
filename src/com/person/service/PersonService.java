package com.person.service;

import java.util.List;
import com.person.model.Person;

public interface PersonService {
	
	List<Person> selectAllPerson();
	int insertPerson(Person p);
	int deletePerson(Person p);
	int updatePerson(Person p, String oldName);
	Person searchByName(Person p);
	List<Person> getPersonByPage(int page, int size);

}
