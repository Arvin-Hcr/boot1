package com.whqfl.entity;

import java.io.Serializable;

public class Rechargerecord implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * cardid
     */
    private Integer cardId;

    /**
     * 充值金额
     */
    private Double rechargeAmount;

    /**
     * 充值后卡余额
     */
    private Double afterAmount;

    /**
     * 充值前卡余额
     */
    private Double beforeAmount;

    /**
     * 充值规则
     */
    private Integer ruleId;

    /**
     * 充值时间
     */
    private String createdTime;

    /**
     * 操作员
     */
    private Integer staffId;

    /**
     * 备注
     */
    private String userName;
    private String momo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Double getAfterAmount() {
        return afterAmount;
    }

    public void setAfterAmount(Double afterAmount) {
        this.afterAmount = afterAmount;
    }

    public Double getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(Double beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Rechargerecord() {
    }

    @Override
    public String toString() {
        return "Rechargerecord{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", rechargeAmount=" + rechargeAmount +
                ", afterAmount=" + afterAmount +
                ", beforeAmount=" + beforeAmount +
                ", ruleId=" + ruleId +
                ", createdTime='" + createdTime + '\'' +
                ", staffId=" + staffId +
                ", userName='" + userName + '\'' +
                ", momo='" + momo + '\'' +
                '}';
    }
}
