package com.person.controller;

import com.person.model.*;
import com.person.controller.*;
import com.person.service.*;
import com.person.view.*;
import java.util.List;
import java.util.Scanner;

public class PersonController {
	private final PersonService service = new PersonServiceImple();
	private final PersonConsoleView view = new PersonConsoleView();
	private final Scanner scanner = new Scanner(System.in);
	
	public void run() {
		
		while(true) {
			System.out.println("1. 전체 출력 | 2. 추가 | 3. 삭제 | 4. 수정 | 5. 찾기 | 6. 파일저장 | 7. 페이징 | 0. 종료");
			String menu = scanner.nextLine();
			switch(menu) {
			case "1" : //서비스 컴포넌트가 받은 dao의 결과를 view에게 전달
				view.showAllList(service.selectAllPerson());
				break;
				
			case "2" : 
				System.out.print("이름 : ");
				String name = scanner.nextLine();
				
				System.out.print("주소 : ");
				String address = scanner.nextLine();
				
				System.out.print("전화번호 : ");
				String phone = scanner.nextLine();
				
				Person p = new Person(name, address, phone);
				
				int res = service.insertPerson(p);
				view.showMessage(res > 0 ? "입력 성공" : "실패");
				break;
				
			case "3" : 
				System.out.print("삭제 할 이름 : ");
				String delete_name = scanner.nextLine();
				
				Person p0 = new Person();
				p0.setName(delete_name);
				
				view.showMessage(service.deletePerson(p0) > 0 ? "삭제 성공" : "실패");
				break;
			
			case "4" :
				System.out.print("기존 이름 : ");
				String oldName = scanner.nextLine();
				
				System.out.print("수정 이름 : ");
				String uname = scanner.nextLine();
				
				System.out.print("수정 주소 : ");
				String uaddress = scanner.nextLine();
				
				System.out.print("수정 전화번호 : ");
				String uphone = scanner.nextLine();
				
				Person p02 = new Person(uname, uaddress, uphone);
				
				int res02 = service.updatePerson(p02, oldName);
				view.showMessage(res02 > 0 ? "수정 성공" : "실패");
				break;
				
			case "5" : 
				System.out.print("검색할 이름 : ");
				String find_name = scanner.nextLine();
				Person p03 = new Person();
				p03.setName(find_name);
				
				Person found = service.searchByName(p03);
				
				if(found != null) {
					view.showMessage("검색 결과가 없습니다.");
				}
				break;
			
			case "6" :
				System.out.print("저장할 파일명을 입력하세요 : ");
				String file_name = scanner.nextLine();
				view.saveToFile(service.selectAllPerson(), file_name);
				break;
				
			case "7" :
				System.out.print("출력 개수 : ");
				int page = Integer.parseInt(scanner.nextLine());
				System.out.print("시작 번호 : ");
				int size = (Integer.parseInt(scanner.nextLine())-1);
				
				List<Person> pageList = service.getPersonByPage(page, size);
				view.showAllList(pageList);
				break;
				
			case "0" : 
				view.showMessage("종료합니다.");
				return;
				
			default : 
				view.showMessage("잘못된 입력입니다.");
			}
			
		}
		
	}

}
