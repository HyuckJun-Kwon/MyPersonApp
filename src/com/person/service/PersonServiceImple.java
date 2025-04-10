package com.person.service;

import java.util.List;
import com.person.model.Person;
import com.person.dao.PersonDao;
import com.person.dao.PersonDaoImple;

//biz, dao 관리 및 구현
//현재 Person 테이블의 insert 값은 계산형 데이터 또는 로직이 없어서 dao만 호출되어 controller, db 연동
//controller -> service(biz X, dao O) -> db
public class PersonServiceImple implements PersonService {

	private final PersonDao dao = new PersonDaoImple();
	
	@Override
	public List<Person> selectAllPerson() {
		return dao.selectAllPerson();
	}

	@Override
	public int insertPerson(Person p) {
		return dao.insertPerson(p);
	}
	
	@Override
	public int deletePerson(Person p) {
		return dao.deletePerson(p);
	}
	
	@Override
	public int updatePerson(Person p, String oldName) {
		return dao.updatePerson(p, oldName);
	}

	@Override
	public Person searchByName(Person p) {
		return dao.searchByName(p);
	}

	@Override
	public List<Person> getPersonByPage(int page, int size) {
		return dao.getPersonByPage(page, size);
	}

}
