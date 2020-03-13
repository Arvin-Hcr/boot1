package com.whqfl.service.impl;

import com.whqfl.dao.MenuDao;
import com.whqfl.dao.MenuDao1;
import com.whqfl.dao.impl.MenuDaoImpl;
import com.whqfl.dao.impl.MenuDaoImpl1;
import com.whqfl.entity.Menu;
import com.whqfl.service.MenuService1;
import com.whqfl.util.BusinessException;

import java.util.List;

public class MenuServiceImpl1 implements MenuService1 {
    private MenuDao1 menuDao = new MenuDaoImpl1();
    @Override
    public List<Menu> getMenuListByRoleId(Integer roleId) throws Exception {
        if (roleId == null || roleId == 0){
            throw new BusinessException("角色ID不能为空");
        }
        return menuDao.getMenuListByRoleId(roleId);
    }
}
