package com.whqfl.entity;


import com.whqfl.util.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**订单表
 */
public class Order implements Serializable {

    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 卡编号
     */
    private Integer cardId;
    /**
     * 卡类型
     */
    private Integer cardType;
    /**
     * 商品价格
     */
    private  Double price;
    /**
     * 实付金额
     */
    private  Double pay;
    /**
     * 商品积分
     */
    private  Integer credit;
    /**
     * 订单状态
     */
    private  Integer status;
    /**
     * 备注
     */
    private  String momo;
    /**
     * 消费时间
     */
    private  String createdTime;

    private String levelName;


    public Integer getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public double getPrice() {
        return price;
    }

    public double getPay() {
        return pay;
    }

    public Integer getCredit() {
        return credit;
    }

    public String getMomo() {
        return momo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = DateUtils.toFormat(createdTime);
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", cardId=" + cardId +
                ", cardType=" + cardType +
                ", price=" + price +
                ", pay=" + pay +
                ", credit=" + credit +
                ", status=" + status +
                ", momo='" + momo + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
