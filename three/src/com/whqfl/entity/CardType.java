package com.whqfl.entity;

import java.io.Serializable;

public class CardType implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 会员等级名字
     * name
     */
    private String name;

    /**
     * 升级所需积分
     */
    private Integer rank;

    /**会员卡状态
     * status
     */
    private Integer status;

    /**
     * 消费折扣系数
     */
    private Double coefficient;

    /**
     * 会员等级ID
     * level
     */
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "CardType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", status=" + status +
                ", coefficient=" + coefficient +
                ", level=" + level +
                '}';
    }
}
