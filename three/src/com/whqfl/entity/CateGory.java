package com.whqfl.entity;

import java.io.Serializable;

public class CateGory implements Serializable {
/**
 * 商品的类别实体类
*/
//实体类
private  Integer id;
//类别名称
private String name;
// 类别状态
private Integer status;
// 备注
private String momo;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    @Override
    public String toString() {
        return "CateGory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", momo='" + momo + '\'' +
                '}';
    }
}
