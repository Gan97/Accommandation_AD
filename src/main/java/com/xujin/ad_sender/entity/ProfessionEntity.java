package com.xujin.ad_sender.entity;

public class ProfessionEntity {
    private Integer ProfessionId;
    private String ProfessionName;

    public Integer getProfessionId() {
        return ProfessionId;
    }

    public void setProfessionId(Integer professionId) {
        ProfessionId = professionId;
    }

    public String getProfessionName() {
        return ProfessionName;
    }

    public void setProfessionName(String professionName) {
        ProfessionName = professionName;
    }

    @Override
    public String toString() {
        return "ProfessionEntity{" +
                "ProfessionId=" + ProfessionId +
                ", ProfessionName='" + ProfessionName + '\'' +
                '}';
    }
}