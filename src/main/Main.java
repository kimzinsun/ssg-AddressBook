package main;

import java.util.Scanner;

import dao.AddressDao;
import file.FileIO;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressDao dao = new AddressDao();

        while (true) {
            System.out.println("========= 주소록 프로그램 ==========");
            System.out.println("1.주소록추가");
            System.out.println("2.주소록삭제");
            System.out.println("3.주소록검색");
            System.out.println("4.주소록수정");
            System.out.println("5.주소록모두출력");
            System.out.println("6.주소록저장");
            System.out.println("7.주소록불러오기");

            System.out.print("메뉴의 번호 >> ");
            int menuNumber = sc.nextInt();

            switch (menuNumber) {
                case 1:
                    dao.insert();
                    break;
                case 2:
                    dao.delete();
                    break;
                case 3:
                    dao.select();
                    break;
                case 4:
                    dao.update();
                    break;
                case 5:
                    dao.allData();
                    break;
                case 6:
                    FileIO.save("address");
                    break;
                case 7:
                    FileIO.load("address");
                    break;
            }
        }

    }
}
