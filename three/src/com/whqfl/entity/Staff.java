package com.whqfl.entity;

import java.io.Serializable;


    public class Staff implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * id
         */
        private Integer id;

        /**
         * 员工id
         */
        private Integer staffId;

        /**
         * 员工姓名
         */
        private String staffName;

        /**
         * 登陆密码
         */
        private String password;

        /**
         * 员工电话
         */
        private String phone;

        /**
         * 员工身份证号码
         */
        private String idCard;

        /**
         * 员工地址
         */
        private String address;

        /**
         * 入职日期
         */
        private String createdTime;

        /**
         * 员工状态(1.在职 2.离职 3.黑名单)
         */
        private Integer status;

        /**
         * roleid
         */
        private Integer roleId;

        /**
         * 备注
         */
        private String momo;

        /**
         * enable
         */
        private Integer enable;

    /**
     * 这个是职位名称
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleid() {
        return roleId;
    }

    public void setRoleid(Integer roleid) {
        this.roleId = roleid;
    }

    public String getMomo() {
        return momo;
    }

    public void setMomo(String momo) {
        this.momo = momo;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", status=" + status +
                ", roleid=" + roleId +
                ", momo='" + momo + '\'' +
                ", enable=" + enable +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}


