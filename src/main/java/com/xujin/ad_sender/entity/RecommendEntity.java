package com.xujin.ad_sender.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2018/3/30
 * \* Time: 01:36
 * \
 */
public class RecommendEntity {
    private ADInfoEntity adInfoEntity;
    private float similer;

    public ADInfoEntity getAdInfoEntity() {
        return adInfoEntity;
    }

    public void setAdInfoEntity(ADInfoEntity adInfoEntity) {
        this.adInfoEntity = adInfoEntity;
    }

    public float getSimiler() {
        return similer;
    }

    public void setSimiler(float similer) {
        this.similer = similer;
    }

    @Override
    public String toString() {
        return "RecommendEntity{" +
                "adInfoEntity=" + adInfoEntity +
                ", similer=" + similer +
                '}';
    }
}
