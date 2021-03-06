package com.whqfl.entity;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色等级
     */
    private Integer grade;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色状态(1启用 0禁用)
     */
    private Integer status;

    public Role() {
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", grade=" + grade +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}

