package com.whqfl.service;

import com.whqfl.entity.Menu;

import java.util.List;
public interface MenuService1 {
    List<Menu> getMenuListByRoleId(Integer roleId) throws Exception;
}
