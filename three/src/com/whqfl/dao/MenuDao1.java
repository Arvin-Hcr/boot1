package com.whqfl.dao;

import com.whqfl.entity.Menu;

import java.util.List;

public interface MenuDao1 {
    List<Menu> getMenuListByRoleId(Integer roleId);
}
