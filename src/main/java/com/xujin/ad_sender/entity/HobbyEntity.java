package com.xujin.ad_sender.entity;

public class HobbyEntity {
    private Integer HobbyID;
    private String HobbyName;

    public Integer getHobbyID() {
        return HobbyID;
    }

    public void setHobbyID(Integer hobbyID) {
        HobbyID = hobbyID;
    }

    public String getHobbyName() {
        return HobbyName;
    }

    public void setHobbyName(String hobbyName) {
        HobbyName = hobbyName;
    }

    @Override
    public String toString() {
        return "HobbyEntity{" +
                "HobbyID=" + HobbyID +
                ", HobbyName='" + HobbyName + '\'' +
                '}';
    }
}

