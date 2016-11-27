package com.js.oldmarket.bean;

import java.io.Serializable;

/*
* Class name :User
*
* Version information :

*
* Describe ：
*
* Author ：裴徐泽
*
* Created by pei on 2016-4-16.
*
*/
public class User implements Serializable {
    private String Username;//id
    private String Name;//不知道是啥
    private String MobilePhone;
    private String password;
    private String PictureUrl;
    private String StudentNo;
    private String Nickname;
    private String Sex;
    private String CollegeID;
    private String CollegeName;//学校
    private boolean IsMobileValidated;
    private String IMEI;

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    public String getStudentNo() {
        return StudentNo;
    }

    public void setStudentNo(String studentNo) {
        StudentNo = studentNo;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getCollegeID() {
        return CollegeID;
    }

    public void setCollegeID(String collegeID) {
        CollegeID = collegeID;
    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String collegeName) {
        CollegeName = collegeName;
    }

    public boolean isMobileValidated() {
        return IsMobileValidated;
    }

    public void setMobileValidated(boolean mobileValidated) {
        IsMobileValidated = mobileValidated;
    }

}
