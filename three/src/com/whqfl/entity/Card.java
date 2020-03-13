package com.whqfl.entity;

import java.io.Serializable;

public class Card implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 会员卡ID
     * cardid
     */
    private Integer cardId;

    /**用户ID
     * userid
     */
    private Integer userId;
    /**
     * idCard
     * 身份证号
     */
    private String idCard;

    /**用户身份证
     * amount
     */
    private Double amount;

    /**
     * 卡积分
     */
    private Integer credit;

    /**
     * 状态(0:正常 1:挂失)
     */
    private Integer status;
    /**
     * 会员卡 等级ID
     */
    private Integer levelId;
    /**
     * 会员卡 等级名字
     */
    private String levelName;

    /**
     * 备注
     * momo
     */
    private  String  momo;
    /**
     * 会员卡持有者
     */
    private  String userName;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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

    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", userId=" + userId +
                ", idCard='" + idCard + '\'' +
                ", amount=" + amount +
                ", credit=" + credit +
                ", status=" + status +
                ", levelId=" + levelId +
                ", levelName='" + levelName + '\'' +
                ", momo='" + momo + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
