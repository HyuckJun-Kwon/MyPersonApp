package com.person.view;

import java.util.List;
import com.person.model.Person;

public class PersonConsoleView {
	public void showAllList(List<Person> list) {
		
		for(Person p : list) {
			System.out.println(p.getName() + "\t" + p.getAddress() + "\t" + p.getPhone());
		}
	}
	
	public void showMessage(String message) {
		System.out.println("[알림]" + message);
	}

	public void saveToFile(List<Person> selectAllPerson, String file_name) {
		try(java.io.FileWriter fw = new java.io.FileWriter(file_name)){
			for(Person p : selectAllPerson) {
				fw.write(p.getName() + "\t" + p.getAddress() + "\t" + p.getPhone());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
