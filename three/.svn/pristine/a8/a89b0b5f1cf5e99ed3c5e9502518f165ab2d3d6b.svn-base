package com.whqfl.service.impl;

import com.whqfl.dao.MenuDao;
import com.whqfl.dao.impl.MenuDaoImpl;
import com.whqfl.entity.Menu;
import com.whqfl.service.MenuService;
import com.whqfl.util.BusinessException;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao = new MenuDaoImpl();
    @Override
    public List<Menu> getMenuListByRoleId(Integer roleId) throws BusinessException {
        if (roleId == null || roleId == 0){
            throw new BusinessException("角色ID不能为空");
        }
        return menuDao.getMenuListByRoleId(roleId);
    }
}
