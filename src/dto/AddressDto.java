package dto;

import java.sql.Date;

public class AddressDto {
    private String name;
    private int age;
    private String phoneNum;
    private Date birth;
    private String memo;

    public AddressDto() {
    }

    public AddressDto(String name, int age, String phoneNum, Date birth, String memo) {
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
        this.birth = birth;
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Date getBirth() {
        return birth;
    }

    public String getMemo() {
        return memo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return name + "," + age + "," + phoneNum + "," + birth + "," + memo;
    }

    public void print() {
        System.out.println("이름: " + name + " 나이: " + age + " 전화번호: " + phoneNum + " 생일: " + birth + " 메모: " + memo);
    }

}