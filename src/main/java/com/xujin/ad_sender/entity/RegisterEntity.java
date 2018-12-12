package com.xujin.ad_sender.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2018/3/16
 * \* Time: 21:18
 * \
 */
public class RegisterEntity {
    private Integer UserID;
    private String UserName;
    private String PassWord;
    private Integer Profession;
    private String Sex;
    private Integer Age;
    private String Hobby;

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public Integer getProfession() {
        return Profession;
    }

    public void setProfession(Integer profession) {
        Profession = profession;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getHobby() {
        return Hobby;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }


    @Override
    public String toString() {
        return "RegisterEntity{" +
                "UserID=" + UserID +
                ", UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                ", Profession='" + Profession + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age=" + Age +
                ", Hobby='" + Hobby + '\'' +
                '}';
    }
}