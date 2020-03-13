package com.whqfl.dao;

import com.whqfl.entity.Menu;

import java.util.List;

/**
 * @author
 * 菜单接口
**/
public interface MenuDao {
    /**
     * 通过roleId 获取 菜单
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(Integer roleId);
}
