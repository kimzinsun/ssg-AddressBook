package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.AddressDto;
import singleton.SingletonClass;

public class AddressDao {
    Scanner sc = new Scanner(System.in);

    public AddressDao() {
    }

    public void insert() {
        System.out.println("주소 정보 입력입니다");

        System.out.print("이름 >> ");
        String name = sc.next();

        System.out.print("나이 >> ");

        while (!sc.hasNextInt()) {
            System.out.println("숫자만 입력해주세요");
            sc.next();
            System.out.print("나이 >> ");
        }
        int age = sc.nextInt();

        System.out.print("전화번호 >> ");

        String regex = "^\\d{3}-\\d{3,4}-\\d{4}$";
        String phoneNum = sc.next();
        while (!phoneNum.matches(regex)) {
            System.out.println("전화번호 형식이 아닙니다");
            System.out.print("전화번호 >> ");
            phoneNum = sc.next();
        }

        System.out.print("생일 (yyyy-MM-dd)>> ");
        String regex2 = "^\\d{4}-\\d{2}-\\d{2}$";
        String birthStr = sc.next();
        while (!birthStr.matches(regex2)) {
            System.out.println("생일 형식이 아닙니다");
            System.out.print("생일 (yyyy-MM-dd)>> ");
            birthStr = sc.next();
        }
        Date birth = Date.valueOf(birthStr);

        System.out.print("메모 >> ");
        String memo = sc.next();

        AddressDto dto = new AddressDto(name, age, phoneNum, birth, memo);

        SingletonClass sc = SingletonClass.getInstance();
        sc.list.add(dto);
    }

    public void delete() {
        System.out.println("삭제하고 싶은 주소록의 이름 >> ");
        String name = sc.next();

        List<AddressDto> findList = search(name);

        if (findList.isEmpty()) {
            System.out.println("주소록을 찾을 수 없습니다");
            return;
        }

        System.out.println("검색된 주소록:");
        for (int i = 0; i < findList.size(); i++) {
            System.out.printf("%d. ", i + 1);
            findList.get(i).print();
        }

        if (findList.size() > 1) {
            System.out.println("삭제할 주소록 번호를 선택하세요: ");
            int choice = sc.nextInt();

            if (choice < 1 || choice > findList.size()) {
                System.out.println("잘못된 선택입니다");
                return;
            }

            AddressDto selectedContact = findList.get(choice - 1);

            SingletonClass singleton = SingletonClass.getInstance();
            singleton.list.remove(selectedContact);

            System.out.println("삭제되었습니다");
        } else {
            SingletonClass singleton = SingletonClass.getInstance();
            singleton.list.remove(findList.get(0));

            System.out.println("삭제되었습니다");
        }
    }

    public void select() {
        System.out.print("검색방법 선택 (1.이름 2.전화번호 3.메모) >> ");

        while (!sc.hasNextInt()) {
            System.out.println("숫자만 입력해주세요");
            sc.next();
            System.out.print("검색방법 선택 (1.이름 2.전화번호 3.메모) >> ");
        }
        int menuNumber = sc.nextInt();

        switch (menuNumber) {
            case 1:
                selectByName();
                break;
            case 2:
                selectByPhoneNum();
                break;
            case 3:
                selectByMemo();
                break;
            default:
                System.out.println("잘못된 메뉴 선택");

        }
    }

    private void selectByName() {
        System.out.println("검색하고 싶은 주소록의 이름 >> ");
        String name = sc.next();

        List<AddressDto> findList = new ArrayList<>();

        SingletonClass singleton = SingletonClass.getInstance();
        for (AddressDto dto : singleton.list) {
            if (dto.getName().contains(name)) {
                findList.add(dto);
            }
        }

        printSearchResults(findList);
    }

    private void selectByPhoneNum() {
        System.out.println("검색하고 싶은 주소록의 전화번호 >> ");
        String phoneNumber = sc.next();

        List<AddressDto> findList = new ArrayList<>();

        SingletonClass singleton = SingletonClass.getInstance();
        for (AddressDto dto : singleton.list) {
            if (dto.getPhoneNum().contains(phoneNumber)) {
                findList.add(dto);
            }
        }

        printSearchResults(findList);

    }

    private void selectByMemo() {
        System.out.println("검색하고 싶은 주소록의 메모 >> ");
        String memo = sc.next();

        List<AddressDto> findList = new ArrayList<>();

        SingletonClass singleton = SingletonClass.getInstance();
        for (AddressDto dto : singleton.list) {
            if (dto.getMemo().contains(memo)) {
                findList.add(dto);
            }
        }

        printSearchResults(findList);
    }

    private void printSearchResults(List<AddressDto> findList) {
        if (findList.isEmpty()) {
            System.out.println("주소록을 찾을 수 없습니다");

            return;
        }

        System.out.println("검색하신 정보는 다음과 같습니다");
        for (AddressDto dto : findList) {
            dto.print();
        }
    }

    public void update() {
        System.out.println("수정하고 싶은 주소록의 이름 >> ");
        String name = sc.next();

        List<AddressDto> findList = search(name);

        if (findList.isEmpty()) {
            System.out.println("주소록을 찾을 수 없습니다");
            return;
        }

        System.out.println("검색된 주소록:");
        for (int i = 0; i < findList.size(); i++) {
            System.out.printf("%d. ", i + 1);
            findList.get(i).print();
        }

        if (findList.size() > 1) {
            System.out.println("수정할 주소록 번호를 선택하세요: ");
            int choice = sc.nextInt();

            if (choice < 1 || choice > findList.size()) {
                System.out.println("잘못된 선택입니다");
                return;
            }

            AddressDto selectedContact = findList.get(choice - 1);

            System.out.print("새로운 이름 >> ");
            String newName = sc.next();
            System.out.print("새로운 전화번호 >> ");
            String newPhoneNum = sc.next();
            System.out.print("새로운 메모 >> ");
            String newMemo = sc.next();

            selectedContact.setName(newName);
            selectedContact.setPhoneNum(newPhoneNum);
            selectedContact.setMemo(newMemo);

            System.out.println("수정되었습니다");
        } else {
            AddressDto selectedContact = findList.get(0);

            System.out.print("새로운 이름 >> ");
            String newName = sc.next();
            System.out.print("새로운 전화번호 >> ");
            String newPhoneNum = sc.next();
            System.out.print("새로운 메모 >> ");
            String newMemo = sc.next();

            selectedContact.setName(newName);
            selectedContact.setPhoneNum(newPhoneNum);
            selectedContact.setMemo(newMemo);

            System.out.println("수정되었습니다");
        }
    }

    public List<AddressDto> search(String name) {
        List<AddressDto> findList = new ArrayList<>();

        SingletonClass sc = SingletonClass.getInstance();
        for (AddressDto dto : sc.list) {
            if (dto.getName().contains(name)) {
                findList.add(dto);
            }
        }

        return findList;
    }

    public void allData() {
        SingletonClass sc = SingletonClass.getInstance();

        for (int i = 0; i < sc.list.size(); i++) {
            AddressDto dto = sc.list.get(i);
            dto.print();
        }
    }
}
